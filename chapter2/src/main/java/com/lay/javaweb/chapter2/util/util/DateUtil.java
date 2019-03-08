/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lay.javaweb.chapter2.util.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	
	/**
	 * 
	 * @param String  
	 * @return
	 */
	public static LocalDate getAfterDay(LocalDate localDate,long day) {
		
		return localDate.plusDays(day);
	}
	
	/**
	 * 
	 * @param localeDate
	 * @return
	 */
	public static LocalDate getBeforeDay(LocalDate localDate,long day) {
		return localDate.minusDays(day);
	}
	
	/**
	 * 
	 * @param localeDate
	 * @return
	 */
	public static LocalDate getAfterMonth(LocalDate localDate,long month) {
		return localDate.plusMonths(month);
	}
	
	/**
	 * 
	 * @param localeDate
	 * @return
	 */
	public static LocalDate getBeforeMonth(LocalDate localDate,long month) {
		return localDate.minusMonths(month);
	}
	
	/**
	 * 
	 * @param localeDate
	 * @return
	 */
	public static LocalDate getAfterYear(LocalDate localDate,long year) {
		return localDate.plusYears(year);
	}
	
	/**
	 * 
	 * @param localeDate
	 * @return
	 */
	public static LocalDate getBeforeYear(LocalDate localDate,long year) {
		return localDate.minusYears(year);
	}
	/**
	 * 
	 * @param date 如："yyyy-MM-dd'T'HH:mm:ss'Z'
	 * @return
	 */
	public static Calendar parseUnDate(String date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Calendar cal = Calendar.getInstance();
		Date dateDate = sdf.parse(date);
		cal.setTime(dateDate);
		return cal;
	}
	
	/**
	 * 
	 * @param pattern 如：MMMM dd,yyyy
	 * @return
	 */
	public static String getEnDay(String pattern){
        return formatDate(new Date(), pattern, Locale.ENGLISH);
	}
	/**
	 * 
	 * @param date
	 * @param pattern 如：MMMM dd,yyyy
	 * @return
	 */
	public static String getEnDay(Date date, String pattern){
        return formatDate(date, pattern, Locale.ENGLISH);
	}

	/**
	 * 获取YYYY格式
	 *
	 * @return
	 */
	public static String getYears() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 获取YYYY格式
	 *
	 * @return
	 */
	public static String getYear(Date date) {
		return formatDate(date, "yyyy");
	}

	/**
	 * 获取YYYY-MM-DD格式
	 *
	 * @return
	 */
	public static String getDay() {
		return formatDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取YYYY-MM-DD格式
	 *
	 * @return
	 */
	public static String getDay(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 获取YYYYMMDD格式
	 *
	 * @return
	 */
	public static String getDays() {
		return formatDate(new Date(), "yyyyMMdd");
	}

	/**
	 * 获取YYYYMMDD格式
	 *
	 * @return
	 */
	public static String getDays(Date date) {
		return formatDate(date, "yyyyMMdd");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 *
	 * @return
	 */
	public static String getTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss.SSS格式
	 *
	 * @return
	 */
	public static String getMsTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获取YYYYMMDDHHmmss格式
	 *
	 * @return
	 */
	public static String getAllTime() {
		return formatDate(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 *
	 * @return
	 */
	public static String getTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String pattern) {
		return formatDate(date, pattern, null);
	}
	
	public static String formatDate(Date date, String pattern, Locale locale) {
		String formatDate = null;
		if (StringUtils.isNotBlank(pattern)) {
			formatDate = DateFormatUtils.format(date, pattern, locale);
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd", locale);
		}
		return formatDate;
	}

	/**
	 * @Title: compareDate
	 * @Description:(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author
	 *             luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (parseDate(s) == null || parseDate(e) == null) {
			return false;
		}
		return parseDate(s).getTime() >= parseDate(e).getTime();
	}

	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static Date parseDate(String date) {
		return parse(date, "yyyy-MM-dd");
	}

	/**
	 * 格式化日期将字符串yyyy-MM-dd转换字符串yyyyMMdd
	 *
	 * @return
	 */
	public static String transformDateFormter(String date) {
		return getDays(parseDate(date));
	}
	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static Date parseTime(String date) {
		return parse(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		try {
			return DateUtils.parseDate(date, pattern);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 把日期转换为Timestamp
	 *
	 * @param date
	 * @return
	 */
	public static Timestamp format(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 校验日期是否合法
	 *
	 * @return
	 */
	public static boolean isValidDate(String s) {
		return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
	}

	/**
	 * 校验日期是否合法
	 *
	 * @return
	 */
	public static boolean isValidDate(String s, String pattern) {
		return parse(s, pattern) != null;
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
					/ 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 *
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				// System.out.println("dt1 在dt2后");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				// System.out.println("dt1在dt2前");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 得到n天之后的日期
	 *
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 *
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	/**
	 * 凌晨
	 * 
	 * @param date
	 * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
	 *       1 返回yyyy-MM-dd 23:59:59日期
	 * @return
	 */
	public static Date weeHours(Date date, int flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		// 时分秒（毫秒数）
		long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
		// 凌晨00:00:00
		cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

		if (flag == 0) {
			return cal.getTime();
		} else if (flag == 1) {
			// 凌晨23:59:59
			cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
		}
		return cal.getTime();
	}

	/**
	 * 获取当前时间之前或之后几分钟 minute
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getTimeByMinute(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * @param dateString
	 *            Thu May 18 2017 00:00:00 GMT+0800 (中国标准时间)
	 * @return 年月日;
	 */
	public static String parseStrToTime(String dateString) {
		dateString = dateString.replace("GMT", "").replaceAll("\\(.*\\)", "");
		// 将字符串转化为date类型，格式2016-10-12
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
		Date dateTrans = null;
		try {
			dateTrans = format.parse(dateString);
			return new SimpleDateFormat("yyyy-MM-dd").format(dateTrans);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString;

	}

	/**
	 * @param dateString
	 *            "Tue Jul 12 12:10:11 GMT+08:00 2016";
	 * @return 时分秒
	 */
	public static String parseHour(String dateString) {

		dateString = dateString.replace("GMT", "").replaceAll("\\(.*\\)", "");
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
		Date dateTrans = null;
		try {
			dateTrans = format.parse(dateString);
			return new SimpleDateFormat("HH:mm:ss").format(dateTrans);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString;
	}

	public static Date before(Date date, long millSeconds) {
		return fromLong(date.getTime() - millSeconds);
	}

	public static Date after(Date date, long millSeconds) {
		return fromLong(date.getTime() + millSeconds);
	}

	public static Date after(Date date, int nday) {
		return fromLong(date.getTime() + nday * 86400000L);
	}

	public static Date afterNDays(int n) {
		return after(getDate(), n * 86400000L);
	}

	public static Date beforeNDays(int n) {
		return beforeNDays(getDate(), n);
	}

	public static Date beforeNDays(Date date, int n) {
		return fromLong(date.getTime() - n * 86400000L);
	}

	public static Date getDate() {
		return Calendar.getInstance().getTime();
	}

	public static long toLong(Date date) {
		return date.getTime();
	}

	public static Date fromLong(long time) {
		Date date = getDate();
		date.setTime(time);
		return date;
	}

	public static Calendar getDateOfNextYear(Calendar date) {
		Calendar nextDate = (Calendar) date.clone();
		nextDate.add(Calendar.YEAR, 1);
		return nextDate;
	}

	public static Calendar getDateOfNextYear(Calendar date, int n) {
		Calendar nextDate = (Calendar) date.clone();
		nextDate.add(Calendar.YEAR, n);
		return nextDate;
	}

	public static Calendar getDateOfNextMonth(Calendar date) {
		Calendar nextDate = (Calendar) date.clone();
		nextDate.add(Calendar.MONTH, 1);
		return nextDate;
	}

	public static Calendar getDateOfNextMonth(Calendar date, int n) {
		Calendar nextDate = (Calendar) date.clone();
		nextDate.add(Calendar.MONTH, n);
		return nextDate;
	}

	public static Calendar getDateOfLastMonth(Calendar date) {
		Calendar lastDate = (Calendar) date.clone();
		lastDate.add(Calendar.MONTH, -1);
		return lastDate;
	}
	
	public static Calendar getDateOfLastMonth(Calendar date, int n) {
		Calendar lastDate = (Calendar) date.clone();
		lastDate.add(Calendar.MONTH, -n);
		return lastDate;
	}
	public static Calendar getDateOfLastYear(Calendar date) {
		Calendar nextDate = (Calendar) date.clone();
		nextDate.add(Calendar.YEAR, -1);
		return nextDate;
	}
	
	public static Calendar getDateOfLastDay(Calendar date,int n) {
		Calendar nextDate = (Calendar) date.clone();
		nextDate.add(Calendar.DATE, -n);
		return nextDate;
	}
	
	public static Calendar getDateOfNextMonthFirst(Calendar date) {
		Calendar nextDate = (Calendar) date.clone();
		nextDate.set(Calendar.DAY_OF_MONTH, 1);
		nextDate.add(Calendar.MONTH, 1);
		return nextDate;
	}
	
	public static Calendar getDateOfNextMonthFirst(String dateStr) {
		Date date = parseDate(dateStr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getDateOfNextMonthFirst(c);
	}

	public static Calendar getDateOfLastMonth(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(dateStr);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return getDateOfLastMonth(c);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid date format(yyyy-MM-dd): " + dateStr);
		}

	}

	public static Date getDateOfNextMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getDateOfNextMonth(c).getTime();
	}

	public static Date getDateOfNextMonth(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getDateOfNextMonth(c, n).getTime();
	}

	public static Date getDateOfNextYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getDateOfNextYear(c).getTime();
	}

	public static Date getDateOfNextYear(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getDateOfNextYear(c, n).getTime();
	}
	
	public static Date getDateOfLastMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getDateOfLastMonth(c).getTime();
	}
	
	public static Date getDateOfLastMonth(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getDateOfLastMonth(c, n).getTime();
	}

	/**
	 * 格式化Oracle Date
	 * 
	 * @param value
	 * @return
	 */
	// public static String buildDateValue(Object value){
	// if(Func.isOracle()){
	// return "to_date('"+ value +"','yyyy-mm-dd HH24:MI:SS')";
	// }else{
	// return Func.toStr(value);
	// }
	// }
	/**
	 * 日期比较
	 * 
	 * @param dataOne
	 * @param dataTwo
	 * @return
	 */

	public static int dateCompare(String dataOne, String dataTwo) {
		int res = dataOne.compareTo(dataTwo);
		if (res > 0) {
			return 1;
		} else if (res == 0) {
			return 0;
		} else {
			return -1;
		}
	}

	public final static String DATEFORMATYMD = "yyyy-MM-dd";
	public final static String DATEFORMATHMS = "yyyy-MM-dd HH:mm:ss";
	public final static String DATEFORMATHMSS = "yyyy-MM-dd HH:mm:ss:SSS";
	public final static String DATEFORMATCH = "yyyy年MM月dd日";
	public final static String DATEFORMATMCH = "yyyy年MM月dd日 HH时mm分ss秒";
	public final static String DATEFORMATSLASH = "yyyy/MM/dd HH:mm:ss";
	public static DateFormat FORMAT_TIME = null;

	public static Calendar c = Calendar.getInstance();;

	public DateUtil() {
	}

	/**
	 * 判断是否闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean LeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isDayOfBegin(Date date) {
		String temp = getDate(date, 2, "");
		if (temp.substring(11, temp.length()).equals("00:00:00")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 得到格式化后的日期
	 * 
	 * @param date需要格式化的日期
	 * @param marked
	 *            标示格式 （1:yyyy-MM-dd; 2:yyyy-MM-dd HH:mm:ss; 3:yyyy年MM月dd日;
	 *            4:yyyy年MM月dd日 HH时mm分ss秒 5:自定义格式） 当marked=5时，参数dateFormat为必填项
	 *            默认状态为2
	 */
	public static String getDate(Date date, int marked, String dateFormat) {
		switch (marked) {
		case 1:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATYMD);
			break;
		case 2:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATHMS);
			break;
		case 3:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATCH);
			break;
		case 4:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATMCH);
			break;
		case 5:
			FORMAT_TIME = new SimpleDateFormat(dateFormat);
			break;
		case 6:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATHMSS);
			break;
		default:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATHMS);
			break;
		}
		if (date == null)
			date = new Date();
		return FORMAT_TIME.format(date);
	}

	/**
	 * 获得当前的年
	 */
	public static int getYear() {
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获得当前月份
	 * 
	 * @return
	 */
	public static int getMonth() {
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当期日期的前一个月
	 */
	public static int getPreMoth() {
		c.clear();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得今天在本年的第几天
	 * 
	 * @return
	 */
	public static int getDayOfYear() {
		return c.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获得今天在本月的第几天(获得当前日)
	 * 
	 * @return
	 */
	public static int getDayOfMonth() {
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得今天在本周的第几天
	 * 
	 * @return
	 */
	public static int getDayOfWeek() {
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得今天是这个月的第几周
	 * 
	 * @return
	 */
	public static int getWeekOfMonth() {
		return c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 得到二个日期间的间隔毫秒数
	 */
	public static long getTwoDayTimes(String sj1, String sj2, boolean second) {
		SimpleDateFormat myFormatter;
		if (second)
			myFormatter = new SimpleDateFormat(DATEFORMATHMS);
		else
			myFormatter = new SimpleDateFormat(DATEFORMATYMD);
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = date.getTime() - mydate.getTime();
		} catch (Exception e) {
		}
		return day;
	}

	/**
	 * 得到二个日期间的间隔毫秒数 sj1-sj2
	 */
	public static long getTwoDayTimes(Date sj1, Date sj2) {
		long day = 0;
		try {
			day = sj1.getTime() - sj2.getTime();
		} catch (Exception e) {
		}
		return day;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return date1-date2
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		long day = getTwoDayTimes(date1, date2, false) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return date1-date2
	 */
	public static long getDays(Date date1, Date date2) {
		if (date1 == null)
			return 0;
		if (date2 == null)
			return 0;
		// 转换为标准时间
		long day = getTwoDayTimes(date1, date2) / (24 * 60 * 60 * 1000);
		return day;
	}

	// 中间日期
	public static String middleDate(Date dateEnd, Date dateBegin) {
		int date = Integer.valueOf(String.valueOf(getDays(dateEnd, dateBegin)));
		System.out.println("间隔天数" + date);
		if (date % 2 == 0) {
			date = date / 2 + 1;
			System.out.println("偶数提前一天" + date);
		} else {
			date = (int) Math.ceil(Double.valueOf(date) / 2);
			System.out.println("奇数向上取整中间日期" + date);
		}
		String dateStr = DateUtil.getDay(getPreDay(dateEnd, date));
		return dateStr;
	}

	public static List<Integer> dayMonthYear(int day, int month, int year) {
		List<Integer> result = new ArrayList<Integer>();
		if (day == 1) {
			if (month == 1) {
				month = 12;
				year = year - 1;
				day = 31;
			} else if (month > 1) {
				month = month - 1;
				day = calculate(year, month);
			}
		}
		result.add(day);
		result.add(month);
		result.add(year);
		return result;
	}

	public static boolean judge(int year) {
		boolean yearleap = (year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0);// 采用布尔数据计算判断是否能整除
		return yearleap;
	}

	public static int calculate(int year, int month) {
		boolean yearleap = judge(year);
		int day;
		if (yearleap && month == 2) {
			day = 29;
		} else if (!yearleap && month == 2) {
			day = 28;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			day = 30;
		} else {
			day = 31;
		}
		return day;
	}

	// 当前日期的前一天
	public static String getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date dBefore = calendar.getTime();
		String dateStr = DateUtil.getDay(dBefore);
		return dateStr;

	}

	/**
	 * 计算当月最后一天,返回字符串
	 */
	public static String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATYMD);

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 获得本年有多少天
	 */
	public static int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	/**
	 * 获取星期几
	 */
	public static String getWeek(Date date) {
		Calendar c1 = Calendar.getInstance();
		if (date != null) {
			c1.setTime(date);
		}
		return new SimpleDateFormat("EEEE").format(c1.getTime());
	}

	/**
	 * 获取星期几
	 * 
	 * @param strDate
	 *            字符串.
	 * @param marked
	 *            标示格式 （1:yyyy-MM-dd; 2:yyyy-MM-dd HH:mm:ss; 3:yyyy年MM月dd日;
	 *            4:yyyy年MM月dd日 HH时mm分ss秒 5:自定义格式） 当marked=5时，参数dateFormat为必填项
	 *            默认状态为2
	 * @param format
	 *            转换格式如:"yyyy-MM-dd HH:mm:ss"
	 */
	public static String getWeek(String strDate, int marked, String format) {
		return getWeek(parseDate(strDate, marked, format));
	}

	// 获取下星期一
	public static String getNextMonday(String strDate) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(parseDate(strDate, 1, ""));
		int mondayPlus = c1.get(Calendar.DAY_OF_WEEK) - 1;
		if (mondayPlus == 0) {
			mondayPlus = 7;
		}
		c1.add(GregorianCalendar.DATE, 8 - mondayPlus);
		Date monday = c1.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获取当前日期的上一个星期一日期
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getPreMonday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		calendar.add(Calendar.WEEK_OF_MONTH, -1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date firstWeek = calendar.getTime();
		String preMonday = DateUtil.getDate(firstWeek, 1, null);
		return preMonday;
	}
	
	/**
	 * 获取当前日期的上一个星期日日期
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getPreSunday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		calendar.add(Calendar.WEEK_OF_MONTH, -1);//周数减一，即上周
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //日子设为星期天Sunday
		Date lastWeek = calendar.getTime();
		String preSunday = DateUtil.getDate(lastWeek, 1, null);
		return preSunday;
	}


	/**
	 * 根据当前时间的毫秒数（相对于January 1, 1970 00:00:00），取当前时间的字符串
	 * 
	 * @param marked
	 *            标示格式 （1:yyyy-MM-dd; 2:yyyy-MM-dd HH:mm:ss; 3:yyyy年MM月dd日;
	 *            4:yyyy年MM月dd日 HH时mm分ss秒 5:自定义格式） 当marked=5时，参数dateFormat为必填项
	 *            默认状态为2
	 * @param format
	 *            转换格式如:"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String changTimeMillisToStr(Long longDate, int marked, String format) {
		switch (marked) {
		case 1:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATYMD);
			break;
		case 2:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATHMS);
			break;
		case 3:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATCH);
			break;
		case 4:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATMCH);
			break;
		case 5:
			FORMAT_TIME = new SimpleDateFormat(format);
			break;
		default:
			FORMAT_TIME = new SimpleDateFormat(DATEFORMATHMS);
			break;
		}
		return FORMAT_TIME.format(longDate);
	}

	/**
	 * 格式化字符串为日期的函数.
	 * 
	 * @param strDate
	 *            字符串.
	 * @param marked
	 *            标示格式 （1:yyyy-MM-dd; 2:yyyy-MM-dd HH:mm:ss; 3:yyyy年MM月dd日;
	 *            4:yyyy年MM月dd日 HH时mm分ss秒 5:自定义格式） 当marked=5时，参数dateFormat为必填项
	 *            默认状态为2
	 * @param format
	 *            转换格式如:"yyyy-MM-dd HH:mm:ss"
	 * @return 字符串包含的日期,失败则返回当前日期
	 */
	public static Date parseDate(String strDate, int marked, String format) {
		try {
			if (strDate == null || strDate.equals("") || strDate.equals("null") || strDate.trim().length() == 0) {
				return new Date();
			}
			switch (marked) {
			case 1:
				FORMAT_TIME = new SimpleDateFormat(DATEFORMATYMD);
				break;
			case 2:
				FORMAT_TIME = new SimpleDateFormat(DATEFORMATHMS);
				break;
			case 3:
				FORMAT_TIME = new SimpleDateFormat(DATEFORMATCH);
				break;
			case 4:
				FORMAT_TIME = new SimpleDateFormat(DATEFORMATMCH);
				break;
			case 5:
				FORMAT_TIME = new SimpleDateFormat(format);
				break;
			case 6:
				FORMAT_TIME = new SimpleDateFormat(format);
				break;
			default:
				FORMAT_TIME = new SimpleDateFormat(DATEFORMATSLASH);
				break;
			}
			return FORMAT_TIME.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static int getYear(String strDate, int marked, String format) {
		Date d = parseDate(strDate, marked, format);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.YEAR);
	}

	public static int getMonth(String strDate, int marked, String format) {
		Date d = parseDate(strDate, marked, format);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 将字符串的 yyyy-mm-dd hh:mm:ss 翻译成数据库中的Long型
	 * 
	 * @param strDate
	 * @param marked
	 *            标示格式 （1:yyyy-MM-dd; 2:yyyy-MM-dd HH:mm:ss; 3:yyyy年MM月dd日;
	 *            4:yyyy年MM月dd日 HH时mm分ss秒 5:自定义格式） 当marked=5时，参数dateFormat为必填项
	 *            默认状态为2
	 * @param 自定义格式
	 * @return
	 */
	public static Long parseString2Long(String strDate, int marked, String format) {
		return new Long(parseDate(strDate, marked, format).getTime());
	}

	public static Long parseDate2Long(Date date) {
		return new Long(date.getTime());
	}

	/**
	 * 比较当前时间和给定的时间（只比较时、分、秒）
	 */
	public static int compHMS(String hmsStart, String hmsEnd) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		Date date = c.getTime();
		hmsStart = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " "
				+ hmsStart;
		hmsEnd = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + hmsEnd;
		Date dateStart = parseDate(hmsStart, 2, "");
		Date dateEnd = parseDate(hmsEnd, 2, "");
		if (date.getTime() >= dateStart.getTime() && date.getTime() <= dateEnd.getTime()) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 判断输入的字符串是否为日期格式(2011-04-04 10:36:00)
	 * 
	 * @param str需要判断的字符串
	 * @return 是返回true，否则返回false
	 */
	public static boolean isDateTime(String str) {
		String regex = "^(((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		return m.matches();
	}

	/**
	 * 判断是否为日期(例如2011-04-04)
	 * 
	 * @param str需要判断的字符串
	 * @return 是返回true，否则返回false
	 */
	public static boolean isDate(String str) {
		String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-9]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		return m.matches();
	}

	/**
	 * 判断是否为日期(例如10:41:12)
	 * 
	 * @param str需要判断的字符串
	 * @return 是返回true，否则返回false
	 */
	public static boolean isTime(String str) {
		String regex = "^(20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		return m.matches();
	}

	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获得本周一的日期
	public static String getMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得本周星期日的日期
	public static String getCurrentWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 取得指定日期所在周的第一天
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得指定日期所在周的最后一天
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	public static Date getNowDate(int marked, String format) {
		return parseDate(getDate(new Date(), marked, format), marked, format);
	}

	public static Date formatDateStr(Date date, String yymm) {
		SimpleDateFormat sdf;
		if (yymm == null) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			sdf = new SimpleDateFormat(yymm);
		}
		String s_date = sdf.format(date);
		try {
			return sdf.parse(s_date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATHMS);
		String s_date = sdf.format(date);
		try {
			return sdf.parse(s_date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	// 返回某间隔时间的日期
	public static List<String> returnDatestr(String begin, String end, int num) {
		List<String> datelist = new ArrayList<String>();
		Long begintemp = parseString2Long(begin, 1, "");
		Long endtemp = parseString2Long(end, 1, "");
		Long timetemp = (endtemp - begintemp) / num;
		while (begintemp <= endtemp) {
			begintemp = begintemp + timetemp;
			datelist.add(changTimeMillisToStr(begintemp, 1, ""));
		}
		return datelist;
	}

	// 返回当前日期的下一天的日期
	public static String returnNext(String datestr) {
		Long begintemp = parseString2Long(datestr, 1, "");
		begintemp = begintemp + (24 * 60 * 60 * 1000);
		return changTimeMillisToStr(begintemp, 1, "");
	}

	public static Date returnPre(Date datestr) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(datestr);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public static Date returnPre(long datestr) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(datestr);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 指定返回当天的前几天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date returnPre(Date date, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	// log----------
	/**
	 * 得到本月的第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}

	/**
	 * 得到当前日期上个月的第一天日期
	 * 
	 * @return
	 */
	public static String getPreMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = calendar.getTime();
		String preMonthFirstDay = DateUtil.getDate(firstDate, 1, null);
		return preMonthFirstDay;
	}

	/**
	 * 得到本月的最后一天
	 * 
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}

	/**
	 * 得到第二天的日期
	 * 
	 * @return
	 */
	public static Date getNextDay() {
		// 获取当前日期的第二天的日期
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		return date;
	}

	/**
	 * 获取当前时间的后几个小时
	 * 
	 * @param ss
	 *            秒为单位
	 * @return
	 */
	public static Date getNextHour(int ss) {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, ss);
		return c.getTime();
	}

	/**
	 * 获取当前时间的前几个小时
	 * 
	 * @param hh
	 *            小时为单位
	 * @return
	 */
	public static Date getPreHour(int hh) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -hh);
		return calendar.getTime();
	}

	/**
	 * 获取当前时间的前几分钟
	 * 
	 * @param min
	 *            分钟为单位
	 * @return
	 */
	public static Date getPreMinute(int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -min);
		return calendar.getTime();
	}

	/**
	 * 获取当前时间距 day 天的时间
	 * 
	 * @param min
	 *            分钟为单位
	 * @return
	 */
	public static Date getPreDay(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -day);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Title: getPreMinute
	 * @Description: 获取距referenceDate日期之前几分钟的时间
	 * @param @param
	 *            referenceDate
	 * @param @param
	 *            min
	 * @param @return
	 *            参数
	 * @return Date 返回类型
	 * @throws @author
	 *             xushunxing
	 * @email 624412479@qq.com
	 * @date 2017年8月9日
	 */
	public static Date getPreMinute(Date referenceDate, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.MINUTE, -min);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Title: getPreHour
	 * @Description: 获取距referenceDate日期之前几小时的时间
	 * @param @param
	 *            referenceDate
	 * @param @param
	 *            hh
	 * @param @return
	 *            参数
	 * @return Date 返回类型
	 * @throws @author
	 *             xushunxing
	 * @email 624412479@qq.com
	 * @date 2017年9月20日
	 */
	public static Date getPreHour(Date referenceDate, int hh) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.HOUR_OF_DAY, -hh);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Title: getPreDay
	 * @Description: 获取距referenceDate日期之前几天的时间
	 * @param @param
	 *            referenceDate
	 * @param @param
	 *            day
	 * @param @return
	 *            参数
	 * @return Date 返回类型
	 * @throws @author
	 *             xushunxing
	 * @email 624412479@qq.com
	 * @date 2017年8月9日
	 */
	public static Date getPreDay(Date referenceDate, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.DATE, -day);
		return calendar.getTime();
	}

	public static Date getNextMinute(Date referenceDate, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.MINUTE, min);
		return calendar.getTime();
	}

	public static Date getNextHour(Date referenceDate, int hh) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.HOUR_OF_DAY, hh);
		return calendar.getTime();
	}

	public static Date getNextDay(Date referenceDate, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	public static String afterSixMonthDate(Date currentDate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(currentDate);
		gc.add(2, 6);
		gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE));
		gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE));
		System.out.println(getDate(gc.getTime()) + ">>>>>>>>>>>>>>>>>");
		return getDate(gc.getTime());
	}

	public static String afterThreeMonthDate(Date currentDate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(currentDate);
		gc.add(2, 3);
		gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE));
		gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE));
		System.out.println(getDate(gc.getTime()) + ">>>>>>>>>>>>>>>>>");
		return getDate(gc.getTime());
	}

	/**
	 * 获取当前时间距year 年的时间
	 * 
	 * @param year
	 *            年为单位
	 * @return
	 */
	public static Date getPreYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -year);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Title: getPreYear
	 * @Description: 获取距referenceDate日期之前几年的时间
	 * @param @param
	 *            referenceDate
	 * @param @param
	 *            year
	 * @param @return
	 *            参数
	 * @return Date 返回类型
	 */
	public static Date getPreYear(Date referenceDate, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.YEAR, -year);
		return calendar.getTime();
	}

	public static Date getNextYear(Date referenceDate, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}
	
	// 绝限50%日期                秒 分 时 天 月 星期 年
	public static String cronExpressionFirst(String noLimitDate) throws Exception {
		Date dateEnd = DateUtil.parseDate(noLimitDate);
		Date dateBegin = new Date();
		String dateStrR = DateUtil.middleDate(dateEnd, dateBegin);
		String dateStr = isWeekend(dateStrR);
		String[] sdate = dateStr.split("-");
		Integer month = Integer.valueOf(sdate[1]);
		Integer day = Integer.valueOf(sdate[2]);
		String cron = "* * * day month ?";
		String cronExpressionMiddle = cron.replace("day", String.valueOf(day)).replace("month",
				String.valueOf(month) + "");
		return cronExpressionMiddle;
	}

	// 绝限75%日期
	public static String cronExpressionSecond(String noLimitDate) throws Exception {
		Date dateEnd = DateUtil.parseDate(noLimitDate);
		Date date = new Date();
		// 50% 日期
		String dateMiddle = DateUtil.middleDate(dateEnd, date);
		// 75% 日期
		Date dateBegin = DateUtil.parseDate(dateMiddle);
		String dateStrR = DateUtil.middleDate(dateEnd, dateBegin);
		String dateStr = isWeekend(dateStrR);
		String[] sdate = dateStr.split("-");
		Integer month = Integer.valueOf(sdate[1]);
		Integer day = Integer.valueOf(sdate[2]);
		String cron = "* * * day month ?";
		String cronExpressionLast = cron.replace("day", String.valueOf(day)).replace("month", String.valueOf(month));
		return cronExpressionLast;
	}

	// 绝限前一天日期
	public static String cronExpressionThird(String noLimitDate) throws Exception {
		String dateBeforeR = DateUtil.getDate(DateUtil.parseDate(noLimitDate));
		String dateBefore = isWeekend(dateBeforeR);
		String[] sdate = dateBefore.split("-");
		Integer month = Integer.valueOf(sdate[1]);
		Integer day = Integer.valueOf(sdate[2]);
		String cron = "* * * day month ?";
		String cronExpressionLast = cron.replace("day", String.valueOf(day)).replace("month", String.valueOf(month));
		return cronExpressionLast;
	}
	
	public static String isWeekend(String date) throws ParseException{  
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");         
        Date bdate = format1.parse(date);  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(bdate);  
        if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){ 
            return getDay(returnPre(bdate, -1));  
        } else if (cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
        	return getDay(returnPre(bdate, -2));
        }
        return date;
    }  
	
	public static void main(String[] args) throws Exception {
		
	}
}
