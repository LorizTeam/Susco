package com.dtac.inventory.form;

public class MaterialPeriodForm extends MaterialForm {

	private static final long serialVersionUID = 1L;
	private String docYear;
	private String docMonth;
	private String initQty;
	private String initAmount;
	private String recvQty;
	private String recvAmount;
	private String issuQty;
	private String issuAmount;
	private String brrwQty;
	private String avalQty;
	private String lastQty;
	private String lastAmount;

	private String lastPRDate;
	private String statusPR;
	private String lastPODate;
	private String statusPO;
	
	public MaterialPeriodForm(){};	
	public MaterialPeriodForm(String matCode, String serial,String matName, String matSendName, String matSearchName, 
		String pUnit, String pUnitName, String rUnit, String rUnitName, String iUnit, String iUnitName,
		String matTypeCode, String matTypeName, String matStockType, String matGrpCode, String matGrpName, 
		String matBrandCode, String matBrandName, String matSupplCode, String matSupplName,
		String matColorCode, String matColorName, String matStuffCode, String matStuffName, String refMatCode, String refMatName,
		String matStatus, String matRemark, String sellPrice, String costPrice, String qtyMaxStock,
		String qtyMinStock, String updateDate, String updateByCode, String updateByName, 
		String reqOrdFlag, String pic1, String pic2, String normalPrice, String empPrice, 
		String vipPrice, String specialPrice, String stock1, String stock2,String stock3, String stock4, String rop,
		
		String docYear, String docMonth, String initQty, String initAmount,
		String recvQty, String recvAmount, String issuQty, String issuAmount, String brrwQty, 
		String avalQty, String lastQty, String lastAmount,
		String lastPRDate, String statusPR, String lastPODate, String statusPO) { //15-06-2012
		
		super(matCode,serial, matName, matSendName, matSearchName, pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName, 
		matTypeCode, matTypeName, matStockType, matGrpCode, matGrpName, 
		matBrandCode, matBrandName, matSupplCode, matSupplName,
		matColorCode, matColorName, matStuffCode, matStuffName, refMatCode, refMatName,
		matStatus, matRemark, sellPrice, costPrice, qtyMaxStock, qtyMinStock, updateDate, updateByCode, updateByName,
		reqOrdFlag, pic1, pic2, normalPrice, empPrice, vipPrice, specialPrice,"","","", stock1, stock2,stock3, stock4, rop);
		
		this.docYear 	= docYear;
		this.docMonth 	= docMonth;
		this.initQty 	= initQty;
		this.initAmount = initAmount;
		this.recvQty 	= recvQty;
		this.recvAmount = recvAmount;
		this.issuQty 	= issuQty;
		this.issuAmount = issuAmount;
		this.brrwQty	= brrwQty;
		this.avalQty 	= avalQty;
		this.lastQty 	= lastQty;
		this.lastAmount = lastAmount;
		this.lastPRDate = lastPRDate;
		this.lastPODate = lastPODate;
		this.statusPR = statusPR;
		this.statusPO = statusPO;
	}
	public void initial() {
		this.docYear	= "";
		this.docMonth	= "";
		this.lastPRDate = "";
		this.lastPODate = "";
		this.statusPR = "";
		this.statusPO = "";

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
	public String getInitQty() {
		return initQty;
	}
	public void setInitQty(String initQty) {
		this.initQty = initQty;
	}
	public String getRecvQty() {
		return recvQty;
	}
	public void setRecvQty(String recvQty) {
		this.recvQty = recvQty;
	}
	public String getIssuQty() {
		return issuQty;
	}
	public void setIssuQty(String issuQty) {
		this.issuQty = issuQty;
	}
	public String getAvalQty() {
		return avalQty;
	}
	public void setAvalQty(String avalQty) {
		this.avalQty = avalQty;
	}
	public String getLastQty() {
		return lastQty;
	}
	public void setLastQty(String lastQty) {
		this.lastQty = lastQty;
	}
	public String getLastPRDate() {
		return lastPRDate;
	}
	public void setLastPRDate(String lastPRDate) {
		this.lastPRDate = lastPRDate;
	}
	public String getStatusPR() {
		return statusPR;
	}
	public void setStatusPR(String statusPR) {
		this.statusPR = statusPR;
	}
	public String getLastPODate() {
		return lastPODate;
	}
	public void setLastPODate(String lastPODate) {
		this.lastPODate = lastPODate;
	}
	public String getStatusPO() {
		return statusPO;
	}
	public void setStatusPO(String statusPO) {
		this.statusPO = statusPO;
	}
	public String getInitAmount() {
		return initAmount;
	}
	public void setInitAmount(String initAmount) {
		this.initAmount = initAmount;
	}
	public String getRecvAmount() {
		return recvAmount;
	}
	public void setRecvAmount(String recvAmount) {
		this.recvAmount = recvAmount;
	}
	public String getIssuAmount() {
		return issuAmount;
	}
	public void setIssuAmount(String issuAmount) {
		this.issuAmount = issuAmount;
	}
	public String getBrrwQty() {
		return brrwQty;
	}
	public void setBrrwQty(String brrwQty) {
		this.brrwQty = brrwQty;
	}
	public String getLastAmount() {
		return lastAmount;
	}
	public void setLastAmount(String lastAmount) {
		this.lastAmount = lastAmount;
	}

}