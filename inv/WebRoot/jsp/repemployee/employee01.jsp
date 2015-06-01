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
		
		String empTypeCode = ""; 
		if (request.getAttribute("empTypeCode") != null) empTypeCode = (String) request.getAttribute("empTypeCode");

		String groupName = ""; 
		if (request.getAttribute("groupName") != null) groupName = (String) request.getAttribute("groupName");
		
		String reportNo = ""; 
		if (request.getAttribute("reportNo") != null) reportNo = (String) request.getAttribute("reportNo");
		
		String fileName = "";
	 		 if (reportNo.equals("1")) fileName = "jsp/repemployee/employee01.jasper";
		else if (reportNo.equals("2")) fileName = "jsp/repemployee/employee02.jasper";
		else if (reportNo.equals("3")) fileName = "jsp/repemployee/employee03.jasper";
		else if (reportNo.equals("4")) fileName = "jsp/repemployee/employee04.jasper";
	 		 
      	File reportFile = new File(application.getRealPath(fileName));
	    if (!reportFile.exists()) throw new JRRuntimeException("File not found. The report design must be compiled first.");

		Map parm = new HashMap();
        parm.put("prmtypecode", empTypeCode);
        parm.put("prmgroupname", groupName.trim()+"%");
        
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