package com.dtac.admin.form;

import org.apache.struts.action.ActionForm;

public class MemberTypeForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	public String memberTypeCode;
	public String memberTypeName;

	public String memberGrpCode;
	public String memberGrpName;
	public String punchCardStatus;
	public String timeStart;
	public String timeStop;
	public String timeLate;

	public MemberTypeForm() {}
	public MemberTypeForm(String memberTypeCode, String memberTypeName) {
		super();
		this.memberTypeCode	= memberTypeCode;
		this.memberTypeName	= memberTypeName;
	}
	public MemberTypeForm(String memberTypeCode, String memberTypeName,
		String memberGrpCode, String memberGrpName) {	//15-04-2010
		super();
		this.memberTypeCode	= memberTypeCode;
		this.memberTypeName	= memberTypeName;
		this.memberGrpCode	= memberGrpCode;
		this.memberGrpName	= memberGrpName;
	}
	public MemberTypeForm(String memberTypeCode, String memberTypeName, 
		String memberGrpCode, String memberGrpName, String punchCardStatus,
		String timeStart, String timeStop, String timeLate) {	//04-06-2009
		super();
		this.memberTypeCode = memberTypeCode;
		this.memberTypeName = memberTypeName;
		this.memberGrpCode	= memberGrpCode;
		this.memberGrpName	= memberGrpName;
		this.punchCardStatus= punchCardStatus;
		this.timeStart 	= timeStart;
		this.timeStop 	= timeStop;
		this.timeLate 	= timeLate;
	}
	public void initial() {
		this.memberTypeCode	= "";
		this.memberTypeName	= "";
		this.memberGrpCode	= "";
		this.memberGrpName	= "";
		this.punchCardStatus= "";
	}
	public String getMemberTypeCode() {
		return memberTypeCode;
	}
	public void setMemberTypeCode(String memberTypeCode) {
		this.memberTypeCode = memberTypeCode;
	}
	public String getMemberTypeName() {
		return memberTypeName;
	}
	public void setMemberTypeName(String memberTypeName) {
		this.memberTypeName = memberTypeName;
	}
	public String getTimeLate() {
		return timeLate;
	}
	public void setTimeLate(String timeLate) {
		this.timeLate = timeLate;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeStop() {
		return timeStop;
	}
	public void setTimeStop(String timeStop) {
		this.timeStop = timeStop;
	}
	public String getMemberGrpCode() {
		return memberGrpCode;
	}
	public void setMemberGrpCode(String memberGrpCode) {
		this.memberGrpCode = memberGrpCode;
	}
	public String getMemberGrpName() {
		return memberGrpName;
	}
	public void setMemberGrpName(String memberGrpName) {
		this.memberGrpName = memberGrpName;
	}
	public String getPunchCardStatus() {
		return punchCardStatus;
	}
	public void setPunchCardStatus(String punchCardStatus) {
		this.punchCardStatus = punchCardStatus;
	}
}