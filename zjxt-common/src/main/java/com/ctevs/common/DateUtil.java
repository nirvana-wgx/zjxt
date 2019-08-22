package com.ctevs.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

    /** 锁对象 */
    private static final Object obj = new Object();

    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> simpleDateFormats = 
            new ConcurrentHashMap<String, ThreadLocal<SimpleDateFormat>>();

    private static  DateFormat sdf;
    private static  final String default_patter = "yyyy-MM-dd";

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT2 = "yyyyMMdd";
    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_LONG2 = "yyyyMMddHHmmss";
    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写 如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
    /** 某天开始时分秒字符串常量 00:00:00 */
    public static final String DAY_BEGIN_STRING_HHMMSS = " 00:00:00";
    /** 某天结束时分秒字符串常量 23:59:59 */
    public static final String DAY_END_STRING_HHMMSS = " 23:59:59";

    private static  SimpleDateFormat getSimpleDateFormat(final String pattern) {
        if (StringUtils.isEmpty(pattern))
            return null;
        ThreadLocal<SimpleDateFormat> simpleDateFormat = simpleDateFormats.get(pattern);
        if (simpleDateFormat == null) {
            synchronized (obj) {
                simpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
                    @Override
                    protected SimpleDateFormat initialValue() {
                        return new SimpleDateFormat(pattern);
                    }
                };
                simpleDateFormats.put(pattern, simpleDateFormat);
            }
        }
        return simpleDateFormat.get();
    }

    private static  SimpleDateFormat getDefaultSimpleDateFormat() {
        final String pattern = default_patter;
        ThreadLocal<SimpleDateFormat> simpleDateFormat = simpleDateFormats.get(pattern);
        if (simpleDateFormat == null) {
            synchronized (obj) {
                simpleDateFormat = new ThreadLocal<SimpleDateFormat>() {

                    @Override
                    protected SimpleDateFormat initialValue() {
                        return new SimpleDateFormat(pattern);
                    }
                };
                simpleDateFormats.put(pattern, simpleDateFormat);
            }
        }
        return simpleDateFormat.get();
    }

    public static  Date parse(String date, String pattern) {
        sdf = getSimpleDateFormat(pattern);
        Date dates = null;
        try {
            dates = sdf.parse(date);
            return dates;
        } catch (ParseException e) {
            e.printStackTrace();
            return dates;
        }
    }

    public static  Date parse(String date) {
        sdf = getDefaultSimpleDateFormat();
        Date dates = null;
        try {
            dates = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return FORMAT_LONG;
    }

    /**
     * 根据预设格式返回当前日期
     * 
     * @return
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 根据用户格式返回当前日期
     * 
     * @param format
     * @return
     */
    public static String getNow(String format) {
        return format(new Date(), format);
    }

    /**
     * 使用预设格式格式化日期
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }

    /**
     * 使用用户格式格式化日期
     * 
     * @param date 日期
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        sdf = getSimpleDateFormat(pattern);
        String returnValue = "";
        if (date != null) {
            returnValue = sdf.format(date);
        }
        return (returnValue);
    }

    /**
     * 使用预设格式提取字符串日期
     * 
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse2(String strDate) {
        return parse(strDate, getDatePattern());
    }

    /**
     * 使用用户格式提取字符串日期
     * 
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse2(String strDate, String pattern) {
        sdf = getSimpleDateFormat(pattern);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在日期上增加数个整月
     * 
     * @param date 日期
     * @param n 要增加的月数
     * @return
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     * 
     * @param date 日期
     * @param n 要增加的天数
     * @return
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, n);
        return cal.getTime();
    }

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        sdf = getSimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     * 
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }

    /**
     * 按默认格式的字符串距离今天的天数
     * 
     * @param date 日期字符串
     * @return
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 按用户格式字符串距离今天的天数
     * 
     * @param date 日期字符串
     * @param format 日期格式
     * @return
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 
     * 计算日期相差时间天 <br/>
     * 
     * @author haibing.xiao
     * @date: 2017年9月13日 下午5:33:45
     * @version 1.0
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static int differentHours(Date date1, Date date2) {
        int hours = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600));
        return hours;
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        
        
        String dateStr = "2018-1-1 13:21:28";
        String dateStr2 = "2018-1-1 14:11:28";

        System.out.println(differentHours( parse(dateStr, DateUtil.FORMAT_LONG),  parse(dateStr2, DateUtil.FORMAT_LONG)));
        
        Thread t1 = new Thread() {
            @Override
            public void run() {
                parse(dateStr, DateUtil.FORMAT_SHORT2);
                System.out.println("thread: " + Thread.currentThread() + " init pattern: " + DateUtil.FORMAT_SHORT2);
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                parse(dateStr2, DateUtil.FORMAT_LONG);
                System.out.println("thread: " + Thread.currentThread() + " init pattern: " + DateUtil.FORMAT_LONG);
            }
        };
        
        ExecutorService exec = Executors.newFixedThreadPool(50);
        for(int i=1;i<=100;i++){
            if (i%2==0) {
                exec.execute(t2);
            }else{
                exec.execute(t1);
            }
            
        }
        exec.shutdown();

       
         

    }
}
