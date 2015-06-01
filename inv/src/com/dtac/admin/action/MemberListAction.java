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
import com.dtac.admin.data.DBMemberAuth;
import com.dtac.admin.data.DBMemberType;
import com.dtac.admin.form.MemberAuthForm;
import com.dtac.admin.form.MemberForm;
import com.dtac.utils.DBProperties;
 /** 
 * MyEclipse Struts
 * Creation date: 30-09-2011
 */
public class MemberListAction extends LookupDispatchAction {

	protected Map getKeyMethodMap() {
		Map map = new HashMap();
		map.put("memberform.button.search", "search");
		map.put("memberform.button.update", "update");	
		return map;
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//27-11-2011	
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
			if (!dbLogin.CheckAppAuth(loginId,appCode,"disp")) {
				request.setAttribute("alertMessage","You don't have authorize.");
				return mapping.findForward("alertmsg");
			}

			MemberAuthForm memberAuthForm = (MemberAuthForm) form;
			String memberId 	= memberAuthForm.getMemberID();
			String firstName	= new String(memberAuthForm.getFirstName().getBytes("ISO8859_1"),"utf-8"); 
			String lastName		= new String(memberAuthForm.getLastName().getBytes("ISO8859_1"),"utf-8");
			String memberTypeCode=memberAuthForm.getMemberTypeCode();
		    
			DBMemberType dbMemberType = new DBMemberType();
			List memberTypeList = dbMemberType.GetMemberTypeList("", "", "");
			if (memberTypeList.size() > 0) request.setAttribute("memberTypeList", memberTypeList);

			DBApplication dbApplication = new DBApplication();
			List applTypeList = dbApplication.GetApplTypeList();
			if (applTypeList.size() > 0) request.setAttribute("applTypeList", applTypeList);

			List memberList = dbLogin.GetLoginList(memberId, firstName, lastName, memberTypeCode);
			if (memberList.size() > 0) request.setAttribute("memberList", memberList);
			
			memberAuthForm.setFirstName(firstName);
			memberAuthForm.setLastName(lastName);
			
			session.setAttribute("memberTypeCode", memberTypeCode);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
		return mapping.findForward(forwardText);
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//06-09-2011	
		String forwardText 	= "success";
		String loginId 		= "";
		String appCode  	= "ad10";
		String alertLang	= "th";
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
				alertLang = (String) session.getAttribute("alertLang");
			}
			DBLogin dbLogin = new DBLogin();			
			if (!dbLogin.CheckAppAuth(loginId,appCode,"mant")) {
				request.setAttribute("alertMessage","You don't have authorize.");
				return mapping.findForward("alertmsg");
			}
			
			MemberAuthForm memberAuthForm = (MemberAuthForm) form;
			String memberId 	= memberAuthForm.getMemberID();
			String firstName	= new String(memberAuthForm.getFirstName().getBytes("ISO8859_1"),"utf-8"); 
			String lastName		= new String(memberAuthForm.getLastName().getBytes("ISO8859_1"),"utf-8");
			String memberGrpCode= memberAuthForm.getMemberGrpCode();
			String memberTypeCode=memberAuthForm.getMemberTypeCode();
			String memberTypeName= "";
			String deptCode		= "";
			String deptName		= "";
			String appType		= memberAuthForm.getAppType();
			
		    DBMember dbMember = new DBMember();
		    List memberList = dbMember.GetMember(memberId);
			if (memberList.size() == 1) {
				MemberForm memberInfoForm =(MemberForm) memberList.get(0);
				firstName 		= memberInfoForm.getFirstName();
				lastName  		= memberInfoForm.getLastName();
				memberTypeCode	= memberInfoForm.getMemberTypeCode();
				memberTypeName 	= memberInfoForm.getMemberTypeName();
				deptCode		= memberInfoForm.getDeptCode();
				deptName		= memberInfoForm.getDeptName();
			}
			
			DBProperties dbProp = new DBProperties();
		    if (memberId.equals("")) {
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.memberid.choose", alertLang));
				
		    } else if (appType.equals("")) {
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.apptype.choose", alertLang));
				
		    } else if (appType.equals("setpswd")) {
		    	memberAuthForm.setPassword("12345");
		    	forwardText = "setpswd";
		    	
		    } else {
		    	DBMemberAuth dbMemberAuth = new DBMemberAuth();
		    	List resultList = dbMemberAuth.ViewMemberAuth(appType, memberId);
		    	if (resultList.size() > 0) request.setAttribute("resultList", resultList);
		    	forwardText = "setauth";
		    }
			
		    if (forwardText.equals("success")) {
		    	DBMemberType dbMemberType = new DBMemberType();
				List memberTypeList = dbMemberType.GetMemberTypeList(memberGrpCode, "", "");
				if (memberTypeList.size() > 0) request.setAttribute("memberTypeList", memberTypeList);

				DBApplication dbApplication = new DBApplication();
				List applTypeList = dbApplication.GetApplTypeList();
				if (applTypeList.size() > 0) request.setAttribute("applTypeList", applTypeList);

				memberList   = dbLogin.GetLoginList(memberId, firstName, lastName, memberTypeCode);				if (memberList.size() > 0) request.setAttribute("memberList",memberList);
				if (memberList.size() > 0) request.setAttribute("memberList", memberList);
		    }		    	
		    		
			memberAuthForm.setFirstName(firstName);
			memberAuthForm.setLastName(lastName);
			memberAuthForm.setMemberTypeCode(memberTypeCode);
			memberAuthForm.setMemberTypeName(memberTypeName);
			memberAuthForm.setDeptCode(deptCode);
			memberAuthForm.setDeptName(deptName);			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
		return mapping.findForward(forwardText);
	}
}