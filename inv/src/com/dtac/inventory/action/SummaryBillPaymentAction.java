package com.dtac.inventory.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.dtac.inventory.form.SummaryBillPaymentForm;

public class SummaryBillPaymentAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			//Form default code Start
			SummaryBillPaymentForm sbpf = (SummaryBillPaymentForm) form;
			String forwardText = "";
			HttpSession session = request.getSession();
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
			//Form default code Start
			//TO DO CODE HERE
			if(request.getParameter("btncancel") == null){
//				เช็คว่ายกเลิกหรือไม่
				if(request.getParameter("btnreturn") != null){
//					เช็ค ถ้ากดย้อนกลับจะกลับไปหน้า choosebudget.jsp
					forwardText = "return";
				}else{
					forwardText = "success";
				}
			}else {
//				ถ้าไม่ยกเลิก จะส่งไปหน้า input_member.jsp
				forwardText = "cancel";
			}
			return mapping.findForward(forwardText);
	}
}
