package gui_listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jfree.chart.JFreeChart;
import entity.IncomeRecord;
import gui_frame.MainFrame;
import gui_panel.AnalysisIncomePanel;
import gui_panel.AnalysisPanel;
import gui_panel.MainPanel;
import service.AnalysisIncomeService;
import util.JFreeChartCreateUtil;

public class AnalysisIncomeListener implements ActionListener {


	private static int year = 0;
	private static int month = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
		AnalysisIncomePanel analysisIncomePanel = AnalysisIncomePanel.getInstance();
		AnalysisIncomeService analysisIncomeService = new AnalysisIncomeService();
		JButton b = (JButton) e.getSource();
		if( b == analysisIncomePanel.getChooseYear() ) {
			String input = JOptionPane.showInputDialog(null,"请输入想要查询年份");
			Pattern pattern = Pattern.compile("[0-9]*");//用来判断字符串中是否存在除字符串以外的字符
			if(input == null) //用户选择取消
				return ;
			if(input.length() == 0) {
				JOptionPane.showMessageDialog(analysisIncomePanel, "年份不能为空");
			}
			else if(pattern.matcher(input).matches()) { //判断用户输入的年份是否为纯数字
				year = Integer.parseInt(input);
			}
			else {
				JOptionPane.showMessageDialog(analysisIncomePanel, "年份不能存在除数字外的其他字符");
			}

		}
		if( b == analysisIncomePanel.getChooseMonth() ) {
			String[] months = {"1月","2月","3月","4月","5月","6月",
					"7月","8月","9月","10月","11月","12月"};
			String singleMonth = (String) JOptionPane.showInputDialog(null, "请选择月份","月份选择",
					JOptionPane.QUESTION_MESSAGE,null,months,months[0]);//从service中得到用户选择的月份字符串
			if(singleMonth == null)
				return ;
			Pattern p = Pattern.compile("[^0-9]");
			Matcher m = p.matcher(singleMonth);
			String monthStr = m.replaceAll("").trim();//monthstr是月份对应纯数字字符串
			month = Integer.parseInt(monthStr);//将数字字符串转换成int型

		}
		if( b == analysisIncomePanel.getRefreshButton() ) {

			try {
				if( year == 0 )
				{
	                JOptionPane.showMessageDialog(analysisIncomePanel, "请先选择年份!");
	                return;
				}
				if( month == 0 )
				{
	                JOptionPane.showMessageDialog(analysisIncomePanel, "请先选择月份!");
	                return;
				}
				List<IncomeRecord> dataList = analysisIncomeService.monthIncomeRecord(year, month);
				JFreeChart chart = JFreeChartCreateUtil.getIncomeChartCreate(dataList);
				analysisIncomePanel.getIncomeChartPanel().setChart(chart);
				analysisIncomePanel.getIncomeChartPanel().updateUI();

			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if( b == analysisIncomePanel.getReturnButton() ) {
			AnalysisPanel analysisPanel = AnalysisPanel.getInstance();
			MainPanel mainPanel = MainPanel.getInstance();
			MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(analysisPanel);
        	analysisPanel.updateData();
        	MainFrame.getInstance().setVisible(true);

		}
		
	}

}
