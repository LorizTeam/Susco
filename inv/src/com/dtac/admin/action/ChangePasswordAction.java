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

import com.dtac.admin.data.DBLogin;
import com.dtac.admin.form.MemberLoginForm;
import com.dtac.utils.DBProperties;
 /** 
 * MyEclipse Struts
 * Creation date: 01-04-2012
 */
public class ChangePasswordAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//02-10-2011
		String alertLang	= "th";
		try {
			MemberLoginForm memberLoginForm = (MemberLoginForm) form;
			String memberId 	= memberLoginForm.getMemberID();
			String password 	= memberLoginForm.getPassword();
			String newPassword 	= memberLoginForm.getNewPassword();
			
			DBLogin dbLogin = new DBLogin();
			DBProperties dbProp = new DBProperties();
			if (memberId.trim().length() < 4) {
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.username.incorrect", alertLang));
				return mapping.findForward("repeat");
				
			} else if (password.equals(newPassword)) {
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.newPassword.incorrect", alertLang));
				return mapping.findForward("repeat");
				
			} else if (newPassword.trim().length() < 4) {
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.newPassword.length", alertLang));
				return mapping.findForward("repeat");
				
			} else if (newPassword.contains("123") || newPassword.contains("234")) {
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.newPassword.difficult", alertLang));
				return mapping.findForward("repeat");
				
			} else if (!dbLogin.CheckLogIn(memberId, password)) {
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.oldPassword.incorrect", alertLang));
				return mapping.findForward("repeat");
				
			} else {
				List loginList = dbLogin.GetLoginList(memberId, "", "", ""); 
				if (loginList.size() == 1) {
					//MemberLoginForm memberInfoForm =(MemberLoginForm) loginList.get(0);
					//String lastChangPswd	= memberInfoForm.getLastChangPswd();
					
					//DateUtil dateUtil = new DateUtil();
					//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					//Date curDate = df.parse(dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'-'));
					//Date expDate = df.parse(dateUtil.CnvToYYYYMMDD(lastChangPswd, '-'));
					//if (curDate.equals(expDate)) {
						//request.setAttribute("alertMessage","You can change password one time per day.");
						//return mapping.findForward("repeat");
						
					//} else {
						HttpSession session = request.getSession();
				  		session.invalidate();
				  		
						dbLogin.UpdatePassword(memberId, newPassword);
						dbLogin.UpdateLastChangePasswd(memberId);
						
						request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.newPassword.finish", alertLang));
					//}
				}				
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward("relogin");
	}
}