package com.dtac.inventory.form;
import org.apache.struts.action.*;
public class ChooseBudgetForm extends ActionForm{
//	จาก ChooseType
	private String btntrue,btndtac,btnais,phone_num;


	//	จากการ เลือก Budget
	private String btn10,btn20,btn30,btn50,btn90,btn150,btn200,btn300,btncancel;
	// method get,set,constructor
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

	public String getBtn10() {
		return btn10;
	}

	public void setBtn10(String btn10) {
		this.btn10 = btn10;
	}

	public String getBtn20() {
		return btn20;
	}

	public void setBtn20(String btn20) {
		this.btn20 = btn20;
	}

	public String getBtn30() {
		return btn30;
	}

	public void setBtn30(String btn30) {
		this.btn30 = btn30;
	}

	public String getBtn50() {
		return btn50;
	}

	public void setBtn50(String btn50) {
		this.btn50 = btn50;
	}

	public String getBtn90() {
		return btn90;
	}

	public void setBtn90(String btn90) {
		this.btn90 = btn90;
	}

	public String getBtn150() {
		return btn150;
	}

	public void setBtn150(String btn150) {
		this.btn150 = btn150;
	}

	public String getBtn200() {
		return btn200;
	}

	public void setBtn200(String btn200) {
		this.btn200 = btn200;
	}

	public String getBtn300() {
		return btn300;
	}

	public void setBtn300(String btn300) {
		this.btn300 = btn300;
	}

	public String getBtncancel() {
		return btncancel;
	}

	public void setBtncancel(String btncancel) {
		this.btncancel = btncancel;
	}

	public ChooseBudgetForm() {
		// TODO Auto-generated constructor stub
	}
	
}
