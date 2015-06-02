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
 	
	 	
 		String fileName = "";
  
	 		  fileName = "jsp/repinventory/Lockmat.jasper"; 
 	 
	 		 
      	File reportFile = new File(application.getRealPath(fileName));
	    if (!reportFile.exists()) throw new JRRuntimeException("File not found. The report design must be compiled first.");
		
		
		Map parm = new HashMap();
        
        
   		
 
    	
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