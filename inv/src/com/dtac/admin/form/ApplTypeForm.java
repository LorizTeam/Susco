package com.dtac.admin.form;

import org.apache.struts.action.ActionForm;

public class ApplTypeForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	public String applTypeCode;
	public String applTypeName;
		
	public ApplTypeForm() {}
	public ApplTypeForm(String applTypeCode, String applTypeName) {
		super();
		this.applTypeCode = applTypeCode;
		this.applTypeName = applTypeName;
	}

	public void initial() {
		this.applTypeCode	= "";
		this.applTypeName	= "";
	}
	public String getApplTypeCode() {
		return applTypeCode;
	}
	public void setApplTypeCode(String applTypeCode) {
		this.applTypeCode = applTypeCode;
	}
	public String getApplTypeName() {
		return applTypeName;
	}
	public void setApplTypeName(String applTypeName) {
		this.applTypeName = applTypeName;
	}
	
}