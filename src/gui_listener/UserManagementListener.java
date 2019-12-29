package gui_listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui_frame.MainFrame;
import gui_panel.ConfigPanel;
import gui_panel.LoginPanel;
import gui_panel.MainPanel;
import gui_panel.UserManagementPanel;
import service.UsersService;

public class UserManagementListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		UserManagementPanel p = UserManagementPanel.getInstance();
		UsersService userSercice = new UsersService();
		
		if( button == p.getBackToConfigPanel() )
		{
			MainPanel mainPanel = MainPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(ConfigPanel.getInstance());
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		
		if( button == p.getExitLogin() )
		{
			LoginPanel loginPanel = LoginPanel.getInstance();
        	MainFrame.getInstance().setContentPane(loginPanel);
        	loginPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		
		if( button == p.getChangePassword() )
		{
			String newPassword = JOptionPane.showInputDialog("请输入修改后的密码:");
			if( newPassword == null )
				return;
			if( newPassword.length() == 0 )
			{
				JOptionPane.showMessageDialog(null, "修改后的密码不能为空!");
				return;
			}
			else
			{
				userSercice.updateUser(userSercice.getNowAccount(), newPassword);
				JOptionPane.showMessageDialog(null, "修改成功!");
				return;
			}
		}
		
		if( button == p.getDeleteUser() )
		{
			LoginPanel loginPanel = LoginPanel.getInstance();
			userSercice.deleteAllUserInfomation();
			JOptionPane.showMessageDialog(null, "注销成功!");
        	MainFrame.getInstance().setContentPane(loginPanel);
        	loginPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return;
		}
	}
}
