<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ page import="com.dtac.admin.form.MemberTypeForm" %>
<%@ page import ="com.dtac.employee.form.EmployeeForm" %>
<%@ include file="../admin/css_blue_style.css" %>
<%
 	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage");
	
	String empID = "";
	if (request.getAttribute("empID") != null) 
		empID = (String) request.getAttribute("empID"); 	
%>   
 <html>
<head>
<script src="/dtac/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="/dtac/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/dtac/jsp/js/tooltip_hiso/style.css"/>
<script type="text/javascript" src="/dtac/jsp/js/tooltip_hiso/script.js"></script> 
</head>
  <body class="blueback">
	<html:form action="/employeeView">
		<fieldset><legend><b class="blue"><br>2.1 ข้อมูลพนักงาน</b></legend>
		<table align="center" width="100%">		
		<tr>
			<td width="15%" rowspan="6">
			 	<div align="center">
				<% if(empID!="") { %>
					<img src=".\\jsp\\image\\memberpic\\<%=empID.trim()%>.JPG"  width="100" height="120" align="center">
				<% } else { %>
  					<img src=".\\jsp\\image\\memberpic\\LogoSchool.jpg"  width="100" height="150" align="center">
				<% } %> 
  				</div>
			</td>
		</tr>		
		<tr>
			<td class="blue">ประเภท&nbsp;:&nbsp;
				<html:select property="empTypeCode" >
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
			<td class="blue">แผนก &nbsp;:&nbsp;				
				<html:select property="empDeptCode">											
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
		</tr>		
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="normal">รหัสพนักงาน &nbsp;:&nbsp;			
				<html:text property="empID" size="5" maxlength="7" readonly="true"/>
			</td>
			<td class="normal">รหัสพนักงาน(เก่า) &nbsp;:&nbsp;
				<html:hidden property="oldEmpID"/>
			</td>			
		</tr>		
		<tr>
			<td class="blue">ชื่อภาษาไทย &nbsp;:&nbsp;
				<html:text property="empNameT" maxlength="25" />
			</td>
			<td class="blue">นามสกุล &nbsp;:&nbsp;
				<html:text property="empLastNameT" maxlength="25" />
			</td>
		</tr>		
		<tr>
			<td class="blue">ชื่อภาษาอังกฤษ &nbsp;:&nbsp;
				<html:text property="empNameE" maxlength="25" />
			</td>
			<td class="blue">นามสกุล &nbsp;:&nbsp;
				<html:text property="empLastNameE" maxlength="25" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:submit property="reqCode"> 
					<bean:message bundle="adminResources" key="memberform.button.update" />	
 				</html:submit>
			</td>
		</tr>
		<tr><td>&nbsp;</td>
			<td class="red" align="right">&nbsp;<%=alertMessage%></td>
		</tr>
		</table>
 		</fieldset>
	
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">พนักงาน</li>
    <li class="TabbedPanelsTab" tabindex="0">ทั่วไป</li>
  </ul>
  	<div class="TabbedPanelsContentGroup">
    	<div class="TabbedPanelsContent">
      			<table width="100%" border="0" align="left" cellpadding="0" cellspacing="5">
      			<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">ที่อยู่&nbsp;:&nbsp;
						<html:text property="address1" size="60" maxlength="150"/>									
					</td>			
					<td class="blue">เพศ	&nbsp;:&nbsp;				
						<html:select property="sex">
							<html:option value="ช" >ช</html:option>
							<html:option value="ญ" >ญ</html:option>
						</html:select>		
				    </td>		
				</tr>	
				<tr>					
					<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:text property="address2" size="60" maxlength="150"/>									
					</td>					
				    <td class="blue">สัญชาติ&nbsp;:&nbsp;		    
				    	<html:text property="nation" size="20" maxlength="30"/>				    				
				   	</td>
				</tr>				
				<tr>
					<td class="blue">เบอร์โทร&nbsp;:&nbsp;
						<html:text property="tel1" size="60" maxlength="100"/>									
					</td>		
				</tr>	
				<tr>
					<td class="blue">E-mail&nbsp;:&nbsp;&nbsp;&nbsp;
						<html:text property="email1" size="60" maxlength="100"/>									
					</td>		
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">การศึกษา&nbsp;:&nbsp;
						<html:select property="educCode">						
						<% if (request.getAttribute("educList") != null) {
							List educList = (List)request.getAttribute("educList");
		   					for (Iterator iterDept = educList.iterator(); iterDept.hasNext();) {
			   					MasterTableForm deptForm = (MasterTableForm) iterDept.next();
		       			%>
	        				<html:option value="<%=deptForm.getTypeCode()%>"><%=deptForm.getThName()%></html:option>
						<% 		} 
							} 
						%>			
						</html:select>		
					</td>
			 		<td class="blue">สาขา&nbsp;:&nbsp;
				 		<html:text property="major" size="40" maxlength="100"/>				 		 
				 	</td>
				</tr>					
				<tr>
					<td class="blue">วันเกิด <font color="red">*</font>&nbsp;:&nbsp;
						<html:text property="birthDay" size="10" maxlength="10"/>						
					</td>
					<td class="blue">วันที่จ้างงาน  <font color="red">*</font> &nbsp;:&nbsp;
						<html:text property="workDate" size="10" maxlength="10"/>					 	
					</td>
				</tr>
				<tr><td class="red">* dd/mm/yyyy (25/12/2554)</td>
				 	<td class="blue">วันที่เลิกจ้างงาน  <font color="red">*</font> &nbsp;:&nbsp;
						<html:text property="expDate" size="10" maxlength="10"/> 	 
					</td>			
				</tr>	
				<tr><td class="blue">สถานที่ทำงาน &nbsp;:&nbsp;
						<html:select property="offiCode">						
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
					<td class="blue">สถานะ  &nbsp;:&nbsp;					
						<html:select property="status">
							<html:option value="AC">ทำงาน</html:option>
							<html:option value="CL">ลาออก</html:option>
						</html:select>	 
					</td>			
				</tr>	
				<tr><td>&nbsp;</td></tr>					
				<tr>
					 <td class="blue" colspan="2">สถานภาพสมรส &nbsp;:&nbsp;					 
						<html:radio property="marryStatus" value="0">&nbsp;โสด</html:radio>&nbsp;&nbsp;&nbsp;&nbsp; 
						<html:radio property="marryStatus" value="1">&nbsp;สมรส (แยกยื่นแบบ)</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio property="marryStatus" value="2">&nbsp;สมรส (ไม่มีรายได้)</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio property="marryStatus" value="3">&nbsp;หม้าย</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;										
					</td>
				</tr>					
				<tr>
					<td class="blue" >จำนวนบุตร (ทั้งหมด)&nbsp;:&nbsp;
						<html:select property="allChild">	 
							<html:option value="0">0</html:option>
							<html:option value="1">1</html:option>
							<html:option value="2">2</html:option>
							<html:option value="3">3</html:option>
							<html:option value="4">4</html:option>
							<html:option value="5">5</html:option>
						</html:select>										
					</td>
				</tr>
				<tr>
					<td class="blue" >จำนวนบุตร (ศึกษา)&nbsp;:&nbsp;
						<html:select property="edcChild">	 
							<html:option value="0">0</html:option>
							<html:option value="1">1</html:option>
							<html:option value="2">2</html:option>
							<html:option value="3">3</html:option>
							<html:option value="4">4</html:option>
							<html:option value="5">5</html:option>
						</html:select>										
					</td>
				</tr>								  			
			    </table>	    
			</div>
			<div class="TabedPanelsContent">
    			<table width="100%" border="0" align="left" cellpadding="0" cellspacing="5">
    			<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue" >หมายเลขบัญชีธนาคาร</td>
					<td><html:text property="accountNo" size="20" maxlength="20"/></td>
					<td class="blue" >เบี้ยประกันชีวิตต่อปี </td>
					<td><html:text property="insure" size="10" maxlength="10"/></td>
				</tr>				  	
				<tr>
					<td class="blue" >หมายเลขประจำตัวผู้เสียภาษี</td>
					<td><html:text property="taxID" size="14" maxlength="13"/></td>
					<td class="blue" >เงินบริจาคต่อปี devote</td>
					<td><html:text property="devote" size="10" maxlength="10"/></td>
				</tr>					  
				<tr>
					<td class="blue" >หมายเลขประกันสังคม</td>
					<td><html:text property="socialNo" size="14" maxlength="13"/></td>
					<td class="blue" >ดอกเบี้ยเงินกู้ต่อปี </td>
					<td><html:text property="interestLoan" size="10" maxlength="10"/></td>				
				</tr>				  
				<tr>
					<td class ="blue">หมายเลขบัตรประชาชน &nbsp;<font color="red">ต้องกรอก</font></td>
					<td><html:text property="idPop" size="14" maxlength="13"/></td>
					<td class="blue">
						<html:checkbox property="pfFlag" value="1">&nbsp;หักเงินกองทุน</html:checkbox>
					</td>
				</tr>			  
				<tr>
					<td class="blue">จำนวนคนในครอบครัวที่ได้หักลดหย่อน&nbsp;&nbsp;
						<html:select property="deductType">	 
							<html:option value="0">0</html:option>
							<html:option value="1">1</html:option>
							<html:option value="2">2</html:option>
							<html:option value="3">3</html:option>
							<html:option value="4">4</html:option>
						</html:select>					
					</td>								
					<td class="blue" >วันที่เข้ากองทุน <font color="red">*</font>&nbsp;&nbsp;	
						<html:text property="pfundDate" size="10" maxlength="10"/>					
					</td>					
				</tr>
				<tr>
					<td class="blue">การบันทึกเวลา&nbsp;&nbsp;
						<html:select property="punchCard">	 
							<html:option value="Y">บัตรตอก</html:option>
							<html:option value="N">ลายนิ้วมือ</html:option>
						</html:select>		
					</td>
				</tr>									
			    </table>
			    	<html:hidden property="projCode" />
			    	<html:hidden property="subProjCode" />
			    	<html:hidden property="groupName" />
    </div>
</div>
</div>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
//-->
</script>
</fieldset>
		</html:form>
	</body>
</html>