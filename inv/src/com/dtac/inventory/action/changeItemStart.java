package com.dtac.inventory.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dtac.admin.data.DBLogin;
import com.dtac.admin.data.DBMasterTable;
import com.dtac.inventory.data.DBMaterial;
import com.dtac.inventory.data.DBMaterialType;
import com.dtac.inventory.form.MaterialForm;
import com.dtac.inventory.form.MaterialTypeForm;
/** 
 * MyEclipse Struts
 * Creation date: 20-03-2013
 */
public class changeItemStart extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {	//30-04-2012
			String forwardText 	= "success";
			String loginId 		= "";
			String appCode  	= "mm00";		
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
		
			} catch (Exception e) {
				throw new Exception(e.getMessage());

			}
			return  mapping.findForward(forwardText);
		}
	}

	

