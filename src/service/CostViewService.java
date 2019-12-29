package service;
 
import java.util.List;
 
import dao.CostRecordDAO;
import entity.CostRecord;
import gui_page.SpendPage;
import util.DateUtil;
 
public class CostViewService {
 
    public SpendPage getSpendPage() {
    	CostRecordDAO dao = new CostRecordDAO();
        // ��������
        List<CostRecord> thisMonthRecords = dao.listThisMonth();
        // ��������
        List<CostRecord> toDayRecords = dao.listToday();
        // ����������
        int thisMonthTotalDay = DateUtil.thisMonthTotalDay();

        double monthSpend = 0;
        double todaySpend = 0;
        double avgSpendPerDay = 0;
        double monthAvailable = 0;
        double dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;

        // Ԥ��
        double monthBudget = new ConfigService().getDoubleBudget();
 
        // ͳ�Ʊ�������
        for (CostRecord record : thisMonthRecords) {
            monthSpend += record.getCost();
        }

        // ͳ�ƽ�������
        for (CostRecord record : toDayRecords) {
            todaySpend += record.getCost();
        }
        
        // �����վ�����
        avgSpendPerDay = monthSpend / thisMonthTotalDay;
        // ���㱾��ʣ��
        monthAvailable = monthBudget - monthSpend;
 
        // ������ĩ
        monthLeftDay = DateUtil.thisMonthLeftDay();
 
        // �����վ�����
        dayAvgAvailable = monthAvailable / monthLeftDay;

        // ����ʹ�ñ���
        usagePercentage = (int)(monthSpend * 100 / monthBudget);

        // ������Щ��Ϣ������SpendPage����
        return new SpendPage((int)monthSpend, (int)todaySpend, 
        		(int)avgSpendPerDay, (int)monthAvailable, (int)dayAvgAvailable, monthLeftDay,
                usagePercentage);
    }
}