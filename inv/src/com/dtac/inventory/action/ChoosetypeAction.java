package com.dtac.inventory.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.dtac.inventory.form.ChoosetypeForm;

public class ChoosetypeAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ChoosetypeForm ctf = (ChoosetypeForm) form;
		String btntrue=request.getParameter("btntrue"),btndtac=request.getParameter("btndtac")
		,btnais=request.getParameter("btnais"),btncancel=request.getParameter("btncancel")
		,phone_num = ctf.getPhone_num();
		//to do code
		String forwardText = "";
		String loginId 		= "";
		HttpSession session = request.getSession();
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
		if(btncancel == null){
			//ถ้ามีการเลือกประเภทการเติมเงิน
			if(btntrue != null){
				String choosetrue = "True";
				session.setAttribute("choosetype",choosetrue);
			}else if(btndtac != null){
				String choosedtac = "DTAC";
				session.setAttribute("choosetype",choosedtac);
			}else if(btnais != null){
				String chooseais = "AIS";
				session.setAttribute("choosetype",chooseais);
			}
			//request.setAttribute("phone_num", phone_num);
			forwardText = "success";
		}else{
			//ถ้ายกเลิกการเติมเงิน
			forwardText = "cancel";
		}
		return mapping.findForward(forwardText);
	}
}
