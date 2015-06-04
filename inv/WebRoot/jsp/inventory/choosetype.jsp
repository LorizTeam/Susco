<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="com.dtac.utils.DateUtil" %>
<%@ page import="com.dtac.inventory.form.ChoosetypeForm" %>
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

  </head>
  
  <body bgcolor="#DAC987">
  <html:form action="/choosetype" method="POST">
  	<% String phone_num = (String) session.getAttribute("phone_num");%>
  	<div class="container ">
  		<h3 class="text-primary"><small>เติมเงินโทรศัพท์ เบอร์  : </small><b><%=phone_num%></b> </h3>
  	</div>
  <div class="container thumbnail">
  <h4 class="text-primary">เลือกผู้ให้บริการ</h4>
  <div class="row ">
  	<div class="col-md-4">  
		<!--  	สำหรับ True-->
		<button class="btn btn-default col-md-12" type="submit" name="btntrue" value="1">
			<img class="img-responsive" src="true.PNG"/>
		
		</button>
	</div>
  	<div class="col-md-4">
  		<!--	สำหรับ Dtac-->
		<button class="btn btn-default col-md-12"  type="submit" name="btndtac" value="1">
			<img class="img-responsive" src="DTAC.png"/>
		</button>
  	</div>
  	<div class="col-md-4">
  		<!--	สำหรับ AIS-->
		<button class="btn btn-default col-md-12"  type="submit" name="btnais" value="1">
			<img class="img-responsive" src="AIS.png"/>
		</button>
  	</div>
  	
  </div>
  </div>
  <!-- สำหรับชำระบิล Start  -->
  <div class="container thumbnail">
  <h4 class="text-primary">เลือกชำระบิล</h4>
  <div class="row ">
  	<div class="col-md-4">  
		<!--  	สำหรับ  ชำระบิลค่าไฟ  -->
		<button class="btn btn-default col-md-12" type="submit" name="btnelectricity" value="1">
			<img class="img-responsive" src="MEA.gif"/>
		
		</button>
	</div>
  	<div class="col-md-4">
  		<!--	สำหรับ  ชำระบิลค่าน้ำ-->
		<button class="btn btn-default col-md-12"  type="submit" name="btnwater" value="1">
			<img class="img-responsive" src="Water.png"/>
		</button>
  	</div>  	
  </div>
  </div>
  <!-- สำหรับชำระบิล End -->
<div class="row">
  	<div class="col-md-4"></div>
  	<div class="col-md-4">
<!--	สำหรับ ยกเลิก-->
	<button class="btn btn-warning col-md-12 btn-lg" type="submit" name="btncancel" value="1"><h4> << กลับ</h4></button></div>
  </div>

<!--	สำหรับHidden-->
	<html:hidden property="phone_num" value="<%=phone_num%>"/>
  </html:form>
  </body>
</html>
