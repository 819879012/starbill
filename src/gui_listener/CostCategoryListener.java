package gui_listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import entity.CostCategory;
import gui_panel.CostCategoryPanel;
import service.CostCategoryService;

public class CostCategoryListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
    	
		UIManager.put("OptionPane.messageFont", new Font("宋体",Font.BOLD,30));
        CostCategoryPanel costCategoryPanel = CostCategoryPanel.getInstance();
        CostCategoryService costCategoryService = new CostCategoryService();
        JButton b = (JButton) e.getSource();
        
        if ( b == costCategoryPanel.getAddCostCategory() ) {
            String name = JOptionPane.showInputDialog(null,"请输入支出分类名称");
            if( name == null )
            	return;
            if ( 0 == name.length() ) {
                JOptionPane.showMessageDialog(costCategoryPanel, "支出分类名称不能为空");
                return;
            }
            costCategoryService.addCostCategory(name);
        }
        
        if ( b == costCategoryPanel.getChangeCostCategory() ) {
            CostCategory c = costCategoryPanel.getSelectedCostCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog("修改分类名称", c.getName());
            if( name == null )
            	return;
            if ( 0 == name.length() ) {
                JOptionPane.showMessageDialog(costCategoryPanel, "分类名称不能为空");
                return;
            }
            costCategoryService.updateCostCategory(id, name);
        }
        
        if ( b == costCategoryPanel.getDeleteCostCategory() ) {
            CostCategory c = costCategoryPanel.getSelectedCostCategory();
            if ( 0 != c.getRecordNumber() ) {
                JOptionPane.showMessageDialog(costCategoryPanel, "本分类下有消费记录存在，不能删除");
                return;
            }
            if ( JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(costCategoryPanel, "确认要删除？") )
                return;
            int id = c.getId();
            costCategoryService.deleteCostCategoryById(id);
        }
        costCategoryPanel.updateData();
    }

}
