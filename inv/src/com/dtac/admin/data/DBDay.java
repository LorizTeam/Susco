/*
 * Created on 14-10-2011
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

import com.dtac.admin.form.DayForm;
import com.dtac.utils.DBConnect;

 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBDay {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetDayList() throws Exception {	//28-9-2009
		List days 	= new ArrayList();

		String dayNo			= "";
		String dayTHAbbvName	= "";
		String dayTHFullName	= "";
		String dayENAbbvName	= "";
		String dayENFullName	= "";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.dayno, a.dayenabbvname, a.daythabbvname, a.daythfullname, " +
			"a.dayenfullname FROM hrmday a ORDER BY a.dayno ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				dayNo				= rs.getString("dayno");
				dayTHAbbvName	= rs.getString("daythabbvname");
				dayTHFullName	= rs.getString("daythfullname");
				dayENAbbvName	= rs.getString("dayenabbvname");
				dayENFullName	= rs.getString("dayenfullname");
				
				days.add(new DayForm(dayNo, dayTHAbbvName, dayTHFullName, dayENAbbvName, dayENFullName));
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
		return days;
	}
	public String GetDayTHFullName(String dayNo) throws Exception {
		String dayTHFullName= "";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT daythfullname FROM hrmday WHERE dayno = '"+dayNo+"' ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				dayTHFullName	= rs.getString("daythfullname");
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
		return dayTHFullName;
	}
}