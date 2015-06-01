<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="com.dtac.utils.DateUtil" %>


<%@ page import="com.dtac.inventory.form.MemberSearchForm" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	MemberSearchForm memberSearchForm = new MemberSearchForm();
	if (request.getAttribute("MemberSearchForm") != null)
	memberSearchForm = (MemberSearchForm) request.getAttribute("MemberSearchForm");
		
	String alertMessage = "";
	if(request.getAttribute("alertMessage")!=null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 
		
	String member_id = "";
	if(request.getAttribute("member_id")!=null) 
		member_id = (String) request.getAttribute("member_id");
	
	String loginId = "";
	if(request.getAttribute("loginId")!=null) 
		loginId = (String) request.getAttribute("loginId"); 
			
   		loginId = (String) session.getAttribute("loginId");
   
   	String qty = "";
	if(request.getAttribute("qty")!=null) {
		qty = (String) request.getAttribute("qty");	
		memberSearchForm.setQty(qty);
		}
	
	String semesterNo = "";
	if(request.getAttribute("semesterNo")!=null) 
		semesterNo = (String) request.getAttribute("semesterNo");
			
	DateUtil dateUtil = new DateUtil();			
	
	String textBoxId = "start";
	if (request.getAttribute("textBoxId") != null) 
		textBoxId = (String) request.getAttribute("textBoxId");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">  
    <title>My JSP 'MaterialRecvHDList.jsp' starting page</title>
    
	<%@ include file="../../css/bootstrap.css" %>
	<%@ include file="../../css/bootstrap-theme.css" %>
	<%@ include file="../../css/inv.css" %>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

<script Language="javascript">
function check_null(form) {
	missinginfo = "";
	//---------------------------------------- POPUP THAI ----------------------------------------------

	if (form.cardid.value == "")
	{
		missinginfo += "\n - รหัสสมาชิก";
	}
	if (missinginfo != "")
	{
		missinginfo = "กรุณาป้อนข้อมูลรหัสสมาชิก :\n" +
		"------------------------------------" +
		missinginfo + "\n------------------------------------";
		alert(missinginfo);
		return false;
	}
	else
	{
		return true;
	}
}
    function getBalance(){
    	
		document.memberSearchForm.totbalance.value=document.memberSearchForm.totpay.value-document.memberSearchForm.sumamount.value
    }
	function getMaterial(matType) {
		var load = window.open('/inv/materialSearchSetup.do?formName=materialMember&matType='+matType,'materialMember',
		                  'scrollbars=yes,menubar=no,height=600,width=1300,resizable=yes,toolbar=no,location=no,status=no');
	}		
    function press(event, textid) {
	//alert(textid);
		if (event.keyCode == 13) {
		 document.memberSearchForm.textBoxId.value = textid;
		 
			if (textid == 'studentid')			document.getElementById('check').focus();
        	else if (textid == 'check')    		document.getElementById('print1').select();
        	//else if (textid == 'qty')    		document.getElementById('matcode').select();
      //  	else if (textid == 'matcode')    	document.getElementById('okey').focus();
		  // In IE9 the focus shifts to the <button> unless we call preventDefault(). Uncomment following line for IE9 fix. Alternatively set type="button" on all button elements and anything else that is a submit or reset too!.
             event.preventDefault();
             event.keyCode = 9;
        }	
    }

</script>
<script>
    var inputamount;
    function btnNum(input){
    	// Start Check input
    	if(input == "CODE"){
    		document.getElementById("flowcode").value = "1";
    		document.getElementById("flowtel").value = "0";
    		document.getElementById("flowamount").value = "0";
    		document.getElementById("flowmoney").value = "0";
    		inputamount = 0;
    	}else if(input == "TEL"){
    		document.getElementById("flowcode").value = "0";
    		document.getElementById("flowtel").value = "1";
    		document.getElementById("flowamount").value = "0";
    		document.getElementById("flowmoney").value = "0";
    		inputamount = 0;
    	}else if(input == "AMOUNT"){
    		document.getElementById("flowcode").value = "0";
    		document.getElementById("flowtel").value = "0";
    		document.getElementById("flowamount").value = "1";
    		document.getElementById("flowmoney").value = "0";
    		inputamount = 0;
    	}else if(input == "MONEY"){
    		document.getElementById("flowcode").value = "0";
    		document.getElementById("flowtel").value = "0";
    		document.getElementById("flowamount").value = "0";
    		document.getElementById("flowmoney").value = "1";
    		inputamount = 0;
    		document.getElementById("totpay").value = "";
    	}
    	// End Check input
    	//Start Input
    	if(document.getElementById("flowcode").value == "1"){
	    	if(input != "DEL" && input != "OK" && input != "." && input != "C" && input != "TEL" && input != "AMOUNT" && input != "CODE" && input != "MONEY" && input != "PRINT"){
		    	document.getElementById("matcode").value += input;
	    	}else if(input == "DEL"){
	    		var textform = document.getElementById("matcode").value;
		    	document.getElementById("matcode").value = textform.substring(0,textform.length -1);
	    	}else if(input == "C"){
		    	document.getElementById("matcode").value = "";
	    	}else if(input == "."){
		    	document.getElementById("matcode").value += input;
	    	}
    	}else if(document.getElementById("flowtel").value == "1"){
	    	if(input != "DEL" && input != "OK" && input != "." && input != "C" && input != "TEL" && input != "AMOUNT" && input != "CODE" && input != "MONEY" && input != "PRINT"){
		    	document.getElementById("studentid").value += input;
	    	}else if(input == "DEL"){
	    		var textform = document.getElementById("studentid").value;
		    	document.getElementById("studentid").value = textform.substring(0,textform.length -1);
	    	}else if(input == "C"){
		    	document.getElementById("studentid").value = "";
	    	}else if(input == "."){
		    	document.getElementById("studentid").value += input;
	    	}
    	}else if(document.getElementById("flowamount").value == "1"){
    	//เช็คสถานะของการเพิ่มข้อมูล ถ้าเป็นการคลิกครั้งแรก จะเครียค่าในช่อง qty ให้เป็น "" แต่ถ้าไม่ใช่การกดครั้งแรกจะเป็นการต่อตัวเลขเข้าไปจากเดิม
    		if(inputamount == 1){
    			if(input != "DEL" && input != "OK" && input != "." && input != "C" && input != "TEL" && input != "AMOUNT" && input != "CODE" && input != "MONEY" && input != "PRINT"){
		    	document.getElementById("qty").value += input;
		    	}else if(input == "DEL"){
		    		var textform = document.getElementById("qty").value;
			    	document.getElementById("qty").value = textform.substring(0,textform.length -1);
		    	}else if(input == "C"){
			    	document.getElementById("qty").value = "";
		    	}else if(input == "."){
			    	document.getElementById("qty").value += input;
		    	}
	    	}else{
	    		document.getElementById("qty").value = "";
    			if(input != "DEL" && input != "OK" && input != "." && input != "C" && input != "TEL" && input != "AMOUNT" && input != "CODE" && input != "MONEY" && input != "PRINT"){
		    		document.getElementById("qty").value += input;
		    		inputamount =1;
		    	}else if(input == "DEL"){
		    		var textform = document.getElementById("qty").value;
			    	document.getElementById("qty").value = textform.substring(0,textform.length -1);
			    	inputamount =1;
		    	}else if(input == "C"){
			    	document.getElementById("qty").value = "";
			    	inputamount =1;
		    	}else if(input == "."){
			    	document.getElementById("qty").value += input;
			    	inputamount =1;
		    	}
    		}
    	}else if(document.getElementById("flowmoney").value == "1"){
	    	if(input != "DEL" && input != "OK" && input != "." && input != "C" && input != "TEL" && input != "AMOUNT" && input != "CODE" && input != "MONEY" && input != "PRINT"){
		    	document.getElementById("totpay").value += input;
		    	getBalance();
	    	}else if(input == "DEL"){
	    		var textform = document.getElementById("totpay").value;
		    	document.getElementById("totpay").value = textform.substring(0,textform.length -1);
		    	getBalance();
	    	}else if(input == "C"){
		    	document.getElementById("totpay").value = "";
		    	getBalance();
	    	}else if(input == "."){
		    	document.getElementById("totpay").value += input;
		    	getBalance();
	    	}
    	}
    	//End Input
    }
    function myOK(input){
    // document.getElementById("flowmoney").value == "1"
    	if(document.getElementById("flowamount").value == "1" ){
    		document.getElementById("flowcode").value = "0";
    		document.getElementById("flowtel").value = "0";
    		document.getElementById("flowamount").value = "0";
    		document.getElementById("flowmoney").value = "1";
    		inputamount = 0;
    		document.getElementById("totpay").value = "";
    	}
    	if(document.getElementById("flowmoney").value == "1" ){
    		getBalance();
    	}
    }	
</script>

 </head>

<body bgcolor="#95b2c3">
<html:form action="/memberSearch" method="POST">
		<h1 class="text-center text-success"><b>ยินดีต้อนรับเข้าสู่ POS System</b></h1>
		
		<html:hidden property="sysdate" value="<%=dateUtil.curDate()%>" /> 
		<!-- ------------------------- add product ----------------------- -->
	<div class="container-fluid thumbnail">
	<div class="col-md-6">
		<div class="row">
			<div class="col-md-3 col-sm-3">
				<h4 class="text-primary">เพิ่มสินค้า</h4>
			</div>
			
		</div>
		<fieldset class="thumbnail">
		<div class="row">
			<div class="col-md-3 col-sm-3 text-right">
				<h4 class="text-primary">รหัสสินค้า</h4>
			</div>
			<div class="col-md-3 col-sm-3">
				<html:text property="matcode" styleClass="form-control" styleId="matcode"/>
			</div>
			<div class="col-md-1 col-sm-1">
				<a href="javascript:getMaterial('001');" class="">
					<img src="jsp/icons/search.gif" />
				</a>
			</div>	
			<BDO dir="rtl"><html:hidden property="semesterNo"/></BDO>
			<div class="col-md-1 col-sm-1">
				<h5 class="text-primary">จำนวน </h5>
			</div>	
			<div class="col-md-2 col-sm-2">
				<html:text property="qty" styleId="qty" styleClass="form-control" 
				 value = "<%= memberSearchForm.getQty()%>" onkeypress="press(event,'qty');"/>		
			</div>
			<div class="col-md-1 col-sm-1">
				<html:submit styleClass="btn btn-success" property="ok" value="เพิ่ม" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-sm-6 text-right">
			</div>	
			<div class="col-md-2 col-sm-2 text-right">
				<h5 class="text-primary">ยอดรวม </h5>
			</div>	
			<div class="col-md-2 col-sm-2">
				<html:text property="sumamount" readonly="true" styleClass="form-control" 
				 value="<%= Float.toString(memberSearchForm.getSumamount())%>"  /> 		
			</div>
			<div class="col-md-1 col-sm-1">
				<h5 class="text-primary"> บาท </h5>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-sm-6 text-right">
			</div>	
			<div class="col-md-2 col-sm-2 text-right">
				<h5 class="text-primary">จ่าย</h5>
			</div>	
			<div class="col-md-2 col-sm-2">
				<html:text property="totpay" styleId="totpay" styleClass="form-control" onkeyup="getBalance();" />	
			</div>
			<div class="col-md-1 col-sm-1">
				<h5 class="text-primary"> บาท </h5>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-sm-6 text-right">
			</div>	
			<div class="col-md-2 col-sm-2 text-right">
				<h5 class="text-primary">ทอน</h5>
			</div>	
			<div class="col-md-2 col-sm-2">
				<html:text property="totbalance" readonly="true" styleClass="form-control" onkeyup="getBalance();" />	
			</div>
			<div class="col-md-1 col-sm-1">
				<h5 class="text-primary"> บาท </h5>
			</div>
		</div>
		 	<html:hidden property="textBoxId"/>
		</fieldset>
		
		<fieldset class=" thumbnail">
		<div class="row">
			<div class="col-md-3 col-sm-3">
<!--				<h4 class="text-primary">ข้อมูลสมาชิก</h4>-->
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 col-sm-3">
				<h4 class="text-primary">เบอร์โทรศัพท์</h4>
			</div>
			<div class="col-md-6 col-sm-6">
			<html:text property="studentID" styleId="studentid"  
			styleClass="form-control" onkeypress="press(event,'studentid');" />
			<html:submit styleClass="btn btn-brown btn-lg col-md-12" property="billpayment" value="Bill Payment" />	   	
			</div>
		</div>
		</fieldset>	 
		
		<fieldset class="thumbnail cal">
			<div class="row">
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-brown btn-lg col-md-12" property="code" value="CODE" /></div>
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-brown btn-lg col-md-12" property="amount" value="AMOUNT" /></div>
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-brown btn-lg col-md-12" property="money" value="MONEY"/></div>
				<div class="col-md-2"><html:button onclick="btnNum(this.value)" styleClass="btn btn-brown btn-lg col-md-12" property="tel" value="TEL"/></div>				
			</div>	
			<div class="row">
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="one" value="1" /></div>
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="two" value="2" /></div>
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="three" value="3"/></div>
				<div class="col-md-2"><html:button onclick="btnNum(this.value)" styleClass="btn btn-danger btn-lg col-md-12" property="clear" value="C"/></div>
			</div>	
			<div class="row">
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="four" value="4"/></div>
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="five" value="5" /> </div>
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="six" value="6" /></div>
				<div class="col-md-2"><html:button onclick="btnNum(this.value)" styleClass="btn btn-warning btn-lg col-md-12" property="three" value="DEL"/></div>				
			</div>	
			<div class="row">
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="seven" value="7"/></div>
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="eight" value="8"/></div>
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="nine" value="9"/></div>
				<div class="col-md-2"><html:button onclick="myOK(this.value)" styleClass="btn btn-primary btn-lg col-md-12" property="OK" value="OK"/></div>				
			</div>	
			<div class="row">
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-default btn-lg col-md-12" property="buttcard" value="."/></div>
				<div class="col-md-3"><html:button onclick="btnNum(this.value)" styleClass="btn btn-info btn-lg col-md-12" property="zero" value="0" /></div>
				<div class="col-md-5">
					<html:submit styleClass="btn btn-success btn-lg col-md-12" property="print"  
					value="Print" styleId="print1" onfocus="press(event,'print1');"/>
				</div>
			</div>			
				<html:hidden property="studentName"  />
			   	<html:hidden property="studentClass" /> 	 		     
				<html:hidden property="recno" value="<%=Integer.toString(memberSearchForm.getRecno())%>" />
				<html:hidden property="hdbuttmat"  value="<%=memberSearchForm.getHdbuttmat()%>" />
				<html:hidden property="matTypeCode"/>
				<html:hidden property="matGrpCode"/>
				<html:hidden property="hdbuttcard"  />
				<html:hidden property="hdbuttqty"   />
				<input type="hidden" id="flowcode" value="0"/>
				<input type="hidden" id="flowtel" value="0"/>
				<input type="hidden" id="flowamount" value="1"/>
				<input type="hidden" id="flowmoney" value="0"/>
				<font color="red"><%=alertMessage%></font>
		</fieldset>
			
	</div>
	<!-- ------------------------- add product ----------------------- -->
	<!-- ------------------------- table product ----------------------- -->
	<div class="col-md-6 ">
		<div class="row">
			<div class="col-md-3 col-sm-3 text-right">
				<h4 class="text-primary">เลขที่ใบเสร็จ</h4>
			</div>
			<div class="col-md-3 col-sm-3  ">
				<html:text property="requestNo" readonly="true" styleClass="form-control" />
			</div>
			<div class="col-md-6 col-sm-6 text-right">
				<h4 class="text-primary">วันที่ : <%=dateUtil.curDate()%></h4>				
			</div>
		</div>
		<fieldset class="thumbnail">
		
		<table class="table table-bordered table-striped"> 
			<tr>	
				<th><html:submit property="delete " styleClass="btn btn-danger btn-sm" value="ลบ"/></th>
				<th>No</th>
				<th class="hide">รหัสสินค้า</th>			 
				<th >สินค้า</th>			
				<th >จำนวน</th>
				<th >ราคา</th>
				<th >รวม</th>
			</tr>
		
		<%	if (request.getAttribute("itemlist") != null) {
				List itemList = (List)request.getAttribute("itemlist");
				int x = 0;
				float sumamount = 0f;
				for (Iterator iter = itemList.iterator(); iter.hasNext();) {
		  			x++;
		  			MemberSearchForm itemForm = (MemberSearchForm) iter.next();
		  			 
		%>
		<tr>			
	   		<td>
		<input type="radio" name="radio1" onclick="memberSearchForm.matcode.value='<%=itemForm.getMatcode()%>';  memberSearchForm.qty.value='<%=itemForm.getQty()%>'; memberSearchForm.recno.value='<%=x%>';"/> 
			   
			   <html:hidden property="hdrecno" value="<%=Integer.toString(x)%>" />
			     
			   	<html:hidden property="oldMatTypeCode" value = "<%=itemForm.getMatTypeCode()%>"/>
			   	<html:hidden property="oldMatGrpCode" value = "<%=itemForm.getMatGrpCode()%>"/>
			   	<html:hidden property="oldSerial" value = "<%=itemForm.getSerial()%>"/>
			   	<html:hidden property="oldRefmatcode" value = "<%=itemForm.getRefmatcode()%>"/>	    			
	   		</td>
	   	<td class="hide"><html:hidden property="oldMatCode" value = "<%=itemForm.getMatcode()%>"/><font size="3:px"><%=itemForm.getMatcode()%></font></td>
			<td><%= x %>.</td>
	   		<td><html:hidden property="oldMatName" value = "<%=itemForm.getMatname()%>"/>&nbsp;<font size="3:px"><%=itemForm.getMatname()%></font></td>
	   		<td><html:hidden property="oldquantity" value = "<%=itemForm.getQty()%>"/><font size="3:px"><%=itemForm.getQty()%></font></td>
		    <td><html:hidden property="oldamount" value = "<%=Float.toString(itemForm.getAmount())%>"/><font size="3:px"><%=itemForm.getAmount()%></font></td>
		    <td><html:hidden property="oldtotalamount" value = "<%=Float.toString(itemForm.getTotalamount())%>"/><font size="3:px"><%=itemForm.getTotalamount()%></font></td>
			
		    <% 
		    }
		     %>
		    
	    </tr>  
	 	<%	   
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>  
		</table> 	
		</fieldset>
		
		
		
	</div>
	<!-- ------------------------- table product ----------------------- -->
	</div>	
	<!--	<table >
			   
				
				 
           <tr>
                	<td class="barc">

					<html:submit styleClass="barc" property="buttmat"  value="MatC"/> 
					
					<html:submit styleClass="barc" property="chk"  
					value="Check" styleId="check" onfocus="press(event,'check');" />
					<html:submit styleClass="barc" property="save" value="Save"/>
				
				
					</td>
	
				</tr>		
		</table>-->
			
</html:form>
 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
		<% if (textBoxId.equals("print")) { %>
			memberSearchForm.print.focus()
		<% }  %>
		<% if (textBoxId.equals("ok")) { %>
			memberSearchForm.matcode.focus()
		<% } %>
		<% if (textBoxId.equals("start")) { %>
			memberSearchForm.matcode.focus()
		<% } %>
	</SCRIPT>
</body>
</html:html>
