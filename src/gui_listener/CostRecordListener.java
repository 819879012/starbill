package gui_listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

import gui_frame.MainFrame;
import gui_panel.AddCostPanel;
import gui_panel.CostCategoryPanel;
import gui_panel.MainPanel;
import service.CostRecordService;
import util.GUIUtil;

public class CostRecordListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		AddCostPanel addCostPanel = AddCostPanel.getInstance();
		CostRecordService service = new CostRecordService();
		JTextField inputMoney = addCostPanel.getInputMoney();
		JTextField inputComment = addCostPanel.getInputComment();
		JXDatePicker datepick = addCostPanel.getDatepick();
		JButton button = (JButton)e.getSource();
		
		if( button == addCostPanel.getAddCategory() )
		{
	    	MainPanel mainPanel = MainPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	CostCategoryPanel costCategoryPanel = CostCategoryPanel.getInstance();
        	costCategoryPanel.updateData();
        	mainPanel.getWorkingPanel().show(costCategoryPanel);
        	MainFrame.getInstance().setVisible(true);
			return;
		}
		
		if( button == addCostPanel.getSaveChange() )
		{
			String cost = inputMoney.getText();
			String comment = inputComment.getText();
			Date date = datepick.getDate();
	        if( 0 == addCostPanel.getCostCategoryModel().getSize() )
	        {
	            JOptionPane.showMessageDialog(addCostPanel, "暂无支出分类，无法添加，请先增加支出分类!");
	            return;
	        }
	        
			if( date == null )
			{
				JOptionPane.showMessageDialog( null, "请选择日期!" );
				return;
			}
			
			if( !GUIUtil.checkEmpty( inputMoney, "金额输入框" ) )
				return;
			if( !GUIUtil.checkEmpty( inputComment, "备注输入框" ) )
				return;
			if( !GUIUtil.checkIsIlleagalNumber( inputMoney, "金额输入框") )
			{
				JOptionPane.showMessageDialog( null, "请确保金额输入为合法的数字!" );
				return;
			}
			
			int cid = addCostPanel.getCostSelectedCategory().getId();
			service.addCostRecord(cid, Double.parseDouble(cost), comment, date);
			service.updateData(addCostPanel);
			
			JOptionPane.showMessageDialog(addCostPanel, "添加成功");
			return;
		}
		
	}

}
