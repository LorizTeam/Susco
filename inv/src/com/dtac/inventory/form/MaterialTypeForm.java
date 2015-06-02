package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;

public class MaterialTypeForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String matTypeCode;
	private String matTypeName;
	private String matStatus;
	private String wahoCode;
	
	private String matGrpCode;
	private String matGrpName;
	private String matGrpStatus;

	public MaterialTypeForm(){};
	public MaterialTypeForm(String matTypeCode, String matTypeName, String matStatus) { //14-04-2012
		super();
		this.matTypeCode = matTypeCode;
		this.matTypeName = matTypeName;
		this.matStatus = matStatus;
	}
	public MaterialTypeForm(String matTypeCode, String matTypeName, String matStatus, 
		String matGrpCode, String matGrpName, String matGrpStatus) {
		super();
		this.matTypeCode = matTypeCode;
		this.matTypeName = matTypeName;
		this.matStatus = matStatus;
		this.matGrpCode = matGrpCode;
		this.matGrpName = matGrpName;
		this.matGrpStatus = matGrpStatus;
	}
	public void initial() {
  		this.matTypeCode	= "";
  		this.matTypeName	= "";
  		this.matStatus	= "";
  		this.matGrpCode		= "";
  		this.matGrpName		= "";
  		this.matGrpStatus	= "";
  	}
	public String getMatTypeCode() {
		return matTypeCode;
	}
	public void setMatTypeCode(String matTypeCode) {
		this.matTypeCode = matTypeCode;
	}
	public String getMatTypeName() {
		return matTypeName;
	}
	public void setMatTypeName(String matTypeName) {
		this.matTypeName = matTypeName;
	}
	public String getMatGrpCode() {
		return matGrpCode;
	}
	public void setMatGrpCode(String matGrpCode) {
		this.matGrpCode = matGrpCode;
	}
	public String getMatGrpName() {
		return matGrpName;
	}
	public void setMatGrpName(String matGrpName) {
		this.matGrpName = matGrpName;
	}
	public String getMatGrpStatus() {
		return matGrpStatus;
	}
	public void setMatGrpStatus(String matGrpStatus) {
		this.matGrpStatus = matGrpStatus;
	}
	public String getWahoCode() {
		return wahoCode;
	}
	public void setWahoCode(String wahoCode) {
		this.wahoCode = wahoCode;
	}
	public String getMatStatus() {
		return matStatus;
	}
	public void setMatStatus(String matStatus) {
		this.matStatus = matStatus;
	}

}