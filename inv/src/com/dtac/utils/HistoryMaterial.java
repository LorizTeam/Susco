package com.dtac.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HistoryMaterial {
	
	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;
	DateUtil dateUtil = new DateUtil();
	// history
	public void AddHistoryMaterial(String matCode, String historydate) 
	
	throws Exception {	//14-04-2012
		 String matName = "", matTypeCode = "", matTypeName = "", matGrpCode = "", pUnitName = "", matSupplName = "", matGrpName = "";
		 String matColorName = "", groupCode = "", groupName = "", refMatName = "", matBrandName = "", normalPrice = "";
		 String empPrice = "", costPrice = "", specialPrice = "", vipPrice = "", stock1 = "", stock2 = "";
		 String rop = "", min = "", max = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.matcode, a.matname, a.searchname, a.punit, a.iunit, a.runit," +
			"a.mattypecode, a.matgrpcode, a.cost, a.priceretail, a.priceemp, a.pricevip, a.pricesale," + //12/06/2013
			"a.brancode, a.suppcode, a.colocode, a.stufcode, a.refmatcode, a.remark, a.status, a.pic1, a.pic2, a.stock1, a.stock2, a.rop, " +
			"a.qtyminstock, a.qtymaxstock, " +
			"b.mattypename, c.matgrpname, vend.vendername, " +
			"punit.thdesc AS punitname, iunit.thdesc AS iunitname, runit.thdesc AS runitname, " +
			"color.thdesc AS colorname, stuff.thdesc AS stuffname, " +
			"brand.thdesc AS brandname, size.thdesc AS sizename " +
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
					matName 		= rs.getString("matname");
					matTypeCode		= rs.getString("mattypecode");
					matTypeName		= rs.getString("mattypename");
					matGrpCode		= rs.getString("matgrpcode");
					matGrpName		= rs.getString("matgrpname");
					pUnitName		= rs.getString("punitname");
					matSupplName	= rs.getString("vendername");
					matColorName	= rs.getString("colorname");
					groupCode		= rs.getString("stufcode");
					groupName		= rs.getString("stuffname");
					refMatName		= rs.getString("sizename");
					matBrandName	= rs.getString("brandname");
					stock1			= rs.getString("stock1");
					stock2			= rs.getString("stock2");
					normalPrice     = rs.getString("priceretail");
					empPrice       	= rs.getString("priceemp");
					costPrice       = rs.getString("cost");
					specialPrice    = rs.getString("pricesale");
					vipPrice       	= rs.getString("pricevip");
					rop				= rs.getString("rop");
					min				= rs.getString("qtyminstock");
					max				= rs.getString("qtymaxstock");
				}
				rs.close();
				pStmt.close();
			
			String	hdate= dateUtil.curDate();
			sqlStmt = "INSERT INTO stockonhand (matcode, stock1, stock2, hddate, colorname, brandname, sizename, unitname, " +
					"mattypecode, mattypename, matgrpcode, matgrpname, groupcode, groupname, matname, priceretail, priceemp, cost, pricesale, pricevip, rop, qtyminstock, qtymaxstock) "+
			"VALUES ('"+matCode+"', "+Float.parseFloat(stock1)+", "+Float.parseFloat(stock2)+", '"+dateUtil.CnvToYYYYMMDDEngY(hdate,'-')+"', " +
					"'"+matColorName+"', '"+matBrandName+"', '"+refMatName+"', '"+pUnitName+"', " +
					"'"+matTypeCode+"', '"+matTypeName+"', '"+matGrpCode+"', '"+matGrpName+"', '"+groupCode+"', '"+groupName+"', '"+matName+"', " +
					""+Float.parseFloat(normalPrice)+", "+Float.parseFloat(empPrice)+", "+Float.parseFloat(costPrice)+", "+Float.parseFloat(specialPrice)+", "+Float.parseFloat(vipPrice)+", " +
					""+Float.parseFloat(rop)+", "+Float.parseFloat(min)+", "+Float.parseFloat(max)+") ";
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
}