package gui_panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import entity.Category;
import entity.CostCategory;
import gui_listener.CostPlanListener;
import gui_model.CostCategoryComboBoxModel;
import gui_model.CostPlanTableModel;
import service.CostPlanService;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class CostPlanPanel extends GraphPanel{

	private String[] headers = { "��������", "��������", "��Լ���ѽ��", "�����¼���ע" };
	private JTextField inputCost = new JTextField(10);
	private JTextField inputComment = new JTextField(10);
	private JXDatePicker datepick = new JXDatePicker(new Date());
	private static CostCategoryComboBoxModel model = new CostCategoryComboBoxModel();
	private JComboBox<CostCategory> categoryCombobox = new JComboBox<>(model);
	private CostPlanTableModel costPlanTableModel = new CostPlanTableModel();
	private JTable costPlanTable = new JTable(costPlanTableModel);
	private JButton addCostPlan = new JButton();
	private JButton deleteCostPlan = new JButton();
	private JButton changeCostPlan = new JButton();
	private static CostPlanPanel instance = new CostPlanPanel();
	
	public CostPlanPanel() {
		super(MainPanel.BACKGROUND);
		this.setLayout(new BorderLayout());
        JTableHeader head = costPlanTable.getTableHeader(); //�������������
        head.setPreferredSize(new Dimension(head.getWidth(), 35));//���ñ�ͷ��С
        head.setFont(new Font("����", Font.BOLD, 18));// ���ñ������
        costPlanTable.setFont(new Font("΢���ź�", Font.BOLD, 35));// ���ñ������
		JScrollPane jp = new JScrollPane(costPlanTable);
		costPlanTable.setRowHeight(50);
		addPanel();
		add(jp,BorderLayout.CENTER);
		addListener();
	}
	
	private void addPanel() {
		UtilPanel southPanel = new UtilPanel(MainPanel.BACKGROUND);
		JToolBar tb = new JToolBar();
		
		setButtonImage();
		setTextFont(new Font("΢���ź�",Font.BOLD,20));
		
		tb.add(addCostPlan);
		tb.add(deleteCostPlan);
		tb.add(changeCostPlan);
		tb.setFloatable(false);
		southPanel.add(tb,BorderLayout.SOUTH);
		add(southPanel,BorderLayout.SOUTH);
		add(northPanel(),BorderLayout.NORTH);
		
	}

	private UtilPanel northPanel() {
		
		UtilPanel northPanel = new UtilPanel(MainPanel.BACKGROUND);
		
		JLabel chooseDate = new JLabel("��ѡ������: ");
		JLabel chooseCategory = new JLabel("��ѡ���������: ");
		JLabel showCommentText = new JLabel("�����뱸ע: ");
		JLabel showCostText = new JLabel("���������ѽ��: ");
		
		northPanel.setLayout(new GridLayout(2,4));
		chooseDate.setFont(new Font("΢���ź�",Font.BOLD,25));
		datepick.setFont(new Font("΢���ź�",Font.BOLD,25));
		chooseCategory.setFont(new Font("΢���ź�",Font.BOLD,25));
		categoryCombobox.setFont(new Font("΢���ź�",Font.BOLD,25));
		showCostText.setFont(new Font("΢���ź�",Font.BOLD,25));
		showCommentText.setFont(new Font("΢���ź�",Font.BOLD,25));
		inputCost.setFont(new Font("΢���ź�",Font.BOLD,25));
		inputComment.setFont(new Font("΢���ź�",Font.BOLD,25));
		
		northPanel.add(chooseDate,BorderLayout.NORTH);
		northPanel.add(datepick,BorderLayout.NORTH);
		northPanel.add(chooseCategory,BorderLayout.NORTH);
		northPanel.add(categoryCombobox,BorderLayout.NORTH);
		northPanel.add(showCostText,BorderLayout.NORTH);
		northPanel.add(inputCost,BorderLayout.NORTH);
		northPanel.add(showCommentText,BorderLayout.NORTH);
		northPanel.add(inputComment,BorderLayout.NORTH);
		return northPanel;
	}
	
	@Override
	public void updateData() {
		costPlanTableModel.setCostPlanList(new CostPlanService().list());
		model.updateJcombobox();
		costPlanTable.updateUI();
		categoryCombobox.updateUI();
		costPlanTable.getSelectionModel().setSelectionInterval(0, 0);
		inputCost.setText("");
		inputComment.setText("");
        if( 0 == costPlanTableModel.getCostPlanList().size() ){
        	changeCostPlan.setEnabled(false);
        	deleteCostPlan.setEnabled(false);
        }
        else{
        	changeCostPlan.setEnabled(true);
        	deleteCostPlan.setEnabled(true);
        }
	}

	@Override
	public void addListener() {
		CostPlanListener listener = new CostPlanListener();
		addCostPlan.addActionListener(listener);
		deleteCostPlan.addActionListener(listener);
		changeCostPlan.addActionListener(listener);
	}

	public static CostPlanPanel getInstance() {
		return instance;
	}

	public String[] getHeaders() {
		return headers;
	}

	public JTextField getInputCost() {
		return inputCost;
	}

	public JTextField getInputComment() {
		return inputComment;
	}

	public JXDatePicker getDatepick() {
		return datepick;
	}

	public CostCategoryComboBoxModel getModel() {
		return model;
	}

	public JComboBox<CostCategory> getCategoryCombobox() {
		return categoryCombobox;
	}

	public CostPlanTableModel getCostPlanTableModel() {
		return costPlanTableModel;
	}

	public JTable getCostPlanTable() {
		return costPlanTable;
	}

	public JButton getAddCostPlan() {
		return addCostPlan;
	}

	public JButton getDeleteCostPlan() {
		return deleteCostPlan;
	}

	public JButton getChangeCostPlan() {
		return changeCostPlan;
	}

	public Category getCostSelectedCategory() {
		return (Category) categoryCombobox.getSelectedItem();
	}

	@Override
	public void setButtonImage() {
		GUIUtil.setButtonImage(addCostPlan, "add1.png", "���Ӽƻ�");
		GUIUtil.setButtonImage(deleteCostPlan, "sub1.png", "ɾ���ƻ�");
		GUIUtil.setButtonImage(changeCostPlan, "refresh.png", "�޸ļƻ�");
		addCostPlan.setForeground(ColorUtil.blueColor);
		deleteCostPlan.setForeground(ColorUtil.blueColor);
		changeCostPlan.setForeground(ColorUtil.blueColor);
	}

	@Override
	public void setTextFont(Font font) {
		addCostPlan.setFont(font);
		deleteCostPlan.setFont(font);
		changeCostPlan.setFont(font);
	}
	
}
