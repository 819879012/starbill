package gui_panel;

import javax.swing.JButton;
import javax.swing.JTable;

import constant.TagXml;
import entity.CostCategory;

@SuppressWarnings("serial")
public class CostCategoryPanel extends PublicCategoryPanel{

	private static CostCategoryPanel instance = new CostCategoryPanel();

	public CostCategoryPanel() {
		super(TagXml.costCategoryPanel);
	}

	public JTable getCostCategoryTabel() {
		return super.getCostCategoryTabel();
	}

	public JButton getAddCostCategory() {
		return super.getAddCategory();
	}

	public JButton getDeleteCostCategory() {
		return super.getDeleteCategory();
	}

	public JButton getChangeCostCategory() {
		return super.getChangeCategory();
	}

	public static CostCategoryPanel getInstance() {
		return instance;
	}

	public CostCategory getSelectedCostCategory() {
	    int index = super.getCostCategoryTabel().getSelectedRow();
	    return super.getCostCategoryTableModel().getCostCategoryList().get(index);
	}

}
