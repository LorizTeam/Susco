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

import com.dtac.employee.form.PeriodForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBPeriod {

	DBConnect agent 	= new DBConnect();
	Connection conn 	= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public List GetPeriodList(String year, String month, String periodNo, String salTypeCode, String status)	
	throws Exception {	//21-08-2012
		List periods 	= new ArrayList();
		String startDate= "", salTypeName = "";
		String endDate 	= "", socialRate = "";
		String engYear	= "";
		String currFlag	= "";
		try {			
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.year, a.engyear, a.month, a.period, a.saltypecode, a.socialrate, " +
			"a.startdate, a.enddate, a.status, a.currflag, b.saltypename " +
			"FROM hrmperiod a " +
			"JOIN saltype b ON (a.saltypecode = b.saltypecode) WHERE " ;
			
			if (!year.equals("")) 	sqlStmt = sqlStmt + "a.year = '"+year+"' AND ";			
			if (!month.equals(""))	sqlStmt = sqlStmt + "a.month = '"+month+"' AND ";			
			if (!periodNo.equals(""))	sqlStmt = sqlStmt + "a.period = '"+periodNo+"' AND ";
			if (!salTypeCode.equals(""))sqlStmt = sqlStmt + "a.saltypecode = '"+salTypeCode+"' AND ";
			if (!status.equals("")) sqlStmt = sqlStmt + "a.status = '"+status+"' AND ";

			sqlStmt = sqlStmt + "a.year <> '' ORDER BY a.year, a.month, a.period, a.saltypecode ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				year 		= rs.getString("year");
				engYear		= rs.getString("engyear");
				month  		= rs.getString("month");
				periodNo	= rs.getString("period");
				salTypeCode	= rs.getString("saltypecode");
				salTypeName	= rs.getString("saltypename");
				socialRate	= rs.getString("socialrate");
				status  	= rs.getString("status");
				currFlag	= rs.getString("currflag");
				if (rs.getString("startdate") != null) 
					startDate = dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("startdate"));
				else startDate = "";
				
				if (rs.getString("enddate") != null) 
					endDate = dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("enddate"));
				else endDate = "";
				
				periods.add(new PeriodForm(year, engYear, month, "", "", "", "", 
					periodNo, salTypeCode, salTypeName, socialRate, startDate, endDate, currFlag, status));
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
		return periods;
	}
	public String CheckPeriodStatus(String year, String month, String periodNo, String salTypeCode)	
	throws Exception {	//20-11-2011
		String status	= "";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT status FROM hrmperiod WHERE year = '"+year+"' AND " +
			"month = '"+month+"' AND period = '"+periodNo+"' AND saltypecode = '"+salTypeCode+"' ";
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				status  = rs.getString("status");
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
		return status;
	}
	public boolean CheckDayInPeriod(String year, String month, String periodNo, String salTypeCode, 
		String transDate)	
	throws Exception {	//20-12-2011
		boolean status	= false;
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT * FROM hrmperiod WHERE year = '"+year+"' AND " +
			"month = '"+month+"' AND period = '"+periodNo+"' AND saltypecode = '"+salTypeCode+"' AND " +
			"startdate <= '"+dateUtil.CnvToYYYYMMDDEngYear(transDate, '-')+"' AND " +
			"enddate >= '"+dateUtil.CnvToYYYYMMDDEngYear(transDate, '-')+"' ";
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				status  = true;
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
		return status;
	}
	public List GetCurrPeriod(String salTypeCode) throws Exception {	//21-08-2012
		List periods 	= new ArrayList();
		String startDate= null, endDate = null;
		String year		= "", month  	= "", periodNo = "";
		String status	= "", engYear	= "", socialRate = "";
		String currFlag	= "", salTypeName="";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.year, a.engyear, a.month, a.period, a.startdate, a.enddate, a.status, " +
			"a.currflag, a.saltypecode, a.socialrate, b.saltypename " +
			"FROM hrmperiod a " +
			"JOIN saltype b ON (a.saltypecode = b.saltypecode) " +
			"WHERE a.saltypecode = '"+salTypeCode+"' AND a.currflag = 'Y' ";
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				year 		= rs.getString("year");
				engYear		= rs.getString("engyear");
				month  		= rs.getString("month");
				periodNo	= rs.getString("period");
				salTypeCode	= rs.getString("saltypecode");
				salTypeName	= rs.getString("saltypename");
				socialRate	= rs.getString("socialrate");
				status  	= rs.getString("status");
				currFlag	= rs.getString("currflag");
				
				periods.add(new PeriodForm(year, engYear, month, "", "", "", "", 
					periodNo, salTypeCode, salTypeName, socialRate, startDate, endDate, currFlag, status));
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
		return periods;
	}
	public List GetPeriod(String salTypeCode, String transDate) throws Exception {	//21-08-2012
		List periods 	= new ArrayList();
		String startDate= null, endDate = null;
		String year		= "", month  	= "", periodNo = "";
		String status	= "", engYear	= "", socialRate = "";
		String currFlag	= "", salTypeName="";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.year, a.engyear, a.month, a.period, a.startdate, a.enddate, a.status, " +
			"a.currflag, a.socialrate, a.saltypecode, b.saltypename " +
			"FROM hrmperiod a " +
			"JOIN saltype b ON (a.saltypecode = b.saltypecode) " +
			"WHERE a.saltypecode = '"+salTypeCode+"' AND " +
			"startdate <= '"+dateUtil.CnvToYYYYMMDDEngYear(transDate, '-')+"' AND " +
			"enddate >= '"+dateUtil.CnvToYYYYMMDDEngYear(transDate, '-')+"' ";

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				year 		= rs.getString("year");
				engYear		= rs.getString("engyear");
				month  		= rs.getString("month");
				periodNo	= rs.getString("period");
				salTypeCode	= rs.getString("saltypecode");
				salTypeName	= rs.getString("saltypename");
				socialRate	= rs.getString("socialrate");
				status  	= rs.getString("status");
				currFlag	= rs.getString("currflag");
				
				periods.add(new PeriodForm(year, engYear, month, "", "", "", "", 
					periodNo, salTypeCode, salTypeName, socialRate, startDate, endDate, currFlag, status));
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
		return periods;
	}
	public void AddAutoPeriod(String year, String periodNo, String salTypeCode) throws Exception {	//20-10-2011
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO hrmperiod " +
			"(year, engyear, month, period, saltypecode, status, currflag) " +
			"(SELECT a.year, a.engyear, b.monthno, '"+periodNo+"', c.saltypecode, 'AC', 'N' " +
			"FROM hrmyear a, hrmmonth b, saltype c " +
			"WHERE a.year = '"+year+"' AND c.saltypecode = '"+salTypeCode+"')";
			
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
	public void UpdatePeriod(String year, String month, String periodNo, String salTypeCode, String socialRate,
		String startDate, String endDate, String status, String loginId) 
	throws Exception {	//20-08-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE hrmperiod SET socialrate = "+Float.parseFloat(socialRate)+", " +
			"status = '"+status+"', " +
			"startdate	= '"+dateUtil.CnvToYYYYMMDDEngYear(startDate, '-')+"', " +
			"enddate	= '"+dateUtil.CnvToYYYYMMDDEngYear(endDate, '-')+"' " +

			"WHERE year = '"+year+"' AND month = '"+month+"' AND period = '"+periodNo+"' AND " +
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
	public void SetCurrentPeriod(String year, String month, String periodNo, String salTypeCode) 
	throws Exception {	//18-10-2011
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE hrmperiod SET currflag = 'N' WHERE saltypecode = '"+salTypeCode+"' ";			
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			
			//======================================================
			sqlStmt = "UPDATE hrmperiod SET currflag = 'Y' " +
			"WHERE year = '"+year+"' AND month = '"+month+"' AND period = '"+periodNo+"' AND " +
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