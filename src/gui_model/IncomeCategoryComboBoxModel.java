package gui_model;
 
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import entity.IncomeCategory;
import service.IncomeCategoryService;
 
public class IncomeCategoryComboBoxModel implements ComboBoxModel<IncomeCategory>{
	
	private IncomeCategoryService service = new IncomeCategoryService();
    private List<IncomeCategory> incomeCategoryList = service.list();
    private IncomeCategory incomeCategory;
    
    public IncomeCategoryComboBoxModel(){
    	incomeCategoryList.clear();
    	incomeCategoryList.addAll(service.list());
        if(!incomeCategoryList.isEmpty())
        	incomeCategory = incomeCategoryList.get(0);
    }
    
    @Override
    public int getSize() {
        return incomeCategoryList.size();
    }
 
    @Override
    public IncomeCategory getElementAt(int index) {
        return incomeCategoryList.get(index);
    }
 
    @Override
    public void addListDataListener(ListDataListener l) {
         
    }
 
    @Override
    public void removeListDataListener(ListDataListener l) {
         
    }

    @Override
    public void setSelectedItem(Object anItem) {
    	incomeCategory = (IncomeCategory) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if(!incomeCategoryList.isEmpty())
            return incomeCategory;
        else
            return null;
    }

	public List<IncomeCategory> getIncomeCategoryList() {
		return incomeCategoryList;
	}

	public void setIncomeCategoryList(List<IncomeCategory> incomeCategoryList) {
		this.incomeCategoryList = incomeCategoryList;
	}

	public IncomeCategory getIncomeCategory() {
		return incomeCategory;
	}

	public void setIncomeCategory(IncomeCategory incomeCategory) {
		this.incomeCategory = incomeCategory;
	}
	public void updateJcombobox()
	{
    	incomeCategoryList.clear();
    	incomeCategoryList.addAll(new IncomeCategoryService().list());
	}
}