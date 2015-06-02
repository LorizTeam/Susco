package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;

public class MatDocTypeForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String docGrpCode;
	private String docTypeCode;
	private String docTypeName;
	private String status;
	
	public MatDocTypeForm(){};		
	public MatDocTypeForm(String docGrpCode, String docTypeCode, String docTypeName, String status) {
		super();
		this.docGrpCode = docGrpCode;
		this.docTypeCode = docTypeCode;
		this.docTypeName = docTypeName;
		this.status = status;
	}
	public void initial() {
  		this.docGrpCode		= "";
  		this.docTypeCode	= "";
  		this.docTypeName	= "";
  		this.status = "";
  	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDocGrpCode() {
		return docGrpCode;
	}
	public void setDocGrpCode(String docGrpCode) {
		this.docGrpCode = docGrpCode;
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public String getDocTypeName() {
		return docTypeName;
	}
	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

}