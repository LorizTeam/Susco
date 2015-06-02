/*
 * Created on 21-08-2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.employee.data;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dtac.employee.form.FingPrntForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBFingPrnt {

	DBConnect agent 	= new DBConnect();
	Connection conn 	= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public List GetFingPrntBRList(String empID, String checkTime) throws Exception { //03-09-2012
		List fpForm = new ArrayList();
		String userID = "", checkType = "";
		try {			
			conn = agent.getConnectTimeAttend("att2000");
			
			String tmp1	= dateUtil.CnvToYYYYMMDD(checkTime, '/');
			String tmp2	= dateUtil.CnvToMMDDYYYY(tmp1);
			
			String sqlStmt = "SELECT a.checktime, a.userid, a.checktype,  IIf(IsNull(b.ssn),'',b.ssn) AS ssn " +
			"FROM checkinout a, userinfo b WHERE a.userid = b.userid AND " +
			"a.checktime >= #"+tmp2+" 00:00:00# AND " +
			"a.checktime <= #"+tmp2+" 23:59:59# ";
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND b.ssn = '"+empID.trim()+"' ";
			
			sqlStmt = sqlStmt + "ORDER BY b.ssn, a.checktime ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				userID 		= rs.getString("userid");
				checkTime	= rs.getString("checktime");
				empID 		= rs.getString("ssn");
				checkType	= rs.getString("checktype");
				//if (!rs.getString("ssn").equals(null)) empID = rs.getString("ssn"); else empID = ""; //not work in access
				if (empID.trim().length() == 5) 
					fpForm.add(new FingPrntForm(empID, userID, checkTime, checkType));
				//System.out.println(checkTime+" "+userID+" "+empID.substring(3, 10));
				
			}
			/*rs.close();
			pStmt.close();
			conn.close();*/
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
		return fpForm;
	}
	public List GetFingPrntTPList(String empID, String checkTime) throws Exception { //03-09-2012
		List fpForm = new ArrayList();
		String userID = "", checkType = "";
		try {			
			conn = agent.getConnectTimeAttend("worksystem");
			
			String tmp1	= dateUtil.CnvToYYYYMMDD(checkTime, '-');
			//String tmp2	= dateUtil.CnvToMMDDYYYY(tmp1);
			
			String sqlStmt = "SELECT sign_time, IIf(IsNull(emp_id),'',emp_id) AS empid FROM TimeRecords " +			
			"WHERE Format([sign_time],'yyyy-mm-dd') = '"+tmp1+"'  ";
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND emp_id = '"+empID.trim()+"' ";
			
			sqlStmt = sqlStmt + "ORDER BY emp_id, sign_time ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);			
			while (rs.next()) {
				userID 		= rs.getString("empid");
				checkTime	= rs.getString("sign_time");
				empID 		= userID;
				//checkType	= rs.getString("checktype");
				//if (!rs.getString("ssn").equals(null)) empID = rs.getString("ssn"); else empID = ""; //not work in access
				if (empID.trim().length() == 5) 
					fpForm.add(new FingPrntForm(empID, userID, checkTime, checkType));
				//System.out.println(checkTime+" "+userID+" "+empID.substring(3, 10));
				
			}
			/*rs.close();
			pStmt.close();
			conn.close();*/
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
		return fpForm;
	}
	public List GetFingPrntList(String workDate, String empID, String empTypeCode, String empDeptCode, 
		String offiCode) 
	throws Exception {	//01-11-2011
		List fingPrntList = new ArrayList();
		String empNameT	= "", empLastNameT	= "";
		String empNameE	= "", empLastNameE	= "";	 
		String startTime= "", stopTime		= "", dayName = ""; 
		String empDeptName= "", empTypeName = "", offiName = "";
		String normal	= "", otN1	= "", otN1_5= "", otN2	= "", otN3	= "";				
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.workdate, a.empid, a.workdate, a.officode, " +
			"TIME(a.starttime) AS starttime, TIME(a.stoptime) AS stoptime, " +
			"a.normal, a.otn1, a.otn1_5, a.otn2, a.otn3, " +
			"b.deptcode, b.empnamet, b.emplastnamet, c.dayenabbvname, o.thdesc AS offiname " +			
			"FROM hrmtimefp a " +
			"JOIN employee b ON (a.empid = b.empid) " +
			"JOIN hrmday c ON (c.dayno = DAYOFWEEK(a.workdate)) " +
			"LEFT JOIN mastertabledt o ON (a.officode = o.typecode AND o.grpcode = 'offi') " +
			"WHERE ";  
			
			if (!workDate.equals("")) sqlStmt = sqlStmt + "a.workdate = '"+workDate+"' AND ";			
			if (!empID.equals("")) 	sqlStmt = sqlStmt + "a.empid = '"+empID.trim()+"' AND ";
			if (!empTypeCode.equals("")) sqlStmt = sqlStmt + "b.typecode = '"+empTypeCode.trim()+"' AND ";
			if (!empDeptCode.equals("")) sqlStmt = sqlStmt + "b.deptcode = '"+empDeptCode.trim()+"' AND ";
			if (!offiCode.equals("")) sqlStmt = sqlStmt + "a.officode = '"+offiCode+"' AND ";
			
			sqlStmt = sqlStmt + " a.empid <> '00000' ORDER BY a.workdate, b.typecode, b.deptcode, b.empid ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				empDeptCode	= rs.getString("deptcode");
				empID		= rs.getString("empid");				
				if (rs.getString("empnamet") != null) empNameT = rs.getString("empnamet"); else empNameT = "";
				if (rs.getString("emplastnamet") != null) empLastNameT = rs.getString("emplastnamet"); 
				else empLastNameT = "";
				if (rs.getString("officode") != null) offiCode 	= rs.getString("officode"); else offiCode = "";
				if (rs.getString("offiname") != null) offiName 	= rs.getString("offiname"); else offiName = "";
				
				if (rs.getString("workdate") != null) workDate = dateUtil.CnvToDDMMYYYY(rs.getString("workdate"));
				else workDate = "";
				dayName 	= rs.getString("dayenabbvname");
				if (rs.getString("starttime") != null) startTime = rs.getString("starttime"); else startTime = "";
				if (rs.getString("stoptime") != null) stopTime = rs.getString("stoptime"); else stopTime = "";
				
				if (rs.getString("normal") != null) normal = rs.getString("normal"); else normal = "";
				if (rs.getString("otN1") != null)  	otN1 = rs.getString("otN1");  	 else otN1 = "";
				if (rs.getString("otN1_5") != null) otN1_5 = rs.getString("otN1_5"); else otN1_5 = "";
				if (rs.getString("otN2") != null)  	otN2 = rs.getString("otN2");  	 else otN2 = "";
				if (rs.getString("otN3") != null)  	otN3 = rs.getString("otN3");  	 else otN3 = "";
				
				
				fingPrntList.add(new FingPrntForm(empID, workDate, empNameT, empLastNameT, empNameE, empLastNameE,
					empTypeCode, empTypeName, empDeptCode, empDeptName, offiCode, offiName,
					dayName, startTime, stopTime, normal, otN1, otN1_5, otN2, otN3));				
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
		return fingPrntList;
	}
	public void UpdateSalType(String empID, String checkTime) throws Exception { //13-11-2011
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE hrmtimefp a, employee b, emptype c SET a.saltypecode = c.typeemp " +
			"WHERE a.empid = b.empid AND b.typecode = c.typecode AND " +
			"a.workdate = '"+checkTime.trim()+"' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	
			//==========================================================================================
			sqlStmt = "UPDATE hrmtimefp a SET a.amstatus = '-', a.pmstatus = '-', a.wkstatus = '-', " +
			"a.hr08_12 = '00:00:00', a.hr13_17 = '00:00:00' " +			
			"WHERE a.workdate = '"+checkTime.trim()+"' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==========================================================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b SET a.amstatus = 'w', a.hr08_12 = '04:00:00' " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"concat(b.workdate,' ',b.timestart) >= a.starttime AND a.workdate = '"+checkTime.trim()+"' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==========================================================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b SET a.amstatus = 'a', a.hr08_12 = '00:00:00' " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"concat(b.workdate,' ',b.timestart) < a.starttime AND a.workdate = '"+checkTime.trim()+"' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==========================================================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b SET a.pmstatus = 'w', a.hr13_17 = '04:00:00' " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"concat(b.workdate,' ',b.timestop) <= a.stoptime AND a.workdate = '"+checkTime.trim()+"' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==========================================================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b SET a.pmstatus = 'a', a.hr13_17 = '00:00:00' " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"concat(b.workdate,' ',b.timestop) > a.stoptime AND a.workdate = '"+checkTime.trim()+"' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==========================================================================================
			sqlStmt = "UPDATE hrmtimefp a SET a.wkstatus = 'w' " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.amstatus = 'w' AND a.pmstatus = 'w' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
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
	public void CalAmountOT(String empID, String checkTime) throws Exception { //13-11-2011
		try {
			conn = agent.getConnectMYSql();
			
			String 
			//==========================================================================================
			sqlStmt = "UPDATE hrmtimefp a SET a.ot00_08 = 0, a.ot17_21 = 0, a.ot21_24 = 0, " +
			"a.hr00_08 = '00:00:00',  a.hr17_21 = '00:00:00', a.hr21_24 = '00:00:00', a.amlate = '00:00:00' " +
			"WHERE a.workdate = '"+checkTime.trim()+"' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//=========================================================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b " +
			"SET a.hr00_08 = timediff(concat(b.workdate,' ',b.timestart), a.starttime) " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"a.workdate = '"+checkTime.trim()+"' AND a.amstatus = 'w' AND TIME(a.starttime) <= '06:00:00' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//=========================================================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b " +
			"SET a.hr08_12 = timediff(concat(b.workdate,' ','12:00:00'), a.starttime) " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"a.workdate = '"+checkTime.trim()+"' AND a.amstatus = 'a' AND TIME(a.starttime) <= '12:00:00' AND " +
			"TIME(a.starttime) >= '08:00:00' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//=========================================================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b " +
			"SET a.hr13_17 = timediff(a.stoptime, concat(b.workdate,' ','13:00:00')) " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'a' AND TIME(a.stoptime) >= '13:00:00' AND " +
			"TIME(a.stoptime) <= '17:00:00' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//=========================================================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b " +
			"SET a.hr17_21 = timediff(a.stoptime, concat(b.workdate,' ',b.timestop)) " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND TIME(a.stoptime) <= '21:00:00' AND " +
			"TIME(a.stoptime) > '18:00:00' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==============================================stop time after 22=========================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b " +
			"SET a.hr17_21 = timediff(concat(b.workdate,' ','21:00:00'), concat(b.workdate,' ',b.timestop)) " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND TIME(a.stoptime) > '21:00:00' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//=========================================================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b " +
			"SET a.hr21_24 = timediff(a.stoptime, concat(b.workdate,' ','21:00:00')) " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND TIME(a.stoptime) > '21:00:00' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//==============================Late=======================================================
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b " +
			"SET a.amlate = timediff(a.starttime, concat(b.workdate,' ',b.timestart)) " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"a.workdate = '"+checkTime.trim()+"' AND TIME(a.starttime) > b.timestart ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			
			//======================================Normal=============================================
			sqlStmt = "UPDATE hrmtimefp a SET a.hr08_17 = ADDTIME(a.hr08_12, a.hr13_17) " +
			"WHERE a.workdate = '"+checkTime.trim()+"' ";				
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//============================
			sqlStmt = "UPDATE hrmtimefp a SET a.normal = HOUR(a.hr08_17) + 0.5 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND MINUTE(a.hr08_17) >= 30 AND MINUTE(a.hr08_17) < 55 ";	
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================			
			sqlStmt = "UPDATE hrmtimefp a SET a.normal = HOUR(a.hr08_17) " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND MINUTE(a.hr08_17) < 30 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================			
			sqlStmt = "UPDATE hrmtimefp a SET a.normal = HOUR(a.hr08_17) + 1 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND MINUTE(a.hr08_17) >= 55 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			

			//======================================OT Hour 00 - 08====================================
			sqlStmt = "UPDATE hrmtimefp a SET a.ot00_08 = HOUR(a.hr00_08) + 0.5 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.amstatus = 'w' AND " +
			"MINUTE(a.hr00_08) >= 30 AND MINUTE(a.hr00_08) < 55 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================			
			sqlStmt = "UPDATE hrmtimefp a SET a.ot00_08 = HOUR(a.hr00_08) " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.amstatus = 'w' AND " +
			"MINUTE(a.hr00_08) < 30 ";							
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================			
			sqlStmt = "UPDATE hrmtimefp a SET a.ot00_08 = HOUR(a.hr00_08) + 1 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.amstatus = 'w' AND " +
			"MINUTE(a.hr00_08) >= 55 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			
			//======================================OT Hour 17 - 21====================================
			sqlStmt = "UPDATE hrmtimefp a SET a.ot17_21 = HOUR(a.hr17_21) + 0.5 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND " +
			"MINUTE(a.hr17_21) >= 30 AND MINUTE(a.hr17_21) < 55 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================			
			sqlStmt = "UPDATE hrmtimefp a SET a.ot17_21 = HOUR(a.hr17_21) " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND " +
			"MINUTE(a.hr17_21) < 30 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================			
			sqlStmt = "UPDATE hrmtimefp a SET a.ot17_21 = HOUR(a.hr17_21) + 1 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND " +
			"MINUTE(a.hr17_21) >= 55 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================			
			sqlStmt = "UPDATE hrmtimefp a SET a.ot17_21 = a.ot17_21 - 0.5 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND " +
			"a.ot17_21 > 0.5 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			
			//======================================OT Hour 21 - 24====================================
			sqlStmt = "UPDATE hrmtimefp a SET a.ot21_24 = HOUR(a.hr21_24) + 0.5 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND " +
			"MINUTE(a.hr21_24) >= 30 AND MINUTE(a.hr21_24) < 55 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================			
			sqlStmt = "UPDATE hrmtimefp a SET a.ot21_24 = HOUR(a.hr21_24) " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND " +
			"MINUTE(a.hr21_24) < 30 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================			
			sqlStmt = "UPDATE hrmtimefp a SET a.ot21_24 = HOUR(a.hr21_24) + 1 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND " +
			"MINUTE(a.hr21_24) >= 55 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================	18-03-2012		
			sqlStmt = "UPDATE hrmtimefp a SET a.ot21_24 = a.ot21_24 - 0.5, a.ot17_21 = 4 " +
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.pmstatus = 'w' AND " +
			"a.ot17_21 = 3.5 AND a.ot21_24 > 0 ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
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
	public void CalOTDairy(String empID, String checkTime, String dayNo, String dayTypeCode) 
	throws Exception { //31-10-2011
		try {
			conn = agent.getConnectMYSql();
			String sqlStmt = "";
			
			if (!dayNo.equals("1") && dayTypeCode.equals("1")) { //1.1.1   24-04-2012
						
				sqlStmt = "UPDATE hrmtimefp a SET a.otn1 = 0, a.otn1_5 = 0, " +
				"a.otn2 = 0, a.otn3 = a.ot17_21 + a.ot00_08 + a.ot21_24 " +
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//=================================
				sqlStmt = "UPDATE hrmtimefp a SET a.otn1_5 = a.otn3, a.otn2 = 0 " +
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' AND a.otn3 <= 4 AND a.otn3 > 0 ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//=================================
				sqlStmt = "UPDATE hrmtimefp a SET a.otn1_5 = 4, a.otn2 = a.otn3 - 4 " +
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' AND a.otn3 > 4 ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//=================================
				sqlStmt = "UPDATE hrmtimefp a SET a.otn3 = 0 " +
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
			} else if (dayNo.equals("1")) {	//1.1.2  18-03-2012
				sqlStmt = "UPDATE hrmtimefp a SET a.otn1 = a.ot00_08 + a.normal + a.ot17_21 + a.ot21_24, " +
				"a.otn1_5 = 0, a.otn2 = 0, a.otn3 = 0 " +				
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//=================================
				sqlStmt = "UPDATE hrmtimefp a SET a.otn2 = a.otn1 " +				
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' AND a.otn1 <= 8 AND a.otn1 > 0 ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//=================================
				sqlStmt = "UPDATE hrmtimefp a SET a.otn3 = a.otn1 - 8, a.otn2 = 8 " +				
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' AND a.otn1 > 8 ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//=================================
				sqlStmt = "UPDATE hrmtimefp a SET a.otn1 = 0, a.normal = 0 " +				
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				//System.out.println("");
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				
			} else if (dayTypeCode.equals("0")) { //1.1.3  19-03-2012
				sqlStmt = "UPDATE hrmtimefp a SET a.otn1_5 = a.ot00_08 + a.normal + a.ot17_21 + a.ot21_24, " +
				"a.otn1 = 0, a.otn2 = 0, a.otn3 = 0 " +				
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//==================================
				sqlStmt = "UPDATE hrmtimefp a SET a.otn1 = a.otn1_5 " +				
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' AND a.otn1_5 <= 8 AND a.otn1_5 > 0 ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//==================================
				sqlStmt = "UPDATE hrmtimefp a SET a.otn3 = a.otn1_5 - 8, a.otn1 = 8 " +				
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' AND a.otn1_5 > 8 ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//==================================
				sqlStmt = "UPDATE hrmtimefp a SET a.otn1_5 = 0, a.normal = 8 " +				
				"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '0' ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);

			}
			
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
	public void CalOTMonthly(String empID, String checkTime, String dayNo, String dayTypeCode) 
	throws Exception { //19-03-2012
		try {
			conn = agent.getConnectMYSql();
			String sqlStmt = "";
			
			if (!dayNo.equals("1") && dayTypeCode.equals("1")) { //2.1.1
						
				sqlStmt = "UPDATE hrmtimefp a, employee b SET a.otn1 = 0, a.otn2 = 0, a.otn3 = 0, " +
				"a.otn1_5 = a.ot17_21 + a.ot00_08 + a.ot21_24 " +				
				"WHERE a.empid = b.empid AND b.typecode NOT IN ('AC','EN') AND " +
				"a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '1' ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				
			} else if (dayNo.equals("1") || dayTypeCode.equals("0")) {	//2.1.2
				sqlStmt = "UPDATE hrmtimefp a, employee b SET a.otn1_5 = a.ot00_08 + a.normal + a.ot17_21 + a.ot21_24, " +
				"a.otn1 = 0, a.otn2 = 0, a.otn3 = 0 " +				
				"WHERE a.empid = b.empid AND b.typecode NOT IN ('AC','EN') AND " +
				"a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '1' ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//==================================
				sqlStmt = "UPDATE hrmtimefp a, employee b SET a.otn1 = a.otn1_5 " +				
				"WHERE a.empid = b.empid AND b.typecode NOT IN ('AC','EN') AND " +
				"a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '1' AND a.otn1_5 <= 8 AND a.otn1_5 > 0 ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//==================================
				sqlStmt = "UPDATE hrmtimefp a, employee b SET a.otn3 = a.otn1_5 - 8, a.otn1 = 8 " +				
				"WHERE a.empid = b.empid AND b.typecode NOT IN ('AC','EN') AND " +
				"a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '1' AND a.otn1_5 > 8 ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				//==================================
				sqlStmt = "UPDATE hrmtimefp a, employee b SET a.otn1_5 = 0 " +				
				"WHERE a.empid = b.empid AND b.typecode NOT IN ('AC','EN') AND " +
				"a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '1' ";
				if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				
			} 
			//==normal=8 monthly sunday,holiday=======
			sqlStmt = "UPDATE hrmtimefp a, hrmcalendar b SET a.normal = 8 " +
			"WHERE a.workdate = b.workdate AND a.saltypecode = b.saltypecode AND b.saltypecode = '1' AND " +
			"a.workdate = '"+checkTime.trim()+"' AND (b.dayno = '1' OR b.daytype = '0') ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);

			//==type AC, EN no OT==============================================================
			sqlStmt = "UPDATE hrmtimefp a, employee b SET a.otn1 = 0, a.otn1_5 = 0, a.otn2 = 0, a.otn3 = 0 " +
			"WHERE a.empid = b.empid AND b.typecode IN ('AC','EN') AND " +
			"a.workdate = '"+checkTime.trim()+"' ";
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
			
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

	public void CopyToPayTrans_(String empID, String checkTime, String salTypeCode, String loginId,
		String year, String month, String periodNo) 
	throws Exception { //06-03-2012
		try {
			conn = agent.getConnectMYSql();

			String sqlStmt = "INSERT INTO paytrans (transdate, empid, itemno, status, projectid, subjobid, " +
			"normal, otn1, otn1_5, otn2, otn3, saltypecode, inputdate, updatedate, updateby, year, month, period) " +
			"(SELECT a.workdate, a.empid, '01', 'T', b.projectid, b.subjobid, " +
			"a.normal, a.otn1, a.otn1_5, a.otn2, a.otn3, a.saltypecode, " +
			"'"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'-')+"'," +
			"'"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(),'-')+"', '"+loginId+"', " +
			"'"+year+"', '"+month+"', '"+periodNo+"' FROM hrmtimefp a, employee b " + 
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.saltypecode = '"+salTypeCode+"' AND ";
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "a.empid = '"+empID.trim()+"' AND ";
			
			sqlStmt = sqlStmt + "a.empid = b.empid AND CONCAT(a.workdate,a.empid,'01') NOT IN " +
					"(SELECT CONCAT(transdate,empid,itemno) FROM paytrans))";
				
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	
			//========fare 10 baht when ot > 1.5 hour===========================================
			sqlStmt = "UPDATE paytrans a, hrmcalendar b SET a.special4 = 10 " +			
			"WHERE a.transdate = b.workdate AND a.saltypecode = b.saltypecode AND " +
			"b.saltypecode = '"+salTypeCode+"' AND b.daytype = '1' AND " +
			"a.itemno = '01' AND a.status = 'T' AND a.otn1_5 >= 1.5 AND " +						 
			"b.workdate = '"+checkTime.trim()+"' ";
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "AND a.empid = '"+empID.trim()+"' ";
				
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
	public void CopyToWorkTime(String empID, String checkTime, String salTypeCode) throws Exception { //06-12-2011
		try {
			conn = agent.getConnectMYSql();

			String sqlStmt = "INSERT IGNORE INTO hrmworktime " +
					"(workdate, empid,    starttime,  stoptime,  r, 	  otn1,  otn1_5,  otn2,  otn3, amlate) " +
			"(SELECT a.workdate,a.empid,a.starttime,a.stoptime,a.normal,a.otn1,a.otn1_5,a.otn2,a.otn3, a.amlate " +
			"FROM hrmtimefp a, employee b " + 
			"WHERE a.workdate = '"+checkTime.trim()+"' AND a.empid = b.empid AND a.workdate >= b.saleffdate AND " +
			"(a.workdate <= b.salenddate OR b.salenddate IS NULL) AND ";
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "a.empid = '"+empID.trim()+"' AND ";
			
			sqlStmt = sqlStmt + "a.saltypecode = '"+salTypeCode+"' AND " +
			"CONCAT(a.workdate,a.empid) NOT IN (SELECT CONCAT(workdate,empid) FROM hrmworktime " +
			"WHERE workdate = '"+checkTime.trim()+"'))";
			
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);

			//================================================================================
			sqlStmt = "UPDATE hrmworktime a, hrmtimefp b " +
			"SET a.starttime = b.starttime, a.stoptime = b.stoptime " +
			"WHERE a.workdate = b.workdate AND a.empid = b.empid AND " + 
			"a.workdate = '"+checkTime.trim()+"' AND ";
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "a.empid = '"+empID.trim()+"' AND ";
			
			sqlStmt = sqlStmt + "b.saltypecode='"+salTypeCode+"' AND (a.starttime IS NULL OR a.stoptime IS NULL)";
							
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//===========================cal late==============================================			
			sqlStmt = "UPDATE hrmworktime a, hrmtimefp b SET a.l = MINUTE(a.amlate) / 60 " +
			"WHERE a.workdate = b.workdate AND a.empid = b.empid AND a.workdate = '"+checkTime.trim()+"' AND ";
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "a.empid = '"+empID.trim()+"' AND ";
			
			sqlStmt = sqlStmt + "b.saltypecode = '"+salTypeCode+"' AND a.amlate > '00:00:00' ";
							
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//====================set ot in minute = 0 20-08-2012===============================			
			sqlStmt = "UPDATE hrmworktime a, hrmtimefp b SET a.amotn1 = '00:00:00', a.amotn1_5 = '00:00:00', " +
			"a.amotn2 = '00:00:00', a.amotn3 = '00:00:00' " +
			"WHERE a.workdate = b.workdate AND a.empid = b.empid AND a.workdate = '"+checkTime.trim()+"' AND ";
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "a.empid = '"+empID.trim()+"' AND ";
			
			sqlStmt = sqlStmt + "b.saltypecode = '"+salTypeCode+"' ";
							
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//===========================cal ot in hr:mm==============================================			
			sqlStmt = "UPDATE hrmworktime a, hrmtimefp b SET " +
			"a.amotn1   = ADDTIME(a.amotn1,   concat(floor(a.otn1),':',mod(a.otn1*60,60))), " +
			"a.amotn1_5 = ADDTIME(a.amotn1_5, concat(floor(a.otn1_5),':',mod(a.otn1_5*60,60))), " +
			"a.amotn2   = ADDTIME(a.amotn2,   concat(floor(a.otn2),':',mod(a.otn2*60,60))), " +
			"a.amotn3   = ADDTIME(a.amotn3,   concat(floor(a.otn3),':',mod(a.otn3*60,60))) " +
			"WHERE a.workdate = b.workdate AND a.empid = b.empid AND a.workdate = '"+checkTime.trim()+"' AND ";
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "a.empid = '"+empID.trim()+"' AND ";
			
			sqlStmt = sqlStmt + "b.saltypecode = '"+salTypeCode+"' ";
							
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