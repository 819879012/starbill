package gui_model;
  
import java.util.List;
import javax.swing.table.AbstractTableModel;
import entity.IncomeCategory;
import service.IncomeCategoryService;

@SuppressWarnings("serial")
public class IncomeCategoryTableModel extends AbstractTableModel {
  
    private String[] columnNames = new String[] { "�����������", "�������"};

    // ʹ�ô�Service���ص�List��ΪTableModel������
  
    private List<IncomeCategory> cs = new IncomeCategoryService().list();
    
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

	public List<IncomeCategory> getIncomeCategoryList() {
		return cs;
	}
	
	public void setIncomeCategoryList(List<IncomeCategory> list) {
		cs = list;
	}

	// ��ͨ��cs.get(rowIndex)��ȡ�ж�Ӧ��Category����
    // Ȼ�����columnIndex���ض�Ӧ������
    public Object getValueAt(int rowIndex, int columnIndex) {
    	IncomeCategory h = cs.get(rowIndex);
        if ( 0 == columnIndex )
            return h.getName();
        if ( 1 == columnIndex )
            return h.getRecordNumber();
        return null;
    }

}