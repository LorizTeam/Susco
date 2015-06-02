package com.dtac.inventory.data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dtac.inventory.form.StudentForm;
import com.dtac.utils.DBConnect;

public class DBStudent {
	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	
	
		// TODO Auto-generated constructor stub
	
	public void AddStudent(String studentID, String studentName, String dateTime) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO masterstudent(studentid, studentname, datetime) " +
			"VALUES ('"+studentID.trim()+"', '"+studentName+"', '"+dateTime+"')";
						
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
	public List GetStudentlist(String studentID) throws Exception {	//03-06-2012
			List studentlist = new ArrayList();
			String studentName = "", dateTime = "";
			Statement pStmt = null;
			ResultSet rs = null;
		try {			
			conn = agent.getConnectMYSql();
			String sqlStmt = "SELECT studentid, studentname, datetime " +
					"FROM masterstudent " +
					"WHERE ";
				if (!studentID.equals(""))	sqlStmt = sqlStmt + "studentid = '"+studentID+"' AND ";
					
					sqlStmt = sqlStmt + "studentid <> '' ORDER BY studentid " ;	 
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				studentID 		= rs.getString("studentid");
				studentName   	= rs.getString("studentname");
				dateTime 		= rs.getString("datetime");
				studentlist.add(new StudentForm(studentID, studentName, dateTime)); 
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
		return studentlist;
	}
	public void UpdateStudent(String studentID, String studentName, String dateTime) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE masterstudent SET studentname = '"+studentName+"', datetime = '"+dateTime+"' "+
			"WHERE studentid = '"+studentID+"'";
						
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
	public void DeleteStudent(String studentID) throws Exception {	//01-05-2012
		try {
			conn = agent.getConnectMYSql();
			String sqlStmt = "DELETE FROM masterstudent WHERE studentid = '"+studentID+"'";
						
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
