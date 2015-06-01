package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;

public class MaterialTakeForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	//header Take
	private String alertMessage;
	private String mark;
	private String serial;
	private String serialchg;
	private String checkbkk; 
	private String oldmonth;
	private String rtnqty;
	private String oldwhocode;
	 
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getCheckbkk() {
		return checkbkk;
	}
	public void setCheckbkk(String checkbkk) {
		this.checkbkk = checkbkk;
	}
	private String status;
	private String requesterno;
	private String requestername;
	private String acountcode;
	private String projectcode;
	private String date;
	private String lotDate;
	private String takedepart;
	private String mobileno;
	private String rccode;
	private String remark;
	private String stockStatus;
	private String txtunit;
	private String division;
	private String dgroup;
	private String docMonth;
 
	//header Change
	private String referrequertno;
	//header Claim
	private String docbycode;
	private String docbyname;
	private String formName;
	private String locaCode;
	private String wahoCode;
	private String wahoName;
	private String takeitemno;
	
	// confirm detail
	private String confirm;
	//detail
	private int recno;
	private int hdrecno;
	private String docTypeCode;
	private String docYear;
	private String docNo;
	private String itemNo;
	private String matCode;
	private String matName;
	private String matCodechg;
	private String matNamechg;
	private String matTypeCode;
	private String takecategories;
	private String refMatCode;
	private String takedescription;
	private String takeunit;
	private String takequantity;
	private String quantitychg;
	private String takeprice;
	private String takeamount;
	private String amountchg;
	private String taketotalamount;
	private String matCategoriecode;
	private String matCategoriename;
	private String matStuffCode;
	private String matColorCode;
	private String matBrandCode;
	private String vendCode;
	private String vendName;
	private String custCode;
	private String custName;
	private String refOrdTypeCode;
	private String refOrdYear;
	private String refOrdNo;
	
	//hidden text
	private String matBrandName;
	private String matTypeName;
	private String matGrpName;
	private String matColorName;
	private String rUnitName;
	private String matStuffName;
	private String refMatName;

	
	//detail claim and brow
	private String takereason;
	private String dateBrow;
	private String dateRetu;
	//12/06/2013
	private String normalPrice;
	private String empPrice;
	private String costPrice;
	private String vipPrice;
	private String specialPrice;
	private String pricesale;
	private String stock1;
	private String stock2;
	private String stock3;
	private String stock4;
	
	private String textBoxId;
	
	public String getStock3() {
		return stock3;
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
	private String lotno;
	//return

	public MaterialTakeForm(){};
	
	public MaterialTakeForm(String matCategoriecode, String matCategoriename) { //16-05-2013
		super();
		this.matCategoriecode = matCategoriecode;
		this.matCategoriename = matCategoriename;
	}
	public MaterialTakeForm(String alertMessage) { //16-05-2013
		super();
		this.alertMessage = alertMessage;
	 
	}
	public MaterialTakeForm(String status,String docTypeCode, String docYear, String docMonth, String docNo, String date, String lotDate,
			String docStatus, String docbycode, String docbyname, String takedepart, String txtunit, String division, String dgroup, String stockStatus, 
			String acountcode, String projectcode, String mobileno, String rccode, 
			String refOrdTypeCode, String refOrdTypeName, String refOrdYear, String refOrdNo,
			String wahoCode, String wahoName, String vendCode, String vendName, String remark, String periodStatus) {
		super();
		this.status = status;
		this.docTypeCode = docTypeCode;
		this.docYear = docYear;
		this.docMonth = docMonth;
		this.docNo = docNo;
		this.date = date;
		this.lotDate = lotDate;
		this.docbycode = docbycode;
		this.docbyname = docbyname;
		this.refOrdTypeCode = refOrdTypeCode;
		this.refOrdYear = refOrdYear;
		this.refOrdNo = refOrdNo;
		this.acountcode = acountcode;
		this.projectcode = projectcode;
		this.mobileno = mobileno;
		this.rccode = rccode;
		this.takedepart = takedepart;
		this.txtunit = txtunit;
		this.division = division;
		this.dgroup = dgroup;
		this.stockStatus = stockStatus;
		this.wahoCode = wahoCode;
		this.wahoName = wahoName;
		this.vendCode = vendCode;
		this.vendName = vendName;
		this.remark = remark;
	}
	public MaterialTakeForm(String docTypeCode, String docYear, String docMonth, String docNo, 
			String wahoCode, String matCode, String matName, String refMatCode, 
			String lotNo, String locaCode, String locaName, String borrowQty, String takequantity, String retuAvalQty,
			String borrowStatus, String borrowCancelStatus, String borrowRemark, String borrowDate, String returnDate,
			String updateDate, String updateByCode, String updateByName, 
			String amntQty, String brrwQty, String alocQty, String avalQty,
			String itemNo, String rUnit, String rUnitName, String taketotalamount, String takeamount, 
			String matTypeCode, String matTypeName, String matBrandCode, String matBrandName, String refMatName,
			String matGrpCode, String matGrpName, String matColorCode, String matColorName, String matStuffCode, String matStuffName,
			String costPrice, String normalPrice, String empPrice, String vipPrice, String specialPrice, String takeprice,
			String retuRemark, String stock1, String stock2, String dateBrow, String dateRetu, String chgStatus, String chgRetu) { //24-07-2012
			super();
			this.docTypeCode = docTypeCode;
			this.docYear = docYear;
			this.docMonth = docMonth;
			this.docNo = docNo;
			this.wahoCode = wahoCode;
			this.matCode = matCode;
			this.matName = matName;
			this.refMatCode = refMatCode;
			this.lotno = lotNo;
			this.locaCode = locaCode;
		//	this.locaName = locaName;
		//	this.borrowQty = borrowQty;
		//	this.retuAmount = retuAmount;
		//	this.retuAvalQty = retuAvalQty;
		//	this.borrowStatus = borrowStatus;
		//	this.borrowCancelStatus = borrowCancelStatus;
		//	this.borrowRemark = borrowRemark;
		//	this.borrowDate = borrowDate;
		//	this.returnDate = returnDate;
		//	this.updateDate = updateDate;
		//	this.updateByCode = updateByCode;
		//	this.updateByName = updateByName;
		//	this.amntQty = amntQty;
		//	this.brrwQty = brrwQty;
		//	this.alocQty = alocQty;
		//	this.avalQty = avalQty;
			this.itemNo = itemNo;
		//	this.rUnit = rUnit;
			this.refMatName = refMatName;
			this.rUnitName = rUnitName;
			this.takequantity = takequantity;
			this.takeamount = takeamount;
			this.taketotalamount = taketotalamount;
			this.matTypeCode 		= matTypeCode;
			this.matTypeName 		= matTypeName;
		//	this.matGrpCode			= matGrpCode;
			this.matGrpName 		= matGrpName;
			this.matBrandCode 		= matBrandCode;
			this.matBrandName 		= matBrandName;
			this.matColorCode 		= matColorCode;
			this.matColorName 		= matColorName;
			this.matStuffCode 		= matStuffCode;
			this.matStuffName 		= matStuffName;
			this.refMatCode			= refMatCode;
		//	this.retuRemark			= retuRemark;
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
		//	this.chgStatus			= chgStatus;
		//	this.chgRetu			= chgRetu;
		}
	public MaterialTakeForm(String docTypeCode, String docYear, String docMonth, String docNo, 
			String wahoCode, String matCode, String matName,String matCodechg, String matNamechg,String custCode, String custName,String pricesale) { //24-07-2012
			super();
			this.docTypeCode = docTypeCode;
			this.docYear = docYear;
			this.docMonth = docMonth;
			this.docNo = docNo;
			this.wahoCode = wahoCode;
			this.matCode = matCode;
			this.matName = matName;
			this.matCodechg = matCodechg;
			this.matNamechg = matNamechg;
			this.custCode = custCode;
			this.custName = custName;
			this.pricesale = pricesale;
 
		}
	
	
	public void initial() {	
		//header
		  requesterno ="";
		  requestername="";
		  acountcode="";
		  projectcode="";
		  date ="";
		  takedepart="";
		  mobileno="";
		  rccode="";
		  remark="";
		//detail
		recno =0;
		 hdrecno=0;
		  itemNo="";
		  matCode="";
		  matTypeCode="";
		  matTypeName="";
		  takecategories="";
		  refMatCode="";
		  takedescription="";
		  takeunit="";
		  takequantity="";
		  takeprice="";
		  takeamount="";
		  taketotalamount="";
		  takereason="";
		matName="";
		refMatName="";
		rUnitName="";
		costPrice="";
		empPrice="";
		normalPrice="";
		specialPrice="";
		vipPrice="";
		takeprice="";
		stock1="";
		stock2="";
		matGrpName="";
		matBrandCode="";
		matBrandName="";
		matColorCode="";
		matColorName="";
		matStuffCode="";
		matStuffName="";
		refMatCode="";
		dateRetu="";
  	}
	
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAmountchg() {
		return amountchg;
	}
	public void setAmountchg(String amountchg) {
		this.amountchg = amountchg;
	}
	public String getMatCodechg() {
		return matCodechg;
	}
	public void setMatCodechg(String matCodechg) {
		this.matCodechg = matCodechg;
	}
	public String getMatNamechg() {
		return matNamechg;
	}
	public void setMatNamechg(String matNamechg) {
		this.matNamechg = matNamechg;
	}
	public String getQuantitychg() {
		return quantitychg;
	}
	public void setQuantitychg(String quantitychg) {
		this.quantitychg = quantitychg;
	}
	public String getSerialchg() {
		return serialchg;
	}
	public void setSerialchg(String serialchg) {
		this.serialchg = serialchg;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getRtnqty() {
		return rtnqty;
	}
	public void setRtnqty(String rtnqty) {
		this.rtnqty = rtnqty;
	}
	public String getOldmonth() {
		return oldmonth;
	}
	public void setOldmonth(String oldmonth) {
		this.oldmonth = oldmonth;
	}
	public String getOldwhocode() {
		return oldwhocode;
	}
	public void setOldwhocode(String oldwhocode) {
		this.oldwhocode = oldwhocode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPricesale() {
		return pricesale;
	}
	public void setPricesale(String pricesale) {
		this.pricesale = pricesale;
	}
	public String getReferrequertno() {
		return referrequertno;
	}


	public void setReferrequertno(String referrequertno) {
		this.referrequertno = referrequertno;
	}

	public String getRequesterno() {
		return requesterno;
	}


	public void setRequesterno(String requesterno) {
		this.requesterno = requesterno;
	}


	public String getRequestername() {
		return requestername;
	}


	public void setRequestername(String requestername) {
		this.requestername = requestername;
	}


	public String getAcountcode() {
		return acountcode;
	}


	public void setAcountcode(String acountcode) {
		this.acountcode = acountcode;
	}


	public String getProjectcode() {
		return projectcode;
	}


	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTakedepart() {
		return takedepart;
	}


	public void setTakedepart(String takedepart) {
		this.takedepart = takedepart;
	}


	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}


	public String getRccode() {
		return rccode;
	}


	public void setRccode(String rccode) {
		this.rccode = rccode;
	}


	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDocbycode() {
		return docbycode;
	}

	public void setDocbycode(String docbycode) {
		this.docbycode = docbycode;
	}

	public String getDocbyname() {
		return docbyname;
	}

	public void setDocbyname(String docbyname) {
		this.docbyname = docbyname;
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
	public int getRecno() {
		return recno;
	}

	public void setRecno(int recno) {
		this.recno = recno;
	}


	public String getMatTypeCode() {
		return matTypeCode;
	}


	public void setMatTypeCode(String matTypeCode) {
		this.matTypeCode = matTypeCode;
	}


	public String getTakecategories() {
		return takecategories;
	}


	public void setTakecategories(String takecategories) {
		this.takecategories = takecategories;
	}


	public String getRefMatCode() {
		return refMatCode;
	}
	
	public void setRefMatCode(String refMatCode) {
		this.refMatCode = refMatCode;
	}
	
	public String getTakedescription() {
		return takedescription;
	}


	public void setTakedescription(String takedescription) {
		this.takedescription = takedescription;
	}


	public String getTakeunit() {
		return takeunit;
	}


	public void setTakeunit(String takeunit) {
		this.takeunit = takeunit;
	}


	public String getTakequantity() {
		return takequantity;
	}


	public void setTakequantity(String takequantity) {
		this.takequantity = takequantity;
	}


	public String getTakeprice() {
		return takeprice;
	}


	public void setTakeprice(String takeprice) {
		this.takeprice = takeprice;
	}


	public String getTakeamount() {
		return takeamount;
	}


	public void setTakeamount(String takeamount) {
		this.takeamount = takeamount;
	}


	public String getTaketotalamount() {
		return taketotalamount;
	}


	public void setTaketotalamount(String taketotalamount) {
		this.taketotalamount = taketotalamount;
	}
	
	public int getHdrecno() {
		return hdrecno;
	}


	public void setHdrecno(int hdrecno) {
		this.hdrecno = hdrecno;
	}

	public String getMatCategoriecode() {
		return matCategoriecode;
	}

	public void setMatCategoriecode(String matCategoriecode) {
		this.matCategoriecode = matCategoriecode;
	}

	public String getMatCategoriename() {
		return matCategoriename;
	}

	public void setMatCategoriename(String matCategoriename) {
		this.matCategoriename = matCategoriename;
	}
	
	public String getMatStuffCode() {
		return matStuffCode;
	}
	public void setMatStuffCode(String matStuffCode) {
		this.matStuffCode = matStuffCode;
	}
	public String getMatColorCode() {
		return matColorCode;
	}
	public void setMatColorCode(String matColorCode) {
		this.matColorCode = matColorCode;
	}
	public String getMatBrandCode() {
		return matBrandCode;
	}
	public void setMatBrandCode(String matBrandCode) {
		this.matBrandCode = matBrandCode;
	}
	public String getTakereason() {
		return takereason;
	}
	
	public void setTakereason(String takereason) {
		this.takereason = takereason;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getTxtunit() {
		return txtunit;
	}
	public void setTxtunit(String txtunit) {
		this.txtunit = txtunit;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
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
	
	public String getTakeitemno() {
		return takeitemno;
	}
	public void setTakeitemno(String takeitemno) {
		this.takeitemno = takeitemno;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public String getDocYear() {
		return docYear;
	}
	public void setDocYear(String docYear) {
		this.docYear = docYear;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getWahoCode() {
		return wahoCode;
	}
	public void setWahoCode(String wahoCode) {
		this.wahoCode = wahoCode;
	}
	public String getDgroup() {
		return dgroup;
	}
	public void setDgroup(String dgroup) {
		this.dgroup = dgroup;
	}
	public String getLocaCode() {
		return locaCode;
	}
	public void setLocaCode(String locaCode) {
		this.locaCode = locaCode;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public String getDateRetu() {
		return dateRetu;
	}
	public void setDateRetu(String dateRetu) {
		this.dateRetu = dateRetu;
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
	public String getMatGrpName() {
		return matGrpName;
	}
	public void setMatGrpName(String matGrpName) {
		this.matGrpName = matGrpName;
	}
	public String getMatColorName() {
		return matColorName;
	}
	public void setMatColorName(String matColorName) {
		this.matColorName = matColorName;
	}
	public String getrUnitName() {
		return rUnitName;
	}
	public void setrUnitName(String rUnitName) {
		this.rUnitName = rUnitName;
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
	public String getDocMonth() {
		return docMonth;
	}
	public void setDocMonth(String docMonth) {
		this.docMonth = docMonth;
	}
	public String getDateBrow() {
		return dateBrow;
	}
	public void setDateBrow(String dateBrow) {
		this.dateBrow = dateBrow;
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
	public String getRefOrdTypeCode() {
		return refOrdTypeCode;
	}
	public void setRefOrdTypeCode(String refOrdTypeCode) {
		this.refOrdTypeCode = refOrdTypeCode;
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
	public String getWahoName() {
		return wahoName;
	}
	public void setWahoName(String wahoName) {
		this.wahoName = wahoName;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getLotDate() {
		return lotDate;
	}
	public void setLotDate(String lotDate) {
		this.lotDate = lotDate;
	}
	public String getTextBoxId() {
		return textBoxId;
	}
	public void setTextBoxId(String textBoxId) {
		this.textBoxId = textBoxId;
	}


}