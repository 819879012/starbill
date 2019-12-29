package gui_listener;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import gui_panel.ConfigPanel;
import gui_panel.MainPanel;
import gui_panel.RecoverPanel;
import service.ConfigService;
import service.RecoverService;
import util.MysqlUtil;
 
public class RecoverListener implements ActionListener {
 
    @Override
    public void actionPerformed(ActionEvent e) {
        RecoverPanel p = RecoverPanel.getInstance();
        RecoverService service = RecoverService.getInstance();
        JButton button = (JButton)e.getSource();
        if( button == p.getRecover() )
        {
            String mysqlPath= new ConfigService().getByKey(ConfigService.MYSQLPATH);
            if(0==mysqlPath.length()){
                JOptionPane.showMessageDialog(p, "ª÷∏¥«∞«Î ¬œ»≈‰÷√mysqlµƒ¬∑æ∂");
                MainPanel.getInstance().getWorkingPanel().show(ConfigPanel.getInstance());
                ConfigPanel.getInstance().getInputMysqlPath().grabFocus();
                return;
            }
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File("starbill.sql"));
            fc.setFileFilter(new FileFilter() {
                @Override
                public String getDescription() {
                    return ".sql";
                }
                 
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".sql");
                }
            });
     
             int returnVal =  fc.showOpenDialog(p);
             File file = fc.getSelectedFile();
             System.out.println(file);
             if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    MysqlUtil.recover(mysqlPath,file.getAbsolutePath());
                    JOptionPane.showMessageDialog(p, "ª÷∏¥≥…π¶");
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(p, "ª÷∏¥ ß∞‹\n,¥ÌŒÛ:\n"+e1.getMessage());
                }
             } 
        }
    
        if( button == p.getRecoverToMemory() )
        {
        	service.recoverAllData();
            JOptionPane.showMessageDialog(p,"ª÷∏¥≥…π¶!");
        }
        
    }
}