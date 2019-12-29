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
	            JOptionPane.showMessageDialog(addEarnPanel, "����������࣬�޷���ӣ����������������!");
	            return;
	        }
	        
			if( date == null )
			{
				JOptionPane.showMessageDialog( null, "��ѡ������!" );
				return;
			}
			
			if( !GUIUtil.checkEmpty( inputMoney, "��������" ) )
				return;
			if( !GUIUtil.checkEmpty( inputComment, "��ע�����" ) )
				return;
			if( !GUIUtil.checkIsIlleagalNumber( inputMoney, "��������") )
			{
				JOptionPane.showMessageDialog( null, "��ȷ���������Ϊ�Ϸ�������!" );
				return;
			}
			
			int cid = addEarnPanel.getIncomeSelectedCategory().getId();
			service.addIncomeRecord(cid, Double.parseDouble(income), comment, date);
			service.updateData(addEarnPanel);
			
			JOptionPane.showMessageDialog(addEarnPanel, "��ӳɹ�");
			return;
		}
		
	}

}
