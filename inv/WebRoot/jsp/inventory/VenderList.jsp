<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dtac.admin.form.MasterTableForm"%>
<%@ page import="com.dtac.inventory.form.VenderForm" %>
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
    <title>My JSP 'VenderList.jsp' starting page</title>
    
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
	<html:form action="/venderList" >
		<fieldset><legend><b class="normal">1.7 Vender</b></legend>
		<table align="center" width="95%">
		<tr>
 			<td class="blue">Vender Code&nbsp;:&nbsp;
 				<html:text property="venderCode" 		size="6" 	maxlength="6"/>
 				&nbsp;&nbsp;&nbsp;Vender Name&nbsp;:&nbsp;
				<html:text property="venderName" 		size="20" 	maxlength="30"/>
 			</td>
			<td class="blue">Search&nbsp;:&nbsp;
				<html:text property="searchName"	size="20" 	maxlength="30"/>
			</td>
			<td class="blue">Vender Type&nbsp;:&nbsp;
				<html:select property="venderTypeCode">
					<%
						if (request.getAttribute("vendTypeList") != null) {
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
 		</tr>
		<tr>
		   	<td class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   		Status &nbsp;:&nbsp;				
				<html:select property="status" >
					<html:option value="AC">Active</html:option>
					<html:option value="CA">Inactive</html:option>
				</html:select>
			</td>		
			<td colspan="2" align="center" class="red">&nbsp;<%=alertMessage%></td>
		</tr>
		<tr>
 			<td class="normal" colspan="2" align="center">
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="venderform.button.search" />
 				</html:submit>&nbsp;&nbsp;&nbsp;&nbsp; 				
 				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="venderform.button.adddata" />
 				</html:submit> 				
 			</td>
        </tr>		
		</table>
		</fieldset> 	
		
	 	
	
		<table align="center" width="90%" border="1">
		<tr>
 			<td colspan="2" class="normal" align="center">
				<html:submit property="reqCode"> 
 					<bean:message bundle="inventoryResources" key="venderform.button.view" />
 				</html:submit>
 			</td>
	 	</tr>
			<tr bgcolor="#8E8EA3">
				<td width="10%" align="center" class="normal">Vender Code</td>		
				<td width="10%" align="center" class="normal">Vender</td>		
				<td width="20%" align="center" class="normal">Search</td>
				<td width="15%" align="center" class="normal">Vender Type</td>
				<td width="10%" align="center" class="normal">Status</td>
			</tr>
		<%	if (request.getAttribute("venderList") != null) {
				List venderList = (List)request.getAttribute("venderList");
				int x = 0;
				for (Iterator iter = venderList.iterator(); iter.hasNext();) {
		  			x++;
		  			VenderForm vender = (VenderForm) iter.next();
		%>
		<tr>			
	   		<td width="10%" class="normal" align="center">
				<input type="radio" name="radio1" 
			   		onclick="venderForm.venderCode.value='<%=vender.getVenderCode()%>';
			   			 	 venderForm.venderName.value = '<%=vender.getVenderName()%>';
			   				 venderForm.searchName.value='<%=vender.getSearchName()%>';
			   				 venderForm.venderTypeCode.value='<%=vender.getVenderTypeCode()%>'"/>&nbsp;&nbsp;<%=vender.getVenderCode()%>
	   		</td>
	   		<td width="10%" class="normal" align="center"><%=vender.getVenderName()%></td>
		    <td width="20%" class="normal" align="center"><%=vender.getSearchName()%></td>	   		  		      	   		  
		    <td width="15%" class="normal" align="center"><%=vender.getVenderTypeName()%></td>

		    <% if (vender.getStatus().equals("AC")) { %>
		    <td width="10%" class="normal" align="center">&nbsp;Active</td>
		    <% } else if (vender.getStatus().equals("CA")) { %>	
		    <td width="10%" class="red" align="center">&nbsp;Inactive</td>
	   		<% } else { %>
	   		<td width="10%" class="normal" align="center">&nbsp;</td>
	   		<% } %>
		      
	    </tr>  
	 	<%		} 
 			} else {
		%>
		<tr><td align="center" colspan="9">No Data Found</td></tr>
		<%	} %>   
		</table> 	
	</html:form>
	 	<SCRIPT TYPE="TEXT/JAVASCRIPT">
			venderForm.venderCode.focus()
		</SCRIPT>	
  </body>
</html:html>