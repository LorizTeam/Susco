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

import com.dtac.inventory.form.ProductForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBProduct {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetProductMasterList(String productCode, String productName, String price, String status) 
	throws Exception {	//18-07-2012
		List productList = new ArrayList();
	 
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.matgrpcode, a.matgrpname, a.price, a.matgrpstatus  " +
		 
			" FROM mmmatgrp a  WHERE  "; 						
			
			if (!productCode.equals("")) sqlStmt = sqlStmt + "a.matgrpcode like '%"+productCode+"%' AND ";
			if (!productName.equals("")) sqlStmt = sqlStmt + "a.matgrpname like '%"+productName+"%' AND "; 
			if (!status.equals("")) 	sqlStmt = sqlStmt + "a.matgrpstatus = '"+status+"' AND";
			
			sqlStmt = sqlStmt + " a.matgrpstatus <> '' ORDER BY a.matgrpcode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				productCode	= rs.getString("matgrpcode");
				status		= rs.getString("matgrpstatus");
				if (rs.getString("matgrpname") != null) productName = rs.getString("matgrpname"); else productName = "";				
				if (rs.getString("price") != null) price = rs.getString("price"); else price = "";
				if (rs.getString("matgrpstatus")!=null) status = rs.getString("matgrpstatus"); else status = "";
	 

				
				productList.add(new ProductForm(productCode, productName, price, status ));
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
		return productList;
	}
	public List GetProductList(String productCode) 
	throws Exception {	//18-07-2012
		List productList = new ArrayList();
	 
		try {
			conn = agent.getConnectMYSql();
		String	productName = "",price = "",status = "";
			String sqlStmt = "SELECT a.matgrpcode, a.matgrpname, a.price, a.matgrpstatus  " +
		 
			" FROM mmmatgrp a  WHERE  "; 						
			
			if (!productCode.equals("")) sqlStmt = sqlStmt + "a.matgrpcode like '%"+productCode+"%' AND ";
	 
			sqlStmt = sqlStmt + " a.matgrpstatus <> '' ORDER BY a.matgrpcode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				productCode	= rs.getString("matgrpcode");
				status		= rs.getString("status");
				if (rs.getString("matgrpname") != null) productName = rs.getString("matgrpname"); else productName = "";				
				if (rs.getString("price") != null) price = rs.getString("price"); else price = "";
				if (rs.getString("status")!=null) status = rs.getString("status"); else status = "";
	 

				
				productList.add(new ProductForm(productCode, productName, price, status ));
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
		return productList;
	} 
	public void AddProductMaster(String productCode, String productName, String price) 
	throws Exception {	//18-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmmatgrp " +
			"(matgrpcode, matgrpname, price, matgrpstatus) " +
			"VALUES ('"+productCode+"', '"+productName+"', '"+price+"', 'AC') ";
			
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