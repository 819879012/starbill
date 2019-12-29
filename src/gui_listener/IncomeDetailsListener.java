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
		UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
		IncomeDetailsPanel incomeDetailsPanel = IncomeDetailsPanel.getInstance();
		IncomeDetailsService incomeDetailsService = new IncomeDetailsService();
		IncomeCategoryService incomeCategoryService = new IncomeCategoryService();
		IncomeDetailsTableModel model = incomeDetailsPanel.getModel();
		JButton b = (JButton)e.getSource();
		
		if( b == incomeDetailsPanel.getChangeButton() ) {
			System.out.println(incomeDetailsPanel.getModel().getIncomeRecordList().size());
			if(incomeDetailsPanel.getModel().getIncomeRecordList().size() == 0 ) {
				JOptionPane.showMessageDialog( null, "用户还未添加收入记录!" );
				return;
			}
			JTable table = incomeDetailsPanel.getShowInfoTable();
			List<IncomeRecord> list = model.getIncomeRecordList();//获得表格中的list
			int selectedRow = table.getSelectedRow();//获得行
			int selectedColunmn = table.getSelectedColumn();//获得列
			
			int id = list.get(selectedRow).getId();//从list中获得对应行的id
			Date date = list.get(selectedRow).getDate();//从list中获得对应的date
			int cid = list.get(selectedRow).getCid();//从list中获得对应的cid
			double income = list.get(selectedRow).getIncome();//从list中获得对应的income
			String comment = list.get(selectedRow).getComment();//从list中获得对应的内容
			
			if( selectedColunmn == 0 ) {//修改日期
				String[] choice = {"年","月","日"};
				String selected = (String) JOptionPane.showInputDialog(null, "请选择类型","类型选择",
						JOptionPane.QUESTION_MESSAGE,null,choice,choice[0]);
				DateUtil dateUtil = new DateUtil();
				int dayOfDate = dateUtil.getDayOfDate(date);
				int monthOfDate = dateUtil.getMonthOfDate(date);
				int yearOfDate = dateUtil.getYearOfDate(date);
				if( selected == null )//用户选择取消
					return ;
				if(selected.equals(choice[0])) {//用户选择修改年
						String year = JOptionPane.showInputDialog(null,"请输入年份");
						
						if( year == null )
							return ;
						else if( !isTrueNum(year) ) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "年份输入不合法");
							return ;
						}
						
						yearOfDate = Integer.parseInt(year);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if( yearOfDate<=0 ) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "年份输入不合法");
							return ;
						}
						if(dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "输入年份的指定月份天数与实际不符，请先修改日期");
							return ;
						}
					}
				else if(selected.equals(choice[1])) {//用户选择修改月
						String month = JOptionPane.showInputDialog(null,"请输入月份");
						if( month == null )
							return ;
						else if( !isTrueNum(month) ) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "月份输入不合法");
							return ;
						}
						monthOfDate = Integer.parseInt(month);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if(monthOfDate<=0||monthOfDate>=13) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "月份输入不合法");
							return ;
						}
						if(dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "输入月份的天数与实际不符，请先修改日期");
							return ;
						}
						
					}
				else if(selected.equals(choice[2])) {//用户选择修改日
						String day = JOptionPane.showInputDialog(null,"请输入日期");
						if( day == null )
							return ;
						else if( !isTrueNum(day) ) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "日期输入不合法");
							return ;
						}
						dayOfDate = Integer.parseInt(day);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if(dayOfDate<=0||dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(incomeDetailsPanel, "日期输入不合法");
							return ;
						}
					}
				String newDateStr = new String(yearOfDate+"-"+monthOfDate+"-"+dayOfDate);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				ParsePosition pos = new ParsePosition(0);
				date = formatter.parse(newDateStr,pos);
			}
			else if( selectedColunmn == 1 ) {//修改类型
				List<IncomeCategory> incomeCategoryList = incomeCategoryService.list();//获得costcategory的list
				String[] incomeCategoryStr = new String[incomeCategoryList.size()] ;//用来存放list中的分类名
				for( int i = 0;i<incomeCategoryList.size();i++) {
					incomeCategoryStr[i] = incomeCategoryList.get(i).getName();
				}
				String chooseCategory = (String) JOptionPane.showInputDialog(null, "请选择类型","类型选择",
						JOptionPane.QUESTION_MESSAGE,null,incomeCategoryStr,incomeCategoryStr[0]);//跳出弹框让用户选择
				if(chooseCategory == null)//用户选择取消
					return ;
				for( int i = 0;i<incomeCategoryList.size();i++) {
					if(incomeCategoryList.get(i).getName().equals(chooseCategory)) {//找到与用户选择相同的分类
						cid = incomeCategoryList.get(i).getId();//替换原来的id
					}
				}
			}
			else if( selectedColunmn == 2 ) {//修改金额
				String incomeStr = JOptionPane.showInputDialog(null,"请输入金额");
				if(incomeStr == null) //用户选择取消
					return ;
				if(incomeStr.length() == 0) {
					JOptionPane.showMessageDialog(incomeDetailsPanel, "金额不能为空");
					return ;
				}
				else if(isTrueNum(incomeStr)) { //判断用户输入的金额是否为纯数字
					income = Double.parseDouble(incomeStr);
				}
				else {
					JOptionPane.showMessageDialog(incomeDetailsPanel, "金额不能存在除数字外的其他字符");
					return ;
				}
			}
			else if( selectedColunmn == 3 ) {//修改备注
				comment = JOptionPane.showInputDialog(null,"请输入备注");
				if( comment == null )
					return ;
			}
			incomeDetailsService.updateIncomeRecord(id, date, cid, income, comment);
			JOptionPane.showMessageDialog( null, "修改成功!" );
		}
		if( b == incomeDetailsPanel.getDeleteButton() ) {
			if(incomeDetailsPanel.getModel().getIncomeRecordList().size() == 0 ) {
				JOptionPane.showMessageDialog( null, "用户还未添加收入记录!" );
				return;
			}
			JTable table = incomeDetailsPanel.getShowInfoTable();
			List<IncomeRecord> list = model.getIncomeRecordList();//获得表格中的list
			int selectedRow = table.getSelectedRow();//获得行
			int id = list.get(selectedRow).getId();//从list中获得对应行的id
			RecoverService recover = RecoverService.getInstance();
			recover.addDeleteIncomeRecord(list.get(selectedRow));
			incomeDetailsService.deleteIncomeRecordById(id);
			JOptionPane.showMessageDialog( null, "删除成功!" );
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
