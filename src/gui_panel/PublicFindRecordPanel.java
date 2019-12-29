package gui_panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import entity.CostCategory;
import entity.IncomeCategory;
import gui_listener.FindCostRecordListener;
import gui_listener.FindIncomeRecordListener;
import gui_model.CostCategoryComboBoxModel;
import gui_model.FindCostRecordTableModel;
import gui_model.FindIncomeRecordTableModel;
import gui_model.IncomeCategoryComboBoxModel;
import service.FindCostRecordService;
import service.FindIncomeRecordService;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class PublicFindRecordPanel extends GraphPanel{
	private JLabel chooseDate = new JLabel("请选择日期: ");
	private JXDatePicker datepick = new JXDatePicker(new Date());
	private JButton searchButton = new JButton();
	private JButton countButton = new JButton();
	private JLabel chooseCategory = new JLabel("请选择费用类型: ");
	
	private IncomeCategoryComboBoxModel incomeCategoryModel = new IncomeCategoryComboBoxModel();
    private JComboBox<IncomeCategory> incomeCategoryComboBox = new JComboBox<>(incomeCategoryModel);
    private FindIncomeRecordTableModel findIncomeRecordTableModel = new FindIncomeRecordTableModel();
    private JTable findIncomeRecordTable  = new JTable(findIncomeRecordTableModel);
    
    private CostCategoryComboBoxModel costCategoryModel = new CostCategoryComboBoxModel();
    private JComboBox<CostCategory> costCategoryComboBox = new JComboBox<>(costCategoryModel);
    private FindCostRecordTableModel findCostRecordTableModel = new FindCostRecordTableModel();
    private JTable findCostRecordTable  = new JTable(findCostRecordTableModel);
    
    private int flag;
    
    public PublicFindRecordPanel(int flag) {
    	super(MainPanel.BACKGROUND);
		this.flag = flag;
		this.setLayout(new BorderLayout());	//设置布局
		if( flag == 1 )
		{
			JTableHeader head = findIncomeRecordTable.getTableHeader(); // 创建表格标题对象
	        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
	        head.setFont(new Font("宋体", Font.BOLD, 18));// 设置表格字体
	        findIncomeRecordTable.setFont(new Font("微软雅黑", Font.BOLD, 35));// 设置表格字体
			JScrollPane jp = new JScrollPane(findIncomeRecordTable);
			findIncomeRecordTable.setRowHeight(50);
			add(jp,BorderLayout.CENTER);
		}
		else
		{
			JTableHeader head = findCostRecordTable.getTableHeader(); // 创建表格标题对象
	        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
	        head.setFont(new Font("宋体", Font.BOLD, 18));// 设置表格字体
	        findCostRecordTable.setFont(new Font("微软雅黑", Font.BOLD, 35));// 设置表格字体
			JScrollPane jp = new JScrollPane(findCostRecordTable);
			findCostRecordTable.setRowHeight(50);
			add(jp,BorderLayout.CENTER);
		}
		addPanel();
		addListener();
		updateData();
    }
    private void addPanel() {
		add(northPanel(),BorderLayout.NORTH);
		add(southPanel(),BorderLayout.SOUTH);
	}
    private Component southPanel() {
		    UtilPanel southPanel = new UtilPanel(MainPanel.BACKGROUND);
		    JToolBar tb = new JToolBar();
			tb.setFloatable(false);
			setButtonImage();
			setTextFont(new Font("微软雅黑",Font.BOLD,20));
			tb.add(searchButton);
			tb.add(countButton);
			
			southPanel.add(tb,BorderLayout.SOUTH);
			return southPanel;
	}

	private UtilPanel northPanel() {
		if(flag == 1) {
		    UtilPanel northPanel = new UtilPanel(MainPanel.BACKGROUND);
			northPanel.setLayout(new GridLayout(1,4));
			chooseDate.setFont(new Font("微软雅黑",Font.BOLD,25));
			datepick.setFont(new Font("微软雅黑",Font.BOLD,25));
			chooseCategory.setFont(new Font("微软雅黑",Font.BOLD,25));
			incomeCategoryComboBox.setFont(new Font("微软雅黑",Font.BOLD,25));
			northPanel.add(chooseDate,BorderLayout.NORTH);
			northPanel.add(datepick,BorderLayout.NORTH);
			northPanel.add(chooseCategory,BorderLayout.NORTH);
			northPanel.add(incomeCategoryComboBox,BorderLayout.NORTH);
			return northPanel;
		}
		else {
			UtilPanel northPanel = new UtilPanel(MainPanel.BACKGROUND);
			northPanel.setLayout(new GridLayout(1,4));
			chooseDate.setFont(new Font("微软雅黑",Font.BOLD,25));
			datepick.setFont(new Font("微软雅黑",Font.BOLD,25));
			chooseCategory.setFont(new Font("微软雅黑",Font.BOLD,25));
			costCategoryComboBox.setFont(new Font("微软雅黑",Font.BOLD,25));
			northPanel.add(chooseDate,BorderLayout.NORTH);
			northPanel.add(datepick,BorderLayout.NORTH);
			northPanel.add(chooseCategory,BorderLayout.NORTH);
			northPanel.add(costCategoryComboBox,BorderLayout.NORTH);
			return northPanel;
		}
	}
	
	@Override
	public void setButtonImage() {
		GUIUtil.setButtonImage(searchButton, "search.png", "查询记录");
		GUIUtil.setButtonImage(countButton, "findMaxCost.png", "查询最大收入");
		GUIUtil.setColor(ColorUtil.blueColor, searchButton,countButton);
	}

	@Override
	public void setTextFont(Font font) {
		searchButton.setFont(font);
		countButton.setFont(font);
	}
	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		if(flag == 1) {
			findIncomeRecordTableModel.setIncomeRecordList(new FindIncomeRecordService().list());
			findIncomeRecordTable.updateUI();
			findIncomeRecordTable.getSelectionModel().setSelectionInterval(0, 0);
			incomeCategoryModel.updateJcombobox();
			incomeCategoryComboBox.updateUI();
		}
		else {
			findCostRecordTableModel.setCostRecordList(new FindCostRecordService().list());
			findCostRecordTable.updateUI();
			findCostRecordTable.getSelectionModel().setSelectionInterval(0, 0);
			costCategoryModel.updateJcombobox();
			costCategoryComboBox.updateUI();
		}
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		if(flag == 1) {
			FindIncomeRecordListener listener = new FindIncomeRecordListener();
			searchButton.addActionListener(listener);
			countButton.addActionListener(listener);
		}
		else {
			FindCostRecordListener listener = new FindCostRecordListener();
			searchButton.addActionListener(listener);
			countButton.addActionListener(listener);
		}
	}
	/**
	 * @return the chooseDate
	 */
	public JLabel getChooseDate() {
		return chooseDate;
	}
	/**
	 * @param chooseDate the chooseDate to set
	 */
	public void setChooseDate(JLabel chooseDate) {
		this.chooseDate = chooseDate;
	}
	/**
	 * @return the datepick
	 */
	public JXDatePicker getDatepick() {
		return datepick;
	}
	/**
	 * @param datepick the datepick to set
	 */
	public void setDatepick(JXDatePicker datepick) {
		this.datepick = datepick;
	}
	/**
	 * @return the searchButton
	 */
	public JButton getSearchButton() {
		return searchButton;
	}
	/**
	 * @param searchButton the searchButton to set
	 */
	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}
	/**
	 * @return the countButton
	 */
	public JButton getCountButton() {
		return countButton;
	}
	/**
	 * @param countButton the countButton to set
	 */
	public void setCountButton(JButton countButton) {
		this.countButton = countButton;
	}
	/**
	 * @return the chooseCategory
	 */
	public JLabel getChooseCategory() {
		return chooseCategory;
	}
	/**
	 * @param chooseCategory the chooseCategory to set
	 */
	public void setChooseCategory(JLabel chooseCategory) {
		this.chooseCategory = chooseCategory;
	}
	/**
	 * @return the incomeCategoryModel
	 */
	public IncomeCategoryComboBoxModel getIncomeCategoryModel() {
		return incomeCategoryModel;
	}
	/**
	 * @param incomeCategoryModel the incomeCategoryModel to set
	 */
	public void setIncomeCategoryModel(IncomeCategoryComboBoxModel incomeCategoryModel) {
		this.incomeCategoryModel = incomeCategoryModel;
	}
	/**
	 * @return the incomeCategoryComboBox
	 */
	public JComboBox<IncomeCategory> getIncomeCategoryComboBox() {
		return incomeCategoryComboBox;
	}
	/**
	 * @param incomeCategoryComboBox the incomeCategoryComboBox to set
	 */
	public void setIncomeCategoryComboBox(JComboBox<IncomeCategory> incomeCategoryComboBox) {
		this.incomeCategoryComboBox = incomeCategoryComboBox;
	}
	/**
	 * @return the findIncomeRecordTableModel
	 */
	public FindIncomeRecordTableModel getFindIncomeRecordTableModel() {
		return findIncomeRecordTableModel;
	}
	/**
	 * @param findIncomeRecordTableModel the findIncomeRecordTableModel to set
	 */
	public void setFindIncomeRecordTableModel(FindIncomeRecordTableModel findIncomeRecordTableModel) {
		this.findIncomeRecordTableModel = findIncomeRecordTableModel;
	}
	/**
	 * @return the findIncomeRecordTable
	 */
	public JTable getFindIncomeRecordTable() {
		return findIncomeRecordTable;
	}
	/**
	 * @param findIncomeRecordTable the findIncomeRecordTable to set
	 */
	public void setFindIncomeRecordTable(JTable findIncomeRecordTable) {
		this.findIncomeRecordTable = findIncomeRecordTable;
	}
	/**
	 * @return the costCategoryModel
	 */
	public CostCategoryComboBoxModel getCostCategoryModel() {
		return costCategoryModel;
	}
	/**
	 * @param costCategoryModel the costCategoryModel to set
	 */
	public void setCostCategoryModel(CostCategoryComboBoxModel costCategoryModel) {
		this.costCategoryModel = costCategoryModel;
	}
	/**
	 * @return the costCategoryComboBox
	 */
	public JComboBox<CostCategory> getCostCategoryComboBox() {
		return costCategoryComboBox;
	}
	/**
	 * @param costCategoryComboBox the costCategoryComboBox to set
	 */
	public void setCostCategoryComboBox(JComboBox<CostCategory> costCategoryComboBox) {
		this.costCategoryComboBox = costCategoryComboBox;
	}
	/**
	 * @return the findCostRecordTableModel
	 */
	public FindCostRecordTableModel getFindCostRecordTableModel() {
		return findCostRecordTableModel;
	}
	/**
	 * @param findCostRecordTableModel the findCostRecordTableModel to set
	 */
	public void setFindCostRecordTableModel(FindCostRecordTableModel findCostRecordTableModel) {
		this.findCostRecordTableModel = findCostRecordTableModel;
	}
	/**
	 * @return the findCostRecordTable
	 */
	public JTable getFindCostRecordTable() {
		return findCostRecordTable;
	}
	/**
	 * @param findCostRecordTable the findCostRecordTable to set
	 */
	public void setFindCostRecordTable(JTable findCostRecordTable) {
		this.findCostRecordTable = findCostRecordTable;
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
