/*
 * Created on 11-10-2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.inventory.data;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;

 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBExportExcelMaster {

	DBConnect agent 	= new DBConnect();
	Connection conn 	= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();

	public String ExportExcel(String matTypeCode, String matGrpCode, String matStuffCode, String fileName, String filePath){ //30-10-2013
		String result = "";
		try{
			//String path	= "C:\\exported\\";
			fileName = fileName+".xls" ;
			HSSFWorkbook hwb = new HSSFWorkbook();

			//---------Start export employee
			HSSFSheet sheet =  hwb.createSheet("employee");
			HSSFRow rowhead=   sheet.createRow((short)0);
			rowhead.createCell((short) 0).setCellValue("EmpID");
			rowhead.createCell((short) 1).setCellValue("EmpNameT");
			rowhead.createCell((short) 2).setCellValue("EmpLastNameT");
			rowhead.createCell((short) 3).setCellValue("cardno");
			rowhead.createCell((short) 4).setCellValue("typecode");
			rowhead.createCell((short) 5).setCellValue("deptcode");
			rowhead.createCell((short) 6).setCellValue("division");
			rowhead.createCell((short) 7).setCellValue("groupname");
			rowhead.createCell((short) 8).setCellValue("projectid");
			rowhead.createCell((short) 9).setCellValue("costcode");
			rowhead.createCell((short) 10).setCellValue("oldempid");
			rowhead.createCell((short) 11).setCellValue("salperday");
			rowhead.createCell((short) 12).setCellValue("shiftcode");
			rowhead.createCell((short) 13).setCellValue("workdate");
			rowhead.createCell((short) 14).setCellValue("expdate");
			rowhead.createCell((short) 15).setCellValue("birthday");
			rowhead.createCell((short) 16).setCellValue("idpop");
			rowhead.createCell((short) 17).setCellValue("subjobid");
			rowhead.createCell((short) 18).setCellValue("status");
			
			String sqlStmt = "SELECT * FROM mmmaterial " +
			"WHERE " ;
			if (!matTypeCode.equals("")) 	sqlStmt = sqlStmt + "mattypecode like "+matTypeCode+" AND ";			
			if (!matGrpCode.equals(""))		sqlStmt = sqlStmt + "matgrpcode like "+matGrpCode+" AND ";			
			if (!matStuffCode.equals(""))	sqlStmt = sqlStmt + "refmatcode like "+matStuffCode+" AND ";	
			
			sqlStmt = sqlStmt + " ORDER BY matcode, mattypecode, matgrpcode, refmatcode ";
	
			conn 	= agent.getConnectMYSql();
			pStmt	= conn.createStatement();
			rs=	pStmt.executeQuery(sqlStmt);
			int i=1;
			while(rs.next()){
				HSSFRow row=   sheet.createRow((short)i);
				row.createCell((short) 0).setCellValue(rs.getString("EmpID"));
				row.createCell((short) 1).setCellValue(rs.getString("EmpNameT"));
				row.createCell((short) 2).setCellValue(rs.getString("EmpLastNameT"));
				row.createCell((short) 3).setCellValue(rs.getString("cardno"));
				row.createCell((short) 4).setCellValue(rs.getString("typecode"));
				row.createCell((short) 5).setCellValue(rs.getString("deptcode"));
				row.createCell((short) 6).setCellValue(rs.getString("division"));
				row.createCell((short) 7).setCellValue(rs.getString("groupname"));
				row.createCell((short) 8).setCellValue(rs.getString("projectid"));
				row.createCell((short) 9).setCellValue(rs.getString("costcode"));
				row.createCell((short) 10).setCellValue(rs.getString("oldempid"));
				row.createCell((short) 11).setCellValue(rs.getString("salperday"));
				row.createCell((short) 12).setCellValue(rs.getString("shiftcode"));
				row.createCell((short) 13).setCellValue(rs.getString("workdate"));
				row.createCell((short) 14).setCellValue(rs.getString("expdate"));
				row.createCell((short) 15).setCellValue(rs.getString("birthday"));
				row.createCell((short) 16).setCellValue(rs.getString("idpop"));
				row.createCell((short) 17).setCellValue(rs.getString("subjobid"));
				row.createCell((short) 18).setCellValue(rs.getString("status"));
				
				i++;
			}
			pStmt.close();
			rs.close();
			conn.close();
			//---------End export employee
			
	

			if (!new File(filePath).exists()){
				new File(filePath).mkdir();
			}
			FileOutputStream fileOut =  new FileOutputStream(filePath+fileName);
			hwb.write(fileOut);
			fileOut.close();
			result = "Your excel file has been generated! "+fileName;
            

            
			} catch ( Exception ex ) {
				result = ex.getMessage();

			} finally {
				try {
					if (rs != null) 	rs.close();
					if (pStmt != null) pStmt.close();
					if (conn != null)  conn.close();
				} catch (Exception e) {
					
				}
			}
			return result;
	}
		
	
}

