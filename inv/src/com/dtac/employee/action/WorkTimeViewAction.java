package com.dtac.employee.action;
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
import com.dtac.admin.data.DBMasterTable;
import com.dtac.admin.data.DBMemberType;
import com.dtac.employee.data.DBWorkTime;
import com.dtac.employee.form.WorkTimeForm;
import com.dtac.utils.DateUtil;

public class WorkTimeViewAction extends LookupDispatchAction {

	protected Map getKeyMethodMap() {
		Map map = new HashMap();
		map.put("timeattendform.button.update",		"update");	
		map.put("timeattendform.button.preview",	"preview");
		return map;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//14-06-2012
		String forwardText 	= "repeat";
		String loginId 		= "";
		String appCode  	= "hr42";
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
					
			WorkTimeForm workTimeForm = (WorkTimeForm) form;
			String empNameT		= new String(workTimeForm.getEmpNameT().getBytes("ISO8859_1"),"utf-8");
			String empLastNameT	= new String(workTimeForm.getEmpLastNameT().getBytes("ISO8859_1"),"utf-8");
			String salTypeName	= new String(workTimeForm.getSalTypeName().getBytes("ISO8859_1"),"utf-8");
			String workDate 	= workTimeForm.getWorkDate();
			String empID		= workTimeForm.getEmpID();
			//String empTypeCode	= workTimeForm.getEmpTypeCode();
			String startTime 	= workTimeForm.getStartTime().trim();
			String stopTime 	= workTimeForm.getStopTime().trim();
			String amlate		= workTimeForm.getAmlate();
			//String punchCard	= workTimeForm.getPunchCard();
			String r 	= workTimeForm.getR();
			String w 	= workTimeForm.getW();
			String b 	= workTimeForm.getB();
			String v 	= workTimeForm.getV();
			String c 	= workTimeForm.getC();
			String a 	= workTimeForm.getA();
			String l 	= workTimeForm.getL();
			String x 	= workTimeForm.getX();
			String xw	= workTimeForm.getXw();
			String xb	= workTimeForm.getXb();
			String xv	= workTimeForm.getXv();
			String xx	= workTimeForm.getXx();
			String otN1	= workTimeForm.getOtN1();
			String otN1_5=workTimeForm.getOtN1_5();
			String otN2	= workTimeForm.getOtN2();
			String otN3	= workTimeForm.getOtN3();
			String workStatus	= workTimeForm.getWorkStatus();
			String alertMessage = "";	
			
			DateUtil dateUtil 	= new DateUtil(); 
				
			startTime = startTime.replace('.', ':') +":00";
			if (!dateUtil.isValidTimeStr(startTime) && !startTime.equals(":00")) alertMessage = "check start time";

			stopTime = stopTime.replace('.', ':') +":00";
			if (!dateUtil.isValidTimeStr(stopTime) && !stopTime.equals(":00"))	alertMessage = "check stop time";
			
			
			//float zz = Float.parseFloat(r) + Float.parseFloat(w) + Float.parseFloat(b) + Float.parseFloat(v) +
				//Float.parseFloat(c) + Float.parseFloat(a) + Float.parseFloat(l);
			
			//if (zz > 8) alertMessage = "check data, total > 8";
			if (alertMessage.equals("")) {	
				DBWorkTime dbworkTime = new DBWorkTime ();
				dbworkTime.updateWorkTime(workDate, empID, startTime, stopTime, amlate, r, w, b, v, c, a, l, x,
					xw, xb, xv, xx, otN1, otN1_5, otN2, otN3, workStatus);
				
				DBMemberType dbMemberType = new DBMemberType();
				List memberTypeList = dbMemberType.GetMemberTypeList("", "", "");
				if (memberTypeList.size() > 0) request.setAttribute("memberTypeList", memberTypeList);
								
				DBMasterTable dbMasterTable = new DBMasterTable();
				List deptList = dbMasterTable.GetMasterTableDTList("dept", "", "", "AC");
				if (deptList.size() > 0) request.setAttribute("deptList", deptList);
				
				alertMessage = "update success";
			} 
				
			request.setAttribute("alertMessage", alertMessage);								
					
			workTimeForm.setEmpNameT(empNameT);
			workTimeForm.setEmpLastNameT(empLastNameT);
			workTimeForm.setSalTypeName(salTypeName);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward(forwardText);
	}
	public ActionForward preview(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//04-02-2012
		String forwardText 	= "repeat";
		String loginId 		= "";
		String appCode  	= "hr42";
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
					
			WorkTimeForm workTimeForm = (WorkTimeForm) form;
			String empNameT		= new String(workTimeForm.getEmpNameT().getBytes("ISO8859_1"),"utf-8");
			String empLastNameT	= new String(workTimeForm.getEmpLastNameT().getBytes("ISO8859_1"),"utf-8");
			String salTypeName	= new String(workTimeForm.getSalTypeName().getBytes("ISO8859_1"),"utf-8");
			String workDate 	= workTimeForm.getWorkDate();
			String workTimeStart= workTimeForm.getWorkTimeStart();
			//String workTimeStop	= workTimeForm.getWorkTimeStop();
			//String empID		= workTimeForm.getEmpID();
			//String empTypeCode	= workTimeForm.getEmpTypeCode();
			String startTime 	= workTimeForm.getStartTime().trim();
			String stopTime 	= workTimeForm.getStopTime().trim();
			//String punchCard	= workTimeForm.getPunchCard();
//			String l 	= workTimeForm.getL();
//			String x 	= workTimeForm.getX();
//			String otN1	= workTimeForm.getOtN1();
//			String otN1_5=workTimeForm.getOtN1_5();
//			String otN2	= workTimeForm.getOtN2();
//			String otN3	= workTimeForm.getOtN3();
//			String salTypeCode	= "";			
			String alertMessage = "";	
			
			DateUtil dateUtil 	= new DateUtil(); 
				
			startTime = startTime.replace('.', ':') +":00";
			if (!dateUtil.isValidTimeStr(startTime) && !startTime.equals(":00")) alertMessage = "check start time";

			stopTime = stopTime.replace('.', ':') +":00";
			if (!dateUtil.isValidTimeStr(stopTime) && !stopTime.equals(":00"))	alertMessage = "check stop time";
			
			//float zz = Float.parseFloat(r) + Float.parseFloat(w) + Float.parseFloat(b) + Float.parseFloat(v) +
				//Float.parseFloat(c) + Float.parseFloat(a) + Float.parseFloat(l);
			
			//if (zz > 8) alertMessage = "check data, total > 8";
			String d1 = dateUtil.CnvToYYYYMMDDEngYear(workDate, '-')+" "+workTimeStart;
			String d2 = dateUtil.CnvToYYYYMMDDEngYear(workDate, '-')+" "+startTime;
			//System.out.println("d1"+d1);
			String amlate = dateUtil.getTimeDiff(d1, d2);
			float tmpL = dateUtil.getTimeDiff_Float(d1, d2);
			//System.out.println("tmpL"+tmpL);
			request.setAttribute("alertMessage", alertMessage);								
		
			workTimeForm.setEmpNameT(empNameT);
			workTimeForm.setEmpLastNameT(empLastNameT);
			workTimeForm.setSalTypeName(salTypeName);
			workTimeForm.setAmlate(amlate);
			workTimeForm.setL(String.valueOf(tmpL));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward(forwardText);
	}
}