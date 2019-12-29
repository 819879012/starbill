package gui_panel;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import gui_listener.LoginListener;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class LoginPanel extends GraphPanel {

	public static String LoginPanelBackground = "LoginPanel.jpg";
	private JLabel userText = new JLabel("«Î ‰»Î’À∫≈: ");
	private JLabel passwordText = new JLabel("«Î ‰»Î√‹¬Î: ");
	private JTextField inputUser = new JTextField(10);
	private JPasswordField inputPassword = new JPasswordField(10);
	private JButton register = new JButton("◊¢≤·’À∫≈");
	private JButton login = new JButton("µ«¬Ω’À∫≈");
	private static LoginPanel instance = new LoginPanel(); 
	
	public LoginPanel() {
		super(LoginPanelBackground);
		setLayout(null);
		addAllConponent();
		addListener();
	}
	
	private void addAllConponent() {
		
		JToolBar toolBar = new JToolBar();
		JToolBar toolBar1 = new JToolBar();
		
		toolBar.setBounds(550, 400, 100, 130);
		toolBar1.setBounds(700, 400, 100, 130);
		toolBar.setFloatable(false);
		toolBar1.setFloatable(false);
		
		setTextFont(new Font("Œ¢»Ì—≈∫⁄",Font.BOLD,20));
		setButtonImage();
		
		userText.setBounds(520, 220, 130, 46);
		inputUser.setBounds(650, 220, 170, 46);
		passwordText.setBounds(520, 300, 154, 46);
		inputPassword.setBounds(650, 300, 170, 46);
		
		add(userText);
		add(inputUser);
		add(passwordText);
		add(inputPassword);

		GUIUtil.setColor(ColorUtil.blueColor, register,login);
		toolBar.add(register);
		toolBar1.add(login);
		
		add(toolBar);
		add(toolBar1);
		
	}

	public void setTextFont(Font font) {
		userText.setFont(font);		
		passwordText.setFont(font);		
		inputUser.setFont(font);		
		inputPassword.setFont(font);		
		register.setFont(font);		
		login.setFont(font);		
	}
	
	public void setButtonImage()
	{
		GUIUtil.setButtonImage(register, "register.png", "◊¢≤·’À∫≈");
		GUIUtil.setButtonImage(login, "login.png", "µ«¬Ω’À∫≈");
	}
	
	public JLabel getUserText() {
		return userText;
	}

	public JLabel getPasswordText() {
		return passwordText;
	}

	public JTextField getInputUser() {
		return inputUser;
	}

	public JPasswordField getInputPassword() {
		return inputPassword;
	}

	public JButton getRegister() {
		return register;
	}

	public JButton getLogin() {
		return login;
	}

	public static LoginPanel getInstance() {
		return instance;
	}

	public String getUserAccount()
	{
		return inputUser.getText();
	}
	
	public String getPassword()
	{
		char[] password = inputPassword.getPassword();
		if( password.length != 0 && password != null )
		return String.valueOf(password);
		return null;
	}
	
	public int getAccount()
	{
		try
		{
			return Integer.parseInt(getUserAccount());
		}catch(NumberFormatException e) {
			return 0;
		}
	}
	
	@Override
	public void updateData() {
		inputUser.setText("");
		inputPassword.setText("");
	}

	@Override
	public void addListener() {
		LoginListener loginListener = new LoginListener();
		register.addActionListener(loginListener);
		login.addActionListener(loginListener);
		login.registerKeyboardAction(loginListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	public void showRegisterPanel(RegisterPanel p)
	{
        Component[] cs = getComponents();
        Component[] componentOfP = p.getComponents();
        for (Component jp : cs) {
            remove(jp);
        }
        for(Component jp : componentOfP)
        	add(jp);
        this.updateUI();
	}
	
}
