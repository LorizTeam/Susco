/*
 * Created on 18-04-2012
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

import com.dtac.inventory.form.VenderForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBVender {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetVenderMasterList(String venderCode, String venderName, String searchName, String venderTypeCode,
		String status) 
	throws Exception {	//18-07-2012
		List venderList = new ArrayList();
		String custTaxId = "", creditLimit = "", creditAvail = "", venderTypeName = "";
		String pic1	=	"", pic2 = "", pic3 = "", pic4 = "",  pic5 = "" ;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.vendercode, a.vendername, a.searchname, a.vendertype, " +
			"a.taxid, a.creditlimit, a.creditavail, a.status, b.thdesc AS vendertypename, " +
			"a.pic1, a.pic2, a.pic3, a.pic4, a.pic5 " +
			"FROM mmvendermaster a " +
			"LEFT JOIN mastertabledt b ON (b.grpcode = 'vdty' AND a.vendertype = b.typecode) " +
			"WHERE ";			
			
			if (!venderCode.equals("")) sqlStmt = sqlStmt + "a.vendercode like '%"+venderCode+"%' AND ";
			if (!venderName.equals("")) sqlStmt = sqlStmt + "a.vendername like '%"+venderName+"%' AND ";
			if (!searchName.equals("")) sqlStmt = sqlStmt + "a.searchname like '%"+searchName+"%' AND ";
			if (!venderTypeCode.equals("")) sqlStmt = sqlStmt + "a.vendertype like '%"+venderTypeCode+"%' AND ";
			if (!status.equals("")) 	sqlStmt = sqlStmt + "a.status = '"+status+"' AND ";
			
			sqlStmt = sqlStmt + "a.status <> '' ORDER BY a.vendercode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				venderCode	= rs.getString("vendercode");
				status		= rs.getString("status");
				if (rs.getString("vendername") != null) venderName = rs.getString("vendername"); else venderName = "";				
				if (rs.getString("searchname") != null) searchName = rs.getString("searchname"); else searchName = "";
				if (rs.getString("vendertype")!=null) venderTypeCode = rs.getString("vendertype"); else venderTypeCode = "";
				if (rs.getString("vendertypename")!=null) venderTypeName = rs.getString("vendertypename"); else venderTypeName = "";
				if (rs.getString("taxid") != null) custTaxId = rs.getString("taxid"); else custTaxId = "";
				if (rs.getString("creditlimit") != null) creditLimit = rs.getString("creditlimit"); else creditLimit = "";
				if (rs.getString("creditavail") != null) creditAvail = rs.getString("creditavail"); else creditAvail = "";
				if (rs.getString("pic1")!= null) pic1 = rs.getString("pic1"); else pic1 = "";
				if (rs.getString("pic2")!= null) pic2 = rs.getString("pic2"); else pic2 = "";
				if (rs.getString("pic3")!= null) pic3 = rs.getString("pic3"); else pic3 = "";
				if (rs.getString("pic4")!= null) pic4 = rs.getString("pic4"); else pic4 = "";
				if (rs.getString("pic5")!= null) pic5 = rs.getString("pic5"); else pic5 = "";
				
				venderList.add(new VenderForm(venderCode, venderName, searchName, venderTypeCode, venderTypeName,
					custTaxId, creditLimit, creditAvail, status, pic1 ,pic2 ,pic3, pic4, pic5));
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
		return venderList;
	}
	public List GetVenderMaster(String venderCode) throws Exception {	//18-07-2012
		List venderList = new ArrayList();
		String venderName  = "", searchName	= "", venderTypeCode= "", venderTypeName = "";
		String venderTaxId = "", creditLimit = "", creditAvail = "", status = "";
		String pic1	=	"", pic2 = "", pic3 = "", pic4 = "",  pic5 = "" ;		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.vendercode, a.vendername, a.searchname, a.vendertype, " +
			"a.taxid, a.creditlimit, a.creditavail, a.status, b.thdesc AS vendertypename, " +
			"a.pic1, a.pic2, a.pic3, a.pic4,a.pic5 " +
			"FROM mmvendermaster a " +
			"LEFT JOIN mastertabledt b ON (b.grpcode = 'cuty' AND a.vendertype = b.typecode) " +
			"WHERE a.vendercode = '"+venderCode+"' ";			
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {				
				venderCode	= rs.getString("vendercode");
				status		= rs.getString("status");
				if (rs.getString("vendername") != null) venderName = rs.getString("vendername"); else venderName = "";				
				if (rs.getString("searchname") != null) searchName = rs.getString("searchname"); else searchName = "";
				if (rs.getString("vendertype")!=null) venderTypeCode = rs.getString("vendertype"); else venderTypeCode = "";
				if (rs.getString("vendertypename")!=null) venderTypeName = rs.getString("vendertypename"); else venderTypeName = "";
				if (rs.getString("taxid") != null) venderTaxId = rs.getString("taxid"); else venderTaxId = "";
				if (rs.getString("creditlimit") != null) creditLimit = rs.getString("creditlimit"); else creditLimit = "";
				if (rs.getString("creditavail") != null) creditAvail = rs.getString("creditavail"); else creditAvail = "";
				if (rs.getString("pic1")!= null) pic1 = rs.getString("pic1"); else pic1 = "";
				if (rs.getString("pic2")!= null) pic2 = rs.getString("pic2"); else pic2 = "";
				if (rs.getString("pic3")!= null) pic3 = rs.getString("pic3"); else pic3 = "";
				if (rs.getString("pic4")!= null) pic4 = rs.getString("pic4"); else pic4 = "";
				if (rs.getString("pic5")!= null) pic5 = rs.getString("pic5"); else pic5 = "";
				
				venderList.add(new VenderForm(venderCode, venderName, searchName, venderTypeCode, venderTypeName,
					venderTaxId, creditLimit, creditAvail, status, pic1 ,pic2 ,pic3, pic4, pic5));
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
		return venderList;
	}
	public void AddVenderMaster(String venderCode, String venderName, String searchName) 
	throws Exception {	//18-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmvendermaster " +
			"(vendercode, vendername, searchname, vendertype, creditlimit, creditavail, taxid) " +
			"VALUES ('"+venderCode+"', '"+venderName+"', '"+searchName+"', '01', 0, 0, '') ";
			
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
	public boolean CheckVender(String venderCode) throws Exception { //29-04-2012
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT * FROM mmvendermaster WHERE vendercode = '"+venderCode+"' ";			
		
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
	public String GetVenderName(String venderCode) throws Exception { //29-04-2012
		String venderName = "";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT vendername FROM mmvendermaster WHERE vendercode = '"+venderCode+"' ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				if (rs.getString("vendername") != null) venderName = rs.getString("vendername"); else venderName = "";
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
		return venderName;
	}
	public void UpdateVender(String venderCode, String venderName, String searchName, String venderTypeCode, 
		String venderTaxId, String creditLimit, String creditAvail, String status) 
	throws Exception { //19-07-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmvendermaster SET vendername = '"+venderName+"', searchname = '"+searchName+"', " +
			"vendertype = '"+venderTypeCode+"', taxid = '"+venderTaxId+"', " +
			"creditlimit = "+creditLimit+", creditavail = "+creditAvail+", status = '"+status+"' " +
			"WHERE vendercode = '"+venderCode+"' ";
			
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