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
			String input = JOptionPane.showInputDialog(null,"��������Ҫ��ѯ���");
			Pattern pattern = Pattern.compile("[0-9]*");//�����ж��ַ������Ƿ���ڳ��ַ���������ַ�
			if(input == null) //�û�ѡ��ȡ��
				return ;
			if(input.length() == 0) {
				JOptionPane.showMessageDialog(panel, "��ݲ���Ϊ��");
			}
			else if(pattern.matcher(input).matches()) { //�ж��û����������Ƿ�Ϊ������
				year = Integer.parseInt(input);
			}
			else {
				JOptionPane.showMessageDialog(panel, "��ݲ��ܴ��ڳ�������������ַ�");
			}
		}
		if( b == panel.getInputMonth() ) {
			String[] months = {"1��","2��","3��","4��","5��","6��",
					"7��","8��","9��","10��","11��","12��"};
			String singleMonth = (String) JOptionPane.showInputDialog(null, "��ѡ���·�","�·�ѡ��",
					JOptionPane.QUESTION_MESSAGE,null,months,months[0]);//��service�еõ��û�ѡ����·��ַ���
			if(singleMonth == null )
				return ;
			Pattern p = Pattern.compile("[^0-9]");
			Matcher m = p.matcher(singleMonth);
			String monthStr = m.replaceAll("").trim();//monthstr���·ݶ�Ӧ�������ַ���
			month = Integer.parseInt(monthStr);//�������ַ���ת����int��
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
				JOptionPane.showMessageDialog(panel, "������ݺ��·ݲ��������Ѽ�¼������������");
				year = 0;
				month = 0;
				return ;
			}
			panel.getMonthCostTableModel().setCostRecordList(newList);
			panel.getShowInfoTable().updateUI();
		}

	}

}
