package gui_listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import entity.IncomeCategory;
import gui_panel.IncomeCategoryPanel;
import service.IncomeCategoryService;

public class IncomeCategoryListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
    	
		UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
		IncomeCategoryPanel incomeCategoryPanel = IncomeCategoryPanel.getInstance();
        IncomeCategoryService IncomeCategoryService = new IncomeCategoryService();
        JButton b = (JButton) e.getSource();
        
        if ( b == incomeCategoryPanel.getAddCategory() ) {
            String name = JOptionPane.showInputDialog(null,"请输入收入分类名称");
            if( name == null )
            	return;
            if ( 0 == name.length() ) {
                JOptionPane.showMessageDialog(incomeCategoryPanel, "收入分类名称不能为空");
                return;
            }
            IncomeCategoryService.addIncomeCategory(name);
        }
        
        if ( b == incomeCategoryPanel.getChangeCategory() ) {
            IncomeCategory c = incomeCategoryPanel.getSelectedIncomeCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog("修改分类名称", c.getName());
            if( name == null )
            	return;
            if ( 0 == name.length() ) {
                JOptionPane.showMessageDialog(incomeCategoryPanel, "分类名称不能为空");
                return;
            }
            IncomeCategoryService.updateIncomeCategory(id, name);
        }
        
        if ( b == incomeCategoryPanel.getDeleteIncomeCategory() ) {
            IncomeCategory c = incomeCategoryPanel.getSelectedIncomeCategory();
            if ( 0 != c.getRecordNumber() ) {
                JOptionPane.showMessageDialog(incomeCategoryPanel, "本分类下有收入记录存在，不能删除");
                return;
            }
            if ( JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(incomeCategoryPanel, "确认要删除？") )
                return;
            int id = c.getId();
            IncomeCategoryService.deleteIncomeCategoryById(id);
        }
        
        incomeCategoryPanel.updateData();
    }

}
