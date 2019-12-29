package gui_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import constant.TagXml;
import gui_listener.AnalysisCostListener;
import gui_listener.AnalysisIncomeListener;
import util.ColorUtil;
import util.GUIUtil;
import util.JFreeChartCreateUtil;

@SuppressWarnings("serial")
public class PublicAnalysisPanel extends GraphPanel{
	
	private JButton returnButton = new JButton();
	private JButton refreshButton = new JButton();
	private JButton chooseMonth = new JButton();
	private JButton chooseYear = new JButton();
	
	private JFreeChart costChart = JFreeChartCreateUtil.JFreeChartCreate();
	private ChartPanel costChartPanel = new ChartPanel(costChart);
	private JFreeChart incomeChart = JFreeChartCreateUtil.JFreeChartCreate();
	private ChartPanel incomeChartPanel = new ChartPanel(incomeChart);
	private int flag;
	
	public PublicAnalysisPanel(int flag) {
		super(MainPanel.BACKGROUND);
		this.flag = flag;
		setLayout(new BorderLayout());
		addPanel();
		addListener();
	}
	
	private void addPanel() {
		// TODO Auto-generated method stub
		if(flag == TagXml.analysisIncomePanel) {
			UtilPanel p1 = new UtilPanel();
			JToolBar tb = new JToolBar();
			setButtonColor( ColorUtil.blueColor );
			setTextFont( new Font("微软雅黑",Font.BOLD,20) );
			setButtonImage();
			tb.add(chooseYear);
			tb.add(chooseMonth);
			tb.add(refreshButton);
			tb.add(returnButton);
			tb.setFloatable(false);
			p1.add(tb,BorderLayout.SOUTH);
			add(p1,BorderLayout.SOUTH); 
			add(incomeChartPanel,BorderLayout.CENTER); 
		}
		else {
			UtilPanel p1 = new UtilPanel();
			JToolBar tb = new JToolBar();
			setButtonColor( ColorUtil.blueColor );
			setTextFont( new Font("微软雅黑",Font.BOLD,20) );
			setButtonImage();
			tb.add(chooseYear);
			tb.add(chooseMonth);
			tb.add(refreshButton);
			tb.add(returnButton);
			tb.setFloatable(false);
			p1.add(tb,BorderLayout.SOUTH);
			add(p1,BorderLayout.SOUTH); 
			add(costChartPanel,BorderLayout.CENTER); 
		}
	}
	@Override
	public void updateData() {
		
	}

	@Override
	public void addListener() {
		if(flag == TagXml.analysisIncomePanel) {
			AnalysisIncomeListener listener = new AnalysisIncomeListener();
			chooseYear.addActionListener(listener);
			chooseMonth.addActionListener(listener);
			refreshButton.addActionListener(listener);
			returnButton.addActionListener(listener);
		}
		else {
			AnalysisCostListener listener = new AnalysisCostListener();
			chooseYear.addActionListener(listener);
			chooseMonth.addActionListener(listener);
			refreshButton.addActionListener(listener);
			returnButton.addActionListener(listener);
		}
	}

	@Override
	public void setButtonImage() {
		GUIUtil.setButtonImage(refreshButton, "refresh2.png", "刷新");	
		GUIUtil.setButtonImage(returnButton, "return2.png", "返回");
		GUIUtil.setButtonImage(chooseMonth, "chooseMonth1.png", "选择月份");
		GUIUtil.setButtonImage(chooseYear, "chooseYear2.png", "选择年份");
	}

	@Override
	public void setTextFont(Font font) {
		refreshButton.setFont(font);
		returnButton.setFont(font);
		chooseMonth.setFont(font);
		chooseYear.setFont(font);
	}
	
	public void setButtonColor( Color color )
	{
		GUIUtil.setColor(color, refreshButton,returnButton,chooseMonth,chooseYear);
	}
	/**
	 * @return the returnButton
	 */
	public JButton getReturnButton() {
		return returnButton;
	}
	/**
	 * @param returnButton the returnButton to set
	 */
	public void setReturnButton(JButton returnButton) {
		this.returnButton = returnButton;
	}
	/**
	 * @return the refreshButton
	 */
	public JButton getRefreshButton() {
		return refreshButton;
	}
	/**
	 * @param refreshButton the refreshButton to set
	 */
	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}
	/**
	 * @return the chooseMonth
	 */
	public JButton getChooseMonth() {
		return chooseMonth;
	}
	/**
	 * @param chooseMonth the chooseMonth to set
	 */
	public void setChooseMonth(JButton chooseMonth) {
		this.chooseMonth = chooseMonth;
	}
	/**
	 * @return the chooseYear
	 */
	public JButton getChooseYear() {
		return chooseYear;
	}
	/**
	 * @param chooseYear the chooseYear to set
	 */
	public void setChooseYear(JButton chooseYear) {
		this.chooseYear = chooseYear;
	}
	/**
	 * @return the costChart
	 */
	public JFreeChart getCostChart() {
		return costChart;
	}
	/**
	 * @param costChart the costChart to set
	 */
	public void setCostChart(JFreeChart costChart) {
		this.costChart = costChart;
	}
	/**
	 * @return the costChartPanel
	 */
	public ChartPanel getCostChartPanel() {
		return costChartPanel;
	}
	/**
	 * @param costChartPanel the costChartPanel to set
	 */
	public void setCostChartPanel(ChartPanel costChartPanel) {
		this.costChartPanel = costChartPanel;
	}
	/**
	 * @return the incomeChart
	 */
	public JFreeChart getIncomeChart() {
		return incomeChart;
	}
	/**
	 * @param incomeChart the incomeChart to set
	 */
	public void setIncomeChart(JFreeChart incomeChart) {
		this.incomeChart = incomeChart;
	}
	/**
	 * @return the incomeChartPanel
	 */
	public ChartPanel getIncomeChartPanel() {
		return incomeChartPanel;
	}
	/**
	 * @param incomeChartPanel the incomeChartPanel to set
	 */
	public void setIncomeChartPanel(ChartPanel incomeChartPanel) {
		this.incomeChartPanel = incomeChartPanel;
	}
	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
