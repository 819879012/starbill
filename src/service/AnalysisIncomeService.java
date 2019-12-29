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

public class AnalysisIncomeService {
	
	IncomeRecordDAO incomeRecordDao = new IncomeRecordDAO();

	public void refreshPanel() {
		
	}
	/**获得某年某月的记录****/
	public List<IncomeRecord> monthIncomeRecord( int year, int month ) throws ParseException{
		IncomeRecordDAO incomeDao = new IncomeRecordDAO();
		List<IncomeRecord> list = incomeDao.list();
		List<IncomeRecord> returnList = new ArrayList<>();
		int totalMonthDay = DateUtil.getDayOfMonthByYear(year, month);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String monthStart = ""+ year + "-" + month + "-01";
		Date monthInit = sdf.parse(monthStart);
		Calendar c = Calendar.getInstance();
		for( int i = 0; i< totalMonthDay; i++ ) {
			IncomeRecord r = new IncomeRecord();
			c.setTime(monthInit);
			c.add(Calendar.DATE, i);
			Date eachDayOfThisMonth = c.getTime();
			double daySpend = getDaySpend(eachDayOfThisMonth,list);
			r.setIncome(daySpend);
			returnList.add(r);
		}
		return returnList;
	}
	
	public double getDaySpend(Date date, List<IncomeRecord> monthRawDate) {
		double daySpend = 0;
		for(IncomeRecord record : monthRawDate) {
			if( record.getDate().equals(date) )
				daySpend += record.getIncome();
		}
		return daySpend;
	}
	
}
