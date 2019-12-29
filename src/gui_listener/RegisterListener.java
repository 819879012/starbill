package gui_listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import gui_frame.MainFrame;
import gui_panel.LoginPanel;
import gui_panel.RegisterPanel;
import service.UsersService;
import util.GUIUtil;

public class RegisterListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
		RegisterPanel registerPanel = RegisterPanel.getInstance();
		JButton button = (JButton)e.getSource();
		UsersService userService = new UsersService();
		JTextField inputUser = registerPanel.getInputUser();
		JPasswordField inputPassword = registerPanel.getInputPassword();
		JPasswordField inputPasswordAgain = registerPanel.getInputPasswordAgain();
		String inputaccount = String.valueOf(registerPanel.getAccount());
		int account;
		String password = registerPanel.getPassword();
		
		if( button == registerPanel.getBack() )
		{
			LoginPanel loginPanel = LoginPanel.getInstance();
        	MainFrame.getInstance().setContentPane(loginPanel);
        	loginPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		
		if( button == registerPanel.getRegister() )
		{
			//check the inputUser is null or not
			if( !GUIUtil.checkEmpty( inputUser, "账号输入框" ) )
			{
				return;
			}
			
			//check the password text
			if( !GUIUtil.checkEmpty( inputPassword, "密码输入框" ) )
			{
				return;
			}
			
			//check the passwordAgain text
			if( !GUIUtil.checkEmpty( inputPasswordAgain, "再次输入密码框" ) )
			{
				return;
			}
			
			if( !GUIUtil.checkIsIlleagalNumber(inputUser,inputaccount) )
			{
				JOptionPane.showMessageDialog(null, "请确保账号为9位以下的正整数!");
				return;
			}
			
			account = Integer.parseInt(inputaccount);
			
			if( userService.getUserByAccount(account) != null )
			{
				JOptionPane.showMessageDialog(null, "该账号已经存在!");
				return;
			}
			else
			{
				if( !registerPanel.getPassword().equals(registerPanel.getPasswordAgain()) )
				{
					JOptionPane.showMessageDialog(null, "两次输入密码不一致!");
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "注册成功!");
					userService.createNewUser(account, password);
	            	MainFrame.getInstance().setContentPane(LoginPanel.getInstance());
	            	MainFrame.getInstance().setVisible(true);
					return ;
				}
			}
		}
	}

}
