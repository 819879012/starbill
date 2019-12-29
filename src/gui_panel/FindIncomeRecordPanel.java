package gui_panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.jdesktop.swingx.JXDatePicker;

import constant.TagXml;
import entity.IncomeCategory;
import gui_model.FindIncomeRecordTableModel;
import gui_model.IncomeCategoryComboBoxModel;

@SuppressWarnings("serial")
public class FindIncomeRecordPanel extends PublicFindRecordPanel{
	
    private static FindIncomeRecordPanel instance = new FindIncomeRecordPanel();
    
	public FindIncomeRecordPanel()
	{
		super(TagXml.findIncomeRecordPanel);
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
	public JButton getCountEarnButton() {
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
	public IncomeCategoryComboBoxModel getModel() {
		return super.getIncomeCategoryModel();
	}

	/**
	 * @return the category
	 */
	public JComboBox<IncomeCategory> getCategory() {
		return super.getIncomeCategoryComboBox();
	}
	public IncomeCategory getIncomeSelectedCategory(){
        return (IncomeCategory) super.getIncomeCategoryModel().getSelectedItem();
    }
	/**
	 * @return the findIncomeRecordTableModel
	 */
	public FindIncomeRecordTableModel getFindIncomeRecordTableModel() {
		return super.getFindIncomeRecordTableModel();
	}

	/**
	 * @return the showResultTable
	 */
	public JTable getShowResultTable() {
		return super.getFindIncomeRecordTable();
	}

	/**
	 * @return the instance
	 */
	public static FindIncomeRecordPanel getInstance() {
		return instance;
	}
}
