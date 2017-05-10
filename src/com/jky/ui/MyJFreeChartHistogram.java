package com.jky.ui;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import com.jky.biz.KindBiz;
import com.jky.biz.SaleRecordBiz;
import com.jky.entity.Kind;
import java.awt.Toolkit;

public class MyJFreeChartHistogram extends ApplicationFrame{
    public MyJFreeChartHistogram()
    {	
        super("乐尔乐商品种类销售比例图表");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\statistitcs.jpg"));
        this.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosed(WindowEvent arg0) {
        		// TODO Auto-generated method stub
//        		super.windowClosed(arg0);
        	}
        	
        });
        this.setContentPane(createPanel()); //构造函数中自动创建Java的panel面板
        
      //创建主题样式，避免中文乱码
    	StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
    	// 设置标题字体
    	standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
    	// 设置图例的字体
    	standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
    	// 设置轴向的字体
    	standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
    	// 应用主题样式
    	ChartFactory.setChartTheme(standardChartTheme);
    	
        this.setBounds(180, 30, 1000, 600);;//以合适的大小显示
        this.setVisible(true);
    }
    
    public static CategoryDataset createDataset() //创建柱状图数据集
    {
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        SaleRecordBiz sbiz = new SaleRecordBiz();
		KindBiz kbiz = new KindBiz();
		List<Kind> allKind = kbiz.queryAllKind(null);
		int size = allKind.size();
		int kindId[]=new int[size];
		String kindName[]=new String[size];
		for (Kind kind : allKind) {
			--size;
			kindId[size] = kind.getKindId();
			kindName[size] = kind.getName();
		}
		
		int value[]=sbiz.countKindSale(kindId);
		String mark[]={"a","b","c","d","e","f","g","h","i","j"};
		for (int i = 0; i < value.length; i++) {
			dataset.setValue(value[i],mark[i],kindName[i] );
		}
        return dataset;
    }
    
    public static JFreeChart createChart(CategoryDataset dataset) //用数据集创建一个图表
    {
        JFreeChart chart=ChartFactory.createBarChart("hi", "商品种类", 
                "单类商品销售总额", dataset, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
        chart.setTitle(new TextTitle("乐尔乐商品种类销售比例图表",new Font("宋体",Font.BOLD+Font.ITALIC,20)));//可以重新设置标题，替换“hi”标题
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
        return chart;
    }
    
    public static JPanel createPanel()
    {
        JFreeChart chart =createChart(createDataset());
        return new ChartPanel(chart); //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
    }
}
