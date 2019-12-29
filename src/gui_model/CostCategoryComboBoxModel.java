package gui_model;
 
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import entity.CostCategory;
import service.CostCategoryService;
 
public class CostCategoryComboBoxModel implements ComboBoxModel<CostCategory>{
 
    private List<CostCategory> costCategoryList = new CostCategoryService().list();
    private CostCategory costCategory;
    
    public CostCategoryComboBoxModel(){
    	updateJcombobox();
        if(!costCategoryList.isEmpty())
        	costCategory = costCategoryList.get(0);
    }
    
    @Override
    public int getSize() {
        return costCategoryList.size();
    }

    @Override
    public CostCategory getElementAt(int index) {
        return costCategoryList.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
         
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
         
    }

    @Override
    public void setSelectedItem(Object anItem) {
    	costCategory = (CostCategory) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if(!costCategoryList.isEmpty())
            return costCategory;
        else
            return null;
    }

	public List<CostCategory> getCostCategoryList() {
		return costCategoryList;
	}

	public void setCostCategoryList(List<CostCategory> costCategoryList) {
		this.costCategoryList = costCategoryList;
	}

	public CostCategory getCostCategory() {
		return costCategory;
	}

	public void setCostCategory(CostCategory costCategory) {
		this.costCategory = costCategory;
	}

	public void updateJcombobox()
	{
    	costCategoryList.clear();
    	costCategoryList.addAll(new CostCategoryService().list());
	}
	
}