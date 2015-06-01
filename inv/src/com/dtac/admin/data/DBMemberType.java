/*
 * Created on 30-03-2012
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

import com.dtac.admin.form.MemberTypeForm;
import com.dtac.utils.DBConnect;

 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMemberType {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetMemberTypeList()	throws Exception {	//26-09-2011
		List memberTypes = new ArrayList();		
		String memberTypeCode	= "";
		String memberTypeName	= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.typecode, a.typename, a.typeempname FROM emptype a "+
			"ORDER BY a.typecode ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				memberTypeCode	= rs.getString("typecode");
				memberTypeName	= rs.getString("typename") + " ("+rs.getString("typeempname")+")";
				
				memberTypes.add(new MemberTypeForm(memberTypeCode, memberTypeName));
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
		return memberTypes;
	}
	public List GetMemberTypeList(String memberGrpCode, String memberTypeCode, String memberTypeName)	
	throws Exception {	//29-09-2011 
		//membergrpcode 0 - Dairy, 1 - Monthly 
		List memberTypes = new ArrayList();		
		String memberGrpName= "", punchCardStatus	= "";
		String timeStart	= "", timeStop	= "", timeLate	= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.typecode, a.typename, a.typeemp, b.saltypename, " +
			"b.timestart, b.timelate, b.timestop " +
			"FROM emptype a " +
			"JOIN saltype b ON (a.typeemp = b.saltypecode) WHERE ";
			
			if (!memberGrpCode.equals("")) sqlStmt = sqlStmt + "a.typeemp = '"+memberGrpCode+"' AND ";
			if (!memberTypeCode.equals("")) sqlStmt = sqlStmt + "a.typecode = '"+memberTypeCode+"' AND ";
			if (!memberTypeName.equals("")) sqlStmt = sqlStmt + "a.typename = '"+memberTypeName+"' AND ";
			
			sqlStmt = sqlStmt + "a.typecode <> '' ORDER BY a.typecode ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				memberGrpCode	= rs.getString("typeemp"); // 0 - Dairy, 1 - Monthly
				memberGrpName	= rs.getString("saltypename");
				memberTypeCode	= rs.getString("typecode");
				memberTypeName	= rs.getString("typename");
				
				if (rs.getString("timestart") != null) timeStart = rs.getString("timestart"); else timeStart = "";
				if (rs.getString("timelate") != null)  timeLate  = rs.getString("timelate");  else timeLate  = "";
				if (rs.getString("timestop") != null)  timeStop  = rs.getString("timestop");  else timeStop  = "";
				
				memberTypes.add(new MemberTypeForm(memberTypeCode, memberTypeName, 
					memberGrpCode, memberGrpName, punchCardStatus, timeStart, timeStop, timeLate));
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
		return memberTypes;
	}
	public String GetMemberTypeName(String memberTypeCode)	
	throws Exception {	//27-09-2011
		String memberTypeName	= "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.typename FROM emptype a WHERE a.typecode = '"+memberTypeCode+"' ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				memberTypeName	= rs.getString("typename");
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
		return memberTypeName;
	}
}