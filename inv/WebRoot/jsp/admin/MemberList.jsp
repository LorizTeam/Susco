<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.ApplTypeForm" %>
<%@ page import="com.dtac.admin.form.MemberLoginForm" %>
<%@ page import="com.dtac.admin.form.MemberTypeForm" %>
<%@ include file="css_blue_style.css" %>
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
    <title>My JSP 'MemberList.jsp' starting page</title>
    
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
	<html:form action="memberList" >
		<fieldset><legend><b class="blue">1.1 กำหนดสิทธิ์การใช้งาน</b></legend>
		<table align="center" width="95%" bgcolor="#FFF0F5">
		<tr>
 			<td class="blue">รหัสประจำตัว &nbsp;:&nbsp;
				<html:text property="memberID" size="10" maxlength="10"/>
 			</td>
			<td class="blue">ชื่อ &nbsp;:&nbsp;
				<html:text property="firstName" size="20" />
			</td>
			<td class="blue">นามสกุล &nbsp;:&nbsp;
 				<html:text property="lastName"  size="15" /> 
 			</td>
 		</tr>
 		<tr><td>&nbsp;</td>
 			<td class="blue">ประเภทบุคลากร &nbsp;:&nbsp;
				<html:select property="memberTypeCode" >
			   		<html:option value="">-N/A-</html:option>
					<% if (request.getAttribute("memberTypeList") != null) {
							List memberTypeList = (List)request.getAttribute("memberTypeList");
							for (int x=0; x<memberTypeList.size(); x++) {
								MemberTypeForm memberType =(MemberTypeForm) memberTypeList.get(x);
					%>
			   		<html:option value="<%=memberType.getMemberTypeCode()%>"><%=memberType.getMemberTypeCode()%> </html:option>
					<% 		} 
						} 
					%>
				</html:select>
			</td>	
 			<td>
 				<html:submit property="reqCode"> 
 					<bean:message bundle="adminResources" key="memberform.button.search" />
 				</html:submit>
 			</td>
		</tr>
		<tr><td colspan="3" align="center" class="red">&nbsp;<%=alertMessage%></td></tr>
		<tr>
			<td class="blue" colspan="2">กำหนดสิทธิ์สำหรับระบบ&nbsp;:&nbsp;
				<html:select property="appType" >
			   		<html:option value="setpswd">&nbsp;&nbsp;รหัสผ่าน</html:option>
					<% if (request.getAttribute("applTypeList") != null) {
							List applTypeList = (List)request.getAttribute("applTypeList");
							for (int x=0; x<applTypeList.size(); x++) {
								ApplTypeForm applType =(ApplTypeForm) applTypeList.get(x);
					%>
			   		<html:option value="<%=applType.getApplTypeCode()%>">
			   			<%=applType.getApplTypeCode()%>-<%=applType.getApplTypeName()%> 
			   		</html:option>
					<% 		} 
						} 
					%>			   		
				</html:select>
				&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="adminResources" key="memberform.button.update" />
 				</html:submit>				
        	</td>
 		 			<html:hidden property="password" />
		 			<html:hidden property="memberTypeName" />        	
        </tr>		
		</table>
		</fieldset> 	
 	
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="95%" bgcolor="lightslategray">
		<tr>	
			<td width="5%"  align="center" class="normal">ลำดับ</td>		
			<td width="10%" align="center" class="normal">รหัสประจำตัว</td>
			<td width="20%" align="center" class="normal">ชื่อ</td>
			<td width="15%" align="center" class="normal">นามสกุล</td>
			<td width="10%" align="center" class="normal">ประเภทบุคลากร</td>		
			<td width="15%" align="center" class="normal">ใช้งานล่าสุด</td>			
			<td width="10%" align="center" class="normal">เปลี่ยนรหัสผ่าน</td>			
		</tr>
		</table>
	
		<table align="center" width="95%" border="1" cellpadding="0" cellspacing="0">
		<%	if (request.getAttribute("memberList") != null) {
				List memberList = (List)request.getAttribute("memberList");
				int x = 0;
				for (Iterator iter = memberList.iterator(); iter.hasNext();) {		  			
					MemberLoginForm memberLoginForm = (MemberLoginForm) iter.next();
					if (!memberLoginForm.getMemberID().trim().equals("00000")) { x++;
		%>
			<% if (memberLoginForm.getStatus().trim().equals("AC")) { %>
			<tr>
			<% } else { %>
			<tr bgcolor="red">
			<% } %>	
		   	  	<td width="5%" align="center" class="normal">
			   		<input type="radio" name="radio1" 
			   		  	onclick="memberAuthForm.memberID.value='<%=memberLoginForm.getMemberID()%>';"/><%=x%>.
	   		  	</td>
			    <td width="10%" align="center" class="normal"><%=memberLoginForm.getMemberID()%></td>
			    <td width="20%" class="normal">&nbsp;<%=memberLoginForm.getFirstName()%></td>
			    <td width="15%" class="normal">&nbsp;<%=memberLoginForm.getLastName()%></td>		    
				<td width="10%" align="center" class="normal"><%=memberLoginForm.getMemberTypeCode()%></td>
				<td width="15%" align="center" class="normal">&nbsp;<%=memberLoginForm.getLastLoginDate()%></td>			
				<td width="10%" align="center" class="normal">&nbsp;<%=memberLoginForm.getLastChangPswd()%></td>			
		    </tr>  
	 	<%			}
	 			} 
			} else {
		%>
			<tr><td align="center" colspan="5">No Data Found</td></tr>
		<%	} %>   
		</table>
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			memberAuthForm.memberID.focus()
		</SCRIPT>	
  </body>
</html:html>