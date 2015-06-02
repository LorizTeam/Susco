/*
 * Created on 24-09-2011
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

import com.dtac.admin.form.MemberAuthForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMemberAuth {
	
	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List ViewMemberAuth(String appType, String memberId) throws Exception {	//02-09-2012
		List authList = new ArrayList();
		
		String memberTypeCode = "", memberTypeName = ""; 
		String firstName = "", lastName = "", status 	= ""; 
		String idKeyDisp = "", idKeyMant= "", idKeyAppv = "", appCode = "", appName = ""; 
		String authDisp	 = "", authMant	= "", authAppv 	= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.appcode, a.appname, b.authmant, b.authdisp, b.authappv, b.empid " +
			"FROM admappllist a " +
			"LEFT JOIN admapplauth b ON (a.appcode = b.appcode AND b.empid = '"+memberId.trim()+"') " +
			"WHERE a.apptype = '"+appType.trim()+"' AND a.appcode NOT LIKE 'ad10%' ORDER BY a.appcode" ;

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);		
			while (rs.next()) {
				if (rs.getString("authdisp") != null) authDisp = rs.getString("authdisp"); else	authDisp = " ";
				if (rs.getString("authmant") != null) authMant = rs.getString("authmant"); else	authMant = " ";
				if (rs.getString("authappv") != null) authAppv = rs.getString("authappv"); else	authAppv = " ";
				
				appCode	= rs.getString("appcode");
				appName	= rs.getString("appname");
						
				idKeyDisp = rs.getString("appcode")+authDisp;
				idKeyMant = rs.getString("appcode")+authMant;
				idKeyAppv = rs.getString("appcode")+authAppv;
				
				authList.add(new MemberAuthForm(memberTypeCode, memberTypeName, 
					memberId, firstName, lastName, status, 
					idKeyDisp, idKeyMant, idKeyAppv, appType, appCode, appName, authMant, authDisp, authAppv));
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
		return authList;
	}
	public boolean CheckRecordMemberAuth(String memberId, String appCode) 
	throws Exception {	//02-10-2011
		boolean resultFlag = false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM admapplauth WHERE " +
			"appcode = '"+appCode+"' AND empid = '"+memberId+"' " ;

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				resultFlag = true;
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
		return resultFlag;
	}
	public void UpdateMemberAuth(String memberId, String authType, String appCode) 
	throws Exception {	//02-09-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE admapplauth SET ";
			
			if (authType.equals("mant")) sqlStmt = sqlStmt + "authmant='X' ";
			if (authType.equals("disp")) sqlStmt = sqlStmt + "authdisp='X' ";
			if (authType.equals("appv")) sqlStmt = sqlStmt + "authappv='X' ";
			
			sqlStmt = sqlStmt + " WHERE appcode = '"+appCode+"' AND empid = '"+memberId+"' ";
			
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
	public void AddMemberAuth(String memberId, String authType,	String appCode) throws Exception {	//02-09-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO admapplauth (empid, appcode, ";
			
			if (authType.equals("mant")) sqlStmt = sqlStmt + "authmant";
			if (authType.equals("disp")) sqlStmt = sqlStmt + "authdisp";
			if (authType.equals("appv")) sqlStmt = sqlStmt + "authappv";
			
			sqlStmt = sqlStmt + ") VALUES ('"+memberId+"', '"+appCode+"', 'X') ";

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
	public void ResetMemberAuth(String memberId, String authType, String appType) throws Exception { //02-09-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE admapplauth SET ";
			
			if (authType.equals("mant")) sqlStmt = sqlStmt + "authmant='-' ";
			if (authType.equals("disp")) sqlStmt = sqlStmt + "authdisp='-' ";
			if (authType.equals("appv")) sqlStmt = sqlStmt + "authappv='-' ";
			
			sqlStmt = sqlStmt + " WHERE appcode LIKE '"+appType.trim()+"%' AND empid = '"+memberId+"' ";

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