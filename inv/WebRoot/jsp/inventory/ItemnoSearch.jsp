<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm"%>
<%@ page import="com.dtac.inventory.form.MaterialTypeForm" %>
<%@ page import="com.dtac.inventory.form.MaterialForm" %>
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
    <title>ค้นหา Item No</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<script language="javascript">
	function returnItemno(matCode, matName, matTypeCode, tMatTypeName, takecategories, tmatGrpName, refMatCode, trefMatName, matStuffCode, tmatStuffName,
	matColorCode, tmatColorName, trUnit, trUnitName, matBrandCode, tmatBrandName, stock1, stock2, normalPrice, empPrice, costPrice, specialPrice, vipPrice) {
		if (opener && !opener.closed){
			   } if (materialForm.formName.value == 'materialChange') {
			 	window.opener.document.materialRetuForm.matCode.value 			= matCode;
			 	window.opener.document.materialRetuForm.matName.value 			= matName;
			 	window.opener.document.materialRetuForm.matTypeCode.value 		= matTypeCode;
		 	 	window.opener.document.materialRetuForm.takecategories.value 	= takecategories;
		 	 	window.opener.document.materialRetuForm.refMatCode.value 		= refMatCode;
		 	 	window.opener.document.materialRetuForm.matStuffCode.value 		= matStuffCode;
		 	 	window.opener.document.materialRetuForm.matColorCode.value 		= matColorCode;
		 	 	window.opener.document.materialRetuForm.matBrandCode.value 		= matBrandCode;
		 	 	window.opener.document.materialRetuForm.stock1.value 			= stock1;
		 	 	window.opener.document.materialRetuForm.stock2.value 			= stock2;
		 	 	window.opener.document.materialRetuForm.normalPrice.value 		= normalPrice;
			 	window.opener.document.materialRetuForm.empPrice.value 			= empPrice;
		 	 	window.opener.document.materialRetuForm.costPrice.value 		= costPrice;
		 	 	window.opener.document.materialRetuForm.specialPrice.value 		= specialPrice;
		 	 	window.opener.document.materialRetuForm.vipPrice.value 			= vipPrice;
		 	 	window.opener.document.materialRetuForm.rUnit.value 			= trUnit;
			 	window.opener.document.materialRetuForm.rUnitName.value 		= trUnitName;
		 	 	window.opener.document.materialRetuForm.matTypeName.value 		= tMatTypeName;
		 	 	window.opener.document.materialRetuForm.matGrpName.value 		= tmatGrpName;
		 	 	window.opener.document.materialRetuForm.refMatName.value 		= trefMatName;
		 	 	window.opener.document.materialRetuForm.matStuffName.value 		= tmatStuffName;
		 	 	window.opener.document.materialRetuForm.matColorName.value 		= tmatColorName;
		 	 	window.opener.document.materialRetuForm.rUnitName.value 		= trUnitName;
		 	 	window.opener.document.materialRetuForm.matBrandName.value 		= tmatBrandName;
		 	opener.focus();
		} 
		window.close();
		return;			
	}
	</script>    
  </head>
  
  <body class="blueback">
	<html:form action="itmenoSearch" >
		<fieldset><legend><b class="blue"><br>Item No</b></legend>
		<table align="center" width="100%">
		<tr>
 			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;รหัสสินค้า&nbsp;:&nbsp;
 				<html:text property="matCode" 		size="15" 	maxlength="20"/>	
 				
 				&nbsp;&nbsp;Collection&nbsp; :&nbsp; 
				<html:select property="matTypeCode" >
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
				<html:hidden property="formName" /> 
 			 
 					&nbsp;&nbsp;ประเภทสินค้า&nbsp; :&nbsp;  
		<html:select property="matGrpCode"> 
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
				&nbsp;&nbsp;แบรนด์สินค้า&nbsp;:&nbsp; 
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
	</td></tr>
	<tr>
			<td class="blue"><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;สถานะ &nbsp;: 
				<html:select property="matStatus"> 
			   	<html:option value="">ไม่ระบุ</html:option> 
				<html:option value="AC">ใช้งาน</html:option> 
				<html:option value="CA">ยกเลิก</html:option> 
				</html:select>	 
 				 
 				&nbsp;&nbsp;&nbsp;ชนิดวัตถุดิบ&nbsp;:&nbsp; 
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
				&nbsp;&nbsp;&nbsp;หน่วยสินค้า &nbsp;:&nbsp; 
				<html:select property="pUnit">		 
				<html:option value="">ไม่ระบุ</html:option>				 
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
			 
				&nbsp;&nbsp;&nbsp;&nbsp; สี&nbsp;:&nbsp; 
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
				&nbsp;&nbsp;size&nbsp;:&nbsp; 
				<html:select property="refMatCode">	 
				<html:option value="">ไม่ระบุ</html:option>						 
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
			</td></tr>
		
		<tr><td align="center" class="red">&nbsp;<%=alertMessage%></td>
 			<td class="normal" >
 				<html:submit property="reqCode">  
 					 <bean:message bundle="inventoryResources" key="materialform.button.search" />
 				</html:submit>				
 			<br></td>
 				<html:hidden property="formName" />    
        </tr>		
		</table>
		</fieldset> 	
		
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightgray">
		<tr>	
			<td width="8%"  align="center" class="normal">ลำดับที่</td>		
			<td width="12%" align="center" class="normal">รหัสสินค้า</td>		
			<td width="35%" align="center" class="normal">ชื่อสินค้า</td>
			<td width="20%" align="center" class="normal">ประเภทสินค้า</td>
			<td width="5%" align="center" class="normal">ชนิด</td>
			<td width="10%" align="center" class="normal">สี</td>
			<td width="10%" align="center" class="normal">ขนาด</td>
		</tr>
		</table>
	
		<table align="center" width="100%" border="1">
		<%	if (request.getAttribute("materialList") != null) {
				List materialList = (List)request.getAttribute("materialList");
				int x = 0;
				for (Iterator iter = materialList.iterator(); iter.hasNext();) {
		  			x++;
		  			MaterialForm materials = (MaterialForm) iter.next();
		  			if (materials.getMatStatus().equals("CA")) {
		%>
		<tr>
		<% } else { %>
		
		<% } %>			
	   		<td width="8%" class="normal" align="center">
				<input type="radio" name="radio1" 
			   		onclick="materialForm.matCode.value='<%=materials.getMatCode()%>';
			   				 materialForm.matTypeCode.value='<%=materials.getMatTypeCode()%>';
			   				 materialForm.matGrpCode.value='<%=materials.getMatGrpCode()%>';
			   				submitView();"/>&nbsp;&nbsp;<%=x%>			
			 		</td>
	   		<td width="12%" align="center" class="normal">&nbsp;<a href="javascript:returnItemno('<%=materials.getMatCode()%>','<%=materials.getMatName()%>','<%=materials.getMatTypeCode()%>','<%=materials.getMatTypeName()%>',
				'<%=materials.getMatGrpCode()%>','<%=materials.getMatGrpName()%>','<%=materials.getRefMatCode()%>','<%=materials.getRefMatName()%>','<%=materials.getMatStuffCode()%>','<%=materials.getMatStuffName()%>','<%=materials.getMatColorCode()%>',
				'<%=materials.getMatColorName()%>','<%=materials.getpUnit()%>','<%=materials.getpUnitName()%>','<%=materials.getMatBrandCode()%>','<%=materials.getMatBrandName()%>','<%=materials.getStock1()%>','<%=materials.getStock2()%>',
				'<%=materials.getNormalPrice()%>','<%=materials.getEmpPrice()%>','<%=materials.getCostPrice()%>',
				'<%=materials.getSpecialPrice()%>','<%=materials.getVipPrice()%>') ;"> 
					<font color="blue"><%=materials.getMatCode()%></font></a></td>
		    <td width="35%" class="normal">&nbsp;<%=materials.getMatName()%></td>   		  		      	   		  
		    <td width="20%" class="normal">&nbsp;<%=materials.getMatTypeName()%>&nbsp;-&nbsp;<%=materials.getMatGrpName()%></td>
		    <td width="5%" align="center" class="normal">&nbsp;<%=materials.getMatStuffName()%></td>  
		    <td width="10%" align="center" class="normal">&nbsp;<%=materials.getMatColorName()%></td>
		    <td width="10%" align="center" class="normal">&nbsp;<%=materials.getRefMatCode()%></td>
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>   
		</table> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			materialForm.matCode.focus()
		</SCRIPT>	
  </body>
</html:html>