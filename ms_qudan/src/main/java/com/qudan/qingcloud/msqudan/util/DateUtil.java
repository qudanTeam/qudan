package com.qudan.qingcloud.msqudan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 时间帮助类
 * 
 * @version $Id: DateUtil.java,v 1.1 2008/05/28 04:29:52 linan Exp $
 * @author sjg
 */
public class DateUtil {

	// private Calendar calendar = Calendar.getInstance();
	
	public static String getFormatDates(String det, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(det);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(date);
	}

	/**
	 * 得到当前的时间，时间格式yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getCurrentDatetime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	/**
	 * 得到当前的时间，时间格式yyyyMMdd
	 * 
	 * @return
	 */
	public static String getCurrentDate2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	/**
	 * 得到当前的时间，时间格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	/**
	 * 得到当前的时间，时间格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getSystemCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}


	/**
	 * 得到当前的时间,自定义时间格式 y 年 M 月 d 日 H 时 m 分 s 秒
	 * 
	 * @param dateFormat
	 *            输出显示的时间格式
	 * @return
	 */
	public static String getCurrentDate(String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}

	/**
	 * 日期格式化，默认日期格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getFormatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 日期格式化，自定义输出日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getFormatDate(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 返回当前日期的 想个的某一时刻的时间，amount为正数 当前时间后的时间， 为负数 当前时间前的时间
	 *
	 * @param d
	 *            ,某一个日期
	 * @param field
	 *            日历字段 y 年 M 月 d 日 H 时 m 分 s 秒
	 * @param amount
	 *            数量
	 * @param format
	 *            日期格式
	 * @return 一个日期
	 */
	public static String getPreDate(Date d, String field, int amount, String format) {
		Date date = getPerDate(d,field,amount);
		if (date == null) {
			return null;
		}
		return getFormatDate(getPerDate(d, field,amount), format);
	}

	/**
	 * 返回当前日期的 想个的某一时刻的时间，amount为正数 当前时间后的时间， 为负数 当前时间前的时间
	 *
	 * @param d
	 *            ,某一个日期
	 * @param field
	 *            日历字段 y 年 M 月 d 日 H 时 m 分 s 秒
	 * @param amount
	 *            数量
	 * @return 一个日期
	 */
	public static  Date getPerDate(Date d, String field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if (field != null && !field.equals("")) {
			if (field.equals("y")) {
				calendar.add(calendar.YEAR, amount);
			} else if (field.equals("M")) {
				calendar.add(calendar.MONTH, amount);
			} else if (field.equals("d")) {
				calendar.add(calendar.DAY_OF_MONTH, amount);
			} else if (field.equals("H")) {
				calendar.add(calendar.HOUR, amount);
			} else if (field.equals("m")) {
				calendar.add(calendar.MINUTE, amount);
			} else if (field.equals("s")) {
				calendar.add(calendar.SECOND, amount);
			}
		} else {
			return null;
		}
		return calendar.getTime();
	}

	/**
	 * 某一个日期的前一个日期
	 * 
	 * @param d
	 *            ,某一个日期
	 * @param field
	 *            日历字段 y 年 M 月 d 日
	 * @param amount
	 *            数量
	 * @return 一个日期
	 */
	public static String getSevenPreDate(Date d, String field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if (field != null && !field.equals("")) {
			if (field.equals("y")) {
				calendar.add(calendar.YEAR, amount);
			} else if (field.equals("M")) {
				calendar.add(calendar.MONTH, amount);
			} else if (field.equals("d")) {
				calendar.add(calendar.DAY_OF_MONTH, amount);
			} else if (field.equals("H")) {
				calendar.add(calendar.HOUR, amount);
			} else if (field.equals("m")) {
				calendar.add(calendar.MINUTE, amount);
			}
		} else {
			return null;
		}
		return getFormatDate(calendar.getTime(), "yyyyMMdd");
	}
	/**
	 * 某一个时间的前七天时间
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  static  String getServenBeforeDate(String date) throws ParseException {
		Date d = new SimpleDateFormat("yyyyMMdd").parse(date);
		String preD = getSevenPreDate(d, "d", -6);
		Date preDate = new SimpleDateFormat("yyyyMMdd").parse(preD);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(preDate);
	}

	/**
	 * 将符合格式的时间字符串转换成Date
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date StrToDate(String str, String sformat) {
		SimpleDateFormat format = new SimpleDateFormat(sformat);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将String型格式化,比如想要将2011-11-11格式化成2011年11月11日,就StringPattern("2011-11-11",
	 * "yyyy-MM-dd","yyyy年MM月dd日").
	 * 
	 * @param date
	 *            String 想要格式化的日期
	 * @param oldPattern
	 *            String 想要格式化的日期的现有格式
	 * @param newPattern
	 *            String 想要格式化成什么格式
	 * @return String
	 */
	public static String StrToFormatDataStr(String date, String oldPattern,
			String newPattern) {
		if (date == null || oldPattern == null || newPattern == null)
			return "";
		SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern); // 实例化模板对象
		SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern); // 实例化模板对象
		Date d = null;
		try {
			d = sdf1.parse(date); // 将给定的字符串中的日期提取出来
		} catch (Exception e) { // 如果提供的字符串格式有错误，则进行异常处理
			e.printStackTrace(); // 打印异常信息
		}
		return sdf2.format(d);
	}

	/**
	 * 两个日期相减，获得秒数
	 *
	 * @return int
	 */
	public static long dateSubM(Date date1, Date date2) {
		return date1.getTime() - date2.getTime();
	}

	/**
	 * 两个日期相减
	 * 
	 * @return int
	 */
	public static int dateSub(Date date1, Date date2) {
		int days = 0;
		days = (int) Math.abs((date2.getTime() - date1.getTime())
				/ (24 * 60 * 60 * 1000)) + 1;
		return days;
	}

	/**
	 * 对传入的日期进行转换，对于形如"2013-5-10"的日期转换为"2013-05-10"
	 * @param paramDate 要进行格式转换的日期
	 * @return 转换后的日期字符串
	 */
	public static String formatDate(String paramDate) {
		String yearStr = paramDate.substring(0, paramDate.indexOf("-"));
		String monthStr = paramDate.substring(paramDate.indexOf("-") + 1,
				paramDate.lastIndexOf("-"));
		String dateStr = paramDate.substring(paramDate.lastIndexOf("-") + 1);
		int month = Integer.parseInt(monthStr);
		int date = Integer.parseInt(dateStr);
		if (month < 10) {
			monthStr = "0" + month;
		}
		if (date < 10) {
			dateStr = "0" + date;
		}
		String newDate = yearStr + "-" + monthStr + "-" + dateStr;
		return newDate;
	}
	
	  /**
     * 转换时间显示格式
     * @param time yyyyMMddHHmmss
     * @return String yyyy年MM月-日 
     */
    public static String showZHtime(String time) throws ParseException {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = dfs.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year;
        int month;
        int day;
        String dateBuf;
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dateBuf = year + "年" + ( month<10 ? "0" + (month+1) : (month+1)) + "月" + ( day<10 ? "0" + day : day) + "日";
        return dateBuf;
    }

    /**
     * 根据年月份得到月份的开始时间和结束时间
     */
    public static Map<String, String> getMonthStartAndEnd(int year,int month){
        Map<String, String> map = new HashMap<String, String>();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, (month-1));
        c.set(Calendar.DATE, 1);
        String startTime = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(c.getTime()) + " 00:00:00";
        if (month == 1 || month==3 || month ==5 || month == 7 || month == 8 || month == 10 || month == 12) {   
            c.set(Calendar.DATE, 31);  
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {    
            c.set(Calendar.DATE, 30);  
        } else if(month == 2){
            c.set(Calendar.DATE, 28);
            if (year % 4  == 0) {
                c.set(Calendar.DATE, 29);
            }
        }
        String endTime  = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(c.getTime()) + " 23:59:59";
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        return map;
    }

    public static Date getDayStart(){
    	String dateStr = getCurrentDate("yyyy-MM-dd");
		dateStr = dateStr + " 00:00:00";
		return StrToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getDayEnd(){
		String dateStr = getCurrentDate("yyyy-MM-dd");
		dateStr = dateStr + " 23:59:59";
		return StrToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
}
