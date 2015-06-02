<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title> MIS</title>    
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
    <%
 		//String memberId  = (String)session.getAttribute("memberId");	
 		String userName =  (String)session.getAttribute("userName");
 		String lastLoginDate = (String)session.getAttribute("lastLoginDate"); 		
 		String lastChangPswd = (String)session.getAttribute("lastChangPswd"); 
 		String nextChangPswd = (String)session.getAttribute("nextChangPswd"); 		  				
  	%>  
  <body bgcolor="#DAC987">
  <br/>
 <br/>
	
  <P align="center"><FONT size="3" color="blue"> 
		Welcome  <%=userName%> 
		<br><br>
		Last Login : <%=lastLoginDate%>
		<br><br>
		Change Password : <%=lastChangPswd%>  Next Time : <%=nextChangPswd%>
		</FONT>
	</P>	
		<P align="center">
	 
	</P>
  </body>
</html>