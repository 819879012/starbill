package service;

import java.util.Collections;
import java.util.List;

import dao.CostRecordDAO;
import entity.CostRecord;

public class StatisticsCostService {
	CostRecordDAO costRecordDao = new CostRecordDAO();
	
	public List<CostRecord> list() {
        List<CostRecord> cs = costRecordDao.list();
        Collections.sort(cs,(c1,c2)->(int)(c2.getCost()-c1.getCost()));
        return cs;
    }
}
