<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
<%@ page import="com.dtac.inventory.form.MaterialForm" %>
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
    <title>My JSP 'MaterialAddNew.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<script Language="javascript">
	function getVender() {
		var load = window.open('/dtac/venderSearchSetup.do?formName=material','material','scrollbars=no,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}	
	function resubmit(form_name, method_value) { 
        document.forms[form_name].matGrpCode.value='';
        document.forms[form_name].action="/dtac/materialAddNew.do?reqCode=Add Data";
        document.forms[form_name].submit();         
    } 
	</script>    
  </head>
  
  <body class="blueback">
	<html:form action="materialAddNew" >
		<fieldset><legend><b class="normal">Add</b></legend>
		<table align="center" width="100%">
		<tr><td>&nbsp;</td></tr>
        <tr>
        	<td class="blue">Product Code&nbsp; <font color="red">*</font>:&nbsp;
				<html:text property="matCode" size="20" maxlength="20"/>
		    </td>
		    <td class="blue">Serial No&nbsp; <font color="red">*</font>:&nbsp;
				<html:text property="serial" size="30" maxlength="50"/>
		    </td>
		    <td class="blue">Product Name&nbsp;<font color="red">*</font>:&nbsp;
				<html:text property="matName" size="30" maxlength="60"/>
		    </td>

		    
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
		   		<td class="blue">Sender Name&nbsp;:&nbsp;
				<html:text property="matSendName" size="20" 	maxlength="30"/>
 			</td>
 			<td class="blue">Vender&nbsp;:&nbsp;
				<html:text property="matSupplCode" size="7"  maxlength="10"/>
				<a href="javascript:getVender();"> <img src="jsp/icons/edit.gif" /> </a>
				<html:text property="matSupplName" size="30" maxlength="100" readonly="true"/>
			</td>
        	<td class="blue">Search &nbsp;:&nbsp;
				<html:text property="matSearchName" size="30" maxlength="50"/>
		    </td>				
		    
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
        	<td class="blue">Collection&nbsp; :&nbsp; 
				<html:select property="matTypeCode" 
					onchange="materialForm.matGrpCode.value='';
				 			materialForm.action='/dtac/materialList.do?reqCode=Add Data';	
				 			materialForm.submit();">
					<% if (request.getAttribute("matTypeList") != null) {
							List matTypeList = (List)request.getAttribute("matTypeList");
							for (int x=0; x<matTypeList.size(); x++) {
								MaterialTypeForm matTypes =(MaterialTypeForm) matTypeList.get(x);
					%>
			   		<html:option value="<%=(String)matTypes.getMatTypeCode()%>">
		   				<%=(String)matTypes.getMatTypeName()%>
			   		</html:option>
					<% 		} 
						} 
					%>
				</html:select>				
		    </td>
		    <td class="blue">Category &nbsp;:&nbsp;
				<html:select property="matGrpCode" >
					<html:option value="">ไม่ระบุ</html:option>
					<% if (request.getAttribute("matGrpList") != null) {
							List matGrpList = (List)request.getAttribute("matGrpList");
							for (int x=0; x<matGrpList.size(); x++) {
								MaterialTypeForm matGrps =(MaterialTypeForm) matGrpList.get(x);
					%>
			   		<html:option value="<%=(String)matGrps.getMatGrpCode()%>">
		   				<%=(String)matGrps.getMatGrpName()%>
			   		</html:option>
					<% 		} 
						} 
					%>
				</html:select>	
				</td>
				<td class="blue">Colour&nbsp;:&nbsp;
				<html:select property="matColorCode">						
					<% if (request.getAttribute("colorList") != null) {
						List colorList = (List)request.getAttribute("colorList");
	   					for (Iterator iterOrder = colorList.iterator(); iterOrder.hasNext();) {
		   					MasterTableForm colorForm = (MasterTableForm) iterOrder.next();
	       			%>
	       				<html:option value="<%=colorForm.getTypeCode()%>"><%=colorForm.getThName()%></html:option>
					<% 		} 
						} 
					%>	
				</html:select>
				&nbsp;&nbsp;&nbsp;Size&nbsp;:&nbsp;
				<html:select property="refMatCode">						
					<% if (request.getAttribute("sizeList") != null) {
						List sizeList = (List)request.getAttribute("sizeList");
	   					for (Iterator iterOrder = sizeList.iterator(); iterOrder.hasNext();) {
		   					MasterTableForm sizeForm = (MasterTableForm) iterOrder.next();
	       			%>
	       				<html:option value="<%=sizeForm.getTypeCode()%>"><%=sizeForm.getThName()%></html:option>
					<% 		} 
						} 
					%>	
				</html:select>					
		    </td>
					    
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="blue">Brand&nbsp;:&nbsp;
				<html:select property="matBrandCode">						
					<% if (request.getAttribute("brandList") != null) {
						List brandList = (List)request.getAttribute("brandList");
	   					for (Iterator iterOrder = brandList.iterator(); iterOrder.hasNext();) {
		   					MasterTableForm brandForm = (MasterTableForm) iterOrder.next();
	       			%>
	       				<html:option value="<%=brandForm.getTypeCode()%>"><%=brandForm.getThName()%></html:option>
					<% 		} 
						} 
					%>	
				</html:select>	
			</td>
			<td class="blue">Unit&nbsp;:&nbsp;
				<html:select property="pUnit">						
				<% if (request.getAttribute("unitList") != null) {
					List unitList = (List)request.getAttribute("unitList");
   					for (Iterator iterOrder = unitList.iterator(); iterOrder.hasNext();) {
	   					MasterTableForm unitForm = (MasterTableForm) iterOrder.next();
       			%>
       				<html:option value="<%=unitForm.getTypeCode()%>"><%=unitForm.getThName()%></html:option>
				<% 		} 
					} 
				%>			
				</html:select>
				<html:hidden property="rUnit"/>
				<html:hidden property="iUnit"/>	
				</td>
				<td class="blue">Group&nbsp;:&nbsp;
				<html:select property="matStuffCode">						
					<% if (request.getAttribute("stuffList") != null) {
						List stuffList = (List)request.getAttribute("stuffList");
	   					for (Iterator iterOrder = stuffList.iterator(); iterOrder.hasNext();) {
		   					MasterTableForm stuffForm = (MasterTableForm) iterOrder.next();
	       			%>
	       				<html:option value="<%=stuffForm.getTypeCode()%>"><%=stuffForm.getThName()%></html:option>
					<% 		} 
						} 
					%>	
				</html:select>		
				&nbsp;&nbsp;&nbsp;&nbsp;Status &nbsp;:&nbsp;
				<html:select property="matStatus" >
					<html:option value="AC">Active</html:option>
					<html:option value="CA">Inactive</html:option>
				</html:select>	
					
			</td>
		</tr>
		
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="blue">
						Cost &nbsp;:&nbsp;
			<html:text property="costPrice" size="7" maxlength="7" />
		    &nbsp;&nbsp;Retail Price&nbsp;:&nbsp;
			<html:text property="normalPrice" size="7" maxlength="7" /></td>
			<td class="blue">
			Employee Price&nbsp;:&nbsp;
			<html:text property="empPrice" size="7" maxlength="7" />
			&nbsp;&nbsp;VIP Price&nbsp;:&nbsp;
			<html:text property="vipPrice" size="7" maxlength="7" /></td>
		     <td class="blue">
		     Special Price(Sale)&nbsp;:&nbsp;
			<html:text property="specialPrice" size="7" maxlength="7" />
			</td> 
		   
		     <td class="blue">		
			</td>    
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			  <td class = "blue">Minimum Stock&nbsp;:&nbsp;
		     	<html:text property="qtyMinStock" size="10" maxlength="10" /> 
		     </td>
			<td class="blue">Maximum Stock&nbsp;:&nbsp;
		     	<html:text property="qtyMaxStock" size="10" maxlength="10" /> 
		   </td>
		   <td class="blue">
		   	Re-order Point&nbsp;:&nbsp;
		     	<html:text property="rop" size="13" maxlength="15" />
		   </td>
		 </tr>
		 <tr><td>&nbsp;</td></tr>
		<tr>
        	<td class="blue" colspan="2">Remark&nbsp;:&nbsp;				
				<html:textarea  property="matRemark" cols="80" rows="3" />	</td>
				<td class="blue">&nbsp;&nbsp;DTAC-Chamchuri&nbsp;
		     	<html:text property="stock1" size="7" maxlength="7" ></html:text>
		     	&nbsp;Supplier-TNT&nbsp;
		     	<html:text property="stock2" size="7" maxlength="7" ></html:text>			
		    </td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
 		<tr>
 			<td align="center">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialform.button.adddata" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="materialform.button.cancel" />
 				</html:submit>
 			</td>
		</tr>
		</table>	
		</fieldset> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			materialForm.matCode.focus()
		</SCRIPT>	
  </body>
</html:html>