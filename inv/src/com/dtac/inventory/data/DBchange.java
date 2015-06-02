package com.dtac.inventory.data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dtac.inventory.form.CollectionForm;
import com.dtac.inventory.form.changeItemAddForm;
import com.dtac.utils.DBConnect;



public class DBchange {
	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	
	
	
	/**
	 * Add these data to the database
	 * 
	 * @author SlashZy
	 * @param docType
	 * @param docCode
	 * @param prCode
	 * @throws Exception
	 */
	public void Add(String docType, String docCode, String prCode) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT  INTO mmchg (doctype, doccode, prcode) " +
			"VALUES ('"+docType.trim()+"', '"+docCode.trim()+"', '"+prCode.trim()+"' )";
						
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
	
	
	/**
	 * find item by mmcode 
	 * 
	 * @author SlashZy
	 * @param mmcode
	 * @return Map
	 * @throws Exception
	 */
	public Map getItem(String mmcode) throws Exception{
		Statement pStmt = null;
		ResultSet rs = null;
		Map res = new HashMap();
		try{
			conn = agent.getConnectMYSql();
			String sqlStmt = "SELECT * FROM mmmaterial a,mmmattype b WHERE a.mattypecode = b.mattypecode AND a.matcode = '"+mmcode+"' ";
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);
			if(rs.next()){
			res.put("itemname",rs.getString("matname"));
			res.put("collection",rs.getString("mattypename"));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return res.get("itemname") != null ? res : null;
	}
	
	
	public boolean getDoc(String docCode) throws Exception{
		Statement pStmt = null;
		ResultSet rs = null;
		boolean res = false;
		try{
			conn = agent.getConnectMYSql();
			String sqlStmt = "SELECT * FROM mmchg WHERE doccode = '"+docCode+"' ";
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);
			if(rs.next()){
				res = true;
			}
			pStmt.close();
			conn.close();
			return res;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List getChangeList() throws Exception {	//03-06-2012
			List change = new ArrayList();
			String docType = "", docCode = "", prCode = "";
			Statement pStmt = null;
			ResultSet rs = null;
		try {			
			conn = agent.getConnectMYSql();
			String sqlStmt = "SELECT * FROM mmchg ORDER BY docCode ASC" ;
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				docType 	= rs.getString("doctype");
				docCode   	= rs.getString("doccode");
				prCode 		= rs.getString("prcode");
				
				change.add(new changeItemAddForm(docType, prCode, docCode)); 
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
		return change;
	}
	public void UpdateCollection(String txtcode, String txtname, String txtstock, String txtaccount) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmattype SET mattypename = '"+txtname+"', stocktype = '"+txtstock+"', acctcode = '"+txtaccount+"' "+
			"WHERE mattypecode = '"+txtcode+"'";
						
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
	public void DeleteCollection(String txtcode) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			String sqlStmt = "DELETE FROM mmmattype WHERE mattypecode = '"+txtcode+"'";
						
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
