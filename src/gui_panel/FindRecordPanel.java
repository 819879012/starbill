package gui_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import gui_listener.FindRecordListener;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class FindRecordPanel extends GraphPanel{

	private JButton findEarnRecord = new JButton();
	private JButton findCostRecord = new JButton();
	private static FindRecordPanel instance = new FindRecordPanel();

	public FindRecordPanel() {
		super(MainPanel.BACKGROUND);
		setLayout(new GridLayout(1,2));
		setButtonImage();
		setTextFont(new Font("Œ¢»Ì—≈∫⁄",Font.BOLD,20));
		JToolBar tb = new JToolBar();
		JToolBar tb1 = new JToolBar();
		
		setButtonColor(ColorUtil.blueColor);
		setButtonImage();
		setTextFont(new Font("Œ¢»Ì—≈∫⁄",Font.BOLD,20));
		
		tb.add(findEarnRecord);
		tb1.add(findCostRecord);
		tb.setFloatable(false);
		tb1.setFloatable(false);
		add(tb,BorderLayout.CENTER);
		add(tb1,BorderLayout.CENTER);
		addListener();
	}
	
	private void setButtonColor(Color color)
	{
		findEarnRecord.setForeground(color);
		findCostRecord.setForeground(color);
	}
	
	public void setButtonImage() 
	{
		GUIUtil.setButtonImage(findEarnRecord, "add1.png", " ’»Î≤È—Ø");
		GUIUtil.setButtonImage(findCostRecord, "sub1.png", "÷ß≥ˆ≤È—Ø");
	}

	public void setTextFont(Font font) 
	{
		findEarnRecord.setFont(font);
		findCostRecord.setFont(font);
	}

	public JButton getFindEarnRecord() {
		return findEarnRecord;
	}

	public JButton getFindCostRecord() {
		return findCostRecord;
	}


	public static FindRecordPanel getInstance() {
		return instance;
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener() {
		FindRecordListener listener = new FindRecordListener();
		findCostRecord.addActionListener(listener);
		findEarnRecord.addActionListener(listener);
	}
}
