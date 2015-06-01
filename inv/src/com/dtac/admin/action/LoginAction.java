//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.0/xslt/JavaClass.xsl
package com.dtac.admin.action;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.dtac.utils.DateUtil;
 /** 
 * MyEclipse Struts
 * Creation date: 10-06-2006
 */
public class LoginAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//26-08-2011
		String forwardText 	= "";
		String alertLang	= "th";
		try {
			MemberLoginForm memberLoginForm = (MemberLoginForm) form;
			String memberId 	= memberLoginForm.getMemberID();
			String password 	= memberLoginForm.getPassword();
			String firstName	= "";
			String lastName		= "";
			String userName		= "";
			String lastLoginDate= "";
			String lastChangPswd= "";

			DBLogin dbLogin = new DBLogin();
			dbLogin.AddAdmLoginDT(memberId, request.getRemoteAddr());

			DBProperties dbProp = new DBProperties();
			if (memberId.trim().length() != 5 && memberId.trim().length() != 7) {
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.username.incorrect", alertLang));
				return mapping.findForward("repeat");
				
			} else if (password.trim().length() < 4) {
				memberLoginForm.setPassword("");
				request.setAttribute("focus", "password");
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.password.length", alertLang));
				return mapping.findForward("repeat");

			} else if (dbLogin.CheckLogIn(memberId, password)) {

				if (!dbLogin.CheckAdmLogIn(memberId)) dbLogin.AddAdmLogin(memberId);
				
				if (dbLogin.CheckLogIn(memberId, "12345")) {
					
					memberLoginForm.setMemberID(memberId);
	  				forwardText = "changpasswd";
	  				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.password.change", alertLang));
	  				
				} else {			
					List loginList = dbLogin.GetLoginList(memberId, "", "", ""); 
					if (loginList.size() == 1) {
						MemberLoginForm memberInfoForm =(MemberLoginForm) loginList.get(0);
						firstName 		= memberInfoForm.getFirstName();	
						lastName 		= memberInfoForm.getLastName();
						lastLoginDate 	= memberInfoForm.getLastLoginDate();
						lastChangPswd	= memberInfoForm.getLastChangPswd();
						userName 		= firstName;
						if (!lastName.equals("")) userName = userName + "  " + lastName;
											
						DateUtil dateUtil = new DateUtil();
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date curDate = df.parse(dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'-'));
						Date expDate = df.parse(dateUtil.addDate(dateUtil.CnvToYYYYMMDD(lastChangPswd, '/') , 90));
						
			  			if (curDate.after(expDate)) {
			  				memberLoginForm.setMemberID(memberId);
			  				
			  				forwardText = "changpasswd";
			  				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.password.change", alertLang));
			  						  				
						} else {
							forwardText = "success";
							
							HttpSession session = request.getSession();
							session.setAttribute("loginId", memberId);
							session.setAttribute("memberId", memberId);
				  			session.setAttribute("userName", userName);
				  			session.setAttribute("alertLang", "th");
				  			session.setAttribute("lastLoginDate", lastLoginDate);
				  			session.setAttribute("lastChangPswd", lastChangPswd);
				  			session.setAttribute("nextChangPswd", 
				  				dateUtil.CnvToDDMMYYYY(dateUtil.addDate(dateUtil.CnvToYYYYMMDD(lastChangPswd, '/') , 45)));

						}
					} else {
						memberLoginForm.setPassword("");
						forwardText = "repeat";
						request.setAttribute("focus", "password");
						request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.username.incorrect", alertLang));

					}
				}
				dbLogin.UpdateLastLogin(memberId);
				
			} else {				
				memberLoginForm.setPassword("");
				forwardText = "repeat";
				request.setAttribute("focus", "password");
				request.setAttribute("alertMessage", dbProp.GetProp("adm", "admerr.password.incorrect", alertLang));				
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward(forwardText);
	}
}