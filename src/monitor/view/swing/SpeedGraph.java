package monitor.view.swing;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;


import monitor.view.View;


public class SpeedGraph extends JPanel implements View {


	private static final long serialVersionUID = 1L;
	private TimeSeries in_rate;
	private TimeSeries out_rate;
	private int maxAge = 60000;
    
	private String speedLabel;
	private String axisLabel;
	private String dateLabel;
	

    public SpeedGraph(String speedLabel, String axisLabel, String dateLabel) {
		
		super(new BorderLayout());
		
		this.speedLabel = speedLabel;
		this.axisLabel = axisLabel;
		this.dateLabel = dateLabel;
		
		init();
		
	}
	
	public SpeedGraph(int maxAge) {
		
		super(new BorderLayout());
		this.maxAge = maxAge;
		init();
		
	}
    
	private void createDataSeries() {
		
		this.in_rate = new TimeSeries("Current inbound speed 0 bit/s");
		this.out_rate = new TimeSeries("Current outbound speed 0 bit/s");
		this.in_rate.setMaximumItemAge(maxAge);
		this.out_rate.setMaximumItemAge(maxAge);
		
	}
	
	private TimeSeriesCollection createDataSet() {
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(this.in_rate);
		dataset.addSeries(this.out_rate);
		
		return dataset;
		
	}
	
	private DateAxis createDateAxis(String dateLabel) {
		
	  return new DateAxis(dateLabel);
	}
	
	private DateAxis getDateAxisWithParameters(DateAxis domain) {
		
		setFont(domain);
		domain.setAutoRange(true);
		domain.setLowerMargin(0.0);
		domain.setUpperMargin(0.0);
		domain.setTickLabelsVisible(true);
		
	  return domain;
	}
	
	private NumberAxis createNumberAxis(String axisLabel) {
		
		return new NumberAxis(axisLabel);
	   
	}
	
	private NumberAxis getNumberAxisWithParameters(NumberAxis range) {
		
		setFont(range);
		range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		return range;
	}
	
	private void setFont(ValueAxis axis) {
		
		axis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		axis.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
		
	}
	
	private XYItemRenderer createXYItemRenderer(boolean lines, boolean shape) {

	  return new XYLineAndShapeRenderer(true, false);
	  
	}
	
    private XYItemRenderer getXYItemRendererWithParameters(XYItemRenderer renderer) {
		
    	renderer.setSeriesPaint(0, Color.BLUE);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setBaseStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		
	  return renderer;
	}
	
	private XYPlot createXYPlot(String axisLabel, String dateLabel, boolean lines, boolean shape) {
		 
		
	  return new XYPlot(createDataSet(), 
			            getDateAxisWithParameters(createDateAxis(dateLabel)), 
			            getNumberAxisWithParameters(createNumberAxis(axisLabel)), 
			            getXYItemRendererWithParameters(createXYItemRenderer(lines, shape)));
		
	}
	
    private XYPlot getPlotWithParameters(XYPlot plot) {
		
    	plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		
	  return plot;
       
	}
	
	
	private void init() {
       
		createDataSeries();
		
		add(getChartPanelWithParameters(createChartPanel(
				                        getJFreeChartObjectWithParameters(
				                        createJFreeChartObject(getSpeedLabel(),
				                                               new Font("SansSerif", 
				                                                        Font.BOLD, 
				                                                        16),
				                                               getPlotWithParameters(
				                                               createXYPlot(getAxisLabel(), 
				                             		                        getDateLabel(),  
				                             		                        true, 
				                             		                        false)),
				                                               true)))));
	}
	
	private JFreeChart createJFreeChartObject(String title, Font titleFont, Plot plot, boolean createLegend) {
		
		return new JFreeChart(title, titleFont, plot, createLegend);
		
	}
	
	private JFreeChart getJFreeChartObjectWithParameters(JFreeChart chart) {
		
		chart.setBackgroundPaint(Color.white);
		
		return chart;
	}
	
	private ChartPanel createChartPanel(JFreeChart chart) {
		
		return new ChartPanel(chart);
		
	}
	
	private ChartPanel getChartPanelWithParameters(ChartPanel chartPanel) {
		
		chartPanel.setPreferredSize(new Dimension(900, 300));
		chartPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4),
		                                                        BorderFactory.createLineBorder(Color.black)));
		
		return chartPanel;
	}
	

	@Override
	public void addObservation(double input_length, double output_length, double max_input_length, double max_output_length) {
		
		   this.in_rate.addOrUpdate(new Millisecond(), input_length);
		   this.in_rate.setKey(String.format("Current inbound speed - %.2f kbits/s", input_length));
		   this.out_rate.addOrUpdate(new Millisecond(), output_length);
		   this.out_rate.setKey(String.format("Current outbound speed - %.2f kbits/s", output_length));
	}


	@Override
	public void addObservation(Map<Object, Object> param) {
		
         throw new UnsupportedOperationException("Using addObservation(double input_length, double output_length, double max_input_length, double max_output_length) method");
		
	}

	public String getSpeedLabel() {
		return speedLabel;
	}

	public void setSpeedLabel(String speedLabel) {
		this.speedLabel = speedLabel;
	}

	public String getAxisLabel() {
		return axisLabel;
	}

	public void setAxisLabel(String axisLabel) {
		this.axisLabel = axisLabel;
	}

	public String getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(String dateLabel) {
		this.dateLabel = dateLabel;
	}


}
