package com.dtac.admin.form;

import org.apache.struts.action.ActionForm;

public class SalaryTypeForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	public String salTypeCode;
	public String salTypeName;
	  
	public SalaryTypeForm() {}	//20-10-2011

	public SalaryTypeForm(String salTypeCode, String salTypeName) {
		super();
		this.salTypeCode = salTypeCode;
		this.salTypeName = salTypeName;
	}

	public void initial() {
      this.salTypeCode	= "";
      this.salTypeName	= "";
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
}