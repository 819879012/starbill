package gui_model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.IncomeCategoryDAO;
import entity.IncomeRecord;
import service.StatisticsEarnService;

@SuppressWarnings("serial")
public class StatisticsEarnTableModel extends AbstractTableModel {
	
	private String[] columnNames = new String[] { "收入总额","收入分类" };
	
	private List<IncomeRecord> incomeRecordList = new StatisticsEarnService().list();
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return incomeRecordList.size();
	}
	public String[] getColumnNames() {
		return columnNames;
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
	
	public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
	
	public List<IncomeRecord> getIncomeRecordList() {
		return incomeRecordList;
	}
	
	public void setIncomeRecordList(List<IncomeRecord> list) {
		incomeRecordList = list;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		IncomeCategoryDAO incomeCategoryDao = new IncomeCategoryDAO();
		IncomeRecord incomeRecord = incomeRecordList.get(rowIndex);
        if ( 0 == columnIndex && incomeCategoryDao.getById(incomeRecord.getCid()) != null)
        	return incomeRecord.getIncome();
        if ( 1 == columnIndex && incomeCategoryDao.getById(incomeRecord.getCid()) != null)
            return incomeCategoryDao.getById(incomeRecord.getCid()).getName();


        return null;
	}
}
