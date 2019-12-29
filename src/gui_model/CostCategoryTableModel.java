package gui_model;
  
import java.util.List;
 
import javax.swing.table.AbstractTableModel;
 
import entity.CostCategory;
import service.CostCategoryService;

@SuppressWarnings("serial")
public class CostCategoryTableModel extends AbstractTableModel {
  
    private String[] columnNames = new String[] { "支出分类名称", "消费次数"};

    // 使用从Service返回的List作为TableModel的数据

    private static List<CostCategory> cs = new CostCategoryService().list();
    
    public int getRowCount() {
        return cs.size();
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

	public List<CostCategory> getCostCategoryList() {
		return cs;
	}
	
	public void setCostCategoryList(List<CostCategory> list) {
		cs = list;
	}

	// 先通过cs.get(rowIndex)获取行对应的CostPlan对象
    // 然后根据columnIndex返回对应的属性
    public Object getValueAt(int rowIndex, int columnIndex) {
        CostCategory h = cs.get(rowIndex);
        if ( 0 == columnIndex )
            return h.getName();
        if ( 1 == columnIndex )
            return h.getRecordNumber();
        return null;
    }

}