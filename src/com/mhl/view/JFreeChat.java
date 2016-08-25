package com.mhl.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class JFreeChat extends JPanel implements ActionListener{
	
	private JPanel jp1,jp2,jp3;
	private JButton jb1,jb2,jb3;
	private JLabel jl1;
	
	
	public static void main(String[] args) {
		JFreeChat fjc = new JFreeChat();
		//fjc.pack();
		
	}

	public JFreeChat() {
		//工具栏
		jp1=new JPanel(new FlowLayout(new FlowLayout().CENTER));
		jb1=new  JButton("折线图");
		jb2=new JButton("饼状图");
		jb3=new JButton("柱状图");
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		//状态栏
		jp2=new JPanel(new FlowLayout(new FlowLayout().LEFT));
		Timer t=new Timer(1000, this);//每隔一秒触发ActonEvent
		jl1=new JLabel("当前时间："+Calendar.getInstance().getTime().toLocaleString()+"   ");
		t.start();
		jp2.add(jl1);
		//jp2.setBackground());
		//图表栏
		jp3=new JPanel();
		
	
		
		this.setLayout(new BorderLayout());
		this.add(jp1,BorderLayout.NORTH);
		this.add(jpanel());
		this.add(jp2,BorderLayout.SOUTH);
		//setContentPane(jpanel());
		//RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
		//this.setSize(800, 600);
	}

	

	
	// 生成显示图表的面板
	 public static JPanel jpanel() {
	  JFreeChart jfc = jfreeChart(getDataset());
	  return new ChartPanel(jfc);
	 } 

	 // 生成图表主对象JFreeChart
	 public static JFreeChart jfreeChart(DefaultCategoryDataset linedataset) {
			  //定义图表对象
			  JFreeChart chart = ChartFactory.createLineChart("折线图", // chart title
			    "时间", // domain axis label
			    "销售额(百万)", // range axis label
			    linedataset, // data
			    PlotOrientation.VERTICAL, // orientation
			    true, // include legend
			    true, // tooltips
			    false // urls
			    );
			  CategoryPlot plot = chart.getCategoryPlot();
			  // customise the range axis...
			  
			  
			  NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			  rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			  rangeAxis.setAutoRangeIncludesZero(true);
			  rangeAxis.setUpperMargin(0.20);
			  rangeAxis.setLabelAngle(Math.PI / 2.0); 
		
			  return chart;
	 } 

	 //生成数据
	 public static DefaultCategoryDataset getDataset() {
		 
			  DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
			  //  各曲线名称
			  String series1 = "冰箱";
			  String series2 = "彩电";
			  String series3 = "洗衣机"; 
		
			  //    横轴名称(列名称)
			  String type1 = "1月";
			  String type2 = "2月";
			  String type3 = "3月"; 
		
			  linedataset.addValue(0.0, series1, type1);
			  linedataset.addValue(4.2, series1, type2);
			  linedataset.addValue(3.9, series1, type3); 
		
			  linedataset.addValue(1.0, series2, type1);
			  linedataset.addValue(5.2, series2, type2);
			  linedataset.addValue(7.9, series2, type3); 
		
			  linedataset.addValue(2.0, series3, type1);
			  linedataset.addValue(9.2, series3, type2);
			  linedataset.addValue(8.9, series3, type3); 
		
			  return linedataset;
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.jl1.setText("当前时间："+Calendar.getInstance().getTime().toLocaleString()+"   ");
	} 

}

