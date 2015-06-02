<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../admin/css_blue_style.css" %>
<%
  	String path = request.getContextPath();
  	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 

	String fileName = "";
	if (request.getAttribute("fileName") != null) 
		fileName = (String) request.getAttribute("fileName"); 

	String picNo  = request.getParameter("picNo");	
	String idCode  = new String(request.getParameter("idCode").getBytes("ISO8859_1"),"utf-8");		
	String pathName  = request.getParameter("pathName");
    String subPathName = "";
	if (request.getParameter("subPathName") != null) 
		subPathName = (String) request.getParameter("subPathName");
		
	String addrType = "";
	if (request.getParameter("addrType") != null) addrType  = (String) request.getParameter("addrType");

	String addrNo = "";
	if (request.getParameter("addrNo") != null) addrNo  = (String) request.getParameter("addrNo");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
  	<base href="<%=basePath%>">
  	<title>Upload Picture</title>
  	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script language="javascript">
	function returnFile(tFileName) {

		if (opener && !opener.closed){
			if (document.uploadForm.pathName.value == 'saleorder') {
				if (document.uploadForm.picNo.value == '1')
			 		window.opener.document.saleOrderForm.pic1.value = tFileName;
			 	
			 	else if (document.uploadForm.picNo.value == '2')
			 		window.opener.document.saleOrderForm.pic2.value = tFileName;

				else if (document.uploadForm.picNo.value == '3')
			 		window.opener.document.saleOrderForm.pic3.value = tFileName;

			 	else if (document.uploadForm.picNo.value == '4')
			 		window.opener.document.saleOrderForm.pic4.value = tFileName;

			 	else if (document.uploadForm.picNo.value == '5')
			 		window.opener.document.saleOrderForm.pic5.value = tFileName;

		 	} else if (document.uploadForm.pathName.value == 'customer') {
				if (document.uploadForm.picNo.value == '1')
			 		window.opener.document.customerForm.pic1.value = tFileName;
			 		
			 	else if (document.uploadForm.picNo.value == '2')
			 		window.opener.document.customerForm.pic2.value = tFileName;
			 		
			 	else if (document.uploadForm.picNo.value == '3')
			 		window.opener.document.customerForm.pic3.value = tFileName;
			 		
			 	else if (document.uploadForm.picNo.value == '4')
			 		window.opener.document.customerForm.pic4.value = tFileName;
			 		
			 	else if (document.uploadForm.picNo.value == '5')
			 		window.opener.document.customerForm.pic5.value = tFileName;
			 		
			} else if (document.uploadForm.pathName.value == 'warehouse') {
				if (document.uploadForm.picNo.value == '1')
			 		window.opener.document.warehouseForm.pic1.value = tFileName;
			 		
			 	else if (document.uploadForm.picNo.value == '2')
			 		window.opener.document.warehouseForm.pic2.value = tFileName;
			
			} else if (document.uploadForm.pathName.value == 'material') {
				if (document.uploadForm.picNo.value == '1')
			 		window.opener.document.materialForm.pic1.value = tFileName;
			 		
			 	else if (document.uploadForm.picNo.value == '2')
			 		window.opener.document.materialForm.pic2.value = tFileName;
			 		
			} else if (document.uploadForm.pathName.value == 'saleorderlogo') { 
			
			} else if (document.uploadForm.pathName.value == 'customermap') {
				if (document.uploadForm.addrType.value == 'ship')
			 		window.opener.document.customerForm.shipPic1.value = tFileName;

				else if (document.uploadForm.addrType.value == 'invx')
			 		window.opener.document.customerForm.invxPic1.value = tFileName;

				else if (document.uploadForm.addrType.value == 'bill')
			 		window.opener.document.customerForm.billPic1.value = tFileName;
				
			} else if (document.uploadForm.pathName.value == 'saleordermap') {
				
			} else alert('no form set');
			
			
		 	opener.focus();		
		} 
		
		window.close();
		return;			
	}
	</script>	
  </head>

  <body class="back1">
  	<form action="./fileUpload.do?pathName=<%=pathName%>&subPathName=<%=subPathName%>&addrType=<%=addrType%>&addrNo=<%=addrNo%>&picNo=<%=picNo%>&idCode=<%=idCode%>" 
  			method="post" enctype="multipart/form-data" name="uploadForm" >
    	<fieldset><legend><b class="blue">Upload รูปภาพ</b></legend>  	
  		<table width="80%" align="center" >
      	<tr><td>&nbsp;</td></tr>
      	<tr><td>&nbsp;</td></tr>      	
        <tr>
            <td align="center" class="blue">Attach File&nbsp;:&nbsp;</td>
            <td width="75%"><input name="file" type="file" class="normal" size="30"></td>
        </tr>
      	<tr><td>&nbsp;</td></tr>      	
      	<tr><td>&nbsp;</td></tr>      	      	
        <tr><td>&nbsp;</td>
            <td class="normal">
            	<input name="submit" type="submit" class="normal" id="submit" value="Upload"/>
            </td>
        </tr>
      	<tr><td>&nbsp;</td></tr>        
      	<tr><td>&nbsp;</td></tr>
      	<tr>
      		<td colspan="2" align="center" class="normal">upload file <%=pathName%> (<%=idCode%>), Picture no. <%=picNo%></td>
      	</tr>     
      	<tr><td>&nbsp;</td></tr>	
		<tr>
			<% if (fileName.equals("")) { %>
			<td colspan="2" align="center" class="blue">ชื่อไฟล์ให้เป็นภาษาอังกฤษ</td>
			<% } else { %>
			<td colspan="2" align="center" class="normal">filename &nbsp;&nbsp;<%=fileName%> </td>
			<% } %>
		</tr>
      	<tr><td>&nbsp;      			
      			<input type="hidden" name="pathName"  value="<%=pathName%>"/>
      			<input type="hidden" name="picNo"     value="<%=picNo%>"/>
      			<input type="hidden" name="addrType"  value="<%=addrType%>"/>
      			<input type="hidden" name="addrNo"    value="<%=addrNo%>"/>
      		</td>
      	</tr>                
		<tr>
			<td colspan="2" align="center" class="red"><%=alertMessage%>&nbsp;&nbsp;&nbsp;
				<% if (!fileName.equals("")) { %>
				<a href="javascript:returnFile('<%=fileName%>');"> <font color="blue">Close</font></a>
				<% } %>			
			</td>				            
		</tr>
  		</table>
  		</fieldset>
  	</form>
  </body>
</html:html>