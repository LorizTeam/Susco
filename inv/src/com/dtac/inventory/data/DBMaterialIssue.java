/*
 * Created on 12-04-2012
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

import com.dtac.inventory.form.MaterialForm;
import com.dtac.inventory.form.MaterialIssueForm;
import com.dtac.inventory.form.MaterialTypeForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMaterialIssue {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;
	DateUtil dateUtil = new DateUtil();
	
	public List GetMaterialIssueHDList(String docTypeCode, String docYear, String docMonth, String docNo, 
		String docStatus, String refOrdTypeCode, String refOrdYear, String refOrdNo, 
		String custCode, String custName) 
	throws Exception {	//02-09-2012
		List matIssueHDList = new ArrayList();
		String docDate = "", docByCode = "", docByName = "", refOrdTypeName = "";
		String wahoCode = "", wahoName = "";
		String remark = "", periodStatus = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.doctypecode, b.doctypename, a.docyear, a.docmonth, a.docno, a.docdate, " +
			"a.refordtypecode, a.refordyear, a.refordno, a.custcode, a.custname, a.docbycode, a.docbyname, " +
			"a.wahocode, c.wahoname, a.remark, a.docstatus, d.status AS periodstatus " +
			"FROM mmmatissuhd a " +
			"JOIN mmdoctype b ON (a.doctypecode = b.doctypecode) " +
			"JOIN mmwarehouse c ON (a.wahocode = c.wahocode) " +
			"LEFT JOIN mmdocperiod d ON (a.docyear = d.year AND a.docmonth = d.month) " +
			"WHERE ";
			
			if (!docTypeCode.equals("")) sqlStmt = sqlStmt + "a.doctypecode = '"+docTypeCode+"' AND ";
			if (!docYear.equals("")) 	sqlStmt = sqlStmt + "a.docyear = '"+docYear+"' AND ";
			if (!docNo.equals("")) 		sqlStmt = sqlStmt + "a.docno = '"+docNo.trim()+"' AND ";
			if (!docMonth.equals("")) 	sqlStmt = sqlStmt + "a.docmonth = '"+docMonth+"' AND ";
			if (!docStatus.equals("")) 	sqlStmt = sqlStmt + "a.docstatus = '"+docStatus+"' AND ";
			if (!refOrdTypeCode.equals("")) sqlStmt = sqlStmt + "a.refordtypecode = '"+refOrdTypeCode+"' AND ";
			if (!refOrdYear.equals("")) sqlStmt = sqlStmt + "a.refordyear = '"+refOrdYear+"' AND ";
			if (!refOrdNo.equals("")) sqlStmt = sqlStmt + "a.refordno like '"+refOrdNo.trim()+"%' AND ";
			if (!custCode.equals("")) sqlStmt = sqlStmt + "a.custcode like '"+custCode.trim()+"%' AND ";
			if (!custName.equals("")) sqlStmt = sqlStmt + "a.custname like '%"+custName.trim()+"%' AND ";
			
			sqlStmt = sqlStmt + "a.docstatus <> '' ORDER BY a.doctypecode, a.docyear, a.docno";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {				
				docTypeCode	= rs.getString("doctypecode");
				docYear 	= rs.getString("docyear");
				docNo 		= rs.getString("docno"); 
				docMonth 	= rs.getString("docmonth");
				docDate 	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("docdate"));
				
				docStatus 	= rs.getString("docStatus");				
				if (rs.getString("docbycode") != null) docByCode	= rs.getString("docbycode"); else docByCode = "";
				if (rs.getString("docbyname") != null) docByName	= rs.getString("docbyname"); else docByName = "";
				if (rs.getString("refordtypecode") != null) refOrdTypeCode	= rs.getString("refordtypecode"); else refOrdTypeCode = "";
				if (rs.getString("refordyear") != null) refOrdYear	= rs.getString("refordyear"); else refOrdYear = "";
				if (rs.getString("refordno") != null) refOrdNo	= rs.getString("refordno"); else refOrdNo = "";
				if (rs.getString("custcode") != null) custCode 	= rs.getString("custcode"); else custCode  = "";
				if (rs.getString("custname") != null) custName 	= rs.getString("custname"); else custName  = "";
				wahoCode 	= rs.getString("wahocode");
				if (rs.getString("wahoname") != null) wahoName 	= rs.getString("wahoname"); else wahoName  = "";
				if (rs.getString("remark") != null) remark 	= rs.getString("remark"); else remark  = "";
				if (rs.getString("periodstatus") != null) periodStatus 	= rs.getString("periodstatus"); 
				else periodStatus  = "CL";
				
				matIssueHDList.add(new MaterialIssueForm(docTypeCode, docYear, docMonth, docNo, docDate, 
					docStatus, docByCode, docByName, refOrdTypeCode, refOrdTypeName, refOrdYear, 
					refOrdNo, wahoCode, wahoName, custCode, custName, remark, periodStatus));
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
		return matIssueHDList;
	}	
	public List GetMaterialIssueDTList(String docTypeCode, String docYear, String docNo, String itemNo) 
	throws Exception { //22-06-2012
		List matIssueDTList = new ArrayList();
		String matCode = "", matName = "", refMatCode = "", iUnit = "";
		String issuQty = "", issuPricePerUnit = "", issuAmount = "", issuStatus = "", issuCancelStatus="";
		
		String locaCode = "", locaName = "";
		String wahoCode = "", lotNo = "", docMonth = "";		
		String amntQty  = "", brrwQty = "", alocQty = "", avalQty = "";
		String updateDate = "", updateByCode = "", updateByName = "";			
		try {
			DecimalFormat df1 = new DecimalFormat("#,###,##0.##");
			DecimalFormat df2 = new DecimalFormat("#,###,##0.00");
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.doctypecode, a.docyear, a.docno, a.itemno, a.matcode, " +
			"a.lotno, a.matname, a.issuqty, a.iunit, a.locacode, a.issustatus, " +
			"a.priceperunit, a.issuamount, a.docbycode, a.docbyname, a.updatedate, wl.locaname " +
			"FROM mmmatissudt a " +
			"JOIN mmmatissuhd b ON (a.doctypecode = b.doctypecode AND a.docyear = b.docyear AND a.docno = b.docno) " +
			"JOIN mmwarehouse wh ON (wh.wahocode = b.wahocode) " +
			"JOIN mmwaholocation wl ON (wh.wahocode = wl.wahocode AND a.locacode = wl.locacode) " +
			"WHERE a.doctypecode = '"+docTypeCode+"' AND a.docyear = '"+docYear+"' AND a.docno = '"+docNo+"' ";
			
			if (!itemNo.equals("")) sqlStmt = sqlStmt + "AND a.itemno = '"+itemNo+"' ";
			
			sqlStmt = sqlStmt + "ORDER By a.doctypecode, a.docyear, a.docno, a.itemno";
			
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				if (rs.getString("matcode") != null) matCode 	= rs.getString("matcode"); else matCode = "";
				if (rs.getString("matname") != null) matName	= rs.getString("matname"); else matName = "";
				if (rs.getString("issuqty") != null) issuQty	= rs.getString("issuqty"); else issuQty = "0";
				if (rs.getString("iunit") != null)   iUnit		= rs.getString("iunit");  else iUnit = "";
				if (rs.getString("itemNo") != null)  itemNo		= rs.getString("itemNo"); else itemNo = "";
				
				if (rs.getString("lotno") != null) 	 lotNo		= rs.getString("lotno");  else lotNo = "";
				if (rs.getString("locacode") != null) locaCode	= rs.getString("locacode"); else locaCode = "";
				if (rs.getString("locaname") != null) locaName	= rs.getString("locaname"); else locaName = "";
				if (rs.getString("priceperunit") != null) 
				issuPricePerUnit	= rs.getString("priceperunit");
				issuStatus			= rs.getString("issustatus");
				issuAmount			= rs.getString("issuamount");
				//if (rs.getString("docbycode") != null) docByCode	= rs.getString("docbycode"); else docByCode = "";
				//if (rs.getString("docbyname") != null) docByName	= rs.getString("docbyname"); else docByName = "";
				if (rs.getString("updatedate") != null) updateDate	= rs.getString("updatedate"); else updateDate = "";
				
				issuCancelStatus = "N";
				if (issuStatus.equals("CL")) issuCancelStatus = "Y"; 
				
				issuQty 		= df1.format(Float.parseFloat(issuQty));
				issuPricePerUnit= df2.format(Float.parseFloat(issuPricePerUnit));
				issuAmount 		= df2.format(Float.parseFloat(issuAmount));

				matIssueDTList.add(new MaterialIssueForm(docTypeCode, docYear, docMonth, docNo,						 
					wahoCode, itemNo, matCode, matName, refMatCode, iUnit, 
					lotNo, locaCode, locaName, issuQty, issuPricePerUnit,
					issuAmount, issuStatus, issuCancelStatus, updateDate, updateByCode, updateByName, 
						 amntQty,  brrwQty,  alocQty,  avalQty
				));
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
		return matIssueDTList;
	}
	public void AddMaterialIssueHD(String docTypeCode, String docYear, String docNo, String docDate, String docStatus,
		String docByCode, String docByName, String refOrdTypeCode, String refOrdYear, String refOrdNo,
		String custCode, String custName, String docMonth, String wahoCode, String remark)
	throws Exception {	//22-06-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO mmmatissuhd (docTypeCode, docYear, docNo, docDate, docStatus, docByCode, docByName, " +
					"refOrdTypeCode, refOrdYear, refOrdNo, custCode, custName, docMonth, wahoCode, remark) " +
			"VALUES ('"+docTypeCode.trim()+"', '"+docYear.trim()+"', '"+docNo.trim()+"', " +
			"'"+dateUtil.CnvToYYYYMMDDEngYear(docDate,'-')+"', '"+docStatus.trim()+"', " +
			"'"+docByCode.trim()+"', '"+docByName.trim()+"', " +
			"'"+refOrdTypeCode.trim()+"','"+refOrdYear.trim()+"','"+refOrdNo.trim()+"'," +
			"'"+custCode.trim()+"','"+custName.trim()+"','"+docMonth.trim()+"','"+wahoCode.trim()+"','"+remark.trim()+"') ";
			
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
	public void UpdateMaterialIssueHD(String docTypeCode, String docYear, String docNo, String docDate, 
		String docStatus, String refOrdTypeCode, String refOrdYear, String refOrdNo,
		String custCode, String custName, String remark) 
	throws Exception { //12-07-2012
		try {
			conn = agent.getConnectMYSql();
	
	    	String sqlStmt = "UPDATE mmmatissuhd SET docdate = '"+dateUtil.CnvToYYYYMMDDEngYear(docDate,'-')+"', " +
	    	"docstatus = '"+docStatus+"', "+
	    	"refordtypecode = '"+refOrdTypeCode+"', refordyear = '"+refOrdYear+"', refordno = '"+refOrdNo.trim()+"', " +
	    	"custcode = '"+custCode.trim()+"', custname = '"+custName.trim()+"', remark = '"+remark.trim()+"' "+
	    	"WHERE doctypecode = '"+docTypeCode+"' AND docyear = '"+docYear+"' AND docno = '"+docNo+"'";
		   			
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
	public void AddMaterialIssueDT(String docTypeCode, String docYear, String docNo,
		String itemNo, String matCode, String matName, String iUnit, String lotNo, String locaCode, 
		String issuQty, String issuPricePerUnit, String issuAmount, String issuStatus,
		String docByCode, String docByName) 
	throws Exception { //02-07-2012
		try {
			conn = agent.getConnectMYSql();	
	    	
	    	String sqlStmt = "INSERT INTO mmmatissudt (doctypecode, docyear, docno, itemno, matcode, matname, " +
	    	"iunit, lotno, locacode, issuqty,issustatus, " +
	    	"docbycode, docbyname, updatedate) " +
	    	"VALUES ('"+docTypeCode+"', '"+docYear+"', '"+docNo+"', '"+itemNo+"', '"+matCode+"', '"+matName+"', " +
	    	"'"+iUnit+"', '"+lotNo+"', '"+locaCode+"', "+Float.parseFloat(issuQty)+", " +
	    	" '"+issuStatus+"', " +
	    	"'"+docByCode+"', '"+docByName+"', '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"')";
				
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
	public void UpdateMaterialIssueDT(String docTypeCode, String docYear, String docNo, String itemNo, 
		String matCode, String matName, String iUnit, String lotNo, String locaCode, 
		String issuQty, String issuPricePerUnit, String issuAmount,
		String issuStatus, String docByCode, String docByName) 
	throws Exception { //10-07-2012
		try {
			conn = agent.getConnectMYSql();
			
		    String sqlStmt = "UPDATE mmmatissudt SET matcode = '"+matCode+"', matname = '"+matName+"', " +
		    "iunit = '"+iUnit+"', issuqty = "+Float.parseFloat(issuQty)+", " +
//		    "priceperunit = "+Float.parseFloat(issuPricePerUnit)+", " +
//		    "issuamount = "+Float.parseFloat(issuAmount)+", " +
		    "lotno = '"+lotNo+"', locacode = '"+locaCode+"', issustatus = '"+issuStatus+"', " +
		    "docbycode = '"+docByCode.trim()+"', docbyname = '"+docByName.trim()+"', " +
		    "updatedate='"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"' " +
		    "WHERE doctypecode = '"+docTypeCode+"' AND docyear = '"+docYear+"' AND docno = '"+docNo+"' AND " +
		    "itemno = '"+itemNo+"' ";
		  			
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
	public void ConfirmMaterialIssueDT(String docTypeCode, String docYear, String docNo, String itemNo,		 
		String docByCode, String docByName, String issuPricePerUnit, String issuAmount) 
	throws Exception { //15-07-2012
		try {
			conn = agent.getConnectMYSql();
			
		    String sqlStmt = "UPDATE mmmatissudt SET issustatus = 'CL', " +
		    "docbyCode = '"+docByCode+"', docbyname = '"+docByName+"', " +
		    "priceperunit = "+Float.parseFloat(issuPricePerUnit)+", " +
		    "issuamount = "+Float.parseFloat(issuAmount)+", " +
		    "updatedate = '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"' " +
		    "WHERE doctypecode = '"+docTypeCode+"' AND docyear = '"+docYear+"' AND docno = '"+docNo+"' AND " +
		    "itemno = '"+itemNo+"' AND issustatus = 'AC' ";		  
			
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
	public void CancelConfirmMaterialIssueDT(String docTypeCode, String docYear, String docNo, String itemNo,		 
		String docByCode, String docByName) 
	throws Exception { //15-07-2012
		try {
			conn = agent.getConnectMYSql();
			
		    String sqlStmt = "UPDATE mmmatissudt SET issustatus = 'AC', " +
		    "docbyCode = '"+docByCode+"', docbyname = '"+docByName+"', " +
		    "updatedate = '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"' " +
		    "WHERE doctypecode = '"+docTypeCode+"' AND docyear = '"+docYear+"' AND docno = '"+docNo+"' AND " +
		    "itemno = '"+itemNo+"' AND issustatus = 'CL' ";		  
			
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
	public String GetLastItemNoMaterialIssueDTList(String docTypeCode, String docYear, String docNo) 
	throws Exception { //07-07-2012
		String itemNo = "0";			
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt= "SELECT itemno FROM mmmatissudt WHERE "+
			"doctypecode = '"+docTypeCode+"' AND docyear = '"+docYear+"' AND docno = '"+docNo+"' " +
			"ORDER BY itemno DESC LIMIT 1";
			
		//	System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				itemNo	= rs.getString("itemno"); 
			}
			itemNo	= String.valueOf(Integer.parseInt(itemNo)+1);
			if (itemNo.length() == 1) itemNo = "0" + itemNo;
			
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
		return itemNo;
	}
} 