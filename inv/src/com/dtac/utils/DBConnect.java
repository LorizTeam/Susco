package com.dtac.utils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnect {
	
	private static Connection conn = null;
	public Connection getConnectMYSql()  throws Exception, IOException {
		
		try	{
			Class.forName ("com.mysql.jdbc.Driver");
			//Class.forName ("org.gjt.mm.mysql.Driver");
			String dbUserName = "root";
			String dbPassword = "1234";
			String url = "jdbc:mysql://localhost:3306/dtacdb?useUnicode=true&characterEncoding=UTF-8";

			conn = DriverManager.getConnection (url, dbUserName, dbPassword);
			
			
			
			return conn;
			
		 
			
			
		} catch(ClassNotFoundException e) {
			throw new Exception("class not found "+e);
		
		}
		catch(SQLException se)
		{
			if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
			throw new Exception(se);
			  
		}
		
	//	finally {
	//	    if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
			 
	//	}
	}
	public Connection getConnectTimeAttend(String odbcName)  throws Exception, IOException {
		try {
			// Connection connect = DriverManager.getConnection("jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ='databasename.mdb;'uid=;pwd=;");
			// DBQ='databasename.mdb;'uid=; ==> DBQ='databasename.mdb';uid=;              ;' ==> ';  

			//  path... \\server\share\folder\access.mdb
			Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
			//Class.forName ("org.gjt.mm.mysql.Driver");
			String dbUserName = "";
			String dbPassword = "1234";
			String url = "jdbc:odbc:"+odbcName;
			conn = DriverManager.getConnection (url, dbUserName,dbPassword);
			return conn;     
			
		} catch(ClassNotFoundException e) {
			throw new Exception("class not found "+e);
		} catch(SQLException se) {
			throw new Exception(se);
		}
	}
}