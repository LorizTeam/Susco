<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="net.sf.jasperreports.engine.export.JRXlsExporter" %> 
<%@ page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter" %> 
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

		String docDate = ""; 
		if (request.getAttribute("docDate") != null) docDate = (String) request.getAttribute("docDate");
 	
		String matTypeCode = ""; 
		if (request.getAttribute("matTypeCode") != null) matTypeCode = (String) request.getAttribute("matTypeCode");
 
		String matGrpCode = ""; 
		if (request.getAttribute("matGrpCode") != null) matGrpCode = (String) request.getAttribute("matGrpCode");
		
		String matStuffCode = ""; 
		if (request.getAttribute("matStuffCode") != null) matStuffCode = (String) request.getAttribute("matStuffCode");
 		
 		String loginId = ""; 
		if (request.getAttribute("loginId") != null) loginId = (String) request.getAttribute("loginId");
 		
 		String stockOnHand = ""; 
		if (request.getAttribute("stockOnHand") != null) stockOnHand = (String) request.getAttribute("stockOnHand");
 		
 		String fileName = "";
 		if(stockOnHand.equals("01")){
	 		  fileName = "jsp/repinventory/stockonhandexcel_10.jasper";
 		}else{
 			  fileName = "jsp/repinventory/stockonhandtransectionexcel_10.jasper";
 		}
		
      	File reportFile = new File(application.getRealPath(fileName));
	    if (!reportFile.exists())	throw new JRRuntimeException("File not found. The report design must be compiled first.");

		Map parm = new HashMap();
        parm.put("prmdocdate", docDate);
        parm.put("prmmattypecode", matTypeCode);
        parm.put("prmmatgrpcode", matGrpCode);
        parm.put("prmmatstuffcode", matStuffCode);
    	parm.put("prmlogin", loginId);  
        
		byte[] bytes = null;
		JasperPrint jasperPrint = JasperManager.fillReport( reportFile.getPath(), parm, conn );
		JRXlsExporter exporter = new JRXlsExporter();
		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
		exporter.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
	  	exporter.setParameter( JRExporterParameter.OUTPUT_STREAM, xlsReport );
		exporter.setParameter( JRExporterParameter.OUTPUT_FILE_NAME, "sample.xls" );
		exporter.exportReport();
   		bytes = xlsReport.toByteArray();
		response.setContentType("application/vnd.ms-excel");
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