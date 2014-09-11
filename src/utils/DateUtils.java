package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 文件名称: DateUtils.java
 * 编写人: yh.zeng
 * 编写时间: 13-12-2
 * 文件描述: 日期工具类
 */
public class DateUtils {
	
	 /**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
        return dayBefore;
    }

	 /**
     * 获得指定日期的前一天
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getSpecifiedDayBefore(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        return c.getTime();
    }
    
    /**
     * 获得指定日期的后一天
     * 
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }
    
    /**
     * 获得指定日期的后一天
     * 
     * @param date
     * @return
     */
    public static Date getSpecifiedDayAfter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        return c.getTime();
    }
    
    /**
     * 获取这个月的最后一天
     * @return
     */
    public static Date getLastDateOfThisMonth(){
    	Calendar calendar=Calendar.getInstance(); 
    	calendar.setTime(new Date());
    	int maxDate = calendar.getMaximum(Calendar.DATE) ;
    	calendar.set(Calendar.DATE, maxDate);
    	
    	return calendar.getTime();
    }
    
    /**
     * 获取这个月的最大天数
     * @return
     */
    public static int getMaxDateOfThisMonth(){
    	Calendar calendar=Calendar.getInstance(); 
    	calendar.setTime(new Date());
    	
    	return calendar.getMaximum(Calendar.DATE) ;
    }
    
}
