package utils;

import freemarker.template.SimpleDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 获取一年中的四季开始和结束日期
     * @param n  若n=0则表示获取今年的，若n=1则获取明年，若n=-1则获取去年的
     * @return value格式为yyyyMMddHHmmss ，所有的key如下：
     *         key=date1_1 则表示一季度开始日期
     *         key=date1_2 则表示一季度结束日期
     *         key=date2_1 则表示二季度开始日期
     *         key=date2_2 则表示二季度结束日期
     *         key=date3_1 则表示三季度开始日期
     *         key=date3_2 则表示三季度结束日期
     *         key=date4_1 则表示四季度开始日期
     *         key=date4_2 则表示四季度结束日期
     */
    public static Map<String,String> getFourQuarterDateMap(int n){
        String date1_1 = "";  //一季度开始日期  格式为yyyyMMddHHmmss
        String date1_2 = "";  //一季度结束日期  格式为yyyyMMddHHmmss
        String date2_1 = "";  //二季度结束日期  格式为yyyyMMddHHmmss
        String date2_2 = "";  //二季度结束日期  格式为yyyyMMddHHmmss
        String date3_1 = "";  //三季度开始日期  格式为yyyyMMddHHmmss
        String date3_2 = "";  //三季度结束日期  格式为yyyyMMddHHmmss
        String date4_1 = "";  //四季度开始日期  格式为yyyyMMddHHmmss
        String date4_2 = "";  //四季度结束日期  格式为yyyyMMddHHmmss
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, n);
        //一季度开始日期
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        date1_1 = timeFormat.format(calendar.getTime());
        //一季度结束日期
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        date1_2 = timeFormat.format(calendar.getTime());
        //二季度开始日期
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        date2_1 = timeFormat.format(calendar.getTime());
        //二季度结束日期
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        date2_2 = timeFormat.format(calendar.getTime());
        //三季度开始日期
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        date3_1 = timeFormat.format(calendar.getTime());
        //三季度结束日期
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        date3_2 = timeFormat.format(calendar.getTime());
        //四季度开始日期
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        date4_1 = timeFormat.format(calendar.getTime());
        //四季度结束日期
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        date4_2 = timeFormat.format(calendar.getTime());

        Map<String,String> returnMap = new HashMap<String,String>();
        returnMap.put("date1_1", date1_1);
        returnMap.put("date1_2", date1_2);
        returnMap.put("date2_1", date2_1);
        returnMap.put("date2_2", date2_2);
        returnMap.put("date3_1", date3_1);
        returnMap.put("date3_2", date3_2);
        returnMap.put("date4_1", date4_1);
        returnMap.put("date4_2", date4_2);

        return returnMap;
    }

    /**
     * 获取两个日期之间相差的天数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long getDatesBetween(Date beginDate,Date endDate){
        long margin = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            beginDate = dateFormat.parse(dateFormat.format(beginDate));
            endDate = dateFormat.parse(dateFormat.format(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        margin = endDate.getTime() - beginDate.getTime();

        margin = margin/(1000*60*60*24);

        return margin;
    }


    /**
     * 获取两个日期之间的所有日期
     * @param beginDate          开始日期
     * @param endDate            结束日期
     * @param includeBeginDate   返回结果是否包含开始日期
     * @param includeEndDate     返回结果是否包含结束日期
     * @return
     */
    public static Date[] getDatesBetween(Date beginDate, Date endDate, boolean includeBeginDate, boolean includeEndDate){
        Long length = Math.abs(getDatesBetween(beginDate, endDate)+1);

        if(!includeBeginDate){
           length--;
        }

        if(!includeEndDate){
            length--;
        }

        Date[] dates = new Date[length.intValue()];
        Date tempDate = beginDate;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);

        if(beginDate.after(endDate)){
            beginDate = endDate;
            endDate = tempDate;
        }

        if(includeBeginDate){
            dates[0] = beginDate;
        }

        int i = includeBeginDate ? 1 : 0;
        while ((tempDate = getSpecifiedDayAfter(beginDate)).before(endDate)) {
            dates[i] = tempDate;
            beginDate = tempDate;
            i++;
        }

        if(includeEndDate){
            dates[length.intValue()-1] = endDate;
        }


        return dates;
    }


}
