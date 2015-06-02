<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm" %>
<%@ include file="../admin/css_blue_style.css" %>
<%
 	String path = request.getContextPath();
 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String grpActionCode = "";
	if (request.getAttribute("grpCode") != null){
		grpActionCode = (String) request.getAttribute("grpCode");
	}
	
	
	String alertMessage = "";
	if (request.getAttribute("alertMessage") != null) 
		alertMessage = (String) request.getAttribute("alertMessage"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'MasterTableList.jsp' starting page</title>
    
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
	<html:form action="/masterTableList" >
		<fieldset><%if (grpActionCode.equals("unit")){%>
		<legend><b class="normal">1.1 Data </b></legend>
		<% } else if(grpActionCode.equals("bran")){ %>
		<legend><b class="normal">1.2 Brand </b></legend>
		 <% } else if(grpActionCode.equals("colo")){ %>
		<legend><b class="normal">1.3 Colour </b></legend>
		 <% } else if(grpActionCode.equals("stuf")){ %>
		<legend><b class="normal">1.4 Group </b></legend>
		 <% } else if(grpActionCode.equals("size")){ %>
		<legend><b class="normal">1.5 Size </b></legend>
		 <%} %>
		 
		<table align="center" width="60%">
 		<tr>	
 			<td class="blue">Type &nbsp;<font color="red">*</font>:&nbsp;
       			<html:select property="grpCode" >
	       			<%
	   					if (request.getAttribute("grpList") != null) {
	   						List grpList = (List)request.getAttribute("grpList");
	   						for (Iterator iterGrp = grpList.iterator(); iterGrp.hasNext();) {
		   				  		MasterTableForm masterForm = (MasterTableForm) iterGrp.next();
	       			%>
        			<html:option value="<%=masterForm.getGrpCode()%>">
        				<%=masterForm.getGrpName()%>
        			</html:option>
					<% 		} 
						}
					%>
			  	</html:select>
			  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
					<bean:message bundle="adminResources" key="memberform.button.search" />	
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<html:submit property="reqCode"> 
					<bean:message bundle="adminResources" key="memberform.button.add" />	
 				</html:submit>
 			</td>
 			<td class="blue">Status&nbsp;:&nbsp;
       			<html:select property="status" >
        			<html:option value="AC">Active</html:option>
        			<html:option value="CA">InActive</html:option>        			
			  	</html:select>
 				<html:hidden property="applTypeCode" />
 				<html:hidden property="typeCode" /> </td>
		</tr>
		<tr>
			<td colspan="2" align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>

		</table>
		</fieldset>
 	
	 	<table align="center" border="1" cellpadding="0" cellspacing="0" width="70%" bgcolor="lightslategray">
		<tr>	
			<td width="15%" align="center" class="normal">
				<html:submit property="reqCode"> 
					<bean:message bundle="adminResources" key="memberform.button.update" />	
 				</html:submit>			
				&nbsp;&nbsp;Code
			</td>	
			<%if(grpActionCode.equals("unit")){%>
			<td width="30%" align="center" class="normal"> ชื่อไทย Unit</td>
			<% } else if(grpActionCode.equals("bran")){ %>
			<td width="30%" align="center" class="normal"> ชื่อไทย Brand</td>	
			<% } else if(grpActionCode.equals("size")){ %>
			<td width="30%" align="center" class="normal"> ชื่อไทย Size</td>
			<% } else if(grpActionCode.equals("colo")){ %> 
			<td width="30%" align="center" class="normal"> ชื่อไทย Colour</td>
			<% } else if(grpActionCode.equals("stuf")){ %>
			<td width="30%" align="center" class="normal"> ชื่อไทย Group</td>
			<%} 
			%>
			
			<%if(grpActionCode.equals("unit")){%>
			<td width="20%" align="center" class="normal"> ชื่ออังกฤษ Unit</td>
			<% } else if(grpActionCode.equals("bran")){ %>
			<td width="20%" align="center" class="normal"> ชื่ออังกฤษ Brand</td>	
			<% } else if(grpActionCode.equals("size")){ %>
			<td width="20%" align="center" class="normal"> ชื่ออังกฤษ Size</td>
			<% } else if(grpActionCode.equals("colo")){ %> 
			<td width="20%" align="center" class="normal"> ชื่ออังกฤษ Colour</td>
			<% } else if(grpActionCode.equals("stuf")){ %>
			<td width="20%" align="center" class="normal"> ชื่ออังกฤษ  Group</td>
			<%} 
			%>
	
			<td width="10%" align="center" class="normal">Status</td>	
		</tr>
		</table>
	
		<table align="center" width="70%" border="1">
		<%	if (request.getAttribute("typeList") != null) {
				List typeList = (List)request.getAttribute("typeList");
				for (int x=0; x<typeList.size(); x++) {
					MasterTableForm items = (MasterTableForm) typeList.get(x);
		%>
		<tr>
   		  	<td width="15%" class="normal">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		  	<input type="radio" name="radio1" 
	   		  			onclick="masterTableForm.grpCode.value='<%=items.getGrpCode()%>';
  		  						 masterTableForm.typeCode.value='<%=items.getTypeCode()%>';"/>
	   		  	<%=items.getTypeCode()%>
   		  	</td>
	      	<td width="30%" align="center" class="normal">&nbsp;<%=items.getThName()%></td>
	      	<td width="20%" align="center" class="normal">&nbsp;<%=items.getEnName()%></td>
 			<% 
 			
 				if (items.getStatus().equals("CA")) { %>
		    <td width="10%" class="normal" align="center">&nbsp;Inactive</td>	   		  		      		      		      
			<% } else {%>		      
		    <td width="10%" class="normal" align="center">&nbsp;Active</td>	   		
		   	   		      		      		      		      
		    <% }} %>	      	     	
	    </tr>  
		<% 		} 
	 		 else {
		%>
		<tr><td align="center" colspan="5">No Data Found</td></tr>
		<%	} %>   	
		</table>
  	</html:form>	
  </body>
</html:html>