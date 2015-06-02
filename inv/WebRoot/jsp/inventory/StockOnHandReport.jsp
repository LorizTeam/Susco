<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.MatDocTypeForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
<%@ page import="com.dtac.inventory.form.WarehouseForm" %>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
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
    <title>MaterialClaimReport.jsp</title>

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
    <html:form action="stockOnHandReport" method="post">
    	<fieldset><legend><b class="blue">4.10 Stock On Hand</b></legend>
      	<table border="0" width="100%" align="center">
		<tr><td width="150">&nbsp;</td></tr>
		<tr> <td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td>
		<td class="blue">
				Collection&nbsp;:&nbsp; 
				<html:select property="matTypeCode" 
					onchange="reportForm.matGrpCode.value='';
				 			reportForm.action='/dtac/stockOnHandReport.do?reqCode=Search';	
				 			reportForm.submit();">
				<html:option value="%">ไม่ระบุ</html:option>
					<% if (request.getAttribute("matTypeList") != null) {
							List matTypeList = (List)request.getAttribute("matTypeList");
							for (int x=0; x<matTypeList.size(); x++) {
								MaterialTypeForm matTypes =(MaterialTypeForm) matTypeList.get(x);
					%>
			   		<html:option value="<%=(String)matTypes.getMatTypeCode()%>">
		   				<%=(String)matTypes.getMatTypeName()%>
			   		</html:option>
					<% 		} 
						} 
					%>
				</html:select>	
				&nbsp;&nbsp;
				Category&nbsp; :&nbsp; 
				<html:select property="matGrpCode" >
				<html:option value="%">ไม่ระบุ</html:option>
					<% if (request.getAttribute("matGrpList") != null) {
							List matGrpList = (List)request.getAttribute("matGrpList");
							for (int x=0; x<matGrpList.size(); x++) {
								MaterialTypeForm matGrps =(MaterialTypeForm) matGrpList.get(x);
					%>
			   		<html:option value="<%=(String)matGrps.getMatGrpCode()%>">
		   				<%=(String)matGrps.getMatGrpName()%>
			   		</html:option>
					<% 		} 
						} 
					%>
				</html:select>	
				&nbsp;&nbsp;
				Group&nbsp;:&nbsp;
				<html:select property="matStuffCode">	
				<html:option value="%">ไม่ระบุ</html:option>					
					<% if (request.getAttribute("stuffList") != null) {
						List stuffList = (List)request.getAttribute("stuffList");
	   					for (Iterator iterOrder = stuffList.iterator(); iterOrder.hasNext();) {
		   					MasterTableForm stuffForm = (MasterTableForm) iterOrder.next();
	       			%>
	       				<html:option value="<%=stuffForm.getTypeCode()%>"><%=stuffForm.getThName()%></html:option>
					<% 		} 
						} 
					%>	
				</html:select>	
		</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr> <td></td>
		<td class="blue">
				Date&nbsp;:&nbsp;
				<html:text property="docDate"  size="10" maxlength="10"/>
		       	&nbsp;&nbsp;&nbsp;<font color="black">(dd/mm/yyyy)</font>
		</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr> <td></td>
		<td class="blue">
				<html:radio value="01" property="stockOnHand">&nbsp;Stock On Hand</html:radio>&nbsp;&nbsp;
				<html:radio value="02" property="stockOnHand">&nbsp;Stock On Hand (Transection)</html:radio>
		</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr>  <td>&nbsp;</td>
		 <td> <html:submit property="reqCode">
					<bean:message bundle="inventoryResources" key="report.button.print" />
			  </html:submit>
			  &nbsp;&nbsp;
				<html:submit property="reqCode">
					<bean:message bundle="inventoryResources" key="report.button.excel" />
				</html:submit>
		 </td>
		</tr>		
		<tr><td>&nbsp;</td></tr>
		<tr>  
			<td colspan="2" align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
		<tr>  
			<td></td>
		</tr>
		</table>
		</fieldset>
	</html:form>
  </body>
</html:html>