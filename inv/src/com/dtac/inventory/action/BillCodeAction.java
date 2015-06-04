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
import com.dtac.inventory.form.BillCodeForm;;

public class BillCodeAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BillCodeForm bcf = (BillCodeForm) form;
		String forwardText = ""
			,billbarcode = bcf.getBillbarcode();
		HttpSession session = request.getSession();
		// TO DO CODE HERE
		if(request.getParameter("btncancel") == null){
			if(request.getParameter("btnsubmit") != null){
				session.setAttribute("billbarcode", billbarcode);
				if(session.getAttribute("choosetype") == "ค่าไฟฟ้า"){
					session.setAttribute("budget", "2500");
					session.setAttribute("totalprice", "2515");
				}else{
					session.setAttribute("budget", "875");
					session.setAttribute("totalprice", "890");
				}
				forwardText = "submit";
			}
		}else{
			forwardText = "cancel";
		}
		return mapping.findForward(forwardText);
	}
}
