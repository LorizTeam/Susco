<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../admin/css_blue_style.css" %>
<%@ include file="/jsp/admin/css_blue_style.css" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 

	String focus = "login";
	if (request.getAttribute("focus") != null) focus = (String) request.getAttribute("focus");		
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>Login Page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 	<meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
  
  <body bgcolor="#DAC987">
  
  <html:form action="login" >
	<br><br><br><br><br>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr>
    	<td align="center" valign="middle" bgcolor="#DAC987">
    		<table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
		   <tr></tr>
		    <tr>
      		  	<td width="7" bgcolor="#F5F1E0"><br></td>
      		  	
			    <td width="755" align="center" bgcolor="#F5F1E0"><strong><span class="style53 style27"><span class="style74 style88">Login to System</span></span></strong></td>
      			<td width="7" align="right" bgcolor="#F5F1E0"><br></td>
			</tr>
    		</table>
    				
    		<table width="450" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#F5F1E0">
		    <tr>
    			<td bgcolor="#F5F1E0">
    				<table width="100%" border="0" cellpadding="0" cellspacing="0">
              		<tr>
                		<td width="41%"><img src="./jsp/image/key_login.jpg" alt="" width="170" height="154"></td>
                		<td width="59%" align="right" valign="top">
                			<table width="250" border="0" cellpadding="3" cellspacing="1" bgcolor="#F5F1E0">
                  			<tr bgcolor="#CCCCCC">
                    			<td width="102" height="30" bgcolor="#F5F1E0" class="style53">
                    				<span class="style97">&nbsp;Username</span>
                    			</td>
                    			<td width="133" height="30" bgcolor="#F5F1E0" class="style53">
                    				<span class="style92">
                  						<html:text property="memberID" size="10" maxlength="10" /> 
                    				</span>
                    			</td>
                  			</tr>
                  			<tr bgcolor="#CCCCCC">
                   	 			<td height="30" bgcolor="#F5F1E0" class="style53">
                   	 				<span class="style97">&nbsp;Password</span>
                   	 			</td>
                    			<td height="30" bgcolor="#F5F1E0" class="style53">
                    				<span class="style92">
                						<html:password property="password" size="12" maxlength="12" /> 
                    				</span>
                    			</td>
                  			</tr>
                			</table>
                  			
                  			<table width="250" border="0" cellpadding="0" cellspacing="0">
                  			<tr><td bgcolor="#F5F1E0">&nbsp;</td></tr>
                    		<tr>
                      			<td align="right" valign="middle" bgcolor="#F5F1E0">
									<div align="center"><html:submit>
										<bean:message bundle="adminResources" key="memberform.button.login" /></html:submit>
									</div>
								</td>
                    		</tr>
               				<tr>
				  	 			<td align="center" bgcolor="#F5F1E0">
									<font size="2" color="red">&nbsp;<%=alertMessage%></font>
				   				</td>
			       			</tr>                    
                  			</table>
                  		</td>
              		</tr>
              		</table>
					<table align="center" width="95%" bgcolor="#FFF0F5">
					<tr>
			 			<td class="blue" ><br></td>
			 		</tr>      
			 		</table>        		
              	</td>
        	</tr>
        	</table>		
		</td>
	</tr>
	</table>	
	</html:form>   
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			memberLoginForm.memberID.focus()
		</SCRIPT>

	<% if (focus.equals("password")) { %>
		<SCRIPT TYPE="TEXT/JAVASCRIPT">
			memberLoginForm.password.focus()
		</SCRIPT>
	<% } %>
	
  </body>
</html:html>