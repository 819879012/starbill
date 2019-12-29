package gui_panel;

import java.awt.Color;

import constant.TagXml;

@SuppressWarnings("serial")
public class AddCostPanel extends CashFlowPanel{
	
	private static AddCostPanel instance = new AddCostPanel();
	
	public AddCostPanel()
	{
		super(TagXml.addCostPanel);
		super.setAddCategory("添加支出分类");
		super.getShowText().setText("添加支出明细 ");
		super.getSetMoneyText().setText("支出金额: ");
		super.setTextColor(Color.red);
	}

	public static AddCostPanel getInstance() {
		return instance;
	}
	
}
