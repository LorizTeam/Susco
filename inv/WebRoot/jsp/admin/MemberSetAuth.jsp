<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MemberAuthForm" %>
<%@ include file="css_blue_style.css" %>
<%
 	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'MemberSetAuth.jsp' starting page</title>
    
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
	<html:form action="/memberSetAuth" >
		<fieldset><legend><b class="blue">1.1 กำหนดสิทธิ์การใช้งาน</b></legend>
		<table align="center" width="90%">
		<tr>
 			<td class="normal">รหัสประจำตัว &nbsp;:&nbsp;
				<html:text property="memberID" size="10" readonly="true"/>
 			</td>
			<td class="normal">ชื่อ &nbsp;:&nbsp;   
				<html:text property="firstName" size="20" readonly="true"/>
 				&nbsp;&nbsp;นามสกุล &nbsp;:&nbsp;
 				<html:text property="lastName"  size="15" readonly="true"/> 
 			</td>
 		</tr>
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
 		<tr><td>&nbsp;</td>
 			<td>
 				<html:submit property="reqCode"> 
 					<bean:message bundle="adminResources" key="memberform.button.update" />
 				</html:submit>&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="adminResources" key="memberform.button.cancel" />
 				</html:submit> 				
 			</td>
	 			<html:hidden property="appType" />
	 			<html:hidden property="deptCode" />
	 			<html:hidden property="memberGrpCode" />
		</tr>
		</table>
		</fieldset>	

	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="80%" bgcolor="lightslategray">
		<tr>	
			<td width="5%"  class="normal" align="center">รายการ</td>		
			<td width="35%" class="normal" align="center">ฟังก์ชันการทำงาน</td>
			<td width="10%" class="normal" align="center">สิทธิ์แก้ไขข้อมูล</td>
			<td width="10%" class="normal" align="center">สิทธิ์เรียกดูข้อมูล</td>
			<td width="10%" class="normal" align="center">สิทธิ์อนุมัติ/หัวหน้า</td>
		</tr>
		</table>
	
		<table align="center" width="80%" border="1">
		<%	if (request.getAttribute("resultList") != null) {
				List authList = (List)request.getAttribute("resultList");
				for (int x=0; x<authList.size(); x++) {
					MemberAuthForm authForm = (MemberAuthForm) authList.get(x);
		%>
		<tr>
   			<td width="5%"  class="normal" align="center"><%=x+1%>.</td>
			<td width="5%"  class="normal" align="center"><%=authForm.getAppCode()%></td>
			<td width="30%" class="normal">&nbsp;<%=authForm.getAppName()%></td>

  		  	<td width="10%" align="center">
  		  	<% if (authForm.getAuthMant().equals("X")) { %>
		    	<input type="checkbox" name="idKeyMant" value="<%=authForm.getIdKeyMant()%>" checked="checked" />
    		<% } else { %>
	    		<input type="checkbox" name="idKeyMant" value="<%=authForm.getIdKeyMant()%>"  />	    	
	    	<% } %>
  		  	</td>
  		  	
	      	<td width="10%" align="center">
	      	<% if (authForm.getAuthDisp().equals("X")) { %>
		    	<input type="checkbox" name="idKeyDisp" value="<%=authForm.getIdKeyDisp()%>" checked="checked" />
    		<% } else { %>
	    		<input type="checkbox" name="idKeyDisp" value="<%=authForm.getIdKeyDisp()%>"  />	    	
	    	<% } %>
	    	</td>

	      	<td width="10%" align="center">
	      	<% if (authForm.getAuthAppv().equals("X")) { %>
		    	<input type="checkbox" name="idKeyAppv" value="<%=authForm.getIdKeyAppv()%>" checked="checked" />
    		<% } else { %>
	    		<input type="checkbox" name="idKeyAppv" value="<%=authForm.getIdKeyAppv()%>"  />	    	
	    	<% } %>
	    	</td>
	    	
	    </tr>  
	 	<% 		} 
 			} else {
		%>
		<tr><td align="center" colspan="6">Not Found</td></tr>
		<% 	} %>
		</table>
 	</html:form>
  </body>
</html:html>