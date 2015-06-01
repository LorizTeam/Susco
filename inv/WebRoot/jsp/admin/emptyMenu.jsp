<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/admin/css_blue_style.css" %>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'emptyMenu.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
  
  <body bgcolor="#DAC987">
<p><br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="jsp/image/Susco.png" width="100" height="90" border="0" > 
	  <img src="jsp/image/button00.jpg" width="140" height="50" border="0" usemap="#Map"> 
	  <map name="Map">
	    <area shape="rect" coords="1,2,142,46" href="jsp/admin/mainAdmin.jsp" target="_parent">
	  </map>

	  <img src="jsp/image/button06.jpg" width="140" height="50" border="0" usemap="#Map1"> 
	  <map name="Map1">
	    <area shape="rect" coords="0,0,141,50" href="jsp/employee/mainEmployee.jsp" target="_parent">
	  </map>

	 
	    <area shape="rect" coords="1,0,143,54" href="jsp/sale/mainSale.jsp" target="_parent">
	  </map>

	  <img src="jsp/image/button04.jpg" width="140" height="50" border="0" usemap="#Map3" > 
	  <map name="Map3">
	    <area shape="rect" coords="3,1,142,49" href="jsp/inventory/mainInventory.jsp" target="_parent">
	  </map>

	   
	  
</p>
<p>

</p>
  </body>
</html>
