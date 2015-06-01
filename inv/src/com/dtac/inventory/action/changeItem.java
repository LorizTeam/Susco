package com.dtac.inventory.action;

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

import com.dtac.admin.data.DBLogin;
import com.dtac.admin.data.DBMonth;
import com.dtac.admin.data.DBYear;
import com.dtac.inventory.data.DBchange;
import com.dtac.inventory.form.CollectionForm;
import com.dtac.inventory.form.MatDocPeriodForm;
import com.dtac.inventory.form.changeItemAddForm;
import com.dtac.inventory.form.changeItemForm;


public class changeItem  extends LookupDispatchAction {
	protected Map getKeyMethodMap() {
		Map map = new HashMap();		
		
		map.put("change.button.recv", "add");
		map.put("change.button.issue", "issue");
		map.put("change.button.search",	"search");
		map.put("collection.button.update",	"update");
		map.put("collection.button.delete",	"delete");
		map.put("change.button.checkItem",	"checkItem");
		map.put("change.button.checkDoc",	"checkDoc");
		return map;
	}
	public ActionForward checkDoc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception { //22-04-2013
			String forwardText = "checkDoc";
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
				if (!dbLogin.CheckAppAuth(loginId, appCode, "mant")) {
					request.setAttribute("alertMessage","You don't have authorize.");
					return mapping.findForward("alertmsg");
				}
				
				if(form.getClass().getName() == "com.dtac.inventory.form.changeItemAddForm"){
				changeItemAddForm changeItem = (changeItemAddForm) form;
				String docType 	= changeItem.getDocType();
				String docCode 	= changeItem.getDocCode();
				String prCode	= changeItem.getPrCode();
				
				DBchange dbChange = new DBchange();
				request.setAttribute("checkDoc",dbChange.getDoc(docCode) ? "ok" : "notfound");
				}
				
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			return  mapping.findForward(forwardText);
		}	
	public ActionForward checkItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception { //22-04-2013
			String forwardText = "checkItem";
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
				if (!dbLogin.CheckAppAuth(loginId, appCode, "mant")) {
					request.setAttribute("alertMessage","You don't have authorize.");
					return mapping.findForward("alertmsg");
				}
				
				if(form.getClass().getName() == "com.dtac.inventory.form.changeItemAddForm"){
				changeItemAddForm changeItem = (changeItemAddForm) form;
				String docType 	= changeItem.getDocType();
				String docCode 	= changeItem.getDocCode();
				String prCode	= changeItem.getPrCode();
				
				DBchange dbChange = new DBchange();
				Map res = dbChange.getItem(prCode);
				request.setAttribute("item", res == null ? "notfound" : res);
				}
				
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			return  mapping.findForward(forwardText);
		}	
	
	
	public ActionForward issue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception { //03-05-2012
			String forwardText = "issue";
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
				if (!dbLogin.CheckAppAuth(loginId, appCode, "mant")) {
					request.setAttribute("alertMessage","You don't have authorize.");
					return mapping.findForward("alertmsg");
				}
				
				if(form.getClass().getName() == "com.dtac.inventory.form.changeItemAddForm"){
				changeItemAddForm changeItem = (changeItemAddForm) form;
				String docType 	= changeItem.getDocType();
				String docCode 	= changeItem.getDocCode();
				String prCode	= changeItem.getPrCode();
				
				DBchange dbChange = new DBchange();
				dbChange.Add(docType, docCode, prCode);
				}
				
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			return  mapping.findForward(forwardText);
		}	
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception { //03-05-2012
			String forwardText = "add";
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
				if (!dbLogin.CheckAppAuth(loginId, appCode, "mant")) {
					request.setAttribute("alertMessage","You don't have authorize.");
					return mapping.findForward("alertmsg");
				}
				
				if(form.getClass().getName() == "com.dtac.inventory.form.changeItemAddForm"){
					changeItemAddForm changeItem = (changeItemAddForm) form;
					String docType 	= changeItem.getDocType();
					String docCode 	= changeItem.getDocCode();
					String prCode	= changeItem.getPrCode();
					
					DBchange dbChange = new DBchange();
					dbChange.Add(docType, docCode, prCode);
					}
				
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			return  mapping.findForward(forwardText);
		}	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {	//04-06-2012
			String forwardText = "search";
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
				
				DBchange dbc = new DBchange();
				List changeList = dbc.getChangeList();
				request.setAttribute("chglist", changeList);
				
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			return  mapping.findForward(forwardText);
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception { //03-05-2012
			String forwardText = "update";
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
				if (!dbLogin.CheckAppAuth(loginId, appCode, "mant")) {
					request.setAttribute("alertMessage","You don't have authorize.");
					return mapping.findForward("alertmsg");
				}
	       			
				CollectionForm collectionForm = (CollectionForm) form;
				String	txtcode =	collectionForm.getCollectcode();
				String	txtname =	collectionForm.getCollectname();
		//		String	txtstock =	collectionForm.getCollectstock();
			
				

			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			return  mapping.findForward(forwardText);
		}	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception { //03-05-2012
			String forwardText = "delete";
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
				if (!dbLogin.CheckAppAuth(loginId, appCode, "mant")) {
					request.setAttribute("alertMessage","You don't have authorize.");
					return mapping.findForward("alertmsg");
				}
	       			
				CollectionForm collectionForm = (CollectionForm) form;
				String	txtcode =	collectionForm.getCollectcode();
				
			
				

			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			return  mapping.findForward(forwardText);
		}
}
