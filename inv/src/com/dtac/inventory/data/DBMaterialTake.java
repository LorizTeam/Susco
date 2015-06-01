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

import com.dtac.inventory.form.MaterialTakeForm;
import com.dtac.inventory.form.MaterialTypeForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMaterialTake {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;
	DateUtil dateUtil = new DateUtil();
	
	public void AddMaterialTakeDT(String doctypecode, String docyear, String docno, String recno, String matCode, String MatName, String MatTypeCode, 
			String categories, String lotno, String locaCode, String refMatCode, String matStuffCode, String matBrandCode, String matColorCode, String unit, String price, String quantity, String amount, String totalamount) 
	throws Exception {	//14-04-2012
			String stock1 = "", stock2 = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * " +
			
			"FROM mmmaterial a " +
			"LEFT JOIN mmmattype b ON (a.mattypecode = b.mattypecode) " +
			"LEFT JOIN mmmatgrp c ON (a.mattypecode = c.mattypecode AND a.matgrpcode = c.matgrpcode) " +
			"LEFT JOIN mmvendermaster vend ON (a.suppcode = vend.vendercode) " +
			"LEFT JOIN mastertabledt punit ON (punit.grpcode = 'unit' AND a.punit = punit.typecode) " +
			"LEFT JOIN mastertabledt iunit ON (iunit.grpcode = 'unit' AND a.iunit = iunit.typecode) " +
			"LEFT JOIN mastertabledt runit ON (runit.grpcode = 'unit' AND a.runit = runit.typecode) " +
			"LEFT JOIN mastertabledt color ON (color.grpcode = 'colo' AND a.colocode = color.typecode) " +
			"LEFT JOIN mastertabledt stuff ON (stuff.grpcode = 'stuf' AND a.stufcode = stuff.typecode) " +
			"LEFT JOIN mastertabledt brand ON (brand.grpcode = 'bran' AND a.brancode = brand.typecode) " +
			"LEFT JOIN mastertabledt size ON (size.grpcode = 'size' AND a.refmatcode = size.typecode) " +
			"Where a.matcode = '"+matCode+"' ";
			
			//System.out.println(sqlStmt);				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {
					stock1			= rs.getString("stock1");
					stock2			= rs.getString("stock2");
				}
				rs.close();
				pStmt.close();
			
			sqlStmt = "INSERT INTO stockmovementdt (doctypecode, docyear, docno, itemno, matcode, matname, mattypecode, matcategories, lotno, locacode, sizecode, stufcode, brancode, colocode, unit, qty, price, amount, totalamount, updatedate, qtybalance, stock1, stock2) "+
			"VALUES ('"+doctypecode+"', '"+docyear+"', '"+docno+"', '"+recno+"', '"+matCode+"', '"+MatName+"', '"+MatTypeCode+"', '"+categories+"', '"+dateUtil.CnvToYYYYMMDDEngYear(lotno,'-')+"', '"+locaCode+"', '"+refMatCode+"','"+matStuffCode+"', " +
					"'"+matBrandCode+"', '"+matColorCode+"', '"+unit+"', "+Float.parseFloat(quantity)+", '"+price+"', "+Float.parseFloat(amount)+", "+Float.parseFloat(totalamount)+", '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"', "+Float.parseFloat(quantity)+", " +
					"'"+stock1+"', '"+stock2+"')";
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
	public void AddMaterialTakeHD(String doctypecode, String  docno, String docyear, String  docmonth, String docDate, String lotDate, String docstatus, String  docbycode, String docbyname, 
			String refordtypecode, String refordyear, String refordno, String referrequertno, String wahoCode, String requesterno, String requestername, 
			String rccode, String acountcode, String mobileno, String projectcode, String takedepart, String txtunit, String division, String dgroup,
			String stockStatus, String remark) 
	
	throws Exception {	//14-04-2012
		try {
			conn = agent.getConnectMYSql();
			refordtypecode ="";
			refordyear="";
			refordno ="";
			String sqlStmt = "INSERT INTO stockmovementhd (doctypecode, docyear, docno, docmonth, docdate, lotdate, docstatus, docByCode, docByName, refordtypecode, refordyear, refordno, " +
					"wahocode, empcode, empname, rccode, acountcode, mobileno, projectcode, department, " +
					"txtunit, division, dgroup, choicestatus, remark) "+
			"VALUES ('"+doctypecode+"', '"+docyear+"', '"+docno+"', '"+docmonth+"', '"+dateUtil.CnvToYYYYMMDDEngYear(docDate,'-')+"', '"+dateUtil.CnvToYYYYMMDDEngYear(lotDate,'-')+"', '"+docstatus.trim()+"', " +
					"'"+docbycode.trim()+"', '"+docbyname.trim()+"', '"+refordtypecode.trim()+"', '"+refordyear.trim()+"','"+refordno.trim()+"', '"+wahoCode+"', '"+requesterno+"', '"+requestername+"', " +
					"'"+rccode+"', '"+acountcode+"', '"+mobileno+"', '"+projectcode+"', '"+takedepart+"', '"+txtunit+"', '"+division+"', '"+dgroup+"', '"+stockStatus+"', '"+remark+"') ";
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
		public String SelectDocno(String doctypecode, String docyear) throws Exception {
				String docno = "";
			try {
				conn = agent.getConnectMYSql();
				
				String sqlStmt = "SELECT doctype, docyear, docno FROM runingsale "+
						"WHERE doctype = '"+doctypecode+"' AND docyear = '"+docyear+"'";
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);		
				while (rs.next()) {
					docno	= rs.getString("docno");
					docno 	= String.valueOf(Integer.parseInt(docno) + 1);
					DBMaterialTake upd = new DBMaterialTake();
					upd.UpdateDocno(doctypecode, docyear, docno);
					
					if (docno.length() == 1) {
						    docno = "00000" + docno; 
					} else if (docno.length() == 2) {
							docno = "0000" + docno; 
					} else if (docno.length() == 3) {
							docno = "000" + docno; 
					} else if (docno.length() == 4) {
							docno = "00" + docno; 
					} else if (docno.length() == 5) {
							docno = "0" + docno; 
					}
				}
				
			
				
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
			return docno;
		}
		public void UpdateDocno(String doctypecode, String docyear, String docno) throws Exception { //03-05-2012
		
			try {
				conn = agent.getConnectMYSql();
				
				String sqlStmt = "UPDATE runingsale SET  docno = '"+docno+"' " +
				"WHERE doctype = '"+doctypecode.trim()+"' AND docyear = '"+docyear+"'";
				
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
		
		//detail ดึงค่า Categories
		public List GetMaterialCategoriesList() throws Exception {
			List matCategorieList = new ArrayList();
			String matCategoriecode = "", matCategoriename = "";
			try {
				conn = agent.getConnectMYSql();
				
				String sqlStmt = "SELECT matCategoriecode, matCategoriename FROM mmmatcategorie ";		
				
				//System.out.println(sqlStmt);				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {
					matCategoriecode	= rs.getString("matCategoriecode");
					matCategoriename	= rs.getString("matCategoriename");		
						
					matCategorieList.add(new MaterialTakeForm(matCategoriecode, matCategoriename));
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
			return matCategorieList;
		}
		public String GetLastItemNoMaterialTakeDTList(String doctypecode, String docyear, String docno) 
		throws Exception { //20-06-2013
			String hdrecno = "0";			
			try {
				conn = agent.getConnectMYSql();
				
				String sqlStmt= "SELECT itemno FROM stockmovementdt WHERE "+
				"doctypecode = '"+doctypecode+"' AND docyear = '"+docyear+"' AND docno = '"+docno+"' " +
				"ORDER BY itemno DESC LIMIT 1";
				
			//	System.out.println(sqlStmt);
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);			
				while (rs.next()) {
					hdrecno	= rs.getString("itemno");
				}
				hdrecno	= String.valueOf(Integer.parseInt(hdrecno)+1);
				if (hdrecno.length() == 1) hdrecno = "0" + hdrecno;
				 
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
			return hdrecno;
		}
		public List GetMaterialTakeHDList(String docTypeCode, String docYear, String docMonth, String docNo, 
				String docStatus, String refOrdTypeCode, String refOrdYear, String refOrdNo,
				String custCode, String custName ) 
			throws Exception {	//01-06-2012
				List matTakeHDList = new ArrayList();
				String status = "",date = "", lotDate = "", docbycode = "", docbyname = "", refOrdTypeName = "";
				String takedepart = "", txtunit = "", division = "", dgroup = "", stockStatus = "", acountcode = "", projectcode = "";
				String mobileno = "", rccode = "", wahoCode = "", wahoName = "";
				String remark = "", periodStatus = "";
				try {
					conn = agent.getConnectMYSql();
					
					String sqlStmt = "SELECT a.status,a.doctypecode, b.doctypename, a.docyear, a.docmonth, a.docno, a.docdate,   " +
					"a.refordtypecode, a.refordyear, a.refordno, a.custcode, a.custname, a.docbycode, a.docbyname, " + 
					"a.wahocode,c.wahoname,  a.remark, a.docstatus, d.status AS periodstatus " +
					"FROM mmmathd a " +
					"JOIN mmdoctype b ON (a.doctypecode = b.doctypecode) " +
					"JOIN mmwarehouse c ON (a.wahocode = c.wahocode) " +
					"LEFT JOIN mmdocperiod d ON (a.docyear = d.year AND a.docmonth = d.month) " + 
					"WHERE " ;
					
					if (!docTypeCode.equals(""))	sqlStmt = sqlStmt + "a.doctypecode = '"+docTypeCode+"' AND ";
					if (!docYear.equals("")) 		sqlStmt = sqlStmt + "a.docyear 		= '"+docYear+"' AND ";
					if (!docNo.equals("")) 			sqlStmt = sqlStmt + "a.docno 		= '"+docNo.trim()+"' AND ";
					if (!docMonth.equals("")) 		sqlStmt = sqlStmt + "a.docmonth 	= '"+docMonth+"' AND ";				 
					if (!custCode.equals(""))	 	sqlStmt = sqlStmt + "a.custcode 	= '"+custCode+"' AND ";
					if (!custName.equals("")) 		sqlStmt = sqlStmt + "a.custname 	= '"+custName+"' AND ";
					
					sqlStmt = sqlStmt + "a.docstatus <> '' ORDER BY a.doctypecode, a.docyear, a.docno";
								
					//System.out.println(sqlStmt);				
					pStmt = conn.createStatement();
					rs = pStmt.executeQuery(sqlStmt);			
					while (rs.next()) {	
						status = rs.getString("status");
						docTypeCode	= rs.getString("doctypecode");
						docYear 	= rs.getString("docyear");
						docNo 		= rs.getString("docno"); 
						docMonth 	= rs.getString("docmonth");
					//	date 	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("docdate"));
						date = dateUtil.CnvToDDMMYYYY(rs.getString("docdate")); 
						docStatus 	= rs.getString("docStatus"); 
						if (rs.getString("docbycode") != null) docbycode	= rs.getString("docbycode"); else docbycode = "";
						if (rs.getString("docbyname") != null) docbyname	= rs.getString("docbyname"); else docbyname = "";
						if (rs.getString("custcode") != null) custCode 	= rs.getString("custcode"); else custCode  = "";
						if (rs.getString("custname") != null) custName 	= rs.getString("custname"); else custName  = "";				 
						wahoCode 	= rs.getString("wahocode");
	 
						if (rs.getString("remark") != null) remark 	= rs.getString("remark"); else remark  = "";
						if (rs.getString("periodstatus") != null) periodStatus 	= rs.getString("periodstatus"); 
						else periodStatus  = "CL";
		
						wahoName = rs.getString("wahoname");
						
						matTakeHDList.add(new MaterialTakeForm(status,docTypeCode, docYear, docMonth, docNo, date, lotDate, docStatus, docbycode, docbyname,
								takedepart, txtunit, division, dgroup, stockStatus, acountcode, projectcode, mobileno, rccode, refOrdTypeCode,
								refOrdTypeName, refOrdYear, refOrdNo, wahoCode, wahoName, custCode, custName, remark, periodStatus));
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
				return matTakeHDList;
			}
		///
		public List GetMaterialTakeHDerr(String docTypeCode, String docYear, String docMonth, String docNo, 
				String docStatus, String refOrdTypeCode, String refOrdYear, String refOrdNo,
				String custCode, String custName ) 
			throws Exception {	//01-06-2012
				List matTakeHDList = new ArrayList();
				String status = "",date = "", lotDate = "", docbycode = "", docbyname = "", refOrdTypeName = "";
				String takedepart = "", txtunit = "", division = "", dgroup = "", stockStatus = "", acountcode = "", projectcode = "";
				String mobileno = "", rccode = "", wahoCode = "", wahoName = "";
				String remark = "", periodStatus = "";
			 
					 
						
						matTakeHDList.add(new MaterialTakeForm(status,docTypeCode, docYear, docMonth, docNo, date, lotDate, docStatus, docbycode, docbyname,
								takedepart, txtunit, division, dgroup, stockStatus, acountcode, projectcode, mobileno, rccode, refOrdTypeCode,
								refOrdTypeName, refOrdYear, refOrdNo, wahoCode, wahoName, custCode, custName, remark, periodStatus));
			 
					rs.close();
					pStmt.close();
					conn.close();
				 
				return matTakeHDList;
			}
		
		///
		public List GetMaterialTakeSerialHDList(String docTypeCode, String docYear, String docMonth, String docNo, 
				String docStatus, String refOrdTypeCode, String refOrdYear, String refOrdNo,
				String serial) 
			throws Exception {	//01-06-2012
				List matTakeHDList = new ArrayList();
				String status = "",date = "", lotDate = "", docbycode = "", docbyname = "", refOrdTypeName = "";
				String takedepart = "", txtunit = "", division = "", dgroup = "", stockStatus = "", acountcode = "", projectcode = "";
				String mobileno = "", rccode = "", wahoCode = "", wahoName = "";
				String remark = "", periodStatus = "",custCode="",custName="";
				try {
					conn = agent.getConnectMYSql();
					
					String sqlStmt = "SELECT a.status,a.doctypecode, b.doctypename, a.docyear, a.docmonth, a.docno, a.docdate,   " +
					"a.refordtypecode, a.refordyear, a.refordno, a.custcode, a.custname, a.docbycode, a.docbyname, " + 
					"a.wahocode,c.wahoname,  a.remark, a.docstatus, d.status AS periodstatus " +
					"FROM mmmathd a " +
					"JOIN mmmatdt e ON (a.doctypecode = b.doctypecode AND a.docno=b.docno  AND a.docyear= b.docyear AND a.docmonth=b.docmonth) " +
					"JOIN mmdoctype b ON (a.doctypecode = b.doctypecode) " +
					"JOIN mmwarehouse c ON (a.wahocode = c.wahocode) " +
					"LEFT JOIN mmdocperiod d ON (a.docyear = d.year AND a.docmonth = d.month) " + 
					"WHERE " ;
					
					if (!docTypeCode.equals(""))	sqlStmt = sqlStmt + "a.doctypecode = '"+docTypeCode+"' AND ";
					if (!docYear.equals("")) 		sqlStmt = sqlStmt + "a.docyear 		= '"+docYear+"' AND ";
					if (!docNo.equals("")) 			sqlStmt = sqlStmt + "a.docno 		= '"+docNo.trim()+"' AND ";
					if (!docMonth.equals("")) 		sqlStmt = sqlStmt + "a.docmonth 	= '"+docMonth+"' AND ";				 
					if (!serial.equals(""))	 	sqlStmt = sqlStmt + "b.serial 	= '"+serial+"' AND ";
			 
					
					sqlStmt = sqlStmt + "a.docstatus <> '' ORDER BY a.doctypecode, a.docyear, a.docno";
								
					//System.out.println(sqlStmt);				
					pStmt = conn.createStatement();
					rs = pStmt.executeQuery(sqlStmt);			
					while (rs.next()) {	
						status = rs.getString("status");
						docTypeCode	= rs.getString("doctypecode");
						docYear 	= rs.getString("docyear");
						docNo 		= rs.getString("docno"); 
						docMonth 	= rs.getString("docmonth");
					//	date 	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("docdate"));
						date = dateUtil.CnvToDDMMYYYY(rs.getString("docdate")); 
						docStatus 	= rs.getString("docStatus"); 
						if (rs.getString("docbycode") != null) docbycode	= rs.getString("docbycode"); else docbycode = "";
						if (rs.getString("docbyname") != null) docbyname	= rs.getString("docbyname"); else docbyname = "";
						 custCode 	= rs.getString("custcode");  
						 custName 	= rs.getString("custname");  				 
						wahoCode 	= rs.getString("wahocode");
	 
						if (rs.getString("remark") != null) remark 	= rs.getString("remark"); else remark  = "";
						if (rs.getString("periodstatus") != null) periodStatus 	= rs.getString("periodstatus"); 
						else periodStatus  = "CL";
		
						wahoName = rs.getString("wahoname");
						
						matTakeHDList.add(new MaterialTakeForm(status,docTypeCode, docYear, docMonth, docNo, date, lotDate, docStatus, docbycode, docbyname,
								takedepart, txtunit, division, dgroup, stockStatus, acountcode, projectcode, mobileno, rccode, refOrdTypeCode,
								refOrdTypeName, refOrdYear, refOrdNo, wahoCode, wahoName, custCode, custName, remark, periodStatus));
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
				return matTakeHDList;
			}	
	//
		public List GetMaterialSerialDTList(String serial) 
			throws Exception {	//01-06-2012
				List matTakeHDList = new ArrayList();
				int recno =0;
				String status = "",date = "", lotDate = "", docbycode = "", docbyname = "", refOrdTypeName = "";
				String takedepart = "", txtunit = "", division = "", dgroup = "", stockStatus = "", acountcode = "", projectcode = "";
				String mobileno = "", rccode = "", wahoCode = "", wahoName = "",matCode= "",   matName= "",pricesale= "";
				String remark = "", periodStatus = "",custCode="",custName="",docTypeCode="",docYear="",docMonth="",docNo="" ,alertMessage="";
				try {
					conn = agent.getConnectMYSql();
					
					String sqlStmt = "SELECT a.status,a.doctypecode,  a.docyear, a.docmonth, a.docno, a.docdate,   " +
					"a.refordtypecode, a.refordyear, a.refordno, a.custcode, a.custname, a.docbycode, a.docbyname,e.matcode,e.matname,e.issuamount, " + 
					"a.wahocode,c.wahoname,  a.remark, a.docstatus, d.status AS periodstatus " +
					"FROM mmmathd a " +
					"JOIN mmmatdt e ON (a.doctypecode = e.doctypecode AND a.docno=e.docno  AND a.docyear= e.docyear AND a.docmonth=e.docmonth) " +
		 
					"JOIN mmwarehouse c ON (a.wahocode = c.wahocode) " +
					"LEFT JOIN mmdocperiod d ON (a.docyear = d.year AND a.docmonth = d.month) " + 
					"WHERE " ;
					
			 			 
					if (!serial.equals(""))	 	sqlStmt = sqlStmt + "serial 	= '"+serial+"' AND ";
			 
					
					sqlStmt = sqlStmt + "a.docstatus <> '' ORDER BY a.doctypecode, a.docyear, a.docno";
								
					//System.out.println(sqlStmt);				
					pStmt = conn.createStatement();
					rs = pStmt.executeQuery(sqlStmt);			
					while (rs.next()) {	
						recno =rs.getRow();
						status = rs.getString("status");
						docTypeCode	= rs.getString("doctypecode");
						docYear 	= rs.getString("docyear");
						docNo 		= rs.getString("docno"); 
						docMonth 	= rs.getString("docmonth");
					//	date 	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("docdate"));
						date = dateUtil.CnvToDDMMYYYY(rs.getString("docdate")); 
					 
						if (rs.getString("docbycode") != null) docbycode	= rs.getString("docbycode"); else docbycode = "";
						if (rs.getString("docbyname") != null) docbyname	= rs.getString("docbyname"); else docbyname = "";
						 custCode 	= rs.getString("custcode");  
						 custName 	= rs.getString("custname");  				 
						 wahoCode 	= rs.getString("wahocode");
	 
						if (rs.getString("remark") != null) remark 	= rs.getString("remark"); else remark  = "";
						if (rs.getString("periodstatus") != null) periodStatus 	= rs.getString("periodstatus"); 
						else periodStatus  = "CL";
		
						wahoName = rs.getString("wahoname");			 
						matCode = rs.getString("matcode");
						matName = rs.getString("matname");
						pricesale = rs.getString("issuamount");
						
						
						matTakeHDList.add(new MaterialTakeForm( docTypeCode,docYear,  docMonth,   docNo, 
								  wahoCode,   matCode,   matName,"" ,"", custCode,   custName,  pricesale));
					}
					if ( recno==0){
					alertMessage = "Record Not Issue";						
					matTakeHDList.add(new MaterialTakeForm( alertMessage));}
					
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
						alertMessage = "Record Not Issue";	
						
						matTakeHDList.add(new MaterialTakeForm( alertMessage));
					}
				}
				return matTakeHDList;
			}	
	//	
		public List GetMaterialTakeDTList(String docTypeCode, String docYear, String docNo, String itemNo) 
		throws Exception { //30-08-2012
			List matTakeDTList = new ArrayList();
			String matCode = "", matName = "", refMatCode = "";
			String takequantity = "", takeamount = "", taketotalamount = "", retuStatus = "", remark = "";
			
			String locaCode = "", locaName = "", matColorCode = "";
			String wahoCode = "", lotNo = "", docMonth = "";		
			String amntQty = "",  brrwQty = "", alocQty = "", avalQty = "";		
			String updateDate = "", updateByCode = "", updateByName = "", recvCancelStatus = "";	
			String poYear = "", poNo = ""; //poItemNo = "";
			String borrowQty = "", retuAvalQty = "", borrowStatus = "";
			String  borrowCancelStatus = "", borrowRemark = "", borrowDate = "", returnDate = "";
			String pUnitName = "", pUnit="", rUnit = "", rUnitName = "", iUnit = "", iUnitName = "";
			String costPrice 	 = "", qtyMaxStock = "", qtyMinStock 	= "", matBrandName = "", matStuffName="", matTypeCode = "";
			String matColorName = "", matStuffCode = "", refMatName= "", matBrandCode = "", retuRemark = "", stock1 = "", stock2 = "", takeprice = "";
			String normalPrice = "", empPrice = "", vipPrice = "", specialPrice = "", matTypeName = "", matGrpName = "", matGrpCode = "";
			String dateBrow = "", dateRetu = "", chgStatus = "", chgRetu = "";
			try {
				DecimalFormat df1 = new DecimalFormat("######0.##");
				DecimalFormat df2 = new DecimalFormat("######0.00");
				conn = agent.getConnectMYSql();
				
				String sqlStmt = "SELECT a.doctypecode, a.docyear, a.docno, a.itemno, a.matcode, a.mattypecode, a.matcategories, c.refmatcode, " +
				"a.amount, a.lotno, a.matname, a.qty, a.retuavalqty, a.qtybalance, a.unit, a.locacode, a.status, a.remark, a.colocode, " +
				"a.totalamount, a.docbycode, a.docbyname, a.updatedate, a.price, a.definedate, a.browdate, c.stock1, c.stock2, " +
				"c.mattypecode, c.matgrpcode, c.cost, c.priceretail, c.priceemp, c.pricevip, c.pricesale," + //12/06/2013
				"c.brancode, c.suppcode, c.colocode, c.stufcode, c.refmatcode, " +
				"wl.locaname, d.lastissudate, f.mattypename, g.matcategoriename, g.matcategoriecode, " +
				"unit.thdesc AS unitname, " +
				"color.thdesc AS colorname, stuff.thdesc AS stuffname, " +
				"brand.thdesc AS brandname, size.thdesc AS sizename " +
				"FROM stockmovementdt a " +
				"JOIN stockmovementhd b ON (a.doctypecode = b.doctypecode AND a.docyear = b.docyear AND a.docno = b.docno) " +
				"JOIN mmwarehouse wh ON (wh.wahocode = b.wahocode) " +
				"JOIN mmwaholocation wl ON (wh.wahocode = wl.wahocode AND a.locacode = wl.locacode) " +
				"LEFT JOIN mmmaterial c ON (c.matcode = a.matcode) " +
				"LEFT JOIN mmmaterialwaho d ON (b.wahocode = d.wahocode AND a.locacode = d.locacode AND " +
					"c.matcode = d.matcode AND a.lotno = d.lotno) " +
				"LEFT JOIN mmmattype f ON (a.mattypecode = f.mattypecode) "+
				"LEFT JOIN mmmatcategorie g ON (a.matcategories = g.matcategoriecode) "+
				"LEFT JOIN mastertabledt unit ON (unit.grpcode = 'unit' AND a.unit = unit.typecode) " +
				"LEFT JOIN mastertabledt color ON (color.grpcode = 'colo' AND a.colocode = color.typecode) " +
				"LEFT JOIN mastertabledt stuff ON (stuff.grpcode = 'stuf' AND a.stufcode = stuff.typecode) " +
				"LEFT JOIN mastertabledt brand ON (brand.grpcode = 'bran' AND a.brancode = brand.typecode) " +
				"LEFT JOIN mastertabledt size ON (size.grpcode = 'size' AND c.refmatcode = size.typecode) " +
				"WHERE a.doctypecode = '"+docTypeCode+"' AND a.docyear = '"+docYear+"' AND a.docno = '"+docNo+"' ";
				
				if (!itemNo.equals("")) sqlStmt = sqlStmt + "AND a.itemno = '"+itemNo+"' ";
				
				sqlStmt = sqlStmt + "ORDER By a.doctypecode, a.docyear, a.docno, a.itemno";
				
				//System.out.println(sqlStmt);
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);				
				while (rs.next()) {
					itemNo		= rs.getString("itemno"); 
					matCode 	= rs.getString("matcode"); 
					if (rs.getString("matname") != null) matName	= rs.getString("matname"); else matName = "";
					//if (rs.getString("refmatcode") != null) refMatCode= rs.getString("refmatcode"); else refMatCode = "";
					//if (rs.getString("unit") != null) rUnit		= rs.getString("unit"); else rUnit = "";
					
					lotNo		= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("lotno"));
					locaCode	= rs.getString("locacode");
					if (rs.getString("locaname") != null) locaName	= rs.getString("locaname"); else locaName = "";
					
					takequantity		= rs.getString("qty");				 
					if (rs.getString("amount")!= null) takeamount= rs.getString("amount"); else takeamount = "";
					if (rs.getString("totalamount")!= null) taketotalamount		= rs.getString("totalamount"); else taketotalamount = "";
					retuStatus		= rs.getString("status");				
					if (rs.getString("remark") != null) remark	= rs.getString("remark"); else remark = "";
					if (rs.getString("updatedate") != null) updateDate	= rs.getString("updatedate"); else updateDate = "";

					//recvCancelStatus = "N";
					//if (rs.getString("lastissudate") == null && retuStatus.equals("CL")) recvCancelStatus = "Y";				
					//else if (recvStatus.equals("CL") && rs.getString("lastissudate") > rs.getString("updatedate"))
					
					//21/06/2013
					matTypeCode		= rs.getString("mattypecode");
					matTypeName		= rs.getString("mattypename");
					matGrpCode		= rs.getString("matcategoriecode");
					matGrpName		= rs.getString("matcategoriename");
					rUnit			= rs.getString("unit"); 
					rUnitName		= rs.getString("unitname");
					matColorCode	= rs.getString("colocode");
					matColorName	= rs.getString("colorname");
					matStuffCode	= rs.getString("stufcode");
					if (rs.getString("stuffname") != null) matStuffName	= rs.getString("stuffname"); else matStuffName = "";
					matBrandCode	= rs.getString("brancode");
					matBrandName	= rs.getString("brandname");
					refMatCode	 	= rs.getString("refmatcode");
					refMatName		= rs.getString("sizename");
					stock1			= rs.getString("stock1");
					stock2			= rs.getString("stock2");
					normalPrice     = rs.getString("priceretail");
					empPrice       	= rs.getString("priceemp");
					costPrice       = rs.getString("cost");
					specialPrice    = rs.getString("pricesale");
					vipPrice       	= rs.getString("pricevip");
				
					if (rs.getString("price") != null)  takeprice	= rs.getString("price"); else takeprice = "";
					if (rs.getString("remark") != null) retuRemark	= rs.getString("remark"); else avalQty = "";
					if (rs.getString("retuavalqty")!= null) retuAvalQty	= rs.getString("retuavalqty"); else retuAvalQty = "";
					if (rs.getString("qtybalance") != null) avalQty	= rs.getString("qtybalance"); else avalQty = "";
					if (rs.getString("browdate") != null) dateBrow	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("browdate")); else dateBrow = "";
					if (rs.getString("definedate") != null) dateRetu	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("definedate")); else dateRetu = "";
					
					takequantity  	= df1.format(Float.parseFloat(takequantity)); // จำนวนรับ / คืน / เคลม / เปลี่ยน
					avalQty			= df1.format(Float.parseFloat(avalQty)); // balance	
					takeamount= df2.format(Float.parseFloat(takeamount));
					taketotalamount 		= df2.format(Float.parseFloat(taketotalamount));
					poYear = "";
					 poNo  = "";
					 //poItemNo = "";
					
					 matTakeDTList.add(new MaterialTakeForm(docTypeCode, docYear, docMonth, docNo, wahoCode, matCode, matName, refMatCode, lotNo,
							 locaCode, locaName, borrowQty, takequantity, retuAvalQty, borrowStatus, borrowCancelStatus, borrowRemark, borrowDate,
							 returnDate, updateDate, updateByCode, updateByName, amntQty, brrwQty, alocQty,avalQty, itemNo, rUnit, rUnitName,
							 taketotalamount, takeamount, matTypeCode, matTypeName, matBrandCode, matBrandName, refMatName, matGrpCode, matGrpName,
							 matColorCode, matColorName, matStuffCode, matStuffName, costPrice, normalPrice, empPrice, vipPrice, specialPrice,
							 takeprice, retuRemark, stock1, stock2, dateBrow, dateRetu, chgStatus, chgRetu));
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
			return matTakeDTList;
		} 
		
	}
