package gui_panel;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import gui_listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class RecoverPanel extends GraphPanel {

	private JButton recover = new JButton();
	private JButton recoverToMemory = new JButton();
	private static RecoverPanel instance = new RecoverPanel();
	
	public RecoverPanel() {
		super(MainPanel.BACKGROUND);
		setLayout(new GridLayout(1,2));
		setButtonImage();
		setTextFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,20));
		JToolBar tb = new JToolBar();
		JToolBar tb1 = new JToolBar();
		tb.add(recover);
		tb1.add(recoverToMemory);
		tb.setFloatable(false);
		tb1.setFloatable(false);
		add(tb);
		add(tb1);
		addListener();
	}
	
	public void setButtonImage() {
		GUIUtil.setColor(ColorUtil.blueColor, recover,recoverToMemory);
		GUIUtil.setButtonImage(recover, "recover.png", "»Ö¸´µ½sqlÎÄ¼þ");
		GUIUtil.setButtonImage(recoverToMemory, "recover.png", "»Ö¸´É¾³ýÊý¾Ý");
	}

	public JButton getRecoverToMemory() {
		return recoverToMemory;
	}

	public void setRecoverToMemory(JButton recoverToMemory) {
		this.recoverToMemory = recoverToMemory;
	}

	public JButton getRecover() {
		return recover;
	}

	public static RecoverPanel getInstance() {
		return instance;
	}

	@Override
	public void updateData() {
		this.updateUI();
	}

	@Override
	public void addListener() {
        RecoverListener listener = new RecoverListener();
        recover.addActionListener(listener);
        recoverToMemory.addActionListener(listener);
	}
	
	@Override
	public void setTextFont(Font font) {
		recover.setFont(font);
		recoverToMemory.setFont(font);
	}

}
