package com.dtac.admin.form;

public class MemberLoginForm extends MemberForm {
	private static final long serialVersionUID = 1L;

	public String ipaddrno;
	public String lastLoginDate;
	public String lastChangPswd;
	public String password;
	public String newPassword;
	
	public MemberLoginForm() {}
	public MemberLoginForm(String memberTypeCode, String memberTypeName, 
		String memberId, String firstName, String lastName, 
		String deptCode, String deptName, String deptNameAbbv, String status, 
		String ipaddrno, String lastLoginDate, String lastChangPswd) {
		//02-10-2011
		super(memberTypeCode, memberTypeName, memberId, firstName, lastName, status);
		this.ipaddrno = ipaddrno;
		this.lastLoginDate = lastLoginDate;
		this.lastChangPswd = lastChangPswd;
	}
	public void initial() {
		this.ipaddrno		= "";
		this.memberID		= "";
		this.firstName		= "";
		this.lastName		= "";
		this.memberTypeCode	= "";
		this.memberTypeName	= "";
		this.deptCode		= "";
		this.deptName		= "";
		this.lastLoginDate 	= "";
		this.lastChangPswd 	= "";
		this.password		= "";
		this.newPassword	= "";
	}
	public String getIpaddrno() {
		return ipaddrno;
	}
	public void setIpaddrno(String ipaddrno) {
		this.ipaddrno = ipaddrno;
	}
	public String getLastChangPswd() {
		return lastChangPswd;
	}
	public void setLastChangPswd(String lastChangPswd) {
		this.lastChangPswd = lastChangPswd;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}