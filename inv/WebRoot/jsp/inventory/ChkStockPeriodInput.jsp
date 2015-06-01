<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.MaterialForm" %>
<%@ page import="com.dtac.inventory.form.WarehouseForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
<%@ page import="com.dtac.inventory.form.MaterialWahoForm" %>
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
    <title>My JSP 'MaterialWahoList.jsp' starting page</title>
    
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
	<html:form action="chkStockPeriodInput" >
		<fieldset><legend><b class="blue"><br>4.6 เช็คสต็อก</b></legend>
		<table align="center" width="90%">
		<tr>
			<td class="blue">ปี &nbsp;:&nbsp;  
 				<html:select property="docYear" >
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
			</td> 
			<td class="blue">เดือน&nbsp;:&nbsp;  
 				<html:select property="docMonth">
	       			<%
	   			        if (request.getAttribute("monthList") != null) {
			        		List monthList = (List)request.getAttribute("monthList");
			        		for (Iterator iterMonth = monthList.iterator(); iterMonth.hasNext();) {
			        			MonthForm monthInfo = (MonthForm) iterMonth.next();
	       			%>
        			<html:option value="<%=monthInfo.getMonth()%>"><%=monthInfo.getMonth()%></html:option>
					<%		} 
						}
					%>
			  	</html:select>
 			</td>		
 			<td class="blue">คลังสินค้า &nbsp;:&nbsp;
				<html:select property="wahoCode" >				
					<% if (request.getAttribute("warehouseList") != null) {
							List warehouseList = (List)request.getAttribute("warehouseList");
							for (int x=0; x<warehouseList.size(); x++) {
								WarehouseForm wahoInfo =(WarehouseForm) warehouseList.get(x);
					%>
			   		<html:option value="<%=wahoInfo.getWahoCode()%>"><%=wahoInfo.getWahoName()%></html:option>
					<% 		} 
						} 
					%>
				</html:select>			
		 	</td>
		    <td class="blue">
			  	<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="checkstockform.button.next" />
 				</html:submit>
		    </td>
		</tr>
		<tr><td align="center" class="red" colspan="9">&nbsp;<%=alertMessage%></td></tr>
 		</table>
		</fieldset> 	
	</html:form>
	
  </body>
</html:html>