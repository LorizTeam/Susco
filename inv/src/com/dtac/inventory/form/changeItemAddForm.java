package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;


public class changeItemAddForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	 
	private String docType;
	private String prCode;
	private String docCode;
	
	
	public changeItemAddForm(){};
	public changeItemAddForm(String docType, String prCode, String docCode) { 
	super();
	this.docCode	= docCode;
	this.prCode		= prCode;
	this.docType	= docType;
	}
	
	public void initial() {
		this.docCode 	= "";
		this.prCode		= "";
		this.docCode	= "";
	}
	
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getPrCode() {
		return prCode;
	}
	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}
	public String getDocCode() {
		return docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
