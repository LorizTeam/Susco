package com.dtac.employee.form;

import org.apache.struts.action.ActionForm;

public class FingPrntForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String empID; 
	private String userID;
	private String checkDate;
	private String checkType;
		
	private String empNameT;
	private String empLastNameT;
	private String empNameE;
	private String empLastNameE;
		
	private String empTypeCode;
	private String empTypeName;
	private String empDeptCode;
	private String empDeptName;
	private String offiCode;
	private String offiName;
	
	private String dayName;
	private String startTime;
	private String stopTime;
	
	private String normal;
	private String otN1;
	private String otN1_5;
	private String otN2;
	private String otN3;
	
	public FingPrntForm() {} //23-10-2011
	public FingPrntForm(String empID, String userID, String checkDate, String checkType) {
		super();
		this.empID = empID;
		this.userID = userID;
		this.checkDate = checkDate;
		this.checkType = checkType;
	}	
	public FingPrntForm(String empID, String checkDate, String empNameT,
		String empLastNameT, String empNameE, String empLastNameE,
		String empTypeCode, String empTypeName, String empDeptCode, String empDeptName, 
		String offiCode, String offiName, String dayName, String startTime, String stopTime,
		String normal, String otN1, String otN1_5, String otN2, String otN3) { //02-11-2011
		super();
		this.empID = empID;
		this.checkDate = checkDate;
		this.empNameT = empNameT;
		this.empLastNameT = empLastNameT;
		this.empNameE = empNameE;
		this.empLastNameE = empLastNameE;
		this.empTypeCode = empTypeCode;
		this.empTypeName = empTypeName;
		this.empDeptCode = empDeptCode;
		this.empDeptName = empDeptName;
		this.offiCode = offiCode; 
		this.offiName = offiName;
		this.dayName = dayName;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.normal = normal;
		this.otN1 = otN1;
		this.otN1_5 = otN1_5;
		this.otN2 = otN2;
		this.otN3 = otN3;
	}
	public void initial() {
		this.empID	= "";
		this.userID	= "";
		this.checkDate = "";
		this.checkType = "";
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getEmpNameT() {
		return empNameT;
	}
	public void setEmpNameT(String empNameT) {
		this.empNameT = empNameT;
	}
	public String getEmpLastNameT() {
		return empLastNameT;
	}
	public void setEmpLastNameT(String empLastNameT) {
		this.empLastNameT = empLastNameT;
	}
	public String getEmpNameE() {
		return empNameE;
	}
	public void setEmpNameE(String empNameE) {
		this.empNameE = empNameE;
	}
	public String getEmpLastNameE() {
		return empLastNameE;
	}
	public void setEmpLastNameE(String empLastNameE) {
		this.empLastNameE = empLastNameE;
	}
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getEmpTypeName() {
		return empTypeName;
	}
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	public String getEmpDeptCode() {
		return empDeptCode;
	}
	public void setEmpDeptCode(String empDeptCode) {
		this.empDeptCode = empDeptCode;
	}
	public String getEmpDeptName() {
		return empDeptName;
	}
	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getNormal() {
		return normal;
	}
	public void setNormal(String normal) {
		this.normal = normal;
	}
	public String getOtN1() {
		return otN1;
	}
	public void setOtN1(String otN1) {
		this.otN1 = otN1;
	}
	public String getOtN1_5() {
		return otN1_5;
	}
	public void setOtN1_5(String otN1_5) {
		this.otN1_5 = otN1_5;
	}
	public String getOtN2() {
		return otN2;
	}
	public void setOtN2(String otN2) {
		this.otN2 = otN2;
	}
	public String getOtN3() {
		return otN3;
	}
	public void setOtN3(String otN3) {
		this.otN3 = otN3;
	}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public String getOffiCode() {
		return offiCode;
	}
	public void setOffiCode(String offiCode) {
		this.offiCode = offiCode;
	}
	public String getOffiName() {
		return offiName;
	}
	public void setOffiName(String offiName) {
		this.offiName = offiName;
	}

 }