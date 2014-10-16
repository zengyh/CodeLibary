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

    /**
     * 获取以date为最后一天的前n天的日期
     * 若date = 2014-4-6 11:33:40, n = 2,则返回[2014-4-5 00:00:00,2014-4-6 23:59:59]
     * @param date
     * @param n
     * @return
     */
    public static Date[] getBeforeDates( Date date, int n ) {

        if ( n <= 0 ) {
            return null;
        }

        Date[] dateArr = new Date[ n ];

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
            SimpleDateFormat dateFormat2 = new SimpleDateFormat( "yyyy-MM-dd[HH:mm:ss]" );

            String dateStr = dateFormat.format( date );
            Date _date = dateFormat.parse( dateStr );

            dateStr += "[23:59:59]";
            dateArr[ n - 1 ] = dateFormat2.parse( dateStr );

            for ( int i = 1; i < n; i++ ) {
                dateArr[ n - i - 1 ] = getSpecifiedDayBefore( _date );
                _date = dateArr[ n - i - 1 ];
            }

        } catch ( ParseException e ) {
            // e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return dateArr;

    }
    
}
