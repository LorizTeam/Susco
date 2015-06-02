package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;


public class changeItemForm extends ActionForm {

	private String matCode;
	private String matName;
	private String matSearchName;
	private String pUnit;			//unit
	private String pUnitName;
	private String rUnit;
	private String rUnitName;
	private String iUnit;
	private String iUnitName;
	
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
	private String refMatCode;
	private String matStatus;
	private String matRemark;
	private String reqOrdFlag;
	private String pic1;
	private String pic2;
	
	private String sellPrice;
	private String costPrice;
	private String qtyMinStock;
	private String updateDate;
	private String updateByCode;
	private String updateByName;
	
	private String newMatCode;
	private String formName;
	private String reportNo;
	
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
	public String getNewMatCode() {
		return newMatCode;
	}
	public void setNewMatCode(String newMatCode) {
		this.newMatCode = newMatCode;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
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
	public String getRefMatCode() {
		return refMatCode;
	}
	public void setRefMatCode(String refMatCode) {
		this.refMatCode = refMatCode;
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
	private String matTypeCode;
	private String matTypeName;
	private String matStockType;
	
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
	public String getpUnitName() {
		return pUnitName;
	}
	public void setpUnitName(String pUnitName) {
		this.pUnitName = pUnitName;
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
	public String getiUnit() {
		return iUnit;
	}
	public void setiUnit(String iUnit) {
		this.iUnit = iUnit;
	}
	public String getiUnitName() {
		return iUnitName;
	}
	public void setiUnitName(String iUnitName) {
		this.iUnitName = iUnitName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private static final long serialVersionUID = 1L;
	private String txtcode;
	private String txtname;
	private String txtstock;
	private String txtaccount;
	private String collectcode;
	private String collectname;
	private String collectstock;
	private String collectaccount;
	 
	
	public changeItemForm(){};
	public changeItemForm(String txtcode, String txtname, String txtstock, String txtaccount) { 
	super();
	this.txtcode = txtcode;
	this.txtname = txtname;
	this.txtstock = txtstock;
	this.txtaccount = txtaccount;
	}
	
	public void initial() {
		this.txtcode = "";
		this.txtname = "";
		this.txtstock = "";
		this.txtaccount = "";
		this.collectcode = "";
		
	}
	
	public String getTxtcode() {
		return txtcode;
	}
	public void setTxtcode(String txtcode) {
		this.txtcode = txtcode;
	}
	public String getTxtname() {
		return txtname;
	}
	public void setTxtname(String txtname) {
		this.txtname = txtname;
	}
	public String getTxtstock() {
		return txtstock;
	}
	public void setTxtstock(String txtstock) {
		this.txtstock = txtstock;
	}
	public String getTxtaccount() {
		return txtaccount;
	}
	public void setTxtaccount(String txtaccount) {
		this.txtaccount = txtaccount;
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
	public String getCollectstock() {
		return collectstock;
	}
	public void setCollectstock(String collectstock) {
		this.collectstock = collectstock;
	}
	public String getCollectaccount() {
		return collectaccount;
	}
	public void setCollectaccount(String collectaccount) {
		this.collectaccount = collectaccount;
	}
	
}
