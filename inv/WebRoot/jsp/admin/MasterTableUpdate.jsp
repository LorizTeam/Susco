<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java"  pageEncoding="utf-8"%>
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
    <title>My JSP 'MasterTableUpdate.jsp' starting page</title>
    
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
	<html:form action="/masterTableUpdate" >
		<fieldset><legend><b class="normal">Adjust</b></legend>
		<table align="center" width="60%">
		<tr><td>&nbsp;</td></tr>
 		<tr>	
			<td class="normal">Data&nbsp;:&nbsp;
		    	<html:text property="grpName"  size="20" maxlength="40" readonly="true"/> 
			</td>		
		</tr>
		<tr><td>&nbsp;</td></tr>
 		<tr>	
			<td class="normal">Code &nbsp;:&nbsp;
		    	<html:text property="typeCode"  size="2" maxlength="2" readonly="true"/> 
			</td>		
		</tr>			  	
		<tr><td>&nbsp;</td></tr>
 		<tr>	
			<td class="blue">Description (ไทย) &nbsp;:&nbsp;
		    	<html:text property="thName"  size="30" maxlength="40" /> 
			</td>		
		</tr>			  	
		<tr><td>&nbsp;</td></tr>
 		<tr>	
			<td class="blue">Description (อังกฤษ) &nbsp;:&nbsp;
		    	<html:text property="enName"  size="30" maxlength="40" /> 
			</td>		
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
		    <td class="blue">Status&nbsp;:&nbsp;
       			<html:select property="status" >
        			<html:option value="AC">Active</html:option>
        			<html:option value="CA">InActive</html:option>        			
			  	</html:select>
			</td>
		</tr>
		<tr>
			<td align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
					<bean:message bundle="adminResources" key="memberform.button.update" />	
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
					<bean:message bundle="adminResources" key="memberform.button.cancel" />	
 				</html:submit>
 			</td>
 				<html:hidden property="applTypeCode" />
 				<html:hidden property="grpCode" />
		</tr>
		</table>
		</fieldset>
  	</html:form>	
  </body>
</html:html>