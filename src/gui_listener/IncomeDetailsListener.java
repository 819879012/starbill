package gui_listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import entity.IncomeCategory;
import entity.IncomeRecord;
import gui_frame.MainFrame;
import gui_model.IncomeDetailsTableModel;
import gui_panel.IncomeDetailsPanel;
import gui_panel.MainPanel;
import service.IncomeCategoryService;
import service.IncomeDetailsService;
import service.RecoverService;
import util.DateUtil;

public class IncomeDetailsListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
		IncomeDetailsPanel incomeDetailsPanel = IncomeDetailsPanel.getInstance();
		IncomeDetailsService incomeDetailsService = new IncomeDetailsService();
		IncomeCategoryService incomeCategoryService = new IncomeCategoryService();
		IncomeDetailsTableModel model = incomeDetailsPanel.getModel();
		JButton b = (JButton)e.getSource();
		
		if( b == incomeDetailsPanel.getChangeButton() ) {
			System.out.println(incomeDetailsPanel.getModel().getIncomeRecordList().size());
			if(incomeDetailsPanel.getModel().getIncomeRecordList().size() == 0 ) {
				JOptionPane.showMessageDialog( null, "�û���δ��������¼!" );
				return;
			}
			JTable table = incomeDetailsPanel.getShowInfoTable();
			List<IncomeRecord> list = model.getIncomeRecordList();//��ñ���е�list
			int selectedRow = table.getSelectedRow();//�����
			int selectedColunmn = table.getSelectedColumn();//�����
			
			int id = list.get(selectedRow).getId();//��list�л�ö�Ӧ�е�id
			Date date = list.get(selectedRow).getDate();//��list�л�ö�Ӧ��date
			int cid = list.get(selectedRow).getCid();//��list�л�ö�Ӧ��cid
			double income = list.get(selectedRow).getIncome();//��list�л�ö�Ӧ��income
			String comment = list.get(selectedRow).getComment();//��list�л�ö�Ӧ������
			
			if( selectedColunmn == 0 ) {//�޸�����
				String[] choice = {"��","��","��"};
				String selected = (String) JOptionPane.showInputDialog(null, "��ѡ������","����ѡ��",
						JOptionPane.QUESTION_MESSAGE,null,choice,choice[0]);
				DateUtil dateUtil = new DateUtil();
				int dayOfDate = dateUtil.getDayOfDate(date);
				int monthOfDate = dateUtil.getMonthOfDate(date);
				int yearOfDate = dateUtil.getYearOfDate(date);
				if( selected == null )//�û�ѡ��ȡ��
					return ;
				if(selected.equals(choice[0])) {//�û�ѡ���޸���
						String year = JOptionPane.showInputDialog(null,"���������");
						
						if( year == null )
							return ;
						else if( !isTrueNum(year) ) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "������벻�Ϸ�");
							return ;
						}
						
						yearOfDate = Integer.parseInt(year);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if( yearOfDate<=0 ) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "������벻�Ϸ�");
							return ;
						}
						if(dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "������ݵ�ָ���·�������ʵ�ʲ����������޸�����");
							return ;
						}
					}
				else if(selected.equals(choice[1])) {//�û�ѡ���޸���
						String month = JOptionPane.showInputDialog(null,"�������·�");
						if( month == null )
							return ;
						else if( !isTrueNum(month) ) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "�·����벻�Ϸ�");
							return ;
						}
						monthOfDate = Integer.parseInt(month);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if(monthOfDate<=0||monthOfDate>=13) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "�·����벻�Ϸ�");
							return ;
						}
						if(dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "�����·ݵ�������ʵ�ʲ����������޸�����");
							return ;
						}
						
					}
				else if(selected.equals(choice[2])) {//�û�ѡ���޸���
						String day = JOptionPane.showInputDialog(null,"����������");
						if( day == null )
							return ;
						else if( !isTrueNum(day) ) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "�������벻�Ϸ�");
							return ;
						}
						dayOfDate = Integer.parseInt(day);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if(dayOfDate<=0||dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "�������벻�Ϸ�");
							return ;
						}
					}
				String newDateStr = new String(yearOfDate+"-"+monthOfDate+"-"+dayOfDate);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				ParsePosition pos = new ParsePosition(0);
				date = formatter.parse(newDateStr,pos);
			}
			else if( selectedColunmn == 1 ) {//�޸�����
				List<IncomeCategory> incomeCategoryList = incomeCategoryService.list();//���costcategory��list
				String[] incomeCategoryStr = new String[incomeCategoryList.size()] ;//�������list�еķ�����
				for( int i = 0;i<incomeCategoryList.size();i++) {
					incomeCategoryStr[i] = incomeCategoryList.get(i).getName();
				}
				String chooseCategory = (String) JOptionPane.showInputDialog(null, "��ѡ������","����ѡ��",
						JOptionPane.QUESTION_MESSAGE,null,incomeCategoryStr,incomeCategoryStr[0]);//�����������û�ѡ��
				if(chooseCategory == null)//�û�ѡ��ȡ��
					return ;
				for( int i = 0;i<incomeCategoryList.size();i++) {
					if(incomeCategoryList.get(i).getName().equals(chooseCategory)) {//�ҵ����û�ѡ����ͬ�ķ���
						cid = incomeCategoryList.get(i).getId();//�滻ԭ����id
					}
				}
			}
			else if( selectedColunmn == 2 ) {//�޸Ľ��
				String incomeStr = JOptionPane.showInputDialog(null,"��������");
				if(incomeStr == null) //�û�ѡ��ȡ��
					return ;
				if(incomeStr.length() == 0) {
					JOptionPane.showMessageDialog(incomeDetailsPanel, "����Ϊ��");
					return ;
				}
				else if(isTrueNum(incomeStr)) { //�ж��û�����Ľ���Ƿ�Ϊ������
					income = Double.parseDouble(incomeStr);
				}
				else {
					JOptionPane.showMessageDialog(incomeDetailsPanel, "���ܴ��ڳ�������������ַ�");
					return ;
				}
			}
			else if( selectedColunmn == 3 ) {//�޸ı�ע
				comment = JOptionPane.showInputDialog(null,"�����뱸ע");
				if( comment == null )
					return ;
			}
			incomeDetailsService.updateIncomeRecord(id, date, cid, income, comment);
			JOptionPane.showMessageDialog( null, "�޸ĳɹ�!" );
		}
		if( b == incomeDetailsPanel.getDeleteButton() ) {
			if(incomeDetailsPanel.getModel().getIncomeRecordList().size() == 0 ) {
				JOptionPane.showMessageDialog( null, "�û���δ��������¼!" );
				return;
			}
			JTable table = incomeDetailsPanel.getShowInfoTable();
			List<IncomeRecord> list = model.getIncomeRecordList();//��ñ���е�list
			int selectedRow = table.getSelectedRow();//�����
			int id = list.get(selectedRow).getId();//��list�л�ö�Ӧ�е�id
			RecoverService recover = RecoverService.getInstance();
			recover.addDeleteIncomeRecord(list.get(selectedRow));
			incomeDetailsService.deleteIncomeRecordById(id);
			JOptionPane.showMessageDialog( null, "ɾ���ɹ�!" );
		}
		if( b == incomeDetailsPanel.getRefreshButton() ) {
			IncomeDetailsPanel newIncomeDetailsPanel = IncomeDetailsPanel.getInstance();
			MainPanel mainPanel = MainPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(newIncomeDetailsPanel);
        	incomeDetailsPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
	}
	public boolean isTrueNum(String num){
        try{
                Double.parseDouble(num);
                return true;
         }catch(NumberFormatException e){
                return false;
        }
	}

}
