<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTakeForm" %>
<%@ page import="com.dtac.inventory.form.MatDocTypeForm" %>
<%@ page import="com.dtac.admin.form.MonthForm" %>
<%@ page import="com.dtac.admin.form.YearForm" %>
<%@ include file="../admin/css_blue_style.css" %>
<%@ page import="com.dtac.inventory.form.WarehouseForm" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";	
	String userName = (String) session.getAttribute("userName");
	
	String docTypeCode = "";
	if (request.getAttribute("docTypeCode") != null) docTypeCode = (String) request.getAttribute("docTypeCode"); 
	
	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'MaterialRecvHDList.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<script Language="javascript">
	function getVender() {
		var load = window.open('/inv/venderSearchSetup.do?formName=materialTake','materialRecv',
		                   'scrollbars=yes,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}	
	function getCustomer() {
		var load = window.open('/inv/customerSearchSetup.do?formName=materialTake','materialTake',
		                   'scrollbars=yes,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}	
	function getEmployee() {
		var load = window.open('/inv/employeeSearchSetup.do?formName=materialTake','materialTake',
		                   'scrollbars=yes,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}
	</script>
  </head>
  
  <body class="blueback">
	<html:form action="/materialTakeList" >
		<fieldset>
		<legend><b class="normal">3.1 Delivery Order (ใบส่งสินค้า)</b>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font class="normal"><%=userName%></font></legend>
		
		<table align="center" width="100%" >
		<tr>
 			<td class="black">Document Type&nbsp;:&nbsp;
      			<html:select property="docTypeCode" >
	      			<%
		        		if (request.getAttribute("matDocTypeList") != null) {
			        		List matDocTypeList = (List)request.getAttribute("matDocTypeList");
			        		for (Iterator iterMonth = matDocTypeList.iterator(); iterMonth.hasNext();) {
			        			MatDocTypeForm matDocInfo = (MatDocTypeForm) iterMonth.next();
	      			%>
	      			<html:option value="<%=matDocInfo.getDocTypeCode()%>">
	       			 	<%=matDocInfo.getDocTypeCode()%>&nbsp; <%=matDocInfo.getDocTypeName()%>
	       			</html:option>
					<%		} 
						}
					%>
				</html:select>							
		    	&nbsp;&nbsp;Year&nbsp;:&nbsp;
       			<html:select property="docYear" >
       				<html:option value="">N/A</html:option>
	       			<%
	   			        if (request.getAttribute("yearList") != null) {
			        		List yearList = (List)request.getAttribute("yearList");
			        		for (Iterator iterYear = yearList.iterator(); iterYear.hasNext();) {
		        		  		YearForm yearForm = (YearForm) iterYear.next();
	       			%>
        			<html:option value="<%=yearForm.getYear()%>"><%=yearForm.getYear()%></html:option>
					<%		} 
						}
					%>
			  	</html:select>
			  	&nbsp;&nbsp;&nbsp;Month &nbsp;:&nbsp;
				<html:select property="docMonth" >
					<html:option value="">N/A</html:option>	
	       			<%
	   			        if (request.getAttribute("monthList") != null) {
			        		List monthList = (List)request.getAttribute("monthList");
			        		for (Iterator iterMonth = monthList.iterator(); iterMonth.hasNext();) {
			        			MonthForm monthInfo = (MonthForm) iterMonth.next();
	       			%>
        			<html:option value="<%=monthInfo.getMonth()%>"><%=monthInfo.getMonth()%></html:option>
					<%		} 
						}
					%>
			  	</html:select>			  	
		    	
		    	  <html:hidden property="docNo" />
		    		
		    </td>
			<td class="black">Customer Code&nbsp;:&nbsp;   
 				<html:text property="vendCode" 	size="8" 	maxlength="10"/>&nbsp;&nbsp;&nbsp;
 				<a href="javascript:getCustomer();"> <img src="jsp/icons/edit.gif" /> </a> 
 				&nbsp; Customer Name&nbsp;:&nbsp;   
 				<html:text property="vendName" 	size="15" 	maxlength="100"/>
 			</td>	
 			<td class="black">&nbsp;
		    </td>	
 		</tr>
 		<tr><td Class="black" colspan="2">
				Document Date&nbsp;:&nbsp;
				<html:text property="date" size="10" maxlength="10"/>&nbsp;&nbsp;<font color="black">dd/mm/yyyy</font>
				&nbsp; &nbsp; 
				Warehouse&nbsp;:&nbsp; 
				<html:select property="wahoCode" >
	      			<%
		        		if (request.getAttribute("warehouseAuth") != null) {
			        		List warehouseAuth = (List)request.getAttribute("warehouseAuth");
			        		for (Iterator iterWaho = warehouseAuth.iterator(); iterWaho.hasNext();) {
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
 		<tr>
 			<td class="red">&nbsp;<%=alertMessage%>
 			<html:hidden property="refOrdTypeCode"/>
 			</td>
 			<td>
 			
			<html:hidden property="refOrdYear"/>
			<html:hidden property="refOrdNo"/>
			</td>
 			<td align="right">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialtakeform.button.adddata" />
 				</html:submit>
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialtakeform.button.search" />
 				</html:submit>
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialtakeform.button.view" />
 				</html:submit>
 			</td>
 		</tr>		
 		
		</table>
		</fieldset> 	
		
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightgray">

		<tr>	
			<td width="10%"  align="center" class="normal">No</td>		
			<td width="15%" align="center" class="normal">Document Number</td>
			<td width="5%"  align="center" class="normal">Month</td>
			<td width="10%" align="center" class="normal">Document Date</td>
			<td width="15%" align="center" class="normal">Warehouse</td>			
			<td width="25%" align="center" class="normal">Customer<br></td>
			<td width="20%" align="center" class="normal">Employee</td>
		</tr>
		</table>
	
		<table align="center" width="100%" border="1">
		<%	if (request.getAttribute("matTakeHDList") != null) {
				List matTakeHDList = (List)request.getAttribute("matTakeHDList");
				int x = 0;
				for (Iterator iter = matTakeHDList.iterator(); iter.hasNext();) {
		  			x++;
		  			MaterialTakeForm matTakeForm = (MaterialTakeForm) iter.next();
		%>
		<tr>			
	   		<td width="10%" class="normal">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="radio1" 
			   		onclick="materialTakeForm.docTypeCode.value='<%=matTakeForm.getDocTypeCode()%>';
			   				materialTakeForm.docYear.value='<%=matTakeForm.getDocYear()%>';
			   				materialTakeForm.docMonth.value='<%=matTakeForm.getDocMonth()%>';
			   				materialTakeForm.vendCode.value='<%=matTakeForm.getVendCode()%>';
			   				materialTakeForm.vendName.value='<%=matTakeForm.getVendName()%>';
			   				materialTakeForm.docNo.value='<%=matTakeForm.getDocNo()%>';
			   			
			   				"/>&nbsp;&nbsp;<%=x%>
	   		</td>	   		
		    <td width="15%" class="normal" align="center">&nbsp;<%=matTakeForm.getDocTypeCode()%>&nbsp;-&nbsp;<%=matTakeForm.getDocYear()%>
		    	&nbsp;/&nbsp;<%=matTakeForm.getDocNo()%>
		    </td>
		    <td width="5%"  class="normal" align="center"><%=matTakeForm.getDocMonth()%></td>	   		  		      	   		  
		    <td width="10%" class="normal" align="center"><%=matTakeForm.getDate()%></td>
		    <td width="15%" class="normal" align="center"><%=matTakeForm.getWahoName()%>&nbsp;</td>
		    <td width="25%" class="normal" align="center"><%=matTakeForm.getVendCode()%>&nbsp;-&nbsp;<%=matTakeForm.getVendName()%></td>
		    <td width="20%" class="normal" align="center"><%=matTakeForm.getDocbyname()%></td>
		    <% } %>
	    </tr>  
	 	<%		
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>  
		</table> 	
	</html:form>
	 	 	
  </body>
</html:html>