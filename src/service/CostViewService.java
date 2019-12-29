package service;
 
import java.util.List;
 
import dao.CostRecordDAO;
import entity.CostRecord;
import gui_page.SpendPage;
import util.DateUtil;
 
public class CostViewService {
 
    public SpendPage getSpendPage() {
    	CostRecordDAO dao = new CostRecordDAO();
        // 本月数据
        List<CostRecord> thisMonthRecords = dao.listThisMonth();
        // 今日数据
        List<CostRecord> toDayRecords = dao.listToday();
        // 本月总天数
        int thisMonthTotalDay = DateUtil.thisMonthTotalDay();

        double monthSpend = 0;
        double todaySpend = 0;
        double avgSpendPerDay = 0;
        double monthAvailable = 0;
        double dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;

        // 预算
        double monthBudget = new ConfigService().getDoubleBudget();
 
        // 统计本月消费
        for (CostRecord record : thisMonthRecords) {
            monthSpend += record.getCost();
        }

        // 统计今日消费
        for (CostRecord record : toDayRecords) {
            todaySpend += record.getCost();
        }
        
        // 计算日均消费
        avgSpendPerDay = monthSpend / thisMonthTotalDay;
        // 计算本月剩余
        monthAvailable = monthBudget - monthSpend;
 
        // 距离月末
        monthLeftDay = DateUtil.thisMonthLeftDay();
 
        // 计算日均可用
        dayAvgAvailable = monthAvailable / monthLeftDay;

        // 计算使用比例
        usagePercentage = (int)(monthSpend * 100 / monthBudget);

        // 根据这些信息，生成SpendPage对象
        return new SpendPage((int)monthSpend, (int)todaySpend, 
        		(int)avgSpendPerDay, (int)monthAvailable, (int)dayAvgAvailable, monthLeftDay,
                usagePercentage);
    }
}