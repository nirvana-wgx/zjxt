package com.ctevs.common;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class DateUtils {

    public static final Pattern DATE_PATTERN = Pattern
            .compile("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$");

    /**
     * 获取当前时间并进行格式化输出
     *
     * @return yyyy/MM/dd HH:mm:ss 时间
     * @throws ParseException
     */
    public static String getDate() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return s.format(new Date());
        //return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(s.format(new Date()));
    }

    public static String getDateYMHD() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH");
        return s.format(new Date());
    }

    /**
     * 获取当前时间并进行格式化输出
     *
     * @return yyyy-MM-dd 时间
     */
    public static String getDateYMD() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");
        return s.format(new Date());
    }

    /**
     * 获取时间的年月日时分
     *
     * @return
     */
    public static String getDateYMDHMSFormat() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return s.format(new Date());
    }

    /**
     * 获取时间的年月日时分
     *
     * @return
     */
    public static String getDateYMDHM() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return s.format(new Date());
    }

    /**
     * 获取当前时间并进行格式化输出
     *
     * @return yyyyMMdd 时间
     */
    public static String getDateYYMMDD() {
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        return s.format(new Date());
    }

    /**
     * 获取当前时间并进行格式化输出
     *
     * @return HHmmss 时间
     */
    public static String getDateHHmmss() {
        SimpleDateFormat s = new SimpleDateFormat("HHmmss");
        return s.format(new Date());
    }

    /**
     * 获取当前时间并进行格式化输出
     *
     * @return yyyy-MM 时间
     */
    public static String getDateYM() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM");
        return s.format(new Date());
    }

    /**
     * 获取当前年并进行格式化输出
     *
     * @return yyyy 时间
     */
    public static String getYear() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy");
        return s.format(new Date());
    }

    /**
     * 获取当前时间并进行格式化输出
     *
     * @return MMddHHmmss 时间
     */
    public static String getDateMDHMS() {
        SimpleDateFormat s = new SimpleDateFormat("MMddHHmmss");
        return s.format(new Date());
    }

    /**
     * 获取当前时间并进行格式化输出
     *
     * @return YYMMddHHmmss 时间
     */
    public static String getDateYMDHMS() {
        SimpleDateFormat s = new SimpleDateFormat("YYMMddHHmmss");
        return s.format(new Date());
    }

    /**
     * 获取当前时间并进行格式化输出
     *
     * @return YYYYMMddHHmmss 时间
     */
    public static String getDateTotalYMDHMS() {
        SimpleDateFormat s = new SimpleDateFormat("YYYYMMddHHmmss");
        return s.format(new Date());
    }

    /**
     * 时间并行格式
     *
     * @return yyyy-MM-dd 时间
     */
    public static String getDateYMD(String date) {
        if (date == null) {
            return null;
        }
        if (DATE_PATTERN.matcher(date).matches()) {
            return date.substring(0, 10);
        }
        return date;
    }

    /**
     * 时间并行格式
     *
     * @return yyyy-MM-dd 时间
     */
    public static String getDateYMDChinese(String date) {
        if (date == null) {
            return null;
        }
        StringBuffer str = new StringBuffer(date.substring(0, 3)).append("年").append(date.substring(4, 6))
                .append("月").append(date.substring(7, 9)).append("日");
        return str.toString();
    }

    /**
     * 获取时间戳
     *
     * @return 时间戳字符串
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 获取当前时间的前10天--wang
     *
     * @return
     */
    public static String getBeforeDate() {
        Calendar canlender = Calendar.getInstance();
        canlender.setTime(new Date());
        canlender.add(Calendar.DAY_OF_MONTH, -10);
        return new SimpleDateFormat("yyyy/MM/dd").format(canlender.getTime());
    }

    /**
     * 格式化抽奖时间
     *
     * @param date 抽奖时间
     * @return HH:mm-HH:mm 时间
     */
    public static String formatLuckyDrawTime(String date) {
        if (StringUtils.isEmpty(date) || date.length() < 13) {
            return null;
        }
        String hh = date.substring(11, 13);
        Integer h = Integer.parseInt(hh);
        StringBuilder sb = new StringBuilder();
        sb.append(h).append(":").append("00").append("-").append(h + 1).append(":").append("00");
        return sb.toString();
    }

    /**
     * 获取第二天时间
     *
     * @return yyyy-MM-dd 第二天时间
     */
    public static String getNextDay() {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String nextDay = s.format(date);
        return nextDay;
    }

    /**
     * 获取第二天时间
     *
     * @return yyyyMMdd 第二天时间
     */
    public static String getAfterDay() {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);// 把日期往后增加1天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推1天的结果
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        String afterDay = s.format(date);
        return afterDay;
    }

    /**
     * 格式化时间
     *
     * @param date 时间
     * @return yyyy-MM-dd 时间
     */
    public static String formatDateYMD(Date date) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return s.format(date);
    }

    /**
     * 格式化时间
     *
     * @param date 时间
     * @return yyyy-MM-dd 时间
     */
    public static String formatDateToYMD(Date date) {
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        return s.format(date);
    }

    /**
     * 格式化时间
     *
     * @param date 时间
     * @return yyyy-MM-dd 时间
     */
    public static String formatDateHMS(Date date) {
        SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
        return s.format(date);
    }

    /**
     * 格式化时间
     *
     * @param date 时间
     * @return yyyy-MM-dd 时间
     */
    public static String formatDateToHMS(Date date) {
        SimpleDateFormat s = new SimpleDateFormat("HHmmss");
        return s.format(date);
    }

    /**
     * 获取当前时间的fate天前的日期
     *
     * @return
     */
    public static String getBeforeWeekDate(String date, Integer fate) {
        Calendar canlender = Calendar.getInstance();
        Date newDate = new Date();
        try {
            newDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        canlender.setTime(newDate);
        canlender.add(Calendar.DAY_OF_MONTH, fate * (-1));
        return new SimpleDateFormat("yyyy-MM-dd").format(canlender.getTime());
    }

    public static Date YMtoDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取时间的间隔 单位分钟
     *
     * @param tareTime
     * @param grossTime
     * @return
     */
    public static long getIntervalOfDate(String tareTime, String grossTime) {
        long minute = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = sdf.parse(tareTime);
            Date date2 = sdf.parse(grossTime);
            Calendar dateOne = Calendar.getInstance();
            Calendar dateTwo = Calendar.getInstance();
            dateOne.setTime(date1);
            dateTwo.setTime(date2);
            long timeOne = dateOne.getTimeInMillis();
            long timeTwo = dateTwo.getTimeInMillis();
            minute = (timeTwo - timeOne) / (1000 * 60);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return minute;
    }

    /**
     * 获取指定月的前一月（年）或后一月（年）
     *
     * @param dateStr
     * @param addYear
     * @param addMonth
     * @return 输入的时期格式为yyyy-MM，输出的日期格式为yyyy-MM
     * @throws Exception
     */
    public static String getLastDay(String dateStr, int addYear, int addMonth) throws ParseException {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM");
            Date sourceDate = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sourceDate);
            cal.add(Calendar.YEAR, addYear);
            cal.add(Calendar.MONTH, addMonth);

            java.text.SimpleDateFormat returnSdf = new java.text.SimpleDateFormat("yyyy-MM");
            String dateTmp = returnSdf.format(cal.getTime());
            // Date returnDate = returnSdf.parse(dateTmp);
            return dateTmp;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ParseException(dateStr, addMonth);
        }
    }

    /**
     * 当月第一天
     *
     * @return
     */
    public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        return str.toString();

    }

    /**
     * 本年第一月
     *
     * @return
     */
    public static String getFirstMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_YEAR, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        return str.toString();
    }

    /**
     * 获取当前月的天数
     *
     * @return
     */
    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取两个时间相隔的小时数(非整数，小时数)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Double getTimeDifferent(String startTime, String endTime) {
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = dFormat.parse(startTime);
            Date endDate = dFormat.parse(endTime);
            double diff = endDate.getTime() - startDate.getTime();
            DecimalFormat df = new DecimalFormat("0.00");
            return new Double(df.format(diff / (1000 * 60 * 60)).toString());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 为年月日添加 -  如20121214变为20112-12-14
     */
    public static String addHengToDate(String date) {
        String returnDate = null;
        if (date != null && !"".equals(date.trim())) {
            returnDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        }
        return returnDate;
    }

    /**
     * 为时分秒添加 :  如121214变为12:14:15
     */
    public static String addHengToTime(String time) {
        String returnTime = null;
        if (time != null && !"".equals(time.trim())) {
            returnTime = time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6);
        }
        return returnTime;
    }
  /*  public static void main(String[] args) {
		System.out.println(addHengToDate("20121214"));
		System.out.println(addHengToTime("121214"));
	}*/

	/*public static String getNextDay(String date) throws Exception {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Date today = formate.parse(date);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, 1);
		Date tommorw = new Date();
		tommorw = calendar.getTime();
		return formate.format(tommorw);
	}*/

    /**
     * 得到下一天
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static String getNextDay(String date) throws Exception {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
        Date today = formate.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 1);
        Date tommorw = new Date();
        tommorw = calendar.getTime();
        return formate.format(tommorw);
    }

    /**
     * 得到上一天
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static String getLastDay(String date) throws Exception {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
        Date today = formate.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -1);
        Date tommorw = new Date();
        tommorw = calendar.getTime();
        return formate.format(tommorw);
    }

    public static String getBeforeDay(String date) throws Exception {
        SimpleDateFormat formate = new SimpleDateFormat("yyyyMMdd");
        Date today = formate.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -2);
        Date tommorw = new Date();
        tommorw = calendar.getTime();
        return formate.format(tommorw);
    }

    /**
     * 将时间拆分成年月日 和 时分秒
     *
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Map<String, String> getStringYMDHSM(String dateStr) {
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        try {
            date = formatter.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Exception: formatter date is false --DateUtils 505");
        }

        SimpleDateFormat ymdformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hmsformat = new SimpleDateFormat("HH:mm:ss");

        map.put("ymd", ymdformat.format(date));
        map.put("hms", hmsformat.format(date));

        return map;
    }

    public static String formatYYYYMMDD(String date) {
        if (date == null || date == "") {
            return "";
        }
        return date.replace("-", "");
    }

    public static String formatHHMMSS(String time) {
        if (time == null || time == "") {
            return "";
        }
        return time.replace(":", "");
    }

    /**
     * @return yyyy-MM-dd
     */
    public static String getDateYMDOther() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return s.format(new Date());
    }

    //
//	public static void main(String[] args) {
//		System.out.println(getDateYMDOther());
//	}
// 格式：年－月－日 小时：分钟：秒
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return dateToString(today.getTime(), FORMAT_ONE);
    }

    /**
     * 把日期转换为字符串
     */
    public static String dateToString(java.util.Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }
}
