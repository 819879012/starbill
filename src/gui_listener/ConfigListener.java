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
	    UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
	    
	    //define set
	    if( button == configPanel.getbSubmit() )
	    {
	    	if( !GUIUtil.checkEmpty(inputBudget, "本月预算") )
	    	{
	    		return;
	    	}
	    	
	    	if( !GUIUtil.checkEmpty(inputMysqlPath, "Mysql路径") )
	    	{
	    		return;
	    	}
	    	
	    	if( !GUIUtil.checkIsIlleagalNumber(inputBudget, "本月预算"))
	    	{
                JOptionPane.showMessageDialog(configPanel, "请确保本月预算为合法的数字!");
	    		return;
	    	}
	    	
	    	String mysqlPath =inputMysqlPath.getText();
	        if( 0!=mysqlPath.length() ){
	            File commandFile = new File(mysqlPath,"bin/mysql.exe");
	            if( !commandFile.exists() ){
	                JOptionPane.showMessageDialog(configPanel, "Mysql路径不正确");
	                inputMysqlPath.grabFocus();
	                return;
	            }
	        }
	        
			ConfigService configService = new ConfigService();
			configService.update(ConfigService.BUDGET,inputBudget.getText(),1);
	        configService.update(ConfigService.MYSQLPATH,mysqlPath,2);
	        JOptionPane.showMessageDialog(configPanel, "设置成功!");
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
	    	String showMessage = "<html><font size=20>"+"使用说明: \n 1.注册用户 \n 2.登陆用户\n 3.设置每个月预算和Mysql路径 \n 4.设置收支分类\n";
	    	UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
	        JOptionPane.showMessageDialog(configPanel, showMessage);
	        return;
	    }
	    
	}
}
