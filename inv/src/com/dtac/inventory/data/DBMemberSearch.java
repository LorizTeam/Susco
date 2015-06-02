package com.dtac.inventory.data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dtac.utils.DateUtil;

import com.dtac.inventory.form.CollectionForm;
import com.dtac.inventory.form.MemberSearchForm;
import com.dtac.utils.DBConnect;

public class DBMemberSearch {
	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;
	DateUtil dateUtil = new DateUtil();
	
		// TODO Auto-generated constructor stub
	public String SelectRequestnoView(String requestno, String docTypeCode, String docyear) throws Exception {

		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT docno ,docyear FROM runingsale "+
					"WHERE doctype = '"+docTypeCode+"' AND docyear = '"+docyear+"' ";
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);		
			while (rs.next()) {
				requestno	= rs.getString("docno");
				requestno 	= String.valueOf(Integer.parseInt(requestno) + 1);
		 
				if (requestno.length() == 1) {
					requestno = "0000000" + requestno; 
				} else if (requestno.length() == 2) {
					requestno = "000000" + requestno; 
				} else if (requestno.length() == 3) {
					requestno = "00000" + requestno; 
				} else if (requestno.length() == 4) {
					requestno = "0000" + requestno; 
				} else if (requestno.length() == 5) {
					requestno = "000" + requestno; 
				} else if (requestno.length() == 6) {
					requestno = "00" + requestno; 
				} else if (requestno.length() == 7) {
					requestno = "0" + requestno; 
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
		return requestno;
		}
	public String SelectSemesterNo() throws Exception {
		String flag = ""; String semesterNo = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT semesterno FROM semester_no "+
					"WHERE flag = 'Y' ";
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);		
			while (rs.next()) {
				semesterNo = rs.getString("semesterno");
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
	return semesterNo;
	}
	public String SelectRequestno(String docyear, String doctypecode) throws Exception {
		String requestno = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM runingsale "+
					"WHERE docyear = '"+docyear+"' AND doctype = '"+doctypecode+"'";
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);		
			while (rs.next()) {
				requestno	= rs.getString("docno");
				requestno 	= String.valueOf(Integer.parseInt(requestno) + 1);
				DBMemberSearch upd = new DBMemberSearch();
				upd.UpdateRequestno(requestno, docyear, doctypecode);
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
		return requestno;
		}
	public void UpdateRequestno(String requestno, String docyear, String doctypecode) throws Exception { //03-05-2012
		
		try {
			conn = agent.getConnectMYSql();

			String sqlStmt = "UPDATE runingsale SET docno = '"+requestno+"' " +
			"WHERE docyear = '"+docyear+"' AND doctype = '"+doctypecode+"'";
			
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
	public String SelectDocNo(String docNo, String docYear) throws Exception {
		String chkdocNo = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM mmmathd "+
					"WHERE docno = '"+docNo+"' AND docyear = '"+docYear+"'";
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);		
			while (rs.next()) {
				if (rs.getString("docno") != null) chkdocNo	= rs.getString("docno"); else chkdocNo = "";
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
		return chkdocNo;
		}
	public String SelectRunNo(String docYear, String docTypeCode) throws Exception {
		String chkRundocNo = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM runingsale "+
					"WHERE docyear = '"+docYear+"' AND doctype = '"+docTypeCode+"'";
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);		
			while (rs.next()) {
				chkRundocNo	= rs.getString("docno");
				chkRundocNo 	= String.valueOf(Integer.parseInt(chkRundocNo) +1);
				
				if (chkRundocNo.length() == 1) {
					chkRundocNo = "0000000" + chkRundocNo; 
				} else if (chkRundocNo.length() == 2) {
					chkRundocNo = "000000" + chkRundocNo; 
				} else if (chkRundocNo.length() == 3) {
					chkRundocNo = "00000" + chkRundocNo; 
				} else if (chkRundocNo.length() == 4) {
					chkRundocNo = "0000" + chkRundocNo; 
				} else if (chkRundocNo.length() == 5) {
					chkRundocNo = "000" + chkRundocNo; 
				} else if (chkRundocNo.length() == 6) {
					chkRundocNo = "00" + chkRundocNo; 
				} else if (chkRundocNo.length() == 7) {
					chkRundocNo = "0" + chkRundocNo; 
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
		return chkRundocNo;
		}
	public String GetLastItemNo(String doctypecode, String docyear, String docno) 
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
	public void Additemhd(String doctypecode, String docyear, String docmonth, String docno, String studentName, String studentClass, String semesterNo, 
			String userID, String userName, String nowDate, String docStatus, float totpay, float totbalance) 
	throws Exception {	//11-04-2014
	
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO mmmathd (doctypecode, docyear, docmonth, docno, custcode, custname, wahocode, docbycode, docbyname, docdate, docstatus, totpay, totbalance) "+
			"VALUES ('"+doctypecode+"', '"+docyear+"', '"+docmonth+"', '"+docno+"', '"+studentName+"', '"+studentClass+"', '"+semesterNo+"', '"+userID+"', '"+userName+"', '"+nowDate+"', " +
					"'"+docStatus+"', '"+totpay+"', '"+totbalance+"')";
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
	public void Additemdt(String doctypecode, String docyear, String docno, String recno,String serial ,String matCode, String MatName, String matTypeCode, String matGrpCode, 
			 float quantity, float amount, float totalamount, String dateTime,String refmatcode) 
	throws Exception {	//14-04-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO mmmatdt (doctypecode, docyear, docno, itemno, serial,matcode, matname, wahocode, issuqty, issuamount, totalamount, docdate, datetime) " +
			"VALUES ('"+doctypecode+"', '"+docyear+"', '"+docno+"', '"+recno+"', '"+serial+"', '"+matCode+"', '"+MatName+"', '"+refmatcode+"', " +
					"'"+quantity+"', '"+amount+"', '"+totalamount+"', " +
					"'"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"', '"+dateTime+"')";
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
	public List Getcollectionlist(String collectcode, String collectname, String wahoCode) throws Exception {	//03-06-2012
			List collection = new ArrayList();
			String wahoName = "";
			Statement pStmt = null;
			ResultSet rs = null;
		try {			
			conn = agent.getConnectMYSql();
			String sqlStmt = "SELECT a.mattypecode, a.mattypename, a.wahocode, b.wahoname " +
					"FROM mmmattype a " +
					"JOIN mmwarehouse b ON (a.wahocode = b.wahocode) " +
					"WHERE a.mattypecode like '%"+collectcode+"%' AND a.mattypename like '%"+collectname+"%' " +
							"AND a.wahocode like '%"+wahoCode+"%' ";
					
					
					sqlStmt = sqlStmt + "ORDER BY a.mattypecode " ;	 
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				collectcode = rs.getString("mattypecode");
				collectname   = rs.getString("mattypename");
				wahoCode = rs.getString("wahocode");	
				wahoName = rs.getString("wahoname");
				collection.add(new CollectionForm(collectcode, collectname, wahoCode, wahoName)); 
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null)    rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return collection;
	}
	public void UpdateSemesterNo(String semesterNo) throws Exception {	//01-05-2012
		String flag1 = "Y", flag2 = "N";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE semester_no SET flag = '"+flag1+"' "+
			"WHERE semesterno = '"+semesterNo+"'";
						
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			
			sqlStmt = "UPDATE semester_no SET flag = '"+flag2+"' "+
			"WHERE semesterno != '"+semesterNo+"'";
						
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
	public void UpdateCollection(String collectcode, String collectname, String wahoCode) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmattype SET mattypename = '"+collectname+"', wahocode = '"+wahoCode+"' "+
			"WHERE mattypecode = '"+collectcode+"'";
						
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
	public void DeleteListhd(String docNo, String docYear) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			String sqlStmt = "DELETE FROM stockmovementhd WHERE docno = '"+docNo+"' AND docyear = '"+docYear+"'";
						
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
	public void DeleteListdt(String docNo, String docYear, String matCode, String itemNo) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			String sqlStmt = "DELETE FROM stockmovementdt WHERE docno = '"+docNo+"' AND docyear = '"+docYear+"' " +
					"AND matcode = '"+matCode+"' AND itemno = '"+itemNo+"'";
						
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
	/*	public List GetMemberDeleteList(String docTypeCode, String docNo, String docYear, String docMonth, String fromAmount, String toAmount) 
	throws Exception {	//01-06-2012
		List memberDeleteList = new ArrayList();
		String date = "", dateTime = "", docbycode = "", docbyname = "", studentName = "";
		float totalAmount = 0f;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT sum(b.totalamount) as sumamount, a.doctypecode, a.docyear, a.docmonth, a.docno, a.docdate, " +
			"a.docbycode, a.docbyname, a.stuname, b.datetime " +
			"FROM stockmovementhd a, stockmovementdt b " +
			"WHERE a.docno = b.docno AND " ;
			
			if (!docTypeCode.equals(""))	sqlStmt = sqlStmt + "a.doctypecode = '"+docTypeCode+"' ";
			if (!docYear.equals("")) 		sqlStmt = sqlStmt + "AND a.docyear 		= '"+docYear+"' ";
			if (!docNo.equals("")) 			sqlStmt = sqlStmt + "AND a.docno 		= '"+docNo.trim()+"' ";
			if (!docMonth.equals("")) 		sqlStmt = sqlStmt + "AND a.docmonth 	= '"+docMonth+"' ";
			if (!fromAmount.equals("")&&!toAmount.equals("")) 	sqlStmt = sqlStmt + "AND b.totalamount between '"+fromAmount+"' AND '"+toAmount+"' ";
			
			sqlStmt = sqlStmt + "Group By a.docno ORDER BY a.docyear, a.docmonth, sumamount ASC limit 20";
						
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {				
				docTypeCode	= rs.getString("doctypecode");
				docYear 	= rs.getString("docyear");
				docNo 		= rs.getString("docno"); 
				docMonth 	= rs.getString("docmonth");
				date 		= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("docdate"));
				dateTime 	= rs.getString("datetime");
				if (rs.getString("docbycode") != null) docbycode	= rs.getString("docbycode"); else docbycode = "";
				if (rs.getString("docbyname") != null) docbyname	= rs.getString("docbyname"); else docbyname = "";
				if (rs.getString("stuname") != null) studentName	= rs.getString("stuname"); else studentName = "-";
				totalAmount	= Float.parseFloat(rs.getString("sumamount"));
				
				memberDeleteList.add(new MemberSearchForm(docTypeCode, docNo, docYear, docMonth, date, dateTime, docbycode, docbyname, studentName, totalAmount));
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
		return memberDeleteList;
	}  */
	public List GetMemberDeleteList(String docTypeCode, String docNo, String docYear, String docMonth, String fromAmount, String matGrpCode) 
		throws Exception {	//01-06-2012
			List memberDeleteList = new ArrayList();
			String date = "", dateTime = "", docbycode = "", docbyname = "", studentName = "", studentClass = "", matGrpName = "", semesterNo = "";
			float totalAmount = 0f;
			try {
				conn = agent.getConnectMYSql();
				
				String sqlStmt = "SELECT sum(b.totalamount) as sumamount, b.totalamount, a.doctypecode, a.docyear, a.docmonth, a.docno, a.docdate, " +
				"a.docbycode, a.docbyname, a.stuname, a.stuclass, a.semesterno, b.datetime, c.matgrpcode, d.matgrpname " +
				"FROM stockmovementhd a join stockmovementdt b inner join mmmaterial c on(b.matcode = c.matcode) " +
				"left join mmmatgrp d on(c.matgrpcode = d.matgrpcode) " +
				"WHERE a.docno = b.docno AND " ;
				
				if (!docTypeCode.equals(""))	sqlStmt = sqlStmt + "a.doctypecode = '"+docTypeCode+"' ";
				if (!docYear.equals("")) 		sqlStmt = sqlStmt + "AND a.docyear 		= '"+docYear+"' ";
				if (!docNo.equals("")) 			sqlStmt = sqlStmt + "AND a.docno 		= '"+docNo.trim()+"' ";
				if (!docMonth.equals("")) 		sqlStmt = sqlStmt + "AND a.docmonth 	= '"+docMonth+"' ";
				if (!matGrpCode.equals("")) 	sqlStmt = sqlStmt + "AND c.matgrpcode = '"+matGrpCode+"' ";
				if (!fromAmount.equals("")){
				sqlStmt = sqlStmt + "Group By a.docno HAVING sum(b.totalamount) = '"+fromAmount+"' ORDER BY a.docno DESC, a.docyear, a.docmonth limit 100";	
				}else{
				sqlStmt = sqlStmt + "Group By a.docno ORDER BY a.docno DESC, a.docyear, a.docmonth limit 100";
				}			
				//System.out.println(sqlStmt);				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);			
				while (rs.next()) {				
					docTypeCode	= rs.getString("doctypecode");
					docYear 	= rs.getString("docyear");
					docNo 		= rs.getString("docno"); 
					docMonth 	= rs.getString("docmonth");
					date 		= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("docdate"));
					dateTime 	= rs.getString("datetime");
					if (rs.getString("docbycode") != null) docbycode	= rs.getString("docbycode"); else docbycode = "";
					if (rs.getString("docbyname") != null) docbyname	= rs.getString("docbyname"); else docbyname = "";
					if (rs.getString("stuname") != null) studentName	= rs.getString("stuname"); else studentName = "-";
					if (rs.getString("stuclass") != null) studentClass	= rs.getString("stuclass"); else studentClass = "-";
					semesterNo 	= rs.getString("semesterno");
					totalAmount	= Float.parseFloat(rs.getString("sumamount"));
					matGrpCode 	= rs.getString("matgrpcode");
					if (rs.getString("matgrpname") != null) matGrpName	= rs.getString("matgrpname"); else matGrpName = "";
					
					memberDeleteList.add(new MemberSearchForm(docTypeCode, docNo, docYear, docMonth, date, dateTime, docbycode, docbyname, 
							studentName, studentClass, totalAmount, matGrpCode, matGrpName, semesterNo));
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
			return memberDeleteList;
	}
	public List GetMemberDeleteListdt(String docTypeCode, String docNo, String docYear) 
	throws Exception {	//01-06-2012
		List memberDeleteListdt = new ArrayList();
		String itemNo = "", date = "", dateTime= "", matCode = "", matName = "", qty = "";
		float amount = 0f, totalAmount = 0f;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.doctypecode, a.docyear, a.docno, a.itemno, a.date, a.datetime, " +
			"a.matcode, b.matname, a.qty, a.amount, a.totalamount " +
			"FROM stockmovementdt a right join mmmaterial b on(a.matcode = b.matcode)" +
			"WHERE " ;
			
			if (!docTypeCode.equals(""))	sqlStmt = sqlStmt + "a.doctypecode = '"+docTypeCode+"' AND ";
			if (!docYear.equals("")) 		sqlStmt = sqlStmt + "a.docyear 		= '"+docYear+"' AND ";
			if (!docNo.equals("")) 			sqlStmt = sqlStmt + "a.docno 		= '"+docNo.trim()+"' AND ";
			
			sqlStmt = sqlStmt + "a.status <> '' ORDER BY a.docyear, a.docno";
						
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {				
				docTypeCode	= rs.getString("doctypecode");
				docYear 	= rs.getString("docyear");
				docNo 		= rs.getString("docno");
				itemNo		= rs.getString("itemno");
				date 		= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("date"));
				dateTime 	= rs.getString("datetime");
				if (rs.getString("matcode") != null) matCode	= rs.getString("matcode"); else matCode = "";
				if (rs.getString("matname") != null) matName	= rs.getString("matname"); else matName = "";
				qty			= rs.getString("qty");
				amount 		= Float.parseFloat(rs.getString("amount"));
				totalAmount = Float.parseFloat(rs.getString("totalamount"));
				
				memberDeleteListdt.add(new MemberSearchForm(docTypeCode, docNo, docYear, itemNo, date, dateTime, matCode, matName, qty, amount, totalAmount));
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
		return memberDeleteListdt;
	}
	public String SelectStudent(String docNo, String docYear) throws Exception {
		String studentName = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM stockmovementhd "+
					"WHERE docno = '"+docNo+"' AND docyear = '"+docYear+"'";
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);		
			while (rs.next()) {
				if (rs.getString("stuname") != null) studentName	= rs.getString("stuname"); else studentName = "";
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
		return studentName;
		}
	public void UpdateStudentName(String docNo, String docYear, String docMonth, String stuName, String stuClass, String arSemesterNo) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE stockmovementhd SET stuname = '"+stuName+"', stuclass = '"+stuClass+"', semesterNo = '"+arSemesterNo+"' "+
			"WHERE docno = '"+docNo+"' AND docyear = '"+docYear+"' AND docmonth = '"+docMonth+"'";
						
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
