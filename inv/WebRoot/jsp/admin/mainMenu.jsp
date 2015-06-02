<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//	HttpSession session = request.getSession();
	String	memberId = "";
	boolean loginFlag = false;
	
	if (session.isNew()) {
		loginFlag = false;
	} else {
		memberId = (String) session.getAttribute("memberId");
		
		if (memberId == null) {
			loginFlag = false;
		} else {
			loginFlag = true;
		}
		//System.out.println("member"+memberId);
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>  ERP</title>    
  </head>
  
  <frameset cols="180,*" frameborder="yes" border="2" framespacing="1">
   	<frame src="jsp/admin/emptyMenu.jsp" name="leftFrame" scrolling="YES">
    <% if (loginFlag) { %>
     <frame src="jsp/admin/main.jsp" name="mainFrame" scrolling="YES">
    <% } else { %> 
     <frame src="/inv/loginstart.do" name="mainFrame">
    <% } %>  
  </frameset>
</html:html>