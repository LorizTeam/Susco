package com.dtac.inventory.form;

import org.apache.struts.action.ActionForm;


public class StudentForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String studentID;
	private String studentName;
	private String dateTime;
	
	private String btAdd;
	private String btDelete;
	private String btUpdate;
	private String btSearch;
	
	public StudentForm(){};
	public StudentForm(String studentID, String studentName, String dateTime) { 
	super();
	this.studentID = studentID;
	this.studentName = studentName;
	this.dateTime = dateTime;
	}
	
	public void initial() {
		this.studentID = "";
		this.studentName = "";
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getBtAdd() {
		return btAdd;
	}
	public void setBtAdd(String btAdd) {
		this.btAdd = btAdd;
	}
	public String getBtDelete() {
		return btDelete;
	}
	public void setBtDelete(String btDelete) {
		this.btDelete = btDelete;
	}
	public String getBtUpdate() {
		return btUpdate;
	}
	public void setBtUpdate(String btUpdate) {
		this.btUpdate = btUpdate;
	}
	public String getBtSearch() {
		return btSearch;
	}
	public void setBtSearch(String btSearch) {
		this.btSearch = btSearch;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
