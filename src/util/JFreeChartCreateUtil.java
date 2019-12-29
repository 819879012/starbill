package util;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import entity.CostRecord;
import entity.IncomeRecord;

public class JFreeChartCreateUtil {
	
	public static JFreeChart JFreeChartCreate() {
		 CategoryDataset mDataset = GetDataset();
		 JFreeChart mChart = ChartFactory.createLineChart(
				 "消费分析",//图名字
	                "日期",//横坐标
	                "消费金额",//纵坐标
	                mDataset,//数据集
	                PlotOrientation.VERTICAL,
	                true, // 显示图例
	                true, // 采用标准生成器
	                false);// 是否生成超链接

		 	setLine(mChart);
	        return mChart;
	 }
	
	 public static JFreeChart getIncomeChartCreate(List<IncomeRecord> list)  {
		 CategoryDataset mDataset = GetIncomeDataset(list);
		 JFreeChart mChart = ChartFactory.createLineChart(
				 "收入分析",//图名字
	                "日期",//横坐标
	                "收入金额",//纵坐标
	                mDataset,//数据集
	                PlotOrientation.VERTICAL,
	                true, // 显示图例
	                true, // 采用标准生成器
	                false);// 是否生成超链接

		 	setLine(mChart);
	        return mChart;
	 }
	 
	 public static JFreeChart getCostChartCreate(List<CostRecord> list) {
		 CategoryDataset mDataset = GetCostDataset(list);
		 JFreeChart mChart = ChartFactory.createLineChart(
				 "消费分析",//图名字
	                "日期",//横坐标
	                "消费金额",//纵坐标
	                mDataset,//数据集
	                PlotOrientation.VERTICAL,
	                true, // 显示图例
	                true, // 采用标准生成器
	                false);// 是否生成超链接

		 	setLine(mChart);
	        return mChart;
	 }
	 
	 private static void setLine(JFreeChart mChart)
	 {
		 	Font font=new Font("隶书", Font.ITALIC, 18);
			mChart.getTitle().setFont(font);
			mChart.getLegend().setItemFont(font);
			CategoryPlot plot=mChart.getCategoryPlot();
			plot.getDomainAxis().setLabelFont(font);//x轴字体
			plot.getDomainAxis().setTickLabelFont(font);
			plot.getRangeAxis().setLabelFont(font); //y轴字体
			
	        CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
	        mPlot.setBackgroundPaint(Color.LIGHT_GRAY);//背景颜色
	        mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
	        mPlot.setOutlinePaint(Color.RED);//边界线
	 }
	 
	 //获取支出数据集
	 public static CategoryDataset GetCostDataset(List<CostRecord> list) {
		 DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		 String lineName = "支出金额";
		 for( int i = 0; i < list.size(); i++ )
		 {
			 mDataset.addValue(list.get(i).getCost(), lineName,String.valueOf(i+1));
		 }
		 return mDataset;
	 }
	 
	 //获取收入数据集
	 public static CategoryDataset GetIncomeDataset(List<IncomeRecord> list) {
		 DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		 String lineName = "收入金额";
		 for( int i = 0; i < list.size(); i++ )
		 {
			 mDataset.addValue(list.get(i).getIncome(), lineName,String.valueOf(i+1));
		 }
		 return mDataset;
	 }
	 
	 public static CategoryDataset GetDataset() {
		 DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		 List<Double> data = new ArrayList<>();
		 for(int i = 1 ;i <= 31;i++) {
			 data.add(0.0);
		 }
		 String lineName = "支出金额";
		 
		 mDataset.addValue(data.get(0), lineName, "1");
	     mDataset.addValue(data.get(1), lineName, "2");
	     mDataset.addValue(data.get(2), lineName, "3");
	     mDataset.addValue(data.get(3), lineName, "4");
	     mDataset.addValue(data.get(4), lineName, "5");
	     mDataset.addValue(data.get(5), lineName, "6");	 
		 return mDataset;
	 }
	 
	 //显示方法
	 public static void show(JFreeChart mChart) {
		 ChartFrame mChartFrame = new ChartFrame("折线图", mChart);
	     mChartFrame.pack();
	     mChartFrame.setLocationRelativeTo(null);
	     mChartFrame.setVisible(true);
	 }
	 
}