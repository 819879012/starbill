package gui_panel;


import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;

import gui_listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class BackupPanel extends GraphPanel {

	private JButton backupToMysql = new JButton();
	private JButton backupToExcel = new JButton();
	private static BackupPanel instance = new BackupPanel();
	
	public BackupPanel() {
		super(MainPanel.BACKGROUND);
		setLayout(new GridLayout(1,2));
		setButtonImage();
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		addAllJcomponents();
		addListener();
	}
	
	private void addAllJcomponents() {
		JToolBar tb = new JToolBar();
		JToolBar tb1 = new JToolBar();
		tb.add(backupToMysql);
		tb1.add(backupToExcel);
		tb.setFloatable(false);
		tb1.setFloatable(false);
		add(tb,BorderLayout.WEST);
		add(tb1,BorderLayout.EAST);
	}

	public void setTextFont(Font font)
	{
		backupToMysql.setFont(font);
		backupToExcel.setFont(font);
	}
	
	public void setButtonImage() {
		GUIUtil.setColor(ColorUtil.blueColor, backupToExcel,backupToMysql);
		GUIUtil.setButtonImage(backupToMysql, "backupButton.png", "备份到数据库");
		GUIUtil.setButtonImage(backupToExcel, "backupButton.png", "备份到本地");
	}
	
	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListener() {
        BackupListener listener = new BackupListener();
        backupToMysql.addActionListener(listener);
        backupToExcel.addActionListener(listener);
	}
	
	public JButton getBackupToMysql() {
		return backupToMysql;
	}

	public JButton getBackupToExcel() {
		return backupToExcel;
	}

	public static BackupPanel getInstance() {
		return instance;
	}

}
