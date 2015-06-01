/*
 * Created on 18-04-2012
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

import com.dtac.inventory.form.ReportForm;
import com.dtac.utils.DBConnect;
 /**
 * @author Setthaphong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBReport {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;
	
	public boolean CheckAging(String matTypeCode) throws Exception { //29-04-2012
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT * FROM mmagingreport WHERE mattypecode = '"+matTypeCode+"'";			
		
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
	public void AddAging(String matTypeCode, String agingJan, String agingFeb, String agingMar, String agingApr, 
			String agingMay, String agingJun, String agingJul, String agingAug, String agingSep, String agingOct, String agingNov, String agingDec) 
	throws Exception {	//18-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmagingreport(mattypecode, agingjan, agingfeb, agingmar, agingapr, agingmay, " +
			"agingjun, agingjul, agingaug, agingsep, agingoct, agingnov, agingdec) " +
			"VALUES ('"+matTypeCode+"', '"+agingJan+"', '"+agingFeb+"', '"+agingMar+"', '"+agingApr+"', " +
					"'"+agingMay+"', '"+agingJun+"', '"+agingJul+"', '"+agingAug+"', '"+agingSep+"', '"+agingOct+"', " +
					"'"+agingNov+"', '"+agingDec+"')";
			
			//System.out.println(sqlStmt);
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
	public void UpdateAging(String matTypeCode, String agingJan, String agingFeb, String agingMar, String agingApr, 
			String agingMay, String agingJun, String agingJul, String agingAug, String agingSep, String agingOct, String agingNov, String agingDec) 
	throws Exception { //19-07-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmagingreport SET agingjan = '"+agingJan+"', agingfeb = '"+agingFeb+"', " +
			"agingmar = '"+agingMar+"', agingapr = '"+agingApr+"', agingmay = '"+agingMay+"', agingjun = '"+agingJun+"', " +
			"agingjul = '"+agingJul+"', agingaug = '"+agingAug+"', agingsep = '"+agingSep+"', agingoct = '"+agingOct+"', " +
			"agingnov = '"+agingNov+"', agingdec = '"+agingDec+"' " +
			"WHERE mattypecode = '"+matTypeCode+"'";
			
			//System.out.println(sqlStmt);
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