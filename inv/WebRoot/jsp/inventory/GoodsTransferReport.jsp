<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.MatDocTypeForm" %>
<%@ page import="com.dtac.inventory.form.WarehouseForm" %>
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
    <html:form action="goodsTransferReport" method="post">
    	<fieldset><legend><b class="blue">5.13 Goods Transfer Report</b></legend>
      	<table border="0" width="80%" align="center">
		<tr><td>&nbsp;</td></tr>
		<tr>
 			<td class="blue">1. Document Type &nbsp;:&nbsp;
      			<html:select property="docTypeCode" >
	      			<%
		        		if (request.getAttribute("matDocTypeList") != null) {
			        		List matDocTypeList = (List)request.getAttribute("matDocTypeList");
			        		for (Iterator iterMonth = matDocTypeList.iterator(); iterMonth.hasNext();) {
			        			MatDocTypeForm matDocInfo = (MatDocTypeForm) iterMonth.next();
	      			%>
	      			<html:option value="<%=matDocInfo.getDocTypeCode()%>">
	       			 <%=matDocInfo.getDocTypeCode()%> - <%=matDocInfo.getDocTypeName()%>
	       			</html:option>
					<%		} 
						}
					%>
				</html:select>
		    	&nbsp;&nbsp;&nbsp;2. Warehouse&nbsp;:&nbsp; 
				<html:select property="wahoCode">
	      			<%
		        		if (request.getAttribute("warehouseList") != null) {
			        		List warehouseList = (List)request.getAttribute("warehouseList");
			        		for (Iterator iterWaho = warehouseList.iterator(); iterWaho.hasNext();) {
			        			WarehouseForm wahoInfo = (WarehouseForm) iterWaho.next();
	      			%>
	       			<html:option value="<%=wahoInfo.getWahoCode()%>">
	       			 	<%=wahoInfo.getWahoCode()%> - <%=wahoInfo.getWahoName()%>
	       			</html:option>
					<%		} 
						}
					%>
				</html:select>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
        	<td class="blue">3. ตั้งแต่วันที่ &nbsp;:&nbsp;
		       	<html:text property="fromDate"  size="10" maxlength="10"/>

		       	&nbsp;&nbsp;&nbsp;&nbsp;4. ถึงวันที่ &nbsp;:&nbsp;
		       	<html:text property="toDate"  size="10" maxlength="10"/>
		       	&nbsp;&nbsp;&nbsp;<font color="black">(dd/mm/yyyy)</font>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>		
		    <td class="blue">5. สถานะเอกสาร &nbsp;:&nbsp; 
 				<html:select property="docStatus" >				
 					<html:option value="">ไม่ระบุ</html:option>	
			   		<html:option value="AC">ดำเนินการ</html:option>
					<html:option value="CA">ยกเลิก</html:option>					
					<html:option value="CL">เสร็จสิ้นแล้ว</html:option>
				</html:select> 	
			</td>		
		</tr>
		<tr><td>&nbsp;</td></tr>		
		<tr>      
			<td class="blue">&nbsp;&nbsp;รายงานที่ต้องการ &nbsp;:&nbsp;</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     			<html:radio value="1" property="reportNo">&nbsp;&nbsp;1.รายละเอียดการรับสินค้า (กรอกข้อ [1],[2],3,4,[5]) </html:radio>
        	</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     			<html:radio value="2" property="reportNo">&nbsp;&nbsp;2.สรุปการรับสินค้า   เรียงตามเลขที่ใบรับ (กรอกข้อ [1],[2],3,4,[5])</html:radio>
        	</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     			<html:radio value="3" property="reportNo">&nbsp;&nbsp;3.สรุปการรับสินค้า   เรียงตามรหัสสินค้า (กรอกข้อ [1],[2],3,4,[5])</html:radio>
        	</td>
        </tr>

		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;		
				<html:submit property="reqCode">
					<bean:message bundle="inventoryResources" key="materialreceiveform.button.print" />
				</html:submit>
			</td>
		</tr>
		</table>
		</fieldset>
	</html:form>
  </body>
</html:html>