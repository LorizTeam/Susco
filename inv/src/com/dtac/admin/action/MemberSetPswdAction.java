//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.0/xslt/JavaClass.xsl
package com.dtac.admin.action;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import com.dtac.admin.data.DBApplication;
import com.dtac.admin.data.DBLogin;
import com.dtac.admin.data.DBMember;
import com.dtac.admin.data.DBMemberType;
import com.dtac.admin.form.MemberAuthForm;
 /** 
 * MyEclipse Struts
 * Creation date: 10-08-2006
 */
public class MemberSetPswdAction extends LookupDispatchAction {

	protected Map getKeyMethodMap() {	      
	    Map map = new HashMap();
	    map.put("memberform.button.update", "update");
	    map.put("memberform.button.cancel", "cancel");
	    return map;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//27-06-2010
		String forwardText 	= "";
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
			if (!dbLogin.CheckAppAuth(loginId,appCode,"mant")) {
				request.setAttribute("alertMessage","You don't have authorize.");
				return mapping.findForward("alertmsg");
			}
			
			MemberAuthForm memberAuthForm = (MemberAuthForm) form;
			String memberId = memberAuthForm.getMemberID();
			String password = memberAuthForm.getPassword();
			String memberGrpCode = memberAuthForm.getMemberGrpCode();
			
			DBMember dbMember = new DBMember();
			if (dbMember.CheckMember(memberId, "AC")) {
				dbLogin.UpdatePassword(memberId, password);
				dbLogin.UpdateLastChangePasswd(memberId);
				forwardText = "success";
			
				DBMemberType dbMemberType = new DBMemberType();
				List memberTypeList = dbMemberType.GetMemberTypeList(memberGrpCode, "", "");
				if (memberTypeList.size() > 0) request.setAttribute("memberTypeList", memberTypeList);

				DBApplication dbApplication = new DBApplication();
				List applTypeList = dbApplication.GetApplTypeList();
				if (applTypeList.size() > 0) request.setAttribute("applTypeList", applTypeList);

			} else {
				forwardText = "alertmsg";
				request.setAttribute("alertMessage", "The system can not update password!!!");
			}
			memberAuthForm.initial();
			memberAuthForm.setMemberGrpCode(memberGrpCode);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward(forwardText);
	}	
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//02-10-2011
		String forwardText 	= "success";
		String loginId 		= "";
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
			
			MemberAuthForm memberAuthForm = (MemberAuthForm) form;
			String memberGrpCode = memberAuthForm.getMemberGrpCode();
			memberAuthForm.initial();
			
			DBMemberType dbMemberType = new DBMemberType();
			List memberTypeList = dbMemberType.GetMemberTypeList(memberGrpCode, "", "");
			if (memberTypeList.size() > 0) request.setAttribute("memberTypeList", memberTypeList);

			DBApplication dbApplication = new DBApplication();
			List applTypeList = dbApplication.GetApplTypeList();
			if (applTypeList.size() > 0) request.setAttribute("applTypeList", applTypeList);

			memberAuthForm.setMemberGrpCode(memberGrpCode);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward(forwardText);
	}
}