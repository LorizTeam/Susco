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
	<html:form action="matTransferList" >
		<fieldset><legend><b class="blue"><br>4.7 โยกสินค้าภายในคลัง</b></legend>
		<table align="center" width="100%">
		<tr>
 			<td class="blue">คลังสินค้า &nbsp;:&nbsp;
				<html:select property="wahoCode" >				
					<html:option value="">ไม่ระบุ</html:option>
					<% if (request.getAttribute("warehouseList") != null) {
							List warehouseList = (List)request.getAttribute("warehouseList");
							for (int x=0; x<warehouseList.size(); x++) {
								WarehouseForm wahoInfo =(WarehouseForm) warehouseList.get(x);
					%>
			   		<html:option value="<%=wahoInfo.getWahoCode()%>"><%=wahoInfo.getWahoName()%></html:option>
					<% 		} 
						} 
					%>
				</html:select>			
		    </td>
		    <td class="blue">รหัสสถานที่&nbsp;:&nbsp;
 				<html:text property="locaCode" 		size="2" 	maxlength="2"/>
 			</td>
			<td class="blue">Collection&nbsp; :&nbsp; 
				<html:select property="matTypeCode" 
					onchange="materialWahoForm.matGrpCode.value='**';
				 			materialWahoForm.action='/dtac/matTransferList.do?reqCode=Search';	
				 			materialWahoForm.submit();">				
					<html:option value="">ไม่ระบุ</html:option>
					<% if (request.getAttribute("matTypeList") != null) {
							List matTypeList = (List)request.getAttribute("matTypeList");
							for (int x=0; x<matTypeList.size(); x++) {
								MaterialTypeForm matTypes =(MaterialTypeForm) matTypeList.get(x);
					%>
			   		<html:option value="<%=(String)matTypes.getMatTypeCode()%>">
		   				<%=matTypes.getMatTypeName()%>
			   		</html:option>
					<% 		} 
						} 
					%>
				</html:select>			
			</td>	
			<td class="blue">ประเภทสินค้า&nbsp; :&nbsp; 
				<html:select property="matGrpCode" >
					<html:option value="">ไม่ระบุ</html:option>
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
		    </td>	
		</tr>		
		<tr>
			<td class="blue">รหัสสินค้า&nbsp;:&nbsp;
 				<html:text property="matCode" 		size="20" 	maxlength="20"/>
 			</td>
 			<td class="blue">ชื่อสินค้า&nbsp;:&nbsp;
 				<html:text property="matName" 		size="30" 	maxlength="100"/>
 			</td>
		    <td>
		    	<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialwahoform.button.search" />
 				</html:submit>	
 			</td>
 		</tr>
 		</table>
		</fieldset> 	
		<html:hidden property="wahoName"/>
		<html:hidden property="locaName"/>
		<html:hidden property="matTypeName"/>
		<html:hidden property="matGrpName"/>
		<html:hidden property="amntQty"/>
		<html:hidden property="lotNo"/>
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightgray">
		<tr>
			<td colspan="10">&nbsp;&nbsp;
		    	<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialwahoform.button.transfer" />
 				</html:submit>	
 			</td>
		</tr>
		<tr>
			<td width="5%"  align="center" class="normal">ลำดับ</td>	
			<td width="5%"  align="center" class="normal">คลัง</td>	
			<td width="10%" align="center" class="normal">สถานที่</td>	
			<td width="15%" align="center" class="normal">รหัสสินค้า</td>		
			<td width="30%" align="center" class="normal">ชื่อสินค้า</td>
			<td width="10%" align="center" class="normal">ล๊อตที่</td>
			<td width="5%"  align="center" class="normal">ทั้งหมด</td>
			<td width="5%"  align="center" class="normal">ยืม</td>
			<td width="5%"  align="center" class="normal">จอง</td>
			<td width="5%"  align="center" class="normal">คงเหลือ</td>
		</tr>
		</table>
	
		<table align="center" width="100%" border="1" >
		<%
			if (request.getAttribute("matWaho") != null) {
				List matWaho = (List)request.getAttribute("matWaho");
				int x = 0;
				for (Iterator iter = matWaho.iterator(); iter.hasNext();) {
				  	x++;
				  	MaterialWahoForm materialWaho = (MaterialWahoForm) iter.next();				
		%>
		<tr>
			<td width="5%" 	class="normal" align="center">
				<input type="radio" name="radio1" 
			   		onclick="materialWahoForm.wahoCode.value='<%=materialWaho.getWahoCode()%>';
			   				materialWahoForm.wahoName.value='<%=materialWaho.getWahoName()%>';
			   				materialWahoForm.locaCode.value='<%=materialWaho.getLocaCode()%>';
			   				materialWahoForm.locaName.value='<%=materialWaho.getLocaName()%>';
			   				materialWahoForm.matTypeCode.value='<%=materialWaho.getMatTypeCode()%>';
			   				materialWahoForm.matTypeName.value='<%=materialWaho.getMatTypeName()%>';
			   				materialWahoForm.matGrpCode.value='<%=materialWaho.getMatGrpCode()%>';
			   				materialWahoForm.matGrpName.value='<%=materialWaho.getMatGrpName()%>';
			   				materialWahoForm.matCode.value='<%=materialWaho.getMatCode()%>';
			   				materialWahoForm.matName.value='<%=materialWaho.getMatName()%>';
			   				materialWahoForm.amntQty.value='<%=materialWaho.getAmntQty()%>';
			   				materialWahoForm.lotNo.value='<%=materialWaho.getLotNo()%>';
			   		"/>&nbsp;<%=x%>.
			 </td>			
	   		<td width="5%"  class="normal">&nbsp;<%=materialWaho.getWahoCode()%></td>	
			<td width="10%" class="normal">&nbsp;<%=materialWaho.getLocaName()%></td>	
			<td width="15%" class="normal">&nbsp;<%=materialWaho.getMatCode()%></td>		
			<td width="30%" class="normal">&nbsp;<%=materialWaho.getMatName()%></td>
			<td width="10%" class="normal">&nbsp;<%=materialWaho.getLotNo()%></td>
			<td width="5%" 	class="normal" align="right">&nbsp;<%=materialWaho.getAmntQty()%></td>
			<td width="5%" 	class="normal" align="right">&nbsp;<%=materialWaho.getBrrwQty()%></td>
			<td width="5%" 	class="normal" align="right">&nbsp;<%=materialWaho.getAlocQty()%></td>
			<td width="5%"  class="normal" align="right">&nbsp;<%=materialWaho.getAvalQty()%></td>
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="10">No Data Found</td></tr>
		<%	} %>   
		</table> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			materialWahoForm.matCode.focus()
		</SCRIPT>	
  </body>
</html:html>