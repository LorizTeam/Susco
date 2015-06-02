<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
<%@ page import="com.dtac.inventory.form.WarehouseForm" %>
<%@ include file="../admin/css_blue_style.css" %>
<%
  	String path = request.getContextPath();
  	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 
		
		
	String wahoCode =  "";
	if (request.getAttribute("wahoCode") != null)
		wahoCode = (String) request.getAttribute("wahoCode");
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<script type="text/javascript" Language="javascript">
	function newDetail() {
		document.warehouseForm.locaCode.value ='';
		document.warehouseForm.locaName.value = '';
		document.warehouseForm.locaStatus.value= 'AC';
	}	
	function uploadPicture(picNo, idCode) {
		var load = window.open('/dtac/jsp/admin/FileUpload.jsp?pathName=warehouse&picNo='+picNo+'&idCode='+idCode,
		                  'waholocation','scrollbars=no,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}	
	function reloadPic1() {
		img = document.getElementById('pic1');
		img.src = '/dtac/jsp/image/warehouse/'+document.warehouseForm.pic1.value
	}
	function reloadPic2() {
		img = document.getElementById('pic2');
		img.src = '/dtac/jsp/image/warehouse/'+document.warehouseForm.pic2.value
	}
	</script>
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'WahoLocationView.jsp' starting page</title>
    
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
	<html:form action="/wahoLocationView" >
		<fieldset><legend><b class="normal">Add & View</b></legend>
		<table align="center" width="60%">
		<tr><td>&nbsp;</td></tr>
        <tr>
        	<td class="normal">Warehouse Code&nbsp;:&nbsp; 
				<html:text property="wahoCode" size="2" maxlength="2" readonly="true"/>
		     	&nbsp;&nbsp;
		     	Warehouse Name&nbsp;:&nbsp; 
				<html:text property="wahoName" size="30" maxlength="50" readonly="true"/>
		    </td>		 
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td class="red">&nbsp;<%=alertMessage%></td></tr>		
		</table>
		</fieldset> <br/>
		
		<table align="center" border="0" cellpadding="0" cellspacing="0" width="90%" >
		<tr>
			<td class= "blue">Warehouse Code &nbsp;&nbsp;
				<html:text property="locaCode" size="5" maxlength="2"/>&nbsp;&nbsp;
				<a href="javascript:newDetail();">เพิ่มรายการใหม่</a>
			</td>
			<td class= "blue">Warehouse Name &nbsp;:&nbsp;
				<html:text property="locaName" size="20" maxlength="20"/>&nbsp;&nbsp;
			</td>
			<td class="blue">Status&nbsp;:&nbsp;
				<html:select property="locaStatus" >
					<html:option value="AC">Active</html:option>
					<html:option value="CA">Inactive</html:option>
				</html:select>
			</td>
			<td>
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.save" />
 				</html:submit>
 			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		</table>
		
		<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightslategray">
	 	<tr>	
			<td width="5%"  align="center" class="normal">No.</td>		
			<td width="7%"  align="center" class="normal">Warehouse Code</td>
			<td width="20%" align="center" class="normal">Warehouse Name</td>
			<td width="20%" align="center" class="normal">Picture 1</td>
			<td width="20%" align="center" class="normal">Picture 2</td>
			<td width="10%" align="center" class="normal">Status</td>
		</tr>
		</table>
					
	
		<table align="center" width="100%" border="1">
		<%	if (request.getAttribute("locationList") != null) {
				List locationList = (List)request.getAttribute("locationList");
				int x = 0;
				for (Iterator iter = locationList.iterator(); iter.hasNext();) {
		  			x++;
					WarehouseForm warehouses = (WarehouseForm) iter.next();
		%>
		<tr>			
   		  	<td width="5%" class="normal" align="center">
				<input type="radio" name="radio1" 
			   		onclick="warehouseForm.locaCode.value='<%=warehouses.getLocaCode()%>';
			   		         warehouseForm.locaName.value='<%=warehouses.getLocaName()%>';
			   		         warehouseForm.locaStatus.value ='<%=warehouses.getLocaStatus()%>';"/>&nbsp;&nbsp;<%=x%>   		  	
   		  	</td>
		    <td width="7%" class="normal">&nbsp;<%=warehouses.getLocaCode()%>&nbsp;</td>
		    <td width="20%" class="normal">&nbsp;<%=warehouses.getLocaName()%>&nbsp;</td>
		    <td width="20%" class="normal">
		    	<a href="javascript:uploadPicture('1','<%=warehouses.getWahoCode().trim()%><%=warehouses.getLocaCode().trim()%>');">Pic1</a>
		    	&nbsp;&nbsp;&nbsp;
		    	<img src=".\\jsp\\image\\warehouse\\<%=warehouses.getPic1().trim()%>" id="pic1" width="120" height="100" />
		    	&nbsp;<%=warehouses.getPic1()%>
		    </td>
		    <td width="20%" class="normal">
		    	<a href="javascript:uploadPicture('2','<%=warehouses.getWahoCode().trim()%><%=warehouses.getLocaCode().trim()%>');">Pic2</a>
		    	&nbsp;&nbsp;&nbsp;
		    	<img src=".\\jsp\\image\\warehouse\\<%=warehouses.getPic2().trim()%>" id="pic2" width="120" height="100" />
		    	&nbsp;<%=warehouses.getPic2()%>
		    </td>
 			<% if (warehouses.getLocaStatus().equals("CA")) { %>
		    <td width="10%" class="red" align="center">&nbsp;ไม่ใช้งาน</td>	   		  		      		      		      
			<% } else { %>		      
		    <td width="10%" class="normal" align="center">&nbsp;ใช้งาน</td>	   		  		      		      		      		      
		    <% } %>	      	     	
		    	   		  
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="3">No Data Found</td></tr>
		<%	} %>   
		</table>	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			warehouseForm.wahoCode.focus()
		</SCRIPT>	
  </body>
</html:html>