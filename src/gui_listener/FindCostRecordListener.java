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
		UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
		FindCostRecordPanel findCostRecordPanel = FindCostRecordPanel.getInstance();
		FindCostRecordService recordService = new FindCostRecordService();
		CostCategoryService costCategoryService = new CostCategoryService();
		JXDatePicker datepick = findCostRecordPanel.getDatepick();
		JButton b = (JButton)e.getSource();
		findCostRecordPanel.updateData();
		if( b == findCostRecordPanel.getCountCostButton() ) {
			if(findCostRecordPanel.getModel().getCostCategory() == null) {
				JOptionPane.showMessageDialog( null, "用户还未添加收入分类!" );
				return;
			}
			List<CostRecord> list = recordService.list();  //获得所有incomerecord
			List<CostRecord> newList = new ArrayList<CostRecord>();//新建incomerecord的list
			if( list.size() == 0 ) {
				JOptionPane.showMessageDialog(findCostRecordPanel, "选择的日期和分类下不存在收入记录，请重新选择");
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
			Date date = datepick.getDate();//获得面板上的日期
			if(findCostRecordPanel.getModel().getCostCategory() == null) {
				JOptionPane.showMessageDialog( null, "用户还未添加收入分类!" );
				return;
			}
			int cid = findCostRecordPanel.getCostSelectedCategory().getId();//获得用户选择分类的cid
			List<CostRecord> list = recordService.list();  //获得所有incomerecord
			List<CostRecord> newList = new ArrayList<CostRecord>();//新建incomerecord的list
			

			SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");//将date格式转化
			String  dateString = formatter.format(date);//将获得的日期转化成string型
			for(int i = 0;i<list.size();i++) {
				String dateList = formatter.format(list.get(i).getDate());//将数据库中的date转化成字符串
				if(dateString.equals(dateList)&&cid==list.get(i).getCid()) {
					newList.add(list.get(i));
				}
			}
			
			if( newList.size()==0 ){
				JOptionPane.showMessageDialog(findCostRecordPanel, "选择的日期和分类下不存在支出记录，请重新选择");
				return ;
			}
			findCostRecordPanel.getFindCostRecordTableModel().setCostRecordList(newList);;
			findCostRecordPanel.getShowResultTable().updateUI();
		}
	}
}
