<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.*" %>
<%@ page import="com.dtac.utils.DBConnect" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.dtac.utils.DateUtil" %>
<%
	Connection conn = null;
    try {
		DBConnect agent = new DBConnect();
  	  	conn = agent.getConnectMYSql();
 	
	String frdate = ""; 
		if (request.getAttribute("frdate") != null) frdate = (String) request.getAttribute("frdate");
 	
		String todate = ""; 
		if (request.getAttribute("todate") != null) todate = (String) request.getAttribute("todate");
 
 		DateUtil dt  = new DateUtil();
 		frdate= dt.CnvToYYYYMMDD(frdate);
 		
 		todate= dt.CnvToYYYYMMDD(todate);
    String    doctype="";
      
		if (request.getAttribute("doctype") != null) doctype = (String) request.getAttribute("doctype");
 		
 		
     String    userid="";
      
		if (request.getAttribute("userid") != null) userid = (String) request.getAttribute("userid");

 
 		String fileName = "";
  
	 		  fileName = "jsp/repinventory/repstocktran.jasper"; 
 	 
	 		 
      	File reportFile = new File(application.getRealPath(fileName));
	    if (!reportFile.exists()) throw new JRRuntimeException("File not found. The report design must be compiled first.");
		
		
		Map parm = new HashMap();
        
        parm.put("prmdoctype",doctype);
        parm.put("userid",userid);
        parm.put("frdate", frdate);
        parm.put("todate", todate);
   		
 
    	
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