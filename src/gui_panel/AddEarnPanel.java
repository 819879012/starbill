package gui_panel;

import java.awt.Color;
import constant.TagXml;

@SuppressWarnings("serial")
public class AddEarnPanel extends CashFlowPanel{
	
	private static AddEarnPanel instance = new AddEarnPanel();
	
	public AddEarnPanel()
	{
		super(TagXml.addEarnPanel);
		super.setAddCategory("����������");
		super.getShowText().setText("���������ϸ ");
		super.getSetMoneyText().setText("������: ");
		super.setTextColor(Color.blue);
	}
	
	public static AddEarnPanel getInstance()
	{
		return instance;
	}
	
}
