<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.WarehouseForm" %>
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
    <title>My JSP 'WarehouseList.jsp' starting page</title>
    
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
	<html:form action="warehouseAuth" >
		<fieldset><legend><b class="normal">กำหนดสิทธิ์ผู้ใช้งานสถานที่เก็บสินค้า</b></legend>
		<table align="center" width="90%">
		<tr><td>&nbsp;</td></tr>
		<tr>
		   	<td class="normal">Warehouse Code&nbsp;:&nbsp;
       			<html:text property="wahoCode" size="2" maxlength="2" readonly="true"/>       			
			</td>	
		 	<td class = "normal"> Warehouse Name&nbsp;:&nbsp;
		 		<html:text property="wahoName" size = "20" maxlength = "50" readonly="true"/> 
		 	</td>				
		</tr>
		<tr>
			<td class="red"><%=alertMessage%></td>
		</tr>
		<tr><td>&nbsp;</td></tr>	
		</table>
		</fieldset>
 	<table width="90%" align="center" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	<td width="45%">
 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightslategray">
	<tr>
	<td align="center" width="100%" colspan="4">รายชื่อพนักงานทั้งหมด</td>
	</tr>
	<tr>
	<td align="center" width="10%">No.</td>
	<td align="center" width="25%">Code</td>
	<td align="center" width="5%">&nbsp;</td>
	<td align="center" width="60%">Name-LastName</td>
	</tr>
	</table>
	</td>
 	<td width="10%">&nbsp;</td>
 	<td width="45%">
<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightslategray">
	<tr>
	<td align="center" width="100%" colspan="4">รายชื่อพนักงานที่มีสิทธิ์ใช้งาน</td>
	</tr>
	<tr>
	<td align="center" width="10%">No.</td>
	<td align="center" width="25%">Code</td>
	<td align="center" width="5%">&nbsp;</td>
	<td align="center" width="60%">Name-LastName</td>
	</tr>
	</table>
	</td>
 	</tr></table>
 	<table align="center" border="0" cellpadding="0" cellspacing="0" width="90%">	<tr>
 	<td width="45%" valign="top">
 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%">
	<%	if (request.getAttribute("notAuthList") != null) {
				List notAuthList = (List)request.getAttribute("notAuthList");
				int x = 0;
				for (Iterator iter = notAuthList.iterator(); iter.hasNext();) {
		  			x++;
					WarehouseForm wahoNotAuthList = (WarehouseForm) iter.next();
		%>
	<tr>
	<td align="center" width="10%">&nbsp;<%=x %>.</td>
	<td align="center" width="25%">&nbsp;<%=wahoNotAuthList.getEmpID() %></td>
	<td align="center" width="5%">&nbsp;<input type="checkbox" name="empNotAuth" value="<%=wahoNotAuthList.getEmpID() %>"/></td>
	<td width="60%">&nbsp;&nbsp;&nbsp;&nbsp;<%=wahoNotAuthList.getEmpNameT() %> <%=wahoNotAuthList.getEmpLastNameT() %></td>
	</tr>
	<%}} else { %>		      
		    <tr><td align="center" colspan="4">No Data Found</td></tr>	   		  		      		      		      		      
		    <% } %>	
	</table>
	</td>
 	<td width="10%" valign="baseline"><table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" >
  	<tr><td align="center"><html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.back" />
 				</html:submit></td></tr>
 				<tr><td>&nbsp;</td></tr>
 	<tr><td align="center"><html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.next" />
 				</html:submit></td></tr>
 </table></td>
 	<td width="45%" valign="top">
<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" >
<%	if (request.getAttribute("warehouseAuthList") != null) {
				List warehouseAuthList = (List)request.getAttribute("warehouseAuthList");
				int x = 0;
				for (Iterator iter = warehouseAuthList.iterator(); iter.hasNext();) {
		  			x++;
					WarehouseForm wahoAuthList = (WarehouseForm) iter.next();
		%>
	<tr>
	<td align="center" width="10%">&nbsp;<%=x %>.</td>
	<td align="center" width="25%">&nbsp;<%=wahoAuthList.getEmpID() %></td>
	<td align="center" width="5%">&nbsp;<input type="checkbox" name="empAuth" value="<%=wahoAuthList.getEmpID() %>"/></td>
	<td width="60%">&nbsp;&nbsp;&nbsp;&nbsp;<%=wahoAuthList.getEmpNameT() %> <%=wahoAuthList.getEmpLastNameT() %></td>
	</tr>
	<%}} else { %>		      
		    <tr><td align="center" colspan="4">No Data Found</td></tr>	   		  		      		      		      		      
		    <% } %>	
	</table>
	</td>
 	</tr>
 	</table>
	
	
		
	</html:form>
  </body>
</html:html>