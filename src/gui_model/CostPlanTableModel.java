package gui_model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import dao.CostCategoryDAO;
import entity.CostPlan;
import service.CostPlanService;

@SuppressWarnings("serial")
public class CostPlanTableModel extends AbstractTableModel {

    private String[] columnNames = new String[] { "消费日期", "费用类型","大约消费金额","消费事件备注"};

    // 使用从Service返回的List作为TableModel的数据

    private List<CostPlan> costPlanList = new CostPlanService().list();

    public int getRowCount() {
        return costPlanList.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public String[] getColumnNames() {
		return columnNames;
	}

	public List<CostPlan> getCostPlanList() {
		return costPlanList;
	}

	public void setCostPlanList(List<CostPlan> list) {
		costPlanList = list;
	}

	// 先通过costPlanList.get(rowIndex)获取行对应的Category对象
    // 然后根据columnIndex返回对应的属性
    public Object getValueAt(int rowIndex, int columnIndex) {
        CostPlan costPlan = costPlanList.get(rowIndex);
        CostCategoryDAO costCategoryDao = new CostCategoryDAO();
        if ( 0 == columnIndex )
            return costPlan.getDate();
        if ( 1 == columnIndex && costCategoryDao.getById(costPlan.getCid()) != null )
            return costCategoryDao.getById(costPlan.getCid()).getName();
        if( 2 == columnIndex )
        	return costPlan.getSpend();
        if( 3 == columnIndex )
        	return costPlan.getComment();
        return null;
    }

}