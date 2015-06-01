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
		String loginId 		= "",
		btn10 = request.getParameter("btn10");
		
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
		forwardText= "cancel";
		return mapping.findForward(forwardText);
	}
}
