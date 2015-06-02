package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;

public class MaterialRetuForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	//header
	private String confirm;
	private String docTypeCode;
	private String docTypeName;
	private String docYear;
	private String docMonth;
	private String docNo;
	private String docDate;
	private String lotDate;
	private String docStatus;
	private String docByCode;
	private String docByName;
	private String refOrdTypeCode;
	private String refOrdTypeName;
	private String refOrdYear;
	private String refOrdNo;	
	private String wahoCode;
	private String wahoName;	
	private String emplCode;
	private String emplName;
	private String vendCode;
	private String vendName;
	private String remark;
	private String periodStatus;
	
	//RetuVend 16/06/2013
	private String  referrequertno;
	//lock 16/06/2013
	private String lockMatTypeCode;
	private String lockMatGrpCode;
	
	//detail
	private String itemNo;
	private String matCode;
	private String matName;
	private String refMatCode;
	private String lotNo;
	private String locaCode;
	private String locaName;
	private String borrowQty;	
	private String borrowStatus;
	private String borrowCancelStatus;
	private String borrowRemark;
	private String borrowDate;
	private String returnDate;
	private String updateDate;
	private String updateByCode;
	private String updateByName;
	private String retuStatus;
	private String retuAmount;
	private String rUnit;
	
	
	private String matTypeCode;		//14-06-2556
	private String matGrpCode;
	private String takeprice;		
	private String normalPrice;   	
	private String empPrice;			  
	private String costPrice;
	private String vipPrice;
	private String specialPrice;
	private String stock1;
	private String stock2;
	private String matCategories;
	private String matBrandCode;
	private String matColorCode;
	private String matStuffCode;	//14-06-2556
	private String retuRemark;
	
	//hidden text
	private String matBrandName;
	private String matTypeName;
	private String matGrpName;
	private String matColorName;
	private String rUnitName;
	private String matStuffName;
	private String refMatName;
	
	//text brow return date
	private String dateBrow;
	private String dateRetu;
	private String browRetu;
	private String chgRetu;
	private String qtyBrwRetu;
	
	//return
	private String retuQty;
	private String retuPricePerUnit;
	private String retuAvalQty;
	private String retuCode;
	private String retuName;

	private String amntQty;
	private String brrwQty;
	private String alocQty;
	private String avalQty;
	
	private String qtyBalance; //02-07-2556 23:56

	private String fromDate;
	private String toDate;
	private String reportNo;
	
	private String qtyReturn;
	private String chgStatus;
	private String cal;
	
	private String qtyReture;

	public MaterialRetuForm(){};	
	public MaterialRetuForm(String docTypeCode, String docYear, String docMonth, String docNo, String docDate, String lotDate, 
		String docStatus, String docByCode, String docByName, 
		String refOrdTypeCode, String refOrdTypeName, String refOrdYear, String refOrdNo,
		String wahoCode, String wahoName, String vendCode, String vendName, String remark, String periodStatus) { //16-06-2012
		super();
		this.docTypeCode = docTypeCode;
		this.docYear = docYear;
		this.docMonth = docMonth;
		this.docNo = docNo;
		this.docDate = docDate;
		this.lotDate = lotDate;
		this.docStatus = docStatus;
		this.docByCode = docByCode;
		this.docByName = docByName;
		this.refOrdTypeCode = refOrdTypeCode;
		this.refOrdTypeName = refOrdTypeName;
		this.refOrdYear = refOrdYear;
		this.refOrdNo = refOrdNo;
		this.wahoCode = wahoCode;
		this.wahoName = wahoName;
		this.vendCode = vendCode;
		this.vendName = vendName;
		this.remark = remark;
		this.periodStatus = periodStatus;
	}	
	public MaterialRetuForm(String docTypeCode, String docYear, String docMonth, String docNo, 
		String wahoCode, String matCode, String matName, String refMatCode, 
		String lotNo, String locaCode, String locaName, String borrowQty, String retuQty, String retuAvalQty,
		String borrowStatus, String borrowCancelStatus, String borrowRemark, String borrowDate, String returnDate,
		String updateDate, String updateByCode, String updateByName, 
		String amntQty, String brrwQty, String alocQty, String avalQty,
		String itemNo, String rUnit, String rUnitName, String retuAmount, String retuPricePerUnit, 
		String matTypeCode, String matTypeName, String matBrandCode, String matBrandName, String refMatName,
		String matGrpCode, String matGrpName, String matColorCode, String matColorName, String matStuffCode, String matStuffName,
		String costPrice, String normalPrice, String empPrice, String vipPrice, String specialPrice, String takeprice,
		String retuRemark, String stock1, String stock2, String dateBrow, String dateRetu, String chgStatus, String chgRetu, String qtyBrwRetu,
		String qtyReture) { //24-07-2012
		super();
		this.docTypeCode = docTypeCode;
		this.docYear = docYear;
		this.docMonth = docMonth;
		this.docNo = docNo;
		this.wahoCode = wahoCode;
		this.matCode = matCode;
		this.matName = matName;
		this.refMatCode = refMatCode;
		this.lotNo = lotNo;
		this.locaCode = locaCode;
		this.locaName = locaName;
		this.borrowQty = borrowQty;
		this.retuAmount = retuAmount;
		this.retuAvalQty = retuAvalQty;
		this.borrowStatus = borrowStatus;
		this.borrowCancelStatus = borrowCancelStatus;
		this.borrowRemark = borrowRemark;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.updateDate = updateDate;
		this.updateByCode = updateByCode;
		this.updateByName = updateByName;
		this.amntQty = amntQty;
		this.brrwQty = brrwQty;
		this.alocQty = alocQty;
		this.avalQty = avalQty;
		this.itemNo = itemNo;
		this.rUnit = rUnit;
		this.refMatName = refMatName;
		this.rUnitName = rUnitName;
		this.retuQty = retuQty;
		this.retuPricePerUnit = retuPricePerUnit;
		this.matTypeCode 		= matTypeCode;
		this.matTypeName 		= matTypeName;
		this.matGrpCode			= matGrpCode;
		this.matGrpName 		= matGrpName;
		this.matBrandCode 		= matBrandCode;
		this.matBrandName 		= matBrandName;
		this.matColorCode 		= matColorCode;
		this.matColorName 		= matColorName;
		this.matStuffCode 		= matStuffCode;
		this.matStuffName 		= matStuffName;
		this.refMatCode			= refMatCode;
		this.retuRemark			= retuRemark;
		this.costPrice 			= costPrice;
		this.empPrice 			= empPrice;
		this.normalPrice 		= normalPrice;
		this.specialPrice 		= specialPrice;
		this.vipPrice 			= vipPrice;
		this.takeprice			= takeprice;
		this.stock1				= stock1;
		this.stock2				= stock2;
		this.dateBrow			= dateBrow;
		this.dateRetu			= dateRetu;
		this.chgStatus			= chgStatus;
		this.chgRetu			= chgRetu;
		this.qtyBrwRetu			= qtyBrwRetu;
		this.qtyReture			= qtyReture;
	}
	
	public void initial() {
		this.docTypeCode = "";
		this.docTypeName = "";
		this.docYear = "";
		this.docMonth = "";
		this.docNo = "";
		this.docDate = "";
		this.docStatus = "";
		this.docByCode = "";
		this.docByName = "";
		this.periodStatus = "";
		this.refOrdTypeCode = "";
		this.refOrdTypeName = "";
		this.refOrdYear = "";
		this.refOrdNo = "";
		this.vendCode = "";
		this.vendName = "";
		this.wahoCode = "";
		this.wahoName = "";
		this.matCode = "";
		this.matName = "";
		this.refMatCode = "";
		this.borrowQty = "";
		this.itemNo = "";
		this.wahoCode="";
		this.wahoName="";
		this.docMonth="";
		this.remark="";
  	}
	public void initialDT() {
		this.itemNo = "**";
		this.matCode = "";
		this.matName = "";
		this.refMatCode = "";
		this.refMatName = "";
		this.rUnit = "";
		this.rUnitName = "";
		this.retuQty = "";
		this.remark="";
		this.matBrandCode = "";
		this.matBrandName = "";
		this.matTypeCode = "";
		this.matTypeName = "";
		this.matGrpCode = "";
		this.matGrpName = "";
		this.matColorCode = "";
		this.matColorName = "";
		this.matStuffCode = "";
		this.matStuffName = "";	
		this.normalPrice = "";   	
		this.empPrice = "";			  
		this.costPrice = "";
		this.vipPrice = "";
		this.specialPrice = "";
		this.retuRemark= "";
		this.qtyBalance = "";
		this.qtyReturn = "";
		this.retuPricePerUnit = "";
		this.retuAmount = "";
		this.cal	= "";
		this.qtyBrwRetu = "";
		this.browRetu = "";
		this.stock1 = "";
		this.stock2 = "";
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
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	public String getDocByCode() {
		return docByCode;
	}
	public void setDocByCode(String docByCode) {
		this.docByCode = docByCode;
	}
	public String getDocByName() {
		return docByName;
	}
	public void setDocByName(String docByName) {
		this.docByName = docByName;
	}
	public String getRefOrdTypeCode() {
		return refOrdTypeCode;
	}
	public void setRefOrdTypeCode(String refOrdTypeCode) {
		this.refOrdTypeCode = refOrdTypeCode;
	}
	public String getRefOrdTypeName() {
		return refOrdTypeName;
	}
	public void setRefOrdTypeName(String refOrdTypeName) {
		this.refOrdTypeName = refOrdTypeName;
	}
	public String getRefOrdYear() {
		return refOrdYear;
	}
	public void setRefOrdYear(String refOrdYear) {
		this.refOrdYear = refOrdYear;
	}
	public String getRefOrdNo() {
		return refOrdNo;
	}
	public void setRefOrdNo(String refOrdNo) {
		this.refOrdNo = refOrdNo;
	}
	public String getVendCode() {
		return vendCode;
	}
	public void setVendCode(String vendCode) {
		this.vendCode = vendCode;
	}
	public String getVendName() {
		return vendName;
	}
	public void setVendName(String vendName) {
		this.vendName = vendName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getMatCode() {
		return matCode;
	}
	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
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
	public String getBorrowQty() {
		return borrowQty;
	}
	public void setBorrowQty(String borrowQty) {
		this.borrowQty = borrowQty;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateByCode() {
		return updateByCode;
	}
	public void setUpdateByCode(String updateByCode) {
		this.updateByCode = updateByCode;
	}
	public String getUpdateByName() {
		return updateByName;
	}
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}
	public String getAmntQty() {
		return amntQty;
	}
	public void setAmntQty(String amntQty) {
		this.amntQty = amntQty;
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
	public String getBorrowStatus() {
		return borrowStatus;
	}
	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}
	public String getRefMatCode() {
		return refMatCode;
	}
	public void setRefMatCode(String refMatCode) {
		this.refMatCode = refMatCode;
	}
	public String getBorrowRemark() {
		return borrowRemark;
	}
	public void setBorrowRemark(String borrowRemark) {
		this.borrowRemark = borrowRemark;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getBorrowCancelStatus() {
		return borrowCancelStatus;
	}
	public void setBorrowCancelStatus(String borrowCancelStatus) {
		this.borrowCancelStatus = borrowCancelStatus;
	}
	public String getRetuQty() {
		return retuQty;
	}
	public void setRetuQty(String retuQty) {
		this.retuQty = retuQty;
	}
	public String getRetuAvalQty() {
		return retuAvalQty;
	}
	public void setRetuAvalQty(String retuAvalQty) {
		this.retuAvalQty = retuAvalQty;
	}
	public String getRetuCode() {
		return retuCode;
	}
	public void setRetuCode(String retuCode) {
		this.retuCode = retuCode;
	}
	public String getRetuName() {
		return retuName;
	}
	public void setRetuName(String retuName) {
		this.retuName = retuName;
	}
	public String getPeriodStatus() {
		return periodStatus;
	}
	public void setPeriodStatus(String periodStatus) {
		this.periodStatus = periodStatus;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getRetuStatus() {
		return retuStatus;
	}
	public void setRetuStatus(String retuStatus) {
		this.retuStatus = retuStatus;
	}
	public String getRetuAmount() {
		return retuAmount;
	}
	public void setRetuAmount(String retuAmount) {
		this.retuAmount = retuAmount;
	}
	public String getrUnit() {
		return rUnit;
	}
	public void setrUnit(String rUnit) {
		this.rUnit = rUnit;
	}
	public String getrUnitName() {
		return rUnitName;
	}
	public void setrUnitName(String rUnitName) {
		this.rUnitName = rUnitName;
	}
	public String getRetuPricePerUnit() {
		return retuPricePerUnit;
	}
	public void setRetuPricePerUnit(String retuPricePerUnit) {
		this.retuPricePerUnit = retuPricePerUnit;
	}
	public String getQtyReturn() {
		return qtyReturn;
	}
	public void setQtyReturn(String qtyReturn) {
		this.qtyReturn = qtyReturn;
	}
	public String getMatTypeCode() {
		return matTypeCode;
	}
	public void setMatTypeCode(String matTypeCode) {
		this.matTypeCode = matTypeCode;
	}
	public String getTakeprice() {
		return takeprice;
	}
	public void setTakeprice(String takeprice) {
		this.takeprice = takeprice;
	}
	public String getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}
	public String getEmpPrice() {
		return empPrice;
	}
	public void setEmpPrice(String empPrice) {
		this.empPrice = empPrice;
	}
	public String getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	public String getVipPrice() {
		return vipPrice;
	}
	public void setVipPrice(String vipPrice) {
		this.vipPrice = vipPrice;
	}
	public String getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(String specialPrice) {
		this.specialPrice = specialPrice;
	}
	public String getStock1() {
		return stock1;
	}
	public void setStock1(String stock1) {
		this.stock1 = stock1;
	}
	public String getStock2() {
		return stock2;
	}
	public void setStock2(String stock2) {
		this.stock2 = stock2;
	}
	public String getMatCategories() {
		return matCategories;
	}
	public void setMatCategories(String matCategories) {
		this.matCategories = matCategories;
	}
	public String getMatBrandCode() {
		return matBrandCode;
	}
	public void setMatBrandCode(String matBrandCode) {
		this.matBrandCode = matBrandCode;
	}
	public String getMatColorCode() {
		return matColorCode;
	}
	public void setMatColorCode(String matColorCode) {
		this.matColorCode = matColorCode;
	}
	public String getMatStuffCode() {
		return matStuffCode;
	}
	public void setMatStuffCode(String matStuffCode) {
		this.matStuffCode = matStuffCode;
	}
	public String getReferrequertno() {
		return referrequertno;
	}
	public void setReferrequertno(String referrequertno) {
		this.referrequertno = referrequertno;
	}
	public String getEmplCode() {
		return emplCode;
	}
	public void setEmplCode(String emplCode) {
		this.emplCode = emplCode;
	}
	public String getEmplName() {
		return emplName;
	}
	public void setEmplName(String emplName) {
		this.emplName = emplName;
	}
	public String getMatBrandName() {
		return matBrandName;
	}
	public void setMatBrandName(String matBrandName) {
		this.matBrandName = matBrandName;
	}
	public String getMatTypeName() {
		return matTypeName;
	}
	public void setMatTypeName(String matTypeName) {
		this.matTypeName = matTypeName;
	}
	public String getMatColorName() {
		return matColorName;
	}
	public void setMatColorName(String matColorName) {
		this.matColorName = matColorName;
	}
	public String getMatStuffName() {
		return matStuffName;
	}
	public void setMatStuffName(String matStuffName) {
		this.matStuffName = matStuffName;
	}
	public String getRefMatName() {
		return refMatName;
	}
	public void setRefMatName(String refMatName) {
		this.refMatName = refMatName;
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
	public String getRetuRemark() {
		return retuRemark;
	}
	public void setRetuRemark(String retuRemark) {
		this.retuRemark = retuRemark;
	}
	public String getQtyBalance() {
		return qtyBalance;
	}
	public void setQtyBalance(String qtyBalance) {
		this.qtyBalance = qtyBalance;
	}
	public String getCal() {
		return cal;
	}
	public void setCal(String cal) {
		this.cal = cal;
	}
	public String getDateBrow() {
		return dateBrow;
	}
	public void setDateBrow(String dateBrow) {
		this.dateBrow = dateBrow;
	}
	public String getDateRetu() {
		return dateRetu;
	}
	public void setDateRetu(String dateRetu) {
		this.dateRetu = dateRetu;
	}
	public String getBrowRetu() {
		return browRetu;
	}
	public void setBrowRetu(String browRetu) {
		this.browRetu = browRetu;
	}
	public String getChgStatus() {
		return chgStatus;
	}
	public void setChgStatus(String chgStatus) {
		this.chgStatus = chgStatus;
	}
	public String getChgRetu() {
		return chgRetu;
	}
	public void setChgRetu(String chgRetu) {
		this.chgRetu = chgRetu;
	}
	public String getQtyBrwRetu() {
		return qtyBrwRetu;
	}
	public void setQtyBrwRetu(String qtyBrwRetu) {
		this.qtyBrwRetu = qtyBrwRetu;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getLockMatTypeCode() {
		return lockMatTypeCode;
	}
	public void setLockMatTypeCode(String lockMatTypeCode) {
		this.lockMatTypeCode = lockMatTypeCode;
	}
	public String getLockMatGrpCode() {
		return lockMatGrpCode;
	}
	public void setLockMatGrpCode(String lockMatGrpCode) {
		this.lockMatGrpCode = lockMatGrpCode;
	}
	public String getLotDate() {
		return lotDate;
	}
	public void setLotDate(String lotDate) {
		this.lotDate = lotDate;
	}
	public String getQtyReture() {
		return qtyReture;
	}
	public void setQtyReture(String qtyReture) {
		this.qtyReture = qtyReture;
	}
}