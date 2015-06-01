<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.employee.form.PeriodForm" %>
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
    <title>PeriodList.jsp</title>

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
    <html:form action="periodList" method="post">
    	<fieldset><legend><b class="normal">3.1 ข้อมูลรอบการคิดเงินเดือน</b></legend>
      	<table border="0" width="50%" align="center">
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
					<% 		} 
						}
					%>
			  	</html:select>
				&nbsp;&nbsp;&nbsp;&nbsp;ประเภท &nbsp;:&nbsp;
				<html:select property="salTypeCode" >			   		
			   		<html:option value="1">รายเดือน</html:option>
			   		<html:option value="0">รายวัน</html:option>
				</html:select>
				&nbsp;&nbsp;&nbsp;&nbsp;				
         		<html:submit property="reqCode">
					<bean:message bundle="employeeResources" key="periodform.button.search" />
				</html:submit>
			</td>
			<td align="center" class="red">&nbsp;<%=alertMessage%></td>
				<html:hidden property="month"/>
				<html:hidden property="periodNo"/>
        </tr>
      	</table>
	    </fieldset>
			  	
	    <table align="center" border="1" width="85%" cellpadding="0" cellspacing="0" bgcolor="lightslategray">
		<tr>	
			<td colspan="2" class="normal">&nbsp;&nbsp;&nbsp;
				<html:submit property="reqCode"> 
					<bean:message bundle="employeeResources" key="periodform.button.update" />
 				</html:submit>			
			</td>		
		</tr>
		<tr>	
			<td width="5%"   align="center" class="normal">ลำดับที่</td>		
			<td width="10%"  align="center" class="normal">เดือน&nbsp;/&nbsp;ปี</td>
			<td width="5%"   align="center" class="normal">ครั้งที่</td>
			<td width="10%"  align="center" class="normal">ประเภท</td>
			<td width="10%"  align="center" class="normal">%ประกันสังคม</td>
			<td width="10%"  align="center" class="normal">เริ่มวันที่</td>								
			<td width="10%"  align="center" class="normal">สิ้นสุดวันที่</td>	
			<td width="10%"  align="center" class="normal">สถานะ</td>	
			<td width="10%"  align="center" class="normal">งวดปัจจุบัน</td>
		</tr>
		</table>

		<table align="center" width="85%" border="1">
		<%	if (request.getAttribute("periodList") != null) {
				List periodList = (List)request.getAttribute("periodList");
				int x = 0;
				for (Iterator iter = periodList.iterator(); iter.hasNext();) {
		  			x++;
		  			PeriodForm periods = (PeriodForm) iter.next();
		%>
		<% if (periods.getCurrFlag().equals("Y")) { %>
		<tr bgcolor="blue">
		<% } else { %>  
	    <tr>		
	    <% } %>
   		  	<td width="5%"  align="center" class="orange">
				<input type="radio" name="radio1" 
			   	  		onclick="periodForm.year.value='<%=periods.getYear()%>';
			   					 periodForm.month.value='<%=periods.getMonth()%>';
			   					 periodForm.periodNo.value='<%=periods.getPeriodNo()%>';
			   					 periodForm.salTypeCode.value='<%=periods.getSalTypeCode()%>';"/><%=x%>.
			   		
	   		</td>
	   		<td width="10%" align="center" class="normal">&nbsp;<%=periods.getMonth()%>&nbsp;/&nbsp;<%=periods.getYear()%></td>
	   		<td width="5%"  align="center" class="normal">&nbsp;<%=periods.getPeriodNo()%></td>
	   		<td width="10%" align="center" class="normal">&nbsp;<%=periods.getSalTypeName()%></td>
	   		<td width="10%" align="center" class="normal">&nbsp;<%=periods.getSocialRate()%></td>
	    	<td width="10%" align="center" class="normal">&nbsp;<%=periods.getStartDate()%></td>
	    	<td width="10%" align="center" class="normal">&nbsp;<%=periods.getEndDate()%></td>	

			<% if (periods.getStatus().equals("AC")) { %>
			<td width="10%" align="center" class="red">แก้ไขข้อมูลได้</td>
			<% } else { %>
			<td width="10%" align="center" class="normal">เสร็จสิ้นแล้ว</td>
			<% } %>			
	    	<td width="10%" align="center" class="normal">&nbsp;<%=periods.getCurrFlag()%></td>	    	  
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