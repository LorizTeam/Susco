package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;

public class MaterialForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String status; 
	private String date; 
	private String docdate; 
	private String matCode;
	private String mark;
	private String serial;
	private String matSendName;
	private String matName;
	private String dtmatName;
	private String matSearchName;
	private String pUnit;			//unit
	private String pUnitName;
	private String rUnit;
	private String rUnitName;
	private String iUnit;
	private String prmfrdate;
	private String prmtodate;
	private String docno;
	private	String year;
	
	private String checkbkk; 
	private String vendCode;
	private String vendName;
	private String iUnitName;
	
	private String matTypeCode;
	private String matTypeName;
	private String matStockType;
	
	
	private String matGrpCode;
	private String matGrpName;
	private String matBrandCode;	//bran
	private String matBrandName;
	private String matSupplCode;	//supp
	private String matSupplName;
	private String matColorCode;	//colo
	private String matColorName;
	private String matStuffCode;	//made of
	private String matStuffName;
	private String refMatCode;     //size
	private String refMatName;
	private String matStatus;
	private String matRemark;
	private String reqOrdFlag;
	private String pic1;
	private String pic2;
	private String impEx;
	
	// lock docTypeCode
	private String docTypeCode;

	private String sellPrice;
	private String costPrice;
	private String qtyMinStock;
	private String qtyMaxStock;
	private String rop;
	private String updateDate;
	private String updateByCode;
	private String updateByName;
	
	private String newMatCode;
	private String formName;
	private String reportNo;
	
	private String normalPrice;
	private String empPrice;
	private String vipPrice;
	private String specialPrice;
	private String warehouseAuth;
	private String lastprdate;
	private String salecde;
	private String salenme;
	private String stock1;
	private String stock2;
	private String stock3;
	private String stock4; 
	private String text_1;
	private String text_2;
	private String text_3;
	private String text_4;
	private String text_5;
	private String text_6;
	private String text_7;
	private String text_8;
	private String text_9;
	private String text_10;
	private String text_11;
	private String text_12;
	private String text_13;
	private String text_14;
	private String text_15;
	private String text_16;
	private String text_17;
	private String text_18;
	private String text_19;
	private String text_20;
	private String text_21;
 
	
	
	
	
	public String getPrmfrdate() {
		return prmfrdate;
	}
	public void setPrmfrdate(String prmfrdate) {
		this.prmfrdate = prmfrdate;
	}
	public String getPrmtodate() {
		return prmtodate;
	}
	public void setPrmtodate(String prmtodate) {
		this.prmtodate = prmtodate;
	}
	public String getStock3() {
		return stock3;
	}
	public String getWarehouseAuth() {
		return warehouseAuth;
	}
	public void setWarehouseAuth(String warehouseAuth) {
		this.warehouseAuth = warehouseAuth;
	}
	public void setStock3(String stock3) {
		this.stock3 = stock3;
	}
	public String getStock4() {
		return stock4;
	}
	public void setStock4(String stock4) {
		this.stock4 = stock4;
	}
	private String textBoxId;
	
	public String getTextBoxId() {
		return textBoxId;
	}
	public void setTextBoxId(String textBoxId) {
		this.textBoxId = textBoxId;
	}
	public MaterialForm(){};	
	public MaterialForm(String matCode,String serial, String matName, String matSendName, String matSearchName,
		String pUnit, String pUnitName, String rUnit, String rUnitName, String iUnit, String iUnitName, 
		String matTypeCode, String matTypeName, String matStockType, String matGrpCode, String matGrpName, 
		String matBrandCode, String matBrandName, String matSupplCode, String matSupplName,
		String matColorCode, String matColorName, String matStuffCode, String matStuffName, String refMatCode, String refMatName,
		String matStatus, String matRemark, String sellPrice, String costPrice, String qtyMaxStock,
		String qtyMinStock, String updateDate, String updateByCode, String updateByName,
		String reqOrdFlag, String pic1, String pic2, String normalPrice, String empPrice, 
		String vipPrice, String specialPrice,String lastprdate,String salecde,String salenme, String stock1, String stock2,String stock3, String stock4, String rop) { //03-05-2012
		super();
		this.matCode 			= matCode;
		this.serial 			= serial;
		this.matName 			= matName;
		this.matSendName		= matSendName;
		this.matSearchName 		= matSearchName;
		this.pUnit 				= pUnit;
		this.rUnit 				= rUnit;
		this.iUnit 				= iUnit;
		this.pUnitName 			= pUnitName;
		this.rUnitName 			= rUnitName;
		this.iUnitName			= iUnitName;
		this.matTypeCode 		= matTypeCode;
		this.matTypeName 		= matTypeName;
		this.matStockType 		= matStockType;
		this.matGrpCode			= matGrpCode;
		this.matGrpName 		= matGrpName;
		this.matBrandCode 		= matBrandCode;
		this.matBrandName 		= matBrandName;
		this.matSupplCode 		= matSupplCode;
		this.matSupplName 		= matSupplName;
		this.matColorCode 		= matColorCode;
		this.matColorName 		= matColorName;
		this.matStuffCode 		= matStuffCode;
		this.matStuffName 		= matStuffName;
		this.refMatCode			= refMatCode;
		this.refMatName			= refMatName;
		this.matStatus 			= matStatus;
		this.matRemark 			= matRemark;
		this.sellPrice 			= sellPrice;  
		this.costPrice 			= costPrice;
		this.qtyMaxStock		= qtyMaxStock;
		this.qtyMinStock 		= qtyMinStock;  
		this.updateDate 		= updateDate;  
		this.updateByCode 		= updateByCode;  
		this.updateByName 		= updateByName;
		this.reqOrdFlag 		= reqOrdFlag;
		this.pic1				= pic1;
		this.pic2				= pic2;
		this.lastprdate         = lastprdate;
		this.salecde            = salecde;
		this.salenme            =  salenme;
		this.stock1				= stock1;
		this.stock2				= stock2;
		this.stock3				= stock3;
		this.stock4				= stock4;
		this.vipPrice  			= vipPrice;
		this.empPrice   		= empPrice;
		this.normalPrice		= normalPrice;
		this.specialPrice		= specialPrice;
		this.rop				= rop;
	}
	public MaterialForm(String matCode,String serial, String matName, String matSearchName, String pUnit, String pUnitName, 
		String matTypeCode, String matTypeName, String matGrpCode, String matGrpName, String matStatus,
		String updateDate, String updateByCode, String updateByName,
		String reqOrdFlag, String pic1, String pic2, String normalPrice, String empPrice, 
		String vipPrice, String specialPrice, String stock1, String stock2,String stock3, String stock4) { //31-05-2012
		super();
		this.matCode = matCode;
		this.serial = serial;
		this.matName = matName;
		this.matSearchName = matSearchName;
		this.pUnit = pUnit;
		this.pUnitName = pUnitName;
		this.matTypeCode = matTypeCode;
		this.matTypeName = matTypeName;
		this.matGrpCode = matGrpCode;
		this.matGrpName = matGrpName;
		this.matStatus = matStatus;
		this.updateDate = updateDate;  
		this.updateByCode = updateByCode;  
		this.updateByName = updateByName;
		this.reqOrdFlag = reqOrdFlag;
		this.pic1		= pic1;
		this.pic2		= pic2;
		this.stock1				= stock1;
		this.stock2				= stock2;
		this.stock3				= stock3;
		this.stock4				= stock4;
		this.vipPrice  			= vipPrice;
		this.empPrice   		= empPrice;
		this.normalPrice		= normalPrice;
		this.specialPrice		= specialPrice;
	}
	public void initial() {
		this.matCode= "";
		this.serial="";
		this.matName= "";
		this.matSearchName = "";
		this.pUnit	= "";
		this.rUnit	= "";
		this.iUnit	= "";
		this.pUnitName = "";
		this.rUnitName = "";
		this.iUnitName = "";
  		this.matTypeCode= "";
  		this.matTypeName= "";
  		this.matStockType = "";
  		this.matGrpCode	= "";
  		this.matGrpName	= "";
  		this.matBrandCode = "";
  		this.matColorCode = "";
  		this.matStuffCode = "";
  		this.refMatCode = "";
  		this.matStatus	= "";
  		this.matRemark	= "";
  		this.sellPrice = "";  
		this.costPrice = "";
		this.qtyMinStock = ""; 
  	}
 
	public String getDocdate() {
		return docdate;
	}
	public void setDocdate(String docdate) {
		this.docdate = docdate;
	}
	public String getText_2() {
		return text_2;
	}
	public void setText_2(String text_2) {
		this.text_2 = text_2;
	}
	public String getText_3() {
		return text_3;
	}
	public void setText_3(String text_3) {
		this.text_3 = text_3;
	}
	public String getText_4() {
		return text_4;
	}
	public void setText_4(String text_4) {
		this.text_4 = text_4;
	}
	public String getText_5() {
		return text_5;
	}
	public void setText_5(String text_5) {
		this.text_5 = text_5;
	}
	public String getText_6() {
		return text_6;
	}
	public void setText_6(String text_6) {
		this.text_6 = text_6;
	}
	public String getText_7() {
		return text_7;
	}
	public void setText_7(String text_7) {
		this.text_7 = text_7;
	}
	public String getText_8() {
		return text_8;
	}
	public void setText_8(String text_8) {
		this.text_8 = text_8;
	}
	public String getText_9() {
		return text_9;
	}
	public void setText_9(String text_9) {
		this.text_9 = text_9;
	}
	public String getText_10() {
		return text_10;
	}
	public void setText_10(String text_10) {
		this.text_10 = text_10;
	}
	public String getText_11() {
		return text_11;
	}
	public void setText_11(String text_11) {
		this.text_11 = text_11;
	}
	public String getText_12() {
		return text_12;
	}
	public void setText_12(String text_12) {
		this.text_12 = text_12;
	}
	public String getText_13() {
		return text_13;
	}
	public void setText_13(String text_13) {
		this.text_13 = text_13;
	}
	public String getText_14() {
		return text_14;
	}
	public void setText_14(String text_14) {
		this.text_14 = text_14;
	}
	public String getText_15() {
		return text_15;
	}
	public void setText_15(String text_15) {
		this.text_15 = text_15;
	}
	public String getText_16() {
		return text_16;
	}
	public void setText_16(String text_16) {
		this.text_16 = text_16;
	}
	public String getText_17() {
		return text_17;
	}
	public void setText_17(String text_17) {
		this.text_17 = text_17;
	}
	public String getText_18() {
		return text_18;
	}
	public void setText_18(String text_18) {
		this.text_18 = text_18;
	}
	public String getText_19() {
		return text_19;
	}
	public void setText_19(String text_19) {
		this.text_19 = text_19;
	}
	public String getText_20() {
		return text_20;
	}
	public void setText_20(String text_20) {
		this.text_20 = text_20;
	}
	public String getText_21() {
		return text_21;
	}
	public void setText_21(String text_21) {
		this.text_21 = text_21;
	}
	public String getText_1() {
		return text_1;
	}
	public void setText_1(String text_1) {
		this.text_1 = text_1;
	}
	public String getDtmatName() {
		return dtmatName;
	}
	public void setDtmatName(String dtmatName) {
		this.dtmatName = dtmatName;
	}
	public String getCheckbkk() {
		return checkbkk;
	}
	public void setCheckbkk(String checkbkk) {
		this.checkbkk = checkbkk;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLastprdate() {
		return lastprdate;
	}
	public void setLastprdate(String lastprdate) {
		this.lastprdate = lastprdate;
	}
	public String getSalecde() {
		return salecde;
	}
	public void setSalecde(String salecde) {
		this.salecde = salecde;
	}
	public String getSalenme() {
		return salenme;
	}
	public void setSalenme(String salenme) {
		this.salenme = salenme;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getDocno() {
		return docno;
	}
	public void setDocno(String docno) {
		this.docno = docno;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	//addnew price of vip normal and employee 28-03-13
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
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
	public String getVipPrice() {
		return vipPrice;
	}
	public void setVipPrice(String vipPrice) {
		this.vipPrice = vipPrice;
	}
	
	//end add
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
	public String getMatStockType() {
		return matStockType;
	}
	public void setMatStockType(String matStockType) {
		this.matStockType = matStockType;
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
	public String getMatCode() {
		return matCode;
	}
	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
	public String getMatSendName() {
		return matSendName;
	}
	public void setMatSendName(String matSendName) {
		this.matSendName = matSendName;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getMatSearchName() {
		return matSearchName;
	}
	public void setMatSearchName(String matSearchName) {
		this.matSearchName = matSearchName;
	}
	public String getpUnit() {
		return pUnit;
	}
	public void setpUnit(String pUnit) {
		this.pUnit = pUnit;
	}
	public String getrUnit() {
		return rUnit;
	}
	public void setrUnit(String rUnit) {
		this.rUnit = rUnit;
	}
	public String getiUnit() {
		return iUnit;
	}
	public void setiUnit(String iUnit) {
		this.iUnit = iUnit;
	}
	public String getMatStatus() {
		return matStatus;
	}
	public void setMatStatus(String matStatus) {
		this.matStatus = matStatus;
	}
	public String getMatRemark() {
		return matRemark;
	}
	public void setMatRemark(String matRemark) {
		this.matRemark = matRemark;
	}
	public String getpUnitName() {
		return pUnitName;
	}
	public void setpUnitName(String pUnitName) {
		this.pUnitName = pUnitName;
	}
	public String getrUnitName() {
		return rUnitName;
	}
	public void setrUnitName(String rUnitName) {
		this.rUnitName = rUnitName;
	}
	public String getiUnitName() {
		return iUnitName;
	}
	public void setiUnitName(String iUnitName) {
		this.iUnitName = iUnitName;
	}
	public String getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	public String getQtyMinStock() {
		return qtyMinStock;
	}
	public void setQtyMinStock(String qtyMinStock) {
		this.qtyMinStock = qtyMinStock;
	}
	public String getQtyMaxStock() {
		return qtyMaxStock;
	}
	public void setQtyMaxStock(String qtyMaxStock) {
		this.qtyMaxStock = qtyMaxStock;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public String getRop() {
		return rop;
	}
	public void setRop(String rop) {
		this.rop = rop;
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
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getMatBrandCode() {
		return matBrandCode;
	}
	public void setMatBrandCode(String matBrandCode) {
		this.matBrandCode = matBrandCode;
	}
	public String getMatBrandName() {
		return matBrandName;
	}
	public void setMatBrandName(String matBrandName) {
		this.matBrandName = matBrandName;
	}
	public String getMatSupplCode() {
		return matSupplCode;
	}
	public void setMatSupplCode(String matSupplCode) {
		this.matSupplCode = matSupplCode;
	}
	public String getMatSupplName() {
		return matSupplName;
	}
	public void setMatSupplName(String matSupplName) {
		this.matSupplName = matSupplName;
	}
	public String getMatColorCode() {
		return matColorCode;
	}
	public void setMatColorCode(String matColorCode) {
		this.matColorCode = matColorCode;
	}
	public String getMatColorName() {
		return matColorName;
	}
	public void setMatColorName(String matColorName) {
		this.matColorName = matColorName;
	}
	public String getRefMatCode() {
		return refMatCode;
	}
	public void setRefMatCode(String refMatCode) {
		this.refMatCode = refMatCode;
	}
	public String getReqOrdFlag() {
		return reqOrdFlag;
	}
	public void setReqOrdFlag(String reqOrdFlag) {
		this.reqOrdFlag = reqOrdFlag;
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
	public String getMatStuffCode() {
		return matStuffCode;
	}
	public void setMatStuffCode(String matStuffCode) {
		this.matStuffCode = matStuffCode;
	}
	public String getMatStuffName() {
		return matStuffName;
	}
	public void setMatStuffName(String matStuffName) {
		this.matStuffName = matStuffName;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getNewMatCode() {
		return newMatCode;
	}
	public void setNewMatCode(String newMatCode) {
		this.newMatCode = newMatCode;
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
	public String getImpEx() {
		return impEx;
	}
	public void setImpEx(String impEx) {
		this.impEx = impEx;
	}
	public String getRefMatName() {
		return refMatName;
	}
	public void setRefMatName(String refMatName) {
		this.refMatName = refMatName;
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
}