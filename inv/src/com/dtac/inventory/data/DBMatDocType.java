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

import com.dtac.inventory.form.MatDocTypeForm;
import com.dtac.inventory.form.MaterialTypeForm;
import com.dtac.utils.DBConnect;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBMatDocType {

	DBConnect agent = new DBConnect();
	Connection conn	= null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public List GetMatDocTypeList(String docGrpCode, String docTypeCode, String status)
	throws Exception { //30-05-2012
		List docTypeList = new ArrayList();
		String docTypeName = "";
		try {
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT a.docgrpcode, a.doctypecode, a.doctypename, a.status " +			
			"FROM mmdoctype a " +			
			"WHERE a.docgrpcode = '"+docGrpCode+"' ";			
			
			if (!docTypeCode.equals("")) sqlStmt = sqlStmt + "AND a.doctypecode = '"+docTypeCode+"' ";
			if (!status.equals("")) sqlStmt = sqlStmt + "AND a.status = '"+status+"' ";
			
			sqlStmt = sqlStmt + "ORDER BY a.doctypecode DESC" ;
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				docGrpCode	= rs.getString("docgrpcode");
				docTypeCode	= rs.getString("doctypecode");		
				docTypeName = rs.getString("doctypename");
				
								
				docTypeList.add(new MatDocTypeForm(docGrpCode, docTypeCode, docTypeName, status));			
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
		return docTypeList;
	}
}