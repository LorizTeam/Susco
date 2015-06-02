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
import com.dtac.admin.data.DBMemberAuth;
import com.dtac.admin.data.DBMemberType;
import com.dtac.admin.form.MemberAuthForm;
 /** 
 * MyEclipse Struts
 * Creation date: 04-10-2011
 */
public class MemberSetAuthAction extends LookupDispatchAction {

	protected Map getKeyMethodMap() {
	    Map map = new HashMap();
	    map.put("memberform.button.update", "update");
	    map.put("memberform.button.cancel", "cancel");
	    return map;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//02-09-2012
		String forwardText 	= "success";
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
			String memberID 	= memberAuthForm.getMemberID();
			String appType		= memberAuthForm.getAppType();
			String memberGrpCode= memberAuthForm.getMemberGrpCode();
			
			DBMemberAuth dbMemberAuth = new DBMemberAuth();
			
			dbMemberAuth.ResetMemberAuth(memberID, "mant", appType);			
			if (request.getParameterValues("idKeyMant") != null) {
				String[] mantList = request.getParameterValues("idKeyMant");
				if (mantList.length > 0) {
					for (int x=0; x<mantList.length; x++) {
						appCode = mantList[x].substring(0,4);
						//String authMant= mantList[x].substring(4,5);
						if (dbMemberAuth.CheckRecordMemberAuth(memberID, appCode))  { 
							dbMemberAuth.UpdateMemberAuth(memberID, "mant", appCode);
						} else {
							dbMemberAuth.AddMemberAuth(memberID, "mant", appCode);
						}
					}
				}	
			}
	
			dbMemberAuth.ResetMemberAuth(memberID, "disp", appType);
			if (request.getParameterValues("idKeyDisp") != null) {
				String[] dispList = request.getParameterValues("idKeyDisp");
				if (dispList.length > 0) {
					for (int x=0; x<dispList.length; x++) {
						appCode = dispList[x].substring(0,4);
						if (dbMemberAuth.CheckRecordMemberAuth(memberID, appCode))  { 
							dbMemberAuth.UpdateMemberAuth(memberID, "disp", appCode);
						} else {
							dbMemberAuth.AddMemberAuth(memberID, "disp", appCode);
						}
					}
				}	
			}

			dbMemberAuth.ResetMemberAuth(memberID, "appv", appType);
			if (request.getParameterValues("idKeyAppv") != null) {
				String[] appvList = request.getParameterValues("idKeyAppv");
				if (appvList.length > 0) {
					for (int x=0; x<appvList.length; x++) {
						appCode = appvList[x].substring(0,4);
						if (dbMemberAuth.CheckRecordMemberAuth(memberID, appCode))  { 
							dbMemberAuth.UpdateMemberAuth(memberID, "appv", appCode);
						} else {
							dbMemberAuth.AddMemberAuth(memberID, "appv", appCode);
						}
					}
				}	
			}

			memberAuthForm.initial();
			memberAuthForm.setMemberID(memberID);
			
			DBMemberType dbMemberType = new DBMemberType();
			List memberTypeList = dbMemberType.GetMemberTypeList(memberGrpCode, "", "");
			if (memberTypeList.size() > 0) request.setAttribute("memberTypeList", memberTypeList);

			DBApplication dbApplication = new DBApplication();
			List applTypeList = dbApplication.GetApplTypeList();
			if (applTypeList.size() > 0) request.setAttribute("applTypeList", applTypeList);

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
			//String firstName	= new String(memberAuthForm.getFirstName().getBytes("ISO8859_1"),"utf-8"); 
			//String lastName		= new String(memberAuthForm.getLastName().getBytes("ISO8859_1"),"utf-8");
			String memberGrpCode= memberAuthForm.getMemberGrpCode();
			
			DBMemberType dbMemberType = new DBMemberType();
			List memberTypeList = dbMemberType.GetMemberTypeList(memberGrpCode, "", "");
			if (memberTypeList.size() > 0) request.setAttribute("memberTypeList", memberTypeList);
	
			DBApplication dbApplication = new DBApplication();
			List applTypeList = dbApplication.GetApplTypeList();
			if (applTypeList.size() > 0) request.setAttribute("applTypeList", applTypeList);

			memberAuthForm.setFirstName("");
			memberAuthForm.setLastName("");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward(forwardText);
	}
}