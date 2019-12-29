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
		
		UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
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
			if( !GUIUtil.checkEmpty( inputUser, "�˺������" ) )
			{
				return;
			}
			
			//check the password text
			if( !GUIUtil.checkEmpty( inputPassword, "���������" ) )
			{
				return;
			}
			
			//check the passwordAgain text
			if( !GUIUtil.checkEmpty( inputPasswordAgain, "�ٴ����������" ) )
			{
				return;
			}
			
			if( !GUIUtil.checkIsIlleagalNumber(inputUser,inputaccount) )
			{
				JOptionPane.showMessageDialog(null, "��ȷ���˺�Ϊ9λ���µ�������!");
				return;
			}
			
			account = Integer.parseInt(inputaccount);
			
			if( userService.getUserByAccount(account) != null )
			{
				JOptionPane.showMessageDialog(null, "���˺��Ѿ�����!");
				return;
			}
			else
			{
				if( !registerPanel.getPassword().equals(registerPanel.getPasswordAgain()) )
				{
					JOptionPane.showMessageDialog(null, "�����������벻һ��!");
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "ע��ɹ�!");
					userService.createNewUser(account, password);
	            	MainFrame.getInstance().setContentPane(LoginPanel.getInstance());
	            	MainFrame.getInstance().setVisible(true);
					return ;
				}
			}
		}
	}

}
