package gui_panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import gui_listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;
 
@SuppressWarnings("serial")
public class ConfigPanel extends GraphPanel {
 
    private static ConfigPanel instance = new ConfigPanel();
    private JLabel showBudgetText = new JLabel("本月预算(￥)");
    private JTextField inputBudget = new JTextField();
    private JLabel showMysqlText = new JLabel("Mysql安装目录");
    private JTextField inputMysqlPath = new JTextField("");
    
    private JButton bSubmit = new JButton("确认设置");
    private JButton userManagement = new JButton("用户管理");
    private JButton bAboutSystem = new JButton("关于系统");

    public ConfigPanel() {
    	super(MainPanel.BACKGROUND);
    	setTextFont(new Font("微软雅黑",Font.BOLD,20));
    	setButtonImage();
    	addPanel();
        addListener();
    }

    private void addPanel() {
    	
        UtilPanel pInput = new UtilPanel(MainPanel.BACKGROUND);
        UtilPanel pSubmit = new UtilPanel(MainPanel.BACKGROUND);
        
        JToolBar tb = new JToolBar();
        
        int gap = 40;
        pInput.setLayout(new GridLayout(4, 1, gap, gap));
 
        pInput.add(showBudgetText);
        pInput.add(inputBudget);
        pInput.add(showMysqlText);
        pInput.add(inputMysqlPath);
        
        tb.add(bSubmit);
        tb.add(userManagement);
        tb.add(bAboutSystem);
        tb.setFloatable(false);
        
        pSubmit.add(tb,BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit, BorderLayout.CENTER);
	}

	public void setButtonImage() {
    	GUIUtil.setColor(ColorUtil.blueColor, bSubmit,bAboutSystem,userManagement);
		GUIUtil.setButtonImage(bSubmit, "confirmSet.png", "确认设置");
		GUIUtil.setButtonImage(bAboutSystem, "help.png", "关于系统");
		GUIUtil.setButtonImage(userManagement, "aboutSystem.png", "用户管理");
	}

	public void setTextFont(Font font) {
    	showBudgetText.setFont(font);
    	inputBudget.setFont(font);
    	showMysqlText.setFont(font);
    	inputMysqlPath.setFont(font);
    	bSubmit.setFont(font);
    	bAboutSystem.setFont(font);
    	userManagement.setFont(font);
	}

	public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance,0.7);
    }

    public static ConfigPanel getInstance() {
		return instance;
	}

	public JLabel getShowBudgetText() {
		return showBudgetText;
	}

	public JTextField getInputBudget() {
		return inputBudget;
	}

	public JLabel getShowMysqlText() {
		return showMysqlText;
	}

	public JTextField getInputMysqlPath() {
		return inputMysqlPath;
	}

	public JButton getbSubmit() {
		return bSubmit;
	}

	public JButton getbAboutSystem() {
		return bAboutSystem;
	}

	public JButton getUserManagement() {
		return userManagement;
	}

	@Override
    public void addListener() {
        ConfigListener configListener = new ConfigListener();
        bSubmit.addActionListener(configListener);
        userManagement.addActionListener(configListener);
        bAboutSystem.addActionListener(configListener);
    }

    @Override
    public void updateData() {
    	ConfigService.init();
        String budget = new ConfigService().getNowUsersBudget();
        String mysqlPath = new ConfigService().getNowUsersMysqlPath();
        inputBudget.setText(budget);
        inputMysqlPath.setText(mysqlPath);
        inputBudget.grabFocus();
    }

}