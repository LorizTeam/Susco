<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="org.apache.commons.validator.Form"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="com.dtac.utils.DateUtil" %>
<%@ page import="com.dtac.inventory.form.ChooseBudgetForm" %>
<%@ page import ="java.util.*" %>
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
  
  <body bgcolor="#DAC987" >
  <html:form action="/ChooseBudget" method="POST">
  	<% 	String phone_num = (String) session.getAttribute("phone_num")
  		,choosetype = (String) session.getAttribute("choosetype");
  	 %>
  	<div class="container text-primary">
	  <h3><small>เติมเงินโทรศัพท์ เบอร์ : </small><b><%=phone_num %></b> </h3>	
	  <h3><small>เติมเงินค่าย</small> <b><%=choosetype %></b> </h3>		
	 	
  	</div>
  <div class="container thumbnail">
   	<h4 class="text-primary">กรุณาเลือกวงเงินที่ต้องการเติม </h4>	
  	<div class="row va">
  		<div class="col-md-3">
  			<!--  	สำหรับ 10 บาท-->
			<button type="submit" class="btn btn-info btn-lg col-md-12" name="btn10" value="1"> 10 บาท  </button>
  		</div>
  		<div class="col-md-3">
  			<!--	สำหรับ 20 บาท-->
			<button type="submit" class="btn btn-info btn-lg col-md-12" name="btn20" value="1"> 20 บาท  </button>
  		</div>
  		<div class="col-md-3">
  			<!--	สำหรับ 30 บาท-->
			<button type="submit" class="btn btn-info btn-lg col-md-12" name="btn30" value="1"> 30 บาท </button>
  		</div>
  		<div class="col-md-3">
  			<!--	สำหรับ 50 บาท-->
			<button type="submit" class="btn btn-info btn-lg col-md-12" name="btn50" value="1"> 50 บาท </button>
  		</div>	
  	</div>
  	<div class="row va">
  		<div class="col-md-3">
  			<!--	สำหรับ 90 บาท-->
			<button type="submit" class="btn btn-info btn-lg col-md-12" name="btn90" value="1"> 90 บาท </button>
  		</div>
  		<div class="col-md-3">
  			<!--	สำหรับ 90 บาท-->
			<button type="submit" class="btn btn-info btn-lg col-md-12" name="btn150" value="1"> 150 บาท </button>
  		</div>
  		<div class="col-md-3">
  			<!--	สำหรับ 90 บาท-->
			<button type="submit" class="btn btn-info btn-lg col-md-12" name="btn200" value="1"> 200 บาท </button>
  		</div>
  		<div class="col-md-3">
  			<!--	สำหรับ 90 บาท-->
			<button type="submit" class="btn btn-info btn-lg col-md-12" name="btn300" value="1"> 300 บาท </button>
  		</div>
  	</div>
  	<div class="row">
  		<div class="col-md-3"></div>
  		<div class="col-md-3">
  			<button type="submit" class="btn btn-warning col-md-12" name="btnreturn" value="1"><h1>กลับ</h1> </button>
  		</div>
  		<div class="col-md-3">
  			<button type="submit" class="btn btn-danger col-md-12" name="btncancel" value="1"><h1> ยกเลิก</h1> </button>
  		</div>
  	</div>
</div>


  </html:form>
  </body>
</html>
