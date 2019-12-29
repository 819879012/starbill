package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.CostRecordDAO;
import entity.CostRecord;

public class FindCostRecordService {
	CostRecordDAO costRecordDao = new CostRecordDAO();
	
	public List<CostRecord> list() {
	    List<CostRecord> cs = new ArrayList<CostRecord>();
	    List<CostRecord> newList = costRecordDao.list();
	    int id = UsersService.getNowUid();
	    for( int i = 0;i<newList.size();i++) {
	    	if(id == newList.get(i).getUid()) {
	    		cs.add(newList.get(i));
	    	}
	    }
	    Collections.sort(cs,(c1,c2)->(int)(c2.getCost()-c1.getCost()));
	    return cs;
	}
}
