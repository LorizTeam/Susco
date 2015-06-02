<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="org.apache.commons.validator.Form"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="UTF-8"%>
<%@ page import="com.dtac.utils.DateUtil" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SummaryBillPayment.jsp' starting page</title>
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
  
  <body> 
    this is my SummaryBillPayment JSP page. <br/>
    <% 	String phone_num = (String) session.getAttribute("phone_num")
  		,choosetype = (String) session.getAttribute("choosetype")
  		,budget = (String) session.getAttribute("budget")
  		,totalprice = (String) session.getAttribute("totalprice");
  	 %>
  	เติมเงินโทรศัพท์ เบอร์ <%=phone_num %><br/>
  	เติมเงินค่าย <%=choosetype %><br/>
  	วงเงินที่เติม  <html:text property="budget" readonly="true" styleClass="form-control" 
				 value="<%=budget %>"/><br/>
  	ยอดรวม  <html:text property="sumamount" readonly="true" styleClass="form-control" 
				 value="<%=totalprice %>"/><br/>
  	จ่าย <br/>
  	ทอน <br/>
  </body>
</html>
