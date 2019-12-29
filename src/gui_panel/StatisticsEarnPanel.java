package gui_panel;

import javax.swing.JButton;
import javax.swing.JTable;

import constant.TagXml;
import gui_model.StatisticsEarnTableModel;

@SuppressWarnings("serial")
public class StatisticsEarnPanel extends PublicStatisticsPanel{
	
	private static StatisticsEarnPanel instance = new StatisticsEarnPanel();
	
	public StatisticsEarnPanel() {
		super(TagXml.statisticsEarnPanel);
		
	}
	
	/**
	 * @return the statisticsEarnTableModel
	 */
	public StatisticsEarnTableModel getStatisticsEarnTableModel() {
		return super.getStatisticsEarnTableModel();
	}

	/**
	 * @return the showInfoTable
	 */
	public JTable getShowInfoTable() {
		return super.getStatisticsEarnTable();
	}

	/**
	 * @return the instance
	 */
	public static StatisticsEarnPanel getInstance() {
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
