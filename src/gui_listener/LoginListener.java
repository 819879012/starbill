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
import gui_panel.MainPanel;
import gui_panel.RegisterPanel;
import service.UsersService;
import util.GUIUtil;

public class LoginListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
		LoginPanel loginPanel = LoginPanel.getInstance();
		JButton button = (JButton) e.getSource();
		JTextField inputAccount = loginPanel.getInputUser();
		JPasswordField inputPassword = loginPanel.getInputPassword();
		UsersService userService = new UsersService();
		String accountOfInput = String.valueOf(loginPanel.getAccount());
		int account;
		String password = loginPanel.getPassword();
		
		if( button == loginPanel.getRegister() )
		{
			RegisterPanel registerPanel = RegisterPanel.getInstance();
        	MainFrame.getInstance().setContentPane(registerPanel);
        	registerPanel.updateData();
        	MainFrame.getInstance().setVisible(true);
			return ;
		}
		
		if( button == loginPanel.getLogin() )
		{
			//check the account text
			if( !GUIUtil.checkEmpty( inputAccount, "�˺������" ) )
			{
				return;
			}
			
			//check the password text
			if( !GUIUtil.checkEmpty( inputPassword, "���������" ) )
			{
				return;
			}
			
			if( !GUIUtil.checkIsIlleagalNumber(inputAccount,accountOfInput) )
			{
				JOptionPane.showMessageDialog(null, "��ȷ���˺�Ϊ9λ���µ�������!");
				return;
			}
			
			account = loginPanel.getAccount();
			//check the account length
			
			if( account != 0 && account > 0 )
			{
				if( userService.checkUserExist(account, password) )
				{
					MainPanel mainPanel = MainPanel.getInstance();
					JOptionPane.showMessageDialog( null, "��½�ɹ�!" );
	            	MainFrame.getInstance().setContentPane(mainPanel);
	            	MainFrame.getInstance().setVisible(true);
					return ;
				}
				else
				{
					//check the account is exist or not
					if( !userService.findUser(account) )
					{
						JOptionPane.showMessageDialog( null, "���û���δע����˺��������!" );
						return;
					}
					//check the password is right or not
					else
					{
						JOptionPane.showMessageDialog( null, "�������!" );
						return;
					}
				}
			}
		}
	}
}
