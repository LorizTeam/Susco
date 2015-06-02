package com.dtac.admin.form;

import org.apache.struts.action.ActionForm;

public class DayForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	public String dayNo;
	public String dayTHAbbvName;
	public String dayTHFullName;
	public String dayENAbbvName;
	public String dayENFullName;

	public DayForm(){};
	public DayForm(String dayNo, String dayTHAbbvName, String dayTHFullName, String dayENAbbvName, 
		String dayENFullName){

		this.dayNo	= dayNo;
		this.dayENAbbvName	= dayENAbbvName;
		this.dayENFullName	= dayENFullName;
		this.dayTHAbbvName	= dayTHAbbvName;
		this.dayTHFullName	= dayTHFullName;
	};    
	public void initial() {
		this.dayNo	= "";
		this.dayENAbbvName	= "";
		this.dayENFullName	= "";
		this.dayTHAbbvName	= "";
		this.dayTHFullName	= "";
	}
	public String getDayNo() {
		return dayNo;
	}
	public void setDayNo(String dayNo) {
		this.dayNo = dayNo;
	}
	public String getDayENAbbvName() {
		return dayENAbbvName;
	}
	public void setDayENAbbvName(String dayENAbbvName) {
		this.dayENAbbvName = dayENAbbvName;
	}
	public String getDayENFullName() {
		return dayENFullName;
	}
	public void setDayENFullName(String dayENFullName) {
		this.dayENFullName = dayENFullName;
	}
	public String getDayTHAbbvName() {
		return dayTHAbbvName;
	}
	public void setDayTHAbbvName(String dayTHAbbvName) {
		this.dayTHAbbvName = dayTHAbbvName;
	}
	public String getDayTHFullName() {
		return dayTHFullName;
	}
	public void setDayTHFullName(String dayTHFullName) {
		this.dayTHFullName = dayTHFullName;
	}
}