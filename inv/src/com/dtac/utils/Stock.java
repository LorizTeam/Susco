package com.dtac.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dtac.inventory.form.CollectionForm;

public class Stock {
	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	
	public String receive(String matCode, float qty) throws Exception{
		 String flag = "";
		 float fqty = qty;
		conn = agent.getConnectMYSql();
		ResultSet rs = null;
		
		String sqlStmt = "SELECT * " +
		"FROM mmmaterial Where matcode = '"+matCode+"' ";
		
		float matQty	= 0;
		float matMax	= 0;
		float matMin	= 0;
		
		//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				matQty = Float.parseFloat(rs.getString("stock1"));
				matMax = Float.parseFloat(rs.getString("qtymaxstock"));	
				matMin = Float.parseFloat(rs.getString("qtyminstock"));
			}
			rs.close();
			pStmt.close();
			
			matQty = matQty + fqty;
	//		if (matMax < matQty) { 
	//			flag = "Stock On Hand Over "+matMax+" ";
	//		}else {
				sqlStmt = "UPDATE mmmaterial SET stock1 = '"+matQty+"' "+
				"WHERE matcode = '"+matCode+"'";
							
				//System.out.println(sqlStmt);				
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				pStmt.close();
				
				flag = "Receive Complete";
	//		}

			conn.close();
			
			return flag;
	}
public String issue(String matCode, float qty) throws Exception{
		String flag = "";
		float fqty = qty;
		conn = agent.getConnectMYSql();
		ResultSet rs = null;
		
		String sqlStmt = "SELECT * " +
		"FROM mmmaterial Where matcode = '"+matCode+"' ";
		
		float matQty	= 0;
		float matMin	= 0;
		//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				matQty = Float.parseFloat(rs.getString("stock1"));
				matMin = Float.parseFloat(rs.getString("qtyminstock"));
			}
			rs.close();
			pStmt.close();
			
			matQty = matQty - fqty;
		//	if (matMin > matQty){
		//		flag = "Stock On Hand Below "+matMin+" ";
		//	}
		//	else {
				sqlStmt = "UPDATE mmmaterial SET stock1 = '"+matQty+"' "+
				"WHERE matcode = '"+matCode+"'";
								
				pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);
				pStmt.close();
				
				flag = "Issue Complete";
		//		}
				
				
			
			conn.close();
		
			return flag;
	}

public boolean checkReceive(String matCode, String qty) throws Exception{
	boolean result = false;
	
	conn = agent.getConnectMYSql();
	ResultSet rs = null;
	float fqty = Float.parseFloat(qty);
	
	String sqlStmt = "SELECT * " +
	"FROM mmmaterial Where matcode = '"+matCode+"' ";
	
	float matQty	= 0;
	float matMax	= 0;
	//System.out.println(sqlStmt);				
		pStmt = conn.createStatement();
		rs = pStmt.executeQuery(sqlStmt);	
		while (rs.next()) {
			matQty = Float.parseFloat(rs.getString("stock1"));
			matMax = Float.parseFloat(rs.getString("qtymaxstock"));
		}
		rs.close();
		pStmt.close();
		matQty = matQty + fqty;
		
		if(matQty>matMax){
			result = true;
		}
		return result ;
	}
public boolean checkIssue(String matCode, String qty) throws Exception{
	boolean result = false;
	
	conn = agent.getConnectMYSql();
	ResultSet rs = null;
	float fqty = Float.parseFloat(qty);
	
	String sqlStmt = "SELECT * " +
	"FROM mmmaterial Where matcode = '"+matCode+"' ";
	
	float matQty	= 0;
	float matMin	= 0;
	//System.out.println(sqlStmt);				
		pStmt = conn.createStatement();
		rs = pStmt.executeQuery(sqlStmt);	
		while (rs.next()) {
			matQty = Float.parseFloat(rs.getString("stock1"));
			matMin = Float.parseFloat(rs.getString("qtyminstock"));
		}
		rs.close();
		pStmt.close();
		matQty = matQty - fqty;
		
		if(matQty<matMin){
			result = true;
		}
		return result ;
	}
public String issueStock1(String matCode, String qty) throws Exception{
	String flag = "";
	float fqty = Float.parseFloat(qty);
	conn = agent.getConnectMYSql();
	ResultSet rs = null;
	
	String sqlStmt = "SELECT * " +
	"FROM mmmaterial Where matcode = '"+matCode+"' ";
	
	float matQty	= 0;
	float matMin	= 0;
	//System.out.println(sqlStmt);				
		pStmt = conn.createStatement();
		rs = pStmt.executeQuery(sqlStmt);	
		while (rs.next()) {
			matQty = Float.parseFloat(rs.getString("stock1"));
			matMin = Float.parseFloat(rs.getString("qtyminstock"));
		}
		rs.close();
		pStmt.close();
		
		matQty = matQty - fqty;
	//	if (matMin > matQty){
	//		flag = "Stock On Hand Below "+matMin+" ";
	//	}
	//	else {
			sqlStmt = "UPDATE mmmaterial SET stock1 = '"+matQty+"' "+
			"WHERE matcode = '"+matCode+"'";
							
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			pStmt.close();
			
			flag = "Issue Complete";
	//		}
			
			
		
		conn.close();
	
		return flag;
}
public String issueStock2(String matCode, String qty) throws Exception{
	String flag = "";
	float fqty = Float.parseFloat(qty);
	conn = agent.getConnectMYSql();
	ResultSet rs = null;
	
	String sqlStmt = "SELECT * " +
	"FROM mmmaterial Where matcode = '"+matCode+"' ";
	
	float matQty	= 0;
	float matMin	= 0;
	//System.out.println(sqlStmt);				
		pStmt = conn.createStatement();
		rs = pStmt.executeQuery(sqlStmt);	
		while (rs.next()) {
			matQty = Float.parseFloat(rs.getString("stock2"));
			matMin = Float.parseFloat(rs.getString("qtyminstock"));
		}
		rs.close();
		pStmt.close();
		
		matQty = matQty - fqty;
	//	if (matMin > matQty){
	//		flag = "Stock On Hand Below "+matMin+" ";
	//	}
	//	else {
			sqlStmt = "UPDATE mmmaterial SET stock2 = '"+matQty+"' "+
			"WHERE matcode = '"+matCode+"'";
							
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			pStmt.close();
			
			flag = "Issue Complete";
	//		}
			
		conn.close();
	
		return flag;
}
}
