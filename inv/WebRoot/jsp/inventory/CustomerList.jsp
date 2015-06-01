<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm"%>
<%@ page import="com.dtac.inventory.form.CustomerForm"  %> 
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
    <title>My JSP 'CustomerList.jsp' starting page</title>
    
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
	<html:form action="/customerList" >
		<fieldset><legend><b class="normal">1.8 Customer</b></legend>
		<table align="center" width="95%">
		<tr>
 			<td class="blue">Customer Code&nbsp;:&nbsp;
 				<html:text property="customerCode" 		size="6" 	maxlength="6"/>
 				&nbsp;&nbsp;&nbsp;Customer Name&nbsp;:&nbsp;
				<html:text property="customerName" 		size="20" 	maxlength="30"/>
 			</td>
			<td class="blue">Search&nbsp;:&nbsp;
				<html:text property="searchName"	size="20" 	maxlength="30"/>
			</td>
			<td class="blue">Customer Type&nbsp;:&nbsp;
				<html:select property="customerTypeCode">
					<%
						if (request.getAttribute("custTypeList") != null) {
							List custTypeList = (List) request.getAttribute("custTypeList");
							for (Iterator iterDept = custTypeList.iterator(); iterDept.hasNext();) {
								MasterTableForm custTypeForm = (MasterTableForm) iterDept.next();
					%>
					<html:option value="<%=custTypeForm.getTypeCode()%>"><%=custTypeForm.getThName()%></html:option>
					<%
						}
							}
					%>
				</html:select>
			</td> 			
 		</tr>
		<tr>
		   	<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   		Status &nbsp;:&nbsp;				
				<html:select property="status" >
					<html:option value="AC">Active</html:option>
					<html:option value="CA">Inactive</html:option>
				</html:select>
			</td>		
			<td colspan="2" align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
		<tr>
 			<td class="normal" colspan="2" align="center">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="customerform.button.search" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp; 				
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="customerform.button.adddata" />
 				</html:submit> 				
 			</td>
        </tr>		
		</table>
		</fieldset> 	
		
	 	
	
		<table align="center" width="90%" border="1">
		<tr bgcolor="#8E8EA3">		
			<th align="center" class="normal">Customer Code</th>		
			<th align="center" class="normal">Customer</th>		
			<th align="center" class="normal">Search</th>
			<th align="center" class="normal">Customer Type</th>
			<th align="center" class="normal">Status</th>
		</tr>
		<%	if (request.getAttribute("customerList") != null) {
				List customerList = (List)request.getAttribute("customerList");
				int x = 0;
				for (Iterator iter = customerList.iterator(); iter.hasNext();) {
		  			x++;
		  			CustomerForm customer = (CustomerForm) iter.next();
		%>
		<tr>			
	   		<td width="10%" class="normal" align="center">
				<input type="radio" name="radio1" 
			   		onclick="customerForm.customerCode.value='<%=customer.getcustomerCode()%>';
			   			 	 customerForm.customerName.value = '<%=customer.getcustomerName()%>';
			   				 customerForm.searchName.value='<%=customer.getSearchName()%>';
			   				 customerForm.customerTypeCode.value='<%=customer.getcustomerTypeCode()%>'"/>&nbsp;&nbsp;<%=customer.getcustomerCode()%>
	   		</td>
	   		<td width="10%" class="normal" align="center"><%=customer.getcustomerName()%></td>
		    <td width="20%" class="normal" align="center"><%=customer.getSearchName()%></td>	   		  		      	   		  
		    <td width="15%" class="normal" align="center"><%=customer.getcustomerTypeName()%></td>

		    <% if (customer.getStatus().equals("AC")) { %>
		    <td width="10%" class="normal" align="center">&nbsp;Active</td>
		    <% } else if (customer.getStatus().equals("CA")) { %>	
		    <td width="10%" class="red" align="center">&nbsp;Inactive</td>
	   		<% } else { %>
	   		<td width="10%" class="normal" align="center">&nbsp;</td>
	   		<% } %>
		      
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>   
		</table> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			customerForm.customerCode.focus()
		</SCRIPT>	
  </body>
</html:html>