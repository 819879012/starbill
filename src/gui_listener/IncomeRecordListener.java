package gui_listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import gui_frame.MainFrame;
import gui_panel.AddEarnPanel;
import gui_panel.IncomeCategoryPanel;
import gui_panel.MainPanel;
import service.IncomeRecordService;
import util.GUIUtil;

public class IncomeRecordListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		AddEarnPanel addEarnPanel = AddEarnPanel.getInstance();
		IncomeRecordService service = new IncomeRecordService();
		JTextField inputMoney = addEarnPanel.getInputMoney();
		JTextField inputComment = addEarnPanel.getInputComment();
		JXDatePicker datepick = addEarnPanel.getDatepick();
		JButton button = (JButton)e.getSource();
		
		if( button == addEarnPanel.getAddCategory() )
		{
	    	MainPanel mainPanel = MainPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	IncomeCategoryPanel incomeCategoryPanel = IncomeCategoryPanel.getInstance();
        	incomeCategoryPanel.updateData();
        	mainPanel.getWorkingPanel().show(incomeCategoryPanel);
        	MainFrame.getInstance().setVisible(true);
			return;
		}
		
		if( button == addEarnPanel.getSaveChange() )
		{
			String income = inputMoney.getText();
			String comment = inputComment.getText();
			Date date = datepick.getDate();
	        if( 0 == addEarnPanel.getIncomeCategoryModel().getSize() )
	        {
	            JOptionPane.showMessageDialog(addEarnPanel, "暂无收入分类，无法添加，请先增加收入分类!");
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
			
			int cid = addEarnPanel.getIncomeSelectedCategory().getId();
			service.addIncomeRecord(cid, Double.parseDouble(income), comment, date);
			service.updateData(addEarnPanel);
			
			JOptionPane.showMessageDialog(addEarnPanel, "添加成功");
			return;
		}
		
	}

}
