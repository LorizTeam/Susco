package com.dtac.inventory.form;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BillCodeForm extends ActionForm{
	
	public BillCodeForm() {
		// TODO Auto-generated constructor stub
	}
	private String billbarcode;
	
	public String getBillbarcode() {
		return billbarcode;
	}
	public void setBillbarcode(String billbarcode) {
		this.billbarcode = billbarcode;
	}

}
