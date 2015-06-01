package com.dtac.inventory.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChoosetypeForm extends ActionForm {

	/**
	 * 
	 */
	//Button
	private String phone_num,btn1,btn2,btninfo;
	private String btntrue,btndtac,btnais,btncancel;
	//	TextBox
	private String tb1,tb2;
	//Radio Button
	private String type;
	private static final long serialVersionUID = 1L;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBtninfo() {
		return btninfo;
	}

	public void setBtninfo(String btninfo) {
		this.btninfo = btninfo;
	}
	
	public String getTb2() {
		return tb2;
	}

	public void setTb2(String tb2) {
		this.tb2 = tb2;
	}

	public String getTb1() {
		return tb1;
	}

	public void setTb1(String tb1) {
		this.tb1 = tb1;
	}

	public String getBtn2() {
		return btn2;
	}

	public void setBtn2(String btn2) {
		this.btn2 = btn2;
	}

	public String getBtn1() {
		return btn1;
	}

	public void setBtn1(String btn1) {
		this.btn1 = btn1;
	}
	
	public ChoosetypeForm() {
		// TODO Auto-generated constructor stub
	}
	
	public String getPhone_num() {
		return phone_num;
	}


	public void setPhone_num(String phoneNum) {
		phone_num = phoneNum;
	}
	public String getBtntrue() {
		return btntrue;
	}

	public void setBtntrue(String btntrue) {
		this.btntrue = btntrue;
	}

	public String getBtndtac() {
		return btndtac;
	}

	public void setBtndtac(String btndtac) {
		this.btndtac = btndtac;
	}

	public String getBtnais() {
		return btnais;
	}

	public void setBtnais(String btnais) {
		this.btnais = btnais;
	}
	public String getBtncancel() {
		return btncancel;
	}

	public void setBtncancel(String btncancel) {
		this.btncancel = btncancel;
	}
}
