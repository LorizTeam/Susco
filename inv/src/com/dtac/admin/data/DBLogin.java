/*
 * Created on 24-09-2006
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

import com.dtac.admin.form.MemberLoginForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;

 /**
 * @author tanakarns
 */
public class DBLogin {

	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public String encrypt(String x) throws Exception {		
		String storepass = "";		
		try {
			java.security.Security.addProvider(new sun.security.provider.Sun());
			java.security.MessageDigest lMessageDigest = java.security.MessageDigest.getInstance("SHA", "SUN");
			byte[] _result = lMessageDigest.digest(x.getBytes());
			storepass = new sun.misc.BASE64Encoder().encode(_result);
			
		} catch (java.security.NoSuchProviderException nspe) {
			
		}
		return storepass;
	}
	public boolean UpdatePassword(String memberId, String passWord)	
	throws Exception {	//02-10-2011
		String encrypPass = encrypt(passWord);
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE employee SET password = '"+encrypPass+"' " +
			"WHERE empid = '"+memberId.trim()+"' ";
				
			//System.out.println(passWord);
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
		return true;
	}  
	public void AddAdmLogin(String memberId) throws Exception {	//26-09-2011
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO admlogin (empid, lastlogindate, lastchangepasswd) " +
			"VALUES ('"+memberId+"', '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'-')+"'," +
			"'"+dateUtil.subtractDate(dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'/'),1)+"')";
			
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	
			pStmt.close();
			conn.close();
		} catch (Exception e) {
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
	}
	public void AddAdmLoginDT(String memberId, String ipaddrno) 
	throws Exception {	//28-09-2011
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO admlogindt (empid, logindate, ipaddr) " +
			"VALUES ('"+memberId+"', '"+dateUtil.curDateTime()+"','"+ipaddrno+"')";
			
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
	public void UpdateLastLogin(String memberId) throws Exception {	//26-09-2011
		try {
			conn = agent.getConnectMYSql();

			String sqlStmt = "UPDATE admlogin SET lastlogindate = '"+dateUtil.curDateTime()+"' " +
			"WHERE empid = '"+memberId+"'";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			pStmt.close();
			conn.close();
		} catch (Exception e) {
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
	}
	public void UpdateLastChangePasswd(String memberId) throws Exception {	//06-10-2011
		try {
			conn = agent.getConnectMYSql();

			String sqlStmt = "UPDATE admlogin SET " +
			"lastchangepasswd = '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'-')+"' " +
			"WHERE empid = '"+memberId.trim()+"'";
			
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			pStmt.close();
			conn.close();
		} catch (Exception e) {
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
	}
	public boolean CheckLogIn(String memberId, String password) throws Exception {	//29-09-2011
		boolean result = false;
		String encrypPass = encrypt(password);
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.password FROM employee a " +
			"WHERE a.empid = '"+memberId+"' AND a.status = 'AC' ";
				
	     	pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				if (encrypPass.equals(rs.getString("password"))) result = true;				
		    }
			rs.close();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
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
	public boolean CheckAdmLogIn(String memberId) throws Exception {	//26-09-2011
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT empid FROM admlogin WHERE empid = '"+memberId+"' ";
				
	     	pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				result = true;
		    }
			rs.close();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
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
	public boolean CheckAppAuth(String memberId, String appCode, String authType) 
	throws Exception {	//29-09-2011
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.empid FROM admapplauth a " +
			"JOIN admappllist b ON (a.appcode = b.appcode) " +
			"WHERE a.empid = '"+memberId.trim()+"' AND a.appcode = '"+appCode.trim()+"' AND ";

				 if (authType.equals("mant")) sqlStmt = sqlStmt + "a.authmant='X' ";
			else if (authType.equals("disp")) sqlStmt = sqlStmt + "a.authdisp='X' ";
			else if (authType.equals("appv")) sqlStmt = sqlStmt + "a.authappv='X' ";

			//System.out.println(sqlStmt);
	     	pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				result = true;
				
				sqlStmt = "INSERT IGNORE INTO admloginappl (empid, logindate, appcode) VALUES " +
				"('"+memberId.trim()+"','"+dateUtil.curDateTime()+"', '"+appCode.trim()+"') ";

				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
		    }
			rs.close();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
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
	public boolean CheckAppAuthGroup(String memberId, String appGroup) 
	throws Exception {	//18-06-2010
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM admapplauthgroup WHERE memberid = '"+memberId.trim()+"' AND " +
			"appgroup = '"+appGroup.trim()+"' ";

			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				result = true;
		    }
			rs.close();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
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
	public List GetLoginList(String memberId, String firstName, String lastName, String memberTypeCode) 
	throws Exception {	//26-11-2011
		List login = new ArrayList();
		String deptCode	= "", deptName = "", deptNameAbbv = "";
		String memberTypeName= "", status = "";
		String ipaddrno	= "", lastLoginDate = "", lastChangPswd = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.empid, a.typecode, a.empnamet, a.emplastnamet, " +
			"a.status, b.typename, c.lastchangepasswd, a.deptcode, d.thdesc AS deptname, " +
			"DATE(c.lastlogindate) AS logindate, TIME(c.lastlogindate) AS logintime, c.lastlogindate " +
			"FROM employee a ";
			if (memberId.equals("00000") || memberId.equals("11173")) sqlStmt = sqlStmt + "LEFT ";
			
			sqlStmt = sqlStmt +	"JOIN emptype b ON (a.typecode = b.typecode) " +			
			"LEFT JOIN admlogin c ON (a.empid = c.empid) " + 
			"LEFT JOIN mastertabledt d ON (d.grpcode = 'dept' AND a.deptcode = d.typecode) " +
			"WHERE ";
			
			if (!memberTypeCode.equals("")) sqlStmt = sqlStmt + "a.typecode = '"+memberTypeCode+"' AND ";
			if (!memberId.equals("")) 	sqlStmt = sqlStmt + "a.empid = '"+memberId.trim()+"' AND ";//can not use like
			if (!firstName.equals(""))	sqlStmt = sqlStmt + "a.empnamet like '%"+firstName.trim()+"%' AND ";
			if (!lastName.equals(""))	sqlStmt = sqlStmt + "a.emplastnamet like '%"+lastName.trim()+"%' AND ";

			sqlStmt = sqlStmt + "a.status = 'AC' ORDER BY a.empid";
			
			//System.out.println(sqlStmt);	
	     	pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				memberId		= rs.getString("empid");
				memberTypeCode 	= rs.getString("typecode");
				if (rs.getString("typename") != null) memberTypeName = rs.getString("typename"); else memberTypeName = "";
				status 			= rs.getString("status");
				
				if (rs.getString("empnamet") != null) firstName = rs.getString("empnamet"); else firstName = "";
				if (rs.getString("emplastnamet") != null) lastName = rs.getString("emplastnamet"); else lastName = "";
				if (rs.getString("deptcode") != null) deptCode	= rs.getString("deptcode");	else deptCode = "-";
				if (rs.getString("deptname") != null) deptName	= rs.getString("deptname");	else deptName = "-";

				if (rs.getString("lastlogindate") != null)
					lastLoginDate 	= dateUtil.CnvToDDMMYYYY(rs.getString("logindate")) + " At " + rs.getString("logintime");
				else lastLoginDate = "";
				
				if (rs.getString("lastchangepasswd") != null)
					lastChangPswd	= dateUtil.CnvToDDMMYYYY(rs.getString("lastchangepasswd"));
				else lastChangPswd = "";
					
				login.add(new MemberLoginForm(memberTypeCode, memberTypeName,
					memberId, firstName, lastName, deptCode, deptName, deptNameAbbv, status, 
					ipaddrno, lastLoginDate, lastChangPswd));
		    }
			rs.close();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
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
		return login;
	}
}