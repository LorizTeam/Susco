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
public class DBMaterial {

	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil = new DateUtil();
	
	public List GetMaterialList(String matCode,String matName,String serial,  String matSearchName,
		String matTypeCode, String matGrpCode, String matColorCode, String matStuffCode, 
		String refMatCode, String matStatus, String pUnit, String matBrandCode) 
	throws Exception { //06-06-2012
		List matList = new ArrayList();
		String matTypeName = "", matStockType = "", matSendName = "";
		String matGrpName = "", matRemark = "";
		String pUnitName = "", rUnit = "", rUnitName = "", iUnit = "", iUnitName = "";
		String sellPrice  = "", costPrice 	 = "", qtyMaxStock = "", qtyMinStock 	= "";  
		String matBrandName = "", matSupplCode = "", matSupplName = "";
		String matColorName = "", matStuffName = "", refMatName = "", reqOrdFlag = "";
		String updateDate = "", updateByCode = "", updateByName = "";
		String lastprdate = "";
		String salecde= "";
		String salenme	= "";
		String regOrdFlag = "", pic1 = "", pic2 = "", stock1 = "", stock2 = "",stock3 = "", stock4 = "", rop = "";
		String normalPrice = "", empPrice = "", vipPrice = "", specialPrice = "";
		try {
			DecimalFormat df1 = new DecimalFormat("######0.##");
			DecimalFormat df2 = new DecimalFormat("######0.00");
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.matcode,a.serial, a.matname, a.searchname, a.punit, a.iunit, a.runit,a.lockdate,a.salecde,a.salenme," +
			"a.mattypecode, a.matgrpcode, a.cost, a.priceretail, a.priceemp, a.pricevip, a.pricesale," + //12/06/2013
			"a.brancode, a.suppcode, a.colocode, a.stufcode, a.refmatcode, a.remark, a.status, a.pic1, a.pic2, a.stock1, a.stock2,a.stock3, a.stock4," +
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
			"WHERE ";			
			if (!matCode.equals("")) sqlStmt = sqlStmt + "a.matcode = '"+matCode+"' AND ";
			if (!matName.equals("")) sqlStmt = sqlStmt + "a.matname like '%"+matName+"%' AND ";
			if (!serial.equals("")) sqlStmt = sqlStmt + "a.serial = '"+serial+"' AND ";
			if (!matSearchName.equals("")) 	sqlStmt = sqlStmt + "a.searchname like '%"+matSearchName+"%' AND ";
			if (!matTypeCode.equals("")) 	sqlStmt = sqlStmt + "a.mattypecode = '"+matTypeCode+"' AND ";
			if (!matGrpCode.equals("")) 	sqlStmt = sqlStmt + "a.matgrpcode = '"+matGrpCode+"' AND ";			
			if (!matColorCode.equals("")) 	sqlStmt = sqlStmt + "a.colocode = '"+matColorCode+"' AND ";
			if (!matStuffCode.equals("")) 	sqlStmt = sqlStmt + "a.stufcode = '"+matStuffCode+"' AND ";
			if (!matBrandCode.equals("")) 	sqlStmt = sqlStmt + "a.brancode = '"+matBrandCode+"' AND ";	
			if (!refMatCode.equals("")) 	sqlStmt = sqlStmt + "a.refmatcode = '"+refMatCode+"' AND ";
			if (!matStatus.equals("")) 		sqlStmt = sqlStmt + "a.status = '"+matStatus+"' AND ";
			if (!pUnit.equals("")) 			sqlStmt = sqlStmt + "a.pUnit = '"+pUnit+"' AND ";
			sqlStmt = sqlStmt + " stock1<>0 OR stock2<>0 OR stock3<>0 OR stock4<>0 AND   " +
			
					"a.status <> '' ORDER BY a.mattypecode, a.matgrpcode, a.matcode " ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				matCode		= rs.getString("matcode");
				serial		= rs.getString("serial");
				
				if (rs.getString("matname") != null) matName = rs.getString("matname"); else matName = "";
				if (rs.getString("searchname") != null) matSearchName = rs.getString("searchname"); 
					else matSearchName = "";
				
				matTypeCode	= rs.getString("mattypecode");
				matTypeName	= rs.getString("mattypename");		
				//matStockType= rs.getString("stocktype");
				matGrpCode	= rs.getString("matgrpcode");
				matGrpName	= rs.getString("matgrpname");
				matStatus	= rs.getString("status");
				
				pUnit	= rs.getString("punit"); pUnitName	= rs.getString("punitname");
				//if (rs.getString("punitname") != null) pUnitName	= rs.getString("punitname"); else pUnitName	= "";
				rUnit	= rs.getString("runit"); rUnitName	= rs.getString("runitname");
				iUnit	= rs.getString("iunit"); iUnitName	= rs.getString("iunitname");
				matSupplCode	= rs.getString("suppcode");
				matSupplName	= rs.getString("vendername");
				matColorCode	= rs.getString("colocode");
				matColorName	= rs.getString("colorname");
				matStuffCode 	= rs.getString("stufcode");
				if (rs.getString("stuffname") != null) matStuffName	= rs.getString("stuffname");
				refMatCode	 	= rs.getString("refmatcode");
				if (rs.getString("sizename") != null) refMatName		= rs.getString("sizename");
				matBrandCode	= rs.getString("brancode");
				matBrandName	= rs.getString("brandname");
				stock1			= rs.getString("stock1");
				stock2			= rs.getString("stock2");
				stock3			= rs.getString("stock3");
				stock4			= rs.getString("stock4");
				normalPrice     = rs.getString("priceretail");
				empPrice       	= rs.getString("priceemp");
				costPrice       = rs.getString("cost");
				specialPrice    = rs.getString("pricesale");
				sellPrice       = rs.getString("pricesale");
				vipPrice       	= rs.getString("pricevip");
				lastprdate     = rs.getString("lockdate");
				salecde = rs.getString("salecde");
				salenme	= rs.getString("salenme");
				
				if (rs.getString("pic1") != null) pic1 = rs.getString("pic1"); else pic1 = "";
				if (rs.getString("pic2") != null) pic2 = rs.getString("pic2"); else pic2 = "";
				
				stock1			= df1.format(Float.parseFloat(stock1));
				stock2			= df1.format(Float.parseFloat(stock2));
				
				matList.add(new MaterialForm(matCode, serial, matName, matSendName, matSearchName, pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName, matTypeCode, 
						matTypeName, matStockType, matGrpCode, matGrpName, matBrandCode, matBrandName, matSupplCode, matSupplName, matColorCode, 
						matColorName, matStuffCode, matStuffName, refMatCode, refMatName, matStatus, matRemark, sellPrice, costPrice, qtyMaxStock, qtyMinStock, 
						lastprdate,salecde,salenme,updateDate, updateByCode, updateByName, reqOrdFlag, pic1, pic2, normalPrice, lastprdate, salecde, salenme, stock1, stock2,stock3, stock4, rop));
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
		return matList;
	}
    /////
	public List GetMaterialListLock(String matCode,String matName,String serial,  String matSearchName,
			String matTypeCode, String matGrpCode, String matColorCode, String matStuffCode, 
			String refMatCode, String matStatus, String pUnit, String matBrandCode) 
		throws Exception { //06-06-2012
			List matList = new ArrayList();
			String matTypeName = "", matStockType = "", matSendName = "";
			String matGrpName = "", matRemark = "";
			String pUnitName = "", rUnit = "", rUnitName = "", iUnit = "", iUnitName = "";
			String sellPrice  = "", costPrice 	 = "", qtyMaxStock = "", qtyMinStock 	= "";  
			String matBrandName = "", matSupplCode = "", matSupplName = "";
			String matColorName = "", matStuffName = "", refMatName = "", reqOrdFlag = "";
			String updateDate = "", updateByCode = "", updateByName = "";
			String lastprdate = "";
			String salecde= "";
			String salenme	= "";
			String regOrdFlag = "", pic1 = "", pic2 = "", stock1 = "", stock2 = "",stock3 = "", stock4 = "", rop = "";
			String normalPrice = "", empPrice = "", vipPrice = "", specialPrice = "";
			try {
				DecimalFormat df1 = new DecimalFormat("######0.##");
				DecimalFormat df2 = new DecimalFormat("######0.00");
				conn = agent.getConnectMYSql();
				
				String sqlStmt = "SELECT a.matcode,a.serial, a.matname, a.searchname, a.punit, a.iunit, a.runit,a.lockdate,a.salecde,a.salenme," +
				"a.mattypecode, a.matgrpcode, a.cost, a.priceretail, a.priceemp, a.pricevip, a.pricesale," + //12/06/2013
				"a.brancode, a.suppcode, a.colocode, a.stufcode, a.refmatcode, a.remark, a.status, a.pic1, a.pic2, a.stock1, a.stock2,a.stock3, a.stock4," +
				"b.mattypename, c.matgrpname, vend.vendername, " +
				"punit.thdesc AS punitname, iunit.thdesc AS iunitname, runit.thdesc AS runitname, " +
				"color.thdesc AS colorname, stuff.thdesc AS stuffname, " +
				"brand.thdesc AS brandname, size.thdesc AS sizename" +
				",a.confirm " +
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
				"WHERE ";			
				if (!matCode.equals("")) sqlStmt = sqlStmt + "a.matcode = '"+matCode+"' AND ";
				if (!matName.equals("")) sqlStmt = sqlStmt + "a.matname like '%"+matName+"%' AND ";
				if (!serial.equals("")) sqlStmt = sqlStmt + "a.serial = '"+serial+"' AND ";
				if (!matSearchName.equals("")) 	sqlStmt = sqlStmt + "a.searchname like '%"+matSearchName+"%' AND ";
				if (!matTypeCode.equals("")) 	sqlStmt = sqlStmt + "a.mattypecode = '"+matTypeCode+"' AND ";
				if (!matGrpCode.equals("")) 	sqlStmt = sqlStmt + "a.matgrpcode = '"+matGrpCode+"' AND ";			
				if (!matColorCode.equals("")) 	sqlStmt = sqlStmt + "a.colocode = '"+matColorCode+"' AND ";
				if (!matStuffCode.equals("")) 	sqlStmt = sqlStmt + "a.stufcode = '"+matStuffCode+"' AND ";
				if (!matBrandCode.equals("")) 	sqlStmt = sqlStmt + "a.brancode = '"+matBrandCode+"' AND ";	
				if (!refMatCode.equals("")) 	sqlStmt = sqlStmt + "a.refmatcode = '"+refMatCode+"' AND ";
				if (!matStatus.equals("")) 		sqlStmt = sqlStmt + "a.confirm = '"+matStatus+"' AND ";
				if (!pUnit.equals("")) 			sqlStmt = sqlStmt + "a.pUnit = '"+pUnit+"' AND ";
				sqlStmt = sqlStmt + "a.status <> '' ORDER BY a.mattypecode, a.matgrpcode, a.matcode " ;
				
				//System.out.println(sqlStmt);				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {
					matCode		= rs.getString("matcode");
					serial		= rs.getString("serial");
					
					if (rs.getString("matname") != null) matName = rs.getString("matname"); else matName = "";
					if (rs.getString("searchname") != null) matSearchName = rs.getString("searchname"); 
						else matSearchName = "";
					
					matTypeCode	= rs.getString("mattypecode");
					matTypeName	= rs.getString("mattypename");		
					//matStockType= rs.getString("stocktype");
					matGrpCode	= rs.getString("matgrpcode");
					matGrpName	= rs.getString("matgrpname");
					matStatus	= rs.getString("confirm");
					
					pUnit	= rs.getString("punit"); pUnitName	= rs.getString("punitname");
					//if (rs.getString("punitname") != null) pUnitName	= rs.getString("punitname"); else pUnitName	= "";
					rUnit	= rs.getString("runit"); rUnitName	= rs.getString("runitname");
					iUnit	= rs.getString("iunit"); iUnitName	= rs.getString("iunitname");
					matSupplCode	= rs.getString("suppcode");
					matSupplName	= rs.getString("vendername");
					matColorCode	= rs.getString("colocode");
					matColorName	= rs.getString("colorname");
					matStuffCode 	= rs.getString("stufcode");
					if (rs.getString("stuffname") != null) matStuffName	= rs.getString("stuffname");
					refMatCode	 	= rs.getString("refmatcode");
					if (rs.getString("sizename") != null) refMatName		= rs.getString("sizename");
					matBrandCode	= rs.getString("brancode");
					matBrandName	= rs.getString("brandname");
					stock1			= rs.getString("stock1");
					stock2			= rs.getString("stock2");
					stock3			= rs.getString("stock3");
					stock4			= rs.getString("stock4");
					normalPrice     = rs.getString("priceretail");
					empPrice       	= rs.getString("priceemp");
					costPrice       = rs.getString("cost");
					specialPrice    = rs.getString("pricesale");
					sellPrice       = rs.getString("pricesale");
					vipPrice       	= rs.getString("pricevip");
					lastprdate     = rs.getString("lockdate");
					salecde = rs.getString("salecde");
					salenme	= rs.getString("salenme");
					
					if (rs.getString("pic1") != null) pic1 = rs.getString("pic1"); else pic1 = "";
					if (rs.getString("pic2") != null) pic2 = rs.getString("pic2"); else pic2 = "";
					
					stock1			= df1.format(Float.parseFloat(stock1));
					stock2			= df1.format(Float.parseFloat(stock2));
					
					matList.add(new MaterialForm(matCode, serial, matName, matSendName, matSearchName, pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName, matTypeCode, 
							matTypeName, matStockType, matGrpCode, matGrpName, matBrandCode, matBrandName, matSupplCode, matSupplName, matColorCode, 
							matColorName, matStuffCode, matStuffName, refMatCode, refMatName, matStatus, matRemark, sellPrice, costPrice, qtyMaxStock, qtyMinStock, 
							lastprdate,salecde,salenme,updateDate, updateByCode, updateByName, reqOrdFlag, pic1, pic2, normalPrice, lastprdate, salecde, salenme, stock1, stock2,stock3, stock4, rop));
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
			return matList;
		}
	/////
	public List GetMaterialLOCKList(String matCode,String matName,String salecde,String serial,  String matSearchName,
			String matTypeCode, String matGrpCode, String matColorCode, String matStuffCode, 
			String refMatCode, String matStatus, String pUnit, String matBrandCode) 
		throws Exception { //06-06-2012
			List matList = new ArrayList();
			String matTypeName = "", matStockType = "", matSendName = "";
			String matGrpName = "", matRemark = "";
			String pUnitName = "", rUnit = "", rUnitName = "", iUnit = "", iUnitName = "";
			String sellPrice  = "", costPrice 	 = "", qtyMaxStock = "", qtyMinStock 	= "";  
			String matBrandName = "", matSupplCode = "", matSupplName = "";
			String matColorName = "", matStuffName = "", refMatName = "", reqOrdFlag = "";
			String updateDate = "", updateByCode = "", updateByName = ""; 
			String lastprdate = "";
		//	String salecde= "";
			String salenme	= "";
			String regOrdFlag = "", pic1 = "", pic2 = "", stock1 = "", stock2 = "",stock3 = "", stock4 = "", rop = "";
			String normalPrice = "", empPrice = "", vipPrice = "", specialPrice = "";
			try {
				DecimalFormat df1 = new DecimalFormat("######0.##");
				DecimalFormat df2 = new DecimalFormat("######0.00");
				conn = agent.getConnectMYSql();
				
				String sqlStmt = "SELECT a.matcode,a.serial, a.matname, a.searchname, a.punit, a.iunit, a.runit," +
				"a.mattypecode, a.matgrpcode, a.cost, a.priceretail, a.priceemp, a.pricevip, a.pricesale," + //12/06/2013
				"a.brancode, a.suppcode, a.colocode, a.stufcode, a.refmatcode, a.remark, a.status,a.lockdate, a.salecde, a.salenme, a.pic1, a.pic2, a.stock1, a.stock2,a.stock3, a.stock4," +
				"b.mattypename, c.matgrpname, vend.vendername, " +
				"punit.thdesc AS punitname, iunit.thdesc AS iunitname, runit.thdesc AS runitname, " +
				"color.thdesc AS colorname, stuff.thdesc AS stuffname, " +
				"brand.thdesc AS brandname, size.thdesc AS sizename " +
				",a.confirm " +
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
				"WHERE ";			
				if (!matCode.equals("")) sqlStmt = sqlStmt + "a.matcode = '"+matCode+"' AND ";
				if (!matName.equals("")) sqlStmt = sqlStmt + "a.matname like '%"+matName+"%' AND ";
				if (!salecde.equals("")) sqlStmt = sqlStmt + "a.salecde = '"+salecde+"' AND ";
				if (!serial.equals("")) sqlStmt = sqlStmt + "a.serial = '"+serial+"' AND ";
				
				if (!matSearchName.equals("")) 	sqlStmt = sqlStmt + "a.searchname like '%"+matSearchName+"%' AND ";
				if (!matTypeCode.equals("")) 	sqlStmt = sqlStmt + "a.mattypecode = '"+matTypeCode+"' AND ";
				if (!matGrpCode.equals("")) 	sqlStmt = sqlStmt + "a.matgrpcode = '"+matGrpCode+"' AND ";			
				if (!matColorCode.equals("")) 	sqlStmt = sqlStmt + "a.colocode = '"+matColorCode+"' AND ";
				if (!matStuffCode.equals("")) 	sqlStmt = sqlStmt + "a.stufcode = '"+matStuffCode+"' AND ";
				if (!matBrandCode.equals("")) 	sqlStmt = sqlStmt + "a.brancode = '"+matBrandCode+"' AND ";	
				if (!refMatCode.equals("")) 	sqlStmt = sqlStmt + "a.refmatcode = '"+refMatCode+"' AND ";
				if (!matStatus.equals("")) 		sqlStmt = sqlStmt + "a.confirm = '"+matStatus+"' AND ";
				if (!pUnit.equals("")) 			sqlStmt = sqlStmt + "a.pUnit = '"+pUnit+"' AND ";
				sqlStmt = sqlStmt + "a.status <> '' ORDER BY a.mattypecode, a.matgrpcode, a.matcode " ;
				
				//System.out.println(sqlStmt);				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {
					matCode		= rs.getString("matcode");
					serial		= rs.getString("serial");
					
					if (rs.getString("matname") != null) matName = rs.getString("matname"); else matName = "";
					if (rs.getString("searchname") != null) matSearchName = rs.getString("searchname"); 
						else matSearchName = "";
					
					matTypeCode	= rs.getString("mattypecode");
					matTypeName	= rs.getString("mattypename");		
					//matStockType= rs.getString("stocktype");
					matGrpCode	= rs.getString("matgrpcode");
					matGrpName	= rs.getString("matgrpname");
					matStatus	= rs.getString("confirm");
					
					pUnit	= rs.getString("punit"); pUnitName	= rs.getString("punitname");
					//if (rs.getString("punitname") != null) pUnitName	= rs.getString("punitname"); else pUnitName	= "";
					rUnit	= rs.getString("runit"); rUnitName	= rs.getString("runitname");
					iUnit	= rs.getString("iunit"); iUnitName	= rs.getString("iunitname");
					matSupplCode	= rs.getString("suppcode");
					matSupplName	= rs.getString("vendername");
					matColorCode	= rs.getString("colocode");
					matColorName	= rs.getString("colorname");
					matStuffCode 	= rs.getString("stufcode");
					if (rs.getString("stuffname") != null) matStuffName	= rs.getString("stuffname");
					refMatCode	 	= rs.getString("refmatcode");
					if (rs.getString("sizename") != null) refMatName		= rs.getString("sizename");
					matBrandCode	= rs.getString("brancode");
					matBrandName	= rs.getString("brandname");
					stock1			= rs.getString("stock1");
					stock2			= rs.getString("stock2");
					stock3			= rs.getString("stock3");
					stock4			= rs.getString("stock4");
					normalPrice     = rs.getString("priceretail");
					empPrice       	= rs.getString("priceemp");
					costPrice       = rs.getString("cost");
					specialPrice    = rs.getString("pricesale");
					sellPrice       = rs.getString("pricesale");
					vipPrice       	= rs.getString("pricevip");
					lastprdate     = rs.getString("lockdate");
					salecde = rs.getString("salecde");
					salenme	= rs.getString("salenme");
					
					if (rs.getString("pic1") != null) pic1 = rs.getString("pic1"); else pic1 = "";
					if (rs.getString("pic2") != null) pic2 = rs.getString("pic2"); else pic2 = "";
					
					stock1			= df1.format(Float.parseFloat(stock1));
					stock2			= df1.format(Float.parseFloat(stock2));
					
					matList.add(new MaterialForm(matCode, serial, matName, matSendName, matSearchName, pUnit, pUnitName, rUnit, rUnitName, iUnit, iUnitName, matTypeCode, 
							matTypeName, matStockType, matGrpCode, matGrpName, matBrandCode, matBrandName, matSupplCode, matSupplName, matColorCode, 
							matColorName, matStuffCode, matStuffName, refMatCode, refMatName, matStatus, matRemark, sellPrice, costPrice, qtyMaxStock, qtyMinStock, 
							updateDate, updateByCode, updateByName, reqOrdFlag, pic1, pic2, normalPrice, empPrice, vipPrice, specialPrice,
							lastprdate,salecde,salenme,stock1, stock2,stock3, stock4, rop));
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
			return matList;
		}
		
	/////
	public List GetMaterialListSerialissue(String matCode,String matName,String serial,  String matSearchName,
			String matTypeCode, String matGrpCode, String matColorCode, String matStuffCode, 
			String refMatCode, String matStatus, String pUnit, String matBrandCode ,String takequantity) 
		throws Exception { //06-06-2012
			List itemlist = new ArrayList();
			String matTypeName = "", matStockType = "", matSendName = "";
			String matGrpName = "", matRemark = "";
			String pUnitName = "", rUnit = "", rUnitName = "", iUnit = "", iUnitName = "";
			String sellPrice  = "", costPrice 	 = "", qtyMaxStock = "", qtyMinStock 	= "";  
			String matBrandName = "", matSupplCode = "", matSupplName = "";
			String matColorName = "", matStuffName = "", refMatName = "", reqOrdFlag = "";
			String updateDate = "", updateByCode = "", updateByName = "";
			String regOrdFlag = "", pic1 = "", pic2 = "", stock1 = "", stock2 = "",stock3 = "", stock4 = "", rop = "";
			String normalPrice = "", empPrice = "", vipPrice = "", pricesale = "";
			try {
				DecimalFormat df1 = new DecimalFormat("######0.##");
				DecimalFormat df2 = new DecimalFormat("######0.00");
				conn = agent.getConnectMYSql();
				
				String sqlStmt = "SELECT a.matcode,a.serial, a.matname, a.searchname, a.punit, a.iunit, a.runit," +
				"a.mattypecode, a.matgrpcode, a.cost, a.priceretail, a.priceemp, a.pricevip, a.pricesale," + //12/06/2013
				"a.brancode, a.suppcode, a.colocode, a.stufcode, a.refmatcode, a.remark, a.status, a.pic1, a.pic2, a.stock1, a.stock2,a.stock3,a.stock4," +
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
				"WHERE ";			
				if (!matCode.equals("")) sqlStmt = sqlStmt + "a.matcode = '"+matCode+"' AND ";
				if (!matName.equals("")) sqlStmt = sqlStmt + "a.matname like '%"+matName+"%' AND ";
				if (!serial.equals("")) sqlStmt = sqlStmt + "a.serial = '"+serial+"' AND ";
				if (!matSearchName.equals("")) 	sqlStmt = sqlStmt + "a.searchname like '%"+matSearchName+"%' AND ";
				if (!matTypeCode.equals("")) 	sqlStmt = sqlStmt + "a.mattypecode = '"+matTypeCode+"' AND ";
				if (!matGrpCode.equals("")) 	sqlStmt = sqlStmt + "a.matgrpcode = '"+matGrpCode+"' AND ";			
				if (!matColorCode.equals("")) 	sqlStmt = sqlStmt + "a.colocode = '"+matColorCode+"' AND ";
				if (!matStuffCode.equals("")) 	sqlStmt = sqlStmt + "a.stufcode = '"+matStuffCode+"' AND ";
				if (!matBrandCode.equals("")) 	sqlStmt = sqlStmt + "a.brancode = '"+matBrandCode+"' AND ";	
				if (!refMatCode.equals("")) 	sqlStmt = sqlStmt + "a.refmatcode = '"+refMatCode+"' AND ";
				if (!matStatus.equals("")) 		sqlStmt = sqlStmt + "a.status = '"+matStatus+"' AND ";
				if (!pUnit.equals("")) 			sqlStmt = sqlStmt + "a.pUnit = '"+pUnit+"' AND ";
				sqlStmt = sqlStmt + "a.status <> '' ORDER BY a.mattypecode, a.matgrpcode, a.matcode " ;
				
				//System.out.println(sqlStmt);				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {
					MaterialTakeForm olditem = new MaterialTakeForm();	
					matCode		= rs.getString("matcode");
					serial		= rs.getString("serial");
					
					if (rs.getString("matname") != null) matName = rs.getString("matname"); else matName = "";
					if (rs.getString("searchname") != null) matSearchName = rs.getString("searchname"); 
						else matSearchName = "";
					
					matTypeCode	= rs.getString("mattypecode");
					matTypeName	= rs.getString("mattypename");		
					//matStockType= rs.getString("stocktype");
					matGrpCode	= rs.getString("matgrpcode");
					matGrpName	= rs.getString("matgrpname");
					matStatus	= rs.getString("status");
					refMatCode  = rs.getString("refMatCode");
					pUnit	= rs.getString("punit"); pUnitName	= rs.getString("punitname");
					//if (rs.getString("punitname") != null) pUnitName	= rs.getString("punitname"); else pUnitName	= "";
					rUnit	= rs.getString("runit"); rUnitName	= rs.getString("runitname");
					iUnit	= rs.getString("iunit"); iUnitName	= rs.getString("iunitname");
					matSupplCode	= rs.getString("suppcode");
					matSupplName	= rs.getString("vendername");
					matColorCode	= rs.getString("colocode");
					matColorName	= rs.getString("colorname");
					matStuffCode 	= rs.getString("stufcode");
					if (rs.getString("stuffname") != null) matStuffName	= rs.getString("stuffname");
					refMatCode	 	= rs.getString("refmatcode");
					if (rs.getString("sizename") != null) refMatName		= rs.getString("sizename");
					matBrandCode	= rs.getString("brancode");
					matBrandName	= rs.getString("brandname");
					stock1			= rs.getString("stock1");
					stock2			= rs.getString("stock2");
					stock3			= rs.getString("stock3");
					stock4			= rs.getString("stock4");
					normalPrice     = rs.getString("priceretail");
					empPrice       	= rs.getString("priceemp");
					costPrice       = rs.getString("cost");
					pricesale    = rs.getString("pricesale");
					vipPrice       	= rs.getString("pricevip");
					
					if (rs.getString("pic1") != null) pic1 = rs.getString("pic1"); else pic1 = "";
					if (rs.getString("pic2") != null) pic2 = rs.getString("pic2"); else pic2 = "";
					
					stock1			= df1.format(Float.parseFloat(stock1));
					stock2			= df1.format(Float.parseFloat(stock2));
					stock3			= df1.format(Float.parseFloat(stock3));
					stock4			= df1.format(Float.parseFloat(stock4));
					olditem.setHdrecno(1);
					olditem.setMatCode(matCode);
					olditem.setMatTypeCode(matTypeCode);
					olditem.setMatTypeName(matTypeName);
					olditem.setTakecategories("");
					olditem.setMatGrpName(matGrpName);
					olditem.setRefMatCode(refMatCode);
					olditem.setRefMatName("");
					olditem.setMatTypeName(matTypeName);
					olditem.setTakeunit(pUnit);
					olditem.setrUnitName(pUnitName);
					olditem.setMatName(matName);
					olditem.setTakequantity(takequantity);
					olditem.setTakeprice(normalPrice);
					olditem.setTakeamount(normalPrice);
					olditem.setTaketotalamount(normalPrice);
					olditem.setMatBrandCode(matBrandCode);
					olditem.setMatBrandName(matBrandName);
					olditem.setMatColorCode(matColorCode);
					olditem.setMatColorName(matColorName);
					olditem.setMatStuffCode(matStuffCode);
					olditem.setMatStuffName(serial);
					olditem.setNormalPrice(normalPrice);
					olditem.setEmpPrice("0");
					olditem.setCostPrice(costPrice);
					olditem.setSpecialPrice("0");
					olditem.setVipPrice("0");
					olditem.setPricesale(pricesale);
					olditem.setStock1(stock1);
					olditem.setStock2(stock2);	
					olditem.setStock3(stock3);
					olditem.setStock4(stock4);	
					itemlist.add(olditem);
					 
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
			return itemlist;
		}
	// get serialno
	public List GetMaterialListSerial(String status,String customer,String matCode,String matName,String serial,  String matSearchName,
			String matTypeCode, String matGrpCode, String matColorCode, String matStuffCode, 
			String refMatCode, String matStatus, String pUnit, String matBrandCode ,String takequantity) 
		throws Exception { //06-06-2012
			List itemlist = new ArrayList();
			String matTypeName = "", matStockType = "", matSendName = "";
			String matGrpName = "", matRemark = "";
			String pUnitName = "", rUnit = "", rUnitName = "", iUnit = "", iUnitName = "";
			String sellPrice  = "", costPrice 	 = "", qtyMaxStock = "", qtyMinStock 	= "";  
			String matBrandName = "", matSupplCode = "", matSupplName = "";
			String matColorName = "", matStuffName = "", refMatName = "", reqOrdFlag = "";
			String updateDate = "", updateByCode = "", updateByName = "";
			String regOrdFlag = "", pic1 = "", pic2 = "", stock1 = "", stock2 = "",stock3 = "", stock4 = "", rop = "";
			String normalPrice = "", empPrice = "", vipPrice = "", pricesale = "";
			try {
				DecimalFormat df1 = new DecimalFormat("######0.##");
				DecimalFormat df2 = new DecimalFormat("######0.00");
				conn = agent.getConnectMYSql();
				
				String sqlStmt = "SELECT a.matcode,a.serial, a.matname, a.searchname, a.punit, a.iunit, a.runit," +
				"a.mattypecode, a.matgrpcode, a.cost, a.priceretail, a.priceemp, a.pricevip, a.pricesale," + //12/06/2013
				"a.brancode, a.suppcode, a.colocode, a.stufcode, a.refmatcode, a.remark, a.status, a.pic1, a.pic2, a.stock1, a.stock2,a.stock3,a.stock4," +
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
				"WHERE ";			

				if (!serial.equals("")) sqlStmt = sqlStmt + "a.serial = '"+serial+"' AND ";

				sqlStmt = sqlStmt + "a.status <> '' ORDER BY a.mattypecode, a.matgrpcode, a.matcode " ;
				
				//System.out.println(sqlStmt);				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sqlStmt);	
				while (rs.next()) {
					MaterialTakeForm olditem = new MaterialTakeForm();	
					matCode		= rs.getString("matcode");
					serial		= rs.getString("serial");
					
					if (rs.getString("matname") != null) matName = rs.getString("matname"); else matName = "";
					if (rs.getString("searchname") != null) matSearchName = rs.getString("searchname"); 
						else matSearchName = "";
					
					matTypeCode	= rs.getString("mattypecode");
					matTypeName	= rs.getString("mattypename");		
					//matStockType= rs.getString("stocktype");
					matGrpCode	= rs.getString("matgrpcode");
					matGrpName	= rs.getString("matgrpname");
					matStatus	= rs.getString("status");
					refMatCode  = rs.getString("refMatCode");
					pUnit	= rs.getString("punit"); pUnitName	= rs.getString("punitname");
					//if (rs.getString("punitname") != null) pUnitName	= rs.getString("punitname"); else pUnitName	= "";
					rUnit	= rs.getString("runit"); rUnitName	= rs.getString("runitname");
					iUnit	= rs.getString("iunit"); iUnitName	= rs.getString("iunitname");
					matSupplCode	= rs.getString("suppcode");
					matSupplName	= rs.getString("vendername");
					matColorCode	= rs.getString("colocode");
					matColorName	= rs.getString("colorname");
					matStuffCode 	= rs.getString("stufcode");
					if (rs.getString("stuffname") != null) matStuffName	= rs.getString("stuffname");
					refMatCode	 	= rs.getString("refmatcode");
					if (rs.getString("sizename") != null) refMatName		= rs.getString("sizename");
					matBrandCode	= rs.getString("brancode");
					matBrandName	= rs.getString("brandname");
					stock1			= rs.getString("stock1");
					stock2			= rs.getString("stock2");
					stock3			= rs.getString("stock3");
					stock4			= rs.getString("stock4");
					normalPrice     = rs.getString("priceretail");
					empPrice       	= rs.getString("priceemp");
					costPrice       = rs.getString("cost");
					pricesale    = rs.getString("pricesale");
					vipPrice       	= rs.getString("pricevip");
					
					if (rs.getString("pic1") != null) pic1 = rs.getString("pic1"); else pic1 = "";
					if (rs.getString("pic2") != null) pic2 = rs.getString("pic2"); else pic2 = "";
					
					stock1			= df1.format(Float.parseFloat(stock1));
					stock2			= df1.format(Float.parseFloat(stock2));
					stock3			= df1.format(Float.parseFloat(stock3));
					stock4			= df1.format(Float.parseFloat(stock4));
					olditem.setHdrecno(1);
					olditem.setStatus(status);
					olditem.setMatCode(matCode);
					olditem.setMatTypeCode(matTypeCode);
					olditem.setMatTypeName(matTypeName);
					olditem.setTakecategories(customer);
					olditem.setMatGrpName(matGrpName);
					olditem.setRefMatCode(refMatCode);
					olditem.setRefMatName("");
					olditem.setMatTypeName(matTypeName);
					olditem.setTakeunit(pUnit);
					olditem.setrUnitName(pUnitName);
					olditem.setMatName(matName);
					olditem.setTakequantity(takequantity);
					olditem.setTakeprice(normalPrice);
					olditem.setTakeamount(normalPrice);
					olditem.setTaketotalamount(normalPrice);
					olditem.setMatBrandCode(matBrandCode);
					olditem.setMatBrandName(matBrandName);
					olditem.setMatColorCode(matColorCode);
					olditem.setMatColorName(matColorName);
					olditem.setMatStuffCode(matStuffCode);
					olditem.setMatStuffName(serial);
					olditem.setNormalPrice(normalPrice);
					olditem.setEmpPrice("0");
					olditem.setCostPrice(costPrice);
					olditem.setSpecialPrice("0");
					olditem.setVipPrice("0");
					olditem.setPricesale(pricesale);
					olditem.setStock1(stock1);
					olditem.setStock2(stock2);	
					olditem.setStock3(stock3);
					olditem.setStock4(stock4);	
					itemlist.add(olditem);
					 
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
			return itemlist;
		}
	//Get Product
	public List GetMaterial(String matCode) throws Exception { //20-05-2012
		List matList = new ArrayList();
		String matName = "", matSendName = "", matSearchName = "", matStatus = "";
		String serial="";
		String matTypeCode= "", matTypeName = "", matStockType = "";
		String matGrpCode = "", matGrpName = "", matRemark = "";
		String pUnit = "", pUnitName = "", rUnit = "", rUnitName = "", iUnit = "", iUnitName = "";
		String sellPrice  = "", costPrice 	 = "", qtyMaxStock = "", qtyMinStock 	= "";  
		String matBrandCode = "", matBrandName = "", matSupplCode = "", matSupplName = "";
		String matColorCode = "", matColorName = "", matStuffCode = "", matStuffName = "", refMatCode = "", refMatName = "";
		String updateDate = "", updateByCode = "", updateByName = "";
		String lastprdate = "";
		String salecde= "";
		String salenme	= "";
		String reqOrdFlag = "", pic1 = "", pic2 = "", stock1 = "", stock2 = "",stock3 = "", stock4 = "", rop = "";
		String normalPrice = "", empPrice = "", specialPrice = "", vipPrice = "";
		try {
			DecimalFormat df1 = new DecimalFormat("######0.##");
			DecimalFormat df2 = new DecimalFormat("######0.00");
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.matcode,a.serial, a.matname, a.matsendname, a.searchname, a.punit, a.iunit, a.runit,a.lockdate,a.salecde,a.salenme," +
			"a.mattypecode, a.matgrpcode, a.remark, a.status, a.cost, a.priceretail, a.priceemp, a.pricevip, a.pricesale, a.stock1, a.stock2,a.stock3, a.stock4, a.rop, " +
			"b.mattypename, c.matgrpname, " +
			"punit.thdesc AS punitname, iunit.thdesc AS iunitname, runit.thdesc AS runitname, " +
			"color.thdesc AS colorname, stuff.thdesc AS stuffname, size.thdesc AS sizename, " +
			"a.brancode, a.suppcode, a.colocode, a.stufcode, a.refmatcode, a.remark, a.reqordflag, a.qtymaxstock, a.qtyminstock, " +
			"a.pic1, a.pic2, vend.vendername  " +
			"FROM mmmaterial a " +
			"LEFT JOIN mmmattype b ON (a.mattypecode = b.mattypecode) " +
			"LEFT JOIN mmmatgrp c ON (a.mattypecode = c.mattypecode AND a.matgrpcode = c.matgrpcode) " +
			"LEFT JOIN mmvendermaster vend ON (a.suppcode = vend.vendercode) " +
			"LEFT JOIN mastertabledt punit ON (punit.grpcode = 'unit' AND a.punit = punit.typecode) " +
			"LEFT JOIN mastertabledt iunit ON (iunit.grpcode = 'unit' AND a.iunit = iunit.typecode) " +
			"LEFT JOIN mastertabledt runit ON (runit.grpcode = 'unit' AND a.runit = runit.typecode) " +
			"LEFT JOIN mastertabledt color ON (color.grpcode = 'colo' AND a.colocode = color.typecode) " +
			"LEFT JOIN mastertabledt stuff ON (stuff.grpcode = 'stuf' AND a.stufcode = stuff.typecode) " +
			"LEFT JOIN mastertabledt size ON (size.grpcode = 'size' AND a.refmatcode = size.typecode) " +
			"WHERE a.matcode = '"+matCode+"'";			
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				matCode		= rs.getString("matcode");
				serial		= rs.getString("serial");
				
				if (rs.getString("matname") != null) matName = rs.getString("matname"); else matName = "";
				if (rs.getString("matsendname") != null) matSendName = rs.getString("matsendname"); else matSendName = "";
				if (rs.getString("searchname") != null) matSearchName = rs.getString("searchname"); 
					else matSearchName = "";
				
				matSendName = rs.getString("matSendName");
				matTypeCode	= rs.getString("mattypecode");
				matTypeName	= rs.getString("mattypename");		
				//matStockType= rs.getString("stocktype");
				matGrpCode	= rs.getString("matgrpcode");
				matGrpName	= rs.getString("matgrpname");
				matStatus	= rs.getString("status");
				
				pUnit	= rs.getString("punit"); pUnitName	= rs.getString("punitname");
				rUnit	= rs.getString("runit"); rUnitName	= rs.getString("runitname");
				iUnit	= rs.getString("iunit"); iUnitName	= rs.getString("iunitname");
				matBrandCode = rs.getString("brancode");
				matSupplCode = rs.getString("suppcode");				
				matSupplName = rs.getString("vendername");
				matColorCode = rs.getString("colocode");
				matColorName = rs.getString("colorname");
				matStuffCode = rs.getString("stufcode");
				matStuffName = rs.getString("stuffname");
				refMatCode	 = rs.getString("refmatcode");
				refMatName	 = rs.getString("sizename");
				matRemark	 = rs.getString("remark");
				reqOrdFlag	 = rs.getString("reqordflag");
				qtyMaxStock	 = rs.getString("qtymaxstock");
				qtyMinStock	 = rs.getString("qtyminstock");
				if (rs.getString("pic1") != null) pic1 = rs.getString("pic1"); else pic1 = "";
				if (rs.getString("pic2") != null) pic2 = rs.getString("pic2"); else pic2 = "";
				costPrice       = rs.getString("cost");
				normalPrice     = rs.getString("priceretail");
				empPrice       	= rs.getString("priceemp");
				specialPrice    = rs.getString("pricesale");
				vipPrice       	= rs.getString("pricevip");
				stock1			= rs.getString("stock1");
				stock2			= rs.getString("stock2");
				stock3			= rs.getString("stock3");
				stock4			= rs.getString("stock4");
				rop				= rs.getString("rop");
				lastprdate     = rs.getString("lockdate");
				salecde = rs.getString("salecde");
				salenme	= rs.getString("salenme");	
				stock1			= df1.format(Float.parseFloat(stock1));
				stock2			= df1.format(Float.parseFloat(stock2));
				stock3			= df1.format(Float.parseFloat(stock3));
				stock4			= df1.format(Float.parseFloat(stock4));
				qtyMaxStock		= df1.format(Float.parseFloat(qtyMaxStock));
				qtyMinStock		= df1.format(Float.parseFloat(qtyMinStock));
				rop				= df1.format(Float.parseFloat(rop));
				
				matList.add(new MaterialForm(matCode,serial, matName, matSendName, matSearchName, pUnit, pUnitName, rUnit, rUnitName, iUnit, 
						iUnitName, matTypeCode, matTypeName, matStockType, matGrpCode, matGrpName, matBrandCode, matBrandName, 
						matSupplCode, matSupplName, matColorCode, matColorName, matStuffCode, matStuffName, refMatCode, refMatName, matStatus, 
						matRemark, sellPrice, costPrice, qtyMaxStock, qtyMinStock, updateDate, updateByCode, updateByName, reqOrdFlag, pic1, pic2, 
						normalPrice, empPrice, vipPrice, specialPrice,lastprdate,salecde,salenme, stock1, stock2,stock3, stock4, rop));
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
		return matList;
	}	
	public boolean CheckMaterial(String matCode) throws Exception { //05-06-2012 
		boolean chk = false;
		try {
			conn = agent.getConnectMYSql();
			
			String sql = "SELECT matcode FROM mmmaterial WHERE matcode = '"+matCode+"' ";
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
				chk = true;
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return chk;
	}
	
	public String getProductname(String prodCode) throws Exception { //05-06-2012 
		String matgrpname = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sql = "SELECT matgrpname FROM mmmatgrp WHERE matgrpcode = '"+prodCode+"' ";
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
				matgrpname = rs.getString("matgrpname");;
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return matgrpname;
	}
	public String getProductprice(String prodCode) throws Exception { //05-06-2012 
		String price = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sql = "SELECT price FROM mmmatgrp WHERE matgrpcode = '"+prodCode+"' ";
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
				price = rs.getString("price");;
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return price;
	}
	public boolean CheckRefMatCode(String matCode, String refMatCode) throws Exception { //10-10-2012 
		boolean chk = false;
		int countMat = 0;
		try {
			conn = agent.getConnectMYSql();
			
			String sql = "SELECT COUNT(refmatcode) FROM mmmaterial WHERE refmatcode = '"+refMatCode+"' AND " +
			"refmatcode <> '' AND matcode <> '"+matCode+"' ";
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
				countMat = Integer.parseInt(rs.getString(1));
			}
			if (countMat >= 1) chk = true;
			
			rs.close();
			pStmt.close();
			conn.close();
		} catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return chk;
	}
	public String GetMatCode(String refMatCode) throws Exception { //10-07-2012 
		String matCode = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sql = "SELECT matcode FROM mmmaterial WHERE refmatcode = '"+refMatCode+"' AND " +
			"refmatcode <> '' ";
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
				matCode = rs.getString("matcode");
			}			
			rs.close();
			pStmt.close();
			conn.close();
		} catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return matCode.toUpperCase();
	}
	public String AddMaterial(String matCode,String userid,String docdate,String typeperson,String serial, String matSendName, String matName, String matSearchName,
		String pUnit, String rUnit, String iUnit, String matTypeCode, String matGrpCode, String matRemark,
		String matBrandCode, String matSupplCode, String matColorCode, String matStuffCode, String refMatCode, String qtyMaxStock,
		String qtyMinStock, String stock1, String stock2, String stock3, String stock4, String normalPrice, String empPrice, String vipPrice, String costPrice, String specialPrice, String rop) 
	throws Exception {	//04-06-2012
		String msg ="ready";
		try {
			
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "INSERT INTO mmmaterial (matcode,typeperson, serial, matsendname, matname, searchname, punit, runit, iunit, mattypecode, matgrpcode, " +
					"remark, brancode, suppcode, colocode, stufcode, refmatcode, qtymaxstock, qtyminstock, stock1, stock2,stock3, stock4, priceretail, " +
					"priceemp, pricevip, cost, pricesale,userid,docdate, rop) " +
			"VALUES ('"+matCode+"','"+typeperson+"', '"+serial+"', '"+matSendName+"', '"+matName+"', '"+matSearchName+"', '"+pUnit+"', '"+rUnit+"', '"+iUnit+"', " +
			"'"+matTypeCode+"', '"+matGrpCode+"', '"+matRemark+"', '"+matBrandCode+"', '"+matSupplCode+"', " +
			"'"+matColorCode+"', '"+matStuffCode+"', '"+refMatCode+"', "+Float.parseFloat(qtyMaxStock)+", "+Float.parseFloat(qtyMinStock)+", " +
			""+Float.parseFloat(stock1)+", "+Float.parseFloat(stock2)+", "+Float.parseFloat(stock3)+", "+Float.parseFloat(stock4)+", "+Float.parseFloat(normalPrice)+", "+Float.parseFloat(empPrice)+", " +
			""+Float.parseFloat(vipPrice)+", "+Float.parseFloat(costPrice)+", "+Float.parseFloat(normalPrice)+", '"+userid+"', '" +docdate+ "' , '"+rop+"') ";
			
			//System.out.println(sqlStmt);
		    pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			
		  msg ="duplicate";
		//	throw new Exception(e.getMessage());
		} finally {
			try {
				if (pStmt != null) pStmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return msg;
	} 
	
	public String AddMaterialBarcode(
			String userid,String text_1,String text_2,String text_3, String text_4, String text_5, String text_6, String text_7,
String text_8,String text_9,String text_10, String text_11, String text_12, String text_13, String text_14,
String text_15,String text_16,String text_17, String text_18, String text_19, String text_20, String text_21
) 
		throws Exception {	//04-06-2012
			String msg ="ready";
			try {
				
				conn = agent.getConnectMYSql();
String sqlStmt = "Delete From bktmpbarcode "+
				"WHERE userid = '"+userid+"' ";					
				//System.out.println(sqlStmt);
			    pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);			
				pStmt.close();
				
  sqlStmt = "INSERT INTO bktmpbarcode(userid,barcode_1,barcode_2, barcode_3, barcode_4, barcode_5, barcode_6, barcode_7, " +
	"barcode_8, barcode_9, barcode_10, barcode_11, barcode_12, barcode_13, barcode_14, " +
	 "barcode_15, barcode_16, barcode_17, barcode_18, barcode_19, barcode_20, barcode_21) " +
				 
"VALUES ('"+userid+"','"+text_1+"', '"+text_2+"', '"+text_3+"', '"+text_4+"', '"+text_5+"', '"+text_6+"', '"+text_7+"', '"
           +text_8+"','"+text_9+"', '"+text_10+"', '"+text_11+"', '"+text_12+"', '"+text_13+"', '"+text_14+"', '"
           +text_15+"','"+text_16+"', '"+text_17+"', '"+text_18+"', '"+text_19+"', '"+text_20+"', '"+text_21+"' "
           +") ";
				
				//System.out.println(sqlStmt);
			    pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);			
				pStmt.close();
				
				conn.close();
			} catch (Exception e) {				
			  msg ="sql err";
			//	throw new Exception(e.getMessage());
			} finally {
				try {
					if (pStmt != null) pStmt.close();
					if (conn != null)  conn.close();
				} catch (SQLException e) {
					throw new Exception(e.getMessage());
				}
			}
			return msg;
		} 
	public void UpdateMaterial(String matCode, String matName, String matSendName, String matSearchName,
		String pUnit, String rUnit, String iUnit, String matTypeCode, String matGrpCode, String matRemark,
		String matBrandCode, String matSupplCode, String matColorCode, String matStuffCode, String refMatCode, String qtyMaxStock, 
		String qtyMinStock, String matStatus, String stock1, String stock2, String normalPrice, String empPrice, 
		String vipPrice, String costPrice, String specialPrice, String rop, String pic1) 
	throws Exception { //16-08-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterial SET matname = '"+matName+"', matsendname = '"+matSendName+"', searchname = '"+matSearchName+"', " +
			"punit = '"+pUnit+"', runit = '"+pUnit+"', iunit = '"+pUnit+"',  status = '"+matStatus+"', " +
			"mattypecode = '"+matTypeCode+"', matgrpcode = '"+matGrpCode+"', remark = '"+matRemark+"', " +
			"brancode = '"+matBrandCode+"',   suppcode = '"+matSupplCode+"', colocode = '"+matColorCode+"', " +
			"stufcode = '"+matStuffCode+"',   refmatcode = '"+refMatCode+"', qtymaxstock = "+Float.parseFloat(qtyMaxStock)+", qtyminstock = "+Float.parseFloat(qtyMinStock)+", " +
			"stock1 = "+Float.parseFloat(stock1)+", stock2 = "+Float.parseFloat(stock2)+", priceretail = "+Float.parseFloat(normalPrice)+", priceemp = "+Float.parseFloat(empPrice)+", " +
			"cost = "+Float.parseFloat(costPrice)+", pricesale = "+Float.parseFloat(specialPrice)+", pricevip = "+Float.parseFloat(vipPrice)+", rop = '"+rop+"', pic1 = '"+pic1+"'" +
			"WHERE matcode = '"+matCode+"' ";
				
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

	
	public String getProcodeStock(String serial) 
	throws Exception { //16-08-2012
    	String matcode = "";
		try {
			conn = agent.getConnectMYSql();
			 
			
	// start select stock befor  issue		
			String sql = "SELECT matcode FROM mmmaterial " +
			"WHERE   serial = '"+serial+"' ";
			 
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
				matcode=	rs.getString("matcode");
				     
			}			
			rs.close();
	 
	
 			
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
		return matcode;
	}
	public String checkStock(String matcode,String serial,String wahoCode) 
	throws Exception { //16-08-2012
    	String stock = "";
		try {
			conn = agent.getConnectMYSql();
		 
			
	// start select stock befor  issue		
			String sql = "SELECT stock1,stock2,stock3,stock4 FROM mmmaterial " +
			"WHERE matcode = '"+matcode+"' and serial = '"+serial+"' ";
			 
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
			
				     if(wahoCode.equals("A1")){
				      stock =  rs.getString("stock1");
					   
				 	 }
				 	 if(wahoCode.equals("B1")){
				 	  stock =  rs.getString("stock2");
				 	  
					 }
				 	 if(wahoCode.equals("C1")){
				 	  stock =  rs.getString("stock3");
				 	 
					 }
				 	 if(wahoCode.equals("D1")){
				 	  stock =  rs.getString("stock4");
				 	   
					 }
			}			
			rs.close();
	// end select stock	befor issue	
			
			     
			 	 
			
			
 	 
 			
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
		return stock;
	}
	
	public String issueMaterial(String matcode,String serial,int issueqty,String wahoCode) 
		throws Exception { //16-08-2012
	    	String stock = "";
			try {
				conn = agent.getConnectMYSql();
				float stock1=0  ,stock2=0 ,stock3=0 ,stock4=0 ;
				
		// start select stock befor  issue		
				String sql = "SELECT stock1,stock2,stock3,stock4 FROM mmmaterial " +
				"WHERE matcode = '"+matcode+"' and serial = '"+serial+"' ";
				 
				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sql);
				if (rs.next()) {
				
					     if(wahoCode.equals("A1")){
					      stock1 =  Float.parseFloat(rs.getString("stock1"));
						  stock1	= stock1-issueqty;
					 	 }
					 	 if(wahoCode.equals("B1")){
					 	  stock2 =  Float.parseFloat(rs.getString("stock2"));
					 	  stock2	= stock2-issueqty;
						 }
					 	 if(wahoCode.equals("C1")){
					 	  stock3 =  Float.parseFloat(rs.getString("stock3"));
					 	  stock3	= stock3-issueqty;
						 }
					 	 if(wahoCode.equals("D1")){
					 	  stock4 =  Float.parseFloat(rs.getString("stock4"));
					 	  stock4	= stock4-issueqty;
						 }
				}			
				rs.close();
		// end select stock	befor issue	
				
				     
				 	 
				String sqlStmt = "UPDATE mmmaterial SET stock1 = "+stock1+", " +
				"stock2 = "+stock2+", stock3 = "+stock3+", " +
				"stock4 = "+stock4+" WHERE matcode = '"+matcode+"' and serial = '"+serial+"' ";
					
				//System.out.println(sqlStmt);
			    pStmt = conn.createStatement();
				pStmt.executeUpdate(sqlStmt);			
				pStmt.close();
			
				
				
	 	// start select stock after   issue		
				 sql = "SELECT stock1,stock2,stock3,stock4 FROM mmmaterial " +
				"WHERE matcode = '"+matcode+"' and serial = '"+serial+"' ";
				
		
				
				pStmt = conn.createStatement();
				rs = pStmt.executeQuery(sql);
				if (rs.next()) {
				
					     if(wahoCode.equals("A1")){
					      stock =  rs.getString("stock1");
						 
					 	 }
					 	 if(wahoCode.equals("B1")){
					 	  stock =  rs.getString("stock2");
					 	 
						 }
					 	 if(wahoCode.equals("C1")){
					 	  stock =   rs.getString("stock3");
					 	 
						 }
					 	 if(wahoCode.equals("D1")){
					 	  stock =   rs.getString("stock4");
					 	 
						 }
				}			
				rs.close();
		// end select stock	after issue				
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
			return stock;
		}
	
//
	public List GetMaterialSerialissueList(String docTypeCode,String docYear,String docMonth,String docNo,String custCode,String custName,String serial,int issueqty) 
	throws Exception { //16-08-2012
    	String stock = "";
    	String wahoCode ="";
    	List matHDList = new ArrayList();
		try {
			conn = agent.getConnectMYSql();
			float stock1=0  ,stock2=0 ,stock3=0 ,stock4=0 ;
			String matcode="",matname="",pricesale="";
			
	// start select stock befor  issue		
			String sql = "SELECT refmatcode,stock1,stock2,stock3,stock4 FROM mmmaterial " +
			"WHERE  serial = '"+serial+"' ";
			 
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
				      wahoCode=   	rs.getString("refmatcode");
				     if(wahoCode.equals("A1")){
				      stock1 =  Float.parseFloat(rs.getString("stock1"));
					  stock1	= stock1-issueqty;
				 	 }
				 	 if(wahoCode.equals("B1")){
				 	  stock2 =  Float.parseFloat(rs.getString("stock2"));
				 	  stock2	= stock2-issueqty;
					 }
				 	 if(wahoCode.equals("C1")){
				 	  stock3 =  Float.parseFloat(rs.getString("stock3"));
				 	  stock3	= stock3-issueqty;
					 }
				 	 if(wahoCode.equals("D1")){
				 	  stock4 =  Float.parseFloat(rs.getString("stock4"));
				 	  stock4	= stock4-issueqty;
					 }
			}			
			rs.close();
	// end select stock	befor issue	
			
			     
			 	 
			String sqlStmt = "UPDATE mmmaterial SET stock1 = "+stock1+", " +
			"stock2 = "+stock2+", stock3 = "+stock3+", " +
			"stock4 = "+stock4+" WHERE  serial = '"+serial+"' ";
				
			//System.out.println(sqlStmt);
		    pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);			
			pStmt.close();
		
			
			
 	// start select stock after   issue		
			 sql = "SELECT stock1,stock2,stock3,stock4,matcode,matname,pricesale FROM mmmaterial " +
			"WHERE   serial = '"+serial+"' ";
			
	
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
				
				matcode =  rs.getString("matcode");
				matname =  rs.getString("matname");
				pricesale =  rs.getString("pricesale");
				
				     if(wahoCode.equals("A1")){
				      stock =  rs.getString("stock1");					 
				 	 }
				 	 if(wahoCode.equals("B1")){
				 	  stock =  rs.getString("stock2");				 	 
					 }
				 	 if(wahoCode.equals("C1")){
				 	  stock =   rs.getString("stock3");				 	 
					 }
				 	 if(wahoCode.equals("D1")){
				 	  stock =   rs.getString("stock4");				 	 
					 }
				 	 
				 	matHDList.add(new MaterialTakeForm( docTypeCode,docYear,  docMonth,   docNo, 
								  wahoCode,   matcode,   matname,"" ,"", custCode,   custName,  pricesale));	 
			}			
			rs.close();
	// end select stock	after issue				
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
		return matHDList;
	}
	
//	
	
	public void GetMaterialSerialreceiveList(String wahoCode,String matCode,String matName,String docTypeCode,String datereturn,String docYear,String docMonth,String docNo,String custCode,String custName,String serial,int receiveqty,String pricesale) 
	throws Exception { //16-08-2012
		try {
			conn = agent.getConnectMYSql();
			float stock1=0  ,stock2=0 ,stock3=0 ,stock4=0,stock5=0 ;
			
	// start select stock		
			String sql = "SELECT stock1,stock2,stock3,stock4,stock5,pricesale FROM mmmaterial " +
			"WHERE matcode = '"+matCode+"' and serial = '"+serial+"' ";
			 
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
			
				     if(wahoCode.equals("A1")){
				      stock1 =  Float.parseFloat(rs.getString("stock1"));
					  stock1	= stock1+receiveqty;
				 	 }
				 	 if(wahoCode.equals("B1")){
				 	  stock2 =  Float.parseFloat(rs.getString("stock2"));
				 	  stock2	= stock2+receiveqty;
					 }
				 	 if(wahoCode.equals("C1")){
				 	  stock3 =  Float.parseFloat(rs.getString("stock3"));
				 	  stock3	= stock3+receiveqty;
					 }
				 	 if(wahoCode.equals("D1")){
				 	  stock4 =  Float.parseFloat(rs.getString("stock4"));
				 	  stock4	= stock4+receiveqty;
					 }
				 	 if(wahoCode.equals("E1")){
					 	  stock5 =  Float.parseFloat(rs.getString("stock5"));
					 	  stock5	= stock4+receiveqty;
					 }
			}			
			rs.close();
	// end select stock		
			String typeperson="",matSendName="",matSearchName="",matGrpCode="";
			     
			String sqlStmt = "INSERT INTO mmmaterialreturn (matcode,datereturn,typeperson,  serial, matsendname, matname, searchname,  matgrpcode, " +
			" stock1, stock2,stock3, stock4,stock5, pricesale) " +
	"VALUES ('"+matCode+"','"+datereturn+"', '"+typeperson+"', '"+serial+"', '"+matSendName+"', '"+matName+"', '"+matSearchName+"',"  +
	" '"+matGrpCode+"', "+ stock1 +", "+stock2+", "+stock3+", "+stock4+", "+stock5+", "+Float.parseFloat(pricesale)+" ) ";
	
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
	
//
	public void receiveMaterial(String matcode,String serial,int receiveqty,String wahoCode) 
	throws Exception { //16-08-2012
		try {
			conn = agent.getConnectMYSql();
			float stock1=0  ,stock2=0 ,stock3=0 ,stock4=0 ;
			
	// start select stock		
			String sql = "SELECT stock1,stock2,stock3,stock4 FROM mmmaterial " +
			"WHERE matcode = '"+matcode+"' and serial = '"+serial+"' ";
			 
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sql);
			if (rs.next()) {
			
				     if(wahoCode.equals("A1")){
				      stock1 =  Float.parseFloat(rs.getString("stock1"));
					  stock1	= stock1+receiveqty;
				 	 }
				 	 if(wahoCode.equals("B1")){
				 	  stock2 =  Float.parseFloat(rs.getString("stock2"));
				 	  stock2	= stock2+receiveqty;
					 }
				 	 if(wahoCode.equals("C1")){
				 	  stock3 =  Float.parseFloat(rs.getString("stock3"));
				 	  stock3	= stock3+receiveqty;
					 }
				 	 if(wahoCode.equals("D1")){
				 	  stock4 =  Float.parseFloat(rs.getString("stock4"));
				 	  stock4	= stock4+receiveqty;
					 }
			}			
			rs.close();
	// end select stock		
			
			     
			 	 
			String sqlStmt = "UPDATE mmmaterial SET stock1 = "+stock1+", " +
			"stock2 = "+stock2+", stock3 = "+stock3+", " +
			"stock4 = "+stock4+" WHERE matcode = '"+matcode+"' and serial = '"+serial+"' ";
				
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
	
//	
	public String issueLOCKMaterial(String status,String lmatCode,String lockdate,String salecde ,String salenme,String serial,String stock1,String stock2) 
	throws Exception { //16-08-2012
    	String msg = "complete";
		try {
			conn = agent.getConnectMYSql();
		 
			if(stock1.equals("1")){
				stock1="0";
			}
			if(stock2.equals("1")){
				stock2="0";
			}			 	 
			String sqlStmt = "UPDATE mmmaterial SET stock1 = '"+stock1+"', lockdate= '" +lockdate  +"' ,  salecde= '" +salecde  +"' ,  salenme = '" +salenme  +"' , " +
			" stock2 = '"+stock2+"' , confirm = '"+status+"' WHERE matcode = '"+lmatCode+"' and serial = '"+serial+"' ";
				
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
		return msg;
	}
	
	public String unLOCKMaterial() 
	throws Exception { //16-08-2012
    	String msg = "complete";
		try {
			conn = agent.getConnectMYSql();
			// 
			int caldate = -15;
			//
			
			String stock1 = "1",stock2="1" ;
 			// STOCK1	 
			String sqlStmt = "UPDATE mmmaterial SET salecde='',salenme='',stock1 = '"+stock1+"', lockdate= '0000-00-00'   " +
			"  WHERE  (DAYOFYEAR(CURDATE()) - DAYOFYEAR( lockdate) ) <= "+caldate+"  AND confirm='N' AND   lockdate <>'0000-00-00' and refmatcode='A1'";
				
	 
		    pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			// STOCK 2
			  sqlStmt = "UPDATE mmmaterial SET salecde='',salenme='',stock2 = '"+stock2+"', lockdate= '0000-00-00'   " +
			"  WHERE  (DAYOFYEAR(CURDATE()) - DAYOFYEAR( lockdate) ) <= "+caldate+"  AND  confirm='N' AND   lockdate <>'0000-00-00' and refmatcode='B1'";
	 
		    pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			
			//
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
		return msg;
	}
	
	public void CopyMaterial(String matCode, String newMatCode) throws Exception { //26-07-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "CREATE TABLE tmp_mmmaterial " +
			"SELECT * FROM mmmaterial WHERE matcode = '"+matCode+"' ";
			
		    pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================================================
			sqlStmt = "UPDATE tmp_mmmaterial SET matcode = '"+newMatCode+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================================================
			sqlStmt = "INSERT INTO mmmaterial " +
	        "SELECT * FROM tmp_mmmaterial WHERE matcode = '"+newMatCode+"' ";
			
			pStmt = conn.createStatement();
			pStmt.executeUpdate(sqlStmt);
			//============================================================
			sqlStmt = "DROP TABLE tmp_mmmaterial";
			
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
	public void UpdateLastPRDate(String matCode) throws Exception { //16-07-2012
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "UPDATE mmmaterial SET " +
			"lockdate = '"+dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-')+"' " +
			"WHERE matcode = '"+matCode+"' ";
				
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