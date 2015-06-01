<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.admin.form.MemberTypeForm" %>
<%@ page import="com.dtac.employee.form.EmployeeForm" %>
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
    <title>My JSP 'EmployeeList.jsp' starting page</title>
    
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
	<html:form action="employeeList" >
		<fieldset><legend><b class="blue"><br>2.1 ข้อมูลพนักงาน</b></legend>
		<table align="center" width="100%">
		<tr>
 			<td class="blue">รหัสประจำตัว &nbsp;:&nbsp;
				<html:text property="empID" 		size="5" maxlength="5"/>
 			</td>
			<td class="blue">ชื่อ &nbsp;:&nbsp;
				<html:text property="empNameT" 		size="20" maxlength="35"/>
			</td>
			<td class="blue">นามสกุล &nbsp;:&nbsp;
 				<html:text property="empLastNameT"  size="15" maxlength="35"/> 
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
		   				<%=(String)memberType.getMemberTypeName()%>
			   		</html:option>
					<% 		} 
						} 
					%>
				</html:select>
			</td>	
			<td class="blue">แผนก&nbsp;:&nbsp;
				<html:select property="empDeptCode">
					<html:option value="">-ไม่ระบุ-</html:option>						
					<% if (request.getAttribute("deptList") != null) {
						List deptList = (List)request.getAttribute("deptList");
	   					for (Iterator iterOrder = deptList.iterator(); iterOrder.hasNext();) {
		   					MasterTableForm deptForm = (MasterTableForm) iterOrder.next();
	       			%>
       				<html:option value="<%=deptForm.getTypeCode()%>"><%=deptForm.getTypeCode()%>-<%=deptForm.getThName()%></html:option>
					<% 		} 
						} 
					%>			
				</html:select>	 				
 			</td>
			<td class="blue">สถานะ &nbsp;:&nbsp;
				<html:select property="status" >
			   		<html:option value="">ไม่ระบุ</html:option>
					<html:option value="AC">ทำงานอยู่</html:option>
					<html:option value="CL">ลาออก</html:option>
				</html:select>	
			</td>	
		</tr>
		<tr>
			<td class="blue">สถานที่ทำงาน &nbsp;:&nbsp;
				<html:select property="offiCode">
					<html:option value="">ไม่ระบุ</html:option>						
				<% if (request.getAttribute("offiList") != null) {
					List offiList = (List)request.getAttribute("offiList");
   					for (Iterator iterOffi = offiList.iterator(); iterOffi.hasNext();) {
	   					MasterTableForm offiForm = (MasterTableForm) iterOffi.next();
       			%>
       				<html:option value="<%=offiForm.getTypeCode()%>"><%=offiForm.getThName()%></html:option>
				<% 		} 
					} 
				%>			
				</html:select>          				
			</td>		
 			<td class="normal">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="employeeResources" key="employeeform.button.search" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp; 				
 				<html:submit property="reqCode"> 
 					<bean:message bundle="employeeResources" key="employeeform.button.view" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp; 				
 				<!--  html:submit property="reqCode"
 					bean:message bundle="employeeResources" key="employeeform.button.copyto" -->
 				 	 			
 			</td>
 			<td class="red">&nbsp;<%=alertMessage%></td>
        </tr>		
		</table>
		</fieldset> 	
 	
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" bgcolor="lightslategray">
		<tr>	
			<td width="5%"  align="center" class="normal">ลำดับ</td>		
			<td width="10%" align="center" class="normal">รหัสประจำตัว</td>
			<td width="20%" align="center" class="normal">ชื่อ</td>
			<td width="15%" align="center" class="normal">นามสกุล</td>
			<td width="10%" align="center" class="normal">ประเภท</td>						
			<td width="10%" align="center" class="normal">แผนก</td>
			<td width="10%" align="center" class="normal">วันที่เริ่มงาน</td>	
			<td width="10%" align="center" class="normal">สถานที่ทำงาน</td>
			<td width="10%" align="center" class="normal">สถานะ</td>		
		</tr>
		</table>
	
		<table align="center" width="100%" border="1" cellpadding="0" cellspacing="0">
		<%	if (request.getAttribute("employeeList") != null) {
				List employeeList = (List)request.getAttribute("employeeList");
				int x = 0;
				for (Iterator iter = employeeList.iterator(); iter.hasNext();) {
		  			x++;
					EmployeeForm empForm = (EmployeeForm) iter.next();
					if (!empForm.getEmpID().trim().equals("00000")) {
		%>
			<tr>
		   	  	<td width="5%" align="center" class="normal">
			   		<input type="radio" name="radio1" 
			   		  	onclick="employeeForm.empID.value='<%=empForm.getEmpID()%>';"/><%=x%>.
			   	</td>
			    <td width="10%" align="center" class="normal">&nbsp;<%=empForm.getEmpID()%></td>
			    <td width="20%" class="normal">&nbsp;<%=empForm.getEmpNameT()%></td>
			    <td width="15%" class="normal">&nbsp;<%=empForm.getEmpLastNameT()%></td>		    
				<td width="10%" class="normal">&nbsp;<%=empForm.getEmpTypeName()%></td>				
				<td width="10%" class="normal">&nbsp;<%=empForm.getEmpDeptName()%></td>			
				<td width="10%" align="center" class="normal">&nbsp;<%=empForm.getWorkDate()%></td>	
				<td width="10%" align="center" class="normal">&nbsp;<%=empForm.getOffiName()%></td>	
				
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
			employeeForm.empID.focus()
		</SCRIPT>	
  </body>
</html:html>