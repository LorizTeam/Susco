<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.YearForm" %>
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
    <title>PeriodUpdate.jsp</title>

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
    <html:form action="periodUpdate" method="post">
    	<fieldset><legend><b class="blue">3.1 แก้ไขข้อมูลรอบการคิดเงินเดือน</b></legend>
      	<table border="0" width="50%" align="center">
        <tr><td>&nbsp;</td></tr>      
		<tr>
          	<td class="normal">เดือน / ปี&nbsp;:&nbsp;
		    	<html:text property="month" maxlength="2" size="2" readonly="true"/> &nbsp;/&nbsp;
        		<html:text property="year"  maxlength="4" size="4" readonly="true"/>
			</td>
      	</tr>
        <tr><td>&nbsp;</td></tr>      
		<tr>
          	<td class="normal">ครั้งที่&nbsp;:&nbsp;
		    	<html:text property="periodNo" maxlength="1" size="1" readonly="true"/> 
			</td>
      	</tr>
        <tr><td>&nbsp;</td></tr>      
		<tr>
          	<td class="normal">ประเภท&nbsp;:&nbsp;
		    	<html:text property="salTypeName" maxlength="10" size="10" readonly="true"/> 
			</td>
      	</tr>
        <tr><td>&nbsp;</td></tr>            	
		<tr>
		    <td class="blue">วันเริ่ม &nbsp;<font color="red">*</font>:&nbsp;
		    	<html:text property="startDate"  size="10" maxlength="10"/> 
		    	&nbsp;&nbsp;&nbsp;
		    	<font color="red">*</font>(dd/mm/yyyy eg. 16/05/2554)
			</td>		
        </tr>
        <tr><td>&nbsp;</td></tr>            	
		<tr>
		    <td class="blue">วันสิ้นสุด &nbsp;<font color="red">*</font>:&nbsp;
		    	<html:text property="endDate"  size="10" maxlength="10"/> 
			</td>		
        </tr>
        <tr><td>&nbsp;</td></tr> 
		<tr>
		    <td class="blue">%หักประกันสังคม &nbsp;:&nbsp;
		    	<html:text property="socialRate"  size="5" maxlength="5"/> 
			</td>		
        </tr>
        <tr><td>&nbsp;</td></tr> 
		<tr>
		    <td class="blue">สถานะ &nbsp;:&nbsp;
        		<html:select property="status">
        			<html:option value="AC">แก้ไขข้อมูลได้</html:option>
        			<html:option value="CL">เสร็จสิ้นแล้ว</html:option>
        		</html:select>
			</td>		
        </tr>
        <tr><td>&nbsp;</td></tr> 
		<tr>
		    <td class="blue">งวดปัจจุบัน &nbsp;:&nbsp;
       			<html:radio property="currFlag" value="Y">&nbsp;ใช่</html:radio>&nbsp;&nbsp;&nbsp;
       			<html:radio property="currFlag" value="N">&nbsp;ไม่ใช่</html:radio>
			</td>		
        </tr>
		<tr>
			<td align="center" class="red">&nbsp;</td>
		</tr>
        <tr>
          	<td>&nbsp;&nbsp;
	          	<html:submit property="reqCode"> 
					<bean:message bundle="employeeResources" key="periodform.button.update" />
				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
				<html:submit property="reqCode"> 
					<bean:message bundle="employeeResources" key="periodform.button.cancel" />
				</html:submit>
			</td>
				<html:hidden property="salTypeCode"/>
        </tr>
		<tr>
			<td align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
      	</table>
	    </fieldset>			  	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			periodForm.startDate.focus()
		</SCRIPT>		
  </body>
</html:html>