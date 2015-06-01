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
public class DBWarehouse {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetWarehouseList(String wahoCode, String wahoName ,String wahoStatus) throws Exception {	//01-05-2012
		List warehouse = new ArrayList();		
		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.wahocode, a.wahoname, a.wahostatus " +
			"FROM mmwarehouse a WHERE ";

			if (!wahoCode.equals(""))	sqlStmt = sqlStmt + "a.wahocode = '"+wahoCode.trim()+"' AND ";
			if (!wahoName.equals(""))	sqlStmt = sqlStmt + "a.wahoname = '"+wahoName.trim()+"' AND ";
			if (!wahoStatus.equals(""))	sqlStmt = sqlStmt + "a.wahostatus = '"+wahoStatus.trim()+"' AND ";
			
			sqlStmt = sqlStmt + "a.wahostatus <> '' ORDER BY  a.wahocode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				wahoCode		= rs.getString("wahocode");
				wahoName		= rs.getString("wahoname");
				wahoStatus		= rs.getString("wahostatus");
				
				warehouse.add(new WarehouseForm(wahoCode, wahoName,	wahoStatus));
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
	public String GetWarehouseName(String wahoCode) throws Exception {	//25-05-2012		
		String wahoName		= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.wahoname FROM mmwarehouse a " +
			"WHERE a.wahocode = '" + wahoCode.trim()+"' ";

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				wahoName	= rs.getString("wahoname");
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
		return wahoName ;
	}
	public void AddWarehouse(String wahoCode, String wahoName, String wahoStatus) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmwarehouse (wahocode, wahoname, wahostatus) " +
			"VALUES ('"+wahoCode.trim()+"', '"+wahoName.trim()+"', '"+wahoStatus+"' )";
						
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
	public void UpdateWarehouse(String wahoCode, String wahoName, String wahoStatus) throws Exception { //03-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmwarehouse SET wahoname = '"+wahoName.trim()+"', " +
			"wahostatus = '"+wahoStatus+"' " +
			"WHERE wahocode = '"+wahoCode.trim()+"' ";
			
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