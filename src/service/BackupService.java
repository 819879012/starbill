package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

public class BackupService {
	
	private static int uid = UsersService.getNowUid();
	private static String backupFilePath = "backupDocuments/";
	private static String userDataFileName = "users" + uid +".xls";
	private static String configDataFileName = "config" + uid +".xls";
	private static String incomeCategoryDataFileName = "incomeCategory"+ uid+".xls";
	private static String costCategoryDataFileName = "costCategory" + uid+".xls";
	private static String incomeRecordFileName = "incomeRecord"+uid+".xls";
	private static String costRecordFileName = "costRecord"+uid+".xls";
	private static String costPlanFileName = "costPlan"+uid+".xls";
	private static HSSFWorkbook workbook = new HSSFWorkbook();
	
	public static void saveAllData() throws IOException
	{
		saveUsersData();
		saveConfigData();
		saveIncomeCategoryData();
		saveCostCategoryData();
		saveIncomeRecordData();
		saveCostRecordData();
		saveCostPlanData();
	}
	
	public static void saveUsersData() throws IOException
	{
		UsersDAO userDao = new UsersDAO();
		String[] fields = {"id","account","password"};
		String filePath = backupFilePath + userDataFileName;
		HSSFSheet sheet = workbook.createSheet("users" + uid);
		HSSFRow row = sheet.createRow(0);
		File file = new File(filePath);
		if( !file.exists() )
		{
			file.createNewFile();
		}
		OutputStream outputStream = new FileOutputStream(file);
		for( int i = 0; i < fields.length; i++ )
		{
			row.createCell(i).setCellValue(fields[i]);
		}
		row.setHeightInPoints(30); // 设置行的高度
		List<Users> userList = userDao.list();
		if( userList == null )
		{
			workbook.setActiveSheet(0);
			workbook.write(outputStream);
			outputStream.close();
			return;
		}
		int size = userList.size();
		for( int i = 0; i < size; i++ )
		{
			if( userList.get(i).getId() == uid )
			{
				HSSFRow row1 = sheet.createRow(i+1);
				String[] everyField = {
						"" + userList.get(i).getId(),
						""+userList.get(i).getAccount(),
						""+userList.get(i).getPassword()
				};
				for( int j = 0; j < everyField.length; j++ )
					row1.createCell(j).setCellValue(everyField[j]);
			}
		}
		workbook.setActiveSheet(0);
		workbook.write(outputStream);
		outputStream.close();
	}
	
	public static void saveConfigData() throws IOException
	{
		ConfigDAO configDao = new ConfigDAO();
		String[] fields = {"id","uid","key","value"};
		String filePath = backupFilePath + configDataFileName;
		HSSFSheet sheet = workbook.createSheet("config" + uid);
		HSSFRow row = sheet.createRow(0);
		File file = new File(filePath);
		if( !file.exists() )
		{
			file.createNewFile();
		}
		OutputStream outputStream = new FileOutputStream(file);
		for( int i = 0; i < fields.length; i++ )
		{
			row.createCell(i).setCellValue(fields[i]);
		}
		row.setHeightInPoints(30); // 设置行的高度
		List<Config> configList = configDao.list();
		if( configList == null )
		{
			workbook.setActiveSheet(1);
			workbook.write(outputStream);
			outputStream.close();
			return;
		}
		int size = configList.size();
		for( int i = 0; i < size; i++ )
		{
			if( configList.get(i).getUid() == uid )
			{
				HSSFRow row1 = sheet.createRow(i+1);
				String[] everyField = {
						"" + configList.get(i).getId(),
						""+configList.get(i).getUid(),
						""+configList.get(i).getKey(),
						""+configList.get(i).getValue()
				};
				for( int j = 0; j < everyField.length; j++ )
					row1.createCell(j).setCellValue(everyField[j]);
			}
		}
		workbook.setActiveSheet(1);
		workbook.write(outputStream);
		outputStream.close();
	}
	
