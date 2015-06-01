package com.dtac.admin.form;

public class MonthForm extends YearForm {
	
	private static final long serialVersionUID = 1L;
	public String month;
	public String monthTHAbbvName;
	public String monthTHFullName;
	public String monthENAbbvName;
	public String monthENFullName;
	
	public MonthForm(){}
	public MonthForm(String year, String month) {	//10-10-2011
		super(year);
		this.month = month;
	}
	public MonthForm(String year, String engYear, String month, 
		String monthTHAbbvName, String monthTHFullName,
		String monthENAbbvName, String monthENFullName) {	//10-10-2011
		super(year, engYear);
		this.month = month;
		this.monthTHAbbvName = monthTHAbbvName;
		this.monthTHFullName = monthTHFullName;
		this.monthENAbbvName = monthENAbbvName;
		this.monthENFullName = monthENFullName;
	}
	public void initial() {
		this.year 		= "";
		this.engYear	= "";
		this.month		= "";
		this.monthTHAbbvName	= "";
		this.monthTHFullName	= "";
		this.monthENAbbvName	= "";
		this.monthENFullName	= "";
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonthENAbbvName() {
		return monthENAbbvName;
	}
	public void setMonthENAbbvName(String monthENAbbvName) {
		this.monthENAbbvName = monthENAbbvName;
	}
	public String getMonthENFullName() {
		return monthENFullName;
	}
	public void setMonthENFullName(String monthENFullName) {
		this.monthENFullName = monthENFullName;
	}
	public String getMonthTHAbbvName() {
		return monthTHAbbvName;
	}
	public void setMonthTHAbbvName(String monthTHAbbvName) {
		this.monthTHAbbvName = monthTHAbbvName;
	}
	public String getMonthTHFullName() {
		return monthTHFullName;
	}
	public void setMonthTHFullName(String monthTHFullName) {
		this.monthTHFullName = monthTHFullName;
	}
}