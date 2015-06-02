<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.employee.form.CalendarForm" %>
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
    <title>CalendarList.jsp</title>

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
    <html:form action="calendarList" method="post">
    	<fieldset><legend><b class="normal">3.2 ปฏิทินวันทำงาน/วันหยุด</b></legend>
      	<table border="0" width="95%" align="center">
        <tr>
			<td class="blue">ปี &nbsp;:&nbsp;
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;เดือน &nbsp;:&nbsp;
      			<html:select property="month" >
	      			<%
		        		if (request.getAttribute("monthList") != null) {
			        		List monthList = (List)request.getAttribute("monthList");
			        		for (Iterator iterMonth = monthList.iterator(); iterMonth.hasNext();) {
		        		  		MonthForm monthForm = (MonthForm) iterMonth.next();
	      			%>
	       			<html:option value="<%=monthForm.getMonth()%>">
	       			 	<%=monthForm.getMonth()%> - <%=monthForm.getMonthTHFullName()%>
	       			</html:option>
					<%		} 
						}
					%>
				</html:select>
			</td>
 			<td class="blue">ประเภท &nbsp;:&nbsp;
				<html:select property="salTypeCode">			   		
			   		<html:option value="1">รายเดือน</html:option>
			   		<html:option value="0">รายวัน</html:option>
				</html:select>
			</td>	
        	<td>
	          	<html:submit property="reqCode">
					<bean:message bundle="employeeResources" key="calendarform.button.search" />
				</html:submit>
			</td>
	        	<html:hidden property="workDate" />
	        	<html:hidden property="engYear" />
	    </tr>
    	<tr>
    		<td>
    	      	<html:submit property="reqCode">
					<bean:message bundle="employeeResources" key="calendarform.button.update" />
				</html:submit>
    		</td>
			<td colspan="2" align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
      	</table>
	    </fieldset>

	    <table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightslategray">
		<tr>	
			<td width="10%" align="center" class="normal">วันที่</td>		
			<td width="10%" align="center" class="normal">วัน</td>
			<td width="10%" align="center" class="normal">ทำงาน/หยุด</td>
			<td width="10%" align="center" class="normal">ประเภท</td>
			<td width="10%" align="center" class="normal">เวลาเริ่มงาน</td>
			<td width="10%" align="center" class="normal">เวลาเลิกงาน</td>
			<td width="10%" align="center" class="normal">หมายเหตุ</td>
		</tr>
		</table>

		<table align="center" width="100%" border="1" >
		<%
			if (request.getAttribute("calendarList") != null) {
				List calendarList = (List)request.getAttribute("calendarList");
				int x = 0;
				for (Iterator iter = calendarList.iterator(); iter.hasNext();) {
				  	x++;
				  	CalendarForm calendars = (CalendarForm) iter.next();
		%>
			<% if (calendars.getDayTypeCode().equals("0")) { %>
			<tr bgcolor="red" >
			<% } else { %>
			<tr>
			<% } %>	
			 	<td width="10%" class="normal">&nbsp;
		   		  	<input type="radio" name="radio1" 
	   		  			onclick="calendarForm.workDate.value='<%=calendars.getWorkDate()%>';
				   		  		 calendarForm.salTypeCode.value='<%=calendars.getSalTypeCode()%>';"/>
					   	&nbsp;&nbsp;<%=calendars.getWorkDate()%>
		   		</td>
		
			    <td width="10%" align="center" class="normal"><%=calendars.getDayName()%></td>		
				<td width="10%"	align="center" class="normal"><%=calendars.getDayTypeName()%></td>
			    <td width="10%" align="center" class="normal"><%=calendars.getSalTypeName()%></td>			      			      
			    <td width="10%" align="center" class="normal"><%=calendars.getTimeStart()%></td>
			    <td width="10%" align="center" class="normal"><%=calendars.getTimeStop()%></td>
			    <td width="10%" align="center" class="normal"><%=calendars.getDayRemark()%></td>
	    	</tr>  
		<% 		} 
 			} else {
		%>
			<tr><td align="center" colspan="9">No Data Found</td></tr>
		<% 	} %>
		</table>
    </html:form>
  </body>
</html:html>