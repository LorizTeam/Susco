<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.DayForm" %>
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
    <title>CalendarUpdate.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body class="blueback">
    <html:form action="calendarUpdate" method="post">
    	<fieldset><legend><b class="normal">3.2 แก้ไขปฏิทินวันทำงาน/วันหยุด</b></legend>
      	<table border="0" width="95%" align="center">
        <tr><td>&nbsp;</td></tr>      	
        <tr>
 			<td class="normal">ประเภท &nbsp;&nbsp;:
				<html:text property="salTypeName" size="20" maxlength="30" readonly="true"/>
			</td>	
		</tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
			<td class="normal">วันที่ &nbsp;:&nbsp;
				<html:text property="workDate" maxlength="10" size="10" readonly="true"/>
				<html:select property="dayNo" disabled="true">
        			<%
        				if (request.getAttribute("dayList") != null) {
        					List dayList = (List)request.getAttribute("dayList");
        					for (Iterator iterDay = dayList.iterator(); iterDay.hasNext();) {
        				  		DayForm dayForm = (DayForm) iterDay.next();
        			%>
		   			<html:option value="<%=dayForm.getDayNo()%>"> <%=dayForm.getDayTHFullName()%>	</html:option>
					<% 		} 
						}
					%>
			  	</html:select>
			</td>
		</tr>
        <tr><td>&nbsp;</td></tr>

		<tr>
			<td class="blue">ประเภทวัน &nbsp;:&nbsp;
				<html:radio value="1" property="dayTypeCode">&nbsp;วันทำงาน</html:radio>&nbsp;&nbsp;
				<html:radio value="0" property="dayTypeCode">&nbsp;&nbsp;วันหยุด</html:radio>
			</td>		
		</tr>
        <tr><td>&nbsp;</td></tr>
		<tr>
			<td class="blue">หมายเหตุกรณีวันหยุด &nbsp;:&nbsp;
				<html:text property="dayRemark" maxlength="45" size="30"/>
			</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
		<tr>
			<td class="blue">เวลาเริ่มงาน &nbsp;&nbsp;:&nbsp;&nbsp;
				<html:text property="timeStart" size="10" maxlength="10" />
				<font color="black">&nbsp;&nbsp;รูปแบบ HH:MM:SS &nbsp;&nbsp; เช่น 08:00:00</font>
			</td>
		</tr>        
        <tr><td>&nbsp;</td></tr>
		<tr>
			<td class="blue">เวลาเลิกงาน &nbsp;&nbsp;:&nbsp;&nbsp;
				<html:text property="timeStop" size="10" maxlength="10" />
				<font color="black">&nbsp;&nbsp;รูปแบบ HH:MM:SS &nbsp;&nbsp; เช่น 17:15:00</font>
			</td>
		</tr>        
        <tr><td>&nbsp;</td></tr>
		<tr>
			<td align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
        <tr>
        	<td>&nbsp;&nbsp;&nbsp;
	          	<html:submit property="reqCode">
					<bean:message bundle="employeeResources" key="calendarform.button.update" />
				</html:submit>&nbsp;&nbsp;&nbsp;
    	      	<html:submit property="reqCode">
					<bean:message bundle="employeeResources" key="calendarform.button.cancel" />
				</html:submit>
			</td>
				<html:hidden property="salTypeCode" />
				<html:hidden property="year" />
				<html:hidden property="engYear" />
				<html:hidden property="month" />
        </tr>
		</table>
		</fieldset>
    </html:form>
  </body>
</html:html>