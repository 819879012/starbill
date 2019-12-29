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
import javax.swing.JTextField;
import javax.swing.UIManager;
import org.jdesktop.swingx.JXDatePicker;

import entity.CostCategory;
import entity.CostPlan;
import gui_panel.CostPlanPanel;
import service.CostCategoryService;
import service.CostPlanService;
import service.RecoverService;
import util.DateUtil;
import util.GUIUtil;

public class CostPlanListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
		CostPlanPanel costPlanPanel = CostPlanPanel.getInstance();
		CostPlanService costPlanService = new CostPlanService();
		CostCategoryService costCategoryService = new CostCategoryService();
		
		JTextField inputCost = costPlanPanel.getInputCost();
		JTextField inputComment = costPlanPanel.getInputComment();
		JXDatePicker datepick = costPlanPanel.getDatepick();
		JButton b = (JButton)e.getSource();
		if( b == costPlanPanel.getAddCostPlan() ) {//�û�ѡ�����Ӱ�ť
			Date date = datepick.getDate();//�������ϵ�����
			if(costPlanPanel.getModel().getCostCategory() == null) {
				JOptionPane.showMessageDialog( null, "�û���δ���֧������!" );
				return;
			}
//			if(costPlanPanel.getCostSelectedCategory().)
			if( costPlanPanel.getCostSelectedCategory() == null )
			{
				JOptionPane.showMessageDialog( null, "�û���δ���֧������!" );
				return;
			}
			int cid = costPlanPanel.getCostSelectedCategory().getId();//����û�ѡ������cid
			if( date == null ) {//�û�ѡ��ȡ�� 
				JOptionPane.showMessageDialog( null, "��ѡ������!" );
				return;
			}
			if( !GUIUtil.checkEmpty( inputCost, "���ѽ��" ) )//����Ϊ��
				return;
			if( !GUIUtil.checkEmpty( inputComment, "��ע�����" ) )//��ע��Ϊ��
				return;
			if( !GUIUtil.checkIsIlleagalNumber( inputCost, "��������") )//��������Ϊ���Ϸ��ַ�
			{
				JOptionPane.showMessageDialog( null, "��ȷ���������Ϊ�Ϸ�������!" );
				return;
			}
			double  cost = Double.parseDouble(inputCost.getText());//����û�����Ľ��
			String comment = inputComment.getText();//����û�����ı�ע
			costPlanService.addCostPlan(date, cid, cost, comment);
			JOptionPane.showMessageDialog( null, "��ӳɹ�!" );
		}
		if( b == costPlanPanel.getChangeCostPlan() ) {
			
			JTable table = costPlanPanel.getCostPlanTable();
			int selectedRow = table.getSelectedRow();//�����
			int selectedColunmn = table.getSelectedColumn();//�����
			List<CostPlan> costPlanList = costPlanService.list();//���costplan��list
			
			int id = costPlanList.get(selectedRow).getId();//��list�л�ö�Ӧ�е�id
			Date date = costPlanList.get(selectedRow).getDate();//��list�л�ö�Ӧ��date
			int cid = costPlanList.get(selectedRow).getCid();//��list�л�ö�Ӧ��cid
			double spend = costPlanList.get(selectedRow).getSpend();//��list�л�ö�Ӧ��spend
			String comment = costPlanList.get(selectedRow).getComment();//��list�л�ö�Ӧ������
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
							JOptionPane.showMessageDialog(costPlanPanel, "������벻�Ϸ�");
							return ;
						}
						
						yearOfDate = Integer.parseInt(year);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if( yearOfDate<=0 ) {
							JOptionPane.showMessageDialog(costPlanPanel, "������벻�Ϸ�");
							return ;
						}
						if(dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(costPlanPanel, "������ݵ�ָ���·�������ʵ�ʲ����������޸�����");
							return ;
						}
					}
				else if(selected.equals(choice[1])) {//�û�ѡ���޸���
						String month = JOptionPane.showInputDialog(null,"�������·�");
						if( month == null )
							return ;
						else if( !isTrueNum(month) ) {
							JOptionPane.showMessageDialog(costPlanPanel, "�·����벻�Ϸ�");
							return ;
						}
						monthOfDate = Integer.parseInt(month);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if(monthOfDate<=0||monthOfDate>=13) {
							JOptionPane.showMessageDialog(costPlanPanel, "�·����벻�Ϸ�");
							return ;
						}
						if(dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(costPlanPanel, "�����·ݵ�������ʵ�ʲ����������޸�����");
							return ;
						}
						
					}
				else if(selected.equals(choice[2])) {//�û�ѡ���޸���
						String day = JOptionPane.showInputDialog(null,"����������");
						if( day == null )
							return ;
						else if( isTrueNum(day) ) {
							JOptionPane.showMessageDialog(costPlanPanel, "�������벻�Ϸ�");
							return ;
						}
						dayOfDate = Integer.parseInt(day);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if(dayOfDate<=0||dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(costPlanPanel, "�������벻�Ϸ�");
							return ;
						}
					}

				String newDateStr = new String(yearOfDate+"-"+monthOfDate+"-"+dayOfDate);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				ParsePosition pos = new ParsePosition(0);
				date = formatter.parse(newDateStr,pos);
			}
			
			else if( selectedColunmn == 1 ) {//�޸�����
				List<CostCategory> costCategoryList = costCategoryService.list();//���costcategory��list
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
			
			else if( selectedColunmn == 2 ) {//�޸Ľ��
				String spendStr = JOptionPane.showInputDialog(null,"��������");
				if(spendStr == null) //�û�ѡ��ȡ��
					return ;
				if(spendStr.length() == 0) {
					JOptionPane.showMessageDialog(costPlanPanel, "����Ϊ��");
					return ;
				}
				else if(isTrueNum(spendStr)) { //�ж��û�����Ľ���Ƿ�Ϊ������
					spend = Double.parseDouble(spendStr);
				}
				else {
					JOptionPane.showMessageDialog(costPlanPanel, "���ܴ��ڳ�������������ַ�");
					return ;
				}
			}
			
			else if( selectedColunmn == 3 ) {//�޸ı�ע
				comment = JOptionPane.showInputDialog(null,"�����뱸ע");
				if( comment == null )
					return ;
			}
			costPlanService.updateCostPlan(id, date, cid, spend, comment);
			JOptionPane.showMessageDialog( null, "�޸ĳɹ�!" );
			
		}
		
		if( b == costPlanPanel.getDeleteCostPlan() ) {
			JTable table = costPlanPanel.getCostPlanTable();
			int selectedRow = table.getSelectedRow();
			List<CostPlan> costPlanList = costPlanService.list();
			int id = costPlanList.get(selectedRow).getId();
			if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(costPlanPanel, "ȷ��Ҫɾ����")) {
				return ;
			}
			RecoverService recover = RecoverService.getInstance();
			recover.addDeleteCostPlan(costPlanList.get(selectedRow));
			costPlanService.deleteCostPlanById(id);
			JOptionPane.showMessageDialog( null, "ɾ���ɹ�!" );
		}
		costPlanPanel.updateData();
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
