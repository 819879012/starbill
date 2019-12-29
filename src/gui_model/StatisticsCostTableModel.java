package gui_model;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import dao.CostCategoryDAO;
import entity.CostRecord;
import service.StatisticsCostService;

@SuppressWarnings("serial")
public class StatisticsCostTableModel extends AbstractTableModel{
private String[] columnNames = new String[] { "收入总额","收入分类" };
	
	private List<CostRecord> costRecordList = new StatisticsCostService().list();
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return costRecordList.size();
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
	
	public List<CostRecord> getCostRecordList() {
		return costRecordList;
	}
	
	public void setCostRecordList(List<CostRecord> list) {
		costRecordList = list;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		CostCategoryDAO costCategoryDao = new CostCategoryDAO();
		CostRecord costRecord = costRecordList.get(rowIndex);
        if ( 0 == columnIndex && costCategoryDao.getById(costRecord.getCid()) != null)
        	return costRecord.getCost();
        if ( 1 == columnIndex && costCategoryDao.getById(costRecord.getCid()) != null)
            return costCategoryDao.getById(costRecord.getCid()).getName();


        return null;
	}
}
