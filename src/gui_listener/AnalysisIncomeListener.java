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
		UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
		AnalysisIncomePanel analysisIncomePanel = AnalysisIncomePanel.getInstance();
		AnalysisIncomeService analysisIncomeService = new AnalysisIncomeService();
		JButton b = (JButton) e.getSource();
		if( b == analysisIncomePanel.getChooseYear() ) {
			String input = JOptionPane.showInputDialog(null,"��������Ҫ��ѯ���");
			Pattern pattern = Pattern.compile("[0-9]*");//�����ж��ַ������Ƿ���ڳ��ַ���������ַ�
			if(input == null) //�û�ѡ��ȡ��
				return ;
			if(input.length() == 0) {
				JOptionPane.showMessageDialog(analysisIncomePanel, "��ݲ���Ϊ��");
			}
			else if(pattern.matcher(input).matches()) { //�ж��û����������Ƿ�Ϊ������
				year = Integer.parseInt(input);
			}
			else {
				JOptionPane.showMessageDialog(analysisIncomePanel, "��ݲ��ܴ��ڳ�������������ַ�");
			}

		}
		if( b == analysisIncomePanel.getChooseMonth() ) {
			String[] months = {"1��","2��","3��","4��","5��","6��",
					"7��","8��","9��","10��","11��","12��"};
			String singleMonth = (String) JOptionPane.showInputDialog(null, "��ѡ���·�","�·�ѡ��",
					JOptionPane.QUESTION_MESSAGE,null,months,months[0]);//��service�еõ��û�ѡ����·��ַ���
			if(singleMonth == null)
				return ;
			Pattern p = Pattern.compile("[^0-9]");
			Matcher m = p.matcher(singleMonth);
			String monthStr = m.replaceAll("").trim();//monthstr���·ݶ�Ӧ�������ַ���
			month = Integer.parseInt(monthStr);//�������ַ���ת����int��

		}
		if( b == analysisIncomePanel.getRefreshButton() ) {

			try {
				if( year == 0 )
				{
	                JOptionPane.showMessageDialog(analysisIncomePanel, "����ѡ�����!");
	                return;
				}
				if( month == 0 )
				{
	                JOptionPane.showMessageDialog(analysisIncomePanel, "����ѡ���·�!");
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
