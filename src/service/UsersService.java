package service;

import java.util.List;

import dao.ConfigDAO;
import dao.CostCategoryDAO;
import dao.CostPlanDAO;
import dao.CostRecordDAO;
import dao.IncomeCategoryDAO;
import dao.IncomeRecordDAO;
import dao.UsersDAO;
import entity.Config;
import entity.CostCategory;
import entity.CostPlan;
import entity.CostRecord;
import entity.IncomeCategory;
import entity.IncomeRecord;
import entity.Users;

public class UsersService {
	
	private UsersDAO userDao = new UsersDAO();
	private static int nowUid;
	private static int nowAccount;
	
	/*
	 * 获得某个用户的账号
	 */
	public Users getUserByAccount(int account)
	{
		return userDao.getByAccount(account);
	}
	
	/*
	 * 创建新用户(注册)
	 */
	public void createNewUser(int account,String password)
	{
		if( userDao.getByAccount(account) == null )
		{
			Users user = new Users(account,password);
			userDao.add(user);
			nowUid = user.getId();
		}
	}
	
	/*
	 * 修改用户,修改密码(用户管理)
	 */
	public void updateUser(int account,String newPassword)
	{
        Users user = userDao.getByAccount(account);
        user.setPassword(newPassword);
        userDao.update(user);
	}
	
	/*
	 * 查询某个用户是否存在
	 */
	public boolean findUser(int account)
	{
		if( getUserByAccount(account) != null )
			return true;
		return false;
	}
	
	/*
	 * 检验用户输入账号密码是否正确
	 */
	public boolean checkUserExist( int account,String password )
	{
		Users user = getUserByAccount(account);
		if(  user == null )
			return false;
		if( user!= null && user.getPassword().equals(password) )
		{
			nowUid = user.getId();
			nowAccount = user.getAccount();
			return true;
		}
		return false;
	}
	
	/*
	 * 获得当前用户的uid
	 */
	public static int getNowUid()
	{
		return nowUid;
	}
	
	/*
	 * 获得当前用户的账号
	 */
	public int getNowAccount()
	{
		return nowAccount;
	}
	
	/*
	 * 删除用户所有的信息
	 */
	public void deleteAllUserInfomation()
	{
		userDao.delete(nowUid);
		deleteAllNowUserConfigRecord(nowUid);
		deleteAllNowUserCostRecord(nowUid);
		deleteAllNowUserCostPlan(nowUid);
		deleteAllNowUserCostCategory(nowUid);
		deleteAllNowUserIncomeRecord(nowUid);
		deleteAllNowUserIncomeCategory(nowUid);
	}
	
	public void deleteAllNowUserConfigRecord(int nowUid)
	{
		ConfigDAO configDao= new ConfigDAO();
		List<Config> userConfigRecordList = configDao.list();
		if( userConfigRecordList == null || userConfigRecordList.size() == 0 )
			return;
		int size = userConfigRecordList.size();
		int start = userConfigRecordList.get(0).getId();
		for( int i = start,cnt = 0; cnt < size; i--,cnt++ )
		{
			if( i >= 0 && configDao.getById(i) != null )
			{
				if( configDao.getById(i).getUid() == nowUid )
				{
					configDao.delete(i);
				}
			}
		}
		
		if( userConfigRecordList.get(0) != null )
		{
			int leftOne = userConfigRecordList.get(0).getId() - 1;
			System.out.println(leftOne);
			configDao.delete(leftOne);
		}
		
	}
	
	/*
	 * delete all the cost category
	 */
	public void deleteAllNowUserCostCategory(int nowUid)
	{
		CostCategoryDAO costCategoryDAO= new CostCategoryDAO();
		List<CostCategory> userCostCategoryList = costCategoryDAO.list();
		if( userCostCategoryList == null || userCostCategoryList.size() == 0 )
			return;
		int size = userCostCategoryList.size();
		int start = userCostCategoryList.get(0).getId();
		for( int i = start,cnt = 0; cnt < size; i--,cnt++ )
		{
			if( i >= 0 && costCategoryDAO.getById(i) != null )
			{
				if( costCategoryDAO.getById(i).getUid() == nowUid )
				{
					System.out.println("delete");
					costCategoryDAO.delete(i);
				}
			}
		}
	}
	
	/*
	 * delete all the income category
	 */
	public void deleteAllNowUserIncomeCategory(int nowUid)
	{
		IncomeCategoryDAO incomeCategoryDAO= new IncomeCategoryDAO();
		List<IncomeCategory> incomeCategoryList = incomeCategoryDAO.list();
		if( incomeCategoryList == null || incomeCategoryList.size() == 0 )
			return;
		int size = incomeCategoryList.size();
		int start = incomeCategoryList.get(0).getId();
		for( int i = start,cnt = 0; cnt < size; i--,cnt++ )
		{
			if( i >= 0 && incomeCategoryDAO.getById(i) != null )
			{
				if( incomeCategoryDAO.getById(i).getUid() == nowUid )
				{
					incomeCategoryDAO.delete(i);
				}
			}
		}
	}
	
	/*
	 * delete all the cost record
	 */
	public void deleteAllNowUserCostRecord(int nowUid)
	{
		CostRecordDAO costRecordDAO= new CostRecordDAO();
		List<CostRecord> costRecordList = costRecordDAO.list();
		if( costRecordList == null || costRecordList.size() == 0 )
			return;
		int size = costRecordList.size();
		int start = costRecordList.get(0).getId();
		for( int i = start,cnt = 0; cnt < size; i++,cnt++ )
		{
			if( costRecordDAO.getById(i) != null )
			{
				if( costRecordDAO.getById(i).getUid() == nowUid )
				{
					costRecordDAO.delete(i);
				}
			}
		}
	}
	
	/*
	 * delete all the income record
	 */
	public void deleteAllNowUserIncomeRecord(int nowUid)
	{
		IncomeRecordDAO incomeRecordDAO= new IncomeRecordDAO();
		List<IncomeRecord> incomeRecordList = incomeRecordDAO.list();
		if( incomeRecordList == null || incomeRecordList.size() == 0 )
			return;
		int size = incomeRecordList.size();
		int start = incomeRecordList.get(0).getId();	
		for( int i = start,cnt = 0; cnt < size; i++,cnt++ )
		{
			if( incomeRecordDAO.getById(i)!= null )
			{
				if( incomeRecordDAO.getById(i).getUid() == nowUid )
				{
					incomeRecordDAO.delete(i);
				}
			}
		}
	}
	
	/*
	 * delete all the cost plan
	 */
	public void deleteAllNowUserCostPlan(int nowUid)
	{
		CostPlanDAO costPlanDao= new CostPlanDAO();
		List<CostPlan> costPlanList = costPlanDao.list();
		if( costPlanList == null || costPlanList.size() == 0 )
			return;
		int size = costPlanList.size();
		int start = costPlanList.get(0).getId();		
		for( int i = start,cnt = 0; cnt < size; i++,cnt++ )
		{
			if( costPlanDao.getById(i) != null )
			{
				if( costPlanDao.getById(i).getUid() == nowUid )
				{
					costPlanDao.delete(i);
				}
			}
		}
	}
	
}
