package constant;

import javax.swing.ImageIcon;

public class TagXml {
	
	private final static int income = 1;
	private final static int cost = 2;
	public final static int addCostPanel = cost;
	public final static int addEarnPanel = income;
	public final static int incomeCategoryPanel = income;
	public final static int costCategoryPanel = cost;
	public final static int costDetailsPanel = cost;
	public final static int incomeDetailsPanel = income;
	public final static int statisticsEarnPanel = income;
	public final static int statisticsCostPanel = cost;
	public final static int findIncomeRecordPanel = income;
	public final static int findCostRecordPanel = cost;
	public final static int analysisCostPanel = cost;
	public final static int analysisIncomePanel = income;
	
	/*
	 * 主框架的窗口图案
	 */
	public final static ImageIcon icon = new ImageIcon("img/mainFrameIcon.jpg");
	/*
	 * 登陆后所有panel的背景图片
	 */
	public final static String BACKGROUND = "allPanelImg.jpg";
	/*
	 * ip 为本电脑的ip
	 */
	public final static String ip = "127.0.0.1";
	
	/*
	 * port 为你安装的mysql 占用的端口 一般默认为 3306
	 */
	public final static int port = 3306;
	
	/*
	 * startbill为数据库名
	 */
	public final static String database = "starbill";
	
	/*
	 * 数据库编码方式为UTF-8
	 */
	public final static String encoding = "UTF-8";
	
	/*
	 * loginName 为mysql登陆名,一般默认为 root
	 */
	public final static String loginName = "root";
	
	/*
	 * password 为mysql登陆密码,一般为空 ""
	 * 也可以自行修改
	 */
	public final static String password = "123456";
	
}
