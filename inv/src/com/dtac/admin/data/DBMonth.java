/*
 * Created on 10-10-2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.admin.data;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dtac.admin.form.MonthForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMonth {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public String GetMonthENFullName(String month)	throws Exception {	//16-11-2009
		String monthENFullName	= "";
		try {
			conn = agent.getConnectMYSql();
			
			if (month.length() == 1) month = "0" + month;
			
			String sqlStmt = "SELECT a.monthenfullname FROM hrmmonth a WHERE a.monthno = '"+month+"' ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				monthENFullName	= rs.getString("monthenfullname");
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
		return monthENFullName;
	}
	public String GetMonthTHFullName(String month)	throws Exception {	//16-11-2009		
		String monthTHFullName	= "";
		try {
			conn = agent.getConnectMYSql();

			if (month.length() == 1) month = "0" + month;

			String sqlStmt = "SELECT a.monththfullname FROM hrmmonth a WHERE a.monthno = '"+month+"' ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				monthTHFullName	= rs.getString("monththfullname");
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
		return monthTHFullName;
	}
	public List GetMonthList()	throws Exception {	//23-10-2009
		List months = new ArrayList();		
		String year			= "";
		String engYear		= "";
		String month		= "";
		String monthTHAbbvName	= "";
		String monthTHFullName	= "";
		String monthENAbbvName	= "";
		String monthENFullName	= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.monthno, a.monthenabbvname, a.monththabbvname, a.monththfullname, " +
			"a.monthenfullname FROM hrmmonth a ORDER BY a.monthno ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				month			= rs.getString("monthno");
				monthTHAbbvName	= rs.getString("monththabbvname");
				monthTHFullName	= rs.getString("monththfullname");
				monthENAbbvName	= rs.getString("monthenabbvname");
				monthENFullName	= rs.getString("monthenfullname");
				
				months.add(new MonthForm(year, engYear, month, 
					monthTHAbbvName, monthTHFullName, monthENAbbvName, monthENFullName));
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
		return months;
	}
	public boolean CheckMonthChk(String year, String month) throws Exception {	//12-10-2011
		boolean resultFlag 	= false;		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM hrmmonthchk " +
			"WHERE year = '"+year+"' AND month = '"+month+"'";

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
				if (rs != null) rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return resultFlag;
	}
	public void AddMonthChk(String year, String month) throws Exception {	//12-10-2011
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO hrmmonthchk (year, month) VALUE " +
			"('"+year+"','"+month+"') ";

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