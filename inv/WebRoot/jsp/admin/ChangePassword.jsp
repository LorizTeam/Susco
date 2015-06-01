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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>Change Password</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 	<meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
  
  <body bgcolor="#95b2c3">
	<html:form action="/changePassword" >
	<br><br><br><br><br>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr>
    	<td align="center" valign="middle">
    		<table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
		    <tr>
	    		<td width="7"><img src="./jsp/image/style04_n_left.gif" alt="" width="7" height="28"></td>
				<td width="755" align="center" background="./jsp/image/style04_n_back.gif">
					<span class="style53 style27"><span class="style74 style88">Change Password</span></span>
				</td>
	      		<td width="7" align="right"><img src="./jsp/image/style04_n_right.gif" alt="" width="7" height="28"></td>
			</tr>
    		</table>
    				
    		<table width="450" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#58636f">
		    <tr>
    			<td bgcolor="#FFFFFF">
	    			<table width="100%" border="0" cellpadding="0" cellspacing="0">
	              	<tr>
	                	<td width="41%"><img src="./jsp/image/key_login.jpg" alt="" width="170" height="154"></td>
	                	<td width="59%" align="right" valign="top">
	                		<table width="250" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
	                  		<tr bgcolor="#CCCCCC">
	                    		<td width="102" height="30" bgcolor="#FFFFFF" class="style53">
	                    			<span class="style97">&nbsp;
	                    				<font color="blue">Username</font>
	                    			</span>
	                    		</td>
	                    		<td width="133" height="30" bgcolor="#FFFFFF" class="style53">
	                    			<span class="style92">
	                  					<html:text property="memberID" size="10" maxlength="10" /> 
	                    			</span>
	                    		</td>
	                  		</tr>
			                <tr bgcolor="#CCCCCC">
	    		                <td height="30" bgcolor="#FFFFFF" class="style53">
	    		                	<span class="style97">&nbsp;
	    		               			<font color="blue">Old Password</font>
	    		               		</span>
	    		               	</td>
	        		            <td height="30" bgcolor="#FFFFFF" class="style53">
	        		            	<span class="style92">
	            				   		<html:password property="password" size="12" maxlength="12" /> 
				                    </span>
				                </td>
			   				</tr>
			                <tr bgcolor="#CCCCCC">
	    		                <td height="30" bgcolor="#FFFFFF" class="style53">
	    		                	<span class="style97">&nbsp;
	    		               			<font color="blue">New Password</font>
	    		               		</span>
	    		               	</td>
	        		            <td height="30" bgcolor="#FFFFFF" class="style53">
	        		            	<span class="style92">
	            				   		<html:password property="newPassword" size="12" maxlength="12" /> 
				                    </span>
				                </td>
			   				</tr>
			                </table>
		                  	
		                  	<table width="250" border="0" cellpadding="0" cellspacing="0">
		                  	<tr><td>&nbsp;</td></tr>
		                    <tr>
		                      	<td align="right" valign="middle">
									<div align="center">
										<html:submit>
											<bean:message key="memberform.button.changepwd" bundle="adminResources"/>
										</html:submit>
									</div>
								</td>
		                    </tr>
		               		<tr>
						  		<td align="center">
									<font size="2" color="red">&nbsp;<%=alertMessage%></font>
						   		</td>
					       	</tr>
		                  	</table>
		                </td>
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
  </body>
</html:html>