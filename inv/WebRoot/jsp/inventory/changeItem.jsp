<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.inventory.form.changeItemAddForm" %>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
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
    <title>My JSP 'MaterialList.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<script Language="javascript">
	function submitView() {        
        document.materialForm.action="/dtac/materialList.do?reqCode=View";
        document.materialForm.submit();         
    } 
	</script> 
  </head>
  
  <body class="blueback">
	<html:form action="changeItem" >
		<fieldset><legend><b class="blue">3.5 เปลี่ยนสินค้า</b></legend>
		<table align="center" width="100%">
		<tr>
 			<td class="blue">รหัสสินค้า&nbsp;:&nbsp;
 				<html:text property="matCode" 		size="20" 	maxlength="20"/>
 			</td>
 			<td class="blue">ชื่อสินค้า &nbsp;:&nbsp;
				<html:text property="matName" 		size="30" 	maxlength="30"/>
 			</td>
 			<td class="blue">คำค้นหา &nbsp;:&nbsp;
				<html:text property="matSearchName" size="20" 	maxlength="30"/>
 			</td>
 		</tr> 		
 		<tr>
			<td class="blue">Collection&nbsp; :&nbsp; 
				<html:select property="matTypeCode" 
					onchange="materialForm.matGrpCode.value='**';
				 			  materialForm.action='dtacy/materialList.do?reqCode=Search&matGrpCode=**';	
				 			  materialForm.submit();">
					<html:option value="">ไม่ระบุ</html:option>
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
		    <td class="blue">ประเภทสินค้า&nbsp; :&nbsp; 
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
			<td class="blue">สถานะ &nbsp;:&nbsp;
				<html:select property="matStatus" >
			   		<html:option value="">ไม่ระบุ</html:option>
					<html:option value="AC">ใช้งาน</html:option>
					<html:option value="CA">ยกเลิก</html:option>
				</html:select>	
 			</td>		    	    
		</tr>
		<tr>
			<td class="blue">ชนิดวัตถุดิบ&nbsp;:&nbsp;
				<html:select property="matStuffCode">	
					<html:option value="">ไม่ระบุ</html:option>					
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
			</td>		    		    
			<td class="blue">สี&nbsp;:&nbsp;
				<html:select property="matColorCode">	
					<html:option value="">ไม่ระบุ</html:option>					
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
			</td>
			<td class="blue">รหัสสินค้าอ้างอิง &nbsp;:&nbsp;
				<html:text property="refMatCode" size="20" maxlength="30"/>
		    </td>		
		</tr>
		<tr><td align="center" class="red">&nbsp;<%=alertMessage%></td>	
 			<td colspan="2" align="center" class="normal">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="change.button.search" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp; 				
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="change.button.recv" />
 				</html:submit>&nbsp;&nbsp; &nbsp;<html:submit property="reqCode">  
 					 <bean:message bundle="inventoryResources" key="change.button.issue" />
 				</html:submit> 				
 			</td>
 				<html:hidden property="qtyMinStock"/>		    		
 		</tr>
		</table>
		</fieldset> 	
		
	 	
	
		<table align="center" width="100%" border="1">
		<tr bgcolor="#8E8EA3">	
			<th align="center" class="normal">ลำดับที่</th>	
			<th align="center" class="normal">ประเภทรายการ</th>	
			<th align="center" class="normal">รหัสรายการ</th>		
			<th align="center" class="normal">รหัสสินค้า</th>
		</tr>
		<%
		if (request.getAttribute("chglist") != null) {
				List chglist = (List)request.getAttribute("chglist");
				int x = 0;
				for (Iterator iter = chglist.iterator(); iter.hasNext();) {
		  			x++;
		  			changeItemAddForm chgItem = (changeItemAddForm) iter.next();
		 %>
		<tr>			
		    <td width="5%"  class="normal" align="center"><%=x%></td>   	   		  		      	   		  
		    <td width="10%" class="normal">&nbsp;<%=chgItem.getDocType()%></td>
		    <td width="15%" class="normal">&nbsp;<%=chgItem.getDocCode()%></td>  
		    <td width="10%" class="normal">&nbsp;<%=chgItem.getPrCode()%></td>
	    </tr>  
	 	<% }
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>   
		</table> 	
	</html:form>
  </body>
</html:html>