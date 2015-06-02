package com.dtac.inventory.form;

public class ChkStockForm extends MaterialForm {

	private static final long serialVersionUID = 1L;
	private String docYear;
	private String docMonth;

	private String wahoCode;
	private String wahoName;
	private String locaCode;
	private String locaName;

	private String lotNo;
	private String amntQty;
	private String brrwQty;
	private String alocQty;
	private String avalQty;
	private String chkStockQty;
	private String costPerUnit;
	private String costAmount;
	private String chkStockDate;
	private String chkStockStatus;

	
	public ChkStockForm(){};	
	public ChkStockForm(String matCode,String serial, String matName,	String matSendName, String matSearchName, 
		String pUnit, String pUnitName, String rUnit, String rUnitName, String iUnit, String iUnitName,
		String matTypeCode, String matTypeName, String matStockType, String matGrpCode, String matGrpName, 
		String matBrandCode, String matBrandName, String matSupplCode, String matSupplName,
		String matColorCode, String matColorName, String matStuffCode, String matStuffName, String refMatCode, String refMatName,
		String matStatus, String matRemark, String sellPrice, String costPrice, String qtyMaxStock,
		String qtyMinStock, String updateDate, String updateByCode, String updateByName, 
		String reqOrdFlag, String pic1, String pic2, String normalPrice, String empPrice, 
		String vipPrice, String specialPrice, String stock1, String stock2,String stock3, String stock4, String rop,
		
		String docYear, String docMonth, String wahoCode, String wahoName,
		String locaCode, String locaName, String lotNo,
		String amntQty, String brrwQty, String alocQty, String avalQty, String chkStockQty, String costPerUnit, String costAmount,
		String chkStockDate, String chkStockStatus ) { //18-10-2012
		
		super(matCode,serial, matName, matSendName, matSearchName, pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName, 
		matTypeCode, matTypeName, matStockType, matGrpCode, matGrpName, 
		matBrandCode, matBrandName, matSupplCode, matSupplName,
		matColorCode, matColorName, matStuffCode, matStuffName, refMatCode, refMatName,
		matStatus, matRemark, sellPrice, costPrice, qtyMaxStock, qtyMinStock, updateDate, updateByCode, updateByName,
		reqOrdFlag, pic1, pic2, normalPrice, empPrice, vipPrice, specialPrice,"","","", stock1, stock2,stock3, stock4, rop);
		
		this.docYear = docYear;
		this.docMonth = docMonth;
		this.wahoCode = wahoCode;
		this.wahoName = wahoName;
		this.locaCode = locaCode;
		this.locaName = locaName;
		this.lotNo = lotNo;
		this.amntQty = amntQty;
		this.brrwQty = brrwQty;
		this.alocQty = alocQty;
		this.avalQty = avalQty;
		this.chkStockQty = chkStockQty;
		this.costPerUnit = costPerUnit;
		this.costAmount = costAmount;
		this.chkStockDate	= chkStockDate;
		this.chkStockStatus = chkStockStatus;
	}

	public void initial() {
		this.docYear	= "";
		this.docMonth	= "";
		this.wahoCode	= "";
		this.wahoName	= "";
		this.brrwQty	= "0";
		this.alocQty	= "0";
		this.avalQty	= "0";
		this.chkStockStatus	= "";
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
	public String getChkStockDate() {
		return chkStockDate;
	}
	public void setChkStockDate(String chkStockDate) {
		this.chkStockDate = chkStockDate;
	}
	public String getBrrwQty() {
		return brrwQty;
	}
	public void setBrrwQty(String brrwQty) {
		this.brrwQty = brrwQty;
	}
	public String getAlocQty() {
		return alocQty;
	}
	public void setAlocQty(String alocQty) {
		this.alocQty = alocQty;
	}
	public String getAvalQty() {
		return avalQty;
	}
	public void setAvalQty(String avalQty) {
		this.avalQty = avalQty;
	}
	public String getChkStockQty() {
		return chkStockQty;
	}
	public void setChkStockQty(String chkStockQty) {
		this.chkStockQty = chkStockQty;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getAmntQty() {
		return amntQty;
	}
	public void setAmntQty(String amntQty) {
		this.amntQty = amntQty;
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
	public String getCostPerUnit() {
		return costPerUnit;
	}
	public void setCostPerUnit(String costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	public String getCostAmount() {
		return costAmount;
	}
	public void setCostAmount(String costAmount) {
		this.costAmount = costAmount;
	}
	public String getDocYear() {
		return docYear;
	}
	public void setDocYear(String docYear) {
		this.docYear = docYear;
	}
	public String getDocMonth() {
		return docMonth;
	}
	public void setDocMonth(String docMonth) {
		this.docMonth = docMonth;
	}
	public String getChkStockStatus() {
		return chkStockStatus;
	}
	public void setChkStockStatus(String chkStockStatus) {
		this.chkStockStatus = chkStockStatus;
	}
}