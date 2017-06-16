package monitor.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import monitor.view.View;


public class PieGraph extends JPanel implements View {

	

	private static final long serialVersionUID = 1L;
	
	private DefaultPieDataset dataset;
	
	
    public PieGraph() {
		
		super(new BorderLayout());
		init();
		
	}
	
	private void init() {
		
		       JFreeChart chart = new JFreeChart("Current type of traffic. Delay 5 second",
				new Font("SansSerif", Font.BOLD, 16), createPiePlot(), true);
				chart.setBackgroundPaint(Color.white);
				ChartPanel chartPanel = new ChartPanel(chart);
				chartPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(4, 4, 4, 4),
				BorderFactory.createLineBorder(Color.black)));
				add(chartPanel);
		
	}
	
	
	private  PiePlot createPiePlot() {
		
		PiePlot plot = new PiePlot(createDataset());
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0} = {2}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()
            ));
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
	public void addObservation(double... param) {
		
        throw new UnsupportedOperationException();

	}

	@Override
	public void addObservation() {
		// TODO Auto-generated method stub
		
	}

}