	public static void saveIncomeCategoryData() throws IOException
	{
		IncomeCategoryDAO incomeCategoryDao = new IncomeCategoryDAO();
		String[] fields = {"id","uid","name"};
		String filePath = backupFilePath + incomeCategoryDataFileName;
		HSSFSheet sheet = workbook.createSheet("incomeCategory"+uid);
		HSSFRow row = sheet.createRow(0);
		File file = new File(filePath);
		if( !file.exists() )
		{
			file.createNewFile();
		}
		OutputStream outputStream = new FileOutputStream(file);
		for( int i = 0; i < fields.length; i++ )
		{
			row.createCell(i).setCellValue(fields[i]);
		}
		row.setHeightInPoints(30); // 设置行的高度
		List<IncomeCategory> incomeCategoryList = incomeCategoryDao.list();
		if( incomeCategoryList == null )
		{
			workbook.setActiveSheet(2);
			workbook.write(outputStream);
			outputStream.close();
			return;
		}
		int size = incomeCategoryList.size();
		for( int i = 0; i < size; i++ )
		{
			if( incomeCategoryList.get(i).getUid() == uid )
			{
				HSSFRow row1 = sheet.createRow(i+1);
				String[] everyField = {
						"" + incomeCategoryList.get(i).getId(),
						""+incomeCategoryList.get(i).getUid(),
						""+incomeCategoryList.get(i).getName()
				};
				for( int j = 0; j < everyField.length; j++ )
					row1.createCell(j).setCellValue(everyField[j]);
			}
		}
		workbook.setActiveSheet(2);
		workbook.write(outputStream);
		outputStream.close();
	}
	
	public static void saveCostCategoryData() throws IOException
	{
		CostCategoryDAO costCategoryDao = new CostCategoryDAO();
		String[] fields = {"id","uid","name"};
		String filePath = backupFilePath + costCategoryDataFileName;
		HSSFSheet sheet = workbook.createSheet("costCategory"+uid);
		HSSFRow row = sheet.createRow(0);
		File file = new File(filePath);
		if( !file.exists() )
		{
			file.createNewFile();
		}
		OutputStream outputStream = new FileOutputStream(file);
		for( int i = 0; i < fields.length; i++ )
		{
			row.createCell(i).setCellValue(fields[i]);
		}
		row.setHeightInPoints(30); // 设置行的高度
		List<CostCategory> costCategoryList = costCategoryDao.list();
		if( costCategoryList == null )
		{
			workbook.setActiveSheet(3);
			workbook.write(outputStream);
			outputStream.close();
			return;
		}
		int size = costCategoryList.size();
		for( int i = 0; i < size; i++ )
		{
			if( costCategoryList.get(i).getUid() == uid )
			{
				HSSFRow row1 = sheet.createRow(i+1);
				String[] everyField = {
						"" + costCategoryList.get(i).getId(),
						""+costCategoryList.get(i).getUid(),
						""+costCategoryList.get(i).getName()
				};
				for( int j = 0; j < everyField.length; j++ )
					row1.createCell(j).setCellValue(everyField[j]);
			}
		}
		workbook.setActiveSheet(3);
		workbook.write(outputStream);
		outputStream.close();
	}
	
	public static void saveIncomeRecordData() throws IOException
	{
		IncomeRecordDAO incomeRecordDao = new IncomeRecordDAO();
		String[] fields = {"id","uid","cid","income","comment","date"};
		String filePath = backupFilePath + incomeRecordFileName;
		HSSFSheet sheet = workbook.createSheet("incomeRecord" + uid);
		HSSFRow row = sheet.createRow(0);
		File file = new File(filePath);
		if( !file.exists() )
		{
			file.createNewFile();
		}
		OutputStream outputStream = new FileOutputStream(file);
		for( int i = 0; i < fields.length; i++ )
		{
			row.createCell(i).setCellValue(fields[i]);
		}
		row.setHeightInPoints(30); // 设置行的高度
		List<IncomeRecord> incomeRecordList = incomeRecordDao.list();
		if( incomeRecordList == null )
		{
			workbook.setActiveSheet(4);
			workbook.write(outputStream);
			outputStream.close();
			return;
		}
		int size = incomeRecordList.size();
		for( int i = 0; i < size; i++ )
		{
			if( incomeRecordList.get(i).getUid() == uid )
			{
				HSSFRow row1 = sheet.createRow(i+1);
				String[] everyField = {
						"" + incomeRecordList.get(i).getId(),
						""+incomeRecordList.get(i).getUid(),
						""+incomeRecordList.get(i).getCid(),
						""+incomeRecordList.get(i).getIncome(),
						""+incomeRecordList.get(i).getComment(),
						""+incomeRecordList.get(i).getDate()
				};
				for( int j = 0; j < everyField.length; j++ )
					row1.createCell(j).setCellValue(everyField[j]);
			}
		}
		workbook.setActiveSheet(4);
		workbook.write(outputStream);
		outputStream.close();
	}
	
	public static void saveCostRecordData() throws IOException
	{
		CostRecordDAO costRecordDao = new CostRecordDAO();
		String[] fields = {"id","uid","cid","income","comment","date"};
		String filePath = backupFilePath + costRecordFileName;
		HSSFSheet sheet = workbook.createSheet("costRecord"+uid);
		HSSFRow row = sheet.createRow(0);
		File file = new File(filePath);
		if( !file.exists() )
		{
			file.createNewFile();
		}
		OutputStream outputStream = new FileOutputStream(file);
		for( int i = 0; i < fields.length; i++ )
		{
			row.createCell(i).setCellValue(fields[i]);
		}
		row.setHeightInPoints(30); // 设置行的高度
		List<CostRecord> costRecordList = costRecordDao.list();
		if( costRecordList == null )
		{
			workbook.setActiveSheet(5);
			workbook.write(outputStream);
			outputStream.close();
			return;
		}
		int size = costRecordList.size();
		for( int i = 0; i < size; i++ )
		{
			if( costRecordList.get(i).getUid() == uid )
			{
				HSSFRow row1 = sheet.createRow(i+1);
				String[] everyField = {
						"" + costRecordList.get(i).getId(),
						""+costRecordList.get(i).getUid(),
						""+costRecordList.get(i).getCid(),
						""+costRecordList.get(i).getCost(),
						""+costRecordList.get(i).getComment(),
						""+costRecordList.get(i).getDate()
				};
				for( int j = 0; j < everyField.length; j++ )
					row1.createCell(j).setCellValue(everyField[j]);
			}
		}
		workbook.setActiveSheet(5);
		workbook.write(outputStream);
		outputStream.close();
	}
	
	public static void saveCostPlanData() throws IOException
	{
		CostPlanDAO costPlanDao = new CostPlanDAO();
		String[] fields = {"id","uid","cid","spend","comment","date"};
		String filePath = backupFilePath + costPlanFileName;
		HSSFSheet sheet = workbook.createSheet("costPlan"+uid);
		HSSFRow row = sheet.createRow(0);
		File file = new File(filePath);
		if( !file.exists() )
		{
			file.createNewFile();
		}
		OutputStream outputStream = new FileOutputStream(file);
		for( int i = 0; i < fields.length; i++ )
		{
			row.createCell(i).setCellValue(fields[i]);
		}
		row.setHeightInPoints(30); // 设置行的高度
		List<CostPlan> costPlanList = costPlanDao.list();
		if( costPlanList == null )
		{
			workbook.setActiveSheet(6);
			workbook.write(outputStream);
			outputStream.close();
			return;
		}
		int size = costPlanList.size();
		for( int i = 0; i < size; i++ )
		{
			if( costPlanList.get(i).getUid() == uid )
			{
				HSSFRow row1 = sheet.createRow(i+1);
				String[] everyField = {
						"" + costPlanList.get(i).getId(),
						""+costPlanList.get(i).getUid(),
						""+costPlanList.get(i).getCid(),
						""+costPlanList.get(i).getSpend(),
						""+costPlanList.get(i).getComment(),
						""+costPlanList.get(i).getDate()
				};
				for( int j = 0; j < everyField.length; j++ )
					row1.createCell(j).setCellValue(everyField[j]);
			}
		}
		workbook.setActiveSheet(6);
		workbook.write(outputStream);
		outputStream.close();
	}
	
}
