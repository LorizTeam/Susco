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
	tmatColorCode, tmatColorName, trUnit, trUnitName, tmatBrandCode, tmatBrandName, tstock1, tstock2,tstock3, tstock4, tnormalPrice, tempPrice, tcostPrice, tspecialPrice, tvipPrice, tSerial) {
		
		if (opener && !opener.closed){
			if (materialForm.formName.value == 'umbrella') {
			 	window.opener.document.umbrellaForm.matCode.value = tMatCode;
			 	window.opener.document.umbrellaForm.matName.value = tMatName;
			 } else if (materialForm.formName.value == 'materialMember') {
			 	window.opener.document.memberSearchForm.matcode.value = tSerial;
			 	
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
			 	window.opener.document.materialTakeForm.stock1.value 			= tstock1;
			 	window.opener.document.materialTakeForm.stock2.value 			= tstock2;
		 	 	window.opener.document.materialTakeForm.stock3.value 			= tstock3;
		 	 	window.opener.document.materialTakeForm.stock4.value 			= tstock4;
		//	 	window.opener.document.materialTakeForm.matColorName.value   	= tmatColorName;
			 	window.opener.document.materialTakeForm.matStuffName.value 	    = tSerial;
			 	window.opener.document.materialTakeForm.takeamount.value 		= tnormalPrice;
			 	window.opener.document.materialTakeForm.taketotalamount.value=0;
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
	</script>    
  </head>
  
  <body class="blueback">
	<html:form action="materialSearch" >
		<fieldset><legend><b class="normal"><br>Data Product</b></legend>
		<table align="center" width="100%" >
 		<tr>
 			<td class="blue">Product Code&nbsp; :&nbsp;  
 				<html:text property="matCode" 		size="20" 	maxlength="30"/>
 			</td>
 			<td class="blue">Product Name&nbsp;:&nbsp;
				<html:text property="matName" 		size="20" 	maxlength="30"/>
 			</td>
 			<td class="blue">Serial No.&nbsp; :&nbsp; 
				<html:text property="matSearchName" size="20" 	maxlength="30"/>
 			</td>
 			
 			
			<td class="blue" width="50"><html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialform.button.search" />
 				</html:submit>	</td>	
 		</tr>
 	 
 			
				    	    
	 
		
		<tr><td align="center" class="red">&nbsp;<%=alertMessage%></td>	
 				<html:hidden property="formName"/>    		
 		</tr>
		</table>
		</fieldset> 	
		
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightgray">
		<tr>	
			<td width="8%"  align="center" class="normal">No</td>		
			<td width="12%" align="center" class="normal">Product Code</td>		
			<td width="25%" align="center" class="normal">Product Name</td>
			<td width="15%" align="center" class="normal">Serial</td>
			<td width="8%" align="center" class="normal">Stock1<br></td>
			<td width="8%" align="center" class="normal">Stock2<br></td>
			<td width="10%" align="center" class="normal">Stock3<br></td>
			<td width="7%" align="center" class="normal">Stock4<br></td>
			<td width="7%" align="center" class="normal">Size</td>
					
		</tr>
		</table>
	
		<table align="center" width="100%" border="1">
		<%	if (request.getAttribute("materialList") != null) {
				List materialList = (List)request.getAttribute("materialList");
				int x = 0;
				for (Iterator iter = materialList.iterator(); iter.hasNext();) {
		  			x++;
		  			MaterialForm materials = (MaterialForm) iter.next();
		%>
		<tr>			
	   		<td width="8%"  class="normal" align="center">&nbsp;<%=x%>.</td>
	   		<td width="12%" class="normal" align="center">&nbsp;
				<%=materials.getMatCode()%>	   		
	   		</td>
		    <td width="25%" class="normal" align="left">&nbsp;<%=materials.getMatName()%></td>	 
		    <td width="15%" class="normal" align="left">&nbsp;
		    <a href="javascript:returnMaterial('<%=materials.getMatCode()%>','<%=materials.getMatName()%>','<%=materials.getMatTypeCode()%>','<%=materials.getMatTypeName()%>',
				'<%=materials.getMatGrpCode()%>','<%=materials.getMatGrpName()%>','<%=materials.getRefMatCode()%>','<%=materials.getRefMatName()%>','<%=materials.getMatStuffCode()%>','<%=materials.getMatStuffName()%>','<%=materials.getMatColorCode()%>',
				'<%=materials.getMatColorName()%>','<%=materials.getpUnit()%>','<%=materials.getpUnitName()%>','<%=materials.getMatBrandCode()%>','<%=materials.getMatBrandName()%>','<%=materials.getStock1()%>','<%=materials.getStock2()%>','<%=materials.getStock3()%>','<%=materials.getStock4()%>','<%=materials.getNormalPrice()%>','<%=materials.getEmpPrice()%>','<%=materials.getCostPrice()%>',
				'<%=materials.getSpecialPrice()%>','<%=materials.getVipPrice()%>',<%=materials.getSerial()%>) ;"> 
					<font color="blue"><%=materials.getSerial()%></font></a></td>	  		  		      	   		  
		   	<td width="8%" align="center" class="normal">&nbsp;<%=materials.getStock1()%></td> 	   		  		      	   		  
		    <td width="8%" align="center" class="normal">&nbsp;<%=materials.getStock2()%></td>
		    <td width="8%" class="normal" align="center">&nbsp;<%=materials.getStock3()%></td>  
		    <td width="8%" class="normal" align="center">&nbsp;<%=materials.getStock4()%></td>
		    <td width="7%" class="normal" align="center">&nbsp;<%=materials.getRefMatName()%></td>
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>   
		</table> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			materialForm.matCode.focus()
		</SCRIPT>	
  </body>
</html:html>