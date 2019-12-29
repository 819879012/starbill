package gui_listener;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import gui_panel.AddCostPanel;
import gui_panel.AddEarnPanel;
import gui_panel.AnalysisPanel;
import gui_panel.BackupPanel;
import gui_panel.CategoryPanel;
import gui_panel.ConfigPanel;
import gui_panel.ConsumptionViewPanel;
import gui_panel.CostPlanPanel;
import gui_panel.DetailsPanel;
import gui_panel.FindRecordPanel;
import gui_panel.HistogramPanel;
import gui_panel.MainPanel;
import gui_panel.MonthCostTablePanel;
import gui_panel.RecoverPanel;
 
public class ToolBarListener implements ActionListener {
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	MainPanel p = MainPanel.getInstance();
        JButton b = (JButton) e.getSource();
        if ( b == p.getbAddEarn() )
        	p.getWorkingPanel().show(AddEarnPanel.getInstance());
        if( b == p.getbAddCost() )
            p.getWorkingPanel().show(AddCostPanel.getInstance());
        if( b == p.getbDetails() )
        	p.getWorkingPanel().show(DetailsPanel.getInstance());
        if( b == p.getbSpend() )
        	p.getWorkingPanel().show(ConsumptionViewPanel.getInstance());
        if( b == p.getbSpendLine() )
        	p.getWorkingPanel().show(HistogramPanel.getInstance());
        if( b == p.getbFind() )
            p.getWorkingPanel().show(FindRecordPanel.getInstance());
        if( b == p.getbCount() )
            p.getWorkingPanel().show(AnalysisPanel.getInstance());
        if( b == p.getbRecord())
            p.getWorkingPanel().show(CostPlanPanel.getInstance());
        if( b == p.getbCategory() )
            p.getWorkingPanel().show(CategoryPanel.getInstance());
        if( b == p.getbReport() )
            p.getWorkingPanel().show(MonthCostTablePanel.getInstance());
        if( b == p.getbConfig() )
            p.getWorkingPanel().show(ConfigPanel.getInstance());
        if( b == p.getbBackup() )
            p.getWorkingPanel().show(BackupPanel.getInstance());
        if( b == p.getbRecover() )
            p.getWorkingPanel().show(RecoverPanel.getInstance());
        
    }
}