package gui_listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui_frame.MainFrame;
import gui_panel.AnalysisCostPanel;
import gui_panel.AnalysisIncomePanel;
import gui_panel.AnalysisPanel;
import gui_panel.MainPanel;
import gui_panel.StatisticsCostPanel;
import gui_panel.StatisticsEarnPanel;

public class AnalysisListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton button = (JButton)e.getSource();
		AnalysisPanel analysisPanel = AnalysisPanel.getInstance();
		MainPanel mainPanel = MainPanel.getInstance();
		
		if( button == analysisPanel.getAnalysisCost() ) {

			AnalysisCostPanel analysisCostPanel = AnalysisCostPanel.getInstance();
			MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(analysisCostPanel);
        	analysisCostPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		else if( button == analysisPanel.getAnalysisEarn() ) {
			AnalysisIncomePanel analysisIncomePanel = AnalysisIncomePanel.getInstance();
			MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(analysisIncomePanel);
        	analysisIncomePanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		else if( button == analysisPanel.getCountCost() ) {
			StatisticsCostPanel statisticsCostPanel = StatisticsCostPanel.getInstance();
			MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(statisticsCostPanel);
        	statisticsCostPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		else if( button == analysisPanel.getCountEarn() ) {
			StatisticsEarnPanel statisticsEarnPanel = StatisticsEarnPanel.getInstance();
			MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(statisticsEarnPanel);
        	statisticsEarnPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
	}
	
}
