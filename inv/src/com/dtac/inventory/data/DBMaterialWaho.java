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

import com.dtac.inventory.form.MaterialWahoForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMaterialWaho {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;
	DateUtil dateUtil = new DateUtil();
	
	public List GetMaterialWahoList(String wahoCode, String locaCode, String lotNo, String matCode, String matName, 
		String matTypeCode, String matGrpCode) 
	throws Exception {	//19-06-2012
		List matWaho = new ArrayList();
		String wahoName = "", wahoStatus = "", locaName = "", serial="",matWahoStatus = "";
		String amntQty = "", brrwQty = "", alocQty = "", avalQty = "", qtyMaxStock = "" ,qtyMinStock = "";		  
		String pUnit = "", pUnitName = "", rUnit = "", rUnitName = "", iUnit = "", iUnitName = "";
		String lastRecvDate = "", lastIssuDate = "", matSearchName = "", matSendName ="";
		String matTypeName 	= "", matStockType = "", matGrpName = "", matStatus = "", matRemark = "", sellPrice="",  costPrice="";
		String updateDate 	= "", updateByCode = "", updateByName = "";
		String matBrandCode = "", matBrandName = "", matSupplCode = "", matSupplName = "";
		String matColorCode = "", matColorName = "", matStuffCode = "", matStuffName = "", refMatCode = "", refMatName = "";
		String reqOrdFlag	= "", pic1 = "", pic2 = "";			
		String costPerUnit 	= "", costAmount = "";
		String normalPrice = "", empPrice = "", vipPrice = "", specialPrice = ""; //04/06/2013
		String stock1 = "" , stock2 = "",stock3 = "" , stock4 = "", rop = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.wahocode, a.locacode, a.matcode, a.lotno, " +
			"a.amntqty, a.brrwqty, a.alocqty, a.avalqty, a.costperunit, a.lastrecvdate, a.lastissudate, " +
			"b.matname , c.locaname, d.wahoname " +
			"FROM mmmaterialwaho a " +
			
			"JOIN mmmaterial b ON (a.matcode = b.matcode) " +			
			"JOIN mmwarehouse d ON (a.wahocode = d.wahocode) " +
			"LEFT JOIN mmwaholocation c ON (a.locacode = c.locacode) " +			
		
			"WHERE ";
			
			if (!wahoCode.equals("")) 	sqlStmt = sqlStmt + "a.wahocode = '"+wahoCode+"' AND ";
			if (!locaCode.equals("")) 	sqlStmt = sqlStmt + "a.locacode = '"+locaCode+"' AND ";
			if (!matCode.equals("")) 	sqlStmt = sqlStmt + "a.matcode = '"+matCode.trim()+"' AND ";
			if (!lotNo.equals("")) 		sqlStmt = sqlStmt + "a.lotno = '"+lotNo.trim()+"' AND ";
			if (!matTypeCode.equals(""))sqlStmt = sqlStmt + "b.mattypecode = '" +matTypeCode.trim()+"' AND ";
			if (!matGrpCode.equals(""))sqlStmt = sqlStmt + "b.matgrpcode = '" +matGrpCode.trim()+"' AND ";
				
			sqlStmt = sqlStmt + "a.matcode <>'' ORDER BY a.wahocode, a.locacode, a.matcode, a.lotno";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {				
				wahoCode	= rs.getString("wahocode");
				matCode		= rs.getString("matcode");
				serial	= rs.getString("serial");
				lotNo		= rs.getString("lotno"); 
				if (rs.getString("wahoname")!= null) wahoName	= rs.getString("wahoname"); else wahoName = "";
				if (rs.getString("matname") != null) matName	= rs.getString("matname");  else matName = "";
				if (rs.getString("locacode")!= null) locaCode	= rs.getString("locacode"); else locaCode = "";
				if (rs.getString("locaname")!= null) locaName	= rs.getString("locaname"); else locaName = "";
				
				amntQty		= rs.getString("amntqty"); 
				brrwQty		= rs.getString("brrwqty"); 
				alocQty		= rs.getString("alocqty"); 
				avalQty		= rs.getString("avalqty"); 
				costPerUnit = rs.getString("costperunit");
				if (rs.getString("lastrecvdate")!= null) lastRecvDate = rs.getString("lastrecvdate"); else lastRecvDate = "";
				if (rs.getString("lastissudate")!= null) lastIssuDate = rs.getString("lastissudate"); else lastIssuDate = "";
				
				matWaho.add(new MaterialWahoForm(matCode,serial, matName, matSendName,	matSearchName, 
						pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName,
						matTypeCode, matTypeName, matStockType, matGrpCode, matGrpName, 
						matBrandCode, matBrandName, matSupplCode, matSupplName,
						matColorCode, matColorName, matStuffCode, matStuffName, refMatCode, refMatName,
						matStatus, matRemark, sellPrice, costPrice, qtyMaxStock,
						qtyMinStock, updateDate, updateByCode, updateByName, 
						reqOrdFlag, pic1, pic2, normalPrice, empPrice, vipPrice, specialPrice, //04/06/2013
						
					wahoCode, wahoName, wahoStatus, locaCode, locaName, lotNo,
					amntQty, brrwQty, alocQty, avalQty, costPerUnit, costAmount,
					lastRecvDate, lastIssuDate, matWahoStatus ,stock1, stock2,stock3, stock4, rop));
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
		return matWaho;
	}
	public void UpdateMaterialWaho(String wahoCode, String matCode, 
		String initQty, String recvQty, String itrdQty, String iprdQty, String brrwQty, String alocQty, String avalQty) 
	throws Exception {
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
	public void AddMaterialWaho(String wahoCode, String locaCode, String matCode, String lotNo) 
	throws Exception { //01-07-2012
		try {
			conn = agent.getConnectMYSql();	

			String sqlStmt ="INSERT INTO mmmaterialwaho (wahocode, locacode, matcode, lotno) " +
			"VALUES ('"+wahoCode+"', '"+locaCode+"', '"+matCode+"', '"+lotNo+"') ";
			
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
	public String GetMaterialAmntQty(String wahoCode, String locaCode, String matCode, String lotNo) 
	throws Exception { //01-07-2012
		String amntQty = "0";			
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT amntqty FROM mmmaterialwaho WHERE "+
			"wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
			"lotno = '"+lotNo+"' ";
			
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
	public String GetMaterialAvalQty(String wahoCode, String locaCode, String matCode, String lotNo) 
	throws Exception { //19-07-2012
		String avalQty = "0";			
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT avalqty FROM mmmaterialwaho WHERE "+
			"wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
			"lotno = '"+lotNo+"' ";
			
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				avalQty	= rs.getString("avalqty");
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
		return avalQty;
	}	
	public String GetCostPerUnit(String wahoCode, String locaCode, String matCode, String lotNo) 
	throws Exception { //16-07-2012
		String costPetUnit = "0";			
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT costperunit FROM mmmaterialwaho WHERE "+
			"wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
			"lotno = '"+lotNo+"' ";
			
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				costPetUnit	= rs.getString("costperunit");
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
		return costPetUnit;
	}
	public int RecvMaterial(String wahoCode, String locaCode, String matCode, String lotNo, 
		String recvQty, String recvAmount) 
	throws Exception { //03-07-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialwaho SET costperunit = ((amntqty * costperunit) + " +
			""+Float.parseFloat(recvAmount)+") / (amntqty + "+Float.parseFloat(recvQty)+"), " +
			"amntqty = amntqty + " +Float.parseFloat(recvQty)+", lastrecvdate = '"+dateUtil.curDateTime()+"' " +
	    	"WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
	    	"lotno = '"+lotNo+"'";

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
	public int CancelRecvMaterial(String wahoCode, String locaCode, String matCode, String lotNo, 
		String recvQty, String recvAmount) 
	throws Exception { //13-07-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialwaho SET costperunit =  IFNULL(((amntqty * costperunit) - " +
			""+Float.parseFloat(recvAmount)+") / (amntqty - "+Float.parseFloat(recvQty)+"),0), " +
			"amntqty = amntqty - " +Float.parseFloat(recvQty)+" " +
	    	"WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
	    	"lotno = '"+lotNo+"'";

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
	public int IssuMaterial(String wahoCode, String locaCode, String matCode, String lotNo, String issuQty)
	throws Exception { //01-09-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialwaho SET amntqty = amntqty - " +Float.parseFloat(issuQty)+
			", lastissudate = '"+dateUtil.curDateTime()+"' " +
	    	"WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
	    	"lotno = '"+lotNo+"'";

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
	public int CancelIssuMaterial(String wahoCode, String locaCode, String matCode, String lotNo, 
		String issuQty, String issuAmount) 
	throws Exception { //15-07-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialwaho SET amntqty = amntqty + " +Float.parseFloat(issuQty)+
			", lastissudate = null " +
	    	" WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
	    	"lotno = '"+lotNo+"'";

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
	public int BrrwMaterial(String wahoCode, String locaCode, String matCode, String lotNo, 
		String borrowQty) 
	throws Exception { //19-07-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialwaho SET brrwqty = brrwqty + " +Float.parseFloat(borrowQty)+
//				", lastissudate = '"+dateUtil.curDateTime()+"' " +
	    	"WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
	    	"lotno = '"+lotNo+"'";

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
	public int CancelBrrwMaterial(String wahoCode, String locaCode, String matCode, String lotNo, 
		String borrowQty) 
	throws Exception { //19-07-2012		
		int tmp_return	= 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialwaho SET brrwqty = brrwqty - " +Float.parseFloat(borrowQty)+
//				", lastissudate = '"+dateUtil.curDateTime()+"' " +
	    	"WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
	    	"lotno = '"+lotNo+"'";

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

	public void CalAvalQty(String wahoCode, String locaCode, String matCode, String lotNo) 
	throws Exception { //13-07-2012		
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterialwaho SET avalqty = (amntqty-brrwqty-alocqty) "+
			"WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND matcode = '"+matCode+"' AND " +
			"lotno = '"+lotNo+"'";
			
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
	public boolean CheckMaterialWaho(String wahoCode, String locaCode, String matCode, String lotNo) 
	throws Exception { //01-07-2012 
		boolean chk = false;
		try {
			conn = agent.getConnectMYSql();
			
			String sql = "SELECT matcode FROM mmmaterialwaho " +
			"WHERE wahocode = '"+wahoCode+"' AND locacode = '"+locaCode+"' AND " +
			"matcode = '"+matCode+"' AND lotno = '"+lotNo+"' ";
			
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