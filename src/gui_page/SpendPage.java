package gui_page;
 
public class SpendPage {
    //��������
    private String monthSpend;
    //��������
    private String todaySpend;
    //�վ�����
    private String avgSpendPerDay;
    //����ʣ��
    private String monthAvailable;
    //�վ�����
    private String dayAvgAvailable;
    //������ĩ
    private String monthLeftDay;
    //ʹ�ñ���
    private int usagePercentage;
    //�Ƿ�֧
    private boolean isOverSpend = false;
 
    public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, int dayAvgAvailable,
        int monthLeftDay, int usagePercentage) {
        this.monthSpend = "��" + monthSpend;
        this.todaySpend = "��" + todaySpend;
        this.avgSpendPerDay = "��" + avgSpendPerDay;
        if (monthAvailable < 0)
            isOverSpend = true;
        if (!isOverSpend) {
            this.monthAvailable = "��" + monthAvailable;
            this.dayAvgAvailable = "��" + dayAvgAvailable;
        } else {
            this.monthAvailable = "��֧" + (0 - monthAvailable);
            this.dayAvgAvailable = "��0";
        }
        this.monthLeftDay = monthLeftDay + "��";
        if( usagePercentage >= 100 )
        	usagePercentage = 100;
        this.usagePercentage = usagePercentage;
    }

	public String getMonthSpend() {
		return monthSpend;
	}

	public void setMonthSpend(String monthSpend) {
		this.monthSpend = monthSpend;
	}

	public String getTodaySpend() {
		return todaySpend;
	}

	public void setTodaySpend(String todaySpend) {
		this.todaySpend = todaySpend;
	}

	public String getAvgSpendPerDay() {
		return avgSpendPerDay;
	}

	public void setAvgSpendPerDay(String avgSpendPerDay) {
		this.avgSpendPerDay = avgSpendPerDay;
	}

	public String getMonthAvailable() {
		return monthAvailable;
	}

	public void setMonthAvailable(String monthAvailable) {
		this.monthAvailable = monthAvailable;
	}

	public String getDayAvgAvailable() {
		return dayAvgAvailable;
	}

	public void setDayAvgAvailable(String dayAvgAvailable) {
		this.dayAvgAvailable = dayAvgAvailable;
	}

	public String getMonthLeftDay() {
		return monthLeftDay;
	}

	public void setMonthLeftDay(String monthLeftDay) {
		this.monthLeftDay = monthLeftDay;
	}

	public int getUsagePercentage() {
		return usagePercentage;
	}

	public void setUsagePercentage(int usagePercentage) {
		this.usagePercentage = usagePercentage;
	}

	public boolean isOverSpend() {
		return isOverSpend;
	}

	public void setOverSpend(boolean isOverSpend) {
		this.isOverSpend = isOverSpend;
	}
    
    
}