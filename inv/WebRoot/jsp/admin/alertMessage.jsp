<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="css_blue_style.css" %>
<%
 	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String alertMessage = "";
	if(request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>Alert Message</title>
    
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
		<table width="100%" height="100%">
		<tr>
			<td align="center">
				<table width="100%">
				<tr>
					<td align="center"><font size="5" color="blue"><%=alertMessage%></font></td>
				</tr>
			 	</table>
			</td>
		</tr>
		</table> 
  	</body>
</html:html>