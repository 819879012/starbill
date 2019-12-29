package gui_panel;
  
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
  
@SuppressWarnings("serial")
public class CenterPanel extends JPanel {
  
    private double rate;//�������
    private JComponent c; //��ʾ�����
    private boolean strech; //�Ƿ�����
  
    public CenterPanel(double rate,boolean strech) {
        this.setLayout(null);
        this.rate = rate;
        this.strech = strech;
    }
      
    public CenterPanel(double rate) {
        this(rate,true);
    }  
  
    public void repaint() {
        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize= c.getPreferredSize();
              
            if(strech)
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            else
                c.setSize(componentSize);
  
            c.setLocation(containerSize.width / 2 - c.getSize().width / 2, containerSize.height / 2 - c.getSize().height / 2);
        }
        super.repaint();
    }

    public void show(JComponent p) {
        this.c = p;
        Component[] cs = getComponents();
        for (Component c : cs) {
            remove(c);
        }
        
        if (p instanceof GraphPanel)
            ((GraphPanel) p).updateData();
        
        add(p);
        this.updateUI();
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(200, 200);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(0.85,true);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JButton b  =new JButton("abc");
        cp.show(b);
          
    }
  
}