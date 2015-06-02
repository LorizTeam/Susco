<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.inventory.form.MaterialForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
<%@ include file="../admin/css_blue_style.css" %>
<%
 	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 
		
	String docTypeCode = "";
	if (session.getAttribute("docTypeCode") != null) docTypeCode = (String) session.getAttribute("docTypeCode");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>ค้นหาสินค้า</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
	<script language="javascript">
	function returnMaterial(tMatCode, tMatName, tMatTypeCode, tMatTypeName, tmatGrpCode, tmatGrpName, trefMatCode, trefMatName, tmatStuffCode, tmatStuffName,
	tmatColorCode, tmatColorName, trUnit, trUnitName, tmatBrandCode, tmatBrandName, tstock1, tstock2, tnormalPrice, tempPrice, tcostPrice, tspecialPrice, tvipPrice, tSerial) {
		
		if (opener && !opener.closed){
			if (materialForm.formName.value == 'umbrella') {
			 	window.opener.document.umbrellaForm.matCode.value = tMatCode;
			 	window.opener.document.umbrellaForm.matName.value = tMatName;
			 	
		 	} else if (materialForm.formName.value == 'quotation') {
			 	window.opener.document.quotationForm.matCode.value = tMatCode;
			 	window.opener.document.quotationForm.matName.value = tMatName;
			 	
		 	} else if (materialForm.formName.value == 'saleOrder') {
			 	window.opener.document.saleOrderForm.matCode.value = tMatCode;
			 	window.opener.document.saleOrderForm.matName.value = tMatName;
			 	
		 	} else if (materialForm.formName.value == 'pr') {
			 	window.opener.document.prForm.matCode.value = tMatCode;
			 	window.opener.document.prForm.matName.value = tMatName;
			 	
		 	} else if (materialForm.formName.value == 'materialRecv') {
			 	window.opener.document.materialRecvForm.matCode.value 			= tMatCode;
			 	window.opener.document.materialRecvForm.matName.value 			= tMatName;
			 	window.opener.document.materialRecvForm.matTypeCode.value 		= tMatTypeCode;
			 	window.opener.document.materialRecvForm.matTypeName.value 		= tMatTypeName;
			 	window.opener.document.materialRecvForm.matGrpCode.value 		= tmatGrpCode;
			 	window.opener.document.materialRecvForm.matGrpName.value 		= tmatGrpName;
			 	window.opener.document.materialRecvForm.refMatCode.value 		= trefMatCode;
			 	window.opener.document.materialRecvForm.refMatName.value 		= trefMatName;
			 	window.opener.document.materialRecvForm.matStuffCode.value 		= tmatStuffCode;
			 	window.opener.document.materialRecvForm.matStuffName.value 		= tmatStuffName;
			 	window.opener.document.materialRecvForm.matColorCode.value 		= tmatColorCode;
			 	window.opener.document.materialRecvForm.matColorName.value 		= tmatColorName;
			 	window.opener.document.materialRecvForm.rUnit.value 			= trUnit;
			 	window.opener.document.materialRecvForm.rUnitName.value 		= trUnitName;
		 	 	window.opener.document.materialRecvForm.matBrandCode.value 		= tmatBrandCode;
		 	 	window.opener.document.materialRecvForm.matBrandName.value 		= tmatBrandName;
		 	 	window.opener.document.materialRecvForm.stock1.value 			= tstock1;
		 	 	window.opener.document.materialRecvForm.stock2.value 			= tstock2;
		 	 	window.opener.document.materialRecvForm.normalPrice.value 		= tnormalPrice;
			 	window.opener.document.materialRecvForm.empPrice.value 			= tempPrice;
		 	 	window.opener.document.materialRecvForm.costPrice.value 		= tcostPrice;
		 	 	window.opener.document.materialRecvForm.specialPrice.value 		= tspecialPrice;
		 	 	window.opener.document.materialRecvForm.vipPrice.value 			= tvipPrice;
			
			 } else if (materialForm.formName.value == 'materialTake') {
			 	window.opener.document.materialTakeForm.matCode.value 			= tMatCode; 
			 	window.opener.document.materialTakeForm.matTypeName.value 		= tMatName;
			 	window.opener.document.materialTakeForm.refMatName.value 		= trefMatName;
			 	window.opener.document.materialTakeForm.stock1.value 			= tstock1;
			 	window.opener.document.materialTakeForm.matColorName.value   	= tmatColorName;
			 	window.opener.document.materialTakeForm.matStuffName.value 	    = tSerial;
			 	
			 	
		 	} else if (materialForm.formName.value == 'materialTransfer') {
			 	window.opener.document.materialTransferForm.matCode.value 			= tMatCode;
			 	window.opener.document.materialTransferForm.matName.value 			= tMatName;
			 	window.opener.document.materialTransferForm.matTypeCode.value 		= tMatTypeCode;
			 	window.opener.document.materialTransferForm.matTypeName.value 		= tMatTypeName;
			 	window.opener.document.materialTransferForm.matGrpCode.value 		= tmatGrpCode;
			 	window.opener.document.materialTransferForm.matGrpName.value 		= tmatGrpName;
			 	window.opener.document.materialTransferForm.refMatCode.value 		= trefMatCode;
			 	window.opener.document.materialTransferForm.refMatName.value 		= trefMatName;
			 	window.opener.document.materialTransferForm.matStuffCode.value 		= tmatStuffCode;
			 	window.opener.document.materialTransferForm.matStuffName.value 		= tmatStuffName;
			 	window.opener.document.materialTransferForm.matColorCode.value 		= tmatColorCode;
			 	window.opener.document.materialTransferForm.matColorName.value 		= tmatColorName;
			 	window.opener.document.materialTransferForm.rUnit.value 			= trUnit;
			 	window.opener.document.materialTransferForm.rUnitName.value 		= trUnitName;
		 	 	window.opener.document.materialTransferForm.matBrandCode.value 		= tmatBrandCode;
		 	 	window.opener.document.materialTransferForm.matBrandName.value 		= tmatBrandName;
		 	 	window.opener.document.materialTransferForm.stock1.value 			= tstock1;
		 	 	window.opener.document.materialTransferForm.stock2.value 			= tstock2;
		 	 	window.opener.document.materialTransferForm.stock3.value 			= tstock1;
		 	 	window.opener.document.materialTransferForm.stock4.value 			= tstock2;
		 	 	window.opener.document.materialTransferForm.normalPrice.value 		= tnormalPrice;
			 	window.opener.document.materialTransferForm.empPrice.value 			= tempPrice;
		 	 	window.opener.document.materialTransferForm.costPrice.value 		= tcostPrice;
		 	 	window.opener.document.materialTransferForm.specialPrice.value 		= tspecialPrice;
		 	 	window.opener.document.materialTransferForm.vipPrice.value 			= tvipPrice;
			 	
		 	} 
		 	else if (materialForm.formName.value == 'materialRetu') {
			 	window.opener.document.materialRetuForm.matCode.value 			= tMatCode;
			 	window.opener.document.materialRetuForm.matName.value 			= tMatName;
			 	window.opener.document.materialRetuForm.matTypeCode.value 		= tMatTypeCode;
			 	window.opener.document.materialRetuForm.matTypeName.value 		= tMatTypeName;
			 	window.opener.document.materialRetuForm.matGrpCode.value 		= tmatGrpCode;
			 	window.opener.document.materialRetuForm.matGrpName.value 		= tmatGrpName;
			 	window.opener.document.materialRetuForm.refMatCode.value 		= trefMatCode;
			 	window.opener.document.materialRetuForm.refMatName.value 		= trefMatName;
			 	window.opener.document.materialRetuForm.matStuffCode.value 		= tmatStuffCode;
			 	window.opener.document.materialRetuForm.matStuffName.value 		= tmatStuffName;
			 	window.opener.document.materialRetuForm.matColorCode.value 		= tmatColorCode;
			 	window.opener.document.materialRetuForm.matColorName.value 		= tmatColorName;
			 	window.opener.document.materialRetuForm.rUnit.value 			= trUnit;
			 	window.opener.document.materialRetuForm.rUnitName.value 		= trUnitName;
		 	 	window.opener.document.materialRetuForm.matBrandCode.value 		= tmatBrandCode;
		 	 	window.opener.document.materialRetuForm.matBrandName.value 		= tmatBrandName;
		 	 	window.opener.document.materialRetuForm.stock1.value 			= tstock1;
		 	 	window.opener.document.materialRetuForm.stock2.value 			= tstock2;
		 	 	window.opener.document.materialRetuForm.normalPrice.value 		= tnormalPrice;
			 	window.opener.document.materialRetuForm.empPrice.value 			= tempPrice;
		 	 	window.opener.document.materialRetuForm.costPrice.value 		= tcostPrice;
		 	 	window.opener.document.materialRetuForm.specialPrice.value 		= tspecialPrice;
		 	 	window.opener.document.materialRetuForm.vipPrice.value 			= tvipPrice;
		 	 	
		 	 	} else if (materialForm.formName.value == 'materialClm') {
			 	window.opener.document.materialRetuForm.matCode.value 			= tMatCode;
			 	window.opener.document.materialRetuForm.matName.value 			= tMatName;
			 	window.opener.document.materialRetuForm.matTypeCode.value 		= tMatTypeCode;
			 	window.opener.document.materialRetuForm.matTypeName.value 		= tMatTypeName;
			 	window.opener.document.materialRetuForm.matGrpCode.value 		= tmatGrpCode;
			 	window.opener.document.materialRetuForm.matGrpName.value 		= tmatGrpName;
			 	window.opener.document.materialRetuForm.refMatCode.value 		= trefMatCode;
			 	window.opener.document.materialRetuForm.refMatName.value 		= trefMatName;
			 	window.opener.document.materialRetuForm.matStuffCode.value 		= tmatStuffCode;
			 	window.opener.document.materialRetuForm.matStuffName.value 		= tmatStuffName;
			 	window.opener.document.materialRetuForm.matColorCode.value 		= tmatColorCode;
			 	window.opener.document.materialRetuForm.matColorName.value 		= tmatColorName;
			 	window.opener.document.materialRetuForm.rUnit.value 			= trUnit;
			 	window.opener.document.materialRetuForm.rUnitName.value 		= trUnitName;
		 	 	window.opener.document.materialRetuForm.matBrandCode.value 		= tmatBrandCode;
		 	 	window.opener.document.materialRetuForm.matBrandName.value 		= tmatBrandName;
		 	 	window.opener.document.materialRetuForm.stock1.value 			= tstock1;
		 	 	window.opener.document.materialRetuForm.stock2.value 			= tstock2;
		 	 	window.opener.document.materialRetuForm.normalPrice.value 		= tnormalPrice;
			 	window.opener.document.materialRetuForm.empPrice.value 			= tempPrice;
		 	 	window.opener.document.materialRetuForm.costPrice.value 		= tcostPrice;
		 	 	window.opener.document.materialRetuForm.specialPrice.value 		= tspecialPrice;
		 	 	window.opener.document.materialRetuForm.vipPrice.value 			= tvipPrice;
			 	
		 	} else if (materialForm.formName.value == 'materialIssue') {
			 	window.opener.document.materialIssueForm.matCode.value = tMatCode;
			 	window.opener.document.materialIssueForm.matName.value = tMatName;
			 	
		 	} else if (materialForm.formName.value == 'materialBrrw') {
			 	window.opener.document.materialBrrwForm.matCode.value = tMatCode;
			 	window.opener.document.materialBrrwForm.matName.value = tMatName;
			 	
			 	
		 	} else if (materialForm.formName.value == 'materialWaho') {
			 	window.opener.document.materialWahoForm.matCode.value = tMatCode;
			 	window.opener.document.materialWahoForm.matName.value = tMatName;
			 	
		 	} else alert('no form set');
		 	opener.focus();
		} 
		window.close();
		return;			
	}
	
	function getEmployee() {
		var load = window.open('/inv/employeeSearchSetup.do?formName=material','material',
		                   'scrollbars=yes,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}
	</script>    
  </head>
  
  <body class="blueback">
	<html:form action="materialLOCK" >
		<fieldset><legend><b class="normal"><br>จอง สินค้า<br></b></legend>
		<table align="center" width="100%" >
 		<tr>
 			<td class="blue">Product Code&nbsp; :&nbsp;  
 				<html:text property="matCode" 		size="20" 	maxlength="30"/>
 			</td>
 			<td class="blue">Product Name&nbsp;:&nbsp;
				<html:text property="matName" 		size="20" 	maxlength="30"/>
 			</td>
 			 
 		</tr>
 	 
 		<tr>  
			 <td class="black">Employee Code&nbsp;:&nbsp;  
 				<html:text property="vendCode" 	size="8" 	maxlength="10"/>&nbsp;&nbsp;&nbsp;
 				<a href="javascript:getEmployee();"> <img src="jsp/icons/edit.gif" /> </a>
 				&nbsp;&nbsp;Employee Name&nbsp;:&nbsp;  
 				<html:text property="vendName" 	size="15" 	maxlength="100"/>
 				&nbsp;&nbsp;&nbsp;&nbsp;
 				Document Date&nbsp;:&nbsp;
				<html:text property="date" size="10" maxlength="10"/>&nbsp;&nbsp;<font color="black">dd/mm/yyyy</font>
 			</td>
 			 		
		   <td class="blue" width="50">
			<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialform.button.search" />
 				</html:submit>	 
			<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialform.button.save" />
 				</html:submit>	
 		<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialform.button.confirm" />
 				</html:submit>
 				</td>	  	    
		</tr>
	 	
		<tr><td align="center" class="red">&nbsp;<%=alertMessage%></td>	
 				<html:hidden property="formName"/>    		
 		</tr>
		</table>
		</fieldset> 	
		
	 
	
		<table align="center" width="100%" border="1">
			<tr bgcolor="#8E8EA3">	
				<th align="center" class="normal">No</th>
				<th align="center" class="normal">Date Lock </th>	
				<th align="center" class="normal">Employee Code </th>
				<th align="center" class="normal">Employee Name </th>	
				<th align="center" class="normal">Product Code</th>		
				<th align="center" class="normal">Product Name</th>
				<th align="center" class="normal">Confirm</th>
				<th align="center" class="normal">Serial</th>
				<th align="center" class="normal">Stock 1 </th>
				<th align="center" class="normal">Stock 2</th>
				<th align="center" class="normal">Price</th>					
			</tr>
		
		<%	if (request.getAttribute("materialList") != null) {
				List materialList = (List)request.getAttribute("materialList");
				int x = 0;
				int i=0;
				for (Iterator iter = materialList.iterator(); iter.hasNext();) {
		  			x++;
		  			MaterialForm materials = (MaterialForm) iter.next();
		%>
		<tr>			
	   		<td width="8%"  class="normal" align="center">&nbsp;<%=x%>.
	   		<INPUT type="checkbox" name="checkbkk" value="<%=i%>">
	   		</td>
	   			<td width="12%" class="normal" align="center">&nbsp;
			 	<%=materials.getLastprdate()%>	   	 	   
			<html:hidden property="lastprdate" value = "<%=materials.getLastprdate()%>"/>
			<html:hidden property="lsalecde" value = "<%=materials.getSalecde()%>"/>
			<html:hidden property="lsalenme" value = "<%=materials.getSalenme()%>"/>
			<html:hidden property="lmatCode" value = "<%=materials.getMatCode()%>"/>
			<html:hidden property="lmatName" value = "<%=materials.getMatName()%>"/>
			<html:hidden property="serial" value = "<%=materials.getSerial()%>"/>
			<html:hidden property="stock1" value = "<%=materials.getStock1()%>"/>
			<html:hidden property="stock2" value = "<%=materials.getStock2()%>"/>
 
			<html:hidden property="sellPrice" value = "<%=materials.getSellPrice()%>"/>
						
	   		</td>
	   		<td width="12%" class="normal" align="center">&nbsp;
			 	<%=materials.getSalecde()%>	   	 	   
						
	   		</td>
	   		<td width="12%" class="normal" align="center">&nbsp;
		  	<%=materials.getSalenme()%>	   
						
	   		</td>
	   		<td width="12%" class="normal" align="center">&nbsp;
				<%=materials.getMatCode()%>	   		
	   		</td>
		    <td width="25%" class="normal" align="left">&nbsp;<%=materials.getMatName()%></td>	 
		    <td width="15%" class="normal" align="left">&nbsp;
           
            <html:select property="status" value="<%=materials.getMatStatus()%>">
            	<html:option value="N">N</html:option>
            	<html:option value="Y">Y</html:option>
            <!-- <html:option value="N">N</html:option>
				<html:option value="Y">Y</html:option> -->	
		    </html:select>  
             </td>
             
			 <td width="15%" class="normal" align="left"><font color="blue"><%=materials.getSerial()%></font></td>	  		  		      	   		  
		   	<td width="8%" align="center" class="normal">&nbsp;<%=materials.getStock1()%></td> 	   		  		      	   		  
		    <td width="8%" align="center" class="normal">&nbsp;<%=materials.getStock2()%></td>
 
		    <td width="7%" class="normal" align="center">&nbsp;<%=materials.getSellPrice()%></td>
	    </tr>  
	 	<%		i=i+1;	} 
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<% } %>   
		</table> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			materialForm.matCode.focus()
		</SCRIPT>	
  </body>
</html:html>