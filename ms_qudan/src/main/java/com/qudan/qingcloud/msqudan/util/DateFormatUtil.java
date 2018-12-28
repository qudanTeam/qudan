package com.qudan.qingcloud.msqudan.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateFormatUtil {

	public static Date getMonthStart(Date date){
		Map<String, String> map = dateMap(date, 10);
		return DateUtil.StrToDate(map.get("stdate").toString(), "yyyy-MM-dd");
	}



	public static Date getMonthEnd(Date date){
		Map<String, String> map = dateMap(date, 10);
		return DateUtil.StrToDate(map.get("edate").toString(), "yyyy-MM-dd");
	}

	public static Map<String, String> dateMap(int interval) {
		Date date = new Date();
		return dateMap(date, interval);
	}

	public static Map<String, String> dateMap(Date date , int interval) {
		Map<String, String> map = new HashMap<String, String>();
		Date d = new Date(date.getTime()*1000-1000*60*60*24);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String dateString = df.format(date); //今天
		//System.out.println(dateString);// new Date()为获取当前系统时间		
		String zutianString = df.format(d);
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();  //上个月日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.MONTH, -1);
		Date theDate = calendar.getTime();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first_prevM = df.format(gcLast.getTime());    //上个月第一天

		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1);
		String day_end_prevM = df.format(calendar.getTime());    //上个月最后一天
//		String day_end_M = df.format(calendar.getTime());		//本月第一天
		
		Calendar sevenc=Calendar.getInstance();  //获取最近一周的日期
		sevenc.setTime(date);
		sevenc.add(Calendar.DATE,-6);  
		Date sevend=sevenc.getTime();
		String day_seven = df.format(sevend);
		
		Calendar twoweek=Calendar.getInstance();//获取最近两周的日期
		twoweek.setTime(date);
		twoweek.add(Calendar.DATE,-13);  
		Date two=twoweek.getTime();
		String day_twoweek = df.format(two);
		
		Calendar threeweek=Calendar.getInstance();//获取最近三周的日期
		threeweek.setTime(date);
		threeweek.add(Calendar.DATE,-20);  
		Date three=threeweek.getTime();
		String day_threeweek = df.format(three);
		
		Calendar onemonth=Calendar.getInstance();//获取最近30的日期
		onemonth.setTime(date);
		onemonth.add(Calendar.DATE,-29);  
		Date onem=onemonth.getTime();
		String lastmonth = df.format(onem);
		
		Calendar twomonth=Calendar.getInstance();//获取最近60天的日期
		twomonth.setTime(date);
		twomonth.add(Calendar.DATE,-59);  
		Date twom=twomonth.getTime();
		String lasttwomonth = df.format(twom);
		
		Calendar sixmonth=Calendar.getInstance();//获取最近180的日期
		sixmonth.setTime(date);
		sixmonth.add(Calendar.DATE,-179);  
		Date sixm=sixmonth.getTime();
		String lasthalfyear = df.format(sixm);
		
		Calendar ca=Calendar.getInstance();  //获取本周开始和结束的日期
		ca.setTime(date);
		int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
		  //中国习惯：周一是一周的开始
		    if (dayOfWeek==1) {
					dayOfWeek=7;
					}else{
					dayOfWeek--;
				}
		  Calendar  cal=(Calendar)ca.clone();
		  cal.add(Calendar.DATE,1-dayOfWeek);
		  Date date1=cal.getTime();
		  cal=(Calendar)ca.clone();
		  cal.add(Calendar.DATE,7-dayOfWeek);
		  Date date2=cal.getTime();
		  String thisWeeks=df.format(date1);
		  String thisWeeke=df.format(date2);

		  Calendar caM=Calendar.getInstance();//获取本月一号和后一天的日期
			caM.setTime(date);
		    int maximum = caM.getActualMaximum(Calendar.DAY_OF_MONTH);
		    int minmum=caM.getActualMinimum(Calendar.DAY_OF_MONTH);
		    int day=caM.get(Calendar.DAY_OF_MONTH);
		    Calendar calLm=(Calendar)caM.clone();
		    calLm.add(Calendar.DATE, maximum-day);
		    Date dateD=calLm.getTime();
		    String thisMonthe=df.format(dateD);
		    calLm=(Calendar)caM.clone();
		    calLm.add(Calendar.DATE, minmum-day);
		    Date dateX=calLm.getTime();
		    String thisMonths=df.format(dateX);
		    
		    
		    Calendar lastWeek=Calendar.getInstance();//获取上周开始和结束日期
			lastWeek.setTime(date);
		    lastWeek.set(Calendar.DAY_OF_WEEK,1);//周天
		    lastWeek.add(Calendar.DATE, 0);
		    Date datee=lastWeek.getTime();
		    String lastWeeke=df.format(datee);
		    lastWeek.add(Calendar.DATE, -6);//上周一
		    Date dates=lastWeek.getTime();
		    String lastWeeks=df.format(dates);
		    
		    
		switch (interval) {

		case 0:  //今天
			map.put("stdate", dateString +" 00:00:00");
			map.put("edate",dateString +" 23:59:59");
			break;
			
		case 1:    //昨天
			map.put("stdate", zutianString +" 00:00:00");
			map.put("edate",zutianString +" 23:59:59");			
			break;
			
		case 2:    //最近一周
			map.put("stdate", day_seven +" 00:00:00");
			map.put("edate",dateString +" 23:59:59");			
			break;
			
		case 3:     //最近两周
			map.put("stdate", day_twoweek +" 00:00:00");
			map.put("edate",dateString +" 23:59:59");			
			break;
			
		case 4:     //最近三周
			map.put("stdate", day_threeweek +" 00:00:00");
			map.put("edate",dateString +" 23:59:59");			
			break;
			
		case 5:        //最近一个月
			map.put("stdate", lastmonth +" 00:00:00");
			map.put("edate",dateString +" 23:59:59");			
			break;
			
		case 6:        //最近两个月
			map.put("stdate", lasttwomonth +" 00:00:00");
			map.put("edate",dateString +" 23:59:59");			
			break;
			
		case 7:    //最近半年
			map.put("stdate", lasthalfyear +" 00:00:00");
			map.put("edate",dateString +" 23:59:59");			
			break;	
			
		case 8:    //本周
			map.put("stdate", thisWeeks +" 00:00:00");
			map.put("edate",thisWeeke +" 23:59:59");			
			break;
			
		case 9:    //上周
			map.put("stdate", lastWeeks +" 00:00:00");
			map.put("edate",lastWeeke +" 23:59:59");			
			break;
			
		case 10:    //本月
			map.put("stdate", thisMonths +" 00:00:00");
			map.put("edate",thisMonthe +" 23:59:59");			
			break;
			
		case 11:    //上月
			map.put("stdate", day_first_prevM +" 00:00:00");
			map.put("edate",day_end_prevM +" 23:59:59");			
			break;
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(DateFormatUtil.dateMap(DateUtil.StrToDate("2018-01-10", "yyyy-MM-dd"), 10));
	}
}
