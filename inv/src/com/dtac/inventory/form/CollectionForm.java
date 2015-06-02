package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;


public class CollectionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String collectcode;
	private String collectname;
	private String matStatus;
	private String docDate;
	
	public CollectionForm(){};
	public CollectionForm(String collectcode, String collectname, String matStatus, String docDate) { 
	super();
	this.collectcode = collectcode;
	this.collectname = collectname;
	this.matStatus 	= matStatus;
	this.docDate 	= docDate;
	}
	
	public void initial() {
		this.collectcode = "";
		this.collectname = "";
		this.matStatus = "";
	}
	
	public String getCollectcode() {
		return collectcode;
	}
	public void setCollectcode(String collectcode) {
		this.collectcode = collectcode;
	}
	public String getCollectname() {
		return collectname;
	}
	public void setCollectname(String collectname) {
		this.collectname = collectname;
	}
	public String getMatStatus() {
		return matStatus;
	}
	public void setMatStatus(String matStatus) {
		this.matStatus = matStatus;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	
}
