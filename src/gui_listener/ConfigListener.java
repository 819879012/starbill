package gui_listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import gui_frame.MainFrame;
import gui_panel.ConfigPanel;
import gui_panel.MainPanel;
import gui_panel.UserManagementPanel;
import service.ConfigService;
import util.GUIUtil;

public class ConfigListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		ConfigPanel configPanel = ConfigPanel.getInstance();
	    JTextField inputBudget = configPanel.getInputBudget();
	    JTextField inputMysqlPath = configPanel.getInputMysqlPath();
	    JButton button = (JButton)e.getSource();
	    UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
	    
	    //define set
	    if( button == configPanel.getbSubmit() )
	    {
	    	if( !GUIUtil.checkEmpty(inputBudget, "����Ԥ��") )
	    	{
	    		return;
	    	}
	    	
	    	if( !GUIUtil.checkEmpty(inputMysqlPath, "Mysql·��") )
	    	{
	    		return;
	    	}
	    	
	    	if( !GUIUtil.checkIsIlleagalNumber(inputBudget, "����Ԥ��"))
	    	{
                JOptionPane.showMessageDialog(configPanel, "��ȷ������Ԥ��Ϊ�Ϸ�������!");
	    		return;
	    	}
	    	
	    	String mysqlPath =inputMysqlPath.getText();
	        if( 0!=mysqlPath.length() ){
	            File commandFile = new File(mysqlPath,"bin/mysql.exe");
	            if( !commandFile.exists() ){
	                JOptionPane.showMessageDialog(configPanel, "Mysql·������ȷ");
	                inputMysqlPath.grabFocus();
	                return;
	            }
	        }
	        
			ConfigService configService = new ConfigService();
			configService.update(ConfigService.BUDGET,inputBudget.getText(),1);
	        configService.update(ConfigService.MYSQLPATH,mysqlPath,2);
	        JOptionPane.showMessageDialog(configPanel, "���óɹ�!");
	    }
	    
	    //users management
	    if( button == configPanel.getUserManagement() )
	    {
	    	MainPanel mainPanel = MainPanel.getInstance();
        	MainFrame.getInstance().setContentPane(mainPanel);
        	mainPanel.getWorkingPanel().show(UserManagementPanel.getInstance());
        	MainFrame.getInstance().setVisible(true);
			return ;
	    }
	    
	    //about system
	    if( button == configPanel.getbAboutSystem() )
	    {
	    	String showMessage = "<html><font size=20>"+"ʹ��˵��: \n 1.ע���û� \n 2.��½�û�\n 3.����ÿ����Ԥ���Mysql·�� \n 4.������֧����\n";
	    	UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
	        JOptionPane.showMessageDialog(configPanel, showMessage);
	        return;
	    }
	    
	}
}
