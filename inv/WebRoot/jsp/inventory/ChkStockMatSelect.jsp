<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.ChkStockForm" %>
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
		
	String lastTab = "0";
	if (request.getAttribute("lastTab") != null) 
		lastTab = (String) request.getAttribute("lastTab"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
	<script src="/dtac/SpryTabbedPanels.js" type="text/javascript"></script>
	<link href="/dtac/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/dtac/jsp/js/tooltip_hiso/style.css"/>
	<script type="text/javascript" src="/dtac/jsp/js/tooltip_hiso/script.js"></script>  
  </head>
  
  <body class="blueback">
	<html:form action="chkStockMatSelect" >
		<fieldset><legend><b class="blue"><br>4.6 เช็คสต็อก</b></legend>
		<table align="center" width="90%">
		<tr>
			<td class="normal">ปี &nbsp;:&nbsp;  
 				<html:text property="docYear" size="4" maxlength="4" readonly="true" />
			</td> 
			<td class="normal">เดือน&nbsp;:&nbsp;  
 				<html:text property="docMonth" size="2" maxlength="2" readonly="true" />
 			</td>		
 			<td class="normal">คลังสินค้า &nbsp;:&nbsp;
				<html:text property="wahoCode" size="2" maxlength="2" readonly="true" />
				&nbsp;-&nbsp;
				<html:text property="wahoName" size="30" maxlength="40" readonly="true" />			
		 	</td>
		</tr>
		<tr><td align="center" class="red" colspan="9">&nbsp;<%=alertMessage%></td></tr>
 		</table>
		</fieldset> 
		<fieldset>
		<table align="center" width="90%">
		<tr>
			<td class="blue">ล๊อตที่ &nbsp;:&nbsp;  
 				<html:text property="lotNo" size="10" maxlength="10"/>
			</td> 
			<td class="blue">สถานที่ &nbsp;:&nbsp;
				<html:select property="locaCode" >
					<html:option value="">ไม่ระบุ</html:option>
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
 			<td class="blue">รหัสสินค้า &nbsp;:&nbsp;
				<html:text property="matCode" size="20" maxlength="20"/>
		 	</td>
		 	<td class="blue">ชื่อสินค้า &nbsp;:&nbsp;
				<html:text property="matName" size="30" maxlength="80"/>
		 	</td>
		 </tr>
		 <tr>
		 	<td>&nbsp;</td>
		 	<td><html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="checkstockform.button.search" />
 				</html:submit>
 			</td>
		 	<td class="blue">ประเภทสินค้า &nbsp;:&nbsp;
				<html:select property="matTypeCode" 
					onchange="chkStockForm.matGrpCode.value='**';
				 			chkStockForm.action='/dtac/chkStockMatSelect.do?reqCode=Search';	
				 			chkStockForm.submit();">				
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
			<td class="blue">ประเภทสินค้าย่อย &nbsp;:&nbsp;
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
		</table>
		</fieldset>	
		
		<div id="TabbedPanels1" class="TabbedPanels">
		<ul class="TabbedPanelsTabGroup">
			<li class="TabbedPanelsTab" tabindex="0">เลือกสินค้า</li>
			<li class="TabbedPanelsTab" tabindex="1">กรอกผลการตรวจนับ</li>
		</ul>
		
		<div class="TabbedPanelsContentGroup">
			<div class="TabbedPanelsContent">
				<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
			 	<tr>
			 		<td width="47.5%">
				 		<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightslategray">
						<tr>
							<td align="center" width="100%" colspan="6">สินค้าทั้งหมด</td>
						</tr>
						<tr>
							<td width="6%"  class="normal" align="center">ลำดับ</td>
							<td width="6%"  class="normal" align="center">สถานที่</td>
							<td width="20%" class="normal" align="center">รหัสสินค้า</td>
							<td width="30%" class="normal" align="center">ชื่อสินค้า</td>
							<td width="10%" class="normal" align="center">ล๊อตที่</td>							
						</tr>
						</table>
					</td>
				 	<td width="5%">&nbsp;</td>
				 	<td width="47.5%">
						<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightslategray">
						<tr>
							<td align="center" width="100%" colspan="6">สินค้าที่ต้องการตรวจนับ</td>
						</tr>
						<tr>
							<td width="6%"  class="normal" align="center">ลำดับ</td>
							<td width="6%"  class="normal" align="center">สถานที่</td>
							<td width="20%" class="normal" align="center">รหัสสินค้า</td>
							<td width="30%" class="normal" align="center">ชื่อสินค้า</td>
							<td width="10%" class="normal" align="center">ล๊อตที่</td>							
						</tr>
						</table>
					</td>
			 	</tr>
			 	</table>
				 	
				<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">	
				<tr>
				 	<td width="47.5%" valign="top">
					 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%">
						<%	if (request.getAttribute("matWahoList") != null) {
								List matWahoList = (List)request.getAttribute("matWahoList");
								int x = 0;
								for (Iterator iter = matWahoList.iterator(); iter.hasNext();) {
						  			x++;
									ChkStockForm matWaho = (ChkStockForm) iter.next();
						%>
						<tr>
							<td width="6%" class="normal" align="center"><%=x%>.</td>
							<td width="6%"  class="normal" align="center">&nbsp;<%=matWaho.getLocaName()%></td>
							<td width="20%" class="normal">&nbsp;
								<input type="checkbox" name="wahoSelectList" 
									value="<%=matWaho.getLocaCode()%><%=matWaho.getLotNo()%><%=matWaho.getMatCode()%>"/>
								&nbsp;<%=matWaho.getMatCode()%>
							</td>
							<td width="30%" class="normal">&nbsp;<%=matWaho.getMatName()%></td>
							<td width="10%" class="normal" align="center">&nbsp;<%=matWaho.getLotNo()%></td>							
						</tr>
						<%		}
							} else { 
						%>		      
						<tr><td align="center" colspan="6">No Data Found</td></tr>	   		  		      		      		      		      
						<% } %>	
						</table>
					</td>
					
				 	<td width="5%" valign="baseline">
				 		<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" >
				  		<tr>
				  			<td align="center">
				  				<html:submit property="reqCode"> 
				 					<bean:message bundle="inventoryResources" key="checkstockform.button.add" />
				 				</html:submit>
				 			</td>
				 		</tr>
				 		<tr><td>&nbsp;</td></tr>
				 		<tr>
				 			<td align="center">
				 				<html:submit property="reqCode"> 
				 					<bean:message bundle="inventoryResources" key="checkstockform.button.remove" />
				 				</html:submit>
				 			</td>
				 		</tr>
				 		</table>
				 	</td>
				 	
					<td width="47.5%" valign="top">
						<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" >
						<%	if (request.getAttribute("chkStockList") != null) {
								List chkStockList = (List)request.getAttribute("chkStockList");
								int x = 0;
								for (Iterator iter = chkStockList.iterator(); iter.hasNext();) {
						  			x++;
									ChkStockForm chkStock = (ChkStockForm) iter.next();
						%>
						<tr>
							<td width="6%"  class="normal" align="center">&nbsp;<%=x%>.</td>
							<td width="6%"  class="normal" align="center">&nbsp;<%=chkStock.getLocaName()%></td>
							<td width="20%" class="normal">&nbsp;
								<input type="checkbox" name="chkStockSelectList" 
									value="<%=chkStock.getLocaCode()%><%=chkStock.getLotNo()%><%=chkStock.getMatCode()%>"/>
								&nbsp;<%=chkStock.getMatCode()%>
							</td>
							<td width="30%" class="normal">&nbsp;<%=chkStock.getMatName()%></td>
							<td width="10%" class="normal" align="center">&nbsp;<%=chkStock.getLotNo()%></td>							
						</tr>
						<%		}
							} else { 
						%>		      
						<tr><td align="center" colspan="6">No Data Found</td></tr>	   		  		      		      		      		      
						<% } %>	
						</table>
					</td>
				</tr>
				</table>
			</div>
			<div class="TabbedPanelsContent">
				<table width="100%">
				<tr>
					<td width="100%" align="right">
						<html:submit property="reqCode"> 
	 					<bean:message bundle="inventoryResources" key="checkstockform.button.update" />
	 					</html:submit>
 					</td>
				</tr>
				</table>
				
				<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightslategray">
				<tr>
					<td width="5%"  class="normal" align="center">ลำดับที่</td>
					<td width="10%" class="normal" align="center">สถานที่</td>
					<td width="20%" class="normal" align="center">รหัสสินค้า</td>
					<td width="30%" class="normal" align="center">ชื่อสินค้า</td>
					<td width="10%" class="normal" align="center">ล๊อตที่</td>					
					<td width="10%" class="normal" align="center">จำนวนที่นับได้</td>
				</tr>
				</table>
				
				<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" >
				<%	if (request.getAttribute("chkStockList") != null) {
						List chkStockList = (List)request.getAttribute("chkStockList");
						int x = 0;
						for (Iterator iter = chkStockList.iterator(); iter.hasNext();) {
				 			x++;
							ChkStockForm chkStock = (ChkStockForm) iter.next();
				%>
				<tr>
					<td width="5%"  class="normal" align="center">&nbsp;<%=x%>.</td>
					<td width="10%" class="normal" align="center">&nbsp;<%=chkStock.getLocaName()%></td>
					<td width="20%" class="normal">&nbsp;<%=chkStock.getMatCode()%></td>
					<td width="30%" class="normal">&nbsp;<%=chkStock.getMatName()%></td>
					<td width="10%" class="normal" align="center">&nbsp;<%=chkStock.getLotNo()%></td>					
					<td width="10%" class="normal" align="center">&nbsp;
						<INPUT type="text" value="<%=chkStock.getChkStockQty() %>" size="6" maxlength="10" name="chkStockQtyList" />
					</td>
				</tr>
				<%		}
					} else { %>		      
				<tr><td align="center" colspan="6">No Data Found</td></tr>	   		  		      		      		      		      
				<% } %>	
				</table>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var lastTab1 = "<%=lastTab%>";
			
			var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", { defaultTab: <%=lastTab%> });
		</script>
	</html:form>
	
  </body>
</html:html>