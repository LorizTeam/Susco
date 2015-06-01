package com.dtac.inventory.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.dtac.inventory.form.ChooseBudgetForm;;

public class ChooseBudgetAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ChooseBudgetForm cbf = (ChooseBudgetForm) form;
		String forwardText = "";
		HttpSession session = request.getSession();
		// To Do Code Here
		String loginId 		= "";	
		if (session.isNew()) {
			session.invalidate();
			request.setAttribute("alertMessage","Session Timeout. Login again.");
			return mapping.findForward("relogin");
		} else {
			loginId = (String) session.getAttribute("loginId");
			request.setAttribute("loginId",loginId);
			String qty ="1";
			request.setAttribute("qty",qty);
			if (loginId == null) {
				request.setAttribute("alertMessage","Please Login.");
				return mapping.findForward("relogin");
			}
		}
		if(request.getParameter("btncancel") == null){
			if(request.getParameter("btn10") != null){
				session.setAttribute("budget","10");
				forwardText= "success";
			}else if (request.getParameter("btn20") != null){
				session.setAttribute("budget","20");
				forwardText= "success";
			}else if (request.getParameter("btn30") != null){
				session.setAttribute("budget","30");
				forwardText= "success";
			}else if (request.getParameter("btn50") != null){
				session.setAttribute("budget","50");
				forwardText= "success";
			}else if (request.getParameter("btn90") != null){
				session.setAttribute("budget","90");
				forwardText= "success";
			}else if (request.getParameter("btn150") != null){
				session.setAttribute("budget","150");
				forwardText= "success";
			}else if (request.getParameter("btn200") != null){
				session.setAttribute("budget","200");
				forwardText= "success";
			}else if (request.getParameter("btn300") != null){
				session.setAttribute("budget","300");
				forwardText= "success";
			}
		}else{
			forwardText= "cancel";
		}
		forwardText= "success";
		return mapping.findForward(forwardText);
	}
}
