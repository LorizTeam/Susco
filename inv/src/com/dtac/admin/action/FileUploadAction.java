//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.0/xslt/JavaClass.xsl
package com.dtac.admin.action;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dtac.admin.data.DBFileUpload;
 /** 
 * MyEclipse Struts
 * Creation date: 10-12-2011
 */
public class FileUploadAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//10-12-2011	
		String forwardText  = "success";
		String loginId		= "";
		//String appCode 		= "hr25";
		try {
			HttpSession session = request.getSession();
			if (session.isNew()) {
				session.invalidate();
				request.setAttribute("alertMessage","Session Timeout. Login again.");
				return mapping.findForward("relogin");
			} else {
				loginId = (String) session.getAttribute("loginId");
				if (loginId == null) {
					request.setAttribute("alertMessage","Please Login.");
					return mapping.findForward("relogin");
				}
				
			}
			/*DBLogin dbLogin = new DBLogin();			
			if (!dbLogin.CheckAppAuth(loginId, appCode, "mant")) {
				request.setAttribute("alertMessage","You don't have authorize.");
				return mapping.findForward("alertmsg");
			}*/
		    
			//==============================================================
			String err = " ";
		    String picNo = (String) request.getParameter("picNo");
		    String idCode = new String(request.getParameter("idCode").getBytes("ISO8859_1"),"utf-8");		    	
		    String picPathName 	  = (String) request.getParameter("pathName");
		    String firstPicPathName = picPathName;
		    
		    String picSubPathName = "";
			if (request.getParameter("subPathName") != null) 
				picSubPathName = (String) request.getParameter("subPathName");
				
			String addrType = "";
			if (request.getParameter("addrType") != null) addrType  = (String) request.getParameter("addrType");

			String addrNo = "";
			if (request.getParameter("addrNo") != null) addrNo  = (String) request.getParameter("addrNo");

		 	String contentType = request.getContentType();
			String boundary = " ";
		    final int BOUNDARY_WORD_SIZE = "boundary=".length();
		    
		    //System.out.println("len11++"+request.getContentLength());
		    /*if (request.getContentLength() > 100000) {
		    	request.setAttribute("alertMessage", "file size limit 100K");
		    	return mapping.findForward(forwardText);
		    }*/
		    
		    
		    if (contentType == null || !contentType.startsWith("multipart/form-data")) {
			    err = "Ilegal ENCTYPE : must be multipart/form-data\n";
			    err += "ENCTYPE set = " + contentType;
			    
		    } else {
		    	boundary = contentType.substring(contentType.indexOf("boundary=") + BOUNDARY_WORD_SIZE);
		    	boundary = "--" + boundary;
		    	//System.out.println("contentType++"+boundary);
		    	try {
			        javax.servlet.ServletInputStream sis = request.getInputStream();
			        byte[] b = new byte[1024];
			        int x = 0, state = 0;
			        String name = null, fileName = null, contentType2 = null;
			        java.io.FileOutputStream buffer = null;
		        		        
			        while((x=sis.readLine(b,0,1024))>-1) {
			        	String s = new String(b,0,x);
			        	
			        	if(s.startsWith(boundary)) {
			        		state = 0;
			        		name = null; contentType2 = null; fileName = null;
			        		
		 		 		} else if(s.startsWith("Content-Disposition") && state==0) {
		    
		 		 			state = 1;
		 		 			if (s.indexOf("filename=") == -1)
		 		 				name = s.substring(s.indexOf("name=") + "name=".length(),s.length()-2);
		 		 			
		 		 			else {		 		 						 		 				
		 		 				name = s.substring(s.indexOf("name=") + "name=".length(),s.lastIndexOf(";"));
		 		 				fileName = s.substring(s.indexOf("filename=") + "filename=".length(),s.length()-2); 

		 		 				//fileName = new String(fileName.getBytes("ISO8859_1"),"utf-8");
		 		 				//fileName = URLDecoder.decode(fileName,"UTF-8");
		 		 				//System.out.println("fileName-"+fileName);
		 		 				//System.out.println("name+"+fileName.substring(fileName.length()-4, fileName.length()-1));

		 		 				if (!fileName.substring(fileName.length()-4, fileName.length()-1).toUpperCase().equals("JPG") &&
		 		 					!fileName.substring(fileName.length()-4, fileName.length()-1).toUpperCase().equals("PDF")) {
		 		 			    	request.setAttribute("alertMessage", "only jpg or pdf file");
		 		 			    	return mapping.findForward(forwardText);	
		 		 				}
		 		 				
		 		 				if (fileName.equals("\"\"")) {
		 		 					fileName = null;
		 		 					
		 		 				} else {
		 		 					String userAgent = request.getHeader("User-Agent");
		 		 					String userSeparator="/";  // default
		 		 					if (userAgent.indexOf("Windows")!=-1) userSeparator="\\";
		 		 					if (userAgent.indexOf("Linux")!=-1) userSeparator="/";
		 		 					fileName = fileName.substring(fileName.lastIndexOf(userSeparator)+1,fileName.length()-1);
		 		 					if (fileName.startsWith( "\"")) fileName = fileName.substring(1);
		 		 				}
		 		 			}
		 		 			name = name.substring(1,name.length()-1);
		 		 			if (name.equals("file")) {
		 		 				if (buffer != null) buffer.close();
		 		 				DBFileUpload dbFileUpload = new DBFileUpload();
		 		 				String pathName = dbFileUpload.GetPicturePath();
		 		 				
		 		 				String salePathName = "";
		 		 				if (picPathName.equals("saleorder")) {
		 		 					salePathName = "\\"+idCode.substring(0,2)+"-"+idCode.substring(2,6)+
		 		 					"-"+idCode.substring(6,11);
		 		 					
		 		 				} else if (picPathName.equals("customer")) {
		 		 					salePathName = "\\"+picSubPathName;
		 		 					
		 		 				} else if (picPathName.equals("customermap")) {
		 		 					picPathName	= "customer";
		 		 					salePathName = "\\"+picSubPathName;		
		 		 					fileName = addrType+addrNo+"_"+fileName;
		 		 					
		 		 				} else if (picPathName.equals("saleordermap")) {
		 		 					picPathName	= "saleorder";
		 		 					salePathName = "\\"+picSubPathName;		
		 		 					fileName = addrType+addrNo+"_"+fileName;
		 		 				}
		 		 				
		 		 				
		 		 				File dir = new File(pathName+"\\"+picPathName+salePathName);
		 		 				dir.mkdirs();
		 		 				buffer = new java.io.FileOutputStream(dir.getPath()+"/"+fileName);
		 		 				
		 		 				if (firstPicPathName.equals("customermap"))
		 		 					dbFileUpload.UpdatePictureFileNameAddress(picPathName, addrType, addrNo, picNo, fileName, idCode);
		 		 				
		 		 				else if (firstPicPathName.equals("saleordermap"))
		 		 					dbFileUpload.UpdatePictureFileNameAddress(picPathName, addrType, addrNo, picNo, fileName, idCode);
		 		 				
		 		 				else
		 		 					dbFileUpload.UpdatePictureFileName(firstPicPathName, picNo, fileName, idCode);
		 		 				
		 		 				request.setAttribute("alertMessage", "Upload Complete.");
		 		 				request.setAttribute("fileName", fileName);
		 		 			}
		 		 		} else if(s.startsWith("Content-Type") && state==1) {
		 		 			state = 2;
		 		 			contentType2 = s.substring(s.indexOf(":")+2,s.length()-2);
		           
		 		 		} else if(s.equals("\r\n") && state != 3) {
		 		 			state = 3;
		 		 		} else {
		 		 			if (name.equals("file")) buffer.write(b,0,x);
		 		 		}
			        }
			        sis.close();
			        buffer.close();
		    	} catch(java.io.IOException e) {
		    		err = e.toString();
		    	}
		    }

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
		return mapping.findForward(forwardText);
	}
}