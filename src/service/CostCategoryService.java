package service;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dao.CostCategoryDAO;
import dao.CostRecordDAO;
import entity.CostCategory;
import entity.CostRecord;
 
public class CostCategoryService {
 
    CostCategoryDAO costCategoryDao = new CostCategoryDAO();
    CostRecordDAO costRecordDao = new CostRecordDAO();

    public List<CostCategory> list() {
    	int uid = UsersService.getNowUid();
        List<CostCategory> cs = costCategoryDao.list();
        List<CostCategory> returnList = new ArrayList<CostCategory>();
        for (CostCategory c : cs) {
        	if( c.getUid() == uid )
        	{
        		returnList.add(c);
        	}
        }
        for (CostCategory c : returnList) {
            List<CostRecord> rs = costRecordDao.list(c.getId());
            c.setRecordNumber(rs.size());
        }
        //sort with the record number
        Collections.sort(returnList,(c1,c2)->c2.getRecordNumber()-c1.getRecordNumber());
        return returnList;
    }

    public void addCostCategory(String name) {
    	RecoverService recover = RecoverService.getInstance();
        CostCategory costCategory = new CostCategory();
        costCategory.setUid(UsersService.getNowUid());
        costCategory.setName(name);
        costCategoryDao.add(costCategory);
        recover.addDeleteCostCategory(costCategory);
    }

    public void updateCostCategory(int id, String name) {
        CostCategory costCategory = new CostCategory();
        costCategory.setUid(UsersService.getNowUid());
        costCategory.setName(name);
        costCategory.setId(id);
        costCategoryDao.update(costCategory);
    }

    public void deleteCostCategoryById(int id) {
    	costCategoryDao.delete(id);
    }

}