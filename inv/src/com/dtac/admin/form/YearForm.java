package com.dtac.admin.form;

import org.apache.struts.action.ActionForm;

public class YearForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	public String year;
	public String engYear;
	  
	public YearForm() {}	//10-11-2009
	public YearForm(String year) {
		super();
		this.year = year;
	}
    public YearForm(String year, String engYear) {
    	super();
    	this.year = year;
    	this.engYear = engYear;
    }
	public void initial() {
      this.year 		= "";
      this.engYear		= "";
	}
	public String getEngYear() {
		return engYear;
	}
	public void setEngYear(String engYear) {
		this.engYear = engYear;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}