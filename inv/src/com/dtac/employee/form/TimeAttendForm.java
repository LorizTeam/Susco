package com.dtac.employee.form;

import org.apache.struts.action.ActionForm;

public class TimeAttendForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String empID; 
	private String empTypeCode;
	private String empDeptCode;
	private String groupName;	
	private String fromDate;
	private String toDate;
	private String year;
	private String month;
	private String reportNo;
		
	public TimeAttendForm() {} //13-01-2012
	public void initial() {
		this.empID = "";
		this.empTypeCode = "";
		this.empDeptCode = "";
		this.groupName = "";
		this.fromDate = "";
		this.toDate = "";
		this.reportNo = "1";
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getEmpDeptCode() {
		return empDeptCode;
	}
	public void setEmpDeptCode(String empDeptCode) {
		this.empDeptCode = empDeptCode;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

 }