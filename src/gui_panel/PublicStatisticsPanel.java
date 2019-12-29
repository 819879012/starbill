package gui_panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.JTableHeader;

import constant.TagXml;
import gui_listener.StatisticsCostListener;
import gui_listener.StatisticsEarnListener;
import gui_model.StatisticsCostTableModel;
import gui_model.StatisticsEarnTableModel;
import service.StatisticsCostService;
import service.StatisticsEarnService;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class PublicStatisticsPanel extends GraphPanel{
	
	private StatisticsCostTableModel statisticsCostTableModel = new StatisticsCostTableModel();
	private JTable statisticsCostTable = new JTable(statisticsCostTableModel);
	private StatisticsEarnTableModel statisticsEarnTableModel = new StatisticsEarnTableModel();
	private JTable statisticsEarnTable = new JTable(statisticsEarnTableModel);
	
	private JButton chooseYear = new JButton();
	private JButton chooseMonth = new JButton();
	private JButton chooseCategory = new JButton();
	private JButton refreshButton = new JButton();
	private JButton returnButton = new JButton();
	private int flag;
	public PublicStatisticsPanel(int flag) {
		super(MainPanel.BACKGROUND);
		this.setLayout(new BorderLayout());
		this.flag = flag;
		if(flag == TagXml.statisticsEarnPanel) {
			JTableHeader head = statisticsEarnTable.getTableHeader(); // 创建表格标题对象
		    head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
		    head.setFont(new Font("宋体", Font.BOLD, 18));// 设置表格字体
		    statisticsEarnTable.setFont(new Font("微软雅黑", Font.BOLD, 35));
	        JScrollPane jp = new JScrollPane(statisticsEarnTable);//设置滚动
	        statisticsEarnTable.setRowHeight(50);
	        add(jp,BorderLayout.CENTER);
		}
		else {
			JTableHeader head = statisticsCostTable.getTableHeader(); // 创建表格标题对象
		    head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
		    head.setFont(new Font("宋体", Font.BOLD, 18));// 设置表格字体
		    statisticsCostTable.setFont(new Font("微软雅黑", Font.BOLD, 35));
	        JScrollPane jp = new JScrollPane(statisticsCostTable);//设置滚动
	        statisticsCostTable.setRowHeight(50);
	        add(jp,BorderLayout.CENTER);
		}
		addPanel();
		addListener();
		updateData();
	}
	
	public void addPanel() {
			UtilPanel southPanel = new UtilPanel(MainPanel.BACKGROUND);
			JToolBar tb = new JToolBar();
			setButtonImage();
			setTextFont(new Font("微软雅黑",Font.BOLD,20));
			tb.add(chooseYear);
			tb.add(chooseMonth);
			tb.add(chooseCategory);
			tb.add(refreshButton);
			tb.add(returnButton);
			tb.setFloatable(false);
			southPanel.add(tb,BorderLayout.SOUTH);
			add(southPanel,BorderLayout.SOUTH);
	}
	@Override
	public void updateData() {
		if(flag == TagXml.statisticsEarnPanel) {
			statisticsEarnTableModel.setIncomeRecordList(new StatisticsEarnService().list());
			statisticsEarnTable.updateUI();
			statisticsEarnTable.getSelectionModel().setSelectionInterval(0, 0);
		}
		else {
			statisticsCostTableModel.setCostRecordList(new StatisticsCostService().list());
			statisticsCostTable.updateUI();
			statisticsCostTable.getSelectionModel().setSelectionInterval(0, 0);
		}
	}

	@Override
	public void addListener() {
		if(flag == TagXml.statisticsEarnPanel) {
			StatisticsEarnListener listener = new StatisticsEarnListener();
			chooseYear.addActionListener(listener);
			chooseMonth.addActionListener(listener);
			chooseCategory.addActionListener(listener);
			returnButton.addActionListener(listener);
			refreshButton.addActionListener(listener);
		}
		else {
			StatisticsCostListener listener = new StatisticsCostListener();
			chooseYear.addActionListener(listener);
			chooseMonth.addActionListener(listener);
			chooseCategory.addActionListener(listener);
			returnButton.addActionListener(listener);
			refreshButton.addActionListener(listener);
		}
	}

	@Override
	public void setButtonImage() {
		GUIUtil.setButtonImage(chooseYear, "chooseYear2.png", "选择年份");
		GUIUtil.setButtonImage(chooseMonth, "chooseMonth1.png", "选择月份");
		GUIUtil.setButtonImage(chooseCategory, "category.png", "选择收入分类");
		GUIUtil.setButtonImage(refreshButton, "refresh2.png", "刷新");
		GUIUtil.setButtonImage(returnButton, "return2.png", "返回");
		refreshButton.setForeground(ColorUtil.blueColor);
		chooseYear.setForeground(ColorUtil.blueColor);
		chooseMonth.setForeground(ColorUtil.blueColor);
		returnButton.setForeground(ColorUtil.blueColor);
		chooseCategory.setForeground(ColorUtil.blueColor);
	}

	@Override
	public void setTextFont(Font font) {
		chooseYear.setFont(font);
		returnButton.setFont(font);
		chooseMonth.setFont(font);
		chooseCategory.setFont(font);
		refreshButton.setFont(font);
	}

	/**
	 * @return the statisticsCostTableModel
	 */
	public StatisticsCostTableModel getStatisticsCostTableModel() {
		return statisticsCostTableModel;
	}

	/**
	 * @param statisticsCostTableModel the statisticsCostTableModel to set
	 */
	public void setStatisticsCostTableModel(StatisticsCostTableModel statisticsCostTableModel) {
		this.statisticsCostTableModel = statisticsCostTableModel;
	}

	/**
	 * @return the statisticsCostTable
	 */
	public JTable getStatisticsCostTable() {
		return statisticsCostTable;
	}

	/**
	 * @param statisticsCostTable the statisticsCostTable to set
	 */
	public void setStatisticsCostTable(JTable statisticsCostTable) {
		this.statisticsCostTable = statisticsCostTable;
	}

	/**
	 * @return the statisticsEarnTableModel
	 */
	public StatisticsEarnTableModel getStatisticsEarnTableModel() {
		return statisticsEarnTableModel;
	}

	/**
	 * @param statisticsEarnTableModel the statisticsEarnTableModel to set
	 */
	public void setStatisticsEarnTableModel(StatisticsEarnTableModel statisticsEarnTableModel) {
		this.statisticsEarnTableModel = statisticsEarnTableModel;
	}

	/**
	 * @return the statisticsEarnTable
	 */
	public JTable getStatisticsEarnTable() {
		return statisticsEarnTable;
	}

	/**
	 * @param statisticsEarnTable the statisticsEarnTable to set
	 */
	public void setStatisticsEarnTable(JTable statisticsEarnTable) {
		this.statisticsEarnTable = statisticsEarnTable;
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
	 * @return the chooseCategory
	 */
	public JButton getChooseCategory() {
		return chooseCategory;
	}

	/**
	 * @param chooseCategory the chooseCategory to set
	 */
	public void setChooseCategory(JButton chooseCategory) {
		this.chooseCategory = chooseCategory;
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
