package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;

public class MaterialOrderForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	//header
	
	private String refcode;
	private String refname;
	private String refdate;
	private String refDepart;
	private String refMob;
	private String refRccode;
	private String refProjcode;
	private String refObj;
	//detail
	
	
	//return


	public MaterialOrderForm(){}	
	public MaterialOrderForm(String ReferCode) { //07-05-2013
		super();
		
	}
	public void initial() {
  	}
	

	public String getRefcode() {
	return refcode;
}
	public void setRefcode(String refcode) {
	this.refcode = refcode;
}
	public String getRefdate() {
		return refdate;
	}
	public String getRefname() {
		return refname;
	}
	public void setRefname(String refname) {
		this.refname = refname;
	}
	public void setRefdate(String refdate) {
		this.refdate = refdate;
	}
	public String getRefDepart() {
		return refDepart;
	}
	public void setRefDepart(String refDepart) {
		this.refDepart = refDepart;
	}
	public String getRefMob() {
		return refMob;
	}
	public void setRefMob(String refMob) {
		this.refMob = refMob;
	}
	public String getRefRccode() {
		return refRccode;
	}
	public void setRefRccode(String refRccode) {
		this.refRccode = refRccode;
	}
	public String getRefProjcode() {
		return refProjcode;
	}
	public void setRefProjcode(String refProjcode) {
		this.refProjcode = refProjcode;
	}
	public String getRefObj() {
		return refObj;
	}
	public void setRefObj(String refObj) {
		this.refObj = refObj;
	}
	
}