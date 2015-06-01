/*
 * Created on 01-05-2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.inventory.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMatDocNumber {

	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public String GetLastDocNo(String docTypeCode, String docYear) throws Exception {	//07-05-2012
		String docNo = "0";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT lastdocno FROM mmdocnumber " +
			"WHERE doctype = '"+docTypeCode+"' AND docyear = '"+docYear+"'";

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				docNo	= rs.getString("lastdocno");
			}
			docNo	= String.valueOf(Integer.parseInt(docNo)+1);
				 if (docNo.length() == 1) docNo = "0000" + docNo;
			else if (docNo.length() == 2) docNo = "000" + docNo;
			else if (docNo.length() == 3) docNo = "00" + docNo;
			else if (docNo.length() == 4) docNo = "0" + docNo;
				 
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
		return docNo;
	}
	public void UpdateLastDocNo(String docTypeCode, String docYear, String docNo)	throws Exception {	//04-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmdocnumber SET lastdocno = "+Integer.parseInt(docNo)+
			" WHERE doctype = '"+docTypeCode+"' AND docyear = '"+docYear+"' ";
			
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