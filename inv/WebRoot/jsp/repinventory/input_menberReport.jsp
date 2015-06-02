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
		
		String studentName = ""; 
		if (request.getAttribute("studentName") != null) studentName = (String) request.getAttribute("studentName");
		
		String docNo = ""; 
		if (request.getAttribute("docNo") != null) docNo = (String) request.getAttribute("docNo");
		
		String docyear = ""; 
		if (request.getAttribute("docyear") != null) docyear = (String) request.getAttribute("docyear");

		String docmonth = ""; 
		if (request.getAttribute("docmonth") != null) docmonth = (String) request.getAttribute("docmonth");
		
		String fileName = "";
		if (studentName.equals("")){
		 	fileName = "jsp/repinventory/fatima.jasper";
		}else{
		 	fileName = "jsp/repinventory/fatimastudent.jasper";
		}
		
	 		 
      	File reportFile = new File(application.getRealPath(fileName));
	    if (!reportFile.exists()) throw new JRRuntimeException("File not found. The report design must be compiled first.");

		Map parm = new HashMap();
		parm.put("prmdocno", docNo);
        parm.put("prmdocyear", docyear);  
        parm.put("prmdocmonth", docmonth);

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