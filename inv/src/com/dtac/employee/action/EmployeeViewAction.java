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
import com.dtac.employee.data.DBEmployee;
import com.dtac.employee.data.DBTaxTemp;
import com.dtac.employee.form.EmployeeForm;
import com.dtac.utils.DateUtil;

public class EmployeeViewAction extends LookupDispatchAction {

	protected Map getKeyMethodMap() {
		Map map = new HashMap();
		map.put("employeeform.button.update",		"update");	 
		return map;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	//07-02-2013
		String forwardText 	= "success";
		String loginId 		= "";
		String appCode  	= "hr21";
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
						
			EmployeeForm empform = (EmployeeForm) form;
			String empTypeCode	= empform.getEmpTypeCode();
			String empDeptCode	= empform.getEmpDeptCode();
			String empID		= empform.getEmpID();			
    		String empNameT		= new String(empform.getEmpNameT().getBytes("ISO8859_1"),"utf-8");
			String empLastNameT	= new String(empform.getEmpLastNameT().getBytes("ISO8859_1"),"utf-8");
			String empNameE		= new String(empform.getEmpNameE().getBytes("ISO8859_1"),"utf-8");
			String empLastNameE	= new String(empform.getEmpLastNameE().getBytes("ISO8859_1"),"utf-8");
			String address1		= new String(empform.getAddress1().getBytes("ISO8859_1"),"utf-8");
			String address2		= new String(empform.getAddress2().getBytes("ISO8859_1"),"utf-8");
			String tel1			= new String(empform.getTel1().getBytes("ISO8859_1"),"utf-8");
			String email1		= new String(empform.getEmail1().getBytes("ISO8859_1"),"utf-8");
			String sex			= new String(empform.getSex().getBytes("ISO8859_1"),"utf-8");
			String nation		= new String(empform.getNation().getBytes("ISO8859_1"),"utf-8");
			String offiCode		= empform.getOffiCode();
			String marryStatus 	= empform.getMarryStatus();
			String allChild		= empform.getAllChild();
			String edcChild		= empform.getEdcChild();
			String deductType	= empform.getDeductType();
						
			String birthDay		= empform.getBirthDay();
			String workDate		= empform.getWorkDate();
			String expDate		= empform.getExpDate();
			String educCode		= empform.getEducCode();
			String major		= new String (empform.getMajor().getBytes("ISO8859_1"),"utf-8");
			String groupName 	= new String (empform.getGroupName().getBytes("ISO8859_1"),"utf-8");
			String projCode		= empform.getProjCode();
			String subProjCode	= empform.getSubProjCode();
			String pfundDate	= empform.getPfundDate();
			String pfFlag		= "0";	if (request.getParameterValues("pfFlag") != null)  pfFlag = "1";
			String punchCard	= empform.getPunchCard();
			String status		= empform.getStatus();
						
			String accountNo	= empform.getAccountNo();
			String taxID		= empform.getTaxID();
			String idPop		= empform.getIdPop();			
			String socialNo		= empform.getSocialNo();
			String insure		= empform.getInsure();
			String devote		= empform.getDevote();
			String interestLoan = empform.getInterestLoan();
			String alertMessage = "";	

			DateUtil dateUtil = new DateUtil();
			if (!expDate.equals("")) {
				if (expDate.trim().length() != 10) alertMessage = "check resign date";
				else if (!dateUtil.isValidDateStr(dateUtil.CnvToYYYYMMDDEngYear(expDate, '-'))) alertMessage = "check resign date";
			}

			DBEmployee dbEmployee = new DBEmployee();
			if (status.equals("CL") && alertMessage.equals("")) {
				dbEmployee.CancelEmployee(empID, expDate);
				empform.setEmpNameT("");
		    	empform.setEmpLastNameT("");
				return mapping.findForward("success");
			}
			
			if (idPop.trim().length() != 13) alertMessage = "check ID Card 13 digits";
			else if (birthDay.trim().length() != 10) alertMessage = "check birth date";
			else if (!dateUtil.isValidDateStr(dateUtil.CnvToYYYYMMDDEngYear(birthDay, '-'))) alertMessage = "check birth date";
			
			else if (workDate.trim().length() != 10) alertMessage = "check start work date";
			else if (!dateUtil.isValidDateStr(dateUtil.CnvToYYYYMMDDEngYear(workDate, '-'))) alertMessage = "check start work date";

			if (!pfundDate.equals("")) { 
				if (pfundDate.trim().length() != 10) alertMessage = "check provident fund date";
				else if (!dateUtil.isValidDateStr(dateUtil.CnvToYYYYMMDDEngYear(pfundDate, '-'))) alertMessage = "check provident fund date";
			} 
			if (!(projCode.equals("") && subProjCode.equals(""))) {
				//DBProjectType dbProjectType = new DBProjectType();
				//if (!dbProjectType.CheckSubJob(projCode, subProjCode)) alertMessage = "check project and sub job code";
			}
			DBMasterTable dbMasterTable = new DBMasterTable();
			if (dbMasterTable.GetMasterTableDTList("dept", empDeptCode, "", "AC").size() != 1)
				alertMessage = "check department code or trade no.";
						
			if (alertMessage.equals("")) {			
	    
				dbEmployee.EditEmployee(empID, empNameT, empLastNameT, empNameE, empLastNameE, empTypeCode, empDeptCode,
					offiCode, address1, address2, tel1, email1, nation, sex, marryStatus, allChild, edcChild, deductType, 
					birthDay, workDate, expDate, educCode, major, groupName, projCode, subProjCode, 
					pfundDate, pfFlag, punchCard, status, accountNo, taxID, idPop, socialNo, insure, devote, 
					interestLoan);

				DBTaxTemp dbTaxTemp = new DBTaxTemp();
				if (!dbTaxTemp.CheckTaxId(idPop)) 
					dbTaxTemp.AddTaxTemp(idPop, empID, marryStatus, edcChild, allChild, deductType, devote, insure, interestLoan);
				
				dbTaxTemp.CalTaxTemp(idPop, empID);
				
				List employeeList = dbEmployee.GetEmployeeList(empID, "", "", "", "", "", "");
				if (employeeList.size() > 0) request.setAttribute("employeeList", employeeList);
				
				empform.initial();
				empform.setStatus("AC");
			} else {
				forwardText = "repeat";
				request.setAttribute("alertMessage", alertMessage);
				
				List educList = dbMasterTable.GetMasterTableDTList("educ", "", "", "");
				if (educList.size() > 0) request.setAttribute("educList", educList);
				
		    	empform.setEmpNameT(empNameT);
		    	empform.setEmpLastNameT(empLastNameT);
		    	empform.setAddress1(address1);
		    	empform.setAddress2(address2);
		    	empform.setTel1(tel1);
		    	empform.setNation(nation);
		    	empform.setMajor(major);
		    	empform.setGroupName(groupName);
			}
			DBMemberType dbMemberType = new DBMemberType();
			List memberTypeList = dbMemberType.GetMemberTypeList("", "", "");
			if (memberTypeList.size() > 0) request.setAttribute("memberTypeList", memberTypeList);
							
			List deptList = dbMasterTable.GetMasterTableDTList("dept", "", "", "AC");
			if (deptList.size() > 0) request.setAttribute("deptList", deptList);

			List offiList = dbMasterTable.GetMasterTableDTList("offi", "", "", "AC");
			if (offiList.size() > 0) request.setAttribute("offiList", offiList);
			
			request.setAttribute("empID", empID);
	    	 
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mapping.findForward(forwardText);
	}
}