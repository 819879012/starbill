package gui_panel;
 

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import util.GUIUtil;
 
//设置主页背景图片的JPnel类
@SuppressWarnings("serial")
public abstract class GraphPanel extends JPanel {
	
	ImageIcon icon;
	Image img;
    
	public GraphPanel( String imgName ) {
		GUIUtil.useLNF();
		icon = new ImageIcon(new File(GUIUtil.IMG_FOLDERNAME,"/"+imgName).getAbsolutePath());
		img=icon.getImage();
		this.setVisible(true);
	}

	public GraphPanel()
	{
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
	}
	
    public abstract void updateData();
    public abstract void addListener();
    public abstract void setButtonImage();
    public abstract void setTextFont( Font font );
}