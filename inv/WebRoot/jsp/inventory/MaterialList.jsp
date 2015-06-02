<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.inventory.form.MaterialForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
<%@ page import="com.dtac.inventory.form.WarehouseForm" %>
<%@ page import="com.dtac.inventory.form.MaterialForm" %>
<%@ include file="../admin/css_blue_style.css" %>
<%
 	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 
		
	String textBoxId = "start";
	if (request.getAttribute("textBoxId") != null) 
		textBoxId = (String) request.getAttribute("textBoxId");	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'MaterialList.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<SCRIPT TYPE="TEXT/JAVASCRIPT">
	function press(event, textid) {
	//alert(event);
		if (event.keyCode == 13) {
			document.materialForm.textBoxId.value = textid;
			if (textid == 'matcode') 	{				
				document.materialForm.action="/inv/materialList.do?reqCode=Search";
        		document.materialForm.submit();    	
        					
			}
			// In IE9 the focus shifts to the <button> unless we call preventDefault(). Uncomment following line for IE9 fix. Alternatively set type="button" on all button elements and anything else that is a submit or reset too!.
             event.preventDefault();
             event.keyCode = 9;
	 	}		
	
	 }
	function getVender() {
		var load = window.open('/inv/venderSearchSetup.do?formName=material','material','scrollbars=no,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}	
	
	</script> 
  </head>
  
  <body class="blueback">
	<html:form action="materialList" >
		<fieldset><legend><b class="normal">2.3 Product Detail</b></legend>
		<table align="center" width="100%">
		<tr>
 			<td class="black">Product Code&nbsp;:&nbsp;
 				<html:text property="matCode" styleId="matcode" size="20" maxlength="20" onkeypress="press(event,'matcode');" />
 			</td>
 			
 			<td class="black">Serial No.&nbsp;:&nbsp;
 				<html:text property="serial" 		size="20" 	maxlength="50"/>
 				<html:submit property="reqCode" > 
 					<bean:message bundle="inventoryResources" key="materialform.button.adddata" />
 				</html:submit> 	
 				
 				
 				Status &nbsp;:&nbsp;
				<html:select property="matStatus" >
			
					<html:option value="AC">Active</html:option>
					<html:option value="CA">Inactive</html:option>
				</html:select>		
 			</td>
 			<td class="black">Product Name&nbsp;:&nbsp;
				<html:text property="matName" 	size="20" 	maxlength="30" readonly="true"/>
				 
 			</td>
 			<td class="black"><br>
 			</td>
 		</tr> 		
 		
		<tr>
		
			 			<td class="black">Vender&nbsp;:&nbsp;
				<html:text property="matSupplCode" size="7"  maxlength="10"/>
				<a href="javascript:getVender();"> <img src="jsp/icons/edit.gif" /> </a>
				<html:text property="matSupplName" size="15" maxlength="100" readonly="true"/>
			</td>
			<td class="black"> 
				
					Warehouse&nbsp;:&nbsp; 
				<html:select property="refMatCode" >
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
						}%></html:select>&nbsp;
						
				Document Date&nbsp;:&nbsp;
				<html:text property="date" size="10" maxlength="10"/>&nbsp;&nbsp;<font color="black">dd/mm/yyyy</font>
				&nbsp; &nbsp;		
				
 			</td>    		    
			<td class="black">Price&nbsp;:&nbsp;
				<html:text property="normalPrice" size="15" 	maxlength="30" readonly="true"/>
 			 
			
				&nbsp;&nbsp; Stock&nbsp; &nbsp;:&nbsp;
				<html:text property="stock1" size="3" maxlength="3"  value="1"/>
			 	&nbsp;&nbsp;
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialform.button.search" />
 				</html:submit> 	
			
		    </td>	
		    
 						
 						
 			 	
		</tr>
	
		<tr><td align="center" class="red">&nbsp;<%=alertMessage%></td>	
 			
 			 		    		
 		</tr>
		</table>
		</fieldset> 	
		
	 	
		
		<table align="center" width="100%" border="1">
		<tr bgcolor="#8E8EA3">	
			<th align="center" class="normal">No.</th>		
			<th align="center" class="normal">Product Code</th>	
			<th align="center" class="normal">Serial No.</th>	
			<th align="center" class="normal">Product Name</th>
			<th align="center" class="normal">Vender</th>
			
			<th align="center" class="normal">Price</th>
			<th align="center" class="normal">Stock 1</th>
			<th align="center" class="normal">Stock 2</th>
			<th align="center" class="normal">Stock 3</th>
			<th align="center" class="normal">Stock 4</th>
			<th align="center" class="normal">Status</th>
		</tr>	
		
		<%	if (request.getAttribute("materialList") != null) {
				List materialList = (List)request.getAttribute("materialList");
				int x = 0;
				for (Iterator iter = materialList.iterator(); iter.hasNext();) {
		  			x++;
		  			MaterialForm materials = (MaterialForm) iter.next();
		  			if (materials.getMatStatus().equals("CA")) {
		%>
		<tr bgcolor="red">
		<% } else { %>
		<tr>
		<% } %>			
	   		<td width="7%" class="normal" align="center">
				<input type="radio" name="radio1" 
			   		onclick="materialForm.matCode.value='<%=materials.getMatCode()%>';
			   		         materialForm.serial.value='<%=materials.getSerial()%>';
			   		         materialForm.matName.value='<%=materials.getMatName()%>';
			   				 "/>&nbsp;&nbsp;<%=x%>
	   		</td>
	   		<td width="11%" align="center" class="normal">&nbsp;<%=materials.getMatCode()%></td>
	   		<td width="20%" align="center" class="normal">&nbsp;<%=materials.getSerial()%></td>
		    <td width="30%" class="normal">&nbsp;<%=materials.getMatName()%></td>
		   	<td width="8%"  class="normal">&nbsp;<%=materials.getMatSupplCode()%></td> 	   		  		      	   		  
		 
		    <td width="10%" align="center" class="normal">&nbsp;<%=materials.getNormalPrice()%></td>
		    <td width="7%" align="center" class="normal">&nbsp;<%=materials.getStock1()%></td>  
		        <td width="7%" align="center" class="normal">&nbsp;<%=materials.getStock2()%></td>  
		            <td width="7%" align="center" class="normal">&nbsp;<%=materials.getStock3()%></td>  
		                <td width="7%" align="center" class="normal">&nbsp;<%=materials.getStock4()%></td>  
		    <td width="7%" align="center" class="normal">&nbsp;<%=materials.getMatStatus()%></td>
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>   
		</table> 	
	</html:form>
		<SCRIPT TYPE="TEXT/JAVASCRIPT">
	<% if (textBoxId.equals("start")) { %>
		materialForm.matCode.focus()
	<% }  %>
	<% if (textBoxId.equals("add")) { %>
		materialForm.serial.focus()
	<% }  %>
</SCRIPT>
  </body>
</html:html>