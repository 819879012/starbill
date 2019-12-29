package gui_panel;

import javax.swing.JButton;
import javax.swing.JTable;

import constant.TagXml;
import gui_model.IncomeDetailsTableModel;

@SuppressWarnings("serial")
public class IncomeDetailsPanel extends PublicDetailsPanel{

	private static IncomeDetailsPanel instance = new IncomeDetailsPanel();
	
	public IncomeDetailsPanel()
	{
		super(TagXml.incomeDetailsPanel);
	}
	/**
	 * @return the model
	 */
	public IncomeDetailsTableModel getModel() {
		return super.getIncomeDetailsmodel();
	}

	/**
	 * @return the showInfoTable
	 */
	public JTable getShowInfoTable() {
		return super.getIncomeDetailsTable();
	}

	/**
	 * @return the changeButton
	 */
	public JButton getChangeButton() {
		return super.getChangeButton();
	}

	/**
	 * @return the deleteButton
	 */
	public JButton getDeleteButton() {
		return super.getDeleteButton();
	}

	/**
	 * @return the refreshButton
	 */
	public JButton getRefreshButton() {
		return super.getRefreshButton();
	}

	/**
	 * @return the instance
	 */
	public static IncomeDetailsPanel getInstance() {
		return instance;
	}
}
