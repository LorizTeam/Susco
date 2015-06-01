/*
 * Created on 18-04-2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.center.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dtac.center.form.ContactForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBContact {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetContact(String tableName, String idCode, String contactNo) throws Exception {	//18-04-2012
		List contactList = new ArrayList();
		String name = "", tel1 = "", tel2 = "", email = "", facebook = "", remark = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.tablename, a.idcode, a.contactno, a.name, " +
			"a.tel1, a.tel2, a.email, a.facebook, a.remark " +
			"FROM mastercontact a " +
			"WHERE a.tablename = '"+tableName+"' AND a.idcode = '"+idCode+"' AND a.contactno = '"+contactNo+"' ";			
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				if (rs.getString("name") != null) name = rs.getString("name"); else name = "";				
				if (rs.getString("tel1") != null) tel1 = rs.getString("tel1"); else tel1 = "";
				if (rs.getString("tel2") != null) tel2 = rs.getString("tel2"); else tel2 = "";
				
				contactList.add(new ContactForm(tableName, idCode, contactNo,
					name, tel1, tel2, email, facebook, remark));
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
		return contactList;
	}
	public void AddContact(String tableName, String idCode, String contactNo, 
		String name, String tel1, String tel2, String email, String facebook, String remark) 
	throws Exception {	//18-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mastercontact (tablename, idcode, contactno, name, " +
			"tel1, tel2, email, facebook, remark) " +
			"VALUES ('"+tableName+"', '"+idCode+"', '"+contactNo+"', '"+name+"', '"+tel1+"', '"+tel2+"', " +
			"'"+email+"', '"+facebook+"', '"+remark+"') ";
			
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
	public boolean CheckContact(String tableName, String idCode, String contactNo) throws Exception { //17-04-2012
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT * FROM mastercontact " +
			"WHERE tablename = '"+tableName+"' AND idcode = '"+idCode+"' AND contactno = '"+contactNo+"' ";
		
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
	public void UpdateContact(String tableName, String idCode, String contactNo, 
		String name, String tel1, String tel2, String email, String facebook, String remark) 
	throws Exception {	//18-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mastercontact SET name = '"+name+"', tel1 = '"+tel1+"', " +
			"tel2 = '"+tel2+"' " +
			"WHERE tablename = '"+tableName+"' AND idcode = '"+idCode+"' AND contactno = '"+contactNo+"' ";
				
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