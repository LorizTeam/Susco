<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="com.dtac.utils.DateUtil" %>
<%@ page import="com.dtac.inventory.form.ChoosetypeForm" %>
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

  </head>
  
  <body bgcolor="#DAC987">
  <html:form action="/choosetype" method="POST">
  	<% String phone_num = request.getParameter("studentID");%>
  	เติมเงินโทรศัพท์ เบอร์ <%=phone_num%><br/>
  	กรุณาเลือกค่ายที่ต้องการเติม
<!--  	สำหรับ True-->
	<button type="submit" name="btntrue" value="1"><img src="jsp/image/Susco.png"/></button>
<!--	สำหรับ Dtac-->
	<button type="submit" name="btndtac" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับ AIS-->
	<button type="submit" name="btnais" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับ ยกเลิก-->
	<button type="submit" name="btncancel" value="1"><img src="jsp/image/button00.jpg"/></button>
<!--	สำหรับHidden-->
	<html:hidden property="phone_num" value="<%=phone_num%>"/>
  </html:form>
  </body>
</html>
