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
		UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
		FindIncomeRecordPanel findIncomeRecordPanel = FindIncomeRecordPanel.getInstance();
		FindIncomeRecordService recordService = new FindIncomeRecordService();
		IncomeCategoryService incomeCategoryService = new IncomeCategoryService();
		JXDatePicker datepick = findIncomeRecordPanel.getDatepick();
		JButton b = (JButton)e.getSource();
		if( b == findIncomeRecordPanel.getCountEarnButton() ) {
			if(findIncomeRecordPanel.getModel().getIncomeCategory() == null) {
				JOptionPane.showMessageDialog( null, "用户还未添加收入分类!" );
				return;
			}
			List<IncomeRecord> list = recordService.list();  //获得所有incomerecord
			List<IncomeRecord> newList = new ArrayList<IncomeRecord>();//新建incomerecord的list
			if( list.size() == 0 ) {
				JOptionPane.showMessageDialog(findIncomeRecordPanel, "选择的日期和分类下不存在收入记录，请重新选择");
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
			Date date = datepick.getDate();//获得面板上的日期
			if(findIncomeRecordPanel.getModel().getIncomeCategory() == null) {
				JOptionPane.showMessageDialog( null, "用户还未添加收入分类!" );
				return;
			}
			int cid = findIncomeRecordPanel.getIncomeSelectedCategory().getId();//获得用户选择分类的cid
			List<IncomeRecord> list = recordService.list();  //获得所有incomerecord
			List<IncomeRecord> newList = new ArrayList<IncomeRecord>();//新建incomerecord的list
			

			SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");//将date格式转化
			String  dateString = formatter.format(date);//将获得的日期转化成string型
			for(int i = 0;i<list.size();i++) {
				String dateList = formatter.format(list.get(i).getDate());//将数据库中的date转化成字符串
				if(dateString.equals(dateList)&&cid==list.get(i).getCid()) {
					newList.add(list.get(i));
				}
			}
			
			if( newList.size() == 0 ){
				JOptionPane.showMessageDialog(findIncomeRecordPanel, "选择的日期和分类下不存在收入记录，请重新选择");
				return ;
			}
			findIncomeRecordPanel.getFindIncomeRecordTableModel().setIncomeRecordList(newList);;
			findIncomeRecordPanel.getShowResultTable().updateUI();
		}
	}

}
