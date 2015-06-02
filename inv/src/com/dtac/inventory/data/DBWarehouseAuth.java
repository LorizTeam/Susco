/*
 * Created on 15-06-2012
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
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBWarehouseAuth {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;
	DateUtil dateUtil = new DateUtil();
	
	public List GetWarehouseAuth(String wahoCode, String empID, String authStatus) 
	throws Exception {	//15-06-2012
		List warehouse = new ArrayList();		
		String wahoName	= "";
		String empNameT = "", empLastNameT = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.wahocode, a.wahoname, a.wahostatus, b.authstatus, " +
			"c.empid, c.empnamet, c.emplastnamet " +
			"FROM mmwarehouse a " +
			"JOIN mmwarehouseauth b ON (a.wahocode = b.wahocode) " +
			"JOIN employee c ON (b.empid = c.empid) " +
			"WHERE ";

			if (!wahoCode.equals("")) 	sqlStmt = sqlStmt + "b.wahocode = '"+wahoCode+"' AND ";
			if (!empID.equals("")) 		sqlStmt = sqlStmt + "b.empid = '" +empID+"' AND ";
			if (!authStatus.equals("")) sqlStmt = sqlStmt + "b.authstatus = '"+authStatus+"' AND ";
			
			sqlStmt = sqlStmt + "a.wahostatus = 'AC' ORDER BY b.wahocode, b.empid, b.authstatus ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				wahoCode	= rs.getString("wahocode");
				wahoName	= rs.getString("wahoname");
				empID 		= rs.getString("empid");
				empNameT	= rs.getString("empnamet");
				empLastNameT= rs.getString("emplastnamet");
				authStatus	= rs.getString("authstatus");
				
				warehouse.add(new WarehouseForm(wahoCode, wahoName, empID, empNameT, empLastNameT, authStatus));
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
	public List GetWarehouseAuthList(String wahoCode1,String wahoCode2,String wahoCode3,String wahoCode4, String wahoCode5,String empID, String authStatus) 
	throws Exception {	//15-06-2012
		List warehouse = new ArrayList();		
		String wahoName	= "";
		String empNameT = "", empLastNameT = "";
		try {
			conn = agent.getConnectMYSql();
		String	wahoCode = "'"+wahoCode1+"','"+wahoCode2+"','"+wahoCode3+"','"+wahoCode4+"','"+wahoCode5+"'"; 
			String sqlStmt = "SELECT a.wahocode, a.wahoname, a.wahostatus, b.authstatus, " +
			"c.empid, c.empnamet, c.emplastnamet " +
			"FROM mmwarehouse a " +
			"JOIN mmwarehouseauth b ON (a.wahocode = b.wahocode) " +
			"JOIN employee c ON (b.empid = c.empid) " +
			"WHERE ";

			if (!wahoCode.equals("")) 	sqlStmt = sqlStmt + "b.wahocode in ("+wahoCode+") AND ";
			if (!empID.equals("")) 		sqlStmt = sqlStmt + "b.empid = '" +empID+"' AND ";
			if (!authStatus.equals("")) sqlStmt = sqlStmt + "b.authstatus = '"+authStatus+"' AND ";
			
			sqlStmt = sqlStmt + "a.wahostatus = 'AC' ORDER BY b.wahocode, b.empid, b.authstatus ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				wahoCode	= rs.getString("wahocode");
				wahoName	= rs.getString("wahoname");
				empID 		= rs.getString("empid");
				empNameT	= rs.getString("empnamet");
				empLastNameT= rs.getString("emplastnamet");
				authStatus	= rs.getString("authstatus");
				
				warehouse.add(new WarehouseForm(wahoCode, wahoName, empID, empNameT, empLastNameT, authStatus));
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
	
	public List GetEmployeeNotInWarehouseAuth(String wahoCode) throws Exception {	//13-06-2012
		List warehouse = new ArrayList();
		String wahoName	= "";
		String empID	= "", empNameT	= "", empLastNameT	= "", authStatus = "";		
  		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.empid, a.empnamet, a.emplastnamet " +
			"FROM employee a " +			
			"WHERE a.status = 'AC' AND a.empid <> '00000' AND a.empid NOT IN " +
				"(SELECT empid FROM mmwarehouseauth WHERE wahocode = '"+wahoCode+"' AND authstatus = 'AC') " +  
			"ORDER BY a.empid ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				empID 		= rs.getString("empid");
				empNameT	= rs.getString("empnamet");
				empLastNameT= rs.getString("emplastnamet");
								
				warehouse.add(new WarehouseForm(wahoCode, wahoName, empID, empNameT, empLastNameT, authStatus));
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
	public void AddWarehouseAuth(String wahoCode, String empID) throws Exception {	//15-06-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmwarehouseauth (wahocode, empid) VALUES " +
			"('"+wahoCode+"', '"+empID+"') ";
			
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
	public boolean CheckWarehouseAuth(String wahoCode, String empID) throws Exception { //29-04-2012
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.wahocode " +
			"FROM mmwarehouse a " +
			"JOIN mmwarehouseauth b ON (a.wahocode = b.wahocode) " +
			"WHERE a.wahocode = '"+wahoCode+"' AND b.empid = '"+empID+"' AND a.wahostatus = 'AC' ";
	
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
	public void UpdateWarehouseAuth(String wahoCode, String empID, String status, String updateBy) throws Exception {//15-06-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmwarehouseauth SET updateby = '"+updateBy+"', authstatus = '"+status+"', " +
			"updatedate = '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'-')+"' " +
			"WHERE wahocode = '"+wahoCode+"' AND empid = '"+empID+"' ";
			
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