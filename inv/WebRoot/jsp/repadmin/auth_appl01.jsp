<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.*" %>
<%@ page import="com.dtac.utils.DBConnect" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%
	Connection conn = null;
    try {
	  	DBConnect agent = new DBConnect();
  	  	conn = agent.getConnectMYSql();

  	  	String appType = ""; 
		if (request.getAttribute("appType")!= null) appType = (String) request.getAttribute("appType");

		String memberId = ""; 
		if (request.getAttribute("memberId")!= null) memberId = (String) request.getAttribute("memberId");

		String reportNo = ""; 
		if (request.getAttribute("reportNo")!= null) reportNo = (String) request.getAttribute("reportNo");
		
		String fileName = "";		
			 if (reportNo.trim().equals("1")) fileName = "jsp/repadmin/auth_appl01.jasper";
		else if (reportNo.trim().equals("2")) fileName = "jsp/repadmin/auth_appl02.jasper";
		else if (reportNo.trim().equals("3")) fileName = "jsp/repadmin/auth_appl03.jasper";
						
      	File reportFile = new File(application.getRealPath(fileName));
		if (!reportFile.exists())	throw new JRRuntimeException("File not found. The report design must be compiled first.");

		Map parm = new HashMap();
        parm.put("prmapptype", appType);
		parm.put("prmmemberid", memberId);
		
		byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parm, conn);
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();				

    } catch (Exception e) {
	    throw new Exception(e.getMessage());
    } finally {
		try {
           	if (conn != null)  conn.close();
		} catch (SQLException e) {
    	   	throw new Exception(e.getMessage());
		}
	}
%>