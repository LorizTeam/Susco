<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.MaterialForm" %>
<%@ page import="com.dtac.inventory.form.WarehouseForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
<%@ page import="com.dtac.inventory.form.MaterialWahoForm" %>
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
	<html:form action="matTransferInput" >
		<fieldset><legend><b class="blue"><br>4.7 โยกสินค้าภายในคลัง</b></legend>
		<table align="center" width="90%">
		<tr>
 			<td class="normal">คลังสินค้า &nbsp;:&nbsp;
				<html:text property="wahoCode" 		size="2" 	maxlength="2" readonly="true"/>
				&nbsp;-&nbsp;
				<html:text property="wahoName" 		size="30" 	maxlength="40" readonly="true"/>			
		    </td>
		    <td class="normal">สถานที่&nbsp;:&nbsp;
 				<html:text property="locaCode" 		size="2" 	maxlength="2" readonly="true"/>
 				&nbsp;-&nbsp;
 				<html:text property="locaName" 		size="30" 	maxlength="50" readonly="true"/>
 			</td>
		</tr>		
		<tr>
			<td class="normal">รหัสสินค้า&nbsp;:&nbsp;
 				<html:text property="matCode" 		size="20" 	maxlength="20" readonly="true"/>
 			</td>
 			<td class="normal">ชื่อสินค้า&nbsp;:&nbsp;
 				<html:text property="matName" 		size="30" 	maxlength="100" readonly="true"/>
 			</td>
 		</tr>
 		<tr>
 			<td class="normal">จำนวนทั้งหมด&nbsp;:&nbsp;
 				<html:text property="amntQty" 		size="10" 	maxlength="13" readonly="true"/>
 			</td>
 			<td class="blue">จำนวนโอน&nbsp;:&nbsp;
 				<html:text property="transferQty" 		size="10" 	maxlength="13"/>
 			</td>
 		</tr>
 		<tr>
 			<td class="normal">ล๊อตที่&nbsp;:&nbsp;
 				<html:text property="lotNo" 		size="10" 	maxlength="10" readonly="true"/>
 			</td>
 			<td class="blue">โอนไปยัง &nbsp;:&nbsp;
				<html:select property="transferToLocaCode" >
	      			<%
		        		if (request.getAttribute("locaList") != null) {
			        		List locaList = (List)request.getAttribute("locaList");
			        		for (Iterator iterMonth = locaList.iterator(); iterMonth.hasNext();) {
			        			WarehouseForm locaInfo = (WarehouseForm) iterMonth.next();
	      			%>
	       			<html:option value="<%=locaInfo.getLocaCode()%>">
	       			 	<%=locaInfo.getLocaCode()%> - <%=locaInfo.getLocaName()%>
	       			</html:option>
					<%		} 
						}
					%>
				</html:select>
		   	</td>
 		</tr>
 		<tr><td align="center" class="red" colspan="9">&nbsp;<%=alertMessage%></td></tr>
 		<tr>
 			<td colspan="5" align="center">
		    	<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialwahoform.button.transfer" />
 				</html:submit>&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialwahoform.button.cancel" />
 				</html:submit>	
 			</td>
 		</tr>
 		</table>
		</fieldset> 	

	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			materialWahoForm.transferQty.focus()
		</SCRIPT>	
  </body>
</html:html>