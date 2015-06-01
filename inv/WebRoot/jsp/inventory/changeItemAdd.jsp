<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.changeItemAddForm" %>
<%@ include file="../admin/css_blue_style.css" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CollectionAdd.jsp' starting page</title>
    
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
  <html:form action="changeItemAdd" >
		<fieldset><legend><b class="blue"> เปลี่ยนสินค้า</b></legend>
		<table align="center" width="60%">
		<tr>
		   <td class="blue">รูปแบบการจัดการ &nbsp; : &nbsp;
		   <html:select property="docType" >
					<html:option value="recv">รับคืน</html:option>
				</html:select>	 <br/></td> 
		</tr>
		<tr>
		   <td class="blue">รหัสสินค้า &nbsp; :&nbsp; <html:text property="prCode" ></html:text>&nbsp;
		   <html:submit property="reqCode"> 
					<bean:message bundle="inventoryResources" key="change.button.checkItem" />
 		</html:submit></td>
		</tr>
		<%if(request.getAttribute("item") != null){
		if(request.getAttribute("item") == "notfound"){
		%><tr>
		<td class="red">&nbsp;ไม่พบสินค้า</td>
		</tr><%
		}else{
		Map item = (Map)request.getAttribute("item");
		 %>
		 <tr>
		<td class="blue">ชื่อสินค้า :&nbsp; <%=item.get("itemname") %></td>
		</tr>
		<tr>
		<td class="blue">Collection :&nbsp; <%=item.get("collection") %></td>
		</tr>
		<%}} %>
		<tr>
		<td class="blue">เลขเอกสาร :&nbsp; <html:text property="docCode" ></html:text></td>
		</tr>
		<br/>
		<td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<html:submit property="reqCode"> 
					<bean:message bundle="inventoryResources" key="change.button.recv" />
 		</html:submit>
		</td>
		</table>
		</fieldset>
    <br>
    </html:form>
  </body>
</html>
