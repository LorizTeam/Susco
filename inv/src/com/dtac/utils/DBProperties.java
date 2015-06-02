/*
 * Created on 18-06-2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dtac.utils;
import java.io.IOException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
 /**
 * @author tanakarns
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBProperties {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement pStmt = null;
	ResultSet rs	= null;

	public String GetProp(String files, String props, String alertLang) throws Exception { //24-08-2011
    	Properties prop = new Properties();
    	String errName	= "";
    	String fileName = "";
    	if (alertLang == null) alertLang = "";
    	alertLang = "_"+alertLang;    	
    	try {
    			 if (files.equals("adm")) fileName = "../struts/AdminResources.properties";
    		else if (files.equals("hrm")) fileName = "../struts/HumanResources.properties";
    		else if (files.equals("pay")) fileName = "../struts/PayrollResources.properties";
    			 
    		InputStream in = getClass().getResourceAsStream(fileName);

    		prop.load(in);
            errName = prop.getProperty(props+alertLang);
            in.close();
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    	return errName;
    }
}