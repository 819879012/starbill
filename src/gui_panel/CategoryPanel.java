package gui_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;

import gui_listener.CategoryListener;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class CategoryPanel extends GraphPanel{

	private JButton earnCategory = new JButton();
	private JButton costCategory = new JButton();
	private static CategoryPanel instance = new CategoryPanel();

	public CategoryPanel() {
		super(MainPanel.BACKGROUND);
		setLayout(new GridLayout(1,2));
		setButtonImage();
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		JToolBar tb = new JToolBar();
		JToolBar tb1 = new JToolBar();
		
		setButtonColor(ColorUtil.blueColor);
		setButtonImage();
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		
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
		GUIUtil.setButtonImage(earnCategory, "add1.png", "收入分类");
		GUIUtil.setButtonImage(costCategory, "sub1.png", "支出分类");
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


	public static CategoryPanel getInstance() {
		return instance;
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener() {
		CategoryListener listener = new CategoryListener();
		costCategory.addActionListener(listener);
		earnCategory.addActionListener(listener);
	}

}
