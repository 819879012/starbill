package gui_panel;

import javax.swing.JButton;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import constant.TagXml;

@SuppressWarnings("serial")
public class AnalysisIncomePanel extends PublicAnalysisPanel{
	
	private static AnalysisIncomePanel instance = new AnalysisIncomePanel();
	
	
	public AnalysisIncomePanel() {
		super(TagXml.analysisIncomePanel);
		
	}
	
	/**
	 * @return the instance
	 */
	public static AnalysisIncomePanel getInstance() {
		return instance;
	}

	/**
	 * @return the returnButton
	 */
	public JButton getReturnButton() {
		return super.getReturnButton();
	}


	public JButton getRefreshButton() {
		return super.getRefreshButton();
	}


	public JButton getChooseMonth() {
		return super.getChooseMonth();
	}
	
	
	public JButton getChooseYear() {
		return super.getChooseYear();
	}
	
	public JFreeChart getmChart() {
		return super.getIncomeChart();
	}


	public ChartPanel getIncomeChartPanel() {
		return super.getIncomeChartPanel();
	}
}
