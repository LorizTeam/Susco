package com.dtac.employee.form;

import com.dtac.admin.form.MonthForm;

public class PeriodForm extends MonthForm {
	
	private static final long serialVersionUID = 1L;
	public String periodNo;
	public String salTypeCode;
	public String salTypeName;
	public String socialRate;
	public String startDate;
	public String endDate;
	public String currFlag;
	public String status;
	public String lastDocNo;
	
	public PeriodForm(){}	
    public PeriodForm(String year, String engYear, String month,
		String monthTHAbbvName, String monthTHFullName,
		String monthENAbbvName, String monthENFullName,	String currFlag, String status, String lastDocNo) {
		//25-02-2012
    	super(year, engYear, month, monthTHAbbvName, monthTHFullName,
			monthENAbbvName, monthENFullName);
		this.currFlag = currFlag;
		this.status = status;
		this.lastDocNo = lastDocNo;
	}
    public PeriodForm(String year, String engYear, String month,
		String monthTHAbbvName, String monthTHFullName,
		String monthENAbbvName, String monthENFullName, 
		String periodNo, String salTypeCode, String salTypeName, String socialRate,
		String startDate, String endDate, String currFlag, String status) { //22-08-2012
		super(year, engYear, month, monthTHAbbvName, monthTHFullName,
			monthENAbbvName, monthENFullName);
		this.periodNo = periodNo;
		this.salTypeCode = salTypeCode;
		this.salTypeName = salTypeName;
		this.socialRate = socialRate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.currFlag = currFlag;
		this.status = status;
	}
    public void initial() {
		this.year 		= "";
		this.engYear	= "";
		this.month		= "";
		this.periodNo 	= "";
		this.salTypeCode= "";
		this.socialRate = "5";
		this.startDate	= "";
		this.endDate	= "";
		this.status		= "";
		this.currFlag	= "";
	}
	public String getPeriodNo() {
		return periodNo;
	}
	public void setPeriodNo(String periodNo) {
		this.periodNo = periodNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCurrFlag() {
		return currFlag;
	}
	public void setCurrFlag(String currFlag) {
		this.currFlag = currFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getLastDocNo() {
		return lastDocNo;
	}
	public void setLastDocNo(String lastDocNo) {
		this.lastDocNo = lastDocNo;
	}
	public String getSocialRate() {
		return socialRate;
	}
	public void setSocialRate(String socialRate) {
		this.socialRate = socialRate;
	}	
}