<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MemberTypeForm" %>
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
    <title>My JSP 'VenderAddNew.jsp' starting page</title>
    
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
	<html:form action="venderAddNew" >
		<fieldset><legend><b class="blue"><br>Add</b></legend>
		<table align="center" width="95%">
		<tr><td>&nbsp;</td></tr>
		<tr>
 			<td class="blue">Vender Code&nbsp;:&nbsp;
 				<html:text property="venderCode" 	size="6" 	maxlength="6"/>
 			</td>	
 		</tr>
 		<tr><td>&nbsp;</td></tr>
 		<tr>
 			<td class="blue">Vender Name&nbsp;:&nbsp;
				<html:text property="venderName" 	size="30" 	maxlength="50"/>
 			</td>
 		</tr>
 		<tr><td>&nbsp;</td></tr>
 		<tr>
			<td class="blue">Search &nbsp;:&nbsp;
				<html:text property="searchName"	size="20" 	maxlength="30"/>
			</td>			
 		</tr>
		<tr><td align="center" class="red">&nbsp;<%=alertMessage%></td></tr>
		<tr>
 			<td class="normal">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="venderform.button.adddata" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp; 				
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="venderform.button.cancel" />
 				</html:submit> 				
 			</td>
        </tr>		
		</table>
		</fieldset> 	 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			venderForm.venderCode.focus()
		</SCRIPT>	
  </body>
</html:html>