package com.dtac.employee.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dtac.employee.form.WorkTimeForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;

public class DBWorkTime {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public List GetWorkTimeList(String empID, String empNameT, String empLastNameT, String empTypeCode,
		String empDeptCode, String workDate) 
	throws Exception {	//01-06-2012
		List wtList = new ArrayList();		
		String empNameE	= "", empLastNameE	= "", empDeptName = "", empTypeName = ""; 
		String r = "", w = "", b = "", v = "", c = "", a = "", l = "", x = "";
		String xw = "",  xb = "", xv = "", xx = "";
		String otN1 = "", otN1_5 = "", otN2 = "", otN3 = "";
		String startTime = "" ,stopTime = "", amlate = "", punchCard = "";
		String workTimeStart = "", workTimeStop = "", workStatus = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.empid, a.typecode, a.empnamet, a.emplastnamet, a.punchcard, " +
			"a.deptcode, b.typename, c.thdesc AS deptname, d.timestart, d.timestop, wt.workdate, " +
			"wt.w, wt.r, wt.b, wt.v, wt.c, wt.a, wt.l, wt.x, wt.xw, wt.xb, wt.xv, wt.xx, " +
			"wt.otn1, wt.otn1_5, wt.otn2, wt.otn3, wt.status, " +
			"TIME_FORMAT(wt.starttime, '%H.%i') AS starttime, TIME_FORMAT(wt.stoptime, '%H.%i') AS stoptime, " +
			"TIME_FORMAT(wt.amlate, '%H.%i') AS amlate " +
			"FROM emptype b " +
			"JOIN employee a ON (a.empid = '"+empID.trim()+"' AND a.typecode = b.typecode) " +			
			"LEFT JOIN mastertabledt c ON (c.grpcode = 'dept' AND a.deptcode = c.typecode) " +
			"JOIN hrmworktime wt on (wt.workdate = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate,'-')+"' AND " +
				"a.empid = wt.empid) "+
			"JOIN hrmcalendar d ON (wt.workdate = d.workdate AND b.typeemp = d.saltypecode) " +
			"WHERE  " ; 
						
			if (!empTypeCode.equals("")) sqlStmt = sqlStmt + "a.typecode = '"+empTypeCode.trim()+"' AND ";
			if (!empDeptCode.equals("")) sqlStmt = sqlStmt + "a.deptcode = '"+empDeptCode.trim()+"' AND ";
			
			sqlStmt = sqlStmt + " a.empid <> '00000' ORDER BY a.empid ";			

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				workTimeStart = rs.getString("timestart");
				workTimeStop  = rs.getString("timestop");
				empID		= rs.getString("empid");
				empTypeCode = rs.getString("typecode");
				empTypeName	= rs.getString("typename");
				empDeptCode	= rs.getString("deptcode");
				empDeptName	= rs.getString("deptname");
				punchCard	= rs.getString("punchcard");
				
				if (rs.getString("empnamet") != null) empNameT = rs.getString("empnamet"); else empNameT = "";
				if (rs.getString("emplastnamet") != null) empLastNameT = rs.getString("emplastnamet"); 
				else empLastNameT = "";
				
				if (rs.getString("workDate") != null)
					workDate = dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("workDate"));
				else workDate = "";
				
				if (rs.getString("amlate") != null) 	amlate = rs.getString("amlate"); else amlate = "";
				if (rs.getString("starttime") != null) startTime = rs.getString("starttime"); else startTime = "";				
				if (rs.getString("stoptime") != null)	stopTime = rs.getString("stoptime"); else stopTime = "";
				
				r	= rs.getString("r");
				w	= rs.getString("w");
				b	= rs.getString("b");
				v	= rs.getString("v");
				c	= rs.getString("c");
				a	= rs.getString("a");
				l	= rs.getString("l");
				x	= rs.getString("x");				
				xw	= rs.getString("xw");
				xb	= rs.getString("xb");
				xv	= rs.getString("xv");
				xx	= rs.getString("xx");
				
				otN1	= rs.getString("otn1");
				otN1_5	= rs.getString("otn1_5");
				otN2	= rs.getString("otn2");
				otN3	= rs.getString("otn3");
				workStatus = rs.getString("status");
				
				wtList.add(new WorkTimeForm(workDate, workTimeStart, workTimeStop,
					empID, empNameT, empLastNameT, empNameE, empLastNameE,
					empTypeCode, empTypeName, empDeptCode, empDeptName, startTime, stopTime, amlate, punchCard,
					r, w, b, v, c, a, l, x, xw, xb, xv, xx, otN1, otN1_5, otN2, otN3, workStatus));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) 	  rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return wtList;
	}
	public List GetResendWorkTimeList(String salTypeCode, String workDate) throws Exception {	//12-07-2012
		List wtList = new ArrayList();		
		String empID = "", empNameT = "", empLastNameT = ""; 
		String empNameE	= "", empLastNameE	= "", empDeptCode = "", empDeptName = "";
		String empTypeCode = "", empTypeName = ""; 
		String r = "", w = "", b = "", v = "", c = "", a = "", l = "", x = "";
		String xw = "",  xb = "", xv = "", xx = "";
		String otN1 = "", otN1_5 = "", otN2 = "", otN3 = "";
		String startTime = "" ,stopTime = "", amlate = "", punchCard = "";
		String workTimeStart = "", workTimeStop = "", workStatus = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.empid, a.typecode, a.empnamet, a.emplastnamet, a.punchcard, " +
			"a.deptcode, b.typename, c.thdesc AS deptname, d.timestart, d.timestop, wt.workdate, " +
			"wt.w, wt.r, wt.b, wt.v, wt.c, wt.a, wt.l, wt.x, wt.xw, wt.xb, wt.xv, wt.xx, " +
			"wt.otn1, wt.otn1_5, wt.otn2, wt.otn3, wt.status, " +
			"TIME_FORMAT(wt.starttime, '%H.%i') AS starttime, TIME_FORMAT(wt.stoptime, '%H.%i') AS stoptime, " +
			"TIME_FORMAT(wt.amlate, '%H.%i') AS amlate " +
			"FROM emptype b " +
			"JOIN employee a ON (a.typecode = b.typecode) " +			
			"LEFT JOIN mastertabledt c ON (c.grpcode = 'dept' AND a.deptcode = c.typecode) " +
			"JOIN hrmworktime wt on (wt.workdate = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate,'-')+"' AND " +
				"a.empid = wt.empid) "+
			"JOIN hrmcalendar d ON (wt.workdate = d.workdate AND b.typeemp = d.saltypecode) " +
			"WHERE b.typeemp = '"+salTypeCode+"' AND d.sendtimedate < wt.lastupdate AND " ; 
									
			sqlStmt = sqlStmt + " a.empid <> '00000' ORDER BY a.empid ";			

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				workTimeStart = rs.getString("timestart");
				workTimeStop  = rs.getString("timestop");
				empID		= rs.getString("empid");
				empTypeCode = rs.getString("typecode");
				empTypeName	= rs.getString("typename");
				empDeptCode	= rs.getString("deptcode");
				empDeptName	= rs.getString("deptname");
				punchCard	= rs.getString("punchcard");
				
				if (rs.getString("empnamet") != null) empNameT = rs.getString("empnamet"); else empNameT = "";
				if (rs.getString("emplastnamet") != null) empLastNameT = rs.getString("emplastnamet"); 
				else empLastNameT = "";
				
				if (rs.getString("workDate") != null)
					workDate = dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("workDate"));
				else workDate = "";
				
				if (rs.getString("amlate") != null) 	amlate = rs.getString("amlate"); else amlate = "";
				if (rs.getString("starttime") != null) startTime = rs.getString("starttime"); else startTime = "";				
				if (rs.getString("stoptime") != null)	stopTime = rs.getString("stoptime"); else stopTime = "";
				
				r	= rs.getString("r");
				w	= rs.getString("w");
				b	= rs.getString("b");
				v	= rs.getString("v");
				c	= rs.getString("c");
				a	= rs.getString("a");
				l	= rs.getString("l");
				x	= rs.getString("x");				
				xw	= rs.getString("xw");
				xb	= rs.getString("xb");
				xv	= rs.getString("xv");
				xx	= rs.getString("xx");
				
				otN1	= rs.getString("otn1");
				otN1_5	= rs.getString("otn1_5");
				otN2	= rs.getString("otn2");
				otN3	= rs.getString("otn3");
				workStatus = rs.getString("status");
				
				wtList.add(new WorkTimeForm(workDate, workTimeStart, workTimeStop,
					empID, empNameT, empLastNameT, empNameE, empLastNameE,
					empTypeCode, empTypeName, empDeptCode, empDeptName, startTime, stopTime, amlate, punchCard,
					r, w, b, v, c, a, l, x, xw, xb, xv, xx, otN1, otN1_5, otN2, otN3, workStatus));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) 	  rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return wtList;
	}
	public void InsertWorkTime(String workDate, String empID) throws Exception { //12-12-2011
		
		try {
			conn = agent.getConnectMYSql();
						
			String sqlStmt = "INSERT INTO hrmworktime (empid, workdate) " +
			"VALUES ('"+empID.trim()+"', '"+dateUtil.CnvToYYYYMMDDEngYear(workDate,'-')+"' )";
	
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}	 
	}
	public void updateWorkTime(String workDate, String empID, String startTime, String stopTime, String amlate, 
		String r, String w, String b, String v, String c, String a, String l, String x,
		String xw, String xb, String xv, String xx,
		String otN1, String otN1_5, String otN2, String otN3, String workStatus)
	throws Exception { //16-06-2012	    
		amlate = amlate.replace('.', ':') +":00";
	    try {
	    	conn = agent.getConnectMYSql();	    
	    	
	    	String sqlStmt = "UPDATE hrmworktime SET r = "+Float.parseFloat(r)+", w = "+Float.parseFloat(w)+", " +
	    	"b = "+Float.parseFloat(b)+", v = "+Float.parseFloat(v)+", c = "+Float.parseFloat(c)+", " +
	    	"a = "+Float.parseFloat(a)+", l = "+Float.parseFloat(l)+", x = "+Float.parseFloat(x)+", " +
	    	"xw= "+Float.parseFloat(xw)+",xb= "+Float.parseFloat(xb)+",xv= "+Float.parseFloat(xv)+", " +
	    	"xx= "+Float.parseFloat(xx)+",amlate = '"+amlate+"', " +
			"otn1 = "+Float.parseFloat(otN1)+", otn1_5 = "+Float.parseFloat(otN1_5)+", " +
			"otn2 = "+Float.parseFloat(otN2)+", otn3 = "+Float.parseFloat(otN3)+", status = '"+workStatus+"', " +
			"lastupdate = '"+dateUtil.curDateTime()+"' ";
	    	
			if (!startTime.equals(":00")) sqlStmt = sqlStmt + 
			",starttime = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate, '-')+" " +startTime+"' ";

			if (!stopTime.equals(":00")) sqlStmt = sqlStmt + 
			",stoptime = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate, '-')+" " +stopTime+"' ";
	    	
			sqlStmt = sqlStmt + "WHERE empid = '"+empID+"' AND " +
			"workdate = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate, '-')+"' ";
	
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//===========================20-08-2012=======================================
			sqlStmt = "UPDATE hrmworktime SET    amr = '00:00:00', amw = '00:00:00', amb = '00:00:00', " +
			"amv = '00:00:00', amc = '00:00:00', ama = '00:00:00', amx = '00:00:00', " +
	    	"amxw= '00:00:00', amxb= '00:00:00', amxv= '00:00:00', amxx= '00:00:00', " +
			"amotn1 = '00:00:00', amotn1_5 = '00:00:00', amotn2 = '00:00:00', amotn3 = '00:00:00' " +		    	
			"WHERE empid = '"+empID+"' AND workdate = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate, '-')+"' ";
		
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==================================================================
			sqlStmt = "UPDATE hrmworktime SET " +
			"amr = ADDTIME(amr, concat(floor(r),':',mod(r*60,60))), " +
			"amw = ADDTIME(amw, concat(floor(w),':',mod(w*60,60))), " +
			"amb = ADDTIME(amb, concat(floor(b),':',mod(b*60,60))), " +
			"amv = ADDTIME(amv, concat(floor(v),':',mod(v*60,60))), " +
			"amc = ADDTIME(amc, concat(floor(c),':',mod(c*60,60))), " +
			"ama = ADDTIME(ama, concat(floor(a),':',mod(a*60,60))), " +	
			"amx = ADDTIME(amx, concat(floor(x),':',mod(x*60,60))), " +
			"amxw = ADDTIME(amxw, concat(floor(xw),':',mod(xw*60,60))), " +
			"amxb = ADDTIME(amxb, concat(floor(xb),':',mod(xb*60,60))), " +
			"amxv = ADDTIME(amxv, concat(floor(xv),':',mod(xv*60,60))), " +
			"amxx = ADDTIME(amxx, concat(floor(xx),':',mod(xx*60,60))), " +
			
		    "amotn1   = ADDTIME(amotn1,   concat(floor(otn1),':',mod(otn1*60,60))), " +
			"amotn1_5 = ADDTIME(amotn1_5, concat(floor(otn1_5),':',mod(otn1_5*60,60))), " +
			"amotn2   = ADDTIME(amotn2,   concat(floor(otn2),':',mod(otn2*60,60))), " +
			"amotn3   = ADDTIME(amotn3,   concat(floor(otn3),':',mod(otn3*60,60))) " +

			"WHERE empid = '"+empID+"' AND workdate = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate, '-')+"' ";
		
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);		
			pStmt.close();
			conn.close();
    	} catch (Exception e) {
   			throw new Exception(e.getMessage());
    	} finally {
			try {
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
    	}	 	
    }    
	public boolean Checkworktime(String empID, String workDate) throws Exception {	//20-11-2011
		boolean resultFlag	= false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM hrmworktime ht " +
			"INNER JOIN Employee e on (e.empID=ht.empID)" +
			"WHERE e.empid = '"+empID+"' AND ht.workDate = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate,'-')+"' " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				resultFlag = true;
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) 	  rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return resultFlag;
	}
	public void AddAutoWorkTime(String checkTime, String offiCode)	throws Exception { //02-09-2012	    
	    try {
	    	conn = agent.getConnectMYSql();	    
	    	
	    	String //========monthly========================================================================
			sqlStmt = "INSERT IGNORE INTO hrmworktime (empid, workdate, a, ama) " +
			"(SELECT a.empid, '"+checkTime+"', 8, '08:00:00' FROM employee a, emptype b, hrmcalendar c " +
			
			"WHERE a.typecode = b.typecode AND c.workdate = '"+checkTime.trim()+"' AND " +
			"a.officode = '"+offiCode+"' AND b.typeemp = c.saltypecode AND " +
			"c.saltypecode='1' AND c.daytype = '1' AND a.status = 'AC' AND a.empid NOT IN ('00000','11173') AND " +
			" CONCAT(c.workdate,a.empid) NOT IN (SELECT CONCAT(workdate,empid) FROM hrmworktime " +
			"WHERE workdate = '"+checkTime.trim()+"') AND c.workdate >= a.saleffdate AND " +
			"(c.workdate <= a.salenddate OR a.salenddate IS NULL))";
	    	
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	

			//=========dairy===========================================================================
			sqlStmt = "INSERT IGNORE INTO hrmworktime (empid, workdate, r, amr) " +
			"(SELECT empid, '"+checkTime+"', 0, '00:00:00' FROM employee a, emptype b, hrmcalendar c " +
			
			"WHERE a.typecode = b.typecode AND c.workdate = '"+checkTime.trim()+"' AND " +
			"a.officode = '"+offiCode+"' AND b.typeemp = c.saltypecode AND " +
			"c.saltypecode='0' AND c.dayno = '1' AND a.status = 'AC' AND a.empid NOT IN ('00000','11173') AND " +
			" CONCAT(c.workdate,a.empid) NOT IN (SELECT CONCAT(workdate,empid) FROM hrmworktime " +
			"WHERE workdate = '"+checkTime.trim()+"') AND c.workdate >= a.saleffdate AND " +
			"(c.workdate <= a.salenddate OR a.salenddate IS NULL))";
		
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			
			//========both monthly and dairy=====================
			/*sqlStmt = "INSERT IGNORE INTO hrmworktime (empid, workdate, r, amr) " +
			"(SELECT empid, '"+checkTime+"', 8, '08:00:00' FROM employee a, emptype b, hrmcalendar c " +
			
			"WHERE a.typecode = b.typecode AND c.workdate = '"+checkTime.trim()+"' AND b.typeemp = c.saltypecode AND " +
			"c.daytype = '0' AND a.status = 'AC' AND a.empid NOT IN ('00000','11173') AND " +
			"CONCAT(c.workdate,a.empid) NOT IN (SELECT CONCAT(workdate,empid) FROM hrmworktime " +
			"WHERE workdate = '"+checkTime.trim()+"') AND c.workdate >= a.saleffdate AND " +
			"(c.workdate <= a.salenddate OR a.salenddate IS NULL))";
		
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	*/
			//===============================================
			sqlStmt = "UPDATE hrmworktime a SET a.amlate = '00:00:00' " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.amlate IS NULL ";
							
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			pStmt.close();
			conn.close();
    	} catch (Exception e) {
   			throw new Exception(e.getMessage());
    	} finally {
			try {
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
    	}	 	
    }    

}