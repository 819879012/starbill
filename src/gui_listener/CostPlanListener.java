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
		UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
		CostPlanPanel costPlanPanel = CostPlanPanel.getInstance();
		CostPlanService costPlanService = new CostPlanService();
		CostCategoryService costCategoryService = new CostCategoryService();
		
		JTextField inputCost = costPlanPanel.getInputCost();
		JTextField inputComment = costPlanPanel.getInputComment();
		JXDatePicker datepick = costPlanPanel.getDatepick();
		JButton b = (JButton)e.getSource();
		if( b == costPlanPanel.getAddCostPlan() ) {//用户选择增加按钮
			Date date = datepick.getDate();//获得面板上的日期
			if(costPlanPanel.getModel().getCostCategory() == null) {
				JOptionPane.showMessageDialog( null, "用户还未添加支出分类!" );
				return;
			}
//			if(costPlanPanel.getCostSelectedCategory().)
			if( costPlanPanel.getCostSelectedCategory() == null )
			{
				JOptionPane.showMessageDialog( null, "用户还未添加支出分类!" );
				return;
			}
			int cid = costPlanPanel.getCostSelectedCategory().getId();//获得用户选择分类的cid
			if( date == null ) {//用户选择取消 
				JOptionPane.showMessageDialog( null, "请选择日期!" );
				return;
			}
			if( !GUIUtil.checkEmpty( inputCost, "消费金额" ) )//金额框为空
				return;
			if( !GUIUtil.checkEmpty( inputComment, "备注输入框" ) )//备注框为空
				return;
			if( !GUIUtil.checkIsIlleagalNumber( inputCost, "金额输入框") )//金额框输入为不合法字符
			{
				JOptionPane.showMessageDialog( null, "请确保金额输入为合法的数字!" );
				return;
			}
			double  cost = Double.parseDouble(inputCost.getText());//获得用户输入的金额
			String comment = inputComment.getText();//获得用户输入的备注
			costPlanService.addCostPlan(date, cid, cost, comment);
			JOptionPane.showMessageDialog( null, "添加成功!" );
		}
		if( b == costPlanPanel.getChangeCostPlan() ) {
			
			JTable table = costPlanPanel.getCostPlanTable();
			int selectedRow = table.getSelectedRow();//获得行
			int selectedColunmn = table.getSelectedColumn();//获得列
			List<CostPlan> costPlanList = costPlanService.list();//获得costplan的list
			
			int id = costPlanList.get(selectedRow).getId();//从list中获得对应行的id
			Date date = costPlanList.get(selectedRow).getDate();//从list中获得对应的date
			int cid = costPlanList.get(selectedRow).getCid();//从list中获得对应的cid
			double spend = costPlanList.get(selectedRow).getSpend();//从list中获得对应的spend
			String comment = costPlanList.get(selectedRow).getComment();//从list中获得对应的内容
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
							JOptionPane.showMessageDialog(costPlanPanel, "年份输入不合法");
							return ;
						}
						
						yearOfDate = Integer.parseInt(year);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if( yearOfDate<=0 ) {
							JOptionPane.showMessageDialog(costPlanPanel, "年份输入不合法");
							return ;
						}
						if(dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(costPlanPanel, "输入年份的指定月份天数与实际不符，请先修改日期");
							return ;
						}
					}
				else if(selected.equals(choice[1])) {//用户选择修改月
						String month = JOptionPane.showInputDialog(null,"请输入月份");
						if( month == null )
							return ;
						else if( !isTrueNum(month) ) {
							JOptionPane.showMessageDialog(costPlanPanel, "月份输入不合法");
							return ;
						}
						monthOfDate = Integer.parseInt(month);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if(monthOfDate<=0||monthOfDate>=13) {
							JOptionPane.showMessageDialog(costPlanPanel, "月份输入不合法");
							return ;
						}
						if(dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(costPlanPanel, "输入月份的天数与实际不符，请先修改日期");
							return ;
						}
						
					}
				else if(selected.equals(choice[2])) {//用户选择修改日
						String day = JOptionPane.showInputDialog(null,"请输入日期");
						if( day == null )
							return ;
						else if( isTrueNum(day) ) {
							JOptionPane.showMessageDialog(costPlanPanel, "日期输入不合法");
							return ;
						}
						dayOfDate = Integer.parseInt(day);
						@SuppressWarnings("static-access")
						int totalDay = dateUtil.getDayOfMonthByYear(yearOfDate, monthOfDate);
						if(dayOfDate<=0||dayOfDate>totalDay) {
							JOptionPane.showMessageDialog(costPlanPanel, "日期输入不合法");
							return ;
						}
					}

				String newDateStr = new String(yearOfDate+"-"+monthOfDate+"-"+dayOfDate);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				ParsePosition pos = new ParsePosition(0);
				date = formatter.parse(newDateStr,pos);
			}
			
			else if( selectedColunmn == 1 ) {//修改类型
				List<CostCategory> costCategoryList = costCategoryService.list();//获得costcategory的list
				String[] costCategoryStr = new String[costCategoryList.size()] ;//用来存放list中的分类名
				for( int i = 0;i<costCategoryList.size();i++) {
					costCategoryStr[i] = costCategoryList.get(i).getName();
				}
				String chooseCategory = (String) JOptionPane.showInputDialog(null, "请选择类型","类型选择",
						JOptionPane.QUESTION_MESSAGE,null,costCategoryStr,costCategoryStr[0]);//跳出弹框让用户选择
				if(chooseCategory == null)//用户选择取消
					return ;
				for( int i = 0;i<costCategoryList.size();i++) {
					if(costCategoryList.get(i).getName().equals(chooseCategory)) {//找到与用户选择相同的分类
						cid = costCategoryList.get(i).getId();//替换原来的id
					}
				}
			}
			
			else if( selectedColunmn == 2 ) {//修改金额
				String spendStr = JOptionPane.showInputDialog(null,"请输入金额");
				if(spendStr == null) //用户选择取消
					return ;
				if(spendStr.length() == 0) {
					JOptionPane.showMessageDialog(costPlanPanel, "金额不能为空");
					return ;
				}
				else if(isTrueNum(spendStr)) { //判断用户输入的金额是否为纯数字
					spend = Double.parseDouble(spendStr);
				}
				else {
					JOptionPane.showMessageDialog(costPlanPanel, "金额不能存在除数字外的其他字符");
					return ;
				}
			}
			
			else if( selectedColunmn == 3 ) {//修改备注
				comment = JOptionPane.showInputDialog(null,"请输入备注");
				if( comment == null )
					return ;
			}
			costPlanService.updateCostPlan(id, date, cid, spend, comment);
			JOptionPane.showMessageDialog( null, "修改成功!" );
			
		}
		
		if( b == costPlanPanel.getDeleteCostPlan() ) {
			JTable table = costPlanPanel.getCostPlanTable();
			int selectedRow = table.getSelectedRow();
			List<CostPlan> costPlanList = costPlanService.list();
			int id = costPlanList.get(selectedRow).getId();
			if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(costPlanPanel, "确认要删除？")) {
				return ;
			}
			RecoverService recover = RecoverService.getInstance();
			recover.addDeleteCostPlan(costPlanList.get(selectedRow));
			costPlanService.deleteCostPlanById(id);
			JOptionPane.showMessageDialog( null, "删除成功!" );
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
