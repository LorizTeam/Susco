<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MemberTypeForm" %>
<%@ include file="../admin/css_blue_style.css" %>
<%
 	String path = request.getContextPath();
 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String alertMessage = "";
	if(request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'MemberTypeList.jsp' starting page</title>
    
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
	<html:form action="/memberTypeList">
		<fieldset><legend><b class="normal">1.2 รายการประเภทบุคลากร</b></legend>
		<table align="center" width="80%">
 		<tr>	
 			<td class="blue">รหัสประเภท &nbsp;:&nbsp;
				<html:text property="memberTypeCode" size="2" maxlength="2"/>
 			</td>
			<td class="blue">กลุ่ม &nbsp;:&nbsp; 
				<html:select property="memberGrpCode" >
					<html:option value=""> &nbsp;ทั้งหมด&nbsp;</html:option>
					<html:option value="0">&nbsp;รายวัน&nbsp;</html:option>
					<html:option value="1">&nbsp;รายเดือน&nbsp;</html:option>								
				</html:select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
 		<tr>
 			<td colspan="2" align="center">
 				<html:submit property="reqCode"> 
					<bean:message bundle="adminResources" key="memberform.button.search" />	
 				</html:submit>&nbsp;&nbsp;&nbsp;
 					<html:hidden property="memberTypeName"/>
 			</td>
		</tr>
		</table>
		</fieldset>
 	
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="90%" bgcolor="lightslategray">
		<tr>	
			<td width="8%"  align="center" class="normal">ลำดับที่</td>				
			<td width="25%" align="center" class="normal">รหัส - รายการ</td>		
			<td width="10%" align="center" class="normal">ประเภท</td>
			<td width="10%" align="center" class="normal">เวลาเริ่มงาน</td>	
			<td width="10%" align="center" class="normal">เวลาเลิกงาน</td>							
		</tr>
		</table>
	
		<table align="center" width="90%" border="1">
		<%	if (request.getAttribute("memberTypeList") != null) {
				List memberTypeList = (List)request.getAttribute("memberTypeList");
				for (int x=0; x<memberTypeList.size(); x++) {
					MemberTypeForm memberTypes = (MemberTypeForm) memberTypeList.get(x);
		%>
		<tr>
   		  	<td width="8%"  class="normal" align="center">
		   		  <input type="radio" name="radio1" 
	   		  		onclick="memberTypeForm.memberTypeCode.value='<%=memberTypes.getMemberTypeCode()%>';"/><%=x+1%>.	   		  	
   		  	</td>
	      	<td width="25%" class="normal">&nbsp;<%=memberTypes.getMemberTypeCode()%>&nbsp;-&nbsp;<%=memberTypes.getMemberTypeName()%></td>
	      	<td width="10%" class="normal">&nbsp;<%=memberTypes.getMemberGrpCode()%>&nbsp;-&nbsp;<%=memberTypes.getMemberGrpName()%></td>
	      	<td width="10%" class="normal" align="center">&nbsp;<%=memberTypes.getTimeStart()%></td>
	      	<td width="10%" class="normal" align="center">&nbsp;<%=memberTypes.getTimeStop()%></td>	      	
	    </tr>  
		 <% 	} 
	 		} else {
		%>
		<tr><td align="center" colspan="5">No Data Found</td></tr>
		<%	} %>   	
		</table>
  	</html:form>	
 	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			memberTypeForm.memberTypeCode.focus()
		</SCRIPT>
  </body>
</html:html>