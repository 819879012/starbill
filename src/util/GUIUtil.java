package util;

import java.awt.Color;

import gui_panel.CenterPanel;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui_frame.*;


/**
* this is a utilTool class for guiProgram
* it has some function to check user's input
* at the same time, it has some other functions
* such as setBackground Image
* @author RenJiaCheng
* @Date 2019/11/8
*/

public class GUIUtil {
	
	// this is save all the background image and all the icon image file name.
	public final static String IMG_FOLDERNAME = "img";
	
	public static void setButtonImage( JButton needSetButton, String imgName, String buttonName )
	{
		ImageIcon icon = new ImageIcon(new File(IMG_FOLDERNAME,imgName).getAbsolutePath());
		needSetButton.setIcon(icon);
		needSetButton.setSize(new Dimension(31,41));
		needSetButton.setToolTipText(buttonName);
		needSetButton.setText(buttonName);
		needSetButton.setVerticalTextPosition(JButton.BOTTOM);
		needSetButton.setHorizontalTextPosition(JButton.CENTER);
	}
	
	public static boolean checkEmpty( JTextField inputText, String input )
	{
		String inputMessage = inputText.getText().trim();
		if( inputMessage == null || inputMessage.length() == 0 )
		{
			JOptionPane.showMessageDialog( null, input + "不能为空" );
			inputText.grabFocus();
			return false;
		}
		return true;
	}
	
	public static boolean checkEmpty( JPasswordField inputText, String input )
	{
		char[] inputMessage = inputText.getPassword();
		if( inputMessage == null || inputMessage.length == 0 )
		{
			JOptionPane.showMessageDialog( null, input + "不能为空" );
			inputText.grabFocus();
			return false;
		}
		return true;
	}
	
	public static boolean checkIsIlleagalNumber( JTextField inputText , String input )
	{
		if( !checkEmpty( inputText, input ) )
		{
			return false;
		}
		else
		{
			try {
				String inputMessage = inputText.getText().trim();
				double num = Double.parseDouble(inputMessage);
				if( num < 0 )
				{
					inputText.grabFocus();
					return false;
				}
				return true;
			}catch(NumberFormatException e) {
				inputText.grabFocus();
				return false;
			}
		}
	}
	
	public static void setColor( Color color, JComponent... cs)
	{
		for (JComponent jComponent : cs) {
			jComponent.setForeground(color);
		}
	}
	
	public static void showPanel(JPanel p,double strech) {
		MainFrame f = MainFrame.getInstance();
	    JFrame showFrame = new JFrame();
	    showFrame.setTitle("欢迎使用星星记账系统!");
	    showFrame.setSize(f.getShowFrameWidth(), f.getShowFrameHeight());
	    showFrame.setLocationRelativeTo(null);
	    CenterPanel cp = new CenterPanel(strech);
	    showFrame.setContentPane(cp);
	    showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    showFrame.setVisible(true);
	    cp.show(p);
	}
	
	public static void showPanel(JPanel p,double strech,int width,int height) {
	    JFrame showFrame = new JFrame();
	    showFrame.setTitle("欢迎使用星星记账系统!");
	    showFrame.setSize(width, height);
	    showFrame.setLocationRelativeTo(null);
	    CenterPanel cp = new CenterPanel(strech);
	    showFrame.setContentPane(cp);
	    showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    showFrame.setVisible(true);
	    cp.show(p);
	}
	
    //加载皮肤
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static double getDouble( JTextField inputText ) {
        return Double.parseDouble(inputText.getText());
    }
    
}
