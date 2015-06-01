/*
 * Created on 05-05-2012
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

import com.dtac.inventory.form.ChkStockForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBChkStock {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;
	DateUtil dateUtil = new DateUtil();
	
	public List GetChkStockList(String docYear, String docMonth, String wahoCode, String locaCode, 
		String lotNo, String matCode, String matName, String matTypeCode, String matGrpCode)
	throws Exception { //20-10-2012
		List chkStockList = new ArrayList();
		String  matSendName = "", matSearchName = "",serial="",
		pUnit = "" , pUnitName = "" , rUnit = "" , rUnitName = "" , iUnit = "" , iUnitName = "" , 
		matTypeName = "" , matStockType = "" , matGrpName = "" , 
		matBrandCode = "" , matBrandName = "" , matSupplCode = "" , matSupplName = "" , matColorCode = "" , matColorName = "" , 
		matStuffCode = "" , matStuffName = "" , refMatCode = "" , refMatName = "", matStatus = "" , matRemark = "" , sellPrice = "" , costPrice = "" , qtyMaxStock = "", qtyMinStock = "" , 
		updateDate = "" , updateByCode = "" , updateByName = "" , reqOrdFlag = "" , pic1 = "" , pic2 = "" , 
		wahoName = "" , locaName = "" , amntQty = "" , brrwQty = "" , alocQty = "" , avalQty = "" , chkStockQty = "", costPerUnit = "" , costAmount = "" , 
		chkStockDate = "", chkStockStatus = "", rop = "";
		String normalPrice = "", empPrice = "", vipPrice = "", specialPrice = ""; //04/06/2013
		String stock1 = "" , stock2 = "",stock3 = "" , stock4 = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.year, a.month, a.wahocode, b.wahoname, a.locacode, c.locaname, a.matcode, d.matname, " +
			"a.lotno, a.avalqty, a.chkstockqty, a.chkstockdate " +			
			"FROM mmchkstock a " +
			"JOIN mmwarehouse b ON (a.wahocode = b.wahocode) " +
			"JOIN mmwaholocation c ON (a.wahocode = c.wahocode AND a.locacode = c.locacode) " +
			"JOIN mmmaterial d ON (a.matcode = d.matcode) " +
			"WHERE a.year = '"+docYear+"' AND a.month = '"+docMonth+"' AND a.wahocode = '"+wahoCode+"' AND ";
			
			if (!locaCode.equals("")) 	sqlStmt = sqlStmt + "a.locacode = '"+locaCode+"' AND ";
			if (!matCode.equals("")) 	sqlStmt = sqlStmt + "a.matcode = '"+matCode.trim()+"' AND ";
			if (!lotNo.equals("")) 		sqlStmt = sqlStmt + "a.lotno = '"+lotNo.trim()+"' AND ";
			if (!matTypeCode.equals(""))sqlStmt = sqlStmt + "d.mattypecode = '" +matTypeCode.trim()+"' AND ";
			if (!matGrpCode.equals("")) sqlStmt = sqlStmt + "d.matgrpcode = '" +matGrpCode.trim()+"' AND ";
				
			sqlStmt = sqlStmt + "a.status = 'AC' " +
			"ORDER BY a.year, a.month, a.wahocode, a.locacode, a.matcode, a.lotno";			
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				serial	= rs.getString("serial");
				if (rs.getString("year") != null) 	docYear		= rs.getString("year"); else docYear = "";
				if (rs.getString("month") != null) 	docMonth	= rs.getString("month"); else docMonth = "";
				if (rs.getString("wahocode") != null) wahoCode	= rs.getString("wahocode"); else wahoCode = "";
				if (rs.getString("wahoname") != null) wahoName	= rs.getString("wahoname"); else wahoName = "";
				if (rs.getString("locacode") != null) locaCode	= rs.getString("locacode"); else locaCode = "";
				if (rs.getString("locaname") != null) locaName	= rs.getString("locaname"); else locaName = "";
				if (rs.getString("matcode") != null) matCode	= rs.getString("matcode"); else matCode = "";
				if (rs.getString("matname") != null) matName	= rs.getString("matname"); else matName = "";
				if (rs.getString("lotno") != null) lotNo	= rs.getString("lotno"); else lotNo = "";
				if (rs.getString("avalqty") != null) avalQty	= rs.getString("avalqty"); else avalQty = "";
				if (rs.getString("chkstockqty") != null) chkStockQty	= rs.getString("chkstockqty"); else chkStockQty = "";
				if (rs.getString("chkstockdate") != null) chkStockDate	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("chkstockdate")); else chkStockDate = "";

				chkStockList.add(new ChkStockForm(matCode,serial, matName, matSendName, matSearchName, 
						pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName, 
						matTypeCode, matTypeName, matStockType, matGrpCode, matGrpName, 
						matBrandCode, matBrandName, matSupplCode, matSupplName, matColorCode, matColorName, 
						matStuffCode, matStuffName, refMatCode, refMatName, matStatus, matRemark, sellPrice, costPrice, qtyMaxStock, qtyMinStock, 
						updateDate, updateByCode, updateByName, reqOrdFlag, pic1, pic2, docYear, docMonth, 
						normalPrice, empPrice, vipPrice, specialPrice, stock1, stock2,stock3, stock4,//04/06/2013
						wahoCode, wahoName, locaCode, locaName, lotNo, amntQty, brrwQty, alocQty, avalQty, chkStockQty, costPerUnit, costAmount, 
						chkStockDate, chkStockStatus, rop));			
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
		return chkStockList;
	}
	public List GetMatWahoList(String docYear, String docMonth, String wahoCode, String locaCode, 
		String lotNo, String matCode, String matName, String matTypeCode, String matGrpCode)
	throws Exception { //18-10-2012
		List chkStockList = new ArrayList();
		String  matSearchName = "" , matSendName = "", serial ="",
		pUnit = "" , pUnitName = "" , rUnit = "" , rUnitName = "" , iUnit = "" , iUnitName = "" , 
		matTypeName = "" , matStockType = "" , matGrpName = "" , 
		matBrandCode = "" , matBrandName = "" , matSupplCode = "" , matSupplName = "" , matColorCode = "" , matColorName = "" , 
		matStuffCode = "" , matStuffName = "" , refMatCode = "" , refMatName = "", matStatus = "" , matRemark = "" , sellPrice = "" , costPrice = "" , qtyMaxStock = "" , qtyMinStock = "" , 
		updateDate = "" , updateByCode = "" , updateByName = "" , reqOrdFlag = "" , pic1 = "" , pic2 = "" , 
		wahoName = "" , locaName = "" , amntQty = "" , brrwQty = "" , alocQty = "" , avalQty = "" , chkStockQty = "", costPerUnit = "" , costAmount = "" , 
		chkStockDate = "", chkStockStatus = "";
		String normalPrice = "", empPrice = "", vipPrice = "", specialPrice = "", stock1 = "" , stock2 = "",stock3 = "" , stock4 = "", rop = ""; //04/06/2013
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.wahocode, b.wahoname, a.locacode, c.locaname, a.matcode, d.matname, a.lotno " +
			"FROM mmmaterialwaho a " +
			"JOIN mmwarehouse b ON (a.wahocode = b.wahocode) " +
			"JOIN mmwaholocation c ON (a.wahocode = c.wahocode AND a.locacode = c.locacode) " +
			"JOIN mmmaterial d ON (a.matcode = d.matcode) " +
			"WHERE a.wahocode = '"+wahoCode+"' AND ";
			
			if (!locaCode.equals("")) 	sqlStmt = sqlStmt + "a.locacode = '"+locaCode+"' AND ";
			if (!matCode.equals("")) 	sqlStmt = sqlStmt + "a.matcode = '"+matCode.trim()+"' AND ";
			if (!lotNo.equals("")) 		sqlStmt = sqlStmt + "a.lotno = '"+lotNo.trim()+"' AND ";
			if (!matTypeCode.equals(""))sqlStmt = sqlStmt + "d.mattypecode = '" +matTypeCode.trim()+"' AND ";
			if (!matGrpCode.equals("")) sqlStmt = sqlStmt + "d.matgrpcode = '" +matGrpCode.trim()+"' AND ";
				
			sqlStmt = sqlStmt + "CONCAT(a.wahocode, a.locacode, a.matcode, a.lotno) " +
				"NOT IN (SELECT CONCAT(wahocode, locacode, matcode, lotno) FROM mmchkstock " +
				"WHERE year = '"+docYear+"' AND month = '"+docMonth+"' AND status = 'AC' ) " +
			"ORDER BY a.wahocode, a.locacode, a.matcode, a.lotno";			
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				serial	= rs.getString("serial");
				if (rs.getString("wahocode") != null) wahoCode	= rs.getString("wahocode"); else wahoCode = "";
				if (rs.getString("wahoname") != null) wahoName	= rs.getString("wahoname"); else wahoName = "";
				if (rs.getString("locacode") != null) locaCode	= rs.getString("locacode"); else locaCode = "";
				if (rs.getString("locaname") != null) locaName	= rs.getString("locaname"); else locaName = "";
				if (rs.getString("matcode") != null) matCode	= rs.getString("matcode"); else matCode = "";
				if (rs.getString("matname") != null) matName	= rs.getString("matname"); else matName = "";
				if (rs.getString("lotno") != null) lotNo	= rs.getString("lotno"); else lotNo = "";

				chkStockList.add(new ChkStockForm(matCode,serial, matName, matSendName, matSearchName, 
						pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName, 
						matTypeCode, matTypeName, matStockType, matGrpCode, matGrpName, 
						matBrandCode, matBrandName, matSupplCode, matSupplName, matColorCode, matColorName, 
						matStuffCode, matStuffName, refMatCode, refMatName, matStatus, matRemark, sellPrice, costPrice, qtyMaxStock, qtyMinStock,
						normalPrice, empPrice, vipPrice, specialPrice, //04/06/2013
						updateDate, updateByCode, updateByName, reqOrdFlag, pic1, pic2, docYear, docMonth, 
						wahoCode, wahoName, locaCode, locaName, lotNo, amntQty, brrwQty, alocQty, avalQty, chkStockQty, costPerUnit, costAmount, 
						chkStockDate, chkStockStatus ,stock1, stock2,stock3, stock4, rop));			
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
		return chkStockList;
	}	
	public void AddChkStock(String chkStockYear, String chkStockMonth, String wahoCode, String locaCode, 
		String matCode, String lotNo, String avalQty, String chkStockQty) 
	throws Exception { //25-07-2012
		try {
			conn = agent.getConnectMYSql();	

			String sqlStmt ="INSERT IGNORE INTO mmchkstock (year, month, wahocode, locacode," +
			"matcode, lotno, avalqty, chkstockqty, chkstockdate, status) " +
			"VALUES ('"+chkStockYear+"', '"+chkStockMonth+"', '"+wahoCode+"', '"+locaCode+"', " +
			"'"+matCode.trim()+"', '"+lotNo.trim()+"', "+
			Float.parseFloat(avalQty)+", "+Float.parseFloat(chkStockQty)+", " +
			"'"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"', 'AC') ";
			
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
	public void UpdateChkStockStatus(String chkStockYear, String chkStockMonth, String wahoCode, String locaCode, 
		String matCode, String lotNo, String chkStockStatus) 
	throws Exception { //18-10-2012
		try {
			conn = agent.getConnectMYSql();	

			String sqlStmt ="UPDATE mmchkstock SET status = '"+chkStockStatus+"' " +
			"WHERE year = '"+chkStockYear+"' AND month = '"+chkStockMonth+"' AND wahocode = '"+wahoCode+"' AND " +
			"locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND lotno = '"+lotNo+"' ";
			
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
	public void UpdateChkStockQty(String chkStockYear, String chkStockMonth, String wahoCode, String locaCode, 
		String matCode, String lotNo, String chkStockQty) 
	throws Exception { //19-10-2012
		try {
			conn = agent.getConnectMYSql();	

			String sqlStmt ="UPDATE mmchkstock SET chkstockqty = "+Float.parseFloat(chkStockQty)+" " +
			"WHERE year = '"+chkStockYear+"' AND month = '"+chkStockMonth+"' AND wahocode = '"+wahoCode+"' AND " +
			"locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND lotno = '"+lotNo+"' ";
			
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
	public boolean CheckExistRecord(String docYear, String docMonth, String wahoCode, String locaCode, 
		String lotNo, String matCode)
	throws Exception { //20-10-2012
		boolean result = false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM mmchkstock " +			
			"WHERE year = '"+docYear+"' AND month = '"+docMonth+"' AND wahocode = '"+wahoCode+"' AND "+
			"locacode = '"+locaCode+"' AND matcode = '"+matCode.trim()+"' AND lotno = '"+lotNo.trim()+"' ";
			
			
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
}