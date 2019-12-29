package util;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;
import entity.CostRecord;
import entity.IncomeRecord;

public class ChartUtil {
	
    private static String[] sampleLabelsOfCost(List<CostRecord> rs) {
        String[] sampleLabels = new String[rs.size()];
        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5)
                sampleLabels[i] = String.valueOf(i + 1 + "��");
        }
 
        return sampleLabels;
 
    }
    
    private static String[] sampleLabelsOfIncome(List<IncomeRecord> rs) {
        String[] sampleLabels = new String[rs.size()];
        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5)
                sampleLabels[i] = String.valueOf(i + 1 + "��");
        }
 
        return sampleLabels;
 
    }
 
    public static double[] sampleValuesOfCost(List<CostRecord> rs) {
        double[] sampleValues = new double[rs.size()];
        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = rs.get(i).getCost();
        }
 
        return sampleValues;
    }
    
    public static double[] sampleValuesOfIncome(List<IncomeRecord> rs) {
        double[] sampleValues = new double[rs.size()];
        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = rs.get(i).getIncome();
        }
 
        return sampleValues;
    }

    public static Image getImageOfCost(List<CostRecord> rs, int width, int height,String name) {
        // �������Ѽ�¼�õ�����������
        double[] sampleValues = sampleValuesOfCost(rs);
        // �������Ѽ�¼�õ����·������ı�
        String[] sampleLabels = sampleLabelsOfCost(rs);
        // �����е����ֵ
        int max = max(sampleValues);
 
        // ������ɫ
        Color[] sampleColors = new Color[] { ColorUtil.blueColor };
 
        // ��״ͼ
        BarChart chart = new BarChart();
 
        // ������������
        chart.setSampleCount(sampleValues.length);
        // ������������
        chart.setSampleValues(0, sampleValues);
        // ��������
        chart.setSampleLabels(sampleLabels);
        // ����������ɫ
        chart.setSampleColors(sampleColors);
        // ����ȡֵ��Χ
        chart.setRange(0, max * 1.2);
        // ��ʾ��������
        chart.setValueLinesOn(true);
        // ��ʾ����
        chart.setSampleLabelsOn(true);
        // ��������ʾ���·�
        chart.setSampleLabelStyle(Chart.BELOW);
 
        // ����ֵ������
        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
        // ��ʾͼ��˵��
        chart.setLegendOn(true);
        // ��ͼ��˵���������
        chart.setLegendPosition(Chart.LEFT);
        // ͼ��˵���е�����
        chart.setLegendLabels(new String[] { name });
        // ͼ��˵��������
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
        // �·����ֵ�����
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
        // ͼ���м䱳����ɫ
        chart.setChartBackground(Color.white);
        // ͼ�����屳����ɫ
        chart.setBackground(ColorUtil.backgroundColor);
        // ��ͼ��ת��ΪImage����
        Image im = chart.getImage(width, height);
        return im;
    }

    public static Image getImageOfIncome(List<IncomeRecord> rs, int width, int height,String name) {
        // ���������¼�õ�����������
        double[] sampleValues = sampleValuesOfIncome(rs);
        // ���������¼�õ����·������ı�
        String[] sampleLabels = sampleLabelsOfIncome(rs);
        // �����е����ֵ
        int max = max(sampleValues);
 
        // ������ɫ
        Color[] sampleColors = new Color[] { ColorUtil.blueColor };
 
        // ��״ͼ
        BarChart chart = new BarChart();
 
        // ������������
        chart.setSampleCount(sampleValues.length);
        // ������������
        chart.setSampleValues(0, sampleValues);
        // ��������
        chart.setSampleLabels(sampleLabels);
        // ����������ɫ
        chart.setSampleColors(sampleColors);
        // ����ȡֵ��Χ
        chart.setRange(0, max * 1.2);
        // ��ʾ��������
        chart.setValueLinesOn(true);
        // ��ʾ����
        chart.setSampleLabelsOn(true);
        // ��������ʾ���·�
        chart.setSampleLabelStyle(Chart.BELOW);
 
        // ����ֵ������
        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
        // ��ʾͼ��˵��
        chart.setLegendOn(true);
        // ��ͼ��˵���������
        chart.setLegendPosition(Chart.LEFT);
        // ͼ��˵���е�����
        chart.setLegendLabels(new String[] { name });
        // ͼ��˵��������
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
        // �·����ֵ�����
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
        // ͼ���м䱳����ɫ
        chart.setChartBackground(Color.white);
        // ͼ�����屳����ɫ
        chart.setBackground(ColorUtil.backgroundColor);
        // ��ͼ��ת��ΪImage����
        Image im = chart.getImage(width, height);
        return im;
    }
    
    public static int max(double[] sampleValues) {
        int max = 0;
        for (double v : sampleValues) {
            if (v > max)
                max = (int) v;
        }
        return max;
    }

    private static String[] sampleLabels() {
        String[] sampleLabels = new String[30];
        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5)
                sampleLabels[i] = String.valueOf(i + 1 + "��");
        }
        return sampleLabels;
    }
 
    public static Image getImage(int width, int height,String name) {
        // ģ����������
        double[] sampleValues = sampleValues();
        // �·���ʾ������
        String[] sampleLabels = sampleLabels();
        // �����е����ֵ
        int max = max(sampleValues);
 
        // ������ɫ
        Color[] sampleColors = new Color[] { ColorUtil.blueColor };
 
        // ��״ͼ
        BarChart chart = new BarChart();
 
        // ������������
        chart.setSampleCount(sampleValues.length);
        // ������������
        chart.setSampleValues(0, sampleValues);
        // ��������
        chart.setSampleLabels(sampleLabels);
        // ����������ɫ
        chart.setSampleColors(sampleColors);
        // ����ȡֵ��Χ
        chart.setRange(0, max * 1.2);
        // ��ʾ��������
        chart.setValueLinesOn(true);
        // ��ʾ����
        chart.setSampleLabelsOn(true);
        // ��������ʾ���·�
        chart.setSampleLabelStyle(Chart.BELOW);
 
        // ����ֵ������
        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
        // ��ʾͼ��˵��
        chart.setLegendOn(true);
        // ��ͼ��˵���������
        chart.setLegendPosition(Chart.LEFT);
        // ͼ��˵���е�����
        chart.setLegendLabels(new String[] {name});
        // ͼ��˵��������
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
        // �·����ֵ�����
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
        // ͼ���м䱳����ɫ
        chart.setChartBackground(Color.white);
        // ͼ�����屳����ɫ
        chart.setBackground(ColorUtil.backgroundColor);
        // ��ͼ��ת��ΪImage����
        Image im = chart.getImage(width, height);
        return im;
    }

    private static double[] sampleValues() {
        double[] result = new double[30];
        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }
        return result;
    }

}