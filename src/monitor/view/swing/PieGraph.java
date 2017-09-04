package monitor.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

import monitor.view.View;


public class PieGraph extends JPanel implements View {

	

	private static final long serialVersionUID = 1L;
	
	private DefaultPieDataset dataset;
	
	private String pieLabel;

	
    public PieGraph(String pieLabel) {
		
		super(new BorderLayout());
		
		this.pieLabel = pieLabel;

		init();
		
	}
	
	private void init() {
		
		       				
        add(getChartPanelWithParameters(createChartPanel(getJFreeChartObjectWithParameters(createJFreeChartObject(getPieLabel(),
                                                                                                                  new Font("SansSerif", 
                                                                                                                           Font.BOLD, 
                                                                                                                           16),
                                                                                                                  getPiePlotWithParameters(createPiePlot(createDataset())),
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
		
		
		chartPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4),
		                                                        BorderFactory.createLineBorder(Color.black)));
		
		return chartPanel;
	}
	
	
	private  PiePlot createPiePlot(DefaultPieDataset dataset) {
		
       return new PiePlot(dataset);
        
	}
	
	private PiePlot getPiePlotWithParameters(PiePlot plot) {
		
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {2}", 
        		                                                           NumberFormat.getNumberInstance(), 
        		                                                           NumberFormat.getPercentInstance()));
       return plot;
       
	}
	
	
	private DefaultPieDataset createDataset() {
        
		dataset = new DefaultPieDataset();
 
        return dataset;        
    }
	
	

	@Override
	public void addObservation(Map<Object, Object> param) {
		
		  dataset.clear();
          param.forEach((k, v) -> dataset.setValue((String)k, (Double)v));

	}

		@Override
	public void addObservation(double input_length, double output_length, double max_input_length,
			double max_output_length) {
		
		
	}

    public String getPieLabel() {
    	
			return pieLabel;
			
    }

	public void setPieLabel(String pieLabel) {
		
			this.pieLabel = pieLabel;
			
	}

		
}
