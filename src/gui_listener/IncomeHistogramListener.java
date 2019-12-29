package gui_listener;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import entity.IncomeRecord;
import gui_panel.IncomeHistogramPanel;
import service.IncomeHistogramService;
import util.ChartUtil;

public class IncomeHistogramListener implements ActionListener {

	private static String year = null;
	private static String month = null;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		IncomeHistogramPanel panel = IncomeHistogramPanel.getInstance();
		IncomeHistogramService service = new IncomeHistogramService();
		JButton button = (JButton)e.getSource();

		if( button == panel.getChooseYearButton() )
		{
            year = JOptionPane.showInputDialog(null,"请输入年份!");
            if( year == null )
            	return;
            if ( 0 == year.length() ) {
                JOptionPane.showMessageDialog(panel, "年份不能为空!");
                return;
            }
            try {
            	Integer.parseInt(year);
            }catch(NumberFormatException e1 ){
                JOptionPane.showMessageDialog(panel, "请输入正确的年份!");
                return;
            }
            if( year.length() != 4 )
            {
                JOptionPane.showMessageDialog(panel, "请输入正确的年份!");
                return;
            }
		}
		
		if( button == panel.getChooseMonthButton() )
		{
            month = JOptionPane.showInputDialog(null,"请输入月份!");
            if( month == null )
            	return;
            if ( 0 == month.length() ) {
                JOptionPane.showMessageDialog(panel, "月份不能为空!");
                return;
            }
            try {
            	int num = Integer.parseInt(month);
            	if( num <= 0 || num > 12 )
            	{
                	JOptionPane.showMessageDialog(panel, "请输入正确的月份!");
                	return;
            	}
            }catch(NumberFormatException e1 ){
                JOptionPane.showMessageDialog(panel, "请输入正确的月份!");
                return;
            }
		}
		
		if( button == panel.getRefreshButton() )
		{
			try {
				if( year == null )
				{
	                JOptionPane.showMessageDialog(panel, "请先选择年份!");
	                return;
				}
				if( month == null )
				{
	                JOptionPane.showMessageDialog(panel, "请先选择月份!");
	                return;
				}
				int yearNum = Integer.parseInt(year);
				int monthNum = Integer.parseInt(month);
				List<IncomeRecord> dataList = service.monthIncomeRecord(yearNum, monthNum);
				Image image = ChartUtil.getImageOfIncome(dataList,800, 400,"月收入报表");
				ImageIcon icon= new ImageIcon(image);
				panel.getLabel().setIcon(icon);
				panel.getLabel().updateUI();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
	}
}
