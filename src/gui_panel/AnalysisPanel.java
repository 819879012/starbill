package gui_panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import gui_listener.AnalysisListener;
import util.GUIUtil;

@SuppressWarnings("serial")
public class AnalysisPanel extends GraphPanel{

	private JButton analysisEarn = new JButton();
	private JButton analysisCost = new JButton();
	private JButton countEarn = new JButton();
	private JButton countCost = new JButton();
	
	private static AnalysisPanel instance = new AnalysisPanel();
	
	public AnalysisPanel() {
		super(MainPanel.BACKGROUND);
		setLayout(new BorderLayout());
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		setButtonImage();
		addPanel();
		addListener();
	}
	
	private void addPanel() {
		
		UtilPanel centerPanel = new UtilPanel(MainPanel.BACKGROUND);
		
		centerPanel.setLayout(new GridLayout(2,2));
		JToolBar tb1 = new JToolBar();
		JToolBar tb2 = new JToolBar();
		JToolBar tb3 = new JToolBar();
		JToolBar tb4 = new JToolBar();
		
		tb1.add(analysisEarn);
		tb2.add(analysisCost);
		tb3.add(countEarn);
		tb4.add(countCost);
		
		tb1.setFloatable(false);
		tb2.setFloatable(false);
		tb3.setFloatable(false);
		tb4.setFloatable(false);
		
		centerPanel.add(tb1);
		centerPanel.add(tb2);
		centerPanel.add(tb3);
		centerPanel.add(tb4);
		
		add(centerPanel,BorderLayout.CENTER);
	}

	public void setButtonImage()
	{
		GUIUtil.setButtonImage(analysisEarn,"analysisEarn.png", "分析收入");
		GUIUtil.setButtonImage(analysisCost,"analysisCost.png", "分析支出");
		GUIUtil.setButtonImage(countEarn,"countEarn.png", "统计收入");
		GUIUtil.setButtonImage(countCost,"countCost.png", "统计支出");
	}
	
	public void setTextFont( Font font )
	{
		analysisEarn.setFont(font);
		analysisCost.setFont(font);
		countEarn.setFont(font);
		countCost.setFont(font);
	}
	
	public JButton getAnalysisEarn() {
		return analysisEarn;
	}

	public JButton getAnalysisCost() {
		return analysisCost;
	}

	public JButton getCountEarn() {
		return countEarn;
	}

	public JButton getCountCost() {
		return countCost;
	}

	public static AnalysisPanel getInstance() {
		return instance;
	}

	@Override
	public void updateData() {
		
	}

	@Override
	public void addListener() {
		AnalysisListener listener = new AnalysisListener();
		analysisEarn.addActionListener(listener);
		analysisCost.addActionListener(listener);
		countEarn.addActionListener(listener);
		countCost.addActionListener(listener);
	}

	public static void main(String[] args) {
		GUIUtil.showPanel(instance, 0.5);
	}
}
