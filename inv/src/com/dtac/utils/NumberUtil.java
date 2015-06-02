package com.dtac.utils;

import org.apache.commons.lang.NumberUtils;

public class NumberUtil {
	
	public String[] CheckFloat(String numStr) throws Exception {
		String errStr[] = {"","",""};
		
		try {
			if (numStr.contains(",")) numStr =  numStr.replaceAll(",", "");
			NumberUtils.createFloat(numStr);
			
		} catch (NumberFormatException e) {
			errStr[0] = "Y";
			errStr[1] = "check ";
			
		} catch (NullPointerException e) {
			errStr[0] = "Y";
			errStr[1] = "input ";
			
		} finally {
			errStr[2] = numStr;
		}		
		return errStr;
	}
	public String[] CheckInteger(String numStr) throws Exception {
		String errStr[] = {"","",""};
		
		try {
			if (numStr.contains(",")) numStr =  numStr.replaceAll(",", "");
			NumberUtils.createInteger(numStr);

		} catch (NumberFormatException e) {
			errStr[0] = "Y";
			errStr[1] = "check ";
			
		} catch (NullPointerException e) {
			errStr[0] = "Y";
			errStr[1] = "input ";
			
		} finally {
			errStr[2] = numStr;
		}		
		return errStr;
	}	
	public boolean isValidIntStr(String numStr) throws Exception {	//21-11-2009
		
		try {
			if (numStr.contains(",")) numStr =  numStr.replaceAll(",", "");
			NumberUtils.createInteger(numStr);

		} catch (NumberFormatException e) {
			return false;
			
		} catch (NullPointerException e) {
			return false;
			
		} 		
		return true;
	}	
	public boolean isValidFloatStr(String numStr) throws Exception {	//7-12-2009
		
		try {
			if (numStr.contains(",")) numStr =  numStr.replaceAll(",", "");
			NumberUtils.createFloat(numStr);

		} catch (NumberFormatException e) {
			return false;
			
		} catch (NullPointerException e) {
			return false;
			
		} 
		return true;
	}	
}