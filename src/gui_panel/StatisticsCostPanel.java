package gui_panel;

import javax.swing.JButton;
import javax.swing.JTable;

import constant.TagXml;
import gui_model.StatisticsCostTableModel;

@SuppressWarnings("serial")
public class StatisticsCostPanel extends PublicStatisticsPanel{
	
	private static StatisticsCostPanel instance = new StatisticsCostPanel();
	
	public StatisticsCostPanel() {
		super(TagXml.statisticsCostPanel);
		
	}
	
	/**
	 * @return the statisticsCostTableModel
	 */
	public StatisticsCostTableModel getStatisticsCostTableModel() {
		return super.getStatisticsCostTableModel();
	}


	/**
	 * @return the showInfoTable
	 */
	public JTable getShowInfoTable() {
		return super.getStatisticsCostTable();
	}


	/**
	 * @return the instance
	 */
	public static StatisticsCostPanel getInstance() {
		return instance;
	}


	/**
	 * @return the chooseYear
	 */
	public JButton getChooseYear() {
		return super.getChooseYear();
	}


	/**
	 * @return the chooseMonth
	 */
	public JButton getChooseMonth() {
		return super.getChooseMonth();
	}


	/**
	 * @return the chooseCategory
	 */
	public JButton getChooseCategory() {
		return super.getChooseCategory();
	}


	/**
	 * @return the refreshButton
	 */
	public JButton getRefreshButton() {
		return super.getRefreshButton();
	}


	/**
	 * @return the returnButton
	 */
	public JButton getReturnButton() {
		return super.getReturnButton();
	}



}
