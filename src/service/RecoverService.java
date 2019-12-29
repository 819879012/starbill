package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ConfigDAO;
import dao.CostCategoryDAO;
import dao.CostPlanDAO;
import dao.CostRecordDAO;
import dao.IncomeCategoryDAO;
import dao.IncomeRecordDAO;
import entity.Config;
import entity.CostCategory;
import entity.CostPlan;
import entity.CostRecord;
import entity.IncomeCategory;
import entity.IncomeRecord;
import util.DBUtil;

public class RecoverService {
	
	private List<CostCategory> costCategoryList = new ArrayList<CostCategory>();
	private List<Config> configList = new ArrayList<Config>();
	private List<IncomeCategory> incomeCategoryList = new ArrayList<IncomeCategory>();
	private List<CostRecord> costRecordList = new ArrayList<CostRecord>();
	private List<IncomeRecord> incomeRecordList = new ArrayList<IncomeRecord>();
	private List<CostPlan> costPlanList = new ArrayList<CostPlan>();
	private static RecoverService instance = new RecoverService();
	
	public RecoverService() {
		// TODO Auto-generated constructor stub
	}

	public void recoverAllData()
	{
		recoverIncomeCategory();
		recoverCostCategory();
		recoverIncomeRecord();
		recoverCostRecord();
		recoverCostPlan();
		recoverConfig();
	}
	
	public void recoverCostCategory()
	{
		if( costCategoryList == null || costCategoryList.size() == 0)
			return;
		CostCategoryDAO dao = new CostCategoryDAO();
		costCategoryList.sort((h1, h2) -> h1.getId()-h2.getId());
		UsersService service = new UsersService();
		service.deleteAllNowUserCostCategory(UsersService.getNowUid());
		for (CostCategory costCategory : costCategoryList) {
			String sql = "alter table costCategory auto_increment =" + costCategory.getId();
			Connection c;
			try {
				c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.add(costCategory);
		}
		costCategoryList.clear();
	}
	
	public void recoverConfig()
	{
		ConfigDAO dao = new ConfigDAO();
		for (Config config : configList) {
			dao.add(config);
		}
		configList.clear();
	}

	public void recoverIncomeCategory()
	{
		if( incomeCategoryList == null || incomeCategoryList.size() == 0)
			return;
		IncomeCategoryDAO dao = new IncomeCategoryDAO();
		incomeCategoryList.sort((h1, h2) -> h1.getId()-h2.getId());
		UsersService service = new UsersService();
		service.deleteAllNowUserIncomeCategory(UsersService.getNowUid());
		for (IncomeCategory incomeCategory : incomeCategoryList) {
			String sql = "alter table incomeCategory auto_increment =" + incomeCategory.getId();
			Connection c;
			try {
				c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.add(incomeCategory);
		}
		incomeCategoryList.clear();
	}
	
	public void recoverCostRecord()
	{
		CostRecordDAO dao = new CostRecordDAO();
		for (CostRecord costRecord : costRecordList) {
			dao.addCostRecord(costRecord);
		}
		costRecordList.clear();
	}
	
	public void recoverIncomeRecord()
	{
		IncomeRecordDAO dao = new IncomeRecordDAO();
		for (IncomeRecord incomeRecord : incomeRecordList) {
			dao.addIncomeRecord(incomeRecord);
		}
		incomeRecordList.clear();
	}
	
	public void recoverCostPlan()
	{
		CostPlanDAO dao = new CostPlanDAO();
		for (CostPlan costPlan : costPlanList) {
			dao.add(costPlan);
		}
		costPlanList.clear();
	}
	
	public void addDeleteCostCategory(CostCategory c)
	{
		costCategoryList.add(c);
	}

	public void addDeleteConfig(Config c)
	{
		configList.add(c);
	}

	public void addDeleteIncomeCategory(IncomeCategory c)
	{
		incomeCategoryList.add(c);
	}

	public void addDeleteCostRecord(CostRecord c)
	{
		costRecordList.add(c);
	}

	public void addDeleteIncomeRecord(IncomeRecord c)
	{
		incomeRecordList.add(c);
	}

	public void addDeleteCostPlan(CostPlan c)
	{
		costPlanList.add(c);
	}

	public List<CostCategory> getCostCategoryList() {
		return costCategoryList;
	}

	public List<Config> getConfigList() {
		return configList;
	}

	public List<IncomeCategory> getIncomeCategoryList() {
		return incomeCategoryList;
	}

	public List<CostRecord> getCostRecordList() {
		return costRecordList;
	}

	public List<IncomeRecord> getIncomeRecordList() {
		return incomeRecordList;
	}

	public List<CostPlan> getCostPlanList() {
		return costPlanList;
	}

	public static RecoverService getInstance() {
		return instance;
	}

}
