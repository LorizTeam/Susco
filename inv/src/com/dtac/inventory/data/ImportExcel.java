package com.dtac.inventory.data;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.dtac.utils.DBConnect;
import com.dtac.utils.DateUtil;

public class ImportExcel {
	DBConnect agent 	= new DBConnect();
	Connection conn 	= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil 	= new DateUtil();
	public String ImportExcel(String fileName) 
	throws Exception{
		String result = "";
		try {
//			String fileName = "C:\\test.xls";
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName));
		    HSSFWorkbook wb = new HSSFWorkbook(fs);
		    HSSFSheet sheet = wb.getSheetAt(0);
		    HSSFRow row;
		    HSSFCell cell;
		    
		    //---------Start Import hrmworkgroup
		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    int cols = 0; // No of columns
		    int tmp = 0;

		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0;i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(tmp > cols) cols = tmp;
		        }
		    }
		    for(int r = 1; r < rows; r++) {
		        row = sheet.getRow(r);
		        if(row != null) {
	                String matCode 			= row.getCell((short)0).getStringCellValue();
	                String matName 			= row.getCell((short)1).getStringCellValue();
	                String matSendName 		= row.getCell((short)2).getStringCellValue();
	                String matSupplCode 	= row.getCell((short)3).getStringCellValue();
	                String matSearchName 	= row.getCell((short)4).getStringCellValue();
	                String matTypeCode 		= row.getCell((short)5).getStringCellValue();
	                String matGrpCode 		= row.getCell((short)6).getStringCellValue();
	                String matColorCode 	= row.getCell((short)7).getStringCellValue();
	                String refMatCode 		= row.getCell((short)8).getStringCellValue();
	                String matBrandCode 	= row.getCell((short)9).getStringCellValue();
	                String pUnit 			= row.getCell((short)10).getStringCellValue();
	                String rUnit 			= "";
	                String iUnit 			= "";
	                String matStuffCode 	= row.getCell((short)11).getStringCellValue();
	                String costPrice 		= row.getCell((short)12).getStringCellValue();
	                String normalPrice 		= row.getCell((short)13).getStringCellValue();
	                String empPrice 		= row.getCell((short)14).getStringCellValue();
	                String vipPrice 		= row.getCell((short)15).getStringCellValue();
	                String specialPrice 	= row.getCell((short)16).getStringCellValue();
	                String qtyMinStock 		= row.getCell((short)17).getStringCellValue();
	                String qtyMaxStock 		= row.getCell((short)18).getStringCellValue();
	                String rop 				= row.getCell((short)19).getStringCellValue();
	                String stock1 			= row.getCell((short)20).getStringCellValue();
	                String stock2 			= row.getCell((short)21).getStringCellValue();
	                String matRemark 		= row.getCell((short)22).getStringCellValue();
	                
	                
	                DBMaterial dbMaterial = new DBMaterial();
	        
	    			List materialList = dbMaterial.GetMaterialList(matCode, "", "","", "", "", 
	    					"", "", "", "", "", "");
	    			if (materialList.size() == 1){
	    				dbMaterial.UpdateMaterial(matCode, matName, matSendName, matSearchName, pUnit, rUnit, iUnit, matTypeCode, matGrpCode, 
	    						matRemark, matBrandCode, matSupplCode, matColorCode, matStuffCode, refMatCode, qtyMaxStock, qtyMinStock, "AC", 
	    						stock1, stock2, normalPrice, empPrice, vipPrice, costPrice, specialPrice, rop, "");
	        			
	    			}else {
	    		//		dbMaterial.AddMaterial(matCode, matSendName, matName, matSearchName, pUnit, rUnit, iUnit, matTypeCode, matGrpCode, 
		       //         		matRemark, matBrandCode, matSupplCode, matColorCode, matStuffCode, refMatCode, qtyMaxStock, qtyMinStock, 
		       //         		stock1, stock2, normalPrice, empPrice, vipPrice, costPrice, specialPrice, rop);
	
	    			}    
//	                
		        }
		    }
		  //---------End Import hrmworkgroup
		    
		} catch (Exception e){
			
		}
//		} catch (SQLException e) {
//			throw new Exception(e.getMessage());
//		} finally {
//			try {
//				if (pStmt != null) pStmt.close();
//				if (conn != null)  conn.close();
//			} catch (SQLException e) {
//				throw new Exception(e.getMessage());
//			}
//		}	
		return result;
	}
}
