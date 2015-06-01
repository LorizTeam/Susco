/*
 * Created on 12-12-2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.employee.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dtac.utils.DBConnect;

 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBTaxTemp {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement pStmt = null;
	ResultSet rs	= null;
	
	public boolean CheckTaxId(String idPop) throws Exception {	//15-12-2011
		boolean resultFlag	= false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM paytaxtemp WHERE idpop = '"+idPop.trim()+"' ";
			
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
	public void AddTaxTemp(String idPop, String empID, String marryStatus, String edcChild, String allChild,
		String deductType, String devote, String insure, String interestLoan) 
	throws Exception { //10-12-2011
		try {
			String taxMarry = marryStatus;			
			if (marryStatus.equals("1") || marryStatus.equals("2")) taxMarry = "1";
			else if (marryStatus.equals("3")) taxMarry = "2";
			
			String marryDoc = "1";
			if (marryStatus.equals("1"))  marryDoc = "4";
			else if (marryStatus.equals("2"))  marryDoc = "3";
			
			int edcCh = Integer.parseInt(edcChild); if (edcCh > 3) edcCh = 3;
			int nonCh = Integer.parseInt(allChild); if (edcCh + nonCh > 3) nonCh = 3 - edcCh;
			float deduP= Float.parseFloat(deductType) * 30000;
			float insuP= Float.parseFloat(insure);
			float devoP= Float.parseFloat(devote);
			float inteP= Float.parseFloat(interestLoan);
			
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO paytaxtemp (idpop, empid, marrystatus, marrydoc, a11, c1, c31no, c32no," +
					"c31, c32, c4, c6, c10) " +
			"VALUES ('"+idPop.trim()+"', '"+empID.toUpperCase()+"', '"+taxMarry+"', '"+marryDoc+"', "+devoP+", 30000, "+
			nonCh+", "+edcCh+", " +
			""+nonCh*15000+", "+edcCh*17000+", "+deduP+", "+insuP+", "+inteP+" )";

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
	public String GetTaxTemp(String idPop, String netIncome) throws Exception { //10-12-2011
		String tax = "0";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE paytaxtemp SET a1 = 12 * "+netIncome+" " +					
			"WHERE idpop = '"+idPop.trim()+"' ";
							
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	
			//=================================
			sqlStmt = "UPDATE paytaxtemp SET a3 = a1 - a2, a4 = 0.4 * (a1 - a2) " +					
			"WHERE idpop = '"+idPop+"'";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================
			sqlStmt = "UPDATE paytaxtemp SET a4 = 60000  WHERE idpop = '"+idPop+"' AND a4 > 60000 ";				
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================
			sqlStmt = "UPDATE paytaxtemp SET a5 = a3 - a4, a7 = a3 - a4 - a6, a10 = a3 - a4 - a6 " +
			"WHERE idpop = '"+idPop+"' ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================
			sqlStmt = "UPDATE paytaxtemp SET a11 = 0.1 * a10 WHERE idpop = '"+idPop+"' AND a11 > (0.1 * a10) ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================
			sqlStmt = "UPDATE paytaxtemp SET a12 = a10 - a11 WHERE idpop = '"+idPop+"' ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//================Cal Tax==================
			sqlStmt = "UPDATE paytaxtemp SET a13 = 0 WHERE idpop = '"+idPop+"' ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			sqlStmt = "UPDATE paytaxtemp SET a13 = 0 WHERE idpop = '"+idPop+"' AND a12 < 150000 ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			sqlStmt = "UPDATE paytaxtemp SET a13 = 0.1 * (a12 - 150000) " +
			"WHERE idpop = '"+idPop+"' AND a12 >= 150000 AND a12 < 500000 ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			sqlStmt = "UPDATE paytaxtemp SET a13 = (0.2 * (a12 - 500000)) + 35000 " +
			"WHERE idpop = '"+idPop+"' AND a12 >= 500000 AND a12 < 1000000 ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			sqlStmt = "UPDATE paytaxtemp SET a13 = (0.3 * (a12 - 1000000)) + 135000 " +
			"WHERE idpop = '"+idPop+"' AND a12 >= 1000000 AND a12 < 4000000 ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			//=============get tax================
			sqlStmt = "SELECT a13/12 FROM paytaxtemp WHERE idpop = '"+idPop.trim()+"' ";					
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				tax = rs.getString(1);			
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
		return tax;
	}	
	public void CalTaxTemp(String idPop, String empID) throws Exception { //14-12-2011
		try {			
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE paytaxtemp a, employee b SET a.c11 = b.social * 12, a.c7 = b.provfund * 12, " +
			"a.a1 = 12 * (b.salary + b.costlive + b.oil + b.other) " +
			"WHERE a.idpop = '"+idPop+"' AND a.idpop = b.idpop AND b.empid = '"+empID+"' ";
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	
			//==================================
			sqlStmt = "UPDATE paytaxtemp  SET b1 = 0 WHERE idpop = '"+idPop+"' AND c7 <= 10000";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==================================
			sqlStmt = "UPDATE paytaxtemp  SET b1 = c7 - 10000, c7 = 10000 WHERE idpop = '"+idPop+"' AND c7 > 10000";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);

			//==============Marry====================
			sqlStmt = "UPDATE paytaxtemp SET c2 = 0 WHERE idpop = '"+idPop+"'  ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==================================
			sqlStmt = "UPDATE paytaxtemp a, employee b SET a.c2 = 30000 " +
			"WHERE a.idpop = '"+idPop+"' AND a.idpop = b.idpop AND b.empid = '"+empID+"' AND b.marrystatus = '2' ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);

			//==================================
			sqlStmt = "UPDATE paytaxtemp SET bsum = b1, a2 = bsum, a3 = a1 - bsum, a4 = 0.4 * (a1 - bsum), " +
			"csum = c1 + c2 + c31 + c32 + c4 + c6 + c7 + c10 + c11 " +					
			"WHERE idpop = '"+idPop+"'";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================
			sqlStmt = "UPDATE paytaxtemp SET a4 = 60000  WHERE idpop = '"+idPop+"' AND a4 > 60000 ";				
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================
			sqlStmt = "UPDATE paytaxtemp SET a5 = a3 - a4, a6 = csum, a7 = a3 - a4 - csum, a10 = a3 - a4 - csum " +
			"WHERE idpop = '"+idPop+"' ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================
			sqlStmt = "UPDATE paytaxtemp SET a11 = 0.1 * a10 WHERE idpop = '"+idPop+"' AND a11 > (0.1 * a10) ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================
			sqlStmt = "UPDATE paytaxtemp SET a12 = a10 - a11 WHERE idpop = '"+idPop+"' ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//================Cal Tax==================
			sqlStmt = "UPDATE paytaxtemp SET a13 = 0 WHERE idpop = '"+idPop+"' ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			sqlStmt = "UPDATE paytaxtemp SET a13 = 0 WHERE idpop = '"+idPop+"' AND a12 < 150000 ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			sqlStmt = "UPDATE paytaxtemp SET a13 = 0.1 * (a12 - 150000) " +
			"WHERE idpop = '"+idPop+"' AND a12 >= 150000 AND a12 < 500000 ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			sqlStmt = "UPDATE paytaxtemp SET a13 = (0.2 * (a12 - 500000)) + 35000 " +
			"WHERE idpop = '"+idPop+"' AND a12 >= 500000 AND a12 < 1000000 ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			sqlStmt = "UPDATE paytaxtemp SET a13 = (0.3 * (a12 - 1000000)) + 135000 " +
			"WHERE idpop = '"+idPop+"' AND a12 >= 1000000 AND a12 < 4000000 ";
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			

			//================Update Tax==================
			sqlStmt = "UPDATE paytaxtemp a, employee b SET b.pinctax = a13 / 12 " +				
			"WHERE a.idpop = '"+idPop+"' AND a.idpop = b.idpop AND b.empid = '"+empID+"' ";			
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