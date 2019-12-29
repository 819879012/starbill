package service;

import java.util.Date;
import dao.CostRecordDAO;
import entity.CostRecord;
import gui_panel.AddCostPanel;

public class CostRecordService {
	
	private CostRecordDAO costRecordDAO = new CostRecordDAO();
	
	/*
	 * add an Cost record
	 */
	public void addCostRecord(int cid,double cost,String comment,Date date)
	{
		CostRecord record = new CostRecord();
		record.setUid(UsersService.getNowUid());
		record.setCid(cid);
		record.setCost(cost);
		record.setComment(comment);
		record.setDate(date);
		costRecordDAO.addCostRecord(record);
	}
	
	public void updateData( AddCostPanel p )
	{
		p.updateData();
	}
	
}
