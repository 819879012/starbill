package util;
  
import java.util.Calendar;
import java.util.Date;
  
public class DateUtil {
    static long millisecondsOfOneDay = 1000*60*60*24;  
  
    public static java.sql.Date util2sql(java.util.Date d){
        return  new java.sql.Date(d.getTime());
    }
    
    /**
     * 获取今天，并且把时，分，秒和毫秒都置0. 因为通过日期控件获取到的日期，就是没有时分秒和毫秒的。
     * @return
     */
    public static Date today(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }
  
    /**
     * 获取月初。使用Calendar获取本月第一天。 在统计消费一览信息的时候，查看本月的消费数据，其实就是从数据库中去把从本月第一天到最后一天的数据查出来，再进行简单统计，所以需要一个获取本月第一天的方法。
     * @return
     */
  
    public static Date monthBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE, 1);
          
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        return c.getTime();
    }
    
    /**
     * 获取月末
     * @return
     */
    public static Date monthEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
          
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }
  
    /**
     * 获取本月一共有多少天
     * @return
     */
    public static int thisMonthTotalDay(){
          
        long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();
  
        return (int) ((lastDayMilliSeconds-firstDayMilliSeconds)/millisecondsOfOneDay) +1;
    }
    
    /**
     * 获得传入 month的总天数
     * @param month 月份 (1-12)
     * @return month total days
     */
    public static int getTotalMonthDays(int month) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.DATE, 1);
      calendar.set(Calendar.MONTH, month - 1);
      return calendar.getActualMaximum(Calendar.DATE);
    }
    
    /**
     *获取本月还剩多少天
     * @return
     */
    public static int thisMonthLeftDay(){
        long lastDayMilliSeconds = monthEnd().getTime();
        long toDayMilliSeconds = today().getTime();
        return (int) ((lastDayMilliSeconds-toDayMilliSeconds)/millisecondsOfOneDay);
    }  
      
	/**
	 * 获取某年某月有多少天
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDayOfMonthByYear(int year,int month){
		Calendar c = Calendar.getInstance();
		c.set(year, month, 0); //输入类型为int类型
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
    /*
     * 获得日期中的月份
     */
    public int getMonthOfDate(Date date)
    {
		String dateString = String.valueOf(date);
		String[] monthOfDate = dateString.split("-");
		int recordMonth = Integer.parseInt(monthOfDate[1]);
		return recordMonth;
    }
    
    /*
     * 获得日期中的年份
     */
    public int getYearOfDate(Date date)
    {
		String dateString = String.valueOf(date);
		String[] yearOfDate = dateString.split("-");
		int recordYear = Integer.parseInt(yearOfDate[0]);
		return recordYear;
    }
    
    /*
     * 获得日期中的天
     */
    public int getDayOfDate(Date date)
    {
		String dateString = String.valueOf(date);
		String[] dayOfDate = dateString.split("-");
		int recordYear = Integer.parseInt(dayOfDate[2]);
		return recordYear;
    }
    
}