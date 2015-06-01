package com.dtac.inventory.form;

public class MaterialWahoForm extends MaterialForm {

	private static final long serialVersionUID = 1L;
	private String wahoCode;
	private String wahoName;
	private String wahoStatus;
	private String locaCode;
	private String locaName;

	private String lotNo;
	private String amntQty;
	private String brrwQty;
	private String alocQty;
	private String avalQty;
	private String costPerUnit;
	private String costAmount;
	private String lastRecvDate;
	private String lastIssuDate;
	private String matWahoStatus;

	private String transferQty;
	private String transferToLocaCode;
	/*private String initQty;
	private String recvQty;
	private String itrdQty;
	private String iprdQty;*/	
	
	public MaterialWahoForm(){};	
	public MaterialWahoForm(String wahoCode, String wahoName,
			String wahoStatus, String locaCode, String locaName, String lotNo,
			String amntQty, String brrwQty, String alocQty, String avalQty,
			String lastRecvDate, String lastIssuDate, String matWahoStatus) {
		super();
		this.wahoCode = wahoCode;
		this.wahoName = wahoName;
		this.wahoStatus = wahoStatus;
		this.locaCode = locaCode;
		this.locaName = locaName;
		this.lotNo = lotNo;
		this.amntQty = amntQty;
		this.brrwQty = brrwQty;
		this.alocQty = alocQty;
		this.avalQty = avalQty;
		this.lastRecvDate = lastRecvDate;
		this.lastIssuDate = lastIssuDate;
		this.matWahoStatus = matWahoStatus;
	}
	public MaterialWahoForm(String matCode, String serial,String matName,	String matSendName, String matSearchName, 
		String pUnit, String pUnitName, String rUnit, String rUnitName, String iUnit, String iUnitName,
		String matTypeCode, String matTypeName, String matStockType, String matGrpCode, String matGrpName, 
		String matBrandCode, String matBrandName, String matSupplCode, String matSupplName,
		String matColorCode, String matColorName, String matStuffCode, String matStuffName, String refMatCode, String refMatName,
		String matStatus, String matRemark, String sellPrice, String costPrice, String qtyMaxStock,
		String qtyMinStock, String updateDate, String updateByCode, String updateByName, 
		String reqOrdFlag, String pic1, String pic2, String normalPrice, String empPrice, 
		String vipPrice, String specialPrice, String stock1, String stock2,String stock3, String stock4, String rop,
		
		String wahoCode, String wahoName,
		String wahoStatus, String locaCode, String locaName, String lotNo,
		String amntQty, String brrwQty, String alocQty, String avalQty, String costPerUnit, String costAmount,
		String lastRecvDate, String lastIssuDate, String matWahoStatus) { //15-06-2012
		
		super(matCode,serial, matName, matSendName, matSearchName, pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName, 
		matTypeCode, matTypeName, matStockType, matGrpCode, matGrpName, 
		matBrandCode, matBrandName, matSupplCode, matSupplName,
		matColorCode, matColorName, matStuffCode, matStuffName, refMatCode, refMatName,
		matStatus, matRemark, sellPrice, costPrice, qtyMaxStock, qtyMinStock, updateDate, updateByCode, updateByName,
		reqOrdFlag, pic1, pic2, normalPrice, empPrice, vipPrice, specialPrice,"","","", stock1, stock2, stock3, stock4, rop);
		
		this.wahoCode = wahoCode;
		this.wahoName = wahoName;
		this.wahoStatus = wahoStatus;
		this.locaCode = locaCode;
		this.locaName = locaName;
		this.lotNo = lotNo;
		this.amntQty = amntQty;
		this.brrwQty = brrwQty;
		this.alocQty = alocQty;
		this.avalQty = avalQty;
		this.costPerUnit = costPerUnit;
		this.costAmount = costAmount;
		this.lastRecvDate = lastRecvDate;
		this.lastIssuDate = lastIssuDate;
		this.matWahoStatus = matWahoStatus;		
	}

	public void initial() {
		this.wahoCode	= "";
		this.wahoName	= "";
		this.brrwQty	= "0";
		this.alocQty	= "0";
		this.avalQty	= "0";
		this.lastRecvDate	= "";
		this.lastIssuDate	= "";

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
	public String getLastRecvDate() {
		return lastRecvDate;
	}
	public void setLastRecvDate(String lastRecvDate) {
		this.lastRecvDate = lastRecvDate;
	}
	public String getLastIssuDate() {
		return lastIssuDate;
	}
	public void setLastIssuDate(String lastIssuDate) {
		this.lastIssuDate = lastIssuDate;
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
	public String getWahoStatus() {
		return wahoStatus;
	}
	public void setWahoStatus(String wahoStatus) {
		this.wahoStatus = wahoStatus;
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
	public String getMatWahoStatus() {
		return matWahoStatus;
	}
	public void setMatWahoStatus(String matWahoStatus) {
		this.matWahoStatus = matWahoStatus;
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
	public String getTransferQty() {
		return transferQty;
	}
	public void setTransferQty(String transferQty) {
		this.transferQty = transferQty;
	}
	public String getTransferToLocaCode() {
		return transferToLocaCode;
	}
	public void setTransferToLocaCode(String transferToLocaCode) {
		this.transferToLocaCode = transferToLocaCode;
	}
}