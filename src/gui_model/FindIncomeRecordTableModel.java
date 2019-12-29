package gui_model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.IncomeCategoryDAO;
import entity.IncomeRecord;
import service.FindIncomeRecordService;

@SuppressWarnings("serial")
public class FindIncomeRecordTableModel extends AbstractTableModel{
	private String[] columnNames = new String[] { "日期", "费用类型", "金额", "备注" };
	
	private List<IncomeRecord> incomeRecordList = new FindIncomeRecordService().list();
	
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
        	return incomeRecord.getDate();
        if ( 1 == columnIndex && incomeCategoryDao.getById(incomeRecord.getCid()) != null)
            return incomeCategoryDao.getById(incomeRecord.getCid()).getName();
        if ( 2 == columnIndex && incomeCategoryDao.getById(incomeRecord.getCid()) != null)
        	return incomeRecord.getIncome();
        if ( 3 == columnIndex && incomeCategoryDao.getById(incomeRecord.getCid()) != null)
        	return incomeRecord.getComment();

        return null;
	}
}
