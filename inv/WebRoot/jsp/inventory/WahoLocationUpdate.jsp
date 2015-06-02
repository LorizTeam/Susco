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
		
	String pic1 = "";
	if (request.getAttribute("pic1") != null) 
		pic1 = (String) request.getAttribute("pic1"); 
		
	String pic2 = "";
	if (request.getAttribute("pic2") != null) 
		pic2 = (String) request.getAttribute("pic2");
		
	String locaCode =  "";
	if (request.getAttribute("locaCode") != null)
		locaCode = (String) request.getAttribute("locaCode");
	
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
		var load = window.open('/dtac/jsp/admin/FileUpload.jsp?pathName=waholocation&picNo='+picNo+'&idCode='+idCode,'waholocation','scrollbars=no,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}	
	function reloadPic1() {
		img = document.getElementById('pic1');
		img.src = '/dtac/jsp/image/waholocation/'+document.warehouseForm.pic1.value
	}
	function reloadPic2() {
		img = document.getElementById('pic2');
		img.src = '/dtac/jsp/image/waholocation/'+document.warehouseForm.pic2.value
	}
	</script>
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'WahoLocationUpdate.jsp' starting page</title>
    
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
	<html:form action="/wahoLocationUpdate">
		<fieldset><legend><b class="blue">2.4 เพิ่มข้อมูลสถานที่เก็บสินค้า</b></legend>
		<table align="center" width="60%">
		<tr><td>&nbsp;</td></tr>
        <tr>
        	<td class="blue">คลังสินค้า&nbsp; :&nbsp; 
				<html:text property="wahoCode" size="2" maxlength="2"/>
		    </td>
		    <td class="blue">ชื่อคลัง&nbsp; :&nbsp; 
				<html:text property="wahoName" size="20" maxlength="20"/>
		    </td>		 
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
 			<td>&nbsp;&nbsp;&nbsp;</td>
		</tr>
		</table>
		</fieldset> <br/>
		
		<table align="center" border="0" cellpadding="0" cellspacing="0" width="90%" >
		<tr>
				<td class= "blue">สถานที่ &nbsp;&nbsp;
				<html:text property="locaCode" size="5" maxlength="2"/>&nbsp;&nbsp;
				</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class= "blue">รายละเอียด&nbsp;:&nbsp;
				<html:text property="locaName" size="20" maxlength="20"/>&nbsp;&nbsp;
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="blue">&nbsp;สถานะ :
						<html:select property="locaStatus" >
								<html:option value="AC">ใช้งาน</html:option>
								<html:option value="CA">ไม่ใช้งาน</html:option>
								</html:select>&nbsp;&nbsp;
			</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
			<td rowspan="2" width="30%" align="center">
								<a href="javascript:reloadPic1();"> 
								<img src=".\\jsp\\image\\waholocation\\<%=pic1.trim()%>" id="pic1" width="100" height="120" /> </a><br/><br/>
								
								<a href="javascript:uploadPicture('1','<%=locaCode.trim()%>');">Pic1</a>
								<html:text property="pic1" readonly = "true" />
							</td>&nbsp;&nbsp;
			<td rowspan="2" width="30%" align="center">
								<a href="javascript:reloadPic2();"> 
								<img src=".\\jsp\\image\\waholocation\\<%=pic2.trim()%>" id="pic2" width="100" height="120" /> </a><br/><br/>
								
								<a href="javascript:uploadPicture('2','<%=locaCode.trim()%>');">Pic2</a>
								<html:text property="pic2" readonly =  "true" />
							</td>
			</tr>
			<tr>
			<td>
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="warehouseform.button.save" />
 				</html:submit>
 			</td>
			</tr>
		<tr><td>&nbsp;</td></tr>
		</table>
			
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			warehouseForm.wahoCode.focus()
		</SCRIPT>	
  </body>
</html:html>