package com.dtac.admin.form;

public class MemberForm extends MemberTypeForm {
	private static final long serialVersionUID = 1L;

	public String memberID;
	public String password;
	public String firstName;
	public String lastName;
    public String deptCode;
    public String deptName;
    public String deptNameAbbv;
	public String status;
	
	public MemberForm() {}
	public MemberForm(String memberTypeCode, String memberTypeName, 
		String memberId, String firstName, String lastName, String status) {
		super(memberTypeCode, memberTypeName);
		//27-09-2009
		for (int len=memberId.length(); len<10; len++) { memberId = memberId + " "; }
		this.memberID 	= memberId;
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.status 	= status;
	}
	public MemberForm(String memberTypeCode, String memberTypeName, 
		String memberGrpCode, String memberGrpName, String punchCardStatus,
		String timeStart, String timeStop, String timeLate, 
		String memberId, String firstName, String lastName, String status) {
		super(memberTypeCode, memberTypeName, memberGrpCode, memberGrpName, punchCardStatus, 
		timeStart, timeStop, timeLate);
		//27-04-2010
		for (int len = memberId.length(); len<10; len++) { memberId = memberId + " "; }
		this.memberID = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
	}
	public MemberForm(String memberTypeCode, String memberTypeName, 
		String memberId, String firstName, String lastName,
		String deptCode, String deptName, String deptNameAbbv, String status) {
		super(memberTypeCode, memberTypeName);
		//27-09-2011
		for (int len=memberId.length(); len<10; len++) { memberId = memberId + " "; }
		this.memberID		= memberId;
		this.firstName		= firstName;
		this.lastName		= lastName;
		this.deptCode		= deptCode;
		this.deptName		= deptName;
		this.deptNameAbbv	= deptNameAbbv;
		this.status			= status;
	}	
	public void initial() {
		this.memberID		= "";
		this.firstName		= "";
		this.lastName		= "";
		this.memberTypeCode	= "";
		this.memberTypeName	= "";
		this.deptCode		= "";
		this.deptName		= "";
		this.deptNameAbbv	= "";
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptNameAbbv() {
		return deptNameAbbv;
	}
	public void setDeptNameAbbv(String deptNameAbbv) {
		this.deptNameAbbv = deptNameAbbv;
	}
}