package util;
  
import java.util.Calendar;
import java.util.Date;
  
public class DateUtil {
    static long millisecondsOfOneDay = 1000*60*60*24;  
  
    public static java.sql.Date util2sql(java.util.Date d){
        return  new java.sql.Date(d.getTime());
    }
    
    /**
     * ��ȡ���죬���Ұ�ʱ���֣���ͺ��붼��0. ��Ϊͨ�����ڿؼ���ȡ�������ڣ�����û��ʱ����ͺ���ġ�
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
     * ��ȡ�³���ʹ��Calendar��ȡ���µ�һ�졣 ��ͳ������һ����Ϣ��ʱ�򣬲鿴���µ��������ݣ���ʵ���Ǵ����ݿ���ȥ�Ѵӱ��µ�һ�쵽���һ������ݲ�������ٽ��м�ͳ�ƣ�������Ҫһ����ȡ���µ�һ��ķ�����
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
     * ��ȡ��ĩ
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
     * ��ȡ����һ���ж�����
     * @return
     */
    public static int thisMonthTotalDay(){
          
        long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();
  
        return (int) ((lastDayMilliSeconds-firstDayMilliSeconds)/millisecondsOfOneDay) +1;
    }
    
    /**
     * ��ô��� month��������
     * @param month �·� (1-12)
     * @return month total days
     */
    public static int getTotalMonthDays(int month) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.DATE, 1);
      calendar.set(Calendar.MONTH, month - 1);
      return calendar.getActualMaximum(Calendar.DATE);
    }
    
    /**
     *��ȡ���»�ʣ������
     * @return
     */
    public static int thisMonthLeftDay(){
        long lastDayMilliSeconds = monthEnd().getTime();
        long toDayMilliSeconds = today().getTime();
        return (int) ((lastDayMilliSeconds-toDayMilliSeconds)/millisecondsOfOneDay);
    }  
      
	/**
	 * ��ȡĳ��ĳ���ж�����
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDayOfMonthByYear(int year,int month){
		Calendar c = Calendar.getInstance();
		c.set(year, month, 0); //��������Ϊint����
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
    /*
     * ��������е��·�
     */
    public int getMonthOfDate(Date date)
    {
		String dateString = String.valueOf(date);
		String[] monthOfDate = dateString.split("-");
		int recordMonth = Integer.parseInt(monthOfDate[1]);
		return recordMonth;
    }
    
    /*
     * ��������е����
     */
    public int getYearOfDate(Date date)
    {
		String dateString = String.valueOf(date);
		String[] yearOfDate = dateString.split("-");
		int recordYear = Integer.parseInt(yearOfDate[0]);
		return recordYear;
    }
    
    /*
     * ��������е���
     */
    public int getDayOfDate(Date date)
    {
		String dateString = String.valueOf(date);
		String[] dayOfDate = dateString.split("-");
		int recordYear = Integer.parseInt(dayOfDate[2]);
		return recordYear;
    }
    
}