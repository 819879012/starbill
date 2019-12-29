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

import entity.CostRecord;
import gui_panel.FindCostRecordPanel;
import service.CostCategoryService;
import service.FindCostRecordService;

public class FindCostRecordListener implements ActionListener{
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
		FindCostRecordPanel findCostRecordPanel = FindCostRecordPanel.getInstance();
		FindCostRecordService recordService = new FindCostRecordService();
		CostCategoryService costCategoryService = new CostCategoryService();
		JXDatePicker datepick = findCostRecordPanel.getDatepick();
		JButton b = (JButton)e.getSource();
		findCostRecordPanel.updateData();
		if( b == findCostRecordPanel.getCountCostButton() ) {
			if(findCostRecordPanel.getModel().getCostCategory() == null) {
				JOptionPane.showMessageDialog( null, "�û���δ����������!" );
				return;
			}
			List<CostRecord> list = recordService.list();  //�������incomerecord
			List<CostRecord> newList = new ArrayList<CostRecord>();//�½�incomerecord��list
			if( list.size() == 0 ) {
				JOptionPane.showMessageDialog(findCostRecordPanel, "ѡ������ںͷ����²����������¼��������ѡ��");
				return ;
			}
			newList.add(list.get(0));
			for(int i=0;i<list.size();i++) {
				if( list.get(i).getCost() >= newList.get(newList.size()-1).getCost()&&newList.size()!=1 ) {
					if( list.get(i).getCost() > newList.get(newList.size()-1).getCost() ) {
						newList.remove(newList.size()-1);
						newList.add(list.get(i));
					}
					else if(list.get(i).getCost() == newList.get(newList.size()-1).getCost() ) {
						newList.add(list.get(i));
					}
				}
			}
			findCostRecordPanel.getFindCostRecordTableModel().setCostRecordList(newList);;
			findCostRecordPanel.getShowResultTable().updateUI();
			
		}
		if( b == findCostRecordPanel.getSearchButton() ) {
			Date date = datepick.getDate();//�������ϵ�����
			if(findCostRecordPanel.getModel().getCostCategory() == null) {
				JOptionPane.showMessageDialog( null, "�û���δ����������!" );
				return;
			}
			int cid = findCostRecordPanel.getCostSelectedCategory().getId();//����û�ѡ������cid
			List<CostRecord> list = recordService.list();  //�������incomerecord
			List<CostRecord> newList = new ArrayList<CostRecord>();//�½�incomerecord��list
			

			SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");//��date��ʽת��
			String  dateString = formatter.format(date);//����õ�����ת����string��
			for(int i = 0;i<list.size();i++) {
				String dateList = formatter.format(list.get(i).getDate());//�����ݿ��е�dateת�����ַ���
				if(dateString.equals(dateList)&&cid==list.get(i).getCid()) {
					newList.add(list.get(i));
				}
			}
			
			if( newList.size()==0 ){
				JOptionPane.showMessageDialog(findCostRecordPanel, "ѡ������ںͷ����²�����֧����¼��������ѡ��");
				return ;
			}
			findCostRecordPanel.getFindCostRecordTableModel().setCostRecordList(newList);;
			findCostRecordPanel.getShowResultTable().updateUI();
		}
	}
}
