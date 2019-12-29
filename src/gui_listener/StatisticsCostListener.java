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
		if( b == panel.getChooseMonth() ) {
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
		if( b == panel.getChooseCategory() ) {
			if(categoryService.list().size() == 0) {
				JOptionPane.showMessageDialog( null, "�û���δ����������!" );
				return;
			}
			List<CostCategory> costCategoryList = categoryService.list();//���costcategory��list
			String[] costCategoryStr = new String[costCategoryList.size()] ;//�������list�еķ�����
			for( int i = 0;i<costCategoryList.size();i++) {
				costCategoryStr[i] = costCategoryList.get(i).getName();
			}
			String chooseCategory = (String) JOptionPane.showInputDialog(null, "��ѡ������","����ѡ��",
					JOptionPane.QUESTION_MESSAGE,null,costCategoryStr,costCategoryStr[0]);//�����������û�ѡ��
			if(chooseCategory == null)//�û�ѡ��ȡ��
				return ;
			for( int i = 0;i<costCategoryList.size();i++) {
				if(costCategoryList.get(i).getName().equals(chooseCategory)) {//�ҵ����û�ѡ����ͬ�ķ���
					cid = costCategoryList.get(i).getId();//�滻ԭ����id
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
				JOptionPane.showMessageDialog( null, "�û���δ��������¼!" );
				return;
			}
			if( year == 0 ) {
				JOptionPane.showMessageDialog( null, "�û���δ������!" );
				return;
			}
			if( month == 0 ) {
				JOptionPane.showMessageDialog( null, "�û���δ����·�!" );
				return;
			}
			if( cid == 0 ) {
				JOptionPane.showMessageDialog( null, "�û���δѡ�����!" );
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
				JOptionPane.showMessageDialog(panel, "�������ݡ��·ݡ������²����������¼������������");
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
