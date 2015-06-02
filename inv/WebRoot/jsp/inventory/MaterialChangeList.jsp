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
		                  'scrollbars=yes,menubar=no,height=786,width=1300,resizable=yes,toolbar=no,location=no,status=no');
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
 function press(event, textid) {
	//alert(event);
		if (event.keyCode == 13) { 
			document.materialTakeForm.textBoxId.value = textid;
			if (textid == 'serial') 	{				
				document.materialTakeForm.action="/inv/materialChangeDTList.do?reqCode=Get Product";
        		document.materialTakeForm.submit();    				
			}else if(textid == 'serialchg'){
				document.materialTakeForm.action="/inv/materialChangeDTList.do?reqCode=Get Product Change";
        		document.materialTakeForm.submit(); 
			}
			
			// In IE9 the focus shifts to the <button> unless we call preventDefault(). Uncomment following line for IE9 fix. Alternatively set type="button" on all button elements and anything else that is a submit or reset too!.
             event.preventDefault();
             event.keyCode = 9;
	 	}		
	
	 }
	</script>

  </head>
  
  <body class="blueback">
	<html:form action="/materialChangeDTList" >
		<fieldset><legend><b class="normal">2.4 Change&nbsp; (ใบเปลี่ยนสินค้า) Add Detail</b> </legend>
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
		    	<td class="blue"> 
 				 Employee Name&nbsp;:&nbsp;  
 				<html:text property="vendName" 	size="11" 	maxlength="100" readonly="true"/>
 				&nbsp;&nbsp;
 				
 				 <font color="blue">Document Date</font> : <html:text property="date" size="10"/>
 				</td>
		    </td>
			<td> <html:hidden property="vendCode" /> <html:hidden property="vendName" /></td>

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
		    	<a href="javascript:printTake();"><font color="green">พิมพ์ใบเปลี่ยนสินค้า (Print)</font></a>
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
			 
			&nbsp;&nbsp;Serial No Receive :&nbsp; 
			<html:text property="serial" styleId="serial" onkeypress="press(event,'serial');"	size="10" maxlength="15" />
	<html:submit property="reqCode" styleId="getproduct"> 
 					<bean:message bundle="inventoryResources" key="materialtakeform.button.getprod" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
			
			<html:hidden property="matStuffCode"/>
			<html:hidden property="textBoxId"/>
			<html:hidden property="recno"/> 
			Product Code&nbsp;:&nbsp; 
			<html:text property="matCode" size="10" maxlength="20" readonly="true"/>
			&nbsp;&nbsp;
			
			&nbsp;&nbsp;&nbsp;&nbsp;Product Name&nbsp;:&nbsp;&nbsp;
			<html:text property="matName" size="15" maxlength="20" readonly="true"></html:text>
				 
						<font color="black">   
			&nbsp; Quantity Receive :&nbsp;   
			<html:text style="text-align:right;padding-right:2px;"  property="takequantity" size="2" maxlength="2" value="1"/>
			&nbsp;&nbsp;&nbsp;&nbsp;Amount&nbsp;:&nbsp;
			<html:text style="text-align:right;padding-right:2px;"  property="takeamount" size="15" maxlength="15" readonly="true"/>
				 
			   
			</td></tr>
	
			<tr>
			<td class="blue">  
			
			&nbsp;&nbsp;Serial No Change :&nbsp; 
			<html:text property="serialchg"  styleId="serialchg" onkeypress="press(event,'serialchg');"	size="10" maxlength="15" />
			 	
		 	<html:submit property="reqCode" styleId="getproductchg"> 
 					<bean:message bundle="inventoryResources" key="materialtakeform.button.getprodchg" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
			
		 
			
		 
			Product Code&nbsp;:&nbsp;
			<html:text property="matCodechg" size="10" maxlength="20" readonly="true"/>
			&nbsp;&nbsp;
			 
			&nbsp;&nbsp;&nbsp;&nbsp;Product Name&nbsp;:&nbsp;
			<html:text property="matNamechg" 	size="15" maxlength="20" readonly="true"/>
			 
				 
						   
			&nbsp;&nbsp;Quantity Change :&nbsp;   
			<html:text style="text-align:right;padding-right:2px;"  property="quantitychg" size="2" maxlength="2" value="1"/>
			&nbsp;&nbsp;&nbsp;&nbsp;Amount&nbsp;:&nbsp;
			<html:text style="text-align:right;padding-right:2px;"  property="amountchg" size="15" maxlength="15" readonly="true"/>
	
			
			</td></tr>
			<tr>
			<td>
			<html:submit property="reqCode" styleId="chg"> 
 					<bean:message bundle="inventoryResources" key="materialtakeform.button.chg" />
 				</html:submit>
 				
 				<html:text property="custCode" size="10" maxlength="15" readonly="true"/>	
 				<html:text property="custName" size="20" maxlength="25" readonly="true"/>
			</td>
			
			</tr>
	
			</table>
			</fieldset>
	<fieldset>

       <table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightgray">
		<tr>	
			<td width="6%"  align="center" class="normal">No</td>
			<td width="12%"  align="center" class="normal">Product Code</td>			
			<td width="10%" align="center" class="normal">Serial No<br></td>
			<td width="10%"  align="center" class="normal">Customer Code<br></td>
			<td width="35%" align="center" class="normal">Product Name</td>			
			<td width="8%" align="center" class="normal">Quantity <br></td>
			<td width="7%"  align="center" class="normal">Amount</td>
			<td width="15%"  align="center" class="normal">Total Amount</td>
		</tr>
		</table>	
		
	<table align="center" width="100%" border="1">
		<%	if (request.getAttribute("itemlist") != null) {
				List itemList = (List)request.getAttribute("itemlist");
				int x = 0;
				for (Iterator iter = itemList.iterator(); iter.hasNext();) {
		  			x++;
		  			MaterialTakeForm itemForm = (MaterialTakeForm) iter.next();
		  			 
		%>
		<tr>			
	   		<td width="6%" class="normal" align="center">
		<input type="radio" name="radio1" 
			   onclick="materialTakeForm.matStuffName.value	='<%=itemForm.getMatStuffName()%>';			   		   
			   		   materialTakeForm.matCode.value='<%=itemForm.getMatCode()%>';	 		   		 	 			   
			   "/>
			   		    			
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
			
		 
			
		    <% } %>
		    
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
		materialTakeForm.serial.focus()
	<% }  %>
	<% if (textBoxId.equals("chg")) { %>
		materialTakeForm.serialchg.focus()
	<% }  %>
	<% if (textBoxId.equals("end")) { %>
		materialTakeForm.chg.focus()
	<% }  %>
	</SCRIPT>
  </body>
</html:html>