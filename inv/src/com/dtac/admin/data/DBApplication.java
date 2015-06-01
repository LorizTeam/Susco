/*
 * Created on 30-09-2011
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

import com.dtac.admin.form.ApplTypeForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBApplication {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetApplTypeList()	throws Exception { //22-5-2009
		List applType = new ArrayList();
		
		String applTypeCode	= "";
		String applTypeName	= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.apptype, a.appname FROM admapplgrp a " +
			"ORDER BY a.apptype ";			
		
		//	System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				applTypeCode 	= rs.getString("apptype");
				applTypeName  	= rs.getString("appname");

				applType.add(new ApplTypeForm(applTypeCode, applTypeName));
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
		return applType;
	}
}