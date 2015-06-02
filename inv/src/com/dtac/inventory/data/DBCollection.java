package com.dtac.inventory.data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dtac.inventory.form.CollectionForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;



public class DBCollection {
	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	
	
		// TODO Auto-generated constructor stub
	
	public void AddCollection(String collectcode, String collectname, String matStatus, String docDate) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO mmmattype(mattypecode, mattypename, matstatus, docdate) " +
			"VALUES ('"+collectcode.trim()+"', '"+collectname.trim()+"', '"+matStatus+"', '"+docDate+"')";
						
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
	public List Getcollectionlist(String collectcode, String collectname, String matStatus) throws Exception {	//03-06-2012
			List collection = new ArrayList();
			String wahoName = "", docDate = "";
			Statement pStmt = null;
			ResultSet rs = null;
			DateUtil dateUtil = new DateUtil();
		try {			
			conn = agent.getConnectMYSql();
			String sqlStmt = "SELECT a.mattypecode, a.mattypename, a.matstatus, a.docdate " +
					"FROM mmmattype a " +
					"WHERE ";
			if (!collectcode.equals("")) sqlStmt = sqlStmt + "a.mattypecode like '%"+collectcode+"%' AND ";
			if (!collectname.equals("")) sqlStmt = sqlStmt + "a.mattypename like '%"+collectname+"%' AND ";
			if (!matStatus.equals("")) sqlStmt = sqlStmt + "a.matstatus = '"+matStatus+"' AND ";
			
					sqlStmt = sqlStmt + "a.matstatus <> '' ORDER BY a.mattypecode " ;	 
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				collectcode 	= rs.getString("mattypecode");
				collectname  	= rs.getString("mattypename");
				matStatus   	= rs.getString("matstatus");
				docDate			= rs.getString("docdate");
				if(!docDate.equals("")){
				docDate = dateUtil.CnvToDDMMYYYYThaiYear(docDate);
				}
				collection.add(new CollectionForm(collectcode, collectname, matStatus, docDate)); 
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null)    rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return collection;
	}
	public void UpdateCollection(String collectcode, String collectname, String matStatus, String docDate) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmattype SET mattypename = '"+collectname+"', matstatus = '"+matStatus+"', docdate = '"+docDate+"'"+
			"WHERE mattypecode = '"+collectcode+"'";
						
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
	public void DeleteCollection(String collectcode) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			String sqlStmt = "DELETE FROM mmmattype WHERE mattypecode = '"+collectcode+"'";
						
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
