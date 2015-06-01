<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="org.apache.commons.validator.Form"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="com.dtac.utils.DateUtil" %>
<%@ page import="com.dtac.inventory.form.ChooseBudgetForm" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'choosetype.jsp' starting page</title>
    <%@ include file="../../css/bootstrap.css" %>
	<%@ include file="../../css/bootstrap-theme.css" %>
	<%@ include file="../../css/inv.css" %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!--	รูปดึงจาก "jsp/image/.png"-->
  </head>
  
  <body bgcolor="#DAC987">
  <html:form action="/ChooseBudget" method="POST">
  	<% 	String phone_num = request.getParameter("phone_num")
  		,choosetype = (String) request.getAttribute("choosetype");
  	 %>
  	เติมเงินโทรศัพท์ เบอร์ <%=phone_num %><br/>
  	เติมเงินค่าย <%=choosetype %>
  	กรุณาเลือกวงเงินที่ต้องการเติม
<!--  	สำหรับ 10 บาท-->
	<button type="submit" name="btn10" value="1"><img src="jsp/image/Susco.png"/></button>
<!--	สำหรับ 20 บาท-->
	<button type="submit" name="btn20" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับ 30 บาท-->
	<button type="submit" name="btn30" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับ 50 บาท-->
	<button type="submit" name="btn50" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับ 90 บาท-->
	<button type="submit" name="btn90" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับ 150 บาท-->
	<button type="submit" name="btn150" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับ 200 บาท-->
	<button type="submit" name="btn200" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับ 300 บาท-->
	<button type="submit" name="btn300" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับ Cancel-->
	<button type="submit" name="btncancel" value="1"><img src="jsp/image/button00.jpg"/></button>
  </html:form>
  </body>
</html>
