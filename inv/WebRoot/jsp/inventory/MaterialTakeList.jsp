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
	
 	String textBoxId = "start";
	if (request.getAttribute("textBoxId") != null) 
	textBoxId = (String) request.getAttribute("textBoxId");		
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
	<html:form action="/materialTakeDTList" >
		<fieldset><legend><b class="normal">3.1 Delivery Order (ใบส่งสินค้า) Add Detail</b> </legend>
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
 				
 				 Customer Code :&nbsp; <html:text property="vendCode" 	readonly="true"/>  
 				 	 Customer Name :&nbsp; <html:text property="vendName" 	readonly="true"/>    
 			 
 				&nbsp;&nbsp;
 				
 				 <font color="blue">Document Date</font> : <html:text property="date" size="10"/>
 				</td>
		    </td>
			 

		</tr>		
		<tr>
		    <td class="normal12">  Warehouse&nbsp;<%=matTakeInfo.getWahoName()%> (<%=matTakeInfo.getWahoCode()%>)  
				<html:hidden property="wahoCode" />
				<html:hidden property="wahoName" />
		    </td>	    		   
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
 			<td class="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	 
		    </td>		    
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
			 
			<html:submit property="reqCode"> 
						<bean:message bundle="inventoryResources" key="materialform.button.adddata" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
			
			<html:hidden property="matStuffCode"/>
			
			<html:hidden property="recno"/>
			Product Code&nbsp;:&nbsp;
			<html:text property="matCode" size="10" maxlength="20"  />
			&nbsp;&nbsp;
			<a href="javascript:getMaterial('fg');"> <img src="jsp/icons/edit.gif" /> </a>
			&nbsp;&nbsp;&nbsp;&nbsp;Product Name&nbsp;:&nbsp;
			<html:text property="matTypeName" 	size="15" maxlength="20" readonly="true"/>
			<html:hidden property="matTypeCode" />
			&nbsp;&nbsp;	 
			Stock1&nbsp; :&nbsp; 
			<html:text property="stock1" size="9" readonly="true" maxlength="10"/>&nbsp;&nbsp;</font>
			Stock2&nbsp; :&nbsp; 
			<html:text property="stock2" size="9" readonly="true" maxlength="10"/>&nbsp;&nbsp;</font>
				 
			   
			</td></tr>
	
			<tr>
			<td class="blue">  
			
			<font color="black"> 
			&nbsp;&nbsp;Quantity Issue :&nbsp; 
			<html:text style="text-align:right;padding-right:2px;"  property="takequantity" size="5" maxlength="5"  value="1"/>
			&nbsp;&nbsp;&nbsp;&nbsp;Amount&nbsp;:&nbsp;
			<html:text style="text-align:right;padding-right:2px;"  property="takeamount" size="15" maxlength="15" readonly="true"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;Total Amount&nbsp;:&nbsp;
			<html:text style="text-align:right;padding-right:2px;"  property="taketotalamount" size="15" maxlength="15"/> 
		  
			 &nbsp;&nbsp;
	 
			Stock3&nbsp; :&nbsp; 
			<html:text property="stock3" size="9" readonly="true" maxlength="10"/>&nbsp;&nbsp;</font>
			Stock4&nbsp; :&nbsp; 
			<html:text property="stock4" size="9" readonly="true" maxlength="10"/>&nbsp;&nbsp;</font>
			</td></tr>
			<tr>
		
			</tr>
			
			</table>
			</fieldset>
	<fieldset>

       <table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightgray">
		<tr>	
			<td width="6%"  align="center" class="normal">No</td>
			<td width="12%"  align="center" class="normal">Product Code</td>			
			<td width="10%" align="center" class="normal">Serial No<br></td>
			<td width="10%"  align="center" class="normal">Customer Code</td>
			<td width="35%" align="center" class="normal">Product Name</td>			
			<td width="8%" align="center" class="normal">Quantity Issue<br></td>
			<td width="7%"  align="center" class="normal">Amount</td>
			<td width="15%"  align="center" class="normal">Total Amount</td>
		</tr>
		</table>	
		
	<table align="center" width="100%" border="1">
		<%	if (request.getAttribute("itemlist") != null) {
				List itemList = (List)request.getAttribute("itemlist");
				int x = 0;
				for (Iterator iter = itemList.iterator(); iter.hasNext();) {
		  			x=x+1;
		  			MaterialTakeForm itemForm = (MaterialTakeForm) iter.next();
		  			 
		%>
		<tr>			
	   		<td width="6%" class="normal" align="center">
		<input type="radio" name="radio1" 
			   onclick="materialTakeForm.matStuffName.value	='<%=itemForm.getMatStuffName()%>';			   		   
			   		   materialTakeForm.matCode.value='<%=itemForm.getMatCode()%>';	
			   		   		   		 	 			   
			   		   "/>
			   		   
			   		   <%=x%>
			   		    			
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
		    <td width="7%"  class="normal" align="center">
		    <html:hidden property="amount" 			value = "<%=itemForm.getTakeamount()%>"/>		<%=itemForm.getTakeamount()%></td>
		    <td width="15%"  class="normal" align="center">
		    <html:hidden property="totalamount" 		value = "<%=itemForm.getTaketotalamount()%>"/>	<%=itemForm.getTaketotalamount()%></td>
			
		 
			
		    <%   }  %>
		    
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
	<% if (textBoxId.equals("start")) { %>
		materialTakeForm.matStuffName.focus()
	<% }  %>
	<% if (textBoxId.equals("add")) { %>
		materialTakeForm.matStuffName.focus()
	<% }  %>
</SCRIPT>	 
  </body>
</html:html>