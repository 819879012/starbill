package gui_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import gui_listener.HistrogramListener;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class HistogramPanel extends GraphPanel{

	private JButton earnCategory = new JButton();
	private JButton costCategory = new JButton();
	private static HistogramPanel instance = new HistogramPanel();

	public HistogramPanel() {
		super(MainPanel.BACKGROUND);
		setLayout(new GridLayout(1,2));
		setButtonImage();
		setTextFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,20));
		JToolBar tb = new JToolBar();
		JToolBar tb1 = new JToolBar();
		
		setButtonColor(ColorUtil.blueColor);
		setButtonImage();
		setTextFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,20));
		
		tb.add(earnCategory);
		tb1.add(costCategory);
		tb.setFloatable(false);
		tb1.setFloatable(false);
		add(tb,BorderLayout.CENTER);
		add(tb1,BorderLayout.CENTER);
		addListener();
	}
	
	private void setButtonColor(Color color)
	{
		earnCategory.setForeground(color);
		costCategory.setForeground(color);
	}
	
	public void setButtonImage() 
	{
		GUIUtil.setButtonImage(earnCategory, "add1.png", "ÊÕÈëÖù×´Í¼");
		GUIUtil.setButtonImage(costCategory, "sub1.png", "Ö§³öÖù×´Í¼");
	}

	public void setTextFont(Font font) 
	{
		earnCategory.setFont(font);
		costCategory.setFont(font);
	}

	public JButton getEarnCategory() {
		return earnCategory;
	}

	public JButton getCostCategory() {
		return costCategory;
	}


	public static HistogramPanel getInstance() {
		return instance;
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener() {
		HistrogramListener listener = new HistrogramListener();
		costCategory.addActionListener(listener);
		earnCategory.addActionListener(listener);
	}

}
