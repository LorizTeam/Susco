<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.inventory.form.MatDocPeriodForm" %>
<%@ page import="com.dtac.admin.form.MonthForm" %>
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
    <title>My JSP 'MatDocPeriodList.jsp' starting page</title>
    
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
	<html:form action="matDocPeriodList" >
		<fieldset><legend><b class="blue"><br>2.2 งวดการทำเอกสาร</b></legend>
		<table align="center" width="70%">
		<tr><td>&nbsp;</td></tr>
		<tr>
		    <td class="blue">ปีเอกสาร &nbsp;:&nbsp;
       			<html:select property="year" >
	       			<%
	   			        if (request.getAttribute("yearList") != null) {
			        		List yearList = (List)request.getAttribute("yearList");
			        		for (Iterator iterYear = yearList.iterator(); iterYear.hasNext();) {
		        		  		YearForm yearForm = (YearForm) iterYear.next();
	       			%>
        			<html:option value="<%=yearForm.getYear()%>"><%=yearForm.getYear()%></html:option>
					<%		} 
						}
					%>
			  	</html:select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;เดือน &nbsp;:&nbsp;
      			<html:select property="month" >
	      			<%
		        		if (request.getAttribute("monthList") != null) {
			        		List monthList = (List)request.getAttribute("monthList");
			        		for (Iterator iterMonth = monthList.iterator(); iterMonth.hasNext();) {
		        		  		MonthForm monthForm = (MonthForm) iterMonth.next();
	      			%>
	       			<html:option value="<%=monthForm.getMonth()%>">
	       			 	<%=monthForm.getMonth()%> - <%=monthForm.getMonthTHFullName()%>
	       			</html:option>
					<%		} 
						}
					%>
				</html:select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="matdocperiodform.button.search" />
 				</html:submit>			  	
		    </td>
		</tr>
		<tr><td class="red" align="center"><%=alertMessage %></td></tr> 					
		</table>
		</fieldset> 	
		
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="50%" bgcolor="">
	 	<tr>
 			<td class="normal" align="center">
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="matdocperiodform.button.open" />
 				</html:submit>
 			</td>
 			<td class="normal" align="center">
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="matdocperiodform.button.close" />
 				</html:submit>
 			</td>
	 	</tr>
		<tr>	
			<td width="10%" align="center" class="normal">ลำดับที่</td>		
			<td width="10%" align="center" class="normal">ปี</td>		
			<td width="10%" align="center" class="normal">เดือน</td>
			<td width="10%" align="center" class="normal">สถานะ</td>
			<td width="10%" align="center" class="normal">วันเปิด</td>
			<td width="10%" align="center" class="normal">วันปิด</td>
		</tr>
		</table>
	
		<table align="center" width="50%" border="1">
		<%	if (request.getAttribute("matDocPeriodList") != null) {
				List matDocPeriodList = (List)request.getAttribute("matDocPeriodList");
				int x = 0;
				for (Iterator iter = matDocPeriodList.iterator(); iter.hasNext();) {
		  			x++;
		  			MatDocPeriodForm matDocs = (MatDocPeriodForm) iter.next();
		%>
		<tr>			
	   		<td width="10%" class="normal" align="center">
				<input type="radio" name="radio1" 
			   		onclick="matDocPeriodForm.year.value='<%=matDocs.getYear()%>';
			   				 matDocPeriodForm.month.value='<%=matDocs.getMonth()%>';
			   				 "/>&nbsp;&nbsp;<%=x%>.
	   		</td>
	   		<td width="10%" class="normal" align="center"><%=matDocs.getYear()%></td>
		    <td width="10%" class="normal" align="center"><%=matDocs.getMonth()%></td>	  
		    <% if (matDocs.getStatus().equals("AC")) { %> 		  		      	   		  
		    <td width="10%" class="blue" align="center">เปิด</td>
		    <% } else { %>
		    <td width="10%" class="red" align="center">ปิดงวด</td>
		    <% } %>
		     <td width="10%" class="normal" align="center"><%=matDocs.getStartDate()%></td>
		     <td width="10%" class="normal" align="center"><%=matDocs.getEndDate()%></td>
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>   
		</table> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			matDocPeriodForm.month.focus()
		</SCRIPT>	
  </body>
</html:html>