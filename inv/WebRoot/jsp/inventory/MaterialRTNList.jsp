<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.MaterialTakeForm"%>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm"%>
<%@ page import="com.dtac.admin.form.MonthForm" %>
<%@ page import="com.dtac.admin.form.YearForm" %>
<%@ page import="com.dtac.inventory.form.MaterialForm" %>
<%@ page import="com.dtac.inventory.form.MatDocTypeForm" %>
<%@ page import="com.dtac.inventory.form.WarehouseForm" %>
<%@ page import="com.dtac.admin.form.MasterTableForm;"%>
<%@ include file="../admin/css_blue_style.css" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage");
		
	List matTakeHDList = null;
	MaterialTakeForm matTakeInfo = null;
	if (request.getAttribute("matTakeHDList") != null) {
		matTakeHDList = (List)request.getAttribute("matTakeHDList");
		if (matTakeHDList.size() == 1) matTakeInfo = (MaterialTakeForm) matTakeHDList.get(0);
	}	
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
    <script Language="javascript">
	function getItemno() {
		var load = window.open('/inv/itmenoSearchSetup.do?formName=material','material',
		                   'scrollbars=yes,menubar=no,height=786,width=1200,resizable=yes,toolbar=no,location=no,status=no');
	}
	function calTotalAmout(){
		document.materialTakeForm.taketotalamount.value=document.materialTakeForm.takequantity.value*document.materialTakeForm.takeamount.value
	}
	function getVender() {
		var load = window.open('/inv/venderSearchSetup.do?formName=materialTake','materialTake',
		                   'scrollbars=yes,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}
	function getEmployee() {
		var load = window.open('/inv/employeeSearchSetup.do?formName=materialBrow','materialBrow',
		                   'scrollbars=yes,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}
	function getMaterial(matType) {
		var load = window.open('/inv/materialSearchSetup.do?formName=materialTake&matType='+matType,'materialTake',
		                  'scrollbars=yes,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}
	function printTake() {
		 	
		var load = window.open('/inv/materialTakeDTList.do?reqCode=Print'+
		                            '&docTypeCode='+document.materialTakeForm.docTypeCode.value+
		                            '&docYear='+document.materialTakeForm.docYear.value+
		                            '&docNo='+document.materialTakeForm.docNo.value+		                               
									'&lotno='+document.materialTakeForm.lotno.value	                                           
		    ,'recv','scrollbars=yes,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}
	function show_confirm(){
		
			var con = confirm("Are You Confirm");
				if (con ==true){
 			 
 			 	window.location="./materialTakeDTList.do"
				document.materialTakeForm.confirm.value='Y';
 	}	
		else{
  			
  				document.materialTakeForm.confirm.value='N';
 	}
 }
	</script>

  </head>
  
  <body class="blueback">
	<html:form action="/materialRTNDTList" >
		<fieldset><legend><b class="normal">3.2 Delivery Return (ใบคืนสินค้า) Add Detail</b> </legend>
		<table align="center" width="100%" class="background1">
		<html:hidden property="confirm"/>
        <tr>
        	<td class="normal12" width="40%">Document Type&nbsp;
    <%=matTakeInfo.getDocTypeCode()%> - <%=matTakeInfo.getDocYear()%> - <%=matTakeInfo.getDocNo()%>
        		<html:hidden property="docTypeCode" />
        		<html:hidden property="docYear" />   
        		<html:hidden property="docMonth" />
        		 <html:hidden property="date" />	     		
 				<html:hidden property="docNo" />
		    </td>
		    <td class="normal12" width="40%">
		    	<td class="blue">&nbsp;  
 				<html:hidden property="vendCode" 		/>
 				 Customer Name&nbsp;:&nbsp;   
 				<html:text property="vendName" 	size="11" 	maxlength="100" readonly="true"/>
 				&nbsp;&nbsp;
 				
 				 <font color="blue">Document Date</font> : <html:text property="date" size="10"/>
 				</td>
		    </td>
			<td> <html:hidden property="vendCode" /> <html:hidden property="vendName" /></td>

		</tr>		
 
		<tr>
		    <td class="normal">Receive By&nbsp;<%=matTakeInfo.getDocbyname()%> (<%=matTakeInfo.getDocbycode()%>)
				<html:hidden property="docbyname" />
				<html:hidden property="docbycode" />
		    </td>			
		</tr>
		<tr>
        	<td class="blue">			
		    </td>
		    <td class="blue"><FONT color="red"><%=alertMessage%></FONT></td>
 			 	    
		</tr>
		<tr>						    		
		</tr>		
		</table>
		</fieldset>
			
			<fieldset><legend><b class="blue"><br>List</b></legend>
			<table align="center" width="100%">
		  <tr>
			<td class="normal">
	 	
			&nbsp;&nbsp;Serial No&nbsp;:&nbsp;
			<html:text property="matStuffName" 	size="10" maxlength="15" />
			 		 
 				
 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
 <html:submit property="reqCode"> 
		<bean:message bundle="inventoryResources" key="materialtakeform.button.save" />				
 				</html:submit>
			
			<html:hidden property="matStuffCode"/>
				 
			
				
			<html:hidden property="recno"/>
			
	 
			</td> 
		 </tr>
			 
			
			</table>
			</fieldset>
	<fieldset>

       
		
	<table align="center" width="100%" border="1">
		<tr bgcolor="#8E8EA3">	
			<th width="6%"  align="center" class="normal">No</th>			 
			<th width="12%"  align="center" class="normal">Stock  Ref Doc No.</th>
			<th width="12%"  align="center" class="normal">Product Code</th>			
			<th width="10%" align="center" class="normal">Serial No<br></th>
			<th width="10%"  align="center" class="normal">Customer Code</th>
			<th width="35%" align="center" class="normal">Product Name</th>			
			<th width="8%" align="center" class="normal">Quantity Borrow<br></th>
			<th width="8%" align="center" class="normal">Quantity Return<br></th>
			<th width="7%"  align="center" class="normal">Amount</th>
			<th width="15%"  align="center" class="normal">Total Amount</th>
		</tr>
		<%	if (request.getAttribute("itemlist") != null) {
				List itemList = (List)request.getAttribute("itemlist");
				int x = 0;
				int i=0;
				for (Iterator iter = itemList.iterator(); iter.hasNext();) {
		  			x=x+1;
		  			MaterialTakeForm itemForm = (MaterialTakeForm) iter.next();
		  			 
		%>
		<tr>			
	   		<td width="8%"  class="normal" align="center">&nbsp;<%=x%>.&nbsp;	
<INPUT type="checkbox" name="checkbkk" value="<%=i%>" >
	   		  
	   		  
	 <%	if (itemForm.getStatus().equals("BW")) {	%>  
		 	<img src="jsp/icons/false.gif" />
			<%}else  {    %>  
			<img src="jsp/icons/true.gif" />
			 <%} %>  
			   		  
  &nbsp;&nbsp;Qty&nbsp; 
			<html:text style="text-align:right;padding-right:2px;" value="1"  property="takequantity" size="5" maxlength="5"  />	
	   		 
	   		</td>
	   		
	   		<td width="15%" class="normal" align="center">&nbsp;
	   		 <%=itemForm.getWahoCode() %>-<%=itemForm.getDocMonth() %>
	   			 
	   		<%=itemForm.getDocTypeCode()%>&nbsp;-&nbsp;<%=itemForm.getDocYear()%>
		    	&nbsp;/&nbsp;<%=itemForm.getDocNo()%>
		    	<html:hidden property="oldwhocode" value = "<%=itemForm.getWahoCode()%>"/>
		    	<html:hidden property="oldmonth" value = "<%=itemForm.getDocMonth()%>"/>
		    	<html:hidden property="olddocTypeCode" value = "<%=itemForm.getDocTypeCode()%>"/>
		    	<html:hidden property="olddocYear" value = "<%=itemForm.getDocYear()%>"/>
		    	<html:hidden property="olddocNo" value = "<%=itemForm.getDocNo()%>"/>
		    </td>
	   		<td width="12%"  class="normal" align="center">
	   		<html:hidden property="oldMatCode" value = "<%=itemForm.getMatCode()%>"/>		<%=itemForm.getMatCode()%></td>
		    <td width="10%"  class="normal" align="center">
		    <html:hidden property="oldMatStuffName" 	value = "<%=itemForm.getMatStuffName()%>"/>		<%=itemForm.getMatStuffName()%></td>	   		  		      	   		  
		    <td width="10%"  class="normal" align="center">
		    <html:hidden property="categories" 		value = "<%=itemForm.getTakecategories()%>"/>	<%=itemForm.getTakecategories()%></td>
	   		<td width="35%"  class="normal" align="center">
	   		<html:hidden property="oldMatName" 		value = "<%=itemForm.getMatName()%>"/>			<%=itemForm.getMatName()%></td>
	   		<td width="8%"  class="normal" align="center">
	   		<html:hidden property="quantity" 			value = "<%=itemForm.getTakequantity()%>"/>		<%=itemForm.getTakequantity()%></td>
	   		<td width="8%"  class="normal" align="center">
	   		<html:hidden property="rtnqty" 			value = "<%=itemForm.getRtnqty() %>"/>		<%=itemForm.getRtnqty()%></td>
		    <td width="7%"  class="normal" align="center">
		    <html:hidden property="amount" 			value = "<%=itemForm.getTakeamount()%>"/>		<%=itemForm.getTakeamount()%></td>
		    <td width="15%"  class="normal" align="center">
		    <html:hidden property="totalamount" 		value = "<%=itemForm.getTaketotalamount()%>"/>	<%=itemForm.getTaketotalamount()%></td>
			
		 
			
		    <%  i=i+1; }  %>
		    
	    </tr>  
	 	<%	  
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>  
		</table> 	
		<table>
		<tr> <td class= "blue">
		<font color="red">Detail List </font>
		</td>
		
		</tr>
		</table>	
		
 
	
		
</fieldset>
		
	</html:form>
	 		<SCRIPT TYPE="TEXT/JAVASCRIPT">
	 
				materialTakeForm.matStuffName.focus()
	 		</SCRIPT>
  </body>
</html:html>