<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="UTF-8"%>
<%@ page import="com.dtac.utils.DateUtil" %>
<%@ page import="com.dtac.inventory.form.SummaryBillPaymentForm" %>
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
  <html:form action="/SummaryBillPayment" method="POST">
    
    <% 	String phone_num = (String) session.getAttribute("phone_num")
  		,choosetype = (String) session.getAttribute("choosetype")
  		,budget = (String) session.getAttribute("budget")
  		,totalprice = (String) session.getAttribute("totalprice");
  	 %>
  	 
  	 <div class="container">
  	 <h3 class="text-center text-primary">สรุปการเติมเงิน</h3>
  	 	<div class="row">
  	 		<div class="col-md-3"></div>
  	 		<div class="col-md-6 thumbnail">
  	 			<h4 class="text-primary"><small>เติมเงินโทรศัพท์ เบอร์  </small> <%=phone_num %></h4>
  	 			<h4 class="text-primary"><small>เติมเงินค่าย </small> <%=choosetype %></h4>
  	 				<div class="row">
  					<div class="col-md-3">
  						<h5>วงเงินที่เติม</h5>
  					</div>
  	 				<div class="col-md-9">
  	 					<h5 class="text-primary"><%=budget %></h5>
  	 				</div>	 
  	 				</div>
  	 				<div class="row">
  	 				<div class="col-md-3">
  	 					<h5>ยอดรวม</h5>
  	 				</div>
  	 				<div class="col-md-9">
  	 					<h5 class="text-primary"><%=totalprice %></h5>
  	 				</div>
  	 				</div>
  	 				<div class="row">
					<div class="col-md-3"><h5>จ่าย</h5></div>
  	 				<div class="col-md-9">
  	 					<html:text property="totalpay" styleId="totalpay"
  	 					styleClass="form-control" value="" onkeyup="GetBalance()"/>
  	 				</div>
  	 				</div>
  	 				<div class="row">
  	 				<div class="col-md-3"><h5>ทอน</h5></div>
  	 				<div class="col-md-9">
  	 					 <html:text property="totalbalance" styleId="totalbalance" 
  	 					 styleClass="form-control" value=""/>
  	 				</div>
  	 				</div>
  	 				
  	 		</div>	 		
  	 	</div>  
  	 	<div class="row">
  	 		<div class="col-md-3"></div>
  	 		<div class="col-md-6">
  	 			<button type="submit" class="btn btn-warning col-md-6" name="btnreturn" value="1">กลับ</button>
  	 			<button type="submit" class="btn btn-danger col-md-6" name="btncancel" value="1">ยกเลิก</button>
  	 		</div>
  	 	</div>	 
  	 </div>


	
<!--	สำหรับ Cancel-->
	
  </html:form>
  </body>
  <script>
  	function GetBalance(){
//  		แสดงเงินทอน
//	alert(document.getElementById("totalpay").value);
  		document.getElementById("totalbalance").value = document.getElementById("totalpay").value - document.getElementById("totalprice").value;
  	}
  </script>
</html>
