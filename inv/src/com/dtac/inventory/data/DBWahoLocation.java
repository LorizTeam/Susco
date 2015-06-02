/*
 * Created on 25-01-2008
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

import com.dtac.inventory.form.WarehouseForm;
import com.dtac.utils.DBConnect;

 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBWahoLocation {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetLocationList(String wahoCode, String locaCode, String locaStatus) throws Exception {	//01-05-2012
		List warehouse = new ArrayList();		
		String wahoName	= "", wahoStatus = "", locaName = "", pic1 = "", pic2= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.wahocode, a.wahoname, b.locacode, b.locaname, b.locastatus, b.pic1, b.pic2 " +
			"FROM mmwarehouse a " +
			"INNER JOIN mmwaholocation b ON (a.wahocode = b.wahocode) " +
			"WHERE a.wahocode = '"+wahoCode+"' ";

			if (!locaCode.equals(""))	sqlStmt = sqlStmt + "AND b.locacode = '"+locaCode.trim()+"' ";
			if (!locaStatus.equals(""))	sqlStmt = sqlStmt + "AND b.locastatus = '"+locaStatus.trim()+"' ";
			
			sqlStmt = sqlStmt + "ORDER BY a.wahocode, b.locacode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				wahoCode		= rs.getString("wahocode");
				wahoName		= rs.getString("wahoname");
				locaCode		= rs.getString("locaCode");
				locaName		= rs.getString("locaName");
				locaStatus		= rs.getString("locastatus");
				if (rs.getString("pic1") != null) pic1 = rs.getString("pic1"); else pic1 = "";				
				if (rs.getString("pic2") != null) pic2 = rs.getString("pic2"); else pic2 = "";	
								
				warehouse.add(new WarehouseForm(wahoCode, wahoName,	wahoStatus,
					locaCode, locaName, locaStatus, pic1, pic2));
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
		return warehouse;
	}
	public void AddLocation(String wahoCode, String locaCode, String locaName, String locaStatus) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmwaholocation (wahocode, locacode, locaname, locastatus) " +
			"VALUES ('"+wahoCode+"', '"+locaCode+"', '"+locaName+"', '"+locaStatus+"' )";
						
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
	public void UpdateLocation(String wahoCode, String locaCode, String locaName, String locaStatus) throws Exception { //03-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmwaholocation SET locaname = '"+locaName+"', " +
			"locastatus = '"+locaStatus+"' " +			
			"WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' ";
			
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
	public boolean CheckLocation(String wahoCode, String locaCode) throws Exception { //29-04-2012
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT * FROM mmwaholocation " +
			"WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' ";			
		
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
}