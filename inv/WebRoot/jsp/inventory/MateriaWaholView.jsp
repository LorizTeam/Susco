<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
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
    <title>My JSP 'MaterialTypeList.jsp' starting page</title>
    
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
	<html:form action="materialTypeList" >
		<fieldset><legend><b class="blue">3.2 สินค้าคงเหลือ ราคาขาย จุดสั่งซื้อ</b></legend>
		<table align="center" width="90%">
        <tr>
        	<td class="blue">คลังสินค้า &nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>
		    <td class="blue">ราคาเฉลียต่อหน่วย &nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>
		    <td class="blue">ราคารับเข้าสุดท้าย &nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>
		</tr>
		<tr>
        	<td class="blue">จำนวนคงเหลือ&nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>
		    <td class="blue">จำนวนที่ถูกจอง &nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>
		    <td class="blue">คงเหลือใช้งาน&nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>
		</tr>
		<tr>
        	<td class="blue">รับเข้าครั้งสุดท้าย &nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>
		    <td class="blue">จ่ายออกครั้งสุดท้าย &nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>
		     <td class="blue">จุดสั่งซื้อต่ำสุด&nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>
		</tr>
		<tr>
        	<td class="blue">หมายเหตุ &nbsp;:&nbsp;
				<html:text property="matTypeCode" size="10" maxlength="10"/>
		    </td>

		</tr>
		<tr>
			<td align="center" class="red"><%=alertMessage%></td>
		</tr>
 		<tr>
 			<td align="center">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="mattypeform.button.search" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="mattypeform.button.adddata" />
 				</html:submit>

 			</td>
		</tr>
		</table>
		</fieldset>
 	
	</html:form>
  </body>
</html:html>