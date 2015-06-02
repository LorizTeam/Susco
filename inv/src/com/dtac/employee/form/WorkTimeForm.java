package com.dtac.employee.form;

import org.apache.struts.action.ActionForm;

public class WorkTimeForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String workDate;
	private String workTimeStart;
	private String workTimeStop;

	private String empID; 		
	private String empNameT;
	private String empLastNameT;
	private String empNameE;
	private String empLastNameE;
		
	private String empTypeCode;
	private String empTypeName;
	private String empDeptCode;
	private String empDeptName;
	private String workStatus;
	private String startTime;
	private String stopTime;
	private String amlate;	//late in minute
	private String punchCard;
	
	private String r;	//normal
	private String w;	//sick
	private String b;	//business
	private String v;	//vacation
	private String c;	//sick during work
	private String a;	//absent
	private String l;	//late
	private String x;	//special business
	private String xw;	
	private String xb;
	private String xv;
	private String xx;
	
	private String otN1;
	private String otN1_5;
	private String otN2;
	private String otN3;

	private String year;	
	private String month;
	private String periodNo;
	private String salTypeCode;
	private String salTypeName;

	public WorkTimeForm() {} //13-11-2011	
	public WorkTimeForm(String workDate, String workTimeStart, String workTimeStop, 
		String empID, String empNameT, String empLastNameT, String empNameE, String empLastNameE,
		String empTypeCode, String empTypeName, String empDeptCode,
		String empDeptName, String startTime, String stopTime, String amlate, String punchCard,
		String r, String w, String b, String v, String c, String a, String l, String x,
		String xw, String xb, String xv, String xx,
		String otN1, String otN1_5, String otN2, String otN3, String workStatus) { //02-06-2012
		super();
		this.workDate = workDate;
		this.workTimeStart = workTimeStart;
		this.workTimeStop = workTimeStop;
		this.empID = empID;
		this.empNameT = empNameT;
		this.empLastNameT = empLastNameT;
		this.empNameE = empNameE;
		this.empLastNameE = empLastNameE;
		this.empTypeCode = empTypeCode;
		this.empTypeName = empTypeName;
		this.empDeptCode = empDeptCode;
		this.empDeptName = empDeptName;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.amlate = amlate;
		this.punchCard = punchCard;
		this.r = r;
		this.w = w;
		this.b = b;
		this.v = v;
		this.c = c;
		this.a = a;
		this.l = l;
		this.x = x;
		this.xw = xw;
		this.xb = xb;
		this.xv = xv;
		this.xx = xx;
		this.otN1 = otN1;
		this.otN1_5 = otN1_5;
		this.otN2 = otN2;
		this.otN3 = otN3;
		this.workStatus = workStatus;
	}
	public void initial() {
		this.empID = "";
		this.workDate = "";
		this.workTimeStart = "";
		this.workTimeStop = "";
		this.empNameT = "";
		this.empLastNameT = "";
		this.startTime = "";
		this.stopTime = "";
		this.amlate = "00.00";
		this.punchCard = "";
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
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
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public String getW() {
		return w;
	}
	public void setW(String w) {
		this.w = w;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getL() {
		return l;
	}
	public void setL(String l) {
		this.l = l;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
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
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getPunchCard() {
		return punchCard;
	}
	public void setPunchCard(String punchCard) {
		this.punchCard = punchCard;
	}
	public String getAmlate() {
		return amlate;
	}
	public void setAmlate(String amlate) {
		this.amlate = amlate;
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
	public String getPeriodNo() {
		return periodNo;
	}
	public void setPeriodNo(String periodNo) {
		this.periodNo = periodNo;
	}
	public String getSalTypeCode() {
		return salTypeCode;
	}
	public void setSalTypeCode(String salTypeCode) {
		this.salTypeCode = salTypeCode;
	}
	public String getSalTypeName() {
		return salTypeName;
	}
	public void setSalTypeName(String salTypeName) {
		this.salTypeName = salTypeName;
	}
	public String getWorkTimeStart() {
		return workTimeStart;
	}
	public void setWorkTimeStart(String workTimeStart) {
		this.workTimeStart = workTimeStart;
	}
	public String getWorkTimeStop() {
		return workTimeStop;
	}
	public void setWorkTimeStop(String workTimeStop) {
		this.workTimeStop = workTimeStop;
	}
	public String getXw() {
		return xw;
	}
	public void setXw(String xw) {
		this.xw = xw;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXv() {
		return xv;
	}
	public void setXv(String xv) {
		this.xv = xv;
	}
	public String getXx() {
		return xx;
	}
	public void setXx(String xx) {
		this.xx = xx;
	}

}