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
    	
		UIManager.put("OptionPane.messageFont", new Font("����",Font.BOLD,30));
		IncomeCategoryPanel incomeCategoryPanel = IncomeCategoryPanel.getInstance();
        IncomeCategoryService IncomeCategoryService = new IncomeCategoryService();
        JButton b = (JButton) e.getSource();
        
        if ( b == incomeCategoryPanel.getAddCategory() ) {
            String name = JOptionPane.showInputDialog(null,"�����������������");
            if( name == null )
            	return;
            if ( 0 == name.length() ) {
                JOptionPane.showMessageDialog(incomeCategoryPanel, "����������Ʋ���Ϊ��");
                return;
            }
            IncomeCategoryService.addIncomeCategory(name);
        }
        
        if ( b == incomeCategoryPanel.getChangeCategory() ) {
            IncomeCategory c = incomeCategoryPanel.getSelectedIncomeCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog("�޸ķ�������", c.getName());
            if( name == null )
            	return;
            if ( 0 == name.length() ) {
                JOptionPane.showMessageDialog(incomeCategoryPanel, "�������Ʋ���Ϊ��");
                return;
            }
            IncomeCategoryService.updateIncomeCategory(id, name);
        }
        
        if ( b == incomeCategoryPanel.getDeleteIncomeCategory() ) {
            IncomeCategory c = incomeCategoryPanel.getSelectedIncomeCategory();
            if ( 0 != c.getRecordNumber() ) {
                JOptionPane.showMessageDialog(incomeCategoryPanel, "���������������¼���ڣ�����ɾ��");
                return;
            }
            if ( JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(incomeCategoryPanel, "ȷ��Ҫɾ����") )
                return;
            int id = c.getId();
            IncomeCategoryService.deleteIncomeCategoryById(id);
        }
        
        incomeCategoryPanel.updateData();
    }

}
