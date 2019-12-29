package gui_panel;

import javax.swing.JButton;
import javax.swing.JTable;

import constant.TagXml;
import gui_model.CostDetailsTableModel;

@SuppressWarnings("serial")
public class CostDetailsPanel extends PublicDetailsPanel{
	
	private static CostDetailsPanel instance = new CostDetailsPanel();
	
	public CostDetailsPanel()
	{
		super(TagXml.costDetailsPanel);
	}
	/**
	 * @return the model
	 */
	public CostDetailsTableModel getModel() {
		return super.getCostDetailsModel();
	}

	/**
	 * @return the showInfoTable
	 */
	public JTable getShowInfoTable() {
		return super.getCostDetailsTable();
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
	public static CostDetailsPanel getInstance() {
		return instance;
	}

}
