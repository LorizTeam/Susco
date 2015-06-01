/*
 * Created on 12-04-2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.inventory.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.dtac.inventory.form.MaterialTypeForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMaterialType {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetMaterialTypeList(String matTypeCode) throws Exception {
		List matTypeList = new ArrayList();
		String matTypeName = "", matStatus = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.mattypecode, a.mattypename, a.matstatus FROM mmmattype a " ;		
			if (!matTypeCode.equals("")) sqlStmt = sqlStmt + "WHERE a.mattypecode = '" + matTypeCode+"' "; 
			

			sqlStmt = sqlStmt + "ORDER BY a.mattypecode" ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				matTypeCode	= rs.getString("mattypecode");
				matTypeName	= rs.getString("mattypename");		
				matStatus= rs.getString("matstatus");
					
				matTypeList.add(new MaterialTypeForm(matTypeCode, matTypeName, matStatus));
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
		return matTypeList;
	}
	// getMatTypeName
	public String GetMaterialTypeName(String matTypeCode) throws Exception {
		String matTypeName = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.mattypecode, a.mattypename, a.matstatus FROM mmmattype a ";			
			
			if (!matTypeCode.equals("")) sqlStmt = sqlStmt + "WHERE a.mattypecode = '" + matTypeCode+"' ";

			sqlStmt = sqlStmt + "ORDER BY a.mattypecode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				matTypeCode	= rs.getString("mattypecode");
				matTypeName	= rs.getString("mattypename");		
			 
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
		return matTypeName;
	}
	// getMatTypeName
	public String GetMaterialGrpName(String matTypeCode, String matGrpCode, String matGrpStatus) 
	throws Exception { //30-04-2012
		String matTypeName = "", matStatus = "", matGrpName = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.mattypecode, a.mattypename, a.matstatus, " +
			"b.matgrpcode, b.matgrpname, b.matgrpstatus " +
			"FROM mmmattype a " +
			"JOIN mmmatgrp b ON (a.mattypecode = b.mattypecode) " +
			"WHERE a.matTypeCode like '%" + matTypeCode+"%' ";			
			
			if (!matGrpCode.equals("")) sqlStmt = sqlStmt + "AND b.matgrpcode = '" + matGrpCode+"' ";
			if (!matGrpStatus.equals("")) sqlStmt = sqlStmt + "AND b.matgrpstatus = '" + matGrpStatus+"' ";
			
			sqlStmt = sqlStmt + "ORDER BY b.mattypecode, b.matgrpstatus, b.matgrpcode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				matTypeCode	= rs.getString("mattypecode");
				matTypeName	= rs.getString("mattypename");		
				matStatus	= rs.getString("matstatus");
				
				matGrpCode	= rs.getString("matgrpcode");
				matGrpName	= rs.getString("matgrpname");
				matGrpStatus= rs.getString("matgrpstatus");
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
		return matGrpName;
	}
	public List GetMaterialGrpList(String matTypeCode, String matGrpCode, String matGrpStatus) 
	throws Exception { //30-04-2012
		List matTypeList = new ArrayList();
		String matTypeName = "", matGrpName = "", matStatus = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.mattypecode, a.mattypename, a.matstatus, " +
			"b.matgrpcode, b.matgrpname, b.matgrpstatus " +
			"FROM mmmattype a " +
			"JOIN mmmatgrp b ON (a.mattypecode = b.mattypecode) WHERE " ;
			if (!matTypeCode.equals("")) sqlStmt = sqlStmt + "a.matTypeCode = '"+matTypeCode+"' AND ";			
			if (!matGrpCode.equals("")) sqlStmt = sqlStmt + "b.matgrpcode = '" + matGrpCode+"' AND ";
			if (!matGrpStatus.equals("")) sqlStmt = sqlStmt + "b.matgrpstatus = '" + matGrpStatus+"' AND ";
			
			sqlStmt = sqlStmt + "a.matTypeCode <> '' ORDER BY b.mattypecode, b.matgrpstatus, b.matgrpcode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				matTypeCode	= rs.getString("mattypecode");
				matTypeName	= rs.getString("mattypename");		
				matStatus	= rs.getString("matstatus");
				
				matGrpCode	= rs.getString("matgrpcode");
				matGrpName	= rs.getString("matgrpname");
				matGrpStatus= rs.getString("matgrpstatus");
								
				matTypeList.add(new MaterialTypeForm(matTypeCode, matTypeName, matStatus, 
					matGrpCode, matGrpName, matGrpStatus));			
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
		return matTypeList;
	}
	public List GetMaterialGrp(String matTypeCode, String matGrpCode ) 
	throws Exception { //30-04-2012
		List matTypeList = new ArrayList();
		String matTypeName = "", matStatus = "", matGrpName = "";
		String matGrpStatus = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.mattypecode, a.mattypename, a.matstatus, " +
			"b.matgrpcode, b.matgrpname, b.matgrpstatus " +
			"FROM mmmattype a " +
			"JOIN mmmatgrp b ON (a.mattypecode = b.mattypecode) " +
			"WHERE a.mattypecode = '" + matTypeCode+"' AND b.matgrpcode = '"+matGrpCode+"'";			
						
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				matTypeCode	= rs.getString("mattypecode");
				matTypeName	= rs.getString("mattypename");		
				matStatus	= rs.getString("matstatus");
				
				matGrpCode	= rs.getString("matgrpcode");
				matGrpName	= rs.getString("matgrpname");
				matGrpStatus= rs.getString("matgrpstatus");
								
				matTypeList.add(new MaterialTypeForm(matTypeCode, matTypeName, matStatus, 
					matGrpCode, matGrpName, matGrpStatus));			
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
		return matTypeList;
	}
	public void AddMaterialGrp(String procHDCode, String procDTCode, String procDTName) 
	throws Exception {	//14-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO jobprocessdt (processcode, subprocesscode, subprocessname, status) " +
			"VALUES ('"+procHDCode+"', '"+procDTCode+"', '"+procDTName+"', 'AC') ";
			
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
	public void UpdateMaterialGrp(String matTypeCode, String matGrpCode, String matGrpName, String matGrpStatus) 
	throws Exception { //14-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmatgrp SET matgrpname = '"+matGrpName+"', matgrpstatus = '"+matGrpStatus+"'" +
			"WHERE mattypecode = '"+matTypeCode+"' AND matgrpcode = '"+matGrpCode+"'";
				
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
	public void AddSubMaterialGrp(String matTypeCode, String matGrpCode, String matGrpName,String matGrpStatus) 
	throws Exception {	//14-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmmatgrp (mattypecode,matgrpcode,matgrpname,matgrpstatus) " +
					"VALUES('"+matTypeCode+"', '"+matGrpCode+"', '"+matGrpName+"', '"+matGrpStatus+"')";
			
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