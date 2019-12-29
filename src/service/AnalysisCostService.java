/**
 * 
 */
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

public class AnalysisCostService {

	CostRecordDAO costRecordDao = new CostRecordDAO();

	public void refreshPanel() {
		
	}
	/**获得某年某月的记录****/
	public List<CostRecord> monthCostRecord( int year, int month ) throws ParseException{
		CostRecordDAO incomeDao = new CostRecordDAO();
		List<CostRecord> list = incomeDao.list();
		List<CostRecord> returnList = new ArrayList<>();
		int totalMonthDay = DateUtil.getDayOfMonthByYear(year, month);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String monthStart = ""+ year + "-" + month + "-01";
		Date monthInit = sdf.parse(monthStart);
		Calendar c = Calendar.getInstance();
		for( int i = 0; i< totalMonthDay; i++ ) {
			CostRecord r = new CostRecord();
			c.setTime(monthInit);
			c.add(Calendar.DATE, i);
			Date eachDayOfThisMonth = c.getTime();
			double daySpend = getDaySpend(eachDayOfThisMonth,list);
			r.setCost(daySpend);
			returnList.add(r);
		}
		return returnList;
	}
	
	public double getDaySpend(Date date, List<CostRecord> monthRawDate) {
		double daySpend = 0;
		for(CostRecord record : monthRawDate) {
			if( record.getDate().equals(date) )
				daySpend += record.getCost();
		}
		return daySpend;
	}
}
