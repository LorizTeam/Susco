/*
 * Created on 11-07-2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.inventory.data;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dtac.inventory.form.MatDocPeriodForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMatDocPeriod {

	DBConnect agent 	= new DBConnect();
	Connection conn 	= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public List GetMatDocPeriodList(String year, String month) throws Exception {	//03-06-2012
		List periods 	= new ArrayList();		
		String startDate= "", endDate = "", currFlag = "", status = ""; 
		try {			
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.year, a.month, a.startdate, a.enddate, a.status, a.currflag " +
			"FROM mmdocperiod a WHERE " ;
					
			if (!year.equals("")) 	sqlStmt = sqlStmt + "a.year = '"+year+"' AND ";			
			if (!month.equals(""))	sqlStmt = sqlStmt + "a.month = '"+month+"' AND ";			
			
			sqlStmt = sqlStmt + "a.year <> '' ORDER BY a.year, a.month ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				year 		= rs.getString("year");
				month       = rs.getString("month");
				currFlag	= rs.getString("currflag");
				status		= rs.getString("status");
								
				if (rs.getString("startdate") != null) 
					startDate = dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("startdate"));
				else startDate = "";
				
				if (rs.getString("enddate") != null) 
					endDate = dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("enddate"));
				else endDate = "";
				
				periods.add(new MatDocPeriodForm(year, month, startDate, endDate, currFlag, status));
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
	public String CheckMatDocPeriodStatus(String year, String month) throws Exception {	//20-05-2012
		String status	= "";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT status FROM mmdocperiod WHERE year = '"+year+"' AND " +
			"month = '"+month+"'  ";
		
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
	public List GetCurrMatDocPeriod() throws Exception { //01-09-2012
		List periods 	= new ArrayList();
		String startDate= null, endDate = null;
		String year		= "", month  	= "";
		String status	= "";
		String currFlag	= "";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.year, a.month, a.startdate, a.enddate, a.status, a.currflag " +
			"FROM mmdocperiod a WHERE a.currflag = 'Y' ";
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				year 		= rs.getString("year");				
				month  		= rs.getString("month");				
				status  	= rs.getString("status");
				currFlag	= rs.getString("currflag");
				
				periods.add(new MatDocPeriodForm(year, month, startDate, endDate, currFlag, status));
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
	public void AddAutoMatDocPeriod(String year) throws Exception {	//20-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmdocperiod (year, month, status, currflag) " +
			"(SELECT a.year, b.monthno, 'AC', 'N' FROM hrmyear a, hrmmonth b " +
			"WHERE a.year = '"+year+"')";
			
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
	public void UpdatePeriod(String year, String month, String periodNo, String salTypeCode,
		String startDate, String endDate, String status, String loginId) 
	throws Exception {	//20-10-2011
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE hrmperiod SET status = '"+status+"', " +
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
	public void openMatDocPeriod(String year, String month) 
	throws Exception { //10-10-2012		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmdocperiod SET status = 'AC', startdate = '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"'"+
			"WHERE year = '"+year+"' AND month = '"+month+"'";
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
	public void closeMatDocPeriod(String year, String month) 
	throws Exception { //10-10-2012		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmdocperiod SET status = 'CL', " +
			"enddate = '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"' "+
			"WHERE year = '"+year+"' AND month = '"+month+"'";
			
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