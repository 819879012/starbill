package daointerface;

import java.util.Date;
import java.util.List;

import entity.CostPlan;

public interface CosrPlanInterface {
	public int getTotal();
	public void add(CostPlan record);
	public void update(CostPlan record);
	public void delete(int id);
	public CostPlan getById(int id);
	public List<CostPlan> list();
	public List<CostPlan> list(int start, int count);
	public List<CostPlan> list(int cid);
	public List<CostPlan> listToday();
	public List<CostPlan> list(Date day);
	public List<CostPlan> listThisMonth();
	public List<CostPlan> list(Date start, Date end);
}
