package gui_panel;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.JTableHeader;

import gui_listener.MonthCostTableListener;
import gui_model.MonthCostTableModel;
import service.MonthCostTableService;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class MonthCostTablePanel extends GraphPanel{

	private String[] headers = { "消费日期", "消费类型", "消费金额", "消费事件备注" };

	private MonthCostTableModel monthCostTableModel = new MonthCostTableModel();
	private JTable showInfoTable = new JTable(monthCostTableModel);
	private JButton chooseYear = new JButton();
	private JButton inputMonth = new JButton();
	private JButton showSearchTable = new JButton();
	private static MonthCostTablePanel instance = new MonthCostTablePanel();

	public MonthCostTablePanel() {
		super(MainPanel.BACKGROUND);
		this.setLayout(new BorderLayout());
		
        JTableHeader head = showInfoTable.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(new Font("宋体", Font.BOLD, 18));// 设置表格字体
        showInfoTable.setFont(new Font("微软雅黑", Font.BOLD, 35));
		JScrollPane jp = new JScrollPane(showInfoTable);
		showInfoTable.setRowHeight(50);
		
		add(jp,BorderLayout.CENTER);
		add(southPanel(),BorderLayout.SOUTH);
		addListener();
		updateData();
	}
	
	public UtilPanel southPanel()
	{
		UtilPanel p = new UtilPanel(MainPanel.BACKGROUND);
		JToolBar tb = new JToolBar();
		setButtonImage();
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		tb.add(chooseYear);
		tb.add(inputMonth);
		tb.add(showSearchTable);
		
		tb.setFloatable(false);
		p.add(tb,BorderLayout.SOUTH);
		return p;
	}
	
	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		monthCostTableModel.setCostRecordList(new MonthCostTableService().list());
		showInfoTable.updateUI();
		showInfoTable.getSelectionModel().setSelectionInterval(0, 0);
		if( monthCostTableModel.getCostRecordList().size() == 0 )
			showSearchTable.setEnabled(false);
		else
			showSearchTable.setEnabled(true);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		MonthCostTableListener listener = new MonthCostTableListener();
		chooseYear.addActionListener(listener);
		inputMonth.addActionListener(listener);
		showSearchTable.addActionListener(listener);
	}
	
	

	/**
	 * @return the headers
	 */
	public String[] getHeaders() {
		return headers;
	}

	/**
	 * @return the monthCostTableModel
	 */
	public MonthCostTableModel getMonthCostTableModel() {
		return monthCostTableModel;
	}

	/**
	 * @return the showInfoTable
	 */
	public JTable getShowInfoTable() {
		return showInfoTable;
	}

	/**
	 * @return the inputMonth
	 */
	public JButton getInputMonth() {
		return inputMonth;
	}
	
	/**
	 * @return the chooseYear
	 */
	public JButton getChooseYear() {
		return chooseYear;
	}

	/**
	 * @return the showSearchTable
	 */
	public JButton getShowSearchTable() {
		return showSearchTable;
	}

	/**
	 * @return the instance
	 */
	public static MonthCostTablePanel getInstance() {
		return instance;
	}

	@Override
	public void setButtonImage() {
		GUIUtil.setButtonImage(inputMonth, "chooseMonth1.png", "选择月份");
		GUIUtil.setButtonImage(showSearchTable, "showMonthCostTable.png", "显示报表");
		GUIUtil.setButtonImage(chooseYear, "chooseYear2.png", "输入年份");
		chooseYear.setForeground(ColorUtil.blueColor);
		inputMonth.setForeground(ColorUtil.blueColor);
		showSearchTable.setForeground(ColorUtil.blueColor);
	}

	@Override
	public void setTextFont(Font font) {
		inputMonth.setFont(font);
		showSearchTable.setFont(font);
		chooseYear.setFont(font);
	}

}
