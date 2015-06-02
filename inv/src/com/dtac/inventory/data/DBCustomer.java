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

import com.dtac.inventory.form.CustomerForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBCustomer {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetCustomerMasterList(String customerCode, String customerName, String searchName, String customerTypeCode,
		String status) 
	throws Exception {	//18-07-2012
		List customerList = new ArrayList();
		String custTaxId = "", creditLimit = "", creditAvail = "", customerTypeName = "";
		String pic1	=	"", pic2 = "", pic3 = "", pic4 = "",  pic5 = "" ;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.csdercode, a.csdername, a.searchname, a.csdertype, " +
			"a.cstaxid, a.creditlimit, a.creditavail, a.status, b.thdesc AS csdertypename, " +
			"a.pic1, a.pic2, a.pic3, a.pic4, a.pic5 " +
			"FROM mmcustomermaster a " +
			"LEFT JOIN mastertabledt b ON (b.grpcode = 'vdty' AND a.csdertype = b.typecode) " +
			"WHERE ";			
			
			if (!customerCode.equals("")) sqlStmt = sqlStmt + "a.csdercode like '%"+customerCode+"%' AND ";
			if (!customerName.equals("")) sqlStmt = sqlStmt + "a.csdername like '%"+customerName+"%' AND ";
			if (!searchName.equals("")) sqlStmt = sqlStmt + "a.searchname like '%"+searchName+"%' AND ";
			if (!customerTypeCode.equals("")) sqlStmt = sqlStmt + "a.csdertype like '%"+customerTypeCode+"%' AND ";
			if (!status.equals("")) 	sqlStmt = sqlStmt + "a.status = '"+status+"' AND ";
			
			sqlStmt = sqlStmt + "a.status <> '' ORDER BY a.csdercode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				customerCode	= rs.getString("csdercode");
				status		= rs.getString("status");
				if (rs.getString("csdername") != null) customerName = rs.getString("csdername"); else customerName = "";				
				if (rs.getString("searchname") != null) searchName = rs.getString("searchname"); else searchName = "";
				if (rs.getString("csdertype")!=null) customerTypeCode = rs.getString("csdertype"); else customerTypeCode = "";
				if (rs.getString("csdertypename")!=null) customerTypeName = rs.getString("csdertypename"); else customerTypeName = "";
				if (rs.getString("cstaxid") != null) custTaxId = rs.getString("cstaxid"); else custTaxId = "";
				if (rs.getString("creditlimit") != null) creditLimit = rs.getString("creditlimit"); else creditLimit = "";
				if (rs.getString("creditavail") != null) creditAvail = rs.getString("creditavail"); else creditAvail = "";
				if (rs.getString("pic1")!= null) pic1 = rs.getString("pic1"); else pic1 = "";
				if (rs.getString("pic2")!= null) pic2 = rs.getString("pic2"); else pic2 = "";
				if (rs.getString("pic3")!= null) pic3 = rs.getString("pic3"); else pic3 = "";
				if (rs.getString("pic4")!= null) pic4 = rs.getString("pic4"); else pic4 = "";
				if (rs.getString("pic5")!= null) pic5 = rs.getString("pic5"); else pic5 = "";
				
				customerList.add(new CustomerForm(customerCode, customerName, searchName, customerTypeCode, customerTypeName,
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
		return customerList;
	}
	public List GetCustomerMaster(String customerCode) throws Exception {	//18-07-2012
		List CustomerList = new ArrayList();
		String customerName  = "", searchName	= "", customerTypeCode= "", customerTypeName = "";
		String customerTaxId = "", creditLimit = "", creditAvail = "", status = "";
		String pic1	=	"", pic2 = "", pic3 = "", pic4 = "",  pic5 = "" ;		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.csdercode, a.csdername, a.searchname, a.csdertype, " +
			"a.cstaxid, a.creditlimit, a.creditavail, a.status, b.thdesc AS csdertypename, " +
			"a.pic1, a.pic2, a.pic3, a.pic4,a.pic5 " +
			"FROM mmcustomermaster a " +
			"LEFT JOIN mastertabledt b ON (b.grpcode = 'cuty' AND a.csdertype = b.typecode) " +
			"WHERE a.csdercode = '"+customerCode+"' ";			
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {				
				customerCode	= rs.getString("vendercode");
				status		= rs.getString("status");
				if (rs.getString("customername") != null) customerName = rs.getString("customername"); else customerName = "";				
				if (rs.getString("searchname") != null) searchName = rs.getString("searchname"); else searchName = "";
				if (rs.getString("customertype")!=null) customerTypeCode = rs.getString("customertype"); else customerTypeCode = "";
				if (rs.getString("customertypename")!=null) customerTypeName = rs.getString("customertypename"); else customerTypeName = "";
				if (rs.getString("cstaxid") != null) customerTaxId = rs.getString("cstaxid"); else customerTaxId = "";
				if (rs.getString("creditlimit") != null) creditLimit = rs.getString("creditlimit"); else creditLimit = "";
				if (rs.getString("creditavail") != null) creditAvail = rs.getString("creditavail"); else creditAvail = "";
				if (rs.getString("pic1")!= null) pic1 = rs.getString("pic1"); else pic1 = "";
				if (rs.getString("pic2")!= null) pic2 = rs.getString("pic2"); else pic2 = "";
				if (rs.getString("pic3")!= null) pic3 = rs.getString("pic3"); else pic3 = "";
				if (rs.getString("pic4")!= null) pic4 = rs.getString("pic4"); else pic4 = "";
				if (rs.getString("pic5")!= null) pic5 = rs.getString("pic5"); else pic5 = "";
				
				CustomerList.add(new CustomerForm(customerCode, customerName, searchName, customerTypeCode, customerTypeName,
					customerTaxId, creditLimit, creditAvail, status, pic1 ,pic2 ,pic3, pic4, pic5));
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
		return CustomerList;
	}
	public void AddCustomerMaster(String customerCode, String customerName, String searchName) 
	throws Exception {	//18-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmcustomermaster " +
			"(csdercode, csdername, searchname, csdertype, creditlimit, creditavail, cstaxid) " +
			"VALUES ('"+customerCode+"', '"+customerName+"', '"+searchName+"', '01', 0, 0, '0') ";
			
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
	
	public String GetCustomerName(String custcode) throws Exception { //29-04-2012
		String custname = "";
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT csdername FROM mmcustomermaster WHERE csdercode = '"+custcode+"' ";			
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				if (rs.getString("csdername") != null) custname = rs.getString("csdername"); else custname = "";
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
		return custname;
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