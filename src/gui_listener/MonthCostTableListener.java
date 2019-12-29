package gui_listener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.CostRecord;
import gui_panel.MonthCostTablePanel;
import service.MonthCostTableService;
import util.DateUtil;

public class MonthCostTableListener implements ActionListener{

	private static int month = 0;
	private static int year = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MonthCostTablePanel panel = MonthCostTablePanel.getInstance();
		MonthCostTableService service = new MonthCostTableService();
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
		if( b == panel.getInputMonth() ) {
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
		if( b == panel.getShowSearchTable() ) {
//			if( month == 0 )
			List<CostRecord> list = service.list();
			DateUtil dateUtil = new DateUtil();
			List<CostRecord> newList = new ArrayList<>();
			for(int i = 0;i<list.size();i++) {
				int dateYear = dateUtil.getYearOfDate(list.get(i).getDate());
				int dateMonth = dateUtil.getMonthOfDate(list.get(i).getDate());
				
				if(dateYear == year && dateMonth == month) {
					newList.add(list.get(i));
				}
			}
			if( newList.size()==0 ) {
				JOptionPane.showMessageDialog(panel, "输入年份和月份不存在消费记录，请重新输入");
				year = 0;
				month = 0;
				return ;
			}
			panel.getMonthCostTableModel().setCostRecordList(newList);
			panel.getShowInfoTable().updateUI();
		}

	}

}
