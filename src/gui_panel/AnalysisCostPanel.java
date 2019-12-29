package gui_panel;

import javax.swing.JButton;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import constant.TagXml;

@SuppressWarnings("serial")
public class AnalysisCostPanel extends PublicAnalysisPanel{
	
	private static AnalysisCostPanel instance = new AnalysisCostPanel();
			
	public AnalysisCostPanel() {
		super(TagXml.analysisCostPanel);
		
	}
	
	/**
	 * @return the instance
	 */
	public static AnalysisCostPanel getInstance() {
		return instance;
	}

	/**
	 * @return the returnButton
	 */
	public JButton getReturnButton() {
		return super.getReturnButton();
	}

	/**
	 * @return the refreshButton
	 */
	public JButton getRefreshButton() {
		return super.getRefreshButton();
	}

	/**
	 * @return the chooseMouth
	 */
	public JButton getChooseMonth() {
		return super.getChooseMonth();
	}
	
	/**
	 * @return the chooseYear
	 */
	public JButton getChooseYear() {
		return super.getChooseYear();
	}

	/**
	 * @return the mChart
	 */
	public JFreeChart getmChart() {
		return super.getCostChart();
	}

	/**
	 * @return the costChartPanel
	 */
	public ChartPanel getCostChartPanel() {
		return super.getCostChartPanel();
	}

}
