package com.dtac.utils;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	public String CnvToYYYYMMDD(String date,char concatSymbol) {
		// input dd/mm/yyyy >>>  yyyy/mm/dd
		String dd;
		String mm;
		String yyyy;
		String ansDate = null;
		// 1/1/2000 --> 01/01/2000 --> 2000/01/01		
		if (date.substring(1,2).equals("/")&&date.substring(3,4).equals("/"))
		{
			dd = "0"+date.substring(0,1);
			mm = "0"+date.substring(2,3);
			yyyy = date.substring(4,8);
			ansDate = (yyyy+concatSymbol+mm+concatSymbol+dd);		
		}
		else
		// 1/10/2000 --> 01/10/2000 --> 2000/10/01		
		if (date.substring(1,2).equals("/")&&date.substring(4,5).equals("/"))
		{
			dd = "0"+date.substring(0,1);
			mm = date.substring(2,4);
			yyyy = date.substring(5,9);
			ansDate = (yyyy+concatSymbol+mm+concatSymbol+dd);		
		}
		else
		// 10/1/2000 --> 10/01/2000 --> 2000/01/10
		if (date.substring(2,3).equals("/")&&date.substring(4,5).equals("/"))
		{
			dd = date.substring(0,2);
			mm = "0"+date.substring(3,4);
			yyyy = date.substring(5,9);
			ansDate = (yyyy+concatSymbol+mm+concatSymbol+dd);
		
		}
		else
		// 10/10/2000 --> 01/01/2000 --> 2000/01/01
		if (date.substring(2,3).equals("/")&&date.substring(5,6).equals("/"))
		{
			dd = date.substring(0,2);
			mm = date.substring(3,5);
			yyyy = date.substring(6,10);
			ansDate = (yyyy+concatSymbol+mm+concatSymbol+dd);
			
		}
		return ansDate;
	}
	public String GetDD(String date) { 
		return date.substring(8,10);
	}
	public String GetMM(String date) { 
		return date.substring(5,7);
	}
	public String GetYYYY(String date) { 
		return date.substring(0,4);
	}
	public String CnvToDDMMYYYY(String date) {
//		 input yyyy/mm/dd >>>  dd/mm/yyyy
		String ansDate;
		String dd;
		String mm;
		String yyyy;
		
		dd = date.substring(8,10);
		mm = date.substring(5,7);
		yyyy = date.substring(0,4);
		ansDate = (dd+"/"+mm+"/"+yyyy);
		return ansDate;
	}
	public String CnvToYYYYMMDD(String date) {
//		 input yyyy/mm/dd >>>  dd/mm/yyyy
		String ansDate;
		String dd;
		String mm;
		String yyyy;
		dd = date.substring(0,2);
		mm = date.substring(3,5);
		yyyy = date.substring(6,10);
		ansDate = (yyyy+"-"+mm+"-"+dd);
		return ansDate;
	}
	public String CnvToMMDDYYYY(String date) {
//		 input yyyy/mm/dd >>>  mm/dd/yyyy
		String ansDate;
		String dd;
		String mm;
		String yyyy;
		dd = date.substring(8,10);
		
		mm = date.substring(5,7);
		yyyy = date.substring(0,4);
		ansDate = (mm+"/"+dd+"/"+yyyy);
		return ansDate;
	}
	public String CnvToDDMMYYYYThaiYear(String date)
	{    
//		 input yyyy/mm/dd >>>  dd/mm/yyyy
//		 change year from 2007 to 2550		
		String ansDate;
		String dd;
		String mm;
		String yyyy;
		dd = date.substring(8,10);
		
		mm = date.substring(5,7);
		yyyy = String.valueOf(Integer.parseInt(date.substring(0,4))+543);
		ansDate = (dd+"/"+mm+"/"+yyyy);
		return ansDate;
	}
	public String CnvToYYYYMMDDEngYear(String date,char concatSymbol)
	{    
//		 input  dd/mm/yyyy >>>  yyyy/mm/dd
//	ex.	 change year from 2550 to 2007		
		String ansDate;
		String dd;
		String mm;
		String yyyy;
		dd = date.substring(0,2);
		
		mm = date.substring(3,5);
		yyyy = String.valueOf(Integer.parseInt(date.substring(6,10))-543);
		ansDate = (yyyy+concatSymbol+mm+concatSymbol+dd);
		//System.out.println("ansDate"+ansDate);
		return ansDate;
	}
	public String CnvToYYYYMMDDEngY(String date,char concatSymbol)
	{    
//		 input  dd/mm/yyyy >>>  yyyy/mm/dd
//	ex.	 change year from 2550 to 2007		
		String ansDate;
		String dd;
		String mm;
		String yyyy;
		dd = date.substring(0,2);
		
		mm = date.substring(3,5);
		yyyy = String.valueOf(Integer.parseInt(date.substring(6,10)));
		ansDate = (yyyy+concatSymbol+mm+concatSymbol+dd);
		//System.out.println("ansDate"+ansDate);
		return ansDate;
	}
	public String addDate(String expDate, int num) {
		Calendar expCalendar=new GregorianCalendar();
		String [] sDate=expDate.split("/");
		expCalendar.set(Integer.parseInt(sDate[0]),Integer.parseInt(sDate[1])-1,Integer.parseInt(sDate[2]));
		
		long exp=expCalendar.getTimeInMillis();
		long plusMi=(long)num*(24*60*60*1000);
		long endMi=exp+plusMi;
		Calendar endCalendar=new GregorianCalendar();
		endCalendar.setTimeInMillis(endMi);
		
		String dd = String.valueOf(endCalendar.get(Calendar.DAY_OF_MONTH));
		String mm = String.valueOf(endCalendar.get(Calendar.MONTH)+1);
		if (dd.trim().length() == 1) dd = "0"+ dd.trim();
		if (mm.trim().length() == 1) mm = "0"+ mm.trim();
		String endDate=endCalendar.get(Calendar.YEAR)+"-"+mm+"-"+dd;
		
		return endDate;
	}
	public String subtractDate(String expDate, int num) {
		Calendar expCalendar=new GregorianCalendar();
		String [] sDate=expDate.split("/");
		expCalendar.set(Integer.parseInt(sDate[0]),Integer.parseInt(sDate[1])-1,Integer.parseInt(sDate[2]));
		
		long exp=expCalendar.getTimeInMillis();
		long minusMi=(long)num*(24*60*60*1000);
		long endMi=exp-minusMi;
		Calendar endCalendar=new GregorianCalendar();
		endCalendar.setTimeInMillis(endMi);
		
		String dd = String.valueOf(endCalendar.get(Calendar.DAY_OF_MONTH));
		String mm = String.valueOf(endCalendar.get(Calendar.MONTH)+1);
		if (dd.trim().length() == 1) dd = "0"+ dd.trim();
		if (mm.trim().length() == 1) mm = "0"+ mm.trim();
		String endDate=endCalendar.get(Calendar.YEAR)+"-"+mm+"-"+dd;
		
		return endDate;
	}
	public static String endDate(int num) {
		// this method for find next date from add num to current date
		Calendar currentCalendar=new GregorianCalendar();
		long nowMi=currentCalendar.getTimeInMillis();
		long plusMi=(long)num*(24*60*60*1000);
		long endMi=nowMi+plusMi;
		Calendar endCalendar=new GregorianCalendar();
		endCalendar.setTimeInMillis(endMi);
		String endDate=endCalendar.get(Calendar.YEAR)+"-"+(endCalendar.get(Calendar.MONTH)+1)+"-"+endCalendar.get(Calendar.DAY_OF_MONTH);
		return endDate;
	}
	public String curDate() {	//20-02-2010
		Calendar currentCalendar= new GregorianCalendar();
		long nowMi				= currentCalendar.getTimeInMillis();
		Calendar curCalendar	= new GregorianCalendar();
		curCalendar.setTimeInMillis(nowMi);
		String days		= String.valueOf(curCalendar.get(Calendar.DAY_OF_MONTH));
		String month 	= String.valueOf(curCalendar.get(Calendar.MONTH)+1);
		String year		= String.valueOf(curCalendar.get(Calendar.YEAR));
		if (days.trim().length() == 1) days = "0"+ days.trim();
		if (month.trim().length() == 1) month = "0"+ month.trim();
		String today	= days+ "/" +month+ "/" +year;
		//curCalendar.get(Calendar.DAY_OF_MONTH)+"/"+(curCalendar.get(Calendar.MONTH)+1)+"/"+curCalendar.get(Calendar.YEAR);
		return today;
	}
	public String curDateTH() throws Exception {
		Calendar currentCalendar=new GregorianCalendar();
		long nowMi=currentCalendar.getTimeInMillis();
		Calendar curCalendar=new GregorianCalendar();
		curCalendar.setTimeInMillis(nowMi);
		String month 	= String.valueOf(curCalendar.get(Calendar.MONTH)+1);
		String year		= String.valueOf(curCalendar.get(Calendar.YEAR)+543);
		
		//DBMonth dbMonth = new DBMonth();
		String monthName= ""; //dbMonth.GetMonthTHFullName(month);
		
		String today	= curCalendar.get(Calendar.DAY_OF_MONTH)+" "+monthName+" "+year;
		return today;
	}
	public int maxDayCurMonth() {
		Calendar currentCalendar = new GregorianCalendar();
		long nowMi=currentCalendar.getTimeInMillis();
		Calendar curCalendar = new GregorianCalendar();
		curCalendar.setTimeInMillis(nowMi);
	    return curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
	}
	public int dayOfWeek(String year,String month,String days) {
		//int intDay	= 0;
		Calendar newCalendar = new GregorianCalendar();
		newCalendar.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(days),0,0,0);
		newCalendar.setTime(newCalendar.getTime());
		//intDay = newCalendar.get(Calendar.DAY_OF_WEEK);
		//int intDay1 = newCalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		//System.out.println("day "+year+"/"+month+"/"+days+"="+intDay);	
		return newCalendar.get(Calendar.DAY_OF_WEEK);
	}
	public int maxDayForMonth(String year,String month) {
		int intMonth = Integer.parseInt(month);
		int days	= 0;
		if (intMonth == 1) {
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.JANUARY, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 2) {
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.FEBRUARY, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 3) {
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.MARCH, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 4) {			
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.APRIL, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 5) {			
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.MAY, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 6) {			
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.JUNE, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 7) {			
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.JULY, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 8) {			
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.AUGUST, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 9) {			
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.SEPTEMBER, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 10) {			
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.OCTOBER, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 11) {			
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.NOVEMBER, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (intMonth == 12) {			
			Calendar curCalendar = new GregorianCalendar(Integer.parseInt(year), Calendar.DECEMBER, 1);
			days = curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}

	    return days; 
	}
	public String curMonth() {
		Calendar currentCalendar=new GregorianCalendar();
		long nowMi=currentCalendar.getTimeInMillis();
		Calendar curCalendar=new GregorianCalendar();
		curCalendar.setTimeInMillis(nowMi);
		//int months = curCalendar.get(Calendar.MONTH)+1;
		String months = String.valueOf(curCalendar.get(Calendar.MONTH)+1);
		if (months.trim().length() == 1) months = "0" + months;
		return months;
	}
	public String curYear() {
		Calendar currentCalendar=new GregorianCalendar();
		long nowMi=currentCalendar.getTimeInMillis();
		Calendar curCalendar=new GregorianCalendar();
		curCalendar.setTimeInMillis(nowMi);
		return String.valueOf(curCalendar.get(Calendar.YEAR));
	}
	public String curTHYear() {
		Calendar currentCalendar=new GregorianCalendar();
		long nowMi=currentCalendar.getTimeInMillis();
		Calendar curCalendar=new GregorianCalendar();
		curCalendar.setTimeInMillis(nowMi);
		return String.valueOf(curCalendar.get(Calendar.YEAR)+543);
	}
	public  String curDateymd(){
		Calendar currentCalendar=new GregorianCalendar();
		long nowMi=currentCalendar.getTimeInMillis();
		Calendar curCalendar=new GregorianCalendar();
		curCalendar.setTimeInMillis(nowMi);
		String today=curCalendar.get(Calendar.YEAR)+"/"+(curCalendar.get(Calendar.MONTH)+1)+"/"+curCalendar.get(Calendar.DAY_OF_MONTH);                   
		return today;
	}
	public  String curTime(){
		Calendar currentCalendar=new GregorianCalendar();
		long nowMi=currentCalendar.getTimeInMillis();
		Calendar curCalendar=new GregorianCalendar();
		curCalendar.setTimeInMillis(nowMi);
		String hh = String.valueOf(curCalendar.get(Calendar.HOUR_OF_DAY));
		String mm = String.valueOf(curCalendar.get(Calendar.MINUTE));
		String ss = String.valueOf(curCalendar.get(Calendar.SECOND));
		if (hh.length() == 1) hh = "0"+ hh;
		if (mm.length() == 1) mm = "0"+ mm;
		if (ss.length() == 1) ss = "0"+ ss;
		//String today = hh+":"+mm+":"+ss;                   
		return hh+":"+mm+":"+ss;
	}
	public String curDateTime() {
		Calendar currentCalendar=new GregorianCalendar();
		long nowMi=currentCalendar.getTimeInMillis();
		Calendar curCalendar=new GregorianCalendar();
		curCalendar.setTimeInMillis(nowMi);
		String today = curCalendar.get(Calendar.YEAR)+"-"+(curCalendar.get(Calendar.MONTH)+1)+"-"+curCalendar.get(Calendar.DAY_OF_MONTH)+" " +
		curCalendar.get(Calendar.HOUR_OF_DAY)+":"+curCalendar.get(Calendar.MINUTE)+":"+curCalendar.get(Calendar.SECOND);
		return today;
	}
	public int showNumDay(String expDate){
		Calendar currentCalendar=new GregorianCalendar();
		Calendar expCalendar=new GregorianCalendar();
		String [] sDate=expDate.split("-");
		expCalendar.set(Integer.parseInt(sDate[0]),Integer.parseInt(sDate[1])-1,Integer.parseInt(sDate[2]));
		long now=currentCalendar.getTimeInMillis();
		long exp=expCalendar.getTimeInMillis();
		long numday=(now-exp)/(24*60*60*1000);
		if(numday>0)
			return (int)numday;
		else
			return 0;
	}
	public boolean isValidDateStr(String inDate) {	//22-11-2009
		if (inDate == null) return false;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (inDate.trim().length() != dateFormat.toPattern().length()) return false;
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
	    } catch (ParseException e) {
	    	//System.out.println("e1"+inDate+";");
	    	return false;
	    } catch (IllegalArgumentException e) {
	    	//System.out.println("e2"+inDate+";");
	    	return false;
	    }
	   // System.out.println("pass"+inDate+";");
	    return true;
	}
	public boolean isValidTimeStr(String inTime) {	//07-12-2009
		if (inTime == null) return false;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		if (inTime.trim().length() != dateFormat.toPattern().length()) return false;
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inTime.trim());
	    } catch (ParseException e) {
	    	//System.out.println("e1"+inDate+";");
	    	return false;
	    } catch (IllegalArgumentException e) {
	    	//System.out.println("e2"+inDate+";");
	    	return false;
	    }
	   // System.out.println("pass"+inDate+";");
	    return true;
	}
	public String getTimeDiff(String firstTime, String secondTime) {	//01-06-2012
		Date d1 = null;
		Date d2 = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		dateFormat.setLenient(false);
		try {
			d1 = dateFormat.parse(firstTime.trim());
			d2 = dateFormat.parse(secondTime.trim());
	    } catch (ParseException e) {
	    	//System.out.println("e1"+inDate+";");
	    	return "error time";
	    }
	    long diff = d2.getTime() - d1.getTime();
	    if (diff <= 0) return "00.00";
	    
        long diffSeconds = diff / 1000 % 60;   
        long diffMinutes = diff / (60 * 1000) % 60;  
        long diffHours = diff / (60 * 60 * 1000);
        
        String hh = String.valueOf(diffHours);
		String mm = String.valueOf(diffMinutes);
		String ss = String.valueOf(diffSeconds);
		if (hh.length() == 1) hh = "0"+ hh;
		if (mm.length() == 1) mm = "0"+ mm;
		if (ss.length() == 1) ss = "0"+ ss;
		//String today = hh+":"+mm+":"+ss;                   
		return hh+"."+mm;
	}
	public float getTimeDiff_Float(String firstTime, String secondTime) {	//01-06-2012
		Date d1 = null;
		Date d2 = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		dateFormat.setLenient(false);
		try {
			d1 = dateFormat.parse(firstTime.trim());
			d2 = dateFormat.parse(secondTime.trim());
	    } catch (ParseException e) {
	    	//System.out.println("e1"+inDate+";");
	    	return 0;
	    }
	    long diff = d2.getTime() - d1.getTime();
	    if (diff <= 0) return 0;
	    
        long diffMinutes = diff / (60 * 1000) ; 
		return (float) (diffMinutes / 60.00);
	}

}