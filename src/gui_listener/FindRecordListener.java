package gui_listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import gui_frame.MainFrame;
import gui_panel.FindCostRecordPanel;
import gui_panel.FindIncomeRecordPanel;
import gui_panel.FindRecordPanel;
import gui_panel.MainPanel;

public class FindRecordListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		FindRecordPanel findRecordPanel = FindRecordPanel.getInstance();
		MainPanel mainPanel = MainPanel.getInstance();
		
		if( button == findRecordPanel.getFindCostRecord() )
		{
			FindCostRecordPanel findCostRecordPanel = FindCostRecordPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(findCostRecordPanel);
        	findCostRecordPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		
		if( button == findRecordPanel.getFindEarnRecord() )
		{
			FindIncomeRecordPanel findIncomeRecordPanel = FindIncomeRecordPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(findIncomeRecordPanel);
        	findIncomeRecordPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
	}
}
