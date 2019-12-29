package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import dao.IncomeRecordDAO;
import entity.IncomeRecord;
import util.DateUtil;

public class IncomeHistogramService {
	
	public List<IncomeRecord> monthIncomeRecord( int year,int month ) throws ParseException
	{
		int uid = UsersService.getNowUid();
		IncomeRecordDAO incomeDao = new IncomeRecordDAO();
		List<IncomeRecord> list = incomeDao.list();
		List<IncomeRecord> recordList = new ArrayList<>();
		List<IncomeRecord> returnList = new ArrayList<>();
		for (IncomeRecord incomeRecord : list ) {
			if( incomeRecord.getUid() == uid )
				recordList.add(incomeRecord);
		}
		int totalMonthDay = DateUtil.getDayOfMonthByYear(year,month);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String monthStart = "" + year + "-" + month + "-01";
		Date monthInit = sdf.parse(monthStart);
		Calendar c = Calendar.getInstance();
		for( int i = 0; i < totalMonthDay; i++ )
		{
			IncomeRecord r = new IncomeRecord();
            c.setTime(monthInit);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime();
            double dayIncome = getDaySpend(eachDayOfThisMonth,recordList);
            r.setIncome(dayIncome);
            r.setDate(eachDayOfThisMonth);
            returnList.add(r);
		}
		return returnList;
	}
	
    /**
     * 获取本月的消费记录集合
     * @return
     */
    public List<IncomeRecord> listThisMonthRecords() {
    	int uid = UsersService.getNowUid();
    	IncomeRecordDAO dao= new IncomeRecordDAO();
        List<IncomeRecord> monthRawData= dao.listThisMonth();
        List<IncomeRecord> result= new ArrayList<>();
        for (IncomeRecord incomeRecord : monthRawData) {
			if( incomeRecord.getUid() == uid )
				result.add(incomeRecord);
		}
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
        	IncomeRecord r = new IncomeRecord();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime();
            double dayIncome = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.setIncome(dayIncome);
            r.setUid(uid);
            result.add(r);
        }
        return result;
    }
    
    /**
     * 获取某一天的收入金额
     * @param date
     * @param monthRawData
     * @return
     */
    public double getDaySpend(Date date,List<IncomeRecord> monthRawData){
        double daySpend = 0;
        if( monthRawData == null || monthRawData.size() == 0 )
        	return 0;
        for ( IncomeRecord record : monthRawData ) {
            if( record.getDate().equals(date) )
                daySpend += record.getIncome();
        }
        return daySpend;
    }
    
}
