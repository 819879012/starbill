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
import gui_listener.CostDetailsListener;
import gui_listener.IncomeDetailsListener;
import gui_model.CostDetailsTableModel;
import gui_model.IncomeDetailsTableModel;
import service.CostDetailsService;
import service.IncomeDetailsService;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class PublicDetailsPanel extends GraphPanel{
	
	private CostDetailsTableModel costDetailsModel = new CostDetailsTableModel();
	private JTable costDetailsTable = new JTable(costDetailsModel);
	private IncomeDetailsTableModel incomeDetailsmodel = new IncomeDetailsTableModel();
	private JTable incomeDetailsTable = new JTable(incomeDetailsmodel);
	
	private JButton changeButton = new JButton();
	private JButton deleteButton = new JButton();
	private JButton refreshButton = new JButton();
	private int flag;
	
	public PublicDetailsPanel(int flag) {
		super(MainPanel.BACKGROUND);
		this.flag = flag;
		this.setLayout(new BorderLayout());	//设置布局
		if( flag == TagXml.incomeDetailsPanel )
		{
			JTableHeader head = incomeDetailsTable.getTableHeader(); // 创建表格标题对象
	        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
	        head.setFont(new Font("宋体", Font.BOLD, 18));// 设置表头字体
	        incomeDetailsTable.setFont(new Font("微软雅黑", Font.BOLD, 35));// 设置表格字体
			JScrollPane jp = new JScrollPane(incomeDetailsTable);
			incomeDetailsTable.setRowHeight(50);
			add(jp,BorderLayout.CENTER);
		}
		else
		{
			JTableHeader head = costDetailsTable.getTableHeader(); // 创建表格标题对象
	        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
	        head.setFont(new Font("宋体", Font.BOLD, 18));// 设置表头字体
	        costDetailsTable.setFont(new Font("微软雅黑", Font.BOLD, 35));// 设置表格字体
			JScrollPane jp = new JScrollPane(costDetailsTable);
			costDetailsTable.setRowHeight(50);
			add(jp,BorderLayout.CENTER);
		}
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
		tb.add(changeButton);
		tb.add(deleteButton);
		tb.add(refreshButton);
		tb.setFloatable(false);
		p.add(tb,BorderLayout.SOUTH);
		return p;
	}
	
	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		if( flag == TagXml.incomeDetailsPanel )
		{
			incomeDetailsmodel.setIncomeRecordList(new IncomeDetailsService().list());
			incomeDetailsTable.updateUI();
			incomeDetailsTable.getSelectionModel().setSelectionInterval(0, 0);
		}
		else if( flag == TagXml.costDetailsPanel )
		{
			costDetailsModel.setCostRecordList(new CostDetailsService().list());
			costDetailsTable.updateUI();
			costDetailsTable.getSelectionModel().setSelectionInterval(0, 0);
		}
	}
	
	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		if( flag == TagXml.incomeDetailsPanel )
		{
			IncomeDetailsListener listener = new IncomeDetailsListener();
			changeButton.addActionListener(listener);
			deleteButton.addActionListener(listener);
			refreshButton.addActionListener(listener);
		}
		else
		{
			CostDetailsListener listener = new CostDetailsListener();
			changeButton.addActionListener(listener);
			deleteButton.addActionListener(listener);
			refreshButton.addActionListener(listener);
		}
	}
	
	@Override
	public void setButtonImage() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 鏄熸槦璁拌处绯荤粺
		GUIUtil.setButtonImage(changeButton, "add1.png", "修改记录");
		GUIUtil.setButtonImage(deleteButton, "sub1.png", "删除记录");
		GUIUtil.setButtonImage(refreshButton, "refresh.png", "刷新记录");
		changeButton.setForeground(ColorUtil.blueColor);
		deleteButton.setForeground(ColorUtil.blueColor);
		refreshButton.setForeground(ColorUtil.blueColor);
	}
	@Override
	public void setTextFont(Font font) {
		// TODO Auto-generated method stub
		changeButton.setFont(font);
		deleteButton.setFont(font);
		refreshButton.setFont(font);
	}
	/**
	 * @return the costDetailsModel
	 */
	public CostDetailsTableModel getCostDetailsModel() {
		return costDetailsModel;
	}
	/**
	 * @param costDetailsModel the costDetailsModel to set
	 */
	public void setCostDetailsModel(CostDetailsTableModel costDetailsModel) {
		this.costDetailsModel = costDetailsModel;
	}
	/**
	 * @return the costDetailsTable
	 */
	public JTable getCostDetailsTable() {
		return costDetailsTable;
	}
	/**
	 * @param costDetailsTable the costDetailsTable to set
	 */
	public void setCostDetailsTable(JTable costDetailsTable) {
		this.costDetailsTable = costDetailsTable;
	}
	/**
	 * @return the incomeDetailsmodel
	 */
	public IncomeDetailsTableModel getIncomeDetailsmodel() {
		return incomeDetailsmodel;
	}
	/**
	 * @param incomeDetailsmodel the incomeDetailsmodel to set
	 */
	public void setIncomeDetailsmodel(IncomeDetailsTableModel incomeDetailsmodel) {
		this.incomeDetailsmodel = incomeDetailsmodel;
	}
	/**
	 * @return the incomeDetailsTable
	 */
	public JTable getIncomeDetailsTable() {
		return incomeDetailsTable;
	}
	/**
	 * @param incomeDetailsTable the incomeDetailsTable to set
	 */
	public void setIncomeDetailsTable(JTable incomeDetailsTable) {
		this.incomeDetailsTable = incomeDetailsTable;
	}
	/**
	 * @return the changeButton
	 */
	public JButton getChangeButton() {
		return changeButton;
	}
	/**
	 * @param changeButton the changeButton to set
	 */
	public void setChangeButton(JButton changeButton) {
		this.changeButton = changeButton;
	}
	/**
	 * @return the deleteButton
	 */
	public JButton getDeleteButton() {
		return deleteButton;
	}
	/**
	 * @param deleteButton the deleteButton to set
	 */
	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
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
