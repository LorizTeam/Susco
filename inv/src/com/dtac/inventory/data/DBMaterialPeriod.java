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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.dtac.inventory.form.MaterialPeriodForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMaterialPeriod {

	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public List GetMaterialPeriodList(String docYear, String docMonth, String matCode, String matName, 
		String matTypeCode) 
	throws Exception {	//14-07-2012
		List matPeriod = new ArrayList();
		String wahoName="",wahoStatus="", initQty = "", initAmount = "", recvQty = "", recvAmount = "";
		String itrdQty="", serial="",
		iprdQty="",  alocQty="",  
		 lastRecvDate="",  lastIssuDate="",  prodWahoStatus="",  matSearchName="",  pUnit="",  pUnitName="",  rUnit="",
		 rUnitName="",  iUnit="",  iUnitName="", 
		 matTypeName="",  matStockType="",
		 matGrpCode="",  matGrpName="",  matStatus="",
		 matRemark="",  sellPrice="",  costPrice="", 
		 qtyMinStock="",  updateDate="",  updateByCode="",
		 updateByName="", matBrandCode="", matBrandName="", matSupplCode="", matSupplName="",
			matColorCode="", matColorName="", matStuffCode="", matStuffName="", refMatCode="", refMatName = "",
			reqOrdFlag="", pic1="", pic2="",locaCode="", locaName="", lotNo="",
			amntQty="",matWahoStatus="";
		String issuQty = "", issuAmount = "", brrwQty = "", avalQty = "", lastQty = "", lastAmount = "";
		String lastPRDate = "", statusPR = "", lastPODate = "", statusPO = "", qtyMaxStock = "" ;
		String normalPrice = "", empPrice = "", vipPrice = "", specialPrice = ""; //04/06/2013
		String stock1 = "" , stock2 = "",stock3 = "" , stock4 = "", rop = "", matSendName= "";
		try {
			DecimalFormat df1 = new DecimalFormat("#,###,##0.##");
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.year, a.month, a.matcode, a.initqty, a.initamount, a.recvqty, a.recvamount, " +
			"a.issuqty, a.issuamount, a.brrwqty, a.avalqty, a.lastqty, a.lastamount, " +
			"b.matname, b.qtyminstock, b.lastprdate  " +
			"FROM mmmaterialperiod a " +			
			"JOIN mmmaterial b ON (a.matcode = b.matcode) " +							
			"WHERE ";

			if (!docYear.equals(""))	sqlStmt = sqlStmt + "a.year = '"+docYear+"' AND ";			
			if (!docMonth.equals(""))	sqlStmt = sqlStmt + "a.month = '"+docMonth+"' AND ";			
			if (!matCode.equals("")) 	sqlStmt = sqlStmt + "a.matcode = '"+matCode.trim()+"' AND ";
			if (!matTypeCode.equals(""))sqlStmt = sqlStmt + "b.mattypecode = '"+matTypeCode+"' AND ";			
			
			sqlStmt = sqlStmt + "a.matcode <> '' ORDER BY a.year, a.month, a.matcode" ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				docYear		= rs.getString("year");
				docMonth	= rs.getString("month");
				matCode		= rs.getString("matcode");
				serial  = rs.getString("serial");
				if (rs.getString("matname") != null) matName	= rs.getString("matname"); else matName = "";
				if (rs.getString("qtyminstock") != null) qtyMinStock = rs.getString("qtyminstock"); else qtyMinStock = "0";
				initQty 	= df1.format(Float.parseFloat(rs.getString("initqty")));
				initAmount	= df1.format(Float.parseFloat(rs.getString("initamount")));
				recvQty		= df1.format(Float.parseFloat(rs.getString("recvqty")));
				recvAmount	= df1.format(Float.parseFloat(rs.getString("recvamount")));				
				issuQty		= df1.format(Float.parseFloat(rs.getString("issuqty")));
				issuAmount	= df1.format(Float.parseFloat(rs.getString("issuamount")));
				brrwQty		= df1.format(Float.parseFloat(rs.getString("brrwqty")));
				avalQty 	= rs.getString("avalqty");
				lastQty		= df1.format(Float.parseFloat(rs.getString("lastqty")));
				lastAmount	= df1.format(Float.parseFloat(rs.getString("lastamount")));				
								
				statusPR = "N";
				if (Float.parseFloat(qtyMinStock) > Float.parseFloat(avalQty)) statusPR = "Y";
				
				avalQty 	= df1.format(Float.parseFloat(avalQty));
				qtyMinStock	= df1.format(Float.parseFloat(qtyMinStock));
				if (rs.getString("lastprdate") != null)
					lastPRDate	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("lastprdate")); else lastPRDate = "";
				
				matPeriod.add(new MaterialPeriodForm(matCode,serial, matName, matSendName,	matSearchName, 
					pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName,
					matTypeCode, matTypeName, matStockType, matGrpCode, matGrpName, 
					matBrandCode, matBrandName, matSupplCode, matSupplName,
					matColorCode, matColorName, matStuffCode, matStuffName, refMatCode, refMatName,
					matStatus, matRemark, sellPrice, costPrice, qtyMaxStock,
					qtyMinStock, updateDate, updateByCode, updateByName, 
					reqOrdFlag, pic1, pic2, normalPrice, empPrice, vipPrice, specialPrice, //04/06/2013
					stock1, stock2,stock3, stock4, rop,
					docYear, docMonth, initQty,	initAmount, recvQty, recvAmount, issuQty, issuAmount,
					brrwQty, avalQty, lastQty, lastAmount,
					lastPRDate, statusPR, lastPODate, statusPO));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) 	rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return matPeriod;
	}
	public void UpdateMaterialWaho(String wahoCode, String matCode, String initQty, String recvQty, String itrdQty, String iprdQty, String brrwQty, String alocQty, String avalQty ) throws Exception {
		try {
			conn = agent.getConnectMYSql();
			int norow =0;
			
			String sqlStmt = "SELECT * FROM mmmaterialwaho WHERE matcode='"+matCode+"'";
			Statement pStmt01 = conn.createStatement();
			ResultSet rs01 = pStmt01.executeQuery(sqlStmt);
			while (rs01.next()) {
				norow = rs01.getRow();	
			}
			rs01.close();
		    pStmt01.close();
		    
			if(norow==1){
				sqlStmt = "UPDATE mmmaterialwaho SET wahocode = '"+wahoCode.trim()+"', initqty = '" +initQty.trim()+"', recvqty = '"+recvQty.trim()+"', itrdqty = '"+itrdQty.trim()+"', brrwqty = '"+brrwQty.trim()+"', alocqty = '"+alocQty.trim()+"', avalqty = '"+avalQty.trim()+"'"+
				" WHERE matcode= '"+matCode+"'";
			}
			else{
//				if(initQty=="") initQty="0";
//				if(recvQty=="") recvQty="0";
//				if(itrdQty=="") itrdQty="0";
//				if(iprdQty=="") iprdQty="0";
//				if(brrwQty=="") brrwQty="0";
//				if(alocQty=="") alocQty="0";
//				if(avalQty=="") avalQty="0";
				sqlStmt ="INSERT INTO mmmaterialwaho (wahocode, matcode, initqty, recvqty, itrdqty, iprdqty, brrwqty, alocqty, avalqty)"+
				" VALUES ('"+wahoCode.trim()+"','"+matCode.trim()+"','"+initQty.trim()+"','"+recvQty.trim()+"','"+itrdQty.trim()+"','"+iprdQty.trim()+"','"+brrwQty.trim()+"','"+alocQty.trim()+"','"+avalQty.trim()+"')";
			}
			
			System.out.println(sqlStmt);				
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
	public void AddMaterialPeriod(String docYear, String docMonth, String matCode) 
	throws Exception { //01-07-2012
		try {
			conn = agent.getConnectMYSql();	

			String sqlStmt ="INSERT INTO mmmaterialperiod (year, month, matcode) " +
			"VALUES ('"+docYear+"', '"+docMonth+"', '"+matCode+"') ";
			
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
	public void CalAvalQty(String docYear, String docMonth, String matCode) 
	throws Exception { //13-07-2012		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialperiod SET avalqty = (initqty+recvqty-issuqty-brrwqty-alocqty) "+
			"WHERE year = '"+docYear+"' AND month = '"+docMonth+"' ";
			
			if (!matCode.equals("")) sqlStmt = sqlStmt + "AND matcode = '"+matCode+"' ";
						
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
	public void calLastQtyAndLastAmount(String docYear, String docMonth, String matCode) 
	throws Exception { //10-10-2012		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialperiod SET lastqty = avalqty, " +
			"lastamount = (initamount+recvamount-issuamount) "+
			"WHERE year = '"+docYear+"' AND month = '"+docMonth+"' ";
			
			if (!matCode.equals("")) sqlStmt = sqlStmt + "AND matcode = '"+matCode+"' ";
						
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
	public void AddAutoMaterialPeriod(String docYear, String docMonth, String monthUpdate) 
	throws Exception {	//10-10-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT IGNORE INTO mmmaterialperiod (year, month, matcode, initqty, initamount) " +
			"(SELECT year, '"+monthUpdate+"', matcode, lastqty, lastamount " +
			"FROM mmmaterialperiod " +
			"WHERE year = '"+docYear+"' AND month = '"+docMonth+"') " ;
			
			//System.out.println(sqlStmt);
		    pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			pStmt.close();
			
			sqlStmt = "UPDATE mmmaterialperiod a, mmmaterialperiod b SET a.initqty = b.lastqty, a.initamount = b.lastamount " +
			"WHERE a.year = b.year AND a.year = '"+docYear+"' AND a.month = '"+monthUpdate+"' AND b.month = '"+docMonth+"' AND a.matcode = b.matcode";
			
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
	public void updateInitQty(String docYear, String docMonth, String matCode, String initQty) 
	throws Exception { //10-10-2012		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialperiod SET initQty = "+Float.parseFloat(initQty)+" "+
			"WHERE year = '"+docYear+"' AND month = '"+docMonth+"' AND ";
			
			if (!docYear.equals(""))	sqlStmt = sqlStmt + "matcode = '"+matCode+"' AND ";
			
			sqlStmt = sqlStmt + "matcode <> ''";
			
			//System.out.print(sqlStmt);
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
	public void updateInitAmount(String docYear, String docMonth, String matCode, String initAmount) 
	throws Exception { //10-10-2012		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialperiod SET initamount = "+Float.parseFloat(initAmount)+" "+
			"WHERE year = '"+docYear+"' AND month = '"+docMonth+"' AND ";
			
			if (!docYear.equals(""))	sqlStmt = sqlStmt + "matcode = '"+matCode+"' AND ";
			
			sqlStmt = sqlStmt + "matcode <> ''";
			
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
	
	public String GetMaterialAmntQty(String wahoCode, String locaCode, String matCode, String lotNo) 
	throws Exception {
		String amntQty = "";
			
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT amntqty FROM mmmaterialwaho WHERE "+
				"wahocode='"+wahoCode+"' AND locacode='"+locaCode.trim()+"' AND matcode='"+matCode.trim()+"' AND lotno='"+lotNo.trim()+"'";
			
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			
			while (rs.next()) {

			amntQty	= rs.getString("amntQty");

			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) 	rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return amntQty;
	}	
	public int AccuRecvQty(String docYear, String docMonth, String matCode, String recvQty, String recvAmount) 
	throws Exception { //03-07-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialperiod SET recvqty = recvqty +"+Float.parseFloat(recvQty)+
	    	", recvamount = recvamount +"+Float.parseFloat(recvAmount)+
	    	" WHERE year = '"+docYear+"' AND month = '"+docMonth+"' AND matcode = '"+matCode+"' ";
	    	
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			tmp_return = pStmt.executeUpdate(sqlStmt);			
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
		return tmp_return;
	} 
	public int CancelAccuRecvQty(String docYear, String docMonth, String matCode, String recvQty, String recvAmount) 
	throws Exception { //15-07-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialperiod SET recvqty = recvqty -"+Float.parseFloat(recvQty)+
	    	", recvamount = recvamount -"+Float.parseFloat(recvAmount)+
	    	" WHERE year = '"+docYear+"' AND month = '"+docMonth+"' AND matcode = '"+matCode+"' ";
	    	
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			tmp_return = pStmt.executeUpdate(sqlStmt);			
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
		return tmp_return;
	}
	public int AccuIssuQty(String docYear, String docMonth, String matCode, String issuQty, String issuAmount) 
	throws Exception { //15-07-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialperiod SET issuqty = issuqty +"+Float.parseFloat(issuQty)+
			", issuamount = issuamount + "+Float.parseFloat(issuAmount)+
			" WHERE year = '"+docYear+"' AND month = '"+docMonth+"' AND matcode = '"+matCode+"' ";
	    	
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			tmp_return = pStmt.executeUpdate(sqlStmt);			
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
		return tmp_return;
	} 
	public int CancelAccuIssuQty(String docYear, String docMonth, String matCode, String issuQty, 
		String issuAmount) 
	throws Exception { //15-07-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialperiod SET issuqty = issuqty -"+Float.parseFloat(issuQty)+
			", issuamount = issuamount -"+Float.parseFloat(issuAmount)+
			" WHERE year = '"+docYear+"' AND month = '"+docMonth+"' AND matcode = '"+matCode+"' ";
	    	
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			tmp_return = pStmt.executeUpdate(sqlStmt);			
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
		return tmp_return;
	}
	public boolean CheckMaterialPeriod(String docYear, String docMonth, String matCode) 
	throws Exception { //01-07-2012 
		boolean chk = false;
		try {
			conn = agent.getConnectMYSql();
			
			String sql = "SELECT matcode FROM mmmaterialperiod " +
			"WHERE year = '"+docYear+"' AND month = '"+docMonth+"' AND matcode = '"+matCode+"' ";
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
				chk = true;
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return chk;
	}

}