package gui_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JToolBar;

import constant.TagXml;
import gui_listener.ToolBarListener;
import gui_panel.CenterPanel;
import util.GUIUtil;

@SuppressWarnings("serial")
public class MainPanel extends GraphPanel{
	
	public final static String BACKGROUND = TagXml.BACKGROUND;
	private static MainPanel instance = new MainPanel(BACKGROUND);
	
    private JToolBar tb = new JToolBar();
    private JButton bAddEarn = new JButton();
    private JButton bAddCost = new JButton();
    private JButton bDetails = new JButton();
    private JButton bFind = new JButton();
    private JButton bCount = new JButton();
    private JButton bSpend = new JButton();
    private JButton bSpendLine = new JButton();
    private JButton bRecord = new JButton();
    private JButton bCategory = new JButton();
    private JButton bReport = new JButton();
    private JButton bConfig = new JButton();
    private JButton bBackup = new JButton();
    private JButton bRecover = new JButton();
    private CenterPanel workingPanel;
    
	private MainPanel(String background) {
		super(background);
        
		setButtonImage();
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		setColor();
		addToolBarItem();
		
        tb.setFloatable(false);
        
        workingPanel = new CenterPanel(0.8);
        setLayout(new BorderLayout());
        add(tb, BorderLayout.NORTH);
        add(workingPanel, BorderLayout.CENTER);
        
        addListener();
	}

	private void addToolBarItem() {
        tb.add(bAddEarn);
        tb.add(bAddCost);
        tb.add(bDetails);
        tb.add(bFind);
        tb.add(bCount);
        tb.add(bSpend);
        tb.add(bSpendLine);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
	}

	private void setColor() {
		
        bAddEarn.setForeground(Color.BLUE);
        bAddCost.setForeground(Color.red);
        bDetails.setForeground(Color.red);
        bFind.setForeground(Color.blue);
        bCount.setForeground(Color.blue);
        bSpend.setForeground(Color.blue);
        bSpendLine.setForeground(Color.blue);
        bRecord.setForeground(Color.red);
        bCategory.setForeground(Color.blue);
        bReport.setForeground(Color.blue);
        bConfig.setForeground(Color.blue);
        bBackup.setForeground(Color.blue);
        bRecover.setForeground(Color.blue);
		
	}

	public void setButtonImage()
	{
		GUIUtil.setButtonImage(bAddEarn, "addEarn.png", "添加收入");
		GUIUtil.setButtonImage(bAddCost, "addSpend.png", "添加支出");
		GUIUtil.setButtonImage(bDetails, "details.png", "收支明细");
		GUIUtil.setButtonImage(bFind, "find.png", "收支查询");
		GUIUtil.setButtonImage(bCount, "count.png", "分析统计");
        GUIUtil.setButtonImage(bSpend, "spendPercent.png", "消费一览");
        GUIUtil.setButtonImage(bSpendLine, "cureLine.png", "收支柱状图");
        GUIUtil.setButtonImage(bRecord, "record.png", "消费计划");
        GUIUtil.setButtonImage(bCategory, "category2.png", "收支分类");
        GUIUtil.setButtonImage(bReport, "report.png", "月消费报表");
        GUIUtil.setButtonImage(bConfig, "set.png", "设置");
        GUIUtil.setButtonImage(bBackup, "backup.png", "备份");
        GUIUtil.setButtonImage(bRecover, "restore.png", "恢复");
	}
	
	public void setTextFont(Font font)
	{
        bAddEarn.setFont(font);
        bAddCost.setFont(font);
        bDetails.setFont(font);
        bFind.setFont(font);
        bCount.setFont(font);
        bSpend.setFont(font);        
        bSpendLine.setFont(font);        
        bRecord.setFont(font);
        bCategory.setFont(font);
        bReport.setFont(font);
        bConfig.setFont(font);
        bBackup.setFont(font);
        bRecover.setFont(font);
	}

	@Override
	public void updateData() {
	}

	@Override
	public void addListener() {
        ToolBarListener listener = new ToolBarListener();
        
        bAddEarn.addActionListener(listener);
        bAddCost.addActionListener(listener);
        bDetails.addActionListener(listener);
        bFind.addActionListener(listener);
        bCount.addActionListener(listener);
        bSpend.addActionListener(listener);
        bSpendLine.addActionListener(listener);
        bRecord.addActionListener(listener);
        bCategory.addActionListener(listener);
        bReport.addActionListener(listener);
        bConfig.addActionListener(listener);
        bBackup.addActionListener(listener);
        bRecover.addActionListener(listener);
		
	}

	public JButton getbCount() {
		return bCount;
	}

	public void setbCount(JButton bCount) {
		this.bCount = bCount;
	}

	public static MainPanel getInstance()
	{
		return instance;
	}
	
	public JToolBar getTb() {
		return tb;
	}

	public JButton getbFind() {
		return bFind;
	}
	
	public JButton getbAddEarn() {
		return bAddEarn;
	}
	
	public JButton getbAddCost() {
		return bAddCost;
	}

	public JButton getbDetails() {
		return bDetails;
	}
	
	public JButton getbSpend() {
		return bSpend;
	}

	public JButton getbSpendLine() {
		return bSpendLine;
	}
	
	public JButton getbRecord() {
		return bRecord;
	}

	public JButton getbCategory() {
		return bCategory;
	}

	public JButton getbReport() {
		return bReport;
	}

	public JButton getbConfig() {
		return bConfig;
	}

	public JButton getbBackup() {
		return bBackup;
	}

	public JButton getbRecover() {
		return bRecover;
	}

	public CenterPanel getWorkingPanel() {
		return workingPanel;
	}

}
