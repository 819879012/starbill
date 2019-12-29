package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import dao.CostPlanDAO;
import dao.CostRecordDAO;
import entity.CostPlan;
 
public class CostPlanService {

    CostPlanDAO costPlanDao = new CostPlanDAO();
    CostRecordDAO costRecordDao = new CostRecordDAO();

    public List<CostPlan> list() {
    	int uid = UsersService.getNowUid();
        List<CostPlan> cs = costPlanDao.list();
        List<CostPlan> returnList = new ArrayList<CostPlan>();
        for (CostPlan costPlan : cs) {
			if( costPlan.getUid() == uid )
				returnList.add(costPlan);
		}
        Collections.sort(returnList,(c1,c2)->(int)(c2.getSpend()-c1.getSpend()));
        return returnList;
    }

    public void addCostPlan(Date date,int cid,double spend,String comment) {
        CostPlan costPlan = new CostPlan();
        costPlan.setUid(UsersService.getNowUid());
        costPlan.setCid(cid);
        costPlan.setSpend(spend);
        costPlan.setComment(comment);
        costPlan.setDate(date);
        costPlanDao.add(costPlan);
    }

    public void updateCostPlan(int id,Date date,int cid,double spend,String comment) {
        CostPlan costPlan = new CostPlan();
        costPlan.setId(id);
        costPlan.setUid(UsersService.getNowUid());
        costPlan.setCid(cid);
        costPlan.setSpend(spend);
        costPlan.setComment(comment);
        costPlan.setDate(date);
        costPlanDao.update(costPlan);
    }

    public void deleteCostPlanById(int id) {
    	costPlanDao.delete(id);
    }

}