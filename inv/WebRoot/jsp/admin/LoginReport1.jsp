<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>LoginReport1.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <html:form action="loginReport1" method="post">
    	<fieldset><legend><b class="blue">2.รายงานการเข้าใช้งานระบบ </b></legend>
      	<table border="0" width="60%" align="center">
      	<tr><td>&nbsp;</td></tr>
		<tr>
        	<td class="blue">1.ตั้งแต่วันที่ &nbsp;:&nbsp;
		       	<html:text property="fromDate"  size="10" maxlength="10"/>

		       	&nbsp;&nbsp;&nbsp;&nbsp;2.ถึงวันที่ &nbsp;:&nbsp;
		       	<html:text property="toDate"  size="10" maxlength="10"/>
		       	&nbsp;&nbsp;&nbsp;<font color="black">(dd/mm/yyyy)</font>
			</td>
		</tr>

		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="blue">3.รหัสพนักงาน &nbsp;:&nbsp;
        		<html:text property="memberID" 	size="5" maxlength="10"/>
        	</td>
        </tr>
        <tr><td>&nbsp;</td></tr>        
		<tr>
			<td colspan="2" align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;รายงานที่ต้องการ &nbsp;:&nbsp;	</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		<html:radio value="1" property="reportNo" >&nbsp;&nbsp;1.การเข้าใช้งานรายระบบรายบุคคล (กรอกข้อ 1,2,[3]) </html:radio>
        	</td>
        </tr>
		<tr><td>&nbsp;</td></tr>
        <tr>
          	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          		<html:submit property="reqCode">
					<bean:message bundle="adminResources" key="memberform.button.print" />
				</html:submit>
			</td>
        </tr>
      	</table>
	    </fieldset>
  	</html:form>
  </body>
</html:html>