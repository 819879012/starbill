package gui_listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui_frame.MainFrame;
import gui_panel.CostDetailsPanel;
import gui_panel.DetailsPanel;
import gui_panel.IncomeDetailsPanel;
import gui_panel.MainPanel;

public class DetailsListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		DetailsPanel detailsPanel = DetailsPanel.getInstance();
		MainPanel mainPanel = MainPanel.getInstance();
		
		if( button == detailsPanel.getDetailsEarn() )
		{
			IncomeDetailsPanel incomeDetailsPanel = IncomeDetailsPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(incomeDetailsPanel);
        	incomeDetailsPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		
		if( button == detailsPanel.getDetailsCost() )
		{
			CostDetailsPanel costDetailsPanel = CostDetailsPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(costDetailsPanel);
        	costDetailsPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
	}
}
