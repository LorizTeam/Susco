<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm"%>

<%@ page import="com.dtac.inventory.form.VenderForm" %>
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
	function returnVender(tVendCode, tVendName) {
		
		if (opener && !opener.closed){
			if (venderForm.formName.value == 'materialRecv') {
			 	window.opener.document.materialRecvForm.vendCode.value = tVendCode;
			 	window.opener.document.materialRecvForm.vendName.value = tVendName;
			} else if (venderForm.formName.value == 'materialRetu') {
			 	window.opener.document.materialRetuForm.vendCode.value = tVendCode;
			 	window.opener.document.materialRetuForm.vendName.value = tVendName;
			} else if (venderForm.formName.value == 'materialTake') {
			 	window.opener.document.materialTakeForm.requesterno.value = tVendCode;
			 	window.opener.document.materialTakeForm.requestername.value = tVendName; 	
		 	} else if (venderForm.formName.value == 'material') {
			 	window.opener.document.materialForm.matSupplCode.value = tVendCode;
			 	window.opener.document.materialForm.matSupplName.value = tVendName;
			 } else if (venderForm.formName.value == 'materialTransfer') {
			 	window.opener.document.materialTransferForm.vendCode.value = tVendCode;
			 	window.opener.document.materialTransferForm.vendName.value = tVendName;
		 	} else if (venderForm.formName.value == 'pr') {
			 	window.opener.document.prForm.vendCode.value = tVendCode;
			 	window.opener.document.prForm.vendName.value = tVendName;
			 	
		 	} else if (venderForm.formName.value == 'po') {
			 	window.opener.document.poForm.vendCode.value = tVendCode;
			 	window.opener.document.poForm.vendName.value = tVendName;
			 	
		 	} else alert('no form set');
		 	opener.focus();
		} 
		window.close();
		return;			
	}
	</script>    
  </head>
  
  <body class="blueback">
	<html:form action="venderSearch" >
		<fieldset><legend><b class="blue"><br>รายการผู้ขาย</b></legend>
		<table align="center" width="100%">
		<tr>
 			<td class="blue">รหัสผู้ขาย&nbsp;:&nbsp;
 				<html:text property="venderCode" 		size="6" 	maxlength="6"/>
 			</td>
 			<td class="blue">ชื่อผู้ขาย &nbsp;:&nbsp;
				<html:text property="venderName" 		size="20" 	maxlength="30"/>
 			</td>
		</tr>
		<tr>
			<td class="blue">คำที่ใช้ค้นหา &nbsp;:&nbsp;
				<html:text property="searchName"	size="20" 	maxlength="30"/>
			</td>
			<td class="blue">ประเภทผู้ขาย &nbsp;:&nbsp;
				<html:select property="venderTypeCode">
					<html:option value="">ไม่ระบุ</html:option>	
					<%
						if (request.getAttribute("vendTypeList") != null) {
							List vendTypeList = (List) request.getAttribute("vendTypeList");
							for (Iterator iterDept = vendTypeList.iterator(); iterDept.hasNext();) {
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
 					<bean:message bundle="inventoryResources" key="venderform.button.search" />
 				</html:submit> 				
 			</td>
 				<html:hidden property="formName"/>    
        </tr>		
		</table>
		</fieldset> 	
		
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightgray">
		<tr>	
			<td width="5%"  align="center" class="normal">ลำดับที่</td>		
			<td width="10%" align="center" class="normal">รหัสผู้ขาย</td>		
			<td width="20%" align="center" class="normal">ชื่อผู้ขาย</td>
			<td width="15%" align="center" class="normal">คำที่ใช้ค้นหา</td>
		</tr>
		</table>
	
		<table align="center" width="100%" border="1">
		<%	if (request.getAttribute("venderList") != null) {
				List venderList = (List)request.getAttribute("venderList");
				int x = 0;
				for (Iterator iter = venderList.iterator(); iter.hasNext();) {
		  			x++;
		  			VenderForm vender = (VenderForm) iter.next();
		%>
		<tr>
			<td width="5%" class="normal" align="center">&nbsp;<%=x%>.</td>			
	   		<td width="10%" class="normal" align="center">
				<a href="javascript:returnVender('<%=vender.getVenderCode()%>','<%=vender.getVenderName()%>');"> 
					<font color="blue"><%=vender.getVenderCode()%></font></a>	   		
	   		
	   		</td>

		    <td width="20%" class="normal">&nbsp;<%=vender.getVenderName()%></td>	   		  		      	   		  
		    <td width="15%" class="normal">&nbsp;<%=vender.getSearchName()%></td>  
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>   
		</table> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			venderForm.vendCode.focus()
		</SCRIPT>	
  </body>
</html:html>