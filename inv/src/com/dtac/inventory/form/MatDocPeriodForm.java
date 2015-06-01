package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;

public class MatDocPeriodForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String year;
	private String month;
	private String startDate;
	private String endDate;
	private String currFlag;
	private String status;
	
	public MatDocPeriodForm(){};	
	public MatDocPeriodForm(String year, String month, String startDate,
		String endDate, String currFlag, String status) { //30-05-2012
		super();
		this.year = year;
		this.month = month;
		this.startDate = startDate;
		this.endDate = endDate;
		this.currFlag = currFlag;
		this.status = status;
	}
	public void initial() {
  		this.year		= "";
  		this.month		= "";
  		this.startDate	= "";
  		this.endDate	= "";
  		this.currFlag	= "";
  		this.status		= "";
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

}