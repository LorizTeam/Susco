<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>My JSP 'WarehouseList.jsp' starting page</title>
    
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
	<html:form action="warehouseList" >
		<fieldset><legend><b class="normal">2.2 Warehouse</b></legend>
		<table align="center" width="90%">
		<tr><td>&nbsp;</td></tr>
		<tr>
		   	<td class="blue">Warehouse Code  &nbsp;:&nbsp;
       			<html:text property="wahoCode" size="2" maxlength="2" />
       			&nbsp;&nbsp;&nbsp;&nbsp;
			</td>	
		 	<td class = "blue"> Warehouse Name &nbsp;:&nbsp;
		 		<html:text property="wahoName" size = "20" maxlength = "50"> </html:text> 
		 	</td>	
		 	<td class="blue">&nbsp;Status :
				<html:select property="wahoStatus" >
					
   					<html:option value="AC">Active</html:option>
					<html:option value="CA">Inactive</html:option>
				</html:select>
			</td>			
		</tr>
		<tr><td class="red"><%=alertMessage%></td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td>
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.adddata" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;  				
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.search" />
 				</html:submit> 
			</td>		
		</tr>
		</table>
		</fieldset>
 	
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="60%" bgcolor="lightslategray">
	 	<tr>
 			<td align="center">
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.update" />
 				</html:submit>
 			</td>
 			<td>
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.viewloca" />
 				</html:submit>
 			</td>
	 	</tr>
		<tr>	
			<td width="10%"  align="center" class="normal">No.</td>		
			<td width="20%" align="center" class="normal">Warehouse Name</td>
			<td width="10%" align="center" class="normal">Status</td>
			<td width="10%" align="center" class="normal">&nbsp;</td>
		</tr>
		</table>	
	
		<table align="center" width="60%" border="1">
		<%	if (request.getAttribute("warehouseList") != null) {
				List warehouseList = (List)request.getAttribute("warehouseList");
				int x = 0;
				for (Iterator iter = warehouseList.iterator(); iter.hasNext();) {
		  			x++;
					WarehouseForm warehouses = (WarehouseForm) iter.next();
		%>
		<tr>			
		      	<% Map parm = new HashMap();
        			parm.put("wahoCode", warehouses.getWahoCode());
        			pageContext.setAttribute("parm", parm);
				%>
		
   		  	<td width="10%" class="normal" align="center">
				<input type="radio" name="radio1" 
			   		onclick="warehouseForm.wahoCode.value='<%=warehouses.getWahoCode()%>';
			   		         warehouseForm.wahoName.value='<%=warehouses.getWahoName()%>';
			   		         warehouseForm.wahoStatus.value ='<%=warehouses.getWahoStatus()%>';"/>&nbsp;&nbsp;<%=x%>
	   		</td>
		    <td width="20%" class="normal">&nbsp;<%=warehouses.getWahoCode()%>&nbsp;-&nbsp;<%=warehouses.getWahoName()%></td>
 			<% if (warehouses.getWahoStatus().equals("CA")) { %>
		    <td width="10%" class="red" align="center">&nbsp;Active</td>	   		  		      		      		      
			<% } else { %>		      
		    <td width="10%" class="normal" align="center">&nbsp;Inactive</td>	   		  		      		      		      		      
		    <% } %>	      	     	
		    <td width="10%" class="normal" align="center">
					<html:link action="warehouseAuthSetup" name="parm" styleClass="blue">
					   	&nbsp;กำหนดสิทธิ์
				    </html:link> 
		    </td>	   		  
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="3">No Data Found</td></tr>
		<%	} %>   
		</table>
	</html:form>
  </body>
</html:html>