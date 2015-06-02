<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.MaterialTakeForm"%>
<%@ include file="../admin/css_blue_style.css" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'MatTransferList.jsp' starting page</title>
    
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
	<html:form action="materialTakeAddList" >
		<fieldset><legend><b class="blue"><br>4.1  เพิ่ม สินค้า</b></legend>
		<table align="center" width="100%">
		  <tr>
			<td class="blue">รหัสเอกสาร&nbsp;:&nbsp;
			<html:text property="docno" size="15" maxlength="20"/>
			&nbsp;&nbsp;&nbsp;&nbsp;วันที่&nbsp;:&nbsp;
			<html:text property="docdate" size="15" maxlength="20"/>
			</td></tr>
			<tr>
			<td class="blue"><br/>
			&nbsp;&nbsp;รหัสผู้เบิกสินค้า&nbsp;:&nbsp;
			<html:text property="empno" size="15" maxlength="20"/>
			&nbsp;&nbsp;ชื่อผู้เบิกสินค้า&nbsp;:&nbsp;
			<html:text property="empname" size="15" maxlength="20"/>
			 &nbsp;&nbsp;แผนก&nbsp;:&nbsp;
			<html:select property="takedepart">
			<html:option value="B1" /><html:option value="B2" /></html:select>
			</td></tr>
			<tr>
			<td width="15%" class="normal"><br/>
			<html:submit property="reqCode" value="Add Data"> 
						<bean:message bundle="inventoryResources" key="materialtakeform.button.adddata" />
 				</html:submit>
			</td>
			</tr>
			</table>
			</fieldset>
	</html:form>
	 		
  </body>
</html:html>