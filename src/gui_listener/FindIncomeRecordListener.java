package gui_listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jdesktop.swingx.JXDatePicker;

import entity.IncomeRecord;
import gui_panel.FindIncomeRecordPanel;
import service.FindIncomeRecordService;
import service.IncomeCategoryService;

public class FindIncomeRecordListener implements ActionListener{

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
		FindIncomeRecordPanel findIncomeRecordPanel = FindIncomeRecordPanel.getInstance();
		FindIncomeRecordService recordService = new FindIncomeRecordService();
		IncomeCategoryService incomeCategoryService = new IncomeCategoryService();
		JXDatePicker datepick = findIncomeRecordPanel.getDatepick();
		JButton b = (JButton)e.getSource();
		if( b == findIncomeRecordPanel.getCountEarnButton() ) {
			if(findIncomeRecordPanel.getModel().getIncomeCategory() == null) {
				JOptionPane.showMessageDialog( null, "�û���δ����������!" );
				return;
			}
			List<IncomeRecord> list = recordService.list();  //�������incomerecord
			List<IncomeRecord> newList = new ArrayList<IncomeRecord>();//�½�incomerecord��list
			if( list.size() == 0 ) {
				JOptionPane.showMessageDialog(findIncomeRecordPanel, "ѡ������ںͷ����²����������¼��������ѡ��");
				return ;
			}
			newList.add(list.get(0));
			for(int i=0;i<list.size();i++) {
				if( list.get(i).getIncome() >= newList.get(newList.size()-1).getIncome()&&newList.size()!=1 ) {
					if( list.get(i).getIncome() > newList.get(newList.size()-1).getIncome() ) {
						newList.remove(newList.size()-1);
						newList.add(list.get(i));
					}
					else if(list.get(i).getIncome() == newList.get(newList.size()-1).getIncome() ) {
						newList.add(list.get(i));
					}
				}
			}
			findIncomeRecordPanel.getFindIncomeRecordTableModel().setIncomeRecordList(newList);;
			findIncomeRecordPanel.getShowResultTable().updateUI();
			
		}
		if( b == findIncomeRecordPanel.getSearchButton() ) {
			Date date = datepick.getDate();//�������ϵ�����
			if(findIncomeRecordPanel.getModel().getIncomeCategory() == null) {
				JOptionPane.showMessageDialog( null, "�û���δ����������!" );
				return;
			}
			int cid = findIncomeRecordPanel.getIncomeSelectedCategory().getId();//����û�ѡ������cid
			List<IncomeRecord> list = recordService.list();  //�������incomerecord
			List<IncomeRecord> newList = new ArrayList<IncomeRecord>();//�½�incomerecord��list
			

			SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");//��date��ʽת��
			String  dateString = formatter.format(date);//����õ�����ת����string��
			for(int i = 0;i<list.size();i++) {
				String dateList = formatter.format(list.get(i).getDate());//�����ݿ��е�dateת�����ַ���
				if(dateString.equals(dateList)&&cid==list.get(i).getCid()) {
					newList.add(list.get(i));
				}
			}
			
			if( newList.size() == 0 ){
				JOptionPane.showMessageDialog(findIncomeRecordPanel, "ѡ������ںͷ����²����������¼��������ѡ��");
				return ;
			}
			findIncomeRecordPanel.getFindIncomeRecordTableModel().setIncomeRecordList(newList);;
			findIncomeRecordPanel.getShowResultTable().updateUI();
		}
	}

}
