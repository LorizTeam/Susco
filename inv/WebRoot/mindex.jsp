<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>  Inventory</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
	<script language="JavaScript">
	  function OpenWindow(){
	  	var hori=(screen.availWidth) +10; 
		var verti=(screen.availHeight)- 25;	
		var newWin=window.open('','Search','top=0,left=0,,width='+hori+',height='+verti+',resizable=no,toolbar=no,menubar=0,scrollbars=1,status=yes,dependent=no,directories=no');
	
		if (newWin==null || typeof(newWin)=="undefined") {
			document.write("You HAVE A POPUP BLOCKER");
		
		} else {
			newWin.location='/inv/jsp/admin/mainMenu.jsp';	
			newWin.opener.name = "_default";
			this.close();
			return;
		}
	  }
	</script>    
  </head>
  <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" onLoad="javascript:OpenWindow();"><!--
  Hello World
  --></body>
</html:html>