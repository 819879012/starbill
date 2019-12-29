package gui_listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import gui_frame.MainFrame;
import gui_panel.HistogramPanel;
import gui_panel.IncomeHistogramPanel;
import gui_panel.MainPanel;
import gui_panel.MonthSpendHistogramPanel;

public class HistrogramListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		HistogramPanel categoryPanel = HistogramPanel.getInstance();
		MainPanel mainPanel = MainPanel.getInstance();
		
		if( button == categoryPanel.getCostCategory() )
		{
			MonthSpendHistogramPanel costCategoryPanel = MonthSpendHistogramPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(costCategoryPanel);
        	costCategoryPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		
		if( button == categoryPanel.getEarnCategory() )
		{
			IncomeHistogramPanel incomeCategoryPanel = IncomeHistogramPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(incomeCategoryPanel);
        	incomeCategoryPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
	}
}
