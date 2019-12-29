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
	 * ����ܵĴ���ͼ��
	 */
	public final static ImageIcon icon = new ImageIcon("img/mainFrameIcon.jpg");
	/*
	 * ��½������panel�ı���ͼƬ
	 */
	public final static String BACKGROUND = "allPanelImg.jpg";
	/*
	 * ip Ϊ�����Ե�ip
	 */
	public final static String ip = "127.0.0.1";
	
	/*
	 * port Ϊ�㰲װ��mysql ռ�õĶ˿� һ��Ĭ��Ϊ 3306
	 */
	public final static int port = 3306;
	
	/*
	 * startbillΪ���ݿ���
	 */
	public final static String database = "starbill";
	
	/*
	 * ���ݿ���뷽ʽΪUTF-8
	 */
	public final static String encoding = "UTF-8";
	
	/*
	 * loginName Ϊmysql��½��,һ��Ĭ��Ϊ root
	 */
	public final static String loginName = "root";
	
	/*
	 * password Ϊmysql��½����,һ��Ϊ�� ""
	 * Ҳ���������޸�
	 */
	public final static String password = "123456";
	
}
