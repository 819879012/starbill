package gui_listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import gui_frame.MainFrame;
import gui_panel.CategoryPanel;
import gui_panel.CostCategoryPanel;
import gui_panel.IncomeCategoryPanel;
import gui_panel.MainPanel;

public class CategoryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		CategoryPanel categoryPanel = CategoryPanel.getInstance();
		MainPanel mainPanel = MainPanel.getInstance();
		
		if( button == categoryPanel.getCostCategory() )
		{
			CostCategoryPanel costCategoryPanel = CostCategoryPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(costCategoryPanel);
        	costCategoryPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		
		if( button == categoryPanel.getEarnCategory() )
		{
			IncomeCategoryPanel incomeCategoryPanel = IncomeCategoryPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(incomeCategoryPanel);
        	incomeCategoryPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
	}

}
