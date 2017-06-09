package monitor.view.swt;


import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Map;

import javax.swing.BorderFactory;

import org.eclipse.swt.widgets.Composite;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.swt.ChartComposite;
import org.jfree.data.general.DefaultPieDataset;

import monitor.view.View;


public class PieGraph extends ChartComposite  implements View {

	private JFreeChart chart;
	
	private DefaultPieDataset dataset;
	
	
    public PieGraph (Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init() {
		
		        /*
		        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		        */
		
		        chart = new JFreeChart("Current type of traffic",
				new Font("SansSerif", Font.BOLD, 24), createPiePlot(), true);
		        System.out.println(chart);
				chart.setBackgroundPaint(Color.white);
				ChartPanel chartPanel = new ChartPanel(chart);
				chartPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(4, 4, 4, 4),
				BorderFactory.createLineBorder(Color.black)));
		        setChart(chart);
		        
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
	
	public JFreeChart getChartObject() {
		
		return chart;
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

}
