/*
 * Created on 10-10-2011
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

import com.dtac.admin.form.YearForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBYear {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement pStmt = null;
	ResultSet rs	= null;
	
	public List GetYearList(String year, String engYear) throws Exception {	//06-10-2011
		List years = new ArrayList();
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.year, a.engyear FROM hrmyear a WHERE ";

			if (!year.equals("")) sqlStmt = sqlStmt + "a.year = '"+year.trim()+"' AND ";

			sqlStmt = sqlStmt + "a.year <> '' ORDER BY a.year DESC ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				year	= rs.getString("year");
				engYear	= rs.getString("engyear");
				
				years.add(new YearForm(year, engYear));
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
		return years;
	}
}