package gui_listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import entity.CostCategory;
import entity.CostRecord;
import gui_frame.MainFrame;
import gui_panel.AnalysisPanel;
import gui_panel.MainPanel;
import gui_panel.StatisticsCostPanel;
import service.CostCategoryService;
import service.StatisticsCostService;
import service.UsersService;
import util.DateUtil;

public class StatisticsCostListener implements ActionListener{
	private static int month = 0;
	private static int year = 0;
	private static int cid = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		StatisticsCostPanel panel = StatisticsCostPanel.getInstance();
		StatisticsCostService service = new StatisticsCostService();
		CostCategoryService categoryService = new CostCategoryService();
		JButton b = (JButton)e.getSource();
		
		if( b == panel.getChooseYear() ) {
			String input = JOptionPane.showInputDialog(null,"请输入想要查询年份");
			Pattern pattern = Pattern.compile("[0-9]*");//用来判断字符串中是否存在除字符串以外的字符
			if(input == null) //用户选择取消
				return ;
			if(input.length() == 0) {
				JOptionPane.showMessageDialog(panel, "年份不能为空");
			}
			else if(pattern.matcher(input).matches()) { //判断用户输入的年份是否为纯数字
				year = Integer.parseInt(input);
			}
			else {
				JOptionPane.showMessageDialog(panel, "年份不能存在除数字外的其他字符");
			}
		}
		if( b == panel.getChooseMonth() ) {
			String[] months = {"1月","2月","3月","4月","5月","6月",
					"7月","8月","9月","10月","11月","12月"};
			String singleMonth = (String) JOptionPane.showInputDialog(null, "请选择月份","月份选择",
					JOptionPane.QUESTION_MESSAGE,null,months,months[0]);//从service中得到用户选择的月份字符串
			if(singleMonth == null )
				return ;
			Pattern p = Pattern.compile("[^0-9]");
			Matcher m = p.matcher(singleMonth);
			String monthStr = m.replaceAll("").trim();//monthstr是月份对应纯数字字符串
			month = Integer.parseInt(monthStr);//将数字字符串转换成int型
		}
		if( b == panel.getChooseCategory() ) {
			if(categoryService.list().size() == 0) {
				JOptionPane.showMessageDialog( null, "用户还未添加收入分类!" );
				return;
			}
			List<CostCategory> costCategoryList = categoryService.list();//获得costcategory的list
			String[] costCategoryStr = new String[costCategoryList.size()] ;//用来存放list中的分类名
			for( int i = 0;i<costCategoryList.size();i++) {
				costCategoryStr[i] = costCategoryList.get(i).getName();
			}
			String chooseCategory = (String) JOptionPane.showInputDialog(null, "请选择类型","类型选择",
					JOptionPane.QUESTION_MESSAGE,null,costCategoryStr,costCategoryStr[0]);//跳出弹框让用户选择
			if(chooseCategory == null)//用户选择取消
				return ;
			for( int i = 0;i<costCategoryList.size();i++) {
				if(costCategoryList.get(i).getName().equals(chooseCategory)) {//找到与用户选择相同的分类
					cid = costCategoryList.get(i).getId();//替换原来的id
				}
			}
		}
		if( b == panel.getRefreshButton() ) {
			List<CostRecord> list = service.list();
			DateUtil dateUtil = new DateUtil();
			List<CostRecord> newList = new ArrayList<CostRecord>();
			int id = UsersService.getNowUid();
			double cost= 0;
			if(panel.getStatisticsCostTableModel().getCostRecordList().size() == 0 ) {
				JOptionPane.showMessageDialog( null, "用户还未添加收入记录!" );
				return;
			}
			if( year == 0 ) {
				JOptionPane.showMessageDialog( null, "用户还未添加年份!" );
				return;
			}
			if( month == 0 ) {
				JOptionPane.showMessageDialog( null, "用户还未添加月份!" );
				return;
			}
			if( cid == 0 ) {
				JOptionPane.showMessageDialog( null, "用户还未选择分类!" );
				return;
			}
			
			for(int i = 0;i<list.size();i++) {
				int dateYear = dateUtil.getYearOfDate(list.get(i).getDate());
				int dateMonth = dateUtil.getMonthOfDate(list.get(i).getDate());
				
				if(dateYear == year && dateMonth == month
						&&list.get(i).getUid() == id&& list.get(i).getCid() == cid) {
					cost += list.get(i).getCost();
				}
			}
			newList.add(new CostRecord());
			newList.get(0).setCid(cid);
			newList.get(0).setUid(id);
			newList.get(0).setCost(cost);
			if( newList.size()==0 ) {
				JOptionPane.showMessageDialog(panel, "输入的年份、月份、分类下不存在收入记录，请重新输入");
				year = 0;
				month = 0;
				cid = 0;
				return ;
			}
			panel.getStatisticsCostTableModel().setCostRecordList(newList);
			panel.getShowInfoTable().updateUI();
		}
		if( b == panel.getReturnButton() ) {
			AnalysisPanel analysisPanel = AnalysisPanel.getInstance();
			MainPanel mainPanel = MainPanel.getInstance();
			MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(analysisPanel);
        	analysisPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
		}
	}
}
