/*
 * Created on 12-12-2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.admin.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dtac.utils.DBConnect;

 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBFileUpload {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement pStmt = null;
	ResultSet rs	= null;
	
	public String GetPicturePath()	throws Exception {	//12-12-2011
		String pathName	= "";
		try {
			conn = agent.getConnectMYSql();
						
			String sqlStmt = "SELECT picbasepath FROM company ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				pathName	= rs.getString("picbasepath");
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
		return pathName;
	}
	public void UpdatePictureFileName(String picPathName, String picNo, String fileName, String idCode) 
	throws Exception { //10-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String tableName = "";
			String whereSql  = "";
			
			if (picPathName.equals("saleorder")) {
				tableName = "sdsaleorder";
				whereSql = "ordtype = '"+idCode.substring(0,2)+"' AND ordyear = '"+idCode.substring(2,6)+"' AND ordno = '"+idCode.substring(6,11)+"' ";
				
			} else if (picPathName.equals("saleorderlogo")) {
				tableName = "sdsaleorderlogo";
				whereSql = "ordtype = '"+idCode.substring(0,2)+"' AND ordyear = '"+idCode.substring(2,6)+"' AND ordno = '"+idCode.substring(6,11)+"' " +
						"AND logono = '"+idCode.substring(11,13)+"' ";
				
			} else if (picPathName.equals("umbrella")) {
				tableName = "umbmaster";
				whereSql = "umbrcode = '"+idCode+"' ";
				
			} else if (picPathName.equals("customer")) {
				tableName = "sdcustmaster";
				whereSql = "custcode = '"+idCode+"' ";
				
			} else if (picPathName.equals("warehouse")) {
				tableName = "mmwaholocation";
				whereSql = "wahocode = '"+idCode.substring(0,2)+"' AND locacode = '"+idCode.substring(2,4)+"' ";
				
			} else if (picPathName.equals("material")) {
				tableName = "mmmaterial";
				whereSql = "matcode = '"+idCode+"' ";
			}
			
			
			String sqlStmt = "UPDATE "+tableName+" SET pic"+picNo+" = '"+fileName+"' " +
			
			"WHERE " +whereSql;
			
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
	public void UpdatePictureFileNameAddress(String picPathName, String addrType, String addrNo,
		String picNo, String fileName, String idCode) 
	throws Exception { //20-11-2012
		try {
			conn = agent.getConnectMYSql();
						
			String whereSql  = "";
			
				 if (picPathName.equals("customer"))  whereSql = "tablename = 'cust' ";
			else if (picPathName.equals("saleorder")) whereSql = "tablename = 'saor' ";
			
						
			String sqlStmt = "UPDATE masteraddress SET pic"+picNo+" = '"+fileName+"' " +
			
			"WHERE " + whereSql + "AND idcode = '"+idCode+"' AND addrtype = '"+addrType+"' AND " +
			"addrno = '"+addrNo+"'";
			
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