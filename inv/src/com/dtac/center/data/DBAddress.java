/*
 * Created on 18-04-2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.center.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.dtac.center.form.AddressForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBAddress {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetAddressList(String tableName, String idCode, String addrType, String addrNo) 
	throws Exception {	//12-05-2012
		List addrList = new ArrayList(); 
		String addr1 = "", addr2 = "", addr3 = ""; 
		String cont1 = "", posi1 = "", tel1 = "", mob1 = "", fax1 = "", email1 = "";
		String cont2 = "", posi2 = "", tel2 = "", mob2 = "", fax2 = "", email2 = "";
		String remark= "", status= "", pic1 = "", qty= "0";
		try {
			DecimalFormat df1 = new DecimalFormat("#0.##");
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.tablename, a.idcode, a.addrtype, a.addrno, " +
			"a.addr1, a.addr2, a.addr3, a.cont1, a.tel1, a.posi1, a.mob1, a.fax1, a.email1, a.cont2, a.tel2, a.posi2, a.mob2, a.fax2, a.email2, " +
			"a.remark, a.status, a.pic1, a.qty " +
			"FROM masteraddress a " +
			"WHERE a.tablename = '"+tableName+"' AND a.idcode = '"+idCode+"' AND a.addrtype = '"+addrType+"' ";			
			
			if (!addrNo.equals("")) sqlStmt = sqlStmt + "AND a.addrno = '"+addrNo+"' ";
			
			//sqlStmt = sqlStmt + "ORDER BY a.addrno DESC ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				addrNo = rs.getString("addrno");
								
				if (rs.getString("addr1") != null) addr1 = rs.getString("addr1"); else addr1 = "";
				if (rs.getString("addr2") != null) addr2 = rs.getString("addr2"); else addr2 = "";
				if (rs.getString("addr3") != null) addr3 = rs.getString("addr3"); else addr3 = "";
				if (rs.getString("cont1") != null) cont1 = rs.getString("cont1"); else cont1 = "";
				if (rs.getString("posi1") != null) posi1 = rs.getString("posi1"); else posi1 = "";
				if (rs.getString("tel1") != null) tel1 = rs.getString("tel1"); else tel1 = "";
				if (rs.getString("mob1") != null) mob1 = rs.getString("mob1"); else mob1 = "";
				if (rs.getString("fax1") != null) fax1= rs.getString("fax1"); else fax1 = "";
				if (rs.getString("email1") !=null)email1= rs.getString("email1"); else email1 = "";
				
				if (rs.getString("cont2") != null) cont2 = rs.getString("cont2"); else cont2 = "";
				if (rs.getString("posi2") != null) posi2 = rs.getString("posi2"); else posi2 = "";
				if (rs.getString("tel2") != null) tel2 = rs.getString("tel2"); else tel2 = "";
				if (rs.getString("mob2") != null) mob2 = rs.getString("mob2"); else mob2 = "";
				if (rs.getString("fax2") != null) fax2= rs.getString("fax2"); else fax2 = "";
				if (rs.getString("email2") !=null)email2 = rs.getString("email2"); else email2 = "";
				
				if (rs.getString("remark") != null) remark = rs.getString("remark"); else remark = "";
				if (rs.getString("pic1") != null) pic1 = rs.getString("pic1"); else pic1 = "";
				
				status = rs.getString("status");
				qty = df1.format(Float.parseFloat(rs.getString("qty")));
				
				addrList.add(new AddressForm(tableName, idCode, addrType, addrNo, addr1, addr2, addr3,
					cont1, posi1, tel1, mob1, fax1, email1, 
					cont2, posi2, tel2, mob2, fax2, email2, 
					remark, status, pic1, qty));
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
		return addrList;
	}
	public void AddAddress(String tableName, String idCode, String addrType, String addrNo, 
		String addr1, String addr2, String addr3, 
		String cont1, String posi1, String tel1, String mob1, String fax1, String email1, 
		String cont2, String posi2, String tel2, String mob2, String fax2, String email2, 
		String remark, String status, String qty) 
	throws Exception {	//28-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO masteraddress (tablename, idcode, addrtype, addrno, " +
			"addr1, addr2, addr3, status, qty, remark, " +
			"cont1, posi1, tel1, mob1, fax1, email1, " +
			"cont2, posi2, tel2, mob2, fax2, email2) " +
			"VALUES ('"+tableName+"', '"+idCode+"', '"+addrType+"', '"+addrNo+"', " +
			"'"+addr1+"', '"+addr2+"', '"+addr3+"', '"+status+"', "+Float.parseFloat(qty)+", '"+remark+"', " +
			"'"+cont1+"', '"+posi1+"', '"+tel1+"', '"+mob1+"', '"+fax1+"', '"+email1+"', " +
			"'"+cont2+"', '"+posi2+"', '"+tel2+"', '"+mob2+"', '"+fax2+"', '"+email2+"') ";
			
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
	public boolean CheckAddress(String tableName, String idCode, String addrType, String addrNo) 
	throws Exception { //29-04-2012
		boolean result 	= false;
		try {
			conn = agent.getConnectMYSql();
		
			String sqlStmt = "SELECT * FROM masteraddress " +
			"WHERE tablename = '"+tableName+"' AND idcode = '"+idCode+"' AND addrtype = '"+addrType+"' " +
			"AND addrno = '"+addrNo+"' ";
		
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
	public void UpdateAddress(String tableName, String idCode, String addrType, String addrNo,
		String addr1, String addr2, String addr3, 
		String cont1, String posi1, String tel1, String mob1, String fax1, String email1, 
		String cont2, String posi2, String tel2, String mob2, String fax2, String email2, 
		String remark, String status, String qty) 
	throws Exception {	//28-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE masteraddress SET addr1 = '"+addr1+"', addr2 = '"+addr2+"', " +
			"addr3 = '"+addr3+"', " +
			"cont1 ='"+cont1+"', tel1 ='"+tel1+"', posi1 = '"+posi1+"', fax1 = '"+fax1+"', email1 = '"+email1+"', " +
			"mob1 = '"+mob1+"', " +
			"cont2 ='"+cont2+"', tel2 ='"+tel2+"', posi2 = '"+posi2+"', fax2 = '"+fax2+"', email2 = '"+email2+"', " +
			"mob2 = '"+mob2+"', remark= '"+remark+"', status = '"+status+"', qty = "+Float.parseFloat(qty)+" " +
			"WHERE tablename = '"+tableName+"' AND idcode = '"+idCode+"' AND addrtype = '"+addrType+"' " +
			"AND addrno = '"+addrNo+"'";
				
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
	public String GetLastAddrNo(String tableName, String idCode, String addrType) throws Exception { //10-05-2012
		String addrNo = "0";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT addrno FROM masteraddress " +
			"WHERE tablename = '"+tableName+"' AND idcode = '"+idCode+"' AND addrtype = '"+addrType+"' " +
			"ORDER BY addrno DESC LIMIT 1";

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				addrNo	= rs.getString("addrno");
			}
			addrNo	= String.valueOf(Integer.parseInt(addrNo)+1);
			if (addrNo.length() == 1) addrNo = "0" + addrNo;
						
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
		return addrNo;
	}
	public String GetAddrNo(String moveRecType, String addrNo, int tAddrSize) throws Exception { //20-11-2012
		//System.out.println("tAddrSize"+tAddrSize+"    addrNo"+addrNo);
		try {
			if (moveRecType.equals("first")) {
				addrNo = "01";
				
			} else if (moveRecType.equals("prev")) {				
				if (Integer.parseInt(addrNo) <= 1) addrNo = "1"; 
				else
					addrNo	= String.valueOf(Integer.parseInt(addrNo)-1);
			} else if (moveRecType.equals("next")) {				
				if (Integer.parseInt(addrNo) <= tAddrSize) addrNo	= String.valueOf(Integer.parseInt(addrNo)+1);
				
			} else if (moveRecType.equals("last")) {
				addrNo = String.valueOf(tAddrSize);
			}
			
			if (addrNo.length() == 1) addrNo = "0" + addrNo;
						
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		return addrNo;
	}
}