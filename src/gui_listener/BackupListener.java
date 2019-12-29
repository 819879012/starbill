package gui_listener;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import gui_panel.BackupPanel;
import gui_panel.ConfigPanel;
import gui_panel.MainPanel;
import service.BackupService;
import service.ConfigService;
import util.MysqlUtil;
 
public class BackupListener implements ActionListener {
 
    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p  =BackupPanel.getInstance();
        JButton button = (JButton)e.getSource();
        
        if( button == p.getBackupToMysql() )
        {
            String mysqlPath= new ConfigService().getByKey(ConfigService.MYSQLPATH);
            if(0==mysqlPath.length()){
                JOptionPane.showMessageDialog(p, "����ǰ����������mysql��·��");
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
            
             int returnVal =  fc.showSaveDialog(p);
             File file = fc.getSelectedFile();
             System.out.println(file);
             if (returnVal == JFileChooser.APPROVE_OPTION) {
                 //���������ļ���û����.sql��β���Զ�����.sql
                 System.out.println(file);
                 if(!file.getName().toLowerCase().endsWith(".sql"))
                     file = new File(file.getParent(),file.getName()+".sql");
                 System.out.println(file);
                try {
                    MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
                    JOptionPane.showMessageDialog(p, "���ݳɹ�,�����ļ�λ��:\n"+file.getAbsolutePath());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(p, "����ʧ��\n,����:\n"+e1.getMessage());  
                }
             }
        }
        
        if( button == p.getBackupToExcel() )
        {
        	try {
				BackupService.saveAllData();
				JOptionPane.showMessageDialog(null, "���������Ѿ����浽backupDocuments�ļ���!");
				return;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        
    }
}