package gui_panel;
 
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import gui_listener.MonthCostHistogramListener;
import util.ChartUtil;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class MonthSpendHistogramPanel extends GraphPanel {
 
    private static MonthSpendHistogramPanel instance = new MonthSpendHistogramPanel();
    private JLabel label = new JLabel();
    
    private JButton chooseMonthButton = new JButton();
    private JButton chooseYearButton = new JButton();
    private JButton refreshButton = new JButton(); 
    
    public MonthSpendHistogramPanel() {
        this.setLayout(new BorderLayout());
        Image image =ChartUtil.getImage(800, 400,"月消费报表");
        ImageIcon icon= new ImageIcon(image);
        label.setIcon(icon);
        add(label,BorderLayout.CENTER);
        add(eastPanel(),BorderLayout.EAST);
        addListener();
    }

    private UtilPanel eastPanel() {
    	UtilPanel p = new UtilPanel(MainPanel.BACKGROUND);
    	p.setLayout(new GridLayout(3,1));
    	JToolBar tb = new JToolBar();
    	JToolBar tb1 = new JToolBar();
    	JToolBar tb2 = new JToolBar();
    	setButtonImage();
    	setTextFont(new Font("微软雅黑",Font.BOLD,20));
    	tb.setFloatable(false);
    	tb1.setFloatable(false);
    	tb2.setFloatable(false);
    	tb.add(chooseMonthButton);
    	tb1.add(refreshButton);
    	tb2.add(chooseYearButton);

    	p.add(tb2);
    	p.add(tb);
    	p.add(tb1);
		return p;
	}

	public void setButtonImage()
    {
		GUIUtil.setButtonImage(chooseYearButton, "chooseMonth1.png", "选择年份");
    	GUIUtil.setButtonImage(chooseMonthButton, "calendar.png", "选择月份");
    	GUIUtil.setButtonImage(refreshButton, "refresh2.png", "刷新图片");
    	GUIUtil.setColor(ColorUtil.blueColor, chooseYearButton,chooseMonthButton,refreshButton);
    }
    
	public void setTextFont(Font font)
	{
		chooseMonthButton.setFont(font);
		refreshButton.setFont(font);
		chooseYearButton.setFont(font);
	}
	
	@Override
	public void updateData() {
	}

	@Override
	public void addListener() {
		MonthCostHistogramListener listener = new MonthCostHistogramListener();
		chooseYearButton.addActionListener(listener);
		chooseMonthButton.addActionListener(listener);
		refreshButton.addActionListener(listener);
	}

	public static MonthSpendHistogramPanel getInstance() {
		return instance;
	}

	public JLabel getLabel() {
		return label;
	}

	public JButton getChooseMonthButton() {
		return chooseMonthButton;
	}

	public JButton getChooseYearButton() {
		return chooseYearButton;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

}