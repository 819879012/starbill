package gui_page;
 
public class SpendPage {
    //本月消费
    private String monthSpend;
    //今日消费
    private String todaySpend;
    //日均消费
    private String avgSpendPerDay;
    //本月剩余
    private String monthAvailable;
    //日均可用
    private String dayAvgAvailable;
    //距离月末
    private String monthLeftDay;
    //使用比例
    private int usagePercentage;
    //是否超支
    private boolean isOverSpend = false;
 
    public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, int dayAvgAvailable,
        int monthLeftDay, int usagePercentage) {
        this.monthSpend = "￥" + monthSpend;
        this.todaySpend = "￥" + todaySpend;
        this.avgSpendPerDay = "￥" + avgSpendPerDay;
        if (monthAvailable < 0)
            isOverSpend = true;
        if (!isOverSpend) {
            this.monthAvailable = "￥" + monthAvailable;
            this.dayAvgAvailable = "￥" + dayAvgAvailable;
        } else {
            this.monthAvailable = "超支" + (0 - monthAvailable);
            this.dayAvgAvailable = "￥0";
        }
        this.monthLeftDay = monthLeftDay + "天";
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