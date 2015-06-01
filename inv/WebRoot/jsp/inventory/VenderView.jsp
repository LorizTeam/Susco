<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm"%>
<%@ include file="../admin/css_blue_style.css"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";

	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null)
		alertMessage = (String) request.getAttribute("alertMessage");

	String empID = "";
	if (request.getAttribute("empID") != null)
		empID = (String) request.getAttribute("empID");
		
	String pic1 = "";
	if (request.getAttribute("pic1") != null) 
		pic1 = (String) request.getAttribute("pic1"); 
		
	String pic2 = "";
	if (request.getAttribute("pic2") != null) 
		pic2 = (String) request.getAttribute("pic2");
		
	String pic3 = "";
	if (request.getAttribute("pic3") !=null)
		pic3  = (String) request.getAttribute("pic3");
	 
	String pic4 = "";
	if (request.getAttribute("pic4") !=null)
		pic4  = (String) request.getAttribute("pic4");
		
	String pic5 = "";
	if (request.getAttribute("pic5") !=null)
		pic5  = (String) request.getAttribute("pic5");
			
	String venderCode =  "";
	if (request.getAttribute("venderCode") != null)
	venderCode = (String) request.getAttribute("venderCode");
%>
<html:html locale="true">
<head>
	<script src="/dtac/SpryTabbedPanels.js" type="text/javascript"></script>
	<link href="/dtac/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/dtac/jsp/js/tooltip_hiso/style.css" />
	<script type="text/javascript" src="/dtac/jsp/js/tooltip_hiso/script.js"></script>
	<script type="text/javascript" Language="javascript">

	function uploadPicture(picNo, idCode) {
		var load = window.open('/dtac/jsp/admin/FileUpload.jsp?pathName=vender&picNo='+picNo+'&idCode='+idCode,'vender','scrollbars=no,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
	}	
	function reloadPic1() {
		img = document.getElementById('pic1');
		img.src = '/dtac/jsp/image/vender/'+document.venderForm.pic1.value
	}
	function reloadPic2() {
		img = document.getElementById('pic2');
		img.src = '/dtac/jsp/image/vender/'+document.venderForm.pic2.value
	}
	function reloadPic3() {
		img = document.getElementById('pic3');
		img.src = '/dtac/jsp/image/vender/'+document.venderForm.pic3.value
	}
	function reloadPic4() {
		img = document.getElementById('pic4');
		img.src = '/dtac/jsp/image/vender/'+document.venderForm.pic4.value
	}
	function reloadPic5() {
		img = document.getElementById('pic5');
		img.src = '/dtac/jsp/image/vender/'+document.venderForm.pic5.value
	}
	</script>
  </head>

  <body class="blueback">
	<html:form action="/venderView">
		<fieldset><legend><b class="blue"><br>1.7 Vender Data</b></legend>
		<table align="center" width="100%">
		<tr>
			<td class="normal">รหัสผู้ขาย &nbsp;:&nbsp;
				<html:text property="venderCode" size="6" maxlength="10" readonly="true" />
			</td>
			<td class="blue">ชื่อผู้ขาย &nbsp;:&nbsp;
				<html:text property="venderName" size="30" maxlength="50" />
			</td>
		</tr>
		<tr>
			<td class="blue">คำที่ใช้ค้นหา &nbsp;:&nbsp;
				<html:text property="searchName" size="30" maxlength="50" />
			</td>
			<td class="blue">สถานะ&nbsp;:&nbsp;
				<html:select property="status">
					<html:option value="AC">ใช้งาน</html:option>	
					<html:option value="CA">ไม่ใช้งาน</html:option>
				</html:select>
			</td>			
		</tr>
		<tr>
			<td class="red">&nbsp;<%=alertMessage%></td>
			<td class="normal">
				<html:submit property="reqCode">
					<bean:message bundle="inventoryResources" key="venderform.button.update" />
				</html:submit>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		</table>
		</fieldset>

		<div id="TabbedPanels1" class="TabbedPanels">
			<ul class="TabbedPanelsTabGroup">
				<li class="TabbedPanelsTab" tabindex="0">ข้อมูลทั่วไปผู้ขาย</li>
				<li class="TabbedPanelsTab" tabindex="0">ที่อยู่จดทะเบียน</li>
				<li class="TabbedPanelsTab" tabindex="0">attach file</li>
			</ul>

			<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">ประเภทผู้ขาย &nbsp;:&nbsp;
						<html:select property="venderTypeCode">
						<% if (request.getAttribute("vendTypeList") != null) {
							List vendTypeList = (List) request.getAttribute("vendTypeList");
							for (Iterator iterDept = vendTypeList.iterator(); iterDept.hasNext();) {
								MasterTableForm vendTypeForm = (MasterTableForm) iterDept.next();
						%>
							<html:option value="<%=vendTypeForm.getTypeCode()%>"><%=vendTypeForm.getThName()%></html:option>
						<%
							}
								}
						%>
						</html:select>
					</td>
					<td class="blue">เลขประจำตัวผู้เสียภาษี &nbsp;:&nbsp;
						<html:text property="venderTaxId" size="10" maxlength="20" />
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">วงเงินเครดิต&nbsp;:&nbsp;
						<html:text property="creditLimit" size="10" maxlength="20" />
					</td>
					<td class="blue">วงเงินเครดิตคงเหลือ&nbsp;:&nbsp;
						<html:text property="creditAvail" size="10" maxlength="20" />
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				</table>
				</div>

				<div class="TabbedPanelsContent">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td class="normal">&nbsp;ที่อยู่จดทะเบียน</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">&nbsp;ที่อยู่ : 
						<html:text property="regiAddr1" size="40" maxlength="80" />
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">  
						&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  
						<html:text property="regiAddr2" size="40" maxlength="80" />
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">  
						&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  
						<html:text property="regiAddr3" size="40" maxlength="80" />
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">ผู้ติดต่อ 1 &nbsp;:&nbsp; 
						<html:text property="regiCont1" size="37" maxlength="60" />
					</td> 
					<td class="blue">ตำแหน่ง  &nbsp;:&nbsp; 
						<html:text property="regiPosi1" size="37" maxlength="60"></html:text>
					</td>
					<td class="blue">Email &nbsp;:&nbsp; 
						<html:text property="regiEmail1" size="37" maxlength="60"></html:text>
					</td>							
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">เบอร์โทร&nbsp;:&nbsp; 
						<html:text property="regiTel1" size="37" maxlength="60"></html:text>
					</td> 
					<td class="blue">มือถือ &nbsp;:&nbsp; 
						<html:text property="regiMob1" size="37" maxlength="60"></html:text>
					</td>
					<td class="blue">แฟกซ์ &nbsp;:&nbsp; 
						<html:text property="regiFax1" size="37" maxlength="60"></html:text>
					</td>							
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">ผู้ติดต่อ 2 &nbsp;:&nbsp; 
						<html:text property="regiCont2" size="37" maxlength="60" />
					</td> 
					<td class="blue">ตำแหน่ง  &nbsp;:&nbsp; 
						<html:text property="regiPosi2" size="37" maxlength="60"></html:text>
					</td>
					<td class="blue">Email &nbsp;:&nbsp; 
						<html:text property="regiEmail2" size="37" maxlength="60"></html:text>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td class="blue">เบอร์โทร &nbsp;:&nbsp; 
						<html:text property="regiTel2" size="37" maxlength="60"></html:text>
					</td> 
					<td class="blue">มือถือ &nbsp;:&nbsp; 
						<html:text property="regiMob2" size="37" maxlength="60"></html:text>
					</td>
					<td class="blue">แฟกซ์ &nbsp;:&nbsp; 
						<html:text property="regiFax2" size="37" maxlength="60"></html:text>
					</td>							
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="blue" colspan="2">หมายเหตุ&nbsp;:&nbsp;
						<html:textarea property="regiRemark"  rows="3" cols="80"/>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				</table>
				</div>
			<div class="TabbedPanelsContent">
					
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td class="normal">&nbsp;Attach file</td>
				</tr>
				<tr><td>&nbsp;</td></tr>						
				<tr>
					<td rowspan="2" width="30%" align="center">
						<a href="javascript:reloadPic1();"> 
						<img src=".\\jsp\\image\\vender\\<%=pic1.trim()%>" id="pic1" width="100" height="120" /> </a><br/><br/>
						
						<a href="javascript:uploadPicture('1','<%=venderCode.trim()%>');">Pic1</a>
						<html:text property="pic1" readonly ="true" />
					</td>
					
					<td rowspan="2" width="30%" align="center">
						<a href="javascript:reloadPic2();"> 
						<img src=".\\jsp\\image\\vender\\<%=pic2.trim()%>" id="pic2" width="100" height="120" /> </a> <br/><br/>
						
						<a href="javascript:uploadPicture('2','<%=venderCode.trim()%>');"> Pic2</a>
						<html:text property="pic2" readonly ="true" />
					</td>
					
					<td rowspan="2" width="30%" align="center">
						<a href="javascript:reloadPic3();"> 
						<img src=".\\jsp\\image\\vender\\<%=pic3.trim()%>" id="pic3" width="100" height="120" /> </a> <br/><br/>
						
						<a href="javascript:uploadPicture('3','<%=venderCode.trim()%>');"> Pic3</a>
						<html:text property="pic3" readonly ="true" />
					</td>
												
				</tr>
								
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
						
				<tr>
					<td rowspan="2" width="30%" align="center">
						<a href="javascript:reloadPic4();"> 
						<img src=".\\jsp\\image\\vender\\<%=pic4.trim()%>" id="pic4" width="100" height="120" /> </a><br/><br/>
						
						<a href="javascript:uploadPicture('4','<%=venderCode.trim()%>');">Pic4</a>
						<html:text property="pic4" readonly ="true" />
					</td>
					
					<td rowspan="2" width="30%" align="center">
						<a href="javascript:reloadPic5();"> 
						<img src=".\\jsp\\image\\vender\\<%=pic5.trim()%>" id="pic5" width="100" height="120" /> </a> <br/><br/>
						
						<a href="javascript:uploadPicture('5','<%=venderCode.trim()%>');"> Pic5</a>
						<html:text property="pic5" readonly ="true" />
					</td>
					
							
				</tr>							
				<tr><td>&nbsp;</td></tr>					
					
				</table>
						
				</div>
			</div>
		</div>
	<script type="text/javascript">
		var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
	</html:form>
  </body>
</html:html>