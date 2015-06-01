<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.dtac.utils.DBConnect" %>

<%
        DBConnect agent = new DBConnect();
  	  	Connection con = agent.getConnectMYSql();
        
    String userid = (String) request.getAttribute("userid");
       
      File reportFile = null;
        	
       reportFile = new File(application.getRealPath("jsp/repinventory/BookBarcode.jasper"));


      
 
    if (!reportFile.exists())
		throw new JRRuntimeException("File not found. The report design must be compiled first.");

Map parm = new HashMap();
       
	
     parm.put("userid", userid);
        
      

	byte[] bytes = 
		JasperRunManager.runReportToPdf(
			reportFile.getPath(), 
			parm, 
			con
			);
	
	response.setContentType("application/pdf");
	response.setContentLength(bytes.length);
	ServletOutputStream ouputStream = response.getOutputStream();
	ouputStream.write(bytes, 0, bytes.length);
	ouputStream.flush();
	ouputStream.close();
%>