package gui_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.JTableHeader;

import constant.TagXml;
import gui_listener.CostCategoryListener;
import gui_listener.IncomeCategoryListener;
import gui_model.CostCategoryTableModel;
import gui_model.IncomeCategoryTableModel;
import service.CostCategoryService;
import service.IncomeCategoryService;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class PublicCategoryPanel extends GraphPanel{

	private JButton addCategory = new JButton();
	private JButton deleteCategory = new JButton();
	private JButton changeCategory = new JButton();
	private IncomeCategoryTableModel incomeCategoryTableModel = new IncomeCategoryTableModel();
	private JTable incomeCategoryTabel = new JTable(incomeCategoryTableModel);
	private static CostCategoryTableModel costCategoryTableModel = new CostCategoryTableModel();
	private JTable costCategoryTabel = new JTable(costCategoryTableModel);
	private int flag;
	
	public PublicCategoryPanel(int flag) {
		super(MainPanel.BACKGROUND);
		this.flag = flag;
		this.setLayout(new BorderLayout());	//设置布局
		if( flag == TagXml.incomeCategoryPanel )
		{
	        JTableHeader head = incomeCategoryTabel.getTableHeader(); // 创建表格标题对象
	        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
	        head.setFont(new Font("宋体", Font.BOLD, 18));// 设置表格字体
	        incomeCategoryTabel.setFont(new Font("微软雅黑", Font.BOLD, 35));// 设置表格字体
	        JScrollPane jp = new JScrollPane(incomeCategoryTabel);//设置滚动
	        incomeCategoryTabel.setRowHeight(50);
	        add(jp,BorderLayout.CENTER);
		}
		else
		{
	        JTableHeader head = costCategoryTabel.getTableHeader(); // 创建表格标题对象
	        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
	        head.setFont(new Font("宋体", Font.BOLD, 18));// 设置表格字体
	        costCategoryTabel.setFont(new Font("微软雅黑", Font.BOLD, 35));// 设置表格字体
	        JScrollPane jp = new JScrollPane(costCategoryTabel);//设置滚动
	        costCategoryTabel.setRowHeight(50);
	        add(jp,BorderLayout.CENTER);
		}
		addPanel();
		addListener();
	}
	
	private void addPanel() {
		UtilPanel southPanel = new UtilPanel(MainPanel.BACKGROUND);
		JToolBar tb = new JToolBar();
		setButtonImage();
		setButtonColor( ColorUtil.blueColor );
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		tb.add(addCategory);
		tb.add(deleteCategory);
		tb.add(changeCategory);
		tb.setFloatable(false);
		southPanel.add(tb,BorderLayout.SOUTH);
		add(southPanel,BorderLayout.SOUTH);
	}
	
	public void setButtonColor( Color color )
	{
		addCategory.setForeground(color);
		deleteCategory.setForeground(color);
		changeCategory.setForeground(color);
	}
	
	@Override
	public void updateData() {
		
		if( flag == TagXml.incomeCategoryPanel )
		{
			incomeCategoryTableModel.setIncomeCategoryList(new IncomeCategoryService().list());
			incomeCategoryTabel.updateUI();
			incomeCategoryTabel.getSelectionModel().setSelectionInterval(0, 0);
	        if( 0 == incomeCategoryTableModel.getIncomeCategoryList().size() ){
	        	changeCategory.setEnabled(false);
	        	deleteCategory.setEnabled(false);
	        }
	        else{
	        	changeCategory.setEnabled(true);
	        	deleteCategory.setEnabled(true);
	        }
		}
		else if( flag == TagXml.costCategoryPanel )
		{
			costCategoryTableModel.setCostCategoryList(new CostCategoryService().list());
			costCategoryTabel.updateUI();
			costCategoryTabel.getSelectionModel().setSelectionInterval(0, 0);
	        if( 0 == costCategoryTableModel.getCostCategoryList().size() ){
	        	changeCategory.setEnabled(false);
	        	deleteCategory.setEnabled(false);
	        }
	        else{
	        	changeCategory.setEnabled(true);
	        	deleteCategory.setEnabled(true);
	        }
		}
	}

	@Override
	public void addListener() {
		if( flag == TagXml.incomeCategoryPanel )
		{
			IncomeCategoryListener listener = new IncomeCategoryListener();
			addCategory.addActionListener(listener);
			deleteCategory.addActionListener(listener);
			changeCategory.addActionListener(listener);
		}
		else
		{
			CostCategoryListener listener = new CostCategoryListener();
			addCategory.addActionListener(listener);
			deleteCategory.addActionListener(listener);
			changeCategory.addActionListener(listener);
		}
	}

	@Override
	public void setButtonImage() {
		addCategory.setForeground(ColorUtil.blueColor);
		deleteCategory.setForeground(ColorUtil.blueColor);
		changeCategory.setForeground(ColorUtil.blueColor);
		GUIUtil.setButtonImage(addCategory, "add1.png", "增加分类");
		GUIUtil.setButtonImage(deleteCategory, "sub1.png", "删除分类");
		GUIUtil.setButtonImage(changeCategory, "refresh.png", "修改分类");
	}

	@Override
	public void setTextFont(Font font) {
		addCategory.setFont(font);
		deleteCategory.setFont(font);
		changeCategory.setFont(font);
	}

	public JButton getAddCategory() {
		return addCategory;
	}

	public void setAddCategory(JButton addCategory) {
		this.addCategory = addCategory;
	}

	public JButton getDeleteCategory() {
		return deleteCategory;
	}

	public void setDeleteCategory(JButton deleteCategory) {
		this.deleteCategory = deleteCategory;
	}

	public JButton getChangeCategory() {
		return changeCategory;
	}

	public void setChangeCategory(JButton changeCategory) {
		this.changeCategory = changeCategory;
	}

	public IncomeCategoryTableModel getIncomeCategoryTableModel() {
		return incomeCategoryTableModel;
	}

	public void setIncomeCategoryTableModel(IncomeCategoryTableModel incomeCategoryTableModel) {
		this.incomeCategoryTableModel = incomeCategoryTableModel;
	}

	public JTable getIncomeCategoryTabel() {
		return incomeCategoryTabel;
	}

	public void setIncomeCategoryTabel(JTable incomeCategoryTabel) {
		this.incomeCategoryTabel = incomeCategoryTabel;
	}

	public CostCategoryTableModel getCostCategoryTableModel() {
		return costCategoryTableModel;
	}

	public static void setCostCategoryTableModel(CostCategoryTableModel costCategoryTableModel) {
		PublicCategoryPanel.costCategoryTableModel = costCategoryTableModel;
	}

	public JTable getCostCategoryTabel() {
		return costCategoryTabel;
	}

	public void setCostCategoryTabel(JTable costCategoryTabel) {
		this.costCategoryTabel = costCategoryTabel;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}
