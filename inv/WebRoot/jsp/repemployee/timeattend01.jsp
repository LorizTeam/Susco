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
		
		String empID = ""; 
		if (request.getAttribute("empID") != null) empID = (String) request.getAttribute("empID");

		String empTypeCode = ""; 
		if (request.getAttribute("empTypeCode") != null) empTypeCode = (String) request.getAttribute("empTypeCode");
		
		String empDeptCode = ""; 
		if (request.getAttribute("empDeptCode") != null) empDeptCode = (String) request.getAttribute("empDeptCode");

		String groupName = ""; 
		if (request.getAttribute("groupName") != null) groupName = (String) request.getAttribute("groupName");

		String fromDate = ""; 
		if (request.getAttribute("fromDate") != null) fromDate = (String) request.getAttribute("fromDate");
		
		String toDate = ""; 
		if (request.getAttribute("toDate") != null) toDate = (String) request.getAttribute("toDate");

		String year = ""; 
		if (request.getAttribute("year") != null) year = (String) request.getAttribute("year");
		
		String month = ""; 
		if (request.getAttribute("month") != null) month = (String) request.getAttribute("month");

		String strDate = ""; 
		if (request.getAttribute("strDate")!= null)	strDate = (String) request.getAttribute("strDate");

		String reportNo = ""; 
		if (request.getAttribute("reportNo") != null) reportNo = (String) request.getAttribute("reportNo");
		
		String fileName = "";
	 		 if (reportNo.equals("1")) fileName = "jsp/repemployee/time_01.jasper";
		else if (reportNo.equals("2")) fileName = "jsp/repemployee/time_02.jasper";
		else if (reportNo.equals("3")) fileName = "jsp/repemployee/time_03.jasper";
		else if (reportNo.equals("3_1")) fileName = "jsp/repemployee/time_03_1.jasper";
		else if (reportNo.equals("4")) fileName = "jsp/repemployee/time_04.jasper";
	 		 
      	File reportFile = new File(application.getRealPath(fileName));
	    if (!reportFile.exists()) throw new JRRuntimeException("File not found. The report design must be compiled first.");

		Map parm = new HashMap();
		parm.put("empid", empID.trim()+"%");
		parm.put("typecode", empTypeCode.trim()+"%");
		parm.put("deptcode", empDeptCode.trim()+"%");
		parm.put("prmgroupname", groupName.trim()+"%");
        parm.put("fromdate", fromDate);
        parm.put("todate", toDate);
        parm.put("prmyear", year);
        parm.put("prmmonth", month);
        parm.put("prmstrdate", strDate);  
        
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