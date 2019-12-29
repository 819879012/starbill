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

import gui_listener.RegisterListener;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class RegisterPanel extends GraphPanel {

	private JLabel userText = new JLabel("«Î ‰»Î’À∫≈: ");
	private JLabel passwordText = new JLabel("«Î ‰»Î√‹¬Î: ");
	private JLabel passwordAgainText = new JLabel("«Î»∑»œ√‹¬Î: ");
	private JTextField inputUser = new JTextField(10);
	private JPasswordField inputPassword = new JPasswordField(10);
	private JPasswordField inputPasswordAgain = new JPasswordField(10);
	private JButton register = new JButton("»∑»œ◊¢≤·");
	private JButton back = new JButton("∑µªÿµ«¬Ω");
	private static RegisterPanel instance = new RegisterPanel();
	
	public RegisterPanel() {
		super(LoginPanel.LoginPanelBackground);
		setLayout(null); 
		addAllComponent();
		addListener();
	}
	
 	private void addAllComponent() {
 		
 		JToolBar toolBar = new JToolBar(); 
 		JToolBar toolBar1 = new JToolBar();
 		
		setTextFont(new Font("Œ¢»Ì—≈∫⁄",Font.BOLD,20));
		setButtonImage();
		
		userText.setBounds(520, 230, 119, 27);
		passwordText.setBounds(520, 300, 119, 27);
		passwordAgainText.setBounds(520, 360, 119, 27);
		inputUser.setBounds(640, 230, 170, 36);
		inputPassword.setBounds(640, 300, 170, 36);
		inputPasswordAgain.setBounds(640, 360, 170, 36);
		
		toolBar.setBounds(550, 420, 100, 130);
		toolBar1.setBounds(700, 420, 100, 130);
		toolBar.setFloatable(false);
		toolBar1.setFloatable(false);
		
		toolBar.add(register);
		toolBar1.add(back);
		
		add(userText);
		add(passwordText);
		add(passwordAgainText);
		add(inputUser);
		add(inputPassword);
		add(inputPasswordAgain);
		add(toolBar);
		add(toolBar1);
		
	}

	public void setButtonImage() {
		GUIUtil.setColor(ColorUtil.blueColor, register,back);
		GUIUtil.setButtonImage(register, "confirmRegister.png", "»∑»œ◊¢≤·");
		GUIUtil.setButtonImage(back, "back.png", "∑µªÿµ«¬Ω");
	}

	public void setTextFont(Font font) {
		userText.setFont(font);
		passwordText.setFont(font);
		passwordAgainText.setFont(font);
		inputUser.setFont(font);
		inputPassword.setFont(font);
		inputPasswordAgain.setFont(font);
		register.setFont(font);
		back.setFont(font);
	}
	
	public void showLoginPanel(LoginPanel p)
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

	@Override
	public void updateData() {
		inputUser.setText("");
		inputPassword.setText("");
		inputPasswordAgain.setText("");
	}

	@Override
	public void addListener() {
		RegisterListener registerListener = new RegisterListener();
		register.addActionListener(registerListener);
		back.addActionListener(registerListener);
		register.registerKeyboardAction(registerListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	public JLabel getUserText() {
		return userText;
	}

	public void setUserText(JLabel userText) {
		this.userText = userText;
	}

	public JLabel getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(JLabel passwordText) {
		this.passwordText = passwordText;
	}

	public JLabel getPasswordAgainText() {
		return passwordAgainText;
	}

	public void setPasswordAgainText(JLabel passwordAgainText) {
		this.passwordAgainText = passwordAgainText;
	}

	public JTextField getInputUser() {
		return inputUser;
	}

	public void setInputUser(JTextField inputUser) {
		this.inputUser = inputUser;
	}

	public JPasswordField getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(JPasswordField inputPassword) {
		this.inputPassword = inputPassword;
	}

	public JPasswordField getInputPasswordAgain() {
		return inputPasswordAgain;
	}

	public void setInputPasswordAgain(JPasswordField inputPasswordAgain) {
		this.inputPasswordAgain = inputPasswordAgain;
	}

	public JButton getRegister() {
		return register;
	}

	public void setRegister(JButton register) {
		this.register = register;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

	public static RegisterPanel getInstance() {
		return instance;
	}

	public String getPassword()
	{
		char[] password = inputPassword.getPassword();
		if( password.length != 0 && password != null )
		return String.valueOf(password);
		return null;
	}
	
	public String getPasswordAgain()
	{
		char[] password = inputPasswordAgain.getPassword();
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
	
	public String getUserAccount()
	{
		return inputUser.getText();
	}
	
	public static void setInstance(RegisterPanel instance) {
		RegisterPanel.instance = instance;
	}

}
