//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.0/xslt/JavaClass.xsl
package com.dtac.admin.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dtac.admin.data.DBApplication;
import com.dtac.admin.data.DBLogin;
import com.dtac.admin.data.DBMemberType;
import com.dtac.admin.form.MemberAuthForm;
 /** 
 * MyEclipse Struts
 * Creation date: 24-09-2011
 */
public class MemberListStartAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//26-09-2011	
		String forwardText  = "success";
		String loginId 		= "";
		String appCode  	= "ad10";
		try {
			HttpSession session = request.getSession();
			if (session.isNew()) {
				session.invalidate();
				request.setAttribute("alertMessage","Session Timeout. Login again.");
				return mapping.findForward("relogin");
			} else {
				loginId = (String) session.getAttribute("loginId");
				if (loginId == null) {
					request.setAttribute("alertMessage","Please Login.");
					return mapping.findForward("relogin");
				}
			}
			DBLogin dbLogin = new DBLogin();			
			if (!dbLogin.CheckAppAuth(loginId,appCode,"disp")) {
				request.setAttribute("alertMessage","You don't have authorize.");
				return mapping.findForward("alertmsg");
			}

			MemberAuthForm memberAuthForm = (MemberAuthForm) form;
			memberAuthForm.initial();
			
			String memberTypeCode = "ST";
			if (session.getAttribute("memberTypeCode") != null) memberTypeCode = (String) session.getAttribute("memberTypeCode");

			DBMemberType dbMemberType = new DBMemberType();
			List memberTypeList = dbMemberType.GetMemberTypeList("", "", "");
			if (memberTypeList.size() > 0) request.setAttribute("memberTypeList", memberTypeList);
			
			DBApplication dbApplication = new DBApplication();
			List applTypeList = dbApplication.GetApplTypeList();
			if (applTypeList.size() > 0) request.setAttribute("applTypeList", applTypeList);

			List memberList   = dbLogin.GetLoginList("", "", "", memberTypeCode);
			if (memberList.size() > 0) request.setAttribute("memberList", memberList);

			memberAuthForm.setMemberTypeCode(memberTypeCode);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
		return mapping.findForward(forwardText);
	}
}