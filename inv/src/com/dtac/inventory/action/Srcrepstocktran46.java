//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.0/xslt/JavaClass.xsl

package com.dtac.inventory.action;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



import com.dtac.inventory.form.MaterialForm;



/** 

 * MyEclipse Struts
 * Creation date: 01-05-2006
 * 
 * XDoclet definition:
 * @struts.action path="/editdata" name="variablesForm" scope="request" validate="true"
 */
public class Srcrepstocktran46 extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * 
	 * 
	 */


 	
 	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
 			HttpServletResponse response) throws Exception {
 //--
  		String chkbutton = null;		
 		
 		String chktxt = null;
               
              
 		MaterialForm materialForm = (MaterialForm) form;	
 	
 		String matcode = materialForm.getMatCode();
 
 		String prmfrdate = materialForm.getPrmfrdate();
 		String prmtodate = materialForm.getPrmtodate();
 		
 		
 
		
 		       
		if (request.getParameter("reqCode")!=null ) 
	{
	 		 chkbutton = request.getParameter("reqCode").trim();
		 		chkbutton = new String( chkbutton.getBytes("ISO8859_1"),"utf-8");
//--- Start print record ------
	 		 chktxt = "PRINT";  	
			if ( chkbutton.equals(chktxt)) 
		{ 		 
				

						request.setAttribute("matcode2",matcode);

	 				   request.setAttribute("prmfrdate",prmfrdate);
	 				  request.setAttribute("prmtodate",prmtodate);
	 		 			String forwardText = "print";
	 		 			return mapping.findForward(forwardText);
	 		 			
	 	 }		 
	 		 
	}
//	--- End print record ------ 
		
		String forwardText = "success";
		return mapping.findForward(forwardText);
  }
	


	
}

