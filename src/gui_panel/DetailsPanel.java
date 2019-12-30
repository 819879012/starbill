package gui_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import gui_listener.DetailsListener;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class DetailsPanel extends GraphPanel{
	
	private JButton detailsEarn = new JButton();
	private JButton detailsCost = new JButton();
	private static DetailsPanel instance = new DetailsPanel();

	public DetailsPanel() {
		super(MainPanel.BACKGROUND);
		setLayout(new GridLayout(1,2));
		setButtonImage();
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		JToolBar tb = new JToolBar();
		JToolBar tb1 = new JToolBar();
		
		setButtonColor(ColorUtil.blueColor);
		setButtonImage();
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		
		tb.add(detailsEarn);
		tb1.add(detailsCost);
		tb.setFloatable(false);
		tb1.setFloatable(false);
		add(tb,BorderLayout.CENTER);
		add(tb1,BorderLayout.CENTER);
		addListener();
	}
	
	private void setButtonColor(Color color)
	{
		detailsEarn.setForeground(color);
		detailsCost.setForeground(color);
	}
	
	public void setButtonImage() 
	{
<<<<<<< HEAD
		GUIUtil.setButtonImage(detailsEarn, "add1.png", "收入明细查询");
		GUIUtil.setButtonImage(detailsCost, "sub1.png", "支出明细查询");
=======
		GUIUtil.setButtonImage(detailsEarn, "add1.png", "收入明细");
		GUIUtil.setButtonImage(detailsCost, "sub1.png", "支出明细");
>>>>>>> 鏄熸槦璁拌处绯荤粺
	}

	public void setTextFont(Font font) 
	{
		detailsEarn.setFont(font);
		detailsCost.setFont(font);
	}

	public JButton getDetailsEarn() {
		return detailsEarn;
	}

	public JButton getDetailsCost() {
		return detailsCost;
	}


	public static DetailsPanel getInstance() {
		return instance;
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener() {
		DetailsListener listener = new DetailsListener();
		detailsEarn.addActionListener(listener);
		detailsCost.addActionListener(listener);
	}

}
