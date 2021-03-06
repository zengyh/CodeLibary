package utils;

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
     * 获取某日期所在季度的开始和结束日期
     * @param date  日期
     * @return value格式为yyyyMMddHHmmss ，所有的key如下：
     *         key=desc  则表示季度名称，值范围 （一季度、二季度、三季度、四季度）
     *         key=date1 则表示所在季度开始日期
     *         key=date2 则表示一季度结束日期
     */
    public static Map<String,String> getTheQuarterDateMap(Date  date){
        String desc = "";   //季度名称，值范围 （一季度、二季度、三季度、四季度）
        String date1 = "";  //所在季度开始日期  格式为yyyyMMddHHmmss
        String date2 = "";  //所在季度结束日期  格式为yyyyMMddHHmmss

        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int monthIndex = calendar.get(Calendar.MONTH);

        if(monthIndex >= 0 && monthIndex <=2) {   //一季度
            desc = "一季度";
            //一季度开始日期
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            date1 = timeFormat.format(calendar.getTime());
            //一季度结束日期
            calendar.set(Calendar.MONTH, 2);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            date2 = timeFormat.format(calendar.getTime());

        }else if(monthIndex >=3 && monthIndex <= 5){ //二季度
            desc = "二季度";
            //二季度开始日期
            calendar.set(Calendar.MONTH, 3);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            date1 = timeFormat.format(calendar.getTime());
            //二季度结束日期
            calendar.set(Calendar.MONTH, 5);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            date2 = timeFormat.format(calendar.getTime());

        }else if(monthIndex >= 6 && monthIndex <=8) {   //三季度
            desc = "三季度";
            //三季度开始日期
            calendar.set(Calendar.MONTH, 6);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            date1 = timeFormat.format(calendar.getTime());
            //三季度结束日期
            calendar.set(Calendar.MONTH, 8);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            date2 = timeFormat.format(calendar.getTime());

        }else if(monthIndex >= 9 && monthIndex <= 11){ //四季度
            desc = "四季度";
            //四季度开始日期
            calendar.set(Calendar.MONTH, 9);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            date1 = timeFormat.format(calendar.getTime());
            //四季度结束日期
            calendar.set(Calendar.MONTH, 11);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            date2 = timeFormat.format(calendar.getTime());
        }


        Map<String,String> returnMap = new HashMap<String,String>();
        returnMap.put("desc", desc);
        returnMap.put("date1", date1);
        returnMap.put("date2", date2);

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


    /**
     * 获取一年中12个月的开始和结束日期
     * @param year 年份，格式yyyy
     * @return   value格式为yyyyMMddHHmmss ，所有的key如下：
     *     key=date1_1 则表示一月份的开始日期
     *     key=date1_2 则表示一月份的结束日期
     *     key=date2_1 则表示二月份的开始日期
     *     key=date2_2 则表示二月份的结束日期
     *     key=date3_1 则表示三月份的开始日期
     *     key=date3_2 则表示三月份的结束日期
     *     key=date4_1 则表示四月份的开始日期
     *     key=date4_2 则表示四月份的结束日期
     *     key=date5_1 则表示五月份的开始日期
     *     key=date5_2 则表示五月份的结束日期
     *     key=date6_1 则表示六月份的开始日期
     *     key=date6_2 则表示六月份的结束日期
     *     key=date7_1 则表示七月份的开始日期
     *     key=date7_2 则表示七月份的结束日期
     *     key=date8_1 则表示八月份的开始日期
     *     key=date8_2 则表示八月份的结束日期
     *     key=date9_1 则表示九月份的开始日期
     *     key=date9_2 则表示九月份的结束日期
     *     key=date10_1 则表示十月份的开始日期
     *     key=date10_2 则表示十月份的结束日期
     *     key=date11_1 则表示十一月份的开始日期
     *     key=date11_2 则表示十一月份的结束日期
     *     key=date12_1 则表示十二月份的开始日期
     *     key=date12_2 则表示十二月份的结束日期
     */
    public static Map<String,String> getEveryMonthFirstEndDateOfYear(int year){
        Map<String,String> returnMap = new HashMap<String,String>();

        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        for(int i=1; i<=12; i++){
            Date[] dates = getFirstEndDateOfMonth(year, i);
            returnMap.put("date"+i+"_1", timeFormat.format(dates[0]));
            returnMap.put("date"+i+"_2", timeFormat.format(dates[1]));
        }

        return returnMap;
    }

    /**
     * 获取某年份的某个月份的开始和结束日期
     * @param year  年份，格式 yyyyMMdd
     * @param n     月份，1到12的数字
     * @return
     */
    public static Date[] getFirstEndDateOfMonth(int year, int n) {
        n = n-1;
        if (n < 0) {
            return null;
        }
        Date dates[] = new Date[2];
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);

        //获取某月份的开始日期
        calendar.set(Calendar.MONTH, n);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        dates[0] = calendar.getTime();
        //获取某月份的结束日期
        calendar.set(Calendar.MONTH, n);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        dates[1] = calendar.getTime();

        return dates;
    }

    /**
     * 获取某个日期的所在月份的最后一天日期
     * @return
     */
    public static Date getLastDateOfThisMonth(Date date){
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(date);
        int maxDate =  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
    	return  calendar.getTime();
    }




}
