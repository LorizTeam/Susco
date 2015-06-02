/*
 * Created on 15-09-2011
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

import com.dtac.admin.form.MasterTableForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMasterTable {

	DBConnect agent 	= new DBConnect();
	Connection conn 	= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public List GetMasterTableHDList(String applTypeCode, String grpCode, String grpName)	
	throws Exception {	//01-10-2012
		List titles = new ArrayList();
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.grpcode, a.grpname FROM mastertablehd a WHERE " ;
			
			if (!grpCode.equals("")) 	sqlStmt = sqlStmt + "a.grpcode = '"+grpCode+"' AND ";
			if (!applTypeCode.equals("")) sqlStmt = sqlStmt + "a.apptype = '"+applTypeCode+"' AND ";
			
			sqlStmt = sqlStmt + "a.hidden = 'N' ORDER BY a.grpcode";			
		
		//	System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				grpCode		= rs.getString("grpcode");
				grpName  	= rs.getString("grpname");
				
				titles.add(new MasterTableForm(applTypeCode, grpCode, grpName));
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
		return titles;
	}
	public List GetMasterTableDTList(String grpCode, String typeCode, String typeGrp, String status)	
	throws Exception {	//30-09-2011
		List titles = new ArrayList();
		String grpName = "", thName = "", enName = "";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.grpcode, a.typecode, a.thdesc, a.endesc, a.typegrp, a.status, " +
			"b.grpname " +
			"FROM mastertabledt a " +
			"JOIN mastertablehd b ON (a.grpcode = b.grpcode) " +
			"WHERE a.grpcode = '"+grpCode+"' " ;
			
			if (!typeCode.equals("")) sqlStmt = sqlStmt + "AND a.typecode = '"+typeCode+"' ";
			
			if (!typeGrp.equals("")) sqlStmt = sqlStmt + "AND a.typegrp = '"+typeGrp+"' ";
			//else sqlStmt = sqlStmt + "AND (a.typegrp = '"+typeGrp+"' OR a.typegrp = '' OR a.typegrp IS NULL) ";
			
			if (!status.equals("")) sqlStmt = sqlStmt + "AND a.status = '"+status+"' ";
			
			sqlStmt = sqlStmt + "ORDER BY a.grpcode, a.typecode";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				grpCode		= rs.getString("grpcode");
				grpName  	= rs.getString("grpname");
				typeCode	= rs.getString("typecode");
				status		= rs.getString("status");
				if (rs.getString("thdesc") != null)	thName  = rs.getString("thdesc"); else thName = "";
				if (rs.getString("endesc") != null)	enName  = rs.getString("endesc"); else enName = "";
				
				titles.add(new MasterTableForm(grpCode, grpName, typeCode, thName, enName, status));
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
		return titles;
	}
	public String GetMasterTableTypeGrp(String grpCode, String typeCode) throws Exception {	//09-03-2012
		String grpName = "";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT a.typegrp " +			
			"FROM mastertabledt a " +
			"JOIN mastertablehd b ON (a.grpcode = b.grpcode) " +
			"WHERE a.grpcode = '"+grpCode+"' AND a.typecode = '"+typeCode+"' " ;
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				grpName		= rs.getString("typegrp");
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
		return grpName;
	}
	public void AddMasterDT(String grpCode, String typeCode, String thName, String enName, 
		String loginId) 
	throws Exception {	//23-11-2009
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO mastertabledt " +
			"(grpcode, typecode, thdesc, endesc, status, updateby, updatedt) " +
			"VALUES ('"+grpCode+"', '"+typeCode+"', '"+thName+"', '"+enName+"', 'AC', " +
			" '"+loginId+"', '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'-')+"') ";			
			
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
	public void UpdateMasterDT(String grpCode, String typeCode, String thName, String enName, 
		String status, String loginId) 
	throws Exception {	//23-11-2009
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mastertabledt SET " +
			"thdesc = '"+thName+"', endesc = '"+enName+"', status = '"+status+"', " +
			"updateby = '"+loginId+"', updatedt	= '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'-')+"' " +
			"WHERE grpcode = '"+grpCode+"' AND typecode = '"+typeCode+"' ";
			
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