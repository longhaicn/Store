package com.jky.ui;

import java.awt.Font;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

import com.jky.biz.KindBiz;
import com.jky.biz.SaleRecordBiz;
import com.jky.entity.Kind;
import com.jky.entity.SaleRecord;
import java.awt.Toolkit;
import java.awt.Color;

public class MyJFreeChartPie  {
	public  MyJFreeChartPie() {
		// 创建主题样式
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);

		DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
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
		
		for (int i = 0; i < value.length; i++) {
			dpd.setValue(kindName[i], value[i]);
		}
		
		
//		dpd.setValue("管理人员", 25);// 输入数据
//		dpd.setValue("市场人员", 25);
//		dpd.setValue("开发人员", 45);
//		dpd.setValue("其他人员", 10);

		JFreeChart chart = ChartFactory.createPieChart("乐尔乐各类商品销售额比例图标", dpd, true, true, false);
		// 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL

		ChartFrame chartFrame = new ChartFrame("乐尔乐各类商品销售额比例图标", chart);
		chartFrame.setBackground(Color.WHITE);
		chartFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\pie.jpg"));
		// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
		//chartFrame.pack(); // 以合适的大小展现图形
		chartFrame.setBounds(180, 30, 1000, 600);
		
		chartFrame.setVisible(true);// 图形是否可见
	}
	
}