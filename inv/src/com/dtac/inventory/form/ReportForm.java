package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;


public class ReportForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String docTypeCode;
	private String wahoCode;
	private String fromDate;
	private String toDate;
	private String reportNo;
	private String fromDocNo;
	private String toDocNo;
	private String stockStatus;
	private String qtyPrice;
	private String empNo;
	private String empName;
	private String matTypeCode;
	private String matCode;
	private String matGrpCode;
	private String matStuffCode;
	private String dgroup;
	private String division;
	private String takeDepart; 
	private String txtUnit;
	private String docDate;
	private String minMax;
	private String stockOnHand;
	private String colStatus;
	private String sortAging;
	
	public ReportForm(){};
	public ReportForm(String docTypeCode) { 
	super();

	}
	
	public void initial() {
		this.wahoCode = "";
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public String getWahoCode() {
		return wahoCode;
	}
	public void setWahoCode(String wahoCode) {
		this.wahoCode = wahoCode;
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
	public String getFromDocNo() {
		return fromDocNo;
	}
	public void setFromDocNo(String fromDocNo) {
		this.fromDocNo = fromDocNo;
	}
	public String getToDocNo() {
		return toDocNo;
	}
	public void setToDocNo(String toDocNo) {
		this.toDocNo = toDocNo;
	}
	public String getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getQtyPrice() {
		return qtyPrice;
	}
	public void setQtyPrice(String qtyPrice) {
		this.qtyPrice = qtyPrice;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getMatTypeCode() {
		return matTypeCode;
	}
	public void setMatTypeCode(String matTypeCode) {
		this.matTypeCode = matTypeCode;
	}
	public String getMatCode() {
		return matCode;
	}
	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
	public String getMatGrpCode() {
		return matGrpCode;
	}
	public void setMatGrpCode(String matGrpCode) {
		this.matGrpCode = matGrpCode;
	}
	public String getMatStuffCode() {
		return matStuffCode;
	}
	public void setMatStuffCode(String matStuffCode) {
		this.matStuffCode = matStuffCode;
	}
	public String getDgroup() {
		return dgroup;
	}
	public void setDgroup(String dgroup) {
		this.dgroup = dgroup;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getTakeDepart() {
		return takeDepart;
	}
	public void setTakeDepart(String takeDepart) {
		this.takeDepart = takeDepart;
	}
	public String getTxtUnit() {
		return txtUnit;
	}
	public void setTxtUnit(String txtUnit) {
		this.txtUnit = txtUnit;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	public String getMinMax() {
		return minMax;
	}
	public void setMinMax(String minMax) {
		this.minMax = minMax;
	}
	public String getStockOnHand() {
		return stockOnHand;
	}
	public void setStockOnHand(String stockOnHand) {
		this.stockOnHand = stockOnHand;
	}
	public String getColStatus() {
		return colStatus;
	}
	public void setColStatus(String colStatus) {
		this.colStatus = colStatus;
	}
	public String getSortAging() {
		return sortAging;
	}
	public void setSortAging(String sortAging) {
		this.sortAging = sortAging;
	}
	
	
	
}
