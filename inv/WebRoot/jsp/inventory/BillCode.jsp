<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%@ page import ="com.dtac.utils.DateUtil" %>
<%@ page import ="com.dtac.inventory.form.BillCodeForm" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BillCode.jsp' starting page</title>
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
  
  <body class="container">
    <html:form action="/BillCode">
 <% String choosetype = (String) session.getAttribute("choosetype");
    %>
    <br/><br/>
    	<div class="row thumbnail">
    		<div class="col-md-3"></div>
    		
    		<h2 class="text-primary">ชำระค่าบริการ  <%=choosetype %></h2>
    		<br/>
    		<div class="col-md-3"></div>
    		<div class="col-md-6">			
    			<div class="row">
    				<div class="col-md-5"><h4 class="text-primary">กรุณาใส่รหัสบาร์โค๊ด</h4></div>
    				<div class="col-md-7">
    					<html:text property="billbarcode" styleId="billbarcode" styleClass="form-control" value=""/>
    				</div>
    			</div>
    			<div class="row">
    				<br/>
		    			<div class="col-md-3"></div>
				    		<!--	สำหรับปุ่ม Submit-->
							<button class="btn btn-success" type="submit" name="btnsubmit" value="Submit">ยืนยัน</button>
							<!--	สำหรับปุ่ม Cancel-->
							<button class="btn btn-danger" type="submit" name="btncancel" value="Cancel">ยกเลิก</button>
		    		
    			</div>
    		</div>
    		<br/>
    		
    	</div>
    	<br/>

   
   
    </html:form>
  </body>
</html>
