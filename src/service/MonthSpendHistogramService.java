package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import dao.CostRecordDAO;
import entity.CostRecord;
import util.DateUtil;

public class MonthSpendHistogramService {
	
	public List<CostRecord> monthCostRecord( int year,int month ) throws ParseException
	{
		int uid = UsersService.getNowUid();
		CostRecordDAO costDao = new CostRecordDAO();
		List<CostRecord> list = costDao.list();
		List<CostRecord> recordList = new ArrayList<>();
		List<CostRecord> returnList = new ArrayList<>();
		for (CostRecord costRecord : list) {
			if( costRecord.getUid() == uid )
				recordList.add(costRecord);
		}
		int totalMonthDay = DateUtil.getDayOfMonthByYear(year,month);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String monthStart = "" + year + "-" + month + "-01";
		Date monthInit = sdf.parse(monthStart);
		Calendar c = Calendar.getInstance();
		for( int i = 0; i < totalMonthDay; i++ )
		{
			CostRecord r = new CostRecord();
            c.setTime(monthInit);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime();
            double daySpend = getDaySpend(eachDayOfThisMonth,recordList);
            r.setCost(daySpend);
            returnList.add(r);
		}
		return returnList;
	}
	
    /**
     * 获取本月的消费记录集合
     * @return
     */
    public List<CostRecord> listThisMonthRecords() {
    	CostRecordDAO dao= new CostRecordDAO();
        List<CostRecord> monthRawData= dao.listThisMonth();
        List<CostRecord> result= new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
        	CostRecord r = new CostRecord();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime();
            double daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.setCost(daySpend);
            result.add(r);
        }
        return result;
    }
    
    /**
     * 获取某一天的消费金额
     * @param d
     * @param monthRawData
     * @return
     */
    public double getDaySpend(Date date,List<CostRecord> monthRawData){
        double daySpend = 0;
        for ( CostRecord record : monthRawData ) {
            if( record.getDate().equals(date) )
                daySpend += record.getCost();
        }
        return daySpend;
    }
    
}
