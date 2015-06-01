/*
 * Created on 11-10-2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.employee.data;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dtac.employee.form.CalendarForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBCalendar {

	DBConnect agent 	= new DBConnect();
	Connection conn 	= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public List GetCalendarList(String year, String month, String days, String salTypeCode)	
	throws Exception {	//10-10-2011
		List calendars 	= new ArrayList();
		String weekNo		= "", workDate		= "";
		String dayNo		= "", dayName		= "";
		String dayTypeCode	= "", dayTypeName 	= "";
		String dayRemark	= "", salTypeName	= "";
		String timeStart	= "", timeStop		= "", timeLate	= "";
		try {			
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.workdate, a.saltypecode, a.weekno, a.dayno, a.daytype, a.remark, " +
			"a.timestart, a.timestop, a.timelate, b.daythfullname, c.saltypename " +
			"FROM hrmcalendar a " +
			"JOIN hrmday b ON (a.dayno = b.dayno) " +
			"JOIN saltype c ON (a.saltypecode = c.saltypecode) " +
			
			"WHERE " +
				"date(a.workdate) >= '"+year.trim()+"-"+month.trim()+"-01' AND " +
				"date(a.workdate) <= '"+year.trim()+"-"+month.trim()+"-"+days.trim()+"' ";
			
			if (!salTypeCode.equals("")) sqlStmt = sqlStmt + "AND a.saltypecode = '"+salTypeCode+"' ";

			sqlStmt = sqlStmt + "ORDER BY a.saltypecode, a.workdate ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				workDate	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("workdate"));
				weekNo		= String.valueOf(rs.getString("weekno"));
				dayNo		= rs.getString("dayno");
				dayName 	= rs.getString("daythfullname");
				dayTypeCode = rs.getString("daytype");
				salTypeCode	= rs.getString("saltypecode");
				salTypeName	= rs.getString("saltypename");
				
				timeStart = rs.getString("timestart");
				timeStop  = rs.getString("timestop");
				timeLate  = rs.getString("timelate");
				
				if (rs.getString("remark")!=null) dayRemark	= rs.getString("remark"); else dayRemark = "";	
				if (dayTypeCode.equals("1")) dayTypeName = "Work"; else dayTypeName = "Holiday";
				
				calendars.add(new CalendarForm(weekNo, dayNo, dayName, workDate, dayTypeCode, dayTypeName,
					salTypeCode, salTypeName, timeStart, timeStop, timeLate, dayRemark));
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
		return calendars;
	}
	public List GetCalendar(String workDate, String salTypeCode) throws Exception {	//01-11-2011
		List calendars 	= new ArrayList();
		String weekNo		= "";
		String dayNo		= "", dayName		= "";
		String dayTypeCode	= "", dayTypeName 	= "";
		String dayRemark	= "", salTypeName	= "";
		String timeStart	= "", timeStop		= "", timeLate	= "";		
		try {			
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.workdate, a.saltypecode, a.weekno, a.dayno, a.daytype, a.remark, " +
			"a.timestart, a.timestop, a.timelate, b.daythfullname, c.saltypename " +
			"FROM hrmcalendar a " +
			"JOIN hrmday b ON (a.dayno = b.dayno) " +
			"JOIN saltype c ON (a.saltypecode = c.saltypecode) " +
			
			"WHERE a.workdate = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate.trim(),'-')+"' " +
			"AND a.saltypecode = '"+salTypeCode+"' ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				workDate	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("workdate"));
				weekNo		= String.valueOf(rs.getString("weekno"));
				dayNo		= rs.getString("dayno");
				dayName 	= rs.getString("daythfullname");
				dayTypeCode = rs.getString("daytype");
				salTypeCode	= rs.getString("saltypecode");
				salTypeName	= rs.getString("saltypename");
								
				timeStart = rs.getString("timestart");
				timeStop  = rs.getString("timestop");
				timeLate  = rs.getString("timelate");
				
				if (rs.getString("remark")!=null) dayRemark	= rs.getString("remark"); else dayRemark = "";	
				if (dayTypeCode.equals("1")) dayTypeName = "Work"; else dayTypeName = "Holiday";
				
				calendars.add(new CalendarForm(weekNo, dayNo, dayName, workDate, dayTypeCode, dayTypeName,
					salTypeCode, salTypeName, timeStart, timeStop, timeLate, dayRemark));
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
		return calendars;
	}
	public boolean CheckCalendar(String workDate, String salTypeCode) throws Exception { //27-11-2011
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT status FROM hrmcalendar WHERE " +
			"workdate = '"+dateUtil.CnvToYYYYMMDD(workDate.trim(),'-')+"' AND " +
			"saltypecode = '"+salTypeCode+"' ";
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				result = true;
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
		return result;
	}	
	public void AddCalendar(String workDate, int dayNo)	throws Exception {	//12-10-2011
		try {			
			conn = agent.getConnectMYSql();
			
			char dayType = '1';
			if ((dayNo == 1) || (dayNo == 0)) dayType	= '0';
				
			String sqlStmt = "INSERT IGNORE INTO hrmcalendar (workdate, saltypecode, dayno, daytype, " +
			"timestart, timelate, timestop) " +
				"(SELECT '"+dateUtil.CnvToYYYYMMDD(workDate.trim(),'-')+"', saltypecode, " +
				"'"+String.valueOf(dayNo).trim()+"', '"+dayType+"', timestart, timelate, timestop " +
				"FROM saltype) ";

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
	public void UpdateCalendar(String salTypeCode, String workDate, String dayTypeCode, 
		String dayRemark, String timeStart, String timeStop)	
	throws Exception {	//12-10-2011
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE hrmcalendar SET " +
				"daytype = '"+dayTypeCode.trim()+"', " +
				"timestart = '"+timeStart.trim()+"', " +
				"timestop  = '"+timeStop.trim()+"', " +
				"remark = '"+dayRemark.trim()+"' " +
			"WHERE workdate = '"+dateUtil.CnvToYYYYMMDDEngYear(workDate.trim(), '-')+"' AND " +
			"saltypecode = '"+salTypeCode+"' ";
			
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
}