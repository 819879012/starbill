package gui_panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.jdesktop.swingx.JXDatePicker;

import constant.TagXml;
import entity.CostCategory;
import gui_model.CostCategoryComboBoxModel;
import gui_model.FindCostRecordTableModel;

@SuppressWarnings("serial")
public class FindCostRecordPanel extends PublicFindRecordPanel{
	
    private static FindCostRecordPanel instance = new FindCostRecordPanel();
    
	public FindCostRecordPanel()
	{
		super(TagXml.findCostRecordPanel);
	}
	
	
	
	/**
	 * @return the chooseDate
	 */
	public JLabel getChooseDate() {
		return super.getChooseDate();
	}

	/**
	 * @return the chooseCategory
	 */
	public JLabel getChooseCategory() {
		return super.getChooseCategory();
	}

	/**
	 * @return the searchButton
	 */
	public JButton getSearchButton() {
		return super.getSearchButton();
	}

	/**
	 * @return the countEarnButton
	 */
	public JButton getCountCostButton() {
		return super.getCountButton();
	}


	/**
	 * @return the datepick
	 */
	public JXDatePicker getDatepick() {
		return super.getDatepick();
	}

	/**
	 * @return the model
	 */
	public CostCategoryComboBoxModel getModel() {
		return super.getCostCategoryModel();
	}

	/**
	 * @return the category
	 */
	public JComboBox<CostCategory> getCategory() {
		return super.getCostCategoryComboBox();
	}
	public CostCategory getCostSelectedCategory(){
//		if( incomeCtgyJcomboBox.getSelectedItem() == null )
//			Systcem.out.println("null");
        return (CostCategory) super.getCostCategoryModel().getSelectedItem();
    }
	/**
	 * @return the findIncomeRecordTableModel
	 */
	public FindCostRecordTableModel getFindCostRecordTableModel() {
		return super.getFindCostRecordTableModel();
	}

	/**
	 * @return the showResultTable
	 */
	public JTable getShowResultTable() {
		return super.getFindCostRecordTable();
	}

	/**
	 * @return the instance
	 */
	public static FindCostRecordPanel getInstance() {
		return instance;
	}
}
