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

import com.dtac.admin.form.MemberForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMember {

	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public List GetMember(String memberId) throws Exception {	//29-09-2011
		List member = new ArrayList();

		String firstName	= "", lastName	= "", status = "";
		String deptCode		= "", deptName	= "", deptNameAbbv = "";
		String memberTypeCode = "", memberTypeName	= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.empid, a.typecode, a.empnamet, a.emplastnamet, a.password, a.status, " +
			"a.deptcode, b.typename, c.thdesc AS deptname " +
			"FROM emptype b " +
			"JOIN employee a ON (a.typecode = b.typecode) " +
			"LEFT JOIN mastertabledt c ON (a.deptcode = c.typecode AND c.grpcode = 'dept') " +
			"WHERE ";  
			
			if (!memberId.equals("")) sqlStmt = sqlStmt + "a.empid = '"+memberId.trim()+"' AND ";

			sqlStmt = sqlStmt + " a.empid <> '00000' ORDER BY a.empid ";			

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				memberId		= rs.getString("empid");
				memberTypeCode 	= rs.getString("typecode");
				memberTypeName	= rs.getString("typename");
				status			= rs.getString("status");
				
				if (rs.getString("empnamet") != null) firstName = rs.getString("empnamet"); else firstName = "";
				if (rs.getString("emplastnamet") != null) lastName = rs.getString("emplastnamet"); else lastName = "";
				
				if (rs.getString("deptcode") != null) deptCode	= rs.getString("deptcode");	else deptCode = "-";
				if (rs.getString("deptname") != null) deptName	= rs.getString("deptname");	else deptName = "-";
								
				member.add(new MemberForm(memberTypeCode, memberTypeName, memberId, firstName, lastName,  
					deptCode, deptName, deptNameAbbv, status));
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
		return member;
	}
	public boolean CheckMember(String memberId, String status) throws Exception { //05-10-2011
		boolean resultFlag	= false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM employee WHERE empid = '"+memberId.trim()+"' ";
			
			if (!status.equals("")) sqlStmt = sqlStmt + "AND status = '"+status.trim()+"' ";

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
	public String GetMemberName(String memberId) throws Exception {	//07-05-2010
		String firstName	= "";
		String lastName		= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT empid, empnamet, emplastnamet " +
			"FROM employee WHERE empid = '"+memberId.trim()+"' ";

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				memberId	= rs.getString("empid");
				firstName	= rs.getString("empnamet");	
				if (rs.getString("emplastnamet") != null) lastName = rs.getString("emplastnamet"); else lastName = "";
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
		return firstName+" "+lastName;
	}
	public String GetLastStudentNo(String memberTypeCode) throws Exception {	//17-06-2010
		String memberId	= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT memberid FROM member " +
			"WHERE membertypecode = '"+memberTypeCode.trim()+"' ORDER BY memberid DESC LIMIT 1";

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				memberId	= rs.getString("memberid");
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
		return memberId;
	}
}