<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="css_blue_style.css" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'MemberSetPswd.jsp' starting page</title>
    
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
	<html:form action="/memberSetPswd" >
		<fieldset><legend><b class="blue">1.1 กำหนด/แก้ไขรหัสผ่าน</b></legend>
		<table align="center" width="90%">
 		<tr><td>&nbsp;</td></tr>		
		<tr>
 			<td class="normal">รหัสประจำตัว &nbsp;:&nbsp;
				<html:text property="memberID" size="10" maxlength="10" readonly="true"/>
 			</td>
			<td class="normal">ชื่อ &nbsp;:&nbsp;   
				<html:text property="firstName" size="20" readonly="true"/>
 				&nbsp;&nbsp;นามสกุล &nbsp;:&nbsp;
 				<html:text property="lastName"  size="15" readonly="true"/> 
 			</td>
 		</tr>
 		<tr><td>&nbsp;</td></tr>
 		<tr>
 			<td class="normal">ประเภทบุคลากร &nbsp;:&nbsp;
 				<html:text property="memberTypeCode" size="3" readonly="true"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<html:text property="memberTypeName" size="30" readonly="true"/>
 			</td>
 			<td class="normal">แผนก &nbsp;:&nbsp;
				<html:text property="deptCode" size="3" readonly="true"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<html:text property="deptName" size="30" readonly="true"/>				
 			</td>
 		</tr>
 		<tr><td>&nbsp;</td></tr> 		 		
 		<tr>
 			<td class="normal">Password &nbsp;:&nbsp;
				<html:password property="password" size="12" maxlength="20" readonly="true"/>
 			</td>
 		</tr>
 		<tr><td>&nbsp;</td></tr>
 		<tr><td class="red">ระบบจะกำหนด password เป็น 12345</td></tr>
 		<tr><td>&nbsp;</td></tr>
 		<tr>
 			<td align="center">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="adminResources" key="memberform.button.update" />
 				</html:submit>&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="adminResources" key="memberform.button.cancel" />
 				</html:submit>
 			</td>
				<html:hidden property="memberGrpCode" /> 			
		</tr>
		</table>
 		</fieldset>
	</html:form>	 	
  </body>
</html:html>