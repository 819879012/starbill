package gui_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import org.jdesktop.swingx.JXDatePicker;
import entity.CostCategory;
import entity.IncomeCategory;
import gui_listener.CostRecordListener;
import gui_listener.IncomeRecordListener;
import gui_model.CostCategoryComboBoxModel;
import gui_model.IncomeCategoryComboBoxModel;
import service.CostCategoryService;
import service.IncomeCategoryService;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class CashFlowPanel extends GraphPanel{
	
	private JLabel showText = new JLabel();
	private JLabel placeholder = new JLabel();
	private JLabel chooseDate = new JLabel("选择日期:");
	private JLabel chooseCategory = new JLabel("费用类型:");
	private JLabel setMoneyText = new JLabel("金额:");
	private JLabel comment = new JLabel("备注:");
	private IncomeCategoryComboBoxModel incomeCategoryModel = new IncomeCategoryComboBoxModel();
	private CostCategoryComboBoxModel costCategoryModel = new CostCategoryComboBoxModel();
	private JComboBox<IncomeCategory> incomeCtgyJcomboBox = new JComboBox<>(incomeCategoryModel);
	private JComboBox<CostCategory> costCtgyJcomboBox = new JComboBox<>(costCategoryModel);
    private JXDatePicker datepick = new JXDatePicker(new Date());
    private JTextField inputMoney = new JTextField();
    private JTextField inputComment = new JTextField("无");
	private JButton saveChange = new JButton("保存添加");
	private JButton addCategory = new JButton();
	private static CashFlowPanel instance = new CashFlowPanel();
	private int choosePanel;
	
	public CashFlowPanel(int choosePanel)
	{
		super(MainPanel.BACKGROUND);
		this.choosePanel = choosePanel;
		setTextFont(new Font("微软雅黑",Font.BOLD,25));
		setTextColor(Color.BLACK);
		setButtonImage();
		GUIUtil.setColor(ColorUtil.blueColor, saveChange,addCategory);
		addJComponent();
		addListener();
		setVisible(true);
	}
	
	public CashFlowPanel()
	{
		
	}

	public void setTextColor(Color color)
	{
		showText.setForeground(color);
		chooseDate.setForeground(color);
		chooseCategory.setForeground(color);
		setMoneyText.setForeground(color);
		comment.setForeground(color);
	}
	
	public void setTextFont(Font font)
	{
		showText.setFont(font);
		chooseDate.setFont(font);
		chooseCategory.setFont(font);
		setMoneyText.setFont(font);
		comment.setFont(font);
		incomeCtgyJcomboBox.setFont(font);
		costCtgyJcomboBox.setFont(font);
		datepick.setFont(font);
		inputMoney.setFont(font);
		inputComment.setFont(font);
		saveChange.setFont(font);
		addCategory.setFont(font);
	}
	
	public void addJComponent()
	{
		setLayout(new BorderLayout());
		add(centerPanel(),BorderLayout.CENTER);
		add(southPanel(),BorderLayout.SOUTH);
	}
	
	private UtilPanel southPanel() {
		UtilPanel p = new UtilPanel(MainPanel.BACKGROUND);
		JToolBar tb = new JToolBar();
		tb.add(saveChange);
		tb.add(addCategory);
		tb.setFloatable(false);
		p.add(tb,BorderLayout.SOUTH);
		return p;
	}

	private UtilPanel centerPanel() {
		
		UtilPanel p = new UtilPanel(MainPanel.BACKGROUND);
		p.setLayout(new GridLayout(5,2,5,5));
		p.add(showText);
		p.add(placeholder);
		p.add(chooseDate);
		p.add(datepick);
		p.add(chooseCategory);
		if( choosePanel == 1 )
			p.add( incomeCtgyJcomboBox );
		else if( choosePanel == 2 )
			p.add( costCtgyJcomboBox );
		p.add(setMoneyText);
		p.add(inputMoney);
		p.add(comment);
		p.add(inputComment);
		return p;
	}

    public void resetInput(){
    	inputMoney.setText("0");
    	inputComment.setText("");
        if( 0!= incomeCategoryModel.getIncomeCategoryList().size() )
        	incomeCtgyJcomboBox.setSelectedIndex(0);
        if( 0!= costCategoryModel.getCostCategoryList().size() )
        	costCtgyJcomboBox.setSelectedIndex(0);
        datepick.setDate(new Date());
    }
    
    @Override
    public void updateData() {
    	
    	if( choosePanel == 1 )
    	{
    		incomeCategoryModel.setIncomeCategoryList(new IncomeCategoryService().list());
    		incomeCtgyJcomboBox.updateUI();
    	}
    	else if( choosePanel == 2 )
    	{
    		costCategoryModel.setCostCategoryList(new CostCategoryService().list());
    		costCtgyJcomboBox.updateUI();
    	}
        resetInput();
        inputMoney.grabFocus();
        
    }

	@Override
	public void addListener() {
		
		if( choosePanel == 1 )
		{
			IncomeRecordListener incomeListener = new IncomeRecordListener();
			saveChange.addActionListener(incomeListener); 
			addCategory.addActionListener(incomeListener); 
		}
		else if( choosePanel == 2 )
		{
			CostRecordListener costListener = new CostRecordListener();
			saveChange.addActionListener(costListener); 
			addCategory.addActionListener(costListener);
		}
	}
	
    public JLabel getShowText() {
		return showText;
	}

	public void setShowText(JLabel showText) {
		this.showText = showText;
	}

	public JLabel getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(JLabel placeholder) {
		this.placeholder = placeholder;
	}

	public JLabel getChooseDate() {
		return chooseDate;
	}

	public void setChooseDate(JLabel chooseDate) {
		this.chooseDate = chooseDate;
	}

	public JLabel getChooseCategory() {
		return chooseCategory;
	}

	public void setChooseCategory(JLabel chooseCategory) {
		this.chooseCategory = chooseCategory;
	}

	public JLabel getSetMoneyText() {
		return setMoneyText;
	}

	public void setSetMoneyText(JLabel setMoneyText) {
		this.setMoneyText = setMoneyText;
	}

	public JLabel getComment() {
		return comment;
	}

	public void setComment(JLabel comment) {
		this.comment = comment;
	}

	public IncomeCategoryComboBoxModel getIncomeCategoryModel() {
		return incomeCategoryModel;
	}

	public void setIncomeCategoryModel(IncomeCategoryComboBoxModel incomeCategoryModel) {
		this.incomeCategoryModel = incomeCategoryModel;
	}

	public CostCategoryComboBoxModel getCostCategoryModel() {
		return costCategoryModel;
	}

	public void setCostCategoryModel(CostCategoryComboBoxModel costCategoryModel) {
		this.costCategoryModel = costCategoryModel;
	}

	public JComboBox<IncomeCategory> getIncomeCtgyJcomboBox() {
		return incomeCtgyJcomboBox;
	}

	public void setIncomeCtgyJcomboBox(JComboBox<IncomeCategory> incomeCtgyJcomboBox) {
		this.incomeCtgyJcomboBox = incomeCtgyJcomboBox;
	}

	public JComboBox<CostCategory> getCostCtgyJcomboBox() {
		return costCtgyJcomboBox;
	}

	public void setCostCtgyJcomboBox(JComboBox<CostCategory> costCtgyJcomboBox) {
		this.costCtgyJcomboBox = costCtgyJcomboBox;
	}

	public JXDatePicker getDatepick() {
		return datepick;
	}

	public void setDatepick(JXDatePicker datepick) {
		this.datepick = datepick;
	}

	public JTextField getInputMoney() {
		return inputMoney;
	}

	public void setInputMoney(JTextField inputMoney) {
		this.inputMoney = inputMoney;
	}

	public JTextField getInputComment() {
		return inputComment;
	}

	public void setInputComment(JTextField inputComment) {
		this.inputComment = inputComment;
	}

	public JButton getSaveChange() {
		return saveChange;
	}

	public void setSaveChange(JButton saveChange) {
		this.saveChange = saveChange;
	}

	public JButton getAddCategory() {
		return addCategory;
	}

	public void setAddCategory(String buttonName) {
		GUIUtil.setButtonImage(addCategory, "return.png", buttonName);
	}

	public static CashFlowPanel getInstance() {
		return instance;
	}

	public static void setInstance(CashFlowPanel instance) {
		CashFlowPanel.instance = instance;
	}
	
	public int getChoosePanel() {
		return choosePanel;
	}

	public void setChoosePanel(int choosePanel) {
		this.choosePanel = choosePanel;
	}

	public IncomeCategory getIncomeSelectedCategory(){
//		if( incomeCtgyJcomboBox.getSelectedItem() == null )
//			System.out.println("null");
        return (IncomeCategory) incomeCtgyJcomboBox.getSelectedItem();
    }
    
    public CostCategory getCostSelectedCategory(){
        return (CostCategory) costCtgyJcomboBox.getSelectedItem();
    }

	@Override
	public void setButtonImage() {
		GUIUtil.setButtonImage(saveChange, "save.png", "保存添加");
	}
    
}
