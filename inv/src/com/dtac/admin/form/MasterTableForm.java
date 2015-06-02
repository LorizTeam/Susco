package com.dtac.admin.form;

import org.apache.struts.action.ActionForm;

public class MasterTableForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	public String applTypeCode;
	public String grpCode;
	public String grpName;
	public String typeCode;
	public String thName;
	public String enName;
	public String status;
	
	public MasterTableForm() {}	//15-11-2009
	public MasterTableForm(String applTypeCode, String grpCode, String grpName) {
		super();
		this.applTypeCode = applTypeCode;
		this.grpCode = grpCode;
		this.grpName = grpName;
	}
	public MasterTableForm(String grpCode, String grpName, 
		String typeCode, String thName, String enName, String status) { //20-12-2012
		super();
		this.grpCode = grpCode;
		this.grpName = grpName;
		this.typeCode = typeCode;
		this.thName = thName;
		this.enName = enName;
		this.status = status;
	}
	public void initial() {
		this.applTypeCode = "";
		this.grpCode = "";
		this.grpName = "";
		this.typeCode = "";
		this.thName = "";
		this.enName = "";
		this.status = "";
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getGrpCode() {
		return grpCode;
	}
	public void setGrpCode(String grpCode) {
		this.grpCode = grpCode;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public String getThName() {
		return thName;
	}
	public void setThName(String thName) {
		this.thName = thName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplTypeCode() {
		return applTypeCode;
	}
	public void setApplTypeCode(String applTypeCode) {
		this.applTypeCode = applTypeCode;
	}   
}