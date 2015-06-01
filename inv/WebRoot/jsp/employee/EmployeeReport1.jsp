<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MemberTypeForm" %>
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
    <title>EmployeeReport1.jsp</title>

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
    <html:form action="employeeReport1" method="post">
    	<fieldset><legend><b class="blue">1 พิมพ์รายงานบุคลากร</b></legend>
      	<table border="0" width="80%" align="center">
		<tr><td>&nbsp;</td></tr>
 		<tr>
 			<td class="blue">1. ประเภทบุคลากร &nbsp;:&nbsp;
				<html:select property="empTypeCode" >
					<% if (request.getAttribute("memberTypeList") != null) {
							List memberTypeList = (List)request.getAttribute("memberTypeList");
							for (int x=0; x<memberTypeList.size(); x++) {
								MemberTypeForm memberType =(MemberTypeForm) memberTypeList.get(x);
					%>
			   		<html:option value="<%=(String)memberType.getMemberTypeCode()%>">
		   				<%=(String)memberType.getMemberTypeCode()%>
			   		</html:option>
					<% 		} 
						} 
					%>
				</html:select>
			</td>
 			<td class="blue">2. ชื่อกลุ่ม &nbsp;:&nbsp;
				<html:text property="groupName" size="30" maxlength="50"/>
 			</td>
				
		</tr>
		<tr><td>&nbsp;</td></tr>		
		<tr>      
			<td class="blue">&nbsp;&nbsp;รายงานที่ต้องการ &nbsp;:&nbsp;</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     			<html:radio value="1" property="reportNo">&nbsp;&nbsp;1.สรุปจำนวนบุคลากร </html:radio>
        	</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       			<html:radio value="2" property="reportNo" >&nbsp;&nbsp;2.การปฏิบัติงานพนักงาน แยกตามประเภท  (กรอกข้อ [2])</html:radio>
    	   	</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       			<html:radio value="3" property="reportNo" >&nbsp;&nbsp;3.รายชื่อพนักงาน เรียงตามแผนก</html:radio>
    	   	</td>
        </tr>
		<tr>      
			<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       			<html:radio value="4" property="reportNo" >&nbsp;&nbsp;4.รายชื่อพนักงาน เงินเดือน ค่าครองชีพ</html:radio>
    	   	</td>
        </tr>

		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;		
				<html:submit property="reqCode">
					<bean:message bundle="employeeResources" key="employeeform.button.print" />
				</html:submit>
			</td>
		</tr>
		</table>
		</fieldset>
	</html:form>
  </body>
</html:html>