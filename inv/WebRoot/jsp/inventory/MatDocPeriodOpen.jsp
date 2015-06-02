<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.MatDocPeriodForm" %>
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
    <title>My JSP 'MatDocPeriodList.jsp' starting page</title>
    
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
	<html:form action="matDocPeriodOpen" >
		<fieldset><legend><b class="blue"><br>2.2 เปิดงวดการทำเอกสาร</b></legend>
		<table align="center" width="70%">
		<tr><td>&nbsp;</td></tr>
		<tr>
		    <td class="normal" align="center">ปีเอกสาร &nbsp;:&nbsp;
       			<html:text property="year" size="4" maxlength="4" readonly="true"/>
   			 &nbsp;&nbsp;&nbsp;เดือน &nbsp;:&nbsp;
      			<html:text property="month" size="2" maxlength="2" readonly="true"/>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr> 
		<tr>
			<td class="normal" align="center">
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="matdocperiodform.button.open" />
 				</html:submit>&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="matdocperiodform.button.cancel" />
 				</html:submit>
 			</td>
		</tr>
		</table>
		</fieldset> 	
		
	 	
	</html:form>
	 	
  </body>
</html:html>