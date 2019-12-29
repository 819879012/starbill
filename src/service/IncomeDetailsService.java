package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import dao.IncomeRecordDAO;
import entity.IncomeRecord;

public class IncomeDetailsService {
	IncomeRecordDAO incomeRecordDao = new IncomeRecordDAO();

	public List<IncomeRecord> list() {
	    List<IncomeRecord> cs = new ArrayList<IncomeRecord>();
	    List<IncomeRecord> newList = incomeRecordDao.list();
	    int id = UsersService.getNowUid();
	    for( int i = 0;i<newList.size();i++) {
	    	if(id == newList.get(i).getUid()) {
	    		cs.add(newList.get(i));
	    	}
	    }
	    Collections.sort(cs,(c1,c2)->(int)(c2.getIncome()-c1.getIncome()));
	    return cs;
	}
	public void updateIncomeRecord(int id,Date date,int cid,double income,String comment) {
		IncomeRecord incomeRecord = new IncomeRecord();
		incomeRecord.setId(id);
		incomeRecord.setCid(cid);
		incomeRecord.setIncome(income);
		incomeRecord.setComment(comment);
		incomeRecord.setDate(date);
        incomeRecordDao.update(incomeRecord);
    }

    public void deleteIncomeRecordById(int id) {
    	incomeRecordDao.delete(id);
    }
}
