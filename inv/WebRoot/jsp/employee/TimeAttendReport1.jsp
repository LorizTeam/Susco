<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MemberTypeForm" %>
<%@ page import="com.dtac.admin.form.MonthForm" %>
<%@ page import="com.dtac.admin.form.YearForm" %>
<%@ include file="../admin/css_blue_style.css" %>
<%
  	String path = request.getContextPath();
  	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
  	<base href="<%=basePath%>">
    <title>TimeAttendReport1.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <html:form action="timeAttendReport1" method="post">
    	<fieldset><legend><b class="blue">2 พิมพ์รายงานการมาทำงาน</b></legend>
      	<table border="0" width="80%" align="center">
		<tr><td>&nbsp;</td></tr>	
		 <tr>
 			<td class="blue">1.ประเภทบุคลากร &nbsp;:&nbsp;
				<html:select property="empTypeCode" >	
					<html:option value="">All</html:option>		   		
					<% if (request.getAttribute("memberTypeList") != null) {
							List memberTypeList = (List)request.getAttribute("memberTypeList");
							for (int x=0; x<memberTypeList.size(); x++) {
								MemberTypeForm memberType =(MemberTypeForm) memberTypeList.get(x);
					%>
			   		<html:option value="<%=(String)memberType.getMemberTypeCode()%>">
		   				<%=(String)memberType.getMemberTypeCode()%>
			   		</html:option>
					<% 		} 
						} 
					%>
				</html:select>
				
 				&nbsp;&nbsp;&nbsp;&nbsp;2.แผนก &nbsp;:&nbsp;
				<html:text property="empDeptCode" size="3" maxlength="3"/>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
        	<td class="blue">3.ตั้งแต่วันที่ &nbsp;:&nbsp;
		       	<html:text property="fromDate"  size="10" maxlength="10"/>

		       	&nbsp;&nbsp;&nbsp;&nbsp;4.ถึงวันที่ &nbsp;:&nbsp;
		       	<html:text property="toDate"  size="10" maxlength="10"/>
		       	&nbsp;&nbsp;&nbsp;<font color="black">(dd/mm/yyyy)</font>
			</td>
		</tr>
      	<tr><td>&nbsp;</td></tr>
      	<tr>
 			<td class="blue">5.รหัสพนักงาน &nbsp;:&nbsp;
				<html:text property="empID" size="10" maxlength="10"/>
				&nbsp;&nbsp;&nbsp;6. ชื่อกลุ่ม &nbsp;:&nbsp;
				<html:text property="groupName" size="30" maxlength="50"/>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>			
			<td class="blue">7.ปี &nbsp;
       			<html:select property="year" >
	       			<%
	   			        if (request.getAttribute("yearList") != null) {
			        		List yearList = (List)request.getAttribute("yearList");
			        		for (Iterator iterYear = yearList.iterator(); iterYear.hasNext();) {
		        		  		YearForm yearForm = (YearForm) iterYear.next();
	       			%>
        			<html:option value="<%=yearForm.getYear()%>"><%=yearForm.getYear()%></html:option>
					<%		} 
						}
					%>
			  	</html:select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8.เดือน &nbsp;
      			<html:select property="month" >
	      			<%
		        		if (request.getAttribute("monthList") != null) {
			        		List monthList = (List)request.getAttribute("monthList");
			        		for (Iterator iterMonth = monthList.iterator(); iterMonth.hasNext();) {
		        		  		MonthForm monthForm = (MonthForm) iterMonth.next();
	      			%>
	       			<html:option value="<%=monthForm.getMonth()%>"><%=monthForm.getMonth()%> </html:option>
					<%		} 
						}
					%>
				</html:select>		
			</td>
		</tr>
      	
		<tr><td>&nbsp;</td></tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;รายงานที่ต้องการ &nbsp;:&nbsp;</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     			<html:radio value="1" property="reportNo">&nbsp;&nbsp;1.ผลการบันทึกเวลาประจำวัน (กรอกข้อ 1,2,3) </html:radio>
        	</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       			<html:radio value="2" property="reportNo" >&nbsp;&nbsp;2.ผลการบันทึกเวลารายบุคคล (กรอกข้อ 1,2,3,4)</html:radio>
    	   	</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       			<html:radio value="3" property="reportNo" >&nbsp;&nbsp;3.รายละเอียด ชม.การทำงานของพนักงาน (กรอกข้อ 1,2,3,4,[5],[6])</html:radio>
    	   	</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       			<html:radio value="3_1" property="reportNo" >&nbsp;&nbsp;3.1 รายละเอียด ชม.การทำงานของพนักงาน (กรอกข้อ 1,2,[5],[6],7,8)</html:radio>
    	   	</td>
        </tr>

		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       			<html:radio value="4" property="reportNo" >&nbsp;&nbsp;4.การปฏิบัติงานพนักงาน แยกตามกลุ่ม (กรอกข้อ [1],[2],3,[6])</html:radio>
    	   	</td>
        </tr>

		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;		
				<html:submit property="reqCode">
					<bean:message bundle="employeeResources" key="timeattendform.button.print" />
				</html:submit>
			</td>
		</tr>
		</table>
		</fieldset>
	</html:form>
  </body>
</html:html>