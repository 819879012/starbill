package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.CostRecordDAO;
import entity.CostRecord;

public class MonthCostTableService {
	
	CostRecordDAO costRecordDao = new CostRecordDAO();
	
	public List<CostRecord> list() {
		int uid = UsersService.getNowUid();
        List<CostRecord> cs = costRecordDao.list();
        List<CostRecord> returnList = new ArrayList<CostRecord>();
        for (CostRecord costRecord : cs ) {
			if( costRecord.getUid() == uid )
				returnList.add(costRecord);
		}
        Collections.sort(returnList,(c1,c2)->(int)(c2.getCost()-c1.getCost()));
        return returnList;
    }
	
}
