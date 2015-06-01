<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm"%>
<%@ page import="com.dtac.inventory.form.ProductForm"  %> 
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
	<html:form action="/productList" >
		<fieldset><legend><b class="normal">&nbsp;Product&nbsp;</b></legend>
		<table align="center" width="95%">
		<tr>
 			<td class="blue">Product Code&nbsp;:&nbsp; 
 				<html:text property="productCode" 		size="15" 	maxlength="15"/> 
 				&nbsp;&nbsp; Product Name&nbsp;:&nbsp; 
				<html:text property="productName" 		size="20" 	maxlength="30"/>
 			</td>
			<td class="blue">Price :&nbsp; 
				<html:text property="price"	size="20" 	maxlength="30"/>
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
 					<bean:message bundle="inventoryResources" key="productform.button.search" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp; 				
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="productform.button.adddata" />
 				</html:submit> 				
 			</td>
        </tr>		
		</table>
		</fieldset> 	
		
	 	
	
		<table align="center" width="90%" border="1">
			<tr bgcolor="#8E8EA3">	
				<th align="center" class="normal">Product Code</th>		
				<th align="center" class="normal">Product<br></th>		
				<th align="center" class="normal">Price<br></th>
				 
				<th align="center" class="normal">Status</th>
			</tr>
		
		<%	if (request.getAttribute("productList") != null) {
				List productList = (List)request.getAttribute("productList");
				int x = 0;
				for (Iterator iter = productList.iterator(); iter.hasNext();) {
		  			x++;
		  			ProductForm product = (ProductForm) iter.next();
		%>
		<tr>			
	   		<td width="10%" class="normal" align="center">
				<input type="radio" name="radio1" 
			   		onclick="productForm.productCode.value='<%=product.getProductCode()%>';
			   			 	 productForm.productName.value = '<%=product.getProductName()%>';
			   				 productForm.price.value='<%=product.getPrice()%>''"/>&nbsp;&nbsp;<%=product.getProductCode()%>
	   		</td>
	   		<td width="10%" class="normal" align="center"><%=product.getProductName()%></td>
		    <td width="20%" class="normal" align="center"><%=product.getPrice()%></td>	   		  		      	   		  
		    

		    <% if (product.getStatus().equals("AC")) { %>
		    <td width="10%" class="normal" align="center">&nbsp;Active</td>
		    <% } else if (product.getStatus().equals("CA")) { %>	
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
			productForm.productCode.focus()
		</SCRIPT>	
  </body>
</html:html>