package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;

public class WarehouseForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String wahoCode;
	private String wahoName;
	private String wahoStatus;
	private String locaCode;
	private String locaName;
	private String locaStatus;
	private String pic1;
	private String pic2;
	
	private String empID;	
	private String empNameT;
	private String empLastNameT;
	private String authStatus;
	
	public WarehouseForm(){}
	public WarehouseForm(String wahoCode, String wahoName, String wahoStatus) {
		super();
		this.wahoCode 	= wahoCode;
		this.wahoName 	= wahoName;
		this.wahoStatus	= wahoStatus;
	}
	public WarehouseForm(String wahoCode, String wahoName, String wahoStatus,
		String locaCode, String locaName, String locaStatus, String pic1, String pic2) { //31-05-2012
		super();
		this.wahoCode = wahoCode;
		this.wahoName = wahoName;
		this.wahoStatus = wahoStatus;
		this.locaCode = locaCode;
		this.locaName = locaName;
		this.locaStatus = locaStatus;
		this.pic1 = pic1;
		this.pic2 = pic2;
	}	
	public WarehouseForm(String wahoCode, String wahoName, String empID,
		String empNameT, String empLastNameT, String authStatus) { //17-06-2012
		super();
		this.wahoCode = wahoCode;
		this.wahoName = wahoName;
		this.empID = empID;
		this.empNameT = empNameT;
		this.empLastNameT = empLastNameT;
		this.authStatus = authStatus;
	}
	public void initial() {
		this.wahoCode 	= "";
		this.wahoName 	= "";
		this.wahoStatus	= "";
		this.locaCode 	= "";
		this.locaName 	= "";
		this.locaStatus = "";
		this.pic1 = "";
		this.pic2 = "";
	}
	public String getWahoCode() {
		return wahoCode;
	}
	public void setWahoCode(String wahoCode) {
		this.wahoCode = wahoCode;
	}
	public String getWahoName() {
		return wahoName;
	}
	public void setWahoName(String wahoName) {
		this.wahoName = wahoName;
	}
	public String getWahoStatus() {
		return wahoStatus;
	}
	public void setWahoStatus(String wahoStatus) {
		this.wahoStatus = wahoStatus;
	}
	public String getLocaCode() {
		return locaCode;
	}
	public void setLocaCode(String locaCode) {
		this.locaCode = locaCode;
	}
	public String getLocaName() {
		return locaName;
	}
	public void setLocaName(String locaName) {
		this.locaName = locaName;
	}
	public String getLocaStatus() {
		return locaStatus;
	}
	public void setLocaStatus(String locaStatus) {
		this.locaStatus = locaStatus;
	}
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpNameT() {
		return empNameT;
	}
	public void setEmpNameT(String empNameT) {
		this.empNameT = empNameT;
	}
	public String getEmpLastNameT() {
		return empLastNameT;
	}
	public void setEmpLastNameT(String empLastNameT) {
		this.empLastNameT = empLastNameT;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
}