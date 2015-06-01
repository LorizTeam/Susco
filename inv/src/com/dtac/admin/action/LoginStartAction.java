//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.0/xslt/JavaClass.xsl
package com.dtac.admin.action;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dtac.admin.form.MemberLoginForm;
import com.dtac.inventory.data.DBMaterial;

 /** 
 * MyEclipse Struts
 * Creation date: 10-09-2006
 */
public class LoginStartAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//25-06-2010		
		String forwardText = "success";
		try {
			MemberLoginForm memberLoginForm = (MemberLoginForm) form;
			memberLoginForm.initial();
			//start service
			DBMaterial dbMaterial = new DBMaterial(); 
			dbMaterial.unLOCKMaterial();
			//end start service
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward(forwardText);
	}
}