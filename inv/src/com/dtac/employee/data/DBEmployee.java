/*
 * Created on 24-09-2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.employee.data;
import java.io.IOException;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.dtac.employee.form.EmployeeForm;
import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBEmployee {

	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	
	public List GetEmployeeList(String empID, String empNameT, String empLastNameT, String empTypeCode,
		String empDeptCode, String offiCode, String status) 
	throws Exception {	//09-02-2013
		List empList = new ArrayList();
		
		String empNameE	= "", empLastNameE	= "", birthDay = "", expDate = "", workDate = "";
		String empDeptName = "", empTypeName = "", oldEmpID= "", punchCard="";
		String address1 = "", address2 = "", tel1 = "", email1 = "", nation = "", sex = "";
		String marryStatus = "", allChild = "", edcChild = "", deductType = "";
		String educCode = "", major = "", groupName = "", pfundDate = "", pfFlag = "";
		String accountNo= "", taxID = "", idPop = "", socialNo = "", insure = "";
		String devote 	= "", interestLoan = "";
		String projCode	= "", subProjCode = "", offiName = "";
		try {
			DecimalFormat df1 = new DecimalFormat("#0");
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.empid, a.typecode, a.empnamet, a.emplastnamet, a.birthday, " +
			"a.empnamee, a.emplastnamee, a.idpop, a.taxid, a.socialno, a.accountno, a.deducttype, " +
			"a.nation, a.address1, a.address2, a.tel1, a.sex, a.allchild, a.edcchild, a.marrystatus, " +
			"a.deptcode, a.edcode, a.major, a.groupname, a.projectid, a.subjobid, a.pfflag, a.pfunddate, " +
			"a.insure, a.devote, a.interestloan, a.punchcard, " +
			"b.typename, c.thdesc AS deptname, a.workdate, a.expdate, a.status, a.oldempid, a.officode, " +
			"o.thdesc AS offiname " +
			"FROM emptype b " +
			"JOIN employee a ON (a.typecode = b.typecode) " +
			"LEFT JOIN mastertabledt c ON (a.deptcode = c.typecode AND c.grpcode = 'dept') " +
			"LEFT JOIN mastertabledt o ON (a.officode = o.typecode AND o.grpcode = 'offi') " +
			"WHERE ";  
			
			if (!empID.equals("")) sqlStmt = sqlStmt + "a.empid like '"+empID.trim()+"%' AND ";
			if (!empTypeCode.equals("")) sqlStmt = sqlStmt + "a.typecode = '"+empTypeCode.trim()+"' AND ";
			if (!empDeptCode.equals("")) sqlStmt = sqlStmt + "a.deptcode = '"+empDeptCode.trim()+"' AND ";
			if (!empNameT.equals("")) sqlStmt = sqlStmt + "a.empnamet like '%"+empNameT.trim()+"%' AND ";
			if (!empLastNameT.equals("")) sqlStmt = sqlStmt + "a.emplastnamet like '%"+empLastNameT.trim()+"%' AND ";
			if (!offiCode.equals("")) sqlStmt = sqlStmt + "a.officode = '"+offiCode+"' AND ";
			if (!status.equals("")) sqlStmt = sqlStmt + "a.status = '"+status+"' AND ";
			
			sqlStmt = sqlStmt + " a.empid <> '00000' ORDER BY a.empid ";			

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				empID		= rs.getString("empid");
				oldEmpID	= rs.getString("oldempid");
				empTypeCode = rs.getString("typecode");
				empTypeName	= rs.getString("typename");
				empDeptCode	= rs.getString("deptcode");
				empDeptName	= rs.getString("deptname");			
				punchCard	= rs.getString("punchcard");
				offiCode 	= rs.getString("officode");
				offiName 	= rs.getString("offiname");
				status		= rs.getString("status");
				
				if (rs.getString("empnamet") != null) 	empNameT = rs.getString("empnamet"); else empNameT = "";
				if (rs.getString("emplastnamet") != null) empLastNameT = rs.getString("emplastnamet"); 
				else empLastNameT = "";
				if (rs.getString("empnamee") != null) 	empNameE = rs.getString("empnamee"); else empNameE = "";
				if (rs.getString("emplastnamee") != null) empLastNameE = rs.getString("emplastnamee"); 
				else empLastNameE = "";
				
				if (rs.getString("address1") != null) 	address1 = rs.getString("address1"); else address1 = "";
				if (rs.getString("address2") != null) 	address2 = rs.getString("address2"); else address2 = "";
				if (rs.getString("tel1") != null) 		tel1 	= rs.getString("tel1"); 	else tel1 = "";
				
				if (rs.getString("nation") != null) 	nation = rs.getString("nation"); else nation = "";
				if (rs.getString("sex") != null) 		sex = rs.getString("sex"); 		else sex = "";
				
				if (rs.getString("marrystatus")!= null) marryStatus = rs.getString("marrystatus"); else marryStatus = "0";
				if (rs.getString("allchild") != null) 	allChild = rs.getString("allchild"); else allChild = "0";
				if (rs.getString("edcchild") != null) 	edcChild = rs.getString("edcchild"); else edcChild = "0";
				if (rs.getString("deducttype") != null) deductType = rs.getString("deducttype"); else deductType = "0";
				
				if (rs.getString("birthday") != null)
					birthDay	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("birthday"));
				else birthDay = "01/01/2500";
				
				if (rs.getString("workdate") != null)
					workDate	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("workdate"));
				else workDate = "01/01/2500";
				
				if (rs.getString("pfunddate") != null)
					pfundDate	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("pfunddate"));
				else pfundDate = "";

				if (rs.getString("expdate") != null)
					expDate	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("expdate"));
				else expDate = "";

				if (rs.getString("edcode") != null) 	educCode = rs.getString("edcode"); else educCode = "0";
				if (rs.getString("major") != null) 		major = rs.getString("major"); 		else major = "";
				if (rs.getString("groupname") != null) groupName = rs.getString("groupname"); else groupName = "";
				if (rs.getString("projectid") != null) projCode = rs.getString("projectid"); else projCode = "";
				if (rs.getString("subjobid") != null) subProjCode = rs.getString("subjobid"); else subProjCode = "";
				if (rs.getString("pfflag") != null) 	pfFlag = rs.getString("pfflag"); 	else pfFlag = "0";
				if (rs.getString("accountno") != null) 	accountNo = rs.getString("accountno"); 	else accountNo = "";
				if (rs.getString("taxid") != null) 		taxID = rs.getString("taxid"); 	else taxID = "";
				if (rs.getString("idpop") != null) 		idPop = rs.getString("idpop"); 	else idPop = "1234567890123";
				if (rs.getString("socialno") != null) 	socialNo = rs.getString("socialno"); 	else socialNo = "";
				
				if (rs.getString("insure") != null) 
					insure = df1.format(Float.parseFloat(rs.getString("insure"))); else insure = "0";
				if (rs.getString("devote") != null) 
					devote = df1.format(Float.parseFloat(rs.getString("devote"))); else devote = "0";
				if (rs.getString("interestloan") != null) 
					interestLoan = df1.format(Float.parseFloat(rs.getString("interestloan"))); else interestLoan = "0";
				
				empList.add(new EmployeeForm(empID, oldEmpID, empNameT, empLastNameT, empNameE, empLastNameE, 
					empTypeCode, empTypeName, empDeptCode, empDeptName, offiCode, offiName,
					address1, address2, tel1, email1, nation, sex, marryStatus, allChild, edcChild, deductType,
					birthDay, workDate, expDate,
					educCode, major, groupName, projCode, subProjCode, pfundDate, pfFlag, punchCard, status, accountNo,
					taxID, idPop, socialNo, insure, devote, interestLoan));
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
		return empList;
	}
	public boolean CheckEmployee(String empID, String status) throws Exception {	//05-12-2011
		boolean resultFlag	= false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT * FROM employee WHERE empid = '"+empID.trim()+"' ";
			
			if (!status.equals("")) sqlStmt = sqlStmt + "AND status = '"+status.trim()+"' ";

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
	public void AddEmployee(String empID, String empTypeCode, String empDeptCode) throws Exception { //10-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			if (empTypeCode.equals("")) empTypeCode = "M";
			if (empDeptCode.equals("")) empDeptCode = "M01";
			
			String sqlStmt = "INSERT INTO employee (empid, typecode, deptcode, division) " +
			"VALUES ('"+empID.trim().toUpperCase()+"', '"+empTypeCode+"', '"+empDeptCode+"', " +
					"'"+empDeptCode.substring(0,1)+"' )";

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
	public void CancelEmployee(String empID, String expDate) throws Exception { //22-03-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE employee SET status = 'CL' ";
			
			if (!expDate.equals("")) sqlStmt = sqlStmt + 
				", expdate = '"+dateUtil.CnvToYYYYMMDDEngYear(expDate, '-')+"' ";
			
			sqlStmt = sqlStmt +	"WHERE empid = '"+empID.trim()+"'";

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
	public void CopyEmployee(String empID, String oldEmpID, String empDeptCode) throws Exception { //19-02-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "DELETE FROM employee_copy WHERE empid IN ('"+empID+"', '"+oldEmpID+"')";

			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================================
			sqlStmt = "INSERT INTO employee_copy (SELECT * FROM employee WHERE empid = '"+oldEmpID+"')";

			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================================empID.substring(0,2)
			sqlStmt = "UPDATE employee_copy SET empid = '"+empID+"', deptcode = '"+empDeptCode+"', " +
			"typecode = '"+empID.substring(0,2)+"', division = '"+empDeptCode.substring(0,1)+"' " +
			"WHERE empid = '"+oldEmpID+"'";

			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			//==================================================
			sqlStmt = "INSERT INTO employee (SELECT * FROM employee_copy WHERE empid = '"+empID+"')";

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
	public void EditEmployee(String empID, String empNameT, String empLastNameT, 
		String empNameE, String empLastNameE, String empTypeCode, String empDeptCode, String offiCode,
		String address1, String address2, String tel1, String email1, String nation, String sex, 
		String marryStatus,	String allChild, String edcChild, String deductType,
		String birthDay, String workDate ,String expDate, String educCode, String major,
		String groupName, String projCode, String subProjCode, String pfundDate, String pfFlag, 
		String punchCard, String status,
		String accountNo, String taxID,	String idPop, String socialNo, String insure,
		String devote, String interestLoan)
	throws IOException, Exception { //09-02-2013
		
	    char concatSymbol = '-';
	    birthDay =dateUtil.CnvToYYYYMMDDEngYear(birthDay, concatSymbol);
	    workDate =dateUtil.CnvToYYYYMMDDEngYear(workDate, concatSymbol);
	    		
	    try {
	    	conn = agent.getConnectMYSql();
	    	
	    	String sqlStmt ="update employee set division = '"+empDeptCode.substring(0,1)+"', " +
	    	"empNameT ='"+empNameT+"', empLastNameT = '"+empLastNameT+"', " +
	    	"empNamee ='"+empNameE+"', empLastNamee = '"+empLastNameE+"', " +
	    	"typecode = '"+empTypeCode+"', 	deptcode = '"+empDeptCode+"', officode = '"+offiCode+"', " +
	    	"address1 = '"+address1+"', 	address2 = '"+address2+"', tel1 = '"+tel1+"', email1 = '"+email1+"', " +
	    	"birthday = '"+birthDay+"', 	workdate = '"+workDate+"', " +
	    	"allchild = '"+allChild+"', 	edcchild = '"+edcChild+"', " +
			"accountno= '"+accountNo+"',	taxid = '"+taxID+"', 	punchcard = '"+punchCard+"', " +
	    	"insure = '"+insure+"',  		devote = '"+devote+"', " +
	    	"interestloan = '"+interestLoan+"', edcode = '"+educCode+"', " +
	    	"socialno = '"+socialNo+"',		sex = '"+sex+"', " +
	    	"nation = '"+nation+"', 		idpop = '"+idPop+"', " +	    				
	    	"projectid = '"+projCode+"', 	subjobid = '"+subProjCode+"',  status = '"+status+"', " +
	    	"groupname = '"+groupName+"', 	deducttype = '"+deductType+"', marrystatus = '"+marryStatus+"', "+
	    	"major = '"+major+"', 			pfflag = '"+pfFlag+"' ";

	    	if (expDate.equals("")) sqlStmt = sqlStmt + ", expdate = null ";
	    	else sqlStmt = sqlStmt + ", expdate = '"+dateUtil.CnvToYYYYMMDDEngYear(expDate, '-')+"' ";
	    	
	    	if (pfundDate.equals("")) sqlStmt = sqlStmt + ", pfunddate = null ";
	    	else
	    		sqlStmt = sqlStmt + ", pfunddate = '"+dateUtil.CnvToYYYYMMDDEngYear(pfundDate, '-')+"' ";
	    	
	    	sqlStmt = sqlStmt +	"WHERE empid = '"+empID+"'";
	    	
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
	public List GetEmployee(String empID) throws Exception { //09-02-2013
		List empList = new ArrayList();
		String empNameT = "", empLastNameT 	= "";
		String empTypeCode="", empTypeName 	= "";
		String empDeptCode="", empDeptName	= "", oldEmpID = "";
		String empNameE	= "", empLastNameE	= "", status = "", birthDay = "", workDate="", expDate ="";
		String punchCard= "", address1 = "", address2 = "", tel1 = "", email1 = "", nation = "", sex = "";
		String marryStatus = "", allChild = "", edcChild = "", deductType = "";
		String educCode = "", major = "", groupName = "", pfundDate = "", pfFlag = "";
		String accountNo= "", taxID = "", idPop = "", socialNo = "", insure = "";
		String devote 	= "", interestLoan = "", projCode = "", subProjCode = "";
		String offiCode = "", offiName = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.empid, a.typecode, a.empnamet, a.emplastnamet, a.birthday, a.status, " +
			"a.deptcode, a.officode, b.typename, c.thdesc AS deptname, o.thdesc AS offiname, a.expdate, " +
			"a.punchcard " +
			"FROM emptype b " +
			"JOIN employee a ON (a.typecode = b.typecode) " +
			"LEFT JOIN mastertabledt c ON (a.deptcode = c.typecode AND c.grpcode = 'dept') " +
			"LEFT JOIN mastertabledt o ON (a.officode = o.typecode AND o.grpcode = 'offi') " +
			"WHERE a.empid = '"+empID.trim()+"' AND a.empid <> '00000' ORDER BY a.empid ";			

			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);				
			while (rs.next()) {
				empID		= rs.getString("empid");
				empTypeCode = rs.getString("typecode");
				empTypeName	= rs.getString("typename");
				empDeptCode	= rs.getString("deptcode");
				empDeptName	= rs.getString("deptname");
				offiCode 	= rs.getString("officode");
				offiName 	= rs.getString("offiname");
				punchCard	= rs.getString("punchcard");
				status		= rs.getString("status");
				expDate		= rs.getString("expdate");
				
				if (rs.getString("empnamet") != null) empNameT = rs.getString("empnamet"); else empNameT = "";
				if (rs.getString("emplastnamet") != null) empLastNameT = rs.getString("emplastnamet"); 
				else empLastNameT = "";
				
				if (rs.getString("birthday") != null)
					birthDay	= dateUtil.CnvToDDMMYYYYThaiYear(rs.getString("birthday"));
				else birthDay = "";
				
				empList.add(new EmployeeForm(empID, oldEmpID, empNameT, empLastNameT, empNameE, empLastNameE, 
					empTypeCode, empTypeName, empDeptCode, empDeptName, offiCode, offiName,
					address1, address2, tel1, email1, nation, sex, marryStatus, allChild, edcChild, deductType,
					birthDay, workDate, expDate,
					educCode, major, groupName, projCode, subProjCode, pfundDate, pfFlag, punchCard, status, accountNo,
					taxID, idPop, socialNo, insure, devote, interestLoan));
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
		return empList;
	}
	public void UpdateSalary(String empID, String itemNo) throws Exception { //04-12-2011
		try {
			conn = agent.getConnectMYSql();

			String sqlStmt = "UPDATE employee a, emptrans b, emptype c " +
			"SET a.salary = b.salary * 26, a.costlive = b.costlive " +
			"WHERE a.empid = b.empid AND a.typecode = c.typecode AND " +
			"c.typeemp = '0' AND a.status = 'AC' AND b.current = 'T' AND b.status = a.status AND " +		
			"a.empid = '"+empID.trim()+"' AND b.itemno = '"+itemNo+"' ";			
				
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);	
			
			//==================================
			sqlStmt = "UPDATE employee a, emptrans b, emptype c " +
			"SET a.salary = b.salary, a.costlive = b.costlive " +
			"WHERE a.empid = b.empid AND a.typecode = c.typecode AND " +
			"c.typeemp = '1' AND a.status = 'AC' AND b.current = 'T' AND b.status = a.status AND " +
			"a.empid = '"+empID.trim()+"' AND b.itemno = '"+itemNo+"' ";			
				
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);

			//===========Oil, Other=======================
			sqlStmt = "UPDATE employee a, emptrans b SET a.oil = b.oil, a.other = b.other " +
			"WHERE a.empid = b.empid AND a.status = 'AC' AND b.current = 'T' AND b.status = a.status AND " +
			"a.empid = '"+empID.trim()+"' AND b.itemno = '"+itemNo+"' ";			
				
			//System.out.println(sqlStmt);
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);

			//=============Social Amt=====================
			sqlStmt = "UPDATE employee a SET a.social = 750 " +
			"WHERE a.empid = '"+empID.trim()+"' AND a.status = 'AC' AND a.salary + a.costlive >= 15000 ";
										
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);

			sqlStmt = "UPDATE employee a SET a.social = (a.salary + a.costlive) * 0.05 " +
			"WHERE a.empid = '"+empID.trim()+"' AND a.status = 'AC' AND a.salary + a.costlive < 15000 ";
										
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);

			//=============Provident Fund=====================
			sqlStmt = "UPDATE employee a SET a.provfund = a.salary * 0.05 " +
			"WHERE a.empid = '"+empID.trim()+"' AND a.status = 'AC'  ";
										
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
	public String GetIdpop(String empID) throws Exception {	//05-12-2011
		String idPop = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT idpop FROM employee WHERE empid = '"+empID.trim()+"' ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				idPop = rs.getString("idpop");			
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
		return idPop;
	}
	public boolean CheckIncomeForTax(String empID) throws Exception {	//05-12-2011
		boolean resultFlag	= false;
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT salary + costlive > 20000 FROM employee WHERE empid = '"+empID.trim()+"' ";
			
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

}