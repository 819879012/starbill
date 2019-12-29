package service;

import java.util.Date;
import dao.IncomeRecordDAO;
import entity.IncomeRecord;
import gui_panel.AddEarnPanel;

public class IncomeRecordService {
	
	private IncomeRecordDAO incomeRecordDAO = new IncomeRecordDAO();
	
	/*
	 * add an Income record
	 */
	public void addIncomeRecord(int cid,double income,String comment,Date date)
	{
		IncomeRecord record = new IncomeRecord();
		record.setUid(UsersService.getNowUid());
		record.setCid(cid);
		record.setIncome(income);
		record.setComment(comment);
		record.setDate(date);
		incomeRecordDAO.addIncomeRecord(record);
	}
	
	public void updateData( AddEarnPanel p )
	{
		p.updateData();
	}
	
}
