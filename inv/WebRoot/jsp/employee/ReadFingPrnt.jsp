<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.employee.form.FingPrntForm" %>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
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
    <title>My JSP 'ReadFingPrnt.jsp' starting page</title>
    
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
	<html:form action="readFingPrnt" >
		<fieldset><legend><b class="blue"><br>4.1 อ่านข้อมูลการบันทึกเวลาจากการ scan นิ้ว</b></legend>
		<table align="center" width="100%">		
		<tr>
 			<td class="blue">1. รหัสประจำตัว &nbsp;:&nbsp;
				<html:text property="empID" 		size="10" maxlength="10" />
 			</td>
 			<td class="normal">ถ้าต้องการอ่านเวลาทุกคน ไม่ต้องป้อนรหัสประจำตัว</td>
 		</tr>
 		<tr><td>&nbsp;</td></tr>
 		<tr>
			<td class="blue">2. ประจำวันที่ &nbsp;:&nbsp;
				<html:text property="checkDate" 	size="10" maxlength="10" />
				&nbsp;&nbsp;&nbsp;<font color="black">dd/mm/yyyy  , 20/12/2011</font>
			</td>
			<td class="red">&nbsp;<%=alertMessage%></td>
 		</tr>
 		<tr><td>&nbsp;</td></tr>
		<tr>
 			<td class="blue">3. สถานที่ทำงาน &nbsp;:&nbsp;
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
 					<bean:message bundle="employeeResources" key="timeattendform.button.readdata" />
 				</html:submit>
 			</td>
 			<td class="blue">แผนก &nbsp;:&nbsp;
				<html:select property="empDeptCode" >
			   		<html:option value="">-N/A-</html:option>
					<% if (request.getAttribute("deptList") != null) {
							List deptList = (List)request.getAttribute("deptList");
	   						for (Iterator iterDept = deptList.iterator(); iterDept.hasNext();) {
		   				  		MasterTableForm deptForm = (MasterTableForm) iterDept.next();
	       			%>
        			<html:option value="<%=deptForm.getTypeCode()%>"><%=deptForm.getTypeCode()%></html:option>
					<% 		} 
						} 
					%>
				</html:select>
				&nbsp;&nbsp;&nbsp;ประเภทบุคลากร &nbsp;:&nbsp;
				<html:select property="empTypeCode" >
			   		<html:option value="">-N/A-</html:option>
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
				&nbsp;&nbsp;&nbsp;			
 				<html:submit property="reqCode"> 
 					<bean:message bundle="employeeResources" key="timeattendform.button.search" />
 				</html:submit>			
			</td> 			
        </tr>
        <tr><td>&nbsp;</td></tr>		
		</table>
		</fieldset>   

	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" class="background1">
		<tr>	
			<td width="12%" align="center" class="normal">แผนก-รหัสประจำตัว</td>
			<td width="15%" align="center" class="normal">ชื่อ-สกุล</td>		
			<td width="10%" align="center" class="normal">วันที่</td>			
			<td width="10%" align="center" class="normal">เวลาเข้า</td>
			<td width="10%" align="center" class="normal">เวลาออก</td>
			<td width="5%"  align="center" class="normal">Normal</td>
			<td width="5%"  align="center" class="normal">1</td>		
			<td width="5%"  align="center" class="normal">1.5</td>			
			<td width="5%"  align="center" class="normal">2</td>
			<td width="5%"  align="center" class="normal">3</td>
		</tr>
		</table>
		
		<table align="center" width="100%" border="1" cellpadding="0" cellspacing="0">
		<%	if (request.getAttribute("fingPrntList") != null) {
				List fpList = (List)request.getAttribute("fingPrntList");
				int x = 0;
				for (Iterator iter = fpList.iterator(); iter.hasNext();) {
		  			x++;
					FingPrntForm fpForm = (FingPrntForm) iter.next();
		%>
			<tr>
	   		  	<td width="12%" class="normal">&nbsp;<%=fpForm.getEmpDeptCode()%>-<%=fpForm.getEmpID()%></td>
			    <td width="15%" class="normal">&nbsp;<%=fpForm.getEmpNameT()%>&nbsp;<%=fpForm.getEmpLastNameT()%></td>
			    <td width="10%" align="center" class="normal"><%=fpForm.getDayName()%>&nbsp;<%=fpForm.getCheckDate()%></td>
			    <td width="10%" align="center" class="normal"><%=fpForm.getStartTime()%></td>
			    <td width="10%" align="center" class="normal"><%=fpForm.getStopTime()%></td>
			    <td width="5%"  align="right" class="blue"><%=fpForm.getNormal()%>&nbsp;</td>	
				<td width="5%"  align="right" class="normal"><%=fpForm.getOtN1()%>&nbsp;</td>			
				<td width="5%"  align="right" class="normal"><%=fpForm.getOtN1_5()%>&nbsp;</td>
				<td width="5%"  align="right" class="normal"><%=fpForm.getOtN2()%>&nbsp;</td>
				<td width="5%"  align="right" class="normal"><%=fpForm.getOtN3()%>&nbsp;</td>
		    </tr>  
	 	<%			
	 			} 
			} else {
		%>
			<tr><td align="center" colspan="5">No Data Found</td></tr>
		<%	} %>   
		</table>		
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			fingPrntForm.empID.focus()
		</SCRIPT>	
  </body>
</html:html>