package gui_panel;

import java.awt.Color;

import constant.TagXml;

@SuppressWarnings("serial")
public class AddCostPanel extends CashFlowPanel{
	
	private static AddCostPanel instance = new AddCostPanel();
	
	public AddCostPanel()
	{
		super(TagXml.addCostPanel);
		super.setAddCategory("���֧������");
		super.getShowText().setText("���֧����ϸ ");
		super.getSetMoneyText().setText("֧�����: ");
		super.setTextColor(Color.red);
	}

	public static AddCostPanel getInstance() {
		return instance;
	}
	
}
