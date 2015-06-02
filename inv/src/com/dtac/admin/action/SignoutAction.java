package com.dtac.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SignoutAction extends Action {
	 
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
  		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//27-06-2010
	  
  		String forwardText = "success";
		HttpSession session = request.getSession();
  		//System.out.println("Signout : "+session.getId());
  		session.invalidate();
  	
  		return mapping.findForward(forwardText);
	}
}