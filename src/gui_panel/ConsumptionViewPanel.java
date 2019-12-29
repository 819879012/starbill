package gui_panel;

import static util.GUIUtil.setColor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import gui_page.SpendPage;
import service.CostViewService;
import util.CircleProgressBar;
import util.ColorUtil;

@SuppressWarnings("serial")
public class ConsumptionViewPanel extends GraphPanel {

    JLabel lMonthSpend = new JLabel("本月消费");
    JLabel lTodaySpend = new JLabel("今日消费");
    JLabel lAvgSpendPerDay = new JLabel("日均消费");
    JLabel lMonthLeft = new JLabel("本月剩余");
    JLabel lDayAvgAvailable = new JLabel("日均可用");
    JLabel lMonthLeftDay = new JLabel("距离月末");
 
    JLabel vMonthSpend = new JLabel("￥2300");
    JLabel vTodaySpend = new JLabel("￥25");
    JLabel vAvgSpendPerDay = new JLabel("￥120");
    JLabel vMonthAvailable = new JLabel("￥2084");
    JLabel vDayAvgAvailable = new JLabel("￥389");
    JLabel vMonthLeftDay = new JLabel("15天");

    CircleProgressBar bar;
    
	private static ConsumptionViewPanel instance = new ConsumptionViewPanel();
	
	public ConsumptionViewPanel() {
		super(MainPanel.BACKGROUND);
		
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);
 
        setColor(Color.BLACK, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
                lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
 
        setTextFont(new Font("微软雅黑",Font.BOLD,23));
        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
	}
	
    public void setTextFont(Font font) {
    	lMonthSpend.setFont(font);
    	lTodaySpend.setFont(font);
    	lAvgSpendPerDay.setFont(font);
    	lMonthLeft.setFont(font);
    	lDayAvgAvailable.setFont(font);
    	lMonthLeftDay.setFont(font);
    	vMonthSpend.setFont(font);
    	vTodaySpend.setFont(font);
    	vAvgSpendPerDay.setFont(font);
    	vMonthAvailable.setFont(font);
        vDayAvgAvailable.setFont(font);
        vMonthLeftDay.setFont(font);
	}

	private JPanel center() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(), BorderLayout.WEST);
        p.add(east());
 
        return p;
    }
 
    private Component east() {
 
        return bar;
    }
 
    private Component west() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1));
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
    }
    
    private JPanel south() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 4));
 
        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthAvailable);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);
 
        return p;
    }
    
    @Override
    public void updateData() {
        SpendPage spend = new CostViewService().getSpendPage();
        vMonthSpend.setText(spend.getMonthSpend());
        vTodaySpend.setText(spend.getTodaySpend());
        vAvgSpendPerDay.setText(spend.getAvgSpendPerDay());
        vMonthAvailable.setText(spend.getMonthAvailable());
        vDayAvgAvailable.setText(spend.getDayAvgAvailable());
        vMonthLeftDay.setText(spend.getMonthLeftDay());
 
        bar.setProgress(spend.getUsagePercentage());
        if (spend.isOverSpend()) {
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
        } else {
            vMonthAvailable.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(spend.getUsagePercentage()));
//        addListener();
    }

	public static ConsumptionViewPanel getInstance() {
		return instance;
	}


	@Override
	public void addListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setButtonImage() {
		// TODO Auto-generated method stub
		
	}
}
