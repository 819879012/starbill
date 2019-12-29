package gui_panel;

import java.awt.Color;
import constant.TagXml;

@SuppressWarnings("serial")
public class AddEarnPanel extends CashFlowPanel{
	
	private static AddEarnPanel instance = new AddEarnPanel();
	
	public AddEarnPanel()
	{
		super(TagXml.addEarnPanel);
		super.setAddCategory("添加收入分类");
		super.getShowText().setText("添加收入明细 ");
		super.getSetMoneyText().setText("收入金额: ");
		super.setTextColor(Color.blue);
	}
	
	public static AddEarnPanel getInstance()
	{
		return instance;
	}
	
}
