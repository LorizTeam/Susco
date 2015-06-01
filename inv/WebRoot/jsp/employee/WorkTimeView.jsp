<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>My JSP 'WorkTimeView.jsp' starting page</title>
    
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
	<html:form action="workTimeView" >
		<fieldset><legend><b class="blue"><br>4.2 บันทึกรายการลาป่วย ลากิจ
		 	<html:text property="empTypeCode"  size="2"  maxlength="2" readonly="true"/>&nbsp;&nbsp;&nbsp;
		 	<html:text property="salTypeName"  size="10" maxlength="20" readonly="true"/>
		 	</b></legend>
		<table align="center" width="95%">	
		<tr><td>&nbsp;</td></tr>	
		<tr>
 			<td class="normal">รหัสประจำตัว &nbsp;:&nbsp;
 				<html:text property="empDeptCode"   size="3" maxlength="3" readonly="true"/>&nbsp;-
				<html:text property="empID" 		size="7" maxlength="7" readonly="true"/>
			</td>
			<td class="normal">ชื่อ &nbsp;:&nbsp;
				<html:text property="empNameT" 		size="30" maxlength="80" readonly="true"/>
			</td>
			<td class="normal">นามสกุล  &nbsp;:&nbsp;
 				<html:text property="empLastNameT"  size="20" maxlength="80" readonly="true"/>
 			</td>
 		</tr>
 		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="normal">
				ปี/เดือน :&nbsp;	<html:text property="year" 		size="4"  maxlength="4"  readonly="true"/>
				&nbsp;/&nbsp;  	<html:text property="month"  	size="2"  maxlength="2"  readonly="true"/>
 				&nbsp;งวดที่ &nbsp;	<html:text property="periodNo" 	size="1"  maxlength="2"  readonly="true"/>
			</td>
			<td class="normal">วันที่  &nbsp;:&nbsp;
				<html:text property="workDate"   size="10" maxlength="10" readonly="true"/>
			</td> 			
			<td class="blue">เวลาเข้างาน  &nbsp;:&nbsp;
				<html:text property="startTime"  size="5" maxlength="7" /> &nbsp;&nbsp;เช่น 08.00&nbsp;&nbsp;&nbsp;
				<html:submit property="reqCode"> 
					<bean:message bundle="employeeResources" key="timeattendform.button.preview" />	
 				</html:submit>
			</td>
		</tr>
		<tr>
			<td class="normal">ปฎิทิน เริ่มงาน  &nbsp;:&nbsp;
				<html:text property="workTimeStart"   size="8" maxlength="8" readonly="true"/>
				&nbsp;&nbsp;เลิกงาน  &nbsp;:&nbsp;
				<html:text property="workTimeStop"   size="8" maxlength="8" readonly="true"/>
			</td>
			<td class="normal">การบันทึกเวลา&nbsp;&nbsp;
				<html:select property="punchCard" disabled="true">	 
					<html:option value="Y">บัตรตอก</html:option>
					<html:option value="N">ลายนิ้วมือ</html:option>
				</html:select>		
			</td>			
			<td class="blue">เวลาออกงาน  &nbsp;:&nbsp;
				<html:text property="stopTime"   size="5" maxlength="7" />
			</td>
				<html:hidden property="punchCard"/>
				<html:hidden property="salTypeCode"/>
		</tr>
		<tr><td>&nbsp;</td></tr>
		</table>
		<table align="center" width="75%">
		<tr>
			<td width="65%" bordercolor="#0000ff">			
				<table width="100%">
				<tr><td>&nbsp;</td>
					<td class="normal" align="center">ข้อมูลการลาหยุดงาน</td>
					<td class="red">หน่วยเป็น ชม. </td>
				</tr>
				<tr>
					<td class="normal" align="right">&nbsp;&nbsp;
						<html:hidden property="xw"    />
					</td>
					<td class="blue" align="right">ลาป่วย (W)&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="w"   size="4" maxlength="4" /> &nbsp;&nbsp;
		 			</td>
				</tr>
				<tr>
					<td class="normal" align="right">&nbsp;&nbsp;
						<html:hidden property="xb"    />
					</td>				
					<td class="blue" align="right">ลากิจ (B)&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="b"   size="4" maxlength="4" /> &nbsp;&nbsp;
		 			</td>
				</tr>
				<tr>
					<td class="normal" align="right">&nbsp;&nbsp;
						<html:hidden property="xv"    />
					</td>				
					<td class="blue" align="right">ลาพักร้อน (V)&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="v"   size="4" maxlength="4" /> &nbsp;&nbsp;
		 			</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td>
					<td class="blue" align="right">ลาป่วยในงาน (C)&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="c"   size="4" maxlength="4" /> &nbsp;&nbsp;
		 			</td>
				</tr>
				<tr><td>&nbsp;</td>
					<td class="blue" align="right">ขาดงาน&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="a"   size="4" maxlength="4" />  
		 			</td>
				</tr>
				<tr><td>&nbsp;</td>
					<td class="blue" align="right">สาย&nbsp;&nbsp;</td>
					<td class="blue">
		 				<html:text property="l"   size="4" maxlength="4"/>
		 				&nbsp;&nbsp;คิดเป็น &nbsp;&nbsp;
		 				<html:text property="amlate"  size="4" maxlength="4"/>&nbsp;&nbsp;(นาที)
		 				
		 			</td>  
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="normal" align="right">&nbsp;&nbsp;
						<html:hidden property="xx"    />
					</td>				
					<td class="blue" align="right">ลากิจพิเศษ (X)&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="x"   size="4" maxlength="4" />&nbsp;&nbsp;
		 			</td>
				</tr>	
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td>
					<td class="blue" align="right">ชั่วโมงปกติ&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="r"   size="4" maxlength="4" />
		 			</td>
				</tr>	
				</table>
			</td>
			<td valign="top">
				<table width="100%">
				<tr>
					<td class="normal" align="right">ข้อมูลการทำ OT</td>					
				</tr>		
				<tr>
					<td class="blue" align="right">OT 1&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="otN1"   size="4" maxlength="4" />
		 			</td>
				</tr>	
				<tr>
					<td class="blue" align="right">OT 1.5&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="otN1_5"   size="4" maxlength="4" />
		 			</td>
				</tr>	
				<tr>
					<td class="blue" align="right">OT 2&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="otN2"   size="4" maxlength="4" />
		 			</td>
				</tr>	
				<tr>
					<td class="blue" align="right">OT 3&nbsp;&nbsp;</td>
					<td class="normal">
		 				<html:text property="otN3"   size="4" maxlength="4" />
		 			</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue" align="right">สถานะ&nbsp;&nbsp;</td>
					<td class="normal">
						<html:select property="workStatus">
							<html:option value="AC">ใช้งาน</html:option>	
							<html:option value="CA">ไม่ใช้งาน</html:option>
						</html:select>
					</td>
				</tr>
				</table>
			</td>
		</tr>	
		<tr><td>&nbsp;</td></tr>
		<tr><td align="center" class="red">&nbsp;<%=alertMessage%></td>
 			<td>&nbsp;
 				<html:submit property="reqCode"> 
					<bean:message bundle="employeeResources" key="timeattendform.button.update" />	
 				</html:submit>
 			</td>
        </tr>		
		</table>	
		</fieldset> 	 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			workTimeForm.w.focus()
		</SCRIPT>	
  </body>
</html:html>