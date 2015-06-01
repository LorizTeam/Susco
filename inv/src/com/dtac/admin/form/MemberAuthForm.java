package com.dtac.admin.form;

public class MemberAuthForm extends MemberForm {
	private static final long serialVersionUID = 1L;
	
	private String idKeyDisp;
	private String idKeyMant;
	private String idKeyAppv;
	private String appType;
	private String appCode;
	private String appName;
	private String authMant;
	private String authDisp;
	private String authAppv;
	
	public String reportNo;
	private String fromDate;
	private String toDate;

	public MemberAuthForm() {}
	public MemberAuthForm(String memberTypeCode, String memberTypeName, 
		String memberId, String firstName, String lastName, String status, 
		String idKeyDisp, String idKeyMant, String idKeyAppv, String appType, String appCode, String appName, 
		String authMant, String authDisp, String authAppv) {
		//13-06-2012
		super(memberTypeCode, memberTypeName, memberId, firstName, lastName, status);
		
		this.idKeyDisp = idKeyDisp;
		this.idKeyMant = idKeyMant;
		this.idKeyAppv = idKeyAppv;
		this.appType 	= appType;
		this.appCode 	= appCode;
		this.appName 	= appName;
		this.authMant = authMant;
		this.authDisp = authDisp;
		this.authAppv = authAppv;
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
		this.appType		= "";
		this.appCode		= "";
	}	
	public String getAppCode() {
		return appCode;
	}
	public String getAppName() {
		return appName;
	}
	public String getAuthDisp() {
		return authDisp;
	}
	public String getAuthMant() {
		return authMant;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public void setAuthDisp(String authDisp) {
		this.authDisp = authDisp;
	}
	public void setAuthMant(String authMant) {
		this.authMant = authMant;
	}
	public String getIdKeyDisp() {
		return idKeyDisp;
	}
	public String getIdKeyMant() {
		return idKeyMant;
	}
	public void setIdKeyDisp(String idKeyDisp) {
		this.idKeyDisp = idKeyDisp;
	}
	public void setIdKeyMant(String idKeyMant) {
		this.idKeyMant = idKeyMant;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getAuthAppv() {
		return authAppv;
	}
	public void setAuthAppv(String authAppv) {
		this.authAppv = authAppv;
	}
	public String getIdKeyAppv() {
		return idKeyAppv;
	}
	public void setIdKeyAppv(String idKeyAppv) {
		this.idKeyAppv = idKeyAppv;
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
}