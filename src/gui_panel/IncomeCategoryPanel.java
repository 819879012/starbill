package gui_panel;

import javax.swing.JButton;
import constant.TagXml;
import entity.IncomeCategory;

@SuppressWarnings("serial")
public class IncomeCategoryPanel extends PublicCategoryPanel{

	public IncomeCategoryPanel() {
		super(TagXml.incomeCategoryPanel);
	}
	
	private static IncomeCategoryPanel instance = new IncomeCategoryPanel();
	
	public IncomeCategory getSelectedIncomeCategory() {
	    int index = super.getIncomeCategoryTabel().getSelectedRow();
	    return super.getIncomeCategoryTableModel().getIncomeCategoryList().get(index);
	}
	
	public JButton getAddIncomeCategory() {
		return super.getAddCategory();
	}

	public JButton getDeleteIncomeCategory() {
		return super.getDeleteCategory();
	}

	public JButton getChangeIncomeCategory() {
		return super.getChangeCategory();
	}

	public static IncomeCategoryPanel getInstance() {
		return instance;
	}

}
