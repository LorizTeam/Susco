<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
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
    <title>My JSP 'WarehouseAddNew.jsp' starting page</title>
    
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
	<html:form action="/warehouseAddNew" >
		<fieldset><legend><b class="normal">Add</b></legend>
		<table align="center" width="60%">
		<tr><td>&nbsp;</td></tr>
        <tr>
        	<td class="blue">Warehouse Code&nbsp; :&nbsp; 
				<html:text property="wahoCode" size="2" maxlength="2"/>
		    </td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
		    <td class="blue">Warehouse Name&nbsp; :&nbsp; 
				<html:text property="wahoName" size="20" maxlength="20"/>
		    </td>		 
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="blue">&nbsp;Status :
				<html:select property="wahoStatus" >
  					<html:option value="AC">Active</html:option>
					<html:option value="CA">Inactive</html:option>
				</html:select>
			</td>		
		</tr>
		<tr>
			<td align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
 		<tr>
 			<td>&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.adddata" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.cancel" />
 				</html:submit>
 			</td>
		</tr>
		</table>
		</fieldset> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			warehouseForm.wahoCode.focus()
		</SCRIPT>	
  </body>
</html:html>