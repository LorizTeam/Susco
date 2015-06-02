<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm"%>

<%@ page import="com.dtac.inventory.form.CustomerForm" %>
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
    <title>ค้นหาผู้ขาย</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<script language="javascript">
	function returnCustomer(tCustCode, tCustName) {
		
		if (opener && !opener.closed){
			if  (customerForm.formName.value == 'materialTake') {
			    window.opener.document.materialTakeForm.vendCode.value =  tCustCode;
			 	window.opener.document.materialTakeForm.vendName.value = tCustName;
			  	
		 	 
		 
		 	} else alert('no form set');
		 	opener.focus();
		} 
		window.close();
		return;			
	}
	</script>    
  </head>
  
  <body class="blueback">
	<html:form action="customerSearch" >
		<fieldset><legend><b class="blue"><br>รายการผู้ซื้อ</b></legend>
		<table align="center" width="100%">
		<tr>
 			<td class="blue">รหัสผู้ซื้อ :&nbsp; 
 				<html:text property="customerCode" 		size="6" 	maxlength="6"/>
 			</td>
 			<td class="blue">ชื่อผู้ซื้อ&nbsp; :&nbsp; 
				<html:text property="customerName" 		size="20" 	maxlength="30"/>
 			</td>
		</tr>
		<tr>
			<td class="blue">คำที่ใช้ค้นหา &nbsp;:&nbsp;
				<html:text property="searchName"	size="20" 	maxlength="30"/>
			</td>
			<td class="blue">ประเภทผู้ซื้อ&nbsp; :&nbsp; 
				<html:select property="customerTypeCode">
					<html:option value="">ไม่ระบุ</html:option>	
					<%
						if (request.getAttribute("customerTypeList") != null) {
							List  TypeList = (List) request.getAttribute("customerTypeList");
							for (Iterator iterDept = TypeList.iterator(); iterDept.hasNext();) {
								MasterTableForm vendTypeForm = (MasterTableForm) iterDept.next();
					%>
					<html:option value="<%=vendTypeForm.getTypeCode()%>"><%=vendTypeForm.getThName()%></html:option>
					<%
							}
						}
					%>
				</html:select>
			</td> 			
 		</tr>
		<tr><td align="center" class="red">&nbsp;<%=alertMessage%></td>
 			<td class="normal" >
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="customerform.button.search" />
 				</html:submit> 				
 			</td>
 				<html:hidden property="formName"/>    
        </tr>		
		</table>
		</fieldset> 	
		
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightgray">
		<tr>	
			<td width="5%"  align="center" class="normal">ลำดับที่</td>		
			<td width="10%" align="center" class="normal">รหัสผู้ซื้อ </td>		
			<td width="20%" align="center" class="normal">ชื่อผู้ซื้อ </td>
			<td width="15%" align="center" class="normal">คำที่ใช้ค้นหา</td>
		</tr>
		</table>
	
		<table align="center" width="100%" border="1">
		<%	if (request.getAttribute("customerList") != null) {
				List customerList = (List)request.getAttribute("customerList");
				int x = 0;
				for (Iterator iter = customerList.iterator(); iter.hasNext();) {
		  			x++;
		  			CustomerForm customer = (CustomerForm) iter.next();
		%>
		<tr>
			<td width="5%" class="normal" align="center">&nbsp;<%=x%>.</td>			
	   		<td width="10%" class="normal" align="center">
				<a href="javascript:returnCustomer('<%=customer.getcustomerCode()%>','<%=customer.getcustomerName()%>');"> 
					<font color="blue"><%=customer.getcustomerCode()%></font></a>	   		
	   		
	   		</td>

		    <td width="20%" class="normal">&nbsp;<%=customer.getcustomerName()%></td>	   		  		      	   		  
		    <td width="15%" class="normal">&nbsp;<%=customer.getSearchName()%></td>  
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