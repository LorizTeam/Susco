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
 /** 
 * MyEclipse Struts
 * Creation date: 01-04-2012
 */
public class ChangePasswordStartAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//02-10-2011
		String forwardText 	= "success";
		try {
			MemberLoginForm memberLoginForm = (MemberLoginForm) form;
			memberLoginForm.initial();
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward(forwardText);
	}
}