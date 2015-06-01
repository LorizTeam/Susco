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
import com.dtac.inventory.form.MaterialTakeForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMaterialTrans {

	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil = new DateUtil();
	
	//=====================================================   INSERT HEADER
	public void AddMaterialTranHD(String status,String doctypecode,String docyear,String docno,String docmonth, String docdate, String docstatus,
			String cuscode,String cusname, String wahocode,String docbycode,String docbyname) 
	throws Exception {	//04-06-2012
		try {
			conn = agent.getConnectMYSql();
			// check dupplicate record 
			int recno =0;
			String sqlStmt = "SELECT * " +			
			"FROM mmmathd  " +			 
			"WHERE doctypecode = '"+doctypecode+"' AND docyear = '"+docyear+"' AND docno = '"+docno+"' ";
	 		
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {
			    recno = rs.getRow();					  
				}
				rs.close();
				 
			
			//start insert table 
		 if(recno==0){
			 sqlStmt = "INSERT INTO mmmathd (status,doctypecode,docyear,docbycode,docbyname,docno, docmonth, docdate, docstatus,custcode, custname, wahocode) " +
			"VALUES ('"+status+"','"+doctypecode+"','"+docyear+"','"+docbycode+"','"+docbyname+"','"+docno+"','"+docmonth+"', '"+docdate+"', '"+docstatus+"', '"+cuscode+"', '"+cusname+"', '"+wahocode+"')";
		
			//"'"+matColorCode+"', '"+matStuffCode+"', '"+refMatCode+"', "+Float.parseFloat(qtyMaxStock)+", "+Float.parseFloat(qtyMinStock)+", " +
			//""+Float.parseFloat(stock1)+", "+Float.parseFloat(stock2)+", "+Float.parseFloat(normalPrice)+", "+Float.parseFloat(empPrice)+", " +
			//""+Float.parseFloat(vipPrice)+", "+Float.parseFloat(costPrice)+", "+Float.parseFloat(specialPrice)+", '"+rop+"') ";
			
			//System.out.println(sqlStmt);
		    pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	
		 }
			// end insert table
				 
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
	//=====================================================   INSERT DETAIL
	public void AddMaterialTranDT(String status,String doctypecode,String custcode,String wahocode ,String docyear,String docno,String docmonth, String docdate,String itemno,String serial,String matcode,String matname,String issuqty,String priceperunit,String issuamount,String issustatus) 
		throws Exception {	//04-06-2012
			try {
				conn = agent.getConnectMYSql();
				
				// check dupplicate record 
				int recno =0;
				String sqlStmt = "SELECT * " +			
				"FROM mmmatdt  " +			 
				"WHERE doctypecode = '"+doctypecode+"' AND docyear = '"+docyear+"' AND docno = '"+docno+"'  AND serial = '"+serial+"' ";
		 		
					pStmt = conn.createStatement();
					rs = pStmt.executeQuery(sqlStmt);	
					while (rs.next()) {
				    recno = rs.getRow();					  
					}
					rs.close();
				
			//start insert table 
			if(recno==0){
			 sqlStmt = "INSERT INTO mmmatdt (doctypecode,status,custcode,wahocode ,docyear, docno,docmonth, docdate, itemno, serial, matcode, matname, issuqty, priceperunit, issuamount, issustatus)" +
				"VALUES ('"+doctypecode+"','"+status+"','"+custcode+"','"+wahocode+"', '"+docyear+"', '"+docno+"', '"+docmonth+"', '"+docdate+"', '"+itemno+"', '"+serial+"', '"+matcode+"', '"+matname+"', '"+issuqty+"', " +
				"'"+priceperunit+"', '"+issuamount+"', '"+issustatus+"') ";
				// end insert table				
				//System.out.println(sqlStmt);
			    pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);	
			 }
			// end insert table					
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
 
	//===================================================== Return Product
	public void AddMaterialTranRTNDT(String status,String doctypecode,String custcode,String wahocode ,String docyear,String docno,String docmonth, String docdate,String itemno,String serial,String matcode,String matname,String qty,String amount,String olddocyear,String olddocmonth,String olddoctypecode,String olddocno) 
	throws Exception {	//04-06-2012
		try {
			conn = agent.getConnectMYSql();
			
			// check dupplicate record 
			int recno =0;
			String sqlStmt = "SELECT * " +			
			"FROM mmmatdt  " +			 
			"WHERE doctypecode = '"+doctypecode+"' AND docyear = '"+docyear+"' AND docno = '"+docno+"'  AND serial = '"+serial+"' ";
	 		
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {
			    recno = rs.getRow();					  
				}
				rs.close();
			
		//start insert table 
		if(recno==0){
		 sqlStmt = "INSERT INTO mmmatdt (status,doctypecode,custcode,wahocode ,docyear, docmonth,docno, docdate, itemno, serial, matcode, matname, rtnqty, issuamount, olddocyear,olddocmonth,olddoctypecode,olddocno)" +
			"VALUES ('"+status+"','"+doctypecode+"','"+custcode+"','"+wahocode+"', '"+docyear+"', '"+docmonth+"', '"+docno+"', '"+docdate+"', '"+itemno+"', '"+serial+"', '"+matcode+"', '"+matname+"', '"+qty+"', '"+amount+"', '"+olddocyear+"', '"+olddocmonth+"', '"+olddoctypecode+"', '"+olddocno+"') ";
			// end insert table				
			//System.out.println(sqlStmt);
		    pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	
		 }
		// end insert table					
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


	//=====================================================   UPDATE DETAIL
	public void UpdateReturnMaterialTranDT(String doctypecode,String custcode,String wahocode ,String docyear,String docno,String docmonth, String serial,String matcode,String qty ) 
		throws Exception {	//04-06-2012
			try {
				conn = agent.getConnectMYSql();
				boolean check =false;
				// check dupplicate record 
			 
			String msgsql ="";
			String sqlStmt ="";
			//START UPDATE HEAD	
			// update status header  when return complete for all document
			// verify select 
			check =false;
			int rtnqty =0;
			int issuqty= 0;
			int prtnqty = Integer.parseInt(qty);
			
			  sqlStmt = "SELECT SUM(rtnqty) rtn, SUM(issuqty) brw " +			
			" FROM mmmatdt WHERE " +				 
					" doctypecode = '" +doctypecode+"' AND custcode = '" +custcode + 
					"' AND wahocode = '" +wahocode+"' AND docyear = '" +docyear + 
					"' AND docno = '" +docno+"' AND docmonth = '" +docmonth + "' ";
				 
	 		
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {					
					rtnqty =  Integer.parseInt(rs.getString("rtn"));
					prtnqty = prtnqty+rtnqty;
					issuqty =  Integer.parseInt(rs.getString("brw"));
					if(prtnqty==issuqty){
					check =true;
					}
				}
				rs.close();
			//
				  if(check){
			       msgsql =" status = 'RT' " ;				   				
			  sqlStmt = "UPDATE mmmathd SET "+ msgsql +" WHERE " +
					" doctypecode = '" +doctypecode+"' AND custcode = '" +custcode + 
					"' AND wahocode = '" +wahocode+"' AND docyear = '" +docyear + 
					"' AND docno = '" +docno+"' AND docmonth = '" +docmonth +"' ";			
			 
			    pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);	
				  }
				  
				pStmt.close();
				 
			
			//END
			
			//START UPDATE DETAIL	
			// update status detail when return complete for all product
			// verify select 
			check =false;
			  rtnqty =0;
			  issuqty= 0;
			  prtnqty = Integer.parseInt(qty);
			  sqlStmt = "SELECT * " +			
			" FROM mmmatdt WHERE " +				 
					" doctypecode = '" +doctypecode+"' AND custcode = '" +custcode + 
					"' AND wahocode = '" +wahocode+"' AND docyear = '" +docyear + 
					"' AND docno = '" +docno+"' AND docmonth = '" +docmonth +
					"' AND matcode = '" +matcode+"' AND  serial = '"+serial+"' ";
	 		
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {					
					rtnqty =  Integer.parseInt(rs.getString("rtnqty"));
					prtnqty = prtnqty+rtnqty;
					issuqty =  Integer.parseInt(rs.getString("issuqty"));
					if(prtnqty==issuqty){
					check =true;
					}
				}
				rs.close();
			//
				  if(check)
			       msgsql =" status = 'RT', rtnqty= '"+prtnqty+"'";
				  else			
			       msgsql ="  rtnqty= '"+prtnqty+"'";	 
				
			  sqlStmt = "UPDATE mmmatdt SET "+ msgsql +" WHERE " +
					" doctypecode = '" +doctypecode+"' AND custcode = '" +custcode + 
					"' AND wahocode = '" +wahocode+"' AND docyear = '" +docyear + 
					"' AND docno = '" +docno+"' AND docmonth = '" +docmonth +
					"' AND matcode = '" +matcode+"' AND  serial = '"+serial+"' ";
					
			 
			    pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);			
				pStmt.close();
				conn.close();
			//END UPDATE DETAIL	
				
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
	//=============================================================================
	
	public List GetMaterialTakeDTList(String custcode,String docTypeCode, String docYear, String docMonth, String docNo	, String serial		 ) 
		throws Exception {	//01-06-2012
		
			List itemlist = new ArrayList();
			
			String matcode = "",  matname = "", matSearchName = "";
			String matTypeCode = "", matColorCode = "", matStuffCode = "", refMatCode = "", matStatus = "", pUnit = "", matBrandCode = "";
			String mobileno = "", rccode = "", wahoCode = "", wahoName = "" ,matGrpCode="";
			String issuqty = "",rtnqty = "",status = "", issuamount, sttotalamount; float totalamoung=0;
			
		 
			try {
				conn = agent.getConnectMYSql();
				
		 
				
				String sqlStmt = "SELECT a.doctypecode, b.doctypename, a.docyear, a.docmonth, a.docno, a.docdate,   " +
				"a.itemno, a.serial, a.matcode, a.matname, a.issuqty,a.rtnqty, a.priceperunit, a.issuamount, " + 
				"a.issustatus,a.wahocode,a.custcode ,a.status " +
				"FROM mmmatdt a " +
				"JOIN mmmathd e ON (a.doctypecode = e.doctypecode AND a.docyear = e.docyear AND a.docmonth = e.docmonth AND " +
				" a.docno = e.docno AND a.docdate = e.docdate  ) " +
				"JOIN mmdoctype b ON (a.doctypecode = b.doctypecode) " +
		 
				"WHERE " ;
				
				if (!custcode.equals("")) 		sqlStmt = sqlStmt + "a.custcode  		= '"+custcode+"'  ";
				if (!docTypeCode.equals(""))	sqlStmt = sqlStmt + "AND a.doctypecode = '"+docTypeCode+"'  ";
				if (!docYear.equals("")) 		sqlStmt = sqlStmt + "AND a.docyear 		= '"+docYear+"'  ";
				if (!docNo.equals("")) 			sqlStmt = sqlStmt + "AND a.docno 		= '"+docNo+"'  ";
				if (!serial.equals("")) 		sqlStmt = sqlStmt + "AND a.serial 		= '"+serial+"'  ";
				if (!docMonth.equals("")) 		sqlStmt = sqlStmt + "AND a.docmonth 	= '"+docMonth+"' ";				 
 
				
				sqlStmt = sqlStmt + "  ORDER BY a.doctypecode, a.docyear,a.docmonth, a.docno";
							
				//System.out.println(sqlStmt);				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);			
				while (rs.next()) {	
					
					MaterialTakeForm olditem = new MaterialTakeForm();	
					
					docTypeCode	= rs.getString("doctypecode");
					docYear 	= rs.getString("docyear");
					docNo 		= rs.getString("docno"); 
					docMonth 	= rs.getString("docmonth");
			//		date = dateUtil.CnvToDDMMYYYY(rs.getString("docdate")); 
					matcode 	= rs.getString("matcode");
					serial 	= rs.getString("serial");
					matname 	= rs.getString("matname");
					issuqty = rs.getString("issuqty");
					rtnqty = rs.getString("rtnqty");
					issuamount = rs.getString("issuamount");
					matStatus 	= rs.getString("issustatus");
					status = rs.getString("status");
 				 
					wahoCode 	= rs.getString("wahocode");
					
					totalamoung = Float.parseFloat(issuqty) * Float.parseFloat(issuamount);
					sttotalamount = Float.toString(totalamoung);
					olditem.setMark("");
					olditem.setStatus(status);
					olditem.setDocTypeCode(docTypeCode);
					olditem.setDocYear(docYear);
					olditem.setDocNo(docNo);
					olditem.setDocMonth(docMonth);
									olditem.setMatCode(matcode);
						 
									olditem.setTakecategories(custcode);
					 
									olditem.setMatName(matname);
						 
									olditem.setTakequantity(issuqty);
									olditem.setRtnqty(rtnqty);
						 
									olditem.setTakeamount(issuamount);
									
									olditem.setTaketotalamount(sttotalamount);
		 
									olditem.setMatStuffName(serial);	
									olditem.setWahoCode(wahoCode);
									olditem.setWahoName(wahoName);
							 
					 
									itemlist.add(olditem);
	 
				  
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
			return itemlist;
		}	
	
	//=============================================================================
	public List GetMaterialTakeRTDTList(String custcode,String docTypeCode, String docYear, String docMonth, String docNo	, String serial		 ) 
	throws Exception {	//01-06-2012
	
		List itemlist = new ArrayList();
		
		String matcode = "",  matname = "", matSearchName = "",custname="";
		String matTypeCode = "", matColorCode = "", matStuffCode = "", refMatCode = "", matStatus = "", pUnit = "", matBrandCode = "";
		String mobileno = "", rccode = "", wahoCode = "", wahoName = "" ,matGrpCode="";
		String issuqty = "",rtnqty = "",status = "", issuamount, sttotalamount; float totalamoung=0;
		
	 
		try {
			conn = agent.getConnectMYSql();
			
	 
			
			String sqlStmt = "SELECT a.doctypecode, b.doctypename, a.docyear, a.docmonth, a.docno, a.docdate,   " +
			"a.itemno, a.serial, a.matcode, a.matname, a.issuqty,a.rtnqty, a.priceperunit, a.issuamount, " + 
			"a.issustatus,a.wahocode,a.custcode ,a.status " +
			"FROM mmmatdt a " +
			"JOIN mmmathd e ON (a.doctypecode = e.doctypecode AND a.docyear = e.docyear AND a.docmonth = e.docmonth AND " +
			" a.docno = e.docno AND a.docdate = e.docdate  ) " +
			"JOIN mmdoctype b ON (a.doctypecode = b.doctypecode) " +
	 
			"WHERE " ;
			
		//	if (!custcode.equals("")) 		sqlStmt = sqlStmt + "a.custcode  		= '"+custcode+"'  ";
			if (!docTypeCode.equals(""))	sqlStmt = sqlStmt + " a.doctypecode = '"+docTypeCode+"'  ";
			if (!docYear.equals("")) 		sqlStmt = sqlStmt + "AND a.docyear 		= '"+docYear+"'  ";
			if (!docNo.equals("")) 			sqlStmt = sqlStmt + "AND a.docno 		= '"+docNo+"'  ";
			if (!serial.equals("")) 		sqlStmt = sqlStmt + "AND a.serial 		= '"+serial+"'  ";
			if (!docMonth.equals("")) 		sqlStmt = sqlStmt + "AND a.docmonth 	= '"+docMonth+"' ";				 

			
			sqlStmt = sqlStmt + "  ORDER BY a.doctypecode, a.docyear,a.docmonth, a.docno";
			
			DBCustomer db = new DBCustomer();
		
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {	
				
				MaterialTakeForm olditem = new MaterialTakeForm();	
				
				docTypeCode	= rs.getString("doctypecode");
				docYear 	= rs.getString("docyear");
				docNo 		= rs.getString("docno"); 
				docMonth 	= rs.getString("docmonth");
				custcode    = rs.getString("custcode");
				custname=	db.GetCustomerName(custcode);
		//		date = dateUtil.CnvToDDMMYYYY(rs.getString("docdate")); 
				matcode 	= rs.getString("matcode");
				serial 	= rs.getString("serial");
				matname 	= rs.getString("matname");
				issuqty = rs.getString("issuqty");
				rtnqty = rs.getString("rtnqty");
				issuamount = rs.getString("issuamount");
				matStatus 	= rs.getString("issustatus");
				status = rs.getString("status");
				 
				wahoCode 	= rs.getString("wahocode");
				
				totalamoung = Float.parseFloat(issuqty) * Float.parseFloat(issuamount);
				sttotalamount = Float.toString(totalamoung);
				olditem.setMark("");
				olditem.setStatus(status);
				olditem.setDocTypeCode(docTypeCode);
				olditem.setDocYear(docYear);
				olditem.setDocNo(docNo);
				olditem.setDocMonth(docMonth);
				
				                olditem.setVendCode(custcode);
				                olditem.setVendName(custname);
				                
								olditem.setMatCode(matcode);
					 
								olditem.setTakecategories(custcode);
				 
								olditem.setMatName(matname);
					 
								olditem.setTakequantity(issuqty);
								olditem.setRtnqty(rtnqty);
					 
								olditem.setTakeamount(issuamount);
								
								olditem.setTaketotalamount(sttotalamount);
	 
								olditem.setMatStuffName(serial);	
								olditem.setWahoCode(wahoCode);
								olditem.setWahoName(wahoName);
						 
				 
								itemlist.add(olditem);
 
			  
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
		return itemlist;
	}	

//=============================================================================
	
	public List GetMaterialRTNDTList(String custcode,String docTypeCode, String docYear, String docMonth, String docNo	, String serial		 ) 
	throws Exception {	//01-06-2012
	
		List itemlist = new ArrayList();
		
		String matcode = "",  matname = "", matSearchName = "";
		String matTypeCode = "", matColorCode = "", matStuffCode = "", refMatCode = "", matStatus = "", pUnit = "", matBrandCode = "";
		String mobileno = "", rccode = "", wahoCode = "", wahoName = "" ,matGrpCode="";
		String issuqty = "",rtnqty = "",status = "", issuamount, sttotalamount; float totalamoung=0;
		
	 
		try {
			conn = agent.getConnectMYSql();
			
	 
			
			String sqlStmt = "SELECT a.doctypecode, b.doctypename, a.docyear, a.docmonth, a.docno, a.docdate,   " +
			"a.itemno, a.serial, a.matcode, a.matname, a.issuqty,a.rtnqty, a.priceperunit, a.issuamount, " + 
			"a.issustatus,a.wahocode,a.custcode ,a.status " +
			"FROM mmmatdt a " +
			"JOIN mmmathd e ON (a.doctypecode = e.doctypecode AND a.docyear = e.docyear AND a.docmonth = e.docmonth AND " +
			" a.docno = e.docno AND a.docdate = e.docdate  ) " +
			"JOIN mmdoctype b ON (a.doctypecode = b.doctypecode) " +
	 
			"WHERE " ;
			
			if (!custcode.equals("")) 		sqlStmt = sqlStmt + "a.custcode  		= '"+custcode+"'  ";
			if (!docTypeCode.equals(""))	sqlStmt = sqlStmt + "AND a.doctypecode = '"+docTypeCode+"'  ";
			if (!docYear.equals("")) 		sqlStmt = sqlStmt + "AND a.docyear 		= '"+docYear+"'  ";
			if (!docNo.equals("")) 			sqlStmt = sqlStmt + "AND a.docno 		= '"+docNo+"'  ";
			if (!serial.equals("")) 		sqlStmt = sqlStmt + "AND a.serial 		= '"+serial+"'  ";
			if (!docMonth.equals("")) 		sqlStmt = sqlStmt + "AND a.docmonth 	= '"+docMonth+"' ";				 

			
			sqlStmt = sqlStmt + "  ORDER BY a.doctypecode, a.docyear,a.docmonth, a.docno";
						
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {	
				
				MaterialTakeForm olditem = new MaterialTakeForm();	
				
				docTypeCode	= rs.getString("doctypecode");
				docYear 	= rs.getString("docyear");
				docNo 		= rs.getString("docno"); 
				docMonth 	= rs.getString("docmonth");
		//		date = dateUtil.CnvToDDMMYYYY(rs.getString("docdate")); 
				matcode 	= rs.getString("matcode");
				serial 	= rs.getString("serial");
				matname 	= rs.getString("matname");
				issuqty = rs.getString("issuqty");
				rtnqty = rs.getString("rtnqty");
				issuamount = rs.getString("issuamount");
				matStatus 	= rs.getString("issustatus");
				status = rs.getString("status");
				 
				wahoCode 	= rs.getString("wahocode");
				
				totalamoung = Float.parseFloat(rtnqty) * Float.parseFloat(issuamount);
				sttotalamount = Float.toString(totalamoung);
				olditem.setMark("");
				olditem.setStatus(status);
				olditem.setDocTypeCode(docTypeCode);
				olditem.setDocYear(docYear);
				olditem.setDocNo(docNo);
				olditem.setDocMonth(docMonth);
								olditem.setMatCode(matcode);
					 
								olditem.setTakecategories(custcode);
				 
								olditem.setMatName(matname);
					 
								olditem.setTakequantity(issuqty);
								olditem.setRtnqty(rtnqty);
					 
								olditem.setTakeamount(issuamount);
								
								olditem.setTaketotalamount(sttotalamount);
	 
								olditem.setMatStuffName(serial);	
								olditem.setWahoCode(wahoCode);
								olditem.setWahoName(wahoName);
						 
				 
								itemlist.add(olditem);
 
			  
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
		return itemlist;
	}	

//=============================================================================
}