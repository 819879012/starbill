package gui_panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;

import gui_listener.UserManagementListener;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class UserManagementPanel extends GraphPanel{

	private JButton changePassword = new JButton();
	private JButton deleteUser = new JButton();
	private JButton exitLogin = new JButton();
	private JButton backToConfigPanel = new JButton();
	private static UserManagementPanel instance = new UserManagementPanel();
	
	public UserManagementPanel() {
		super(MainPanel.BACKGROUND);
		setLayout(new BorderLayout());
		setTextFont(new Font("微软雅黑",Font.BOLD,20));
		setButtonImage();
		addPanel();
	}
	
	private void addPanel() {
		
		UtilPanel centerPanel = new UtilPanel(MainPanel.BACKGROUND);
		
		centerPanel.setLayout(new GridLayout(2,2));
		JToolBar tb1 = new JToolBar();
		JToolBar tb2 = new JToolBar();
		JToolBar tb3 = new JToolBar();
		JToolBar tb4 = new JToolBar();
		
		tb1.add(changePassword);
		tb2.add(deleteUser);
		tb3.add(exitLogin);
		tb4.add(backToConfigPanel);
		
		tb1.setFloatable(false);
		tb2.setFloatable(false);
		tb3.setFloatable(false);
		tb4.setFloatable(false);
		
		centerPanel.add(tb1);
		centerPanel.add(tb2);
		centerPanel.add(tb3);
		centerPanel.add(tb4);
		addListener();
		add(centerPanel,BorderLayout.CENTER);
	}

	public void setButtonImage()
	{
		GUIUtil.setButtonImage(changePassword,"changePassword.png", "修改密码");
		GUIUtil.setButtonImage(deleteUser,"deleteUser.png", "注销用户");
		GUIUtil.setButtonImage(exitLogin,"return.png", "退出登陆");
		GUIUtil.setButtonImage(backToConfigPanel,"back.png", "返回设置界面");
		GUIUtil.setColor(ColorUtil.blueColor, changePassword,deleteUser,exitLogin,backToConfigPanel);
	}
	
	public void setTextFont(Font font )
	{
		changePassword.setFont(font);
		deleteUser.setFont(font);
		exitLogin.setFont(font);
		backToConfigPanel.setFont(font);
	}
	
	public JButton getChangePassword() {
		return changePassword;
	}

	public JButton getDeleteUser() {
		return deleteUser;
	}

	public JButton getExitLogin() {
		return exitLogin;
	}

	public JButton getBackToConfigPanel() {
		return backToConfigPanel;
	}

	public static UserManagementPanel getInstance() {
		return instance;
	}

	@Override
	public void updateData() {
		
	}

	@Override
	public void addListener() {
		UserManagementListener listener = new UserManagementListener();
		changePassword.addActionListener(listener);
		deleteUser.addActionListener(listener);
		exitLogin.addActionListener(listener);
		backToConfigPanel.addActionListener(listener);
	}

}
