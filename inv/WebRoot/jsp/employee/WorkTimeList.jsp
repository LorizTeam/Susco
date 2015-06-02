<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MemberTypeForm" %>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.employee.form.EmployeeForm" %>
<%@ page import="com.dtac.employee.form.WorkTimeForm"  %>
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
    <title>My JSP 'WorkTimeList.jsp' starting page</title>
    
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
	<html:form action="workTimeList" >
		<fieldset><legend><b class="blue"><br>4.2 บันทึกรายการลาป่วย ลากิจ</b></legend>
		<table align="center" width="95%">
		<tr>
 			<td class="blue">แผนก &nbsp;:&nbsp;
				<html:text property="empDeptCode" size="3" maxlength="3"/>
				&nbsp;&nbsp;&nbsp;รหัสประจำตัว &nbsp;:&nbsp;
				<html:text property="empID" size="7" maxlength="7"/>
 			</td>
			<td class="blue">ชื่อ &nbsp;:&nbsp;
				<html:text property="empNameT" 		size="20" />
			</td>
			<td class="blue">นามสกุล &nbsp;:&nbsp;
 				<html:text property="empLastNameT"  size="15" /> 
 			</td>
 		</tr>
 		<tr>
 			<td class="blue">ประเภทบุคลากร &nbsp;:&nbsp;
				<html:select property="empTypeCode" >
			   		<html:option value="">-ไม่ระบุ-</html:option>
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
 			<td class="blue">สถานะ &nbsp;:&nbsp;
				<html:select property="workStatus">
			   		<html:option value="">ไม่ระบุ</html:option>
					<html:option value="AC">ทำงานอยู่</html:option>
					<html:option value="CL">ลาออก</html:option>
				</html:select>	
 			</td> 			
			<td class="normal">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="employeeResources" key="timeattendform.button.search" />
 				</html:submit>				
			</td>	
		</tr>
		<tr><td colspan="3" align="center" class="red">&nbsp;<%=alertMessage%></td></tr>
		<tr>
 			<td colspan="3" class="blue">ประจำวันที่ &nbsp;:&nbsp;
				<html:text property="workDate" size="10" maxlength="10"/> 
				&nbsp;<font color="black">(เช่น 30/12/2554)</font>				
 				&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="employeeResources" key="timeattendform.button.view" />
 				</html:submit> 			
 			</td>
        </tr>		
		</table>
		</fieldset> 	
		
 		<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightslategray">
		<tr>	
			<td width="5%"  align="center" class="normal">ลำดับ</td>		
			<td width="12%" align="center" class="normal">แผนก-รหัสประจำตัว</td>
			<td width="20%" align="center" class="normal">ชื่อ</td>
			<td width="15%" align="center" class="normal">นามสกุล</td>
			<td width="10%" align="center" class="normal">ประเภท</td>		
			<td width="10%" align="center" class="normal">วันที่ทำงาน</td>	
			<td width="10%" align="center" class="normal">วันที่ออกจากงาน</td>
			<td width="10%" align="center" class="normal">สถานะ</td>				
		</tr>
		</table>
	
		<table align="center" width="100%" border="1" cellpadding="0" cellspacing="0">
		<%	if (request.getAttribute("employeeList") != null) {
				List wtList = (List)request.getAttribute("employeeList");
				int x = 0;
				for (Iterator iter = wtList.iterator(); iter.hasNext();) {
		  			x++;
					EmployeeForm empForm = (EmployeeForm) iter.next();
					if (!empForm.getEmpID().trim().equals("00000")) {
		%>
			<tr>		
				<td width="5%" align="center" class="normal">
					<input type="radio" name="radio1" 
					  	onclick="workTimeForm.empID.value='<%=empForm.getEmpID()%>';
					  			 workTimeForm.empTypeCode.value='<%=empForm.getEmpTypeCode()%>';
					  			 workTimeForm.empDeptCode.value='<%=empForm.getEmpDeptCode()%>'"/><%=x%>.
				</td>
				<td width="12%" class="normal">&nbsp;<%=empForm.getEmpDeptCode()%>-<%=empForm.getEmpID()%></td>
				<td width="20%" class="normal">&nbsp;<%=empForm.getEmpNameT()%></td>
				<td width="15%" class="normal">&nbsp;<%=empForm.getEmpLastNameT()%></td>		    
				<td width="10%" align="center" class="normal">&nbsp;<%=empForm.getEmpTypeCode()%></td>
				<td width="10%" align="center" class="normal">&nbsp;<%=empForm.getWorkDate()%></td>	
				<td width="10%" align="center" class="normal">&nbsp;<%=empForm.getExpDate()%></td>	
				
				<% if (empForm.getStatus().equals("AC")) { %>
				<td width="10%" class="normal" align="center">&nbsp;ทำงานอยู่</td>			
				<% } else if (empForm.getStatus().equals("CL")) { %>
				<td width="10%" class="red" align="center">&nbsp;ลาออก</td>
				<% } else { %>
				<td width="10%" class="red" align="center">&nbsp;ไม่ระบุ</td>
				<% } %>			    
			
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
			workTimeForm.empID.focus()
		</SCRIPT>	
  </body>
</html:html>