package service;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dao.IncomeCategoryDAO;
import dao.IncomeRecordDAO;
import entity.IncomeCategory;
import entity.IncomeRecord;

public class IncomeCategoryService {
 
    private IncomeCategoryDAO incomeCategoryDao = new IncomeCategoryDAO();
    private IncomeRecordDAO incomeRecordDao = new IncomeRecordDAO();
    
    public List<IncomeCategory> list() {
    	int uid = UsersService.getNowUid();
        List<IncomeCategory> cs = incomeCategoryDao.list();
        List<IncomeCategory> returnList = new ArrayList<IncomeCategory>();
        for (IncomeCategory incomeCategory : cs) {
			if( incomeCategory.getUid() == uid ) 
				returnList.add(incomeCategory);
		}
        for (IncomeCategory c : returnList) {
            List<IncomeRecord> rs =incomeRecordDao.list(c.getId());
            c.setRecordNumber(rs.size());
        }
        //sort with the record number
        Collections.sort(returnList,(c1,c2)->c2.getRecordNumber()-c1.getRecordNumber());
        return returnList;
    }

    public void addIncomeCategory(String name) {
    	RecoverService recover = RecoverService.getInstance();
        IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setUid(UsersService.getNowUid());
        incomeCategory.setName(name);
        incomeCategoryDao.add(incomeCategory);
        recover.addDeleteIncomeCategory(incomeCategory);
    }

    public void updateIncomeCategory(int id, String name) {
        IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setUid(UsersService.getNowUid());
        incomeCategory.setName(name);
        incomeCategory.setId(id);
        incomeCategoryDao.update(incomeCategory);
    }

    public void deleteIncomeCategoryById(int id) {
    	incomeCategoryDao.delete(id);
    }

	public IncomeCategoryDAO getIncomeCategoryDao() {
		return incomeCategoryDao;
	}

	public IncomeRecordDAO getIncomeRecordDao() {
		return incomeRecordDao;
	}
    
}