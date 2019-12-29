package gui_frame;
 
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import constant.TagXml;
import gui_panel.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
    private static MainFrame instance = new MainFrame();
    private ImageIcon icon = TagXml.icon;
    private int showFrameWidth = 1350;
    private int showFrameHeight = 800;
    
    private MainFrame(){
        this.setSize(showFrameWidth,showFrameHeight);
        this.setTitle("–«–«º«’ÀœµÕ≥");
        this.setIconImage(icon.getImage());
        this.setContentPane(MainPanel.getInstance());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public int getShowFrameWidth() {
		return showFrameWidth;
	}

	public void setShowFrameWidth(int showFrameWidth) {
		this.showFrameWidth = showFrameWidth;
	}

	public int getShowFrameHeight() {
		return showFrameHeight;
	}

	public void setShowFrameHeight(int showFrameHeight) {
		this.showFrameHeight = showFrameHeight;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public static MainFrame getInstance() {
		return instance;
	}
    
}