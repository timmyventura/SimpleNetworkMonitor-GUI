package monitor.view.swing;



import java.awt.Component;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

import monitor.view.Frame;
import monitor.view.View;



public class MainFrame extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private View pieGraph;
	private View informGraph;
	private View speedGraph;

    public MainFrame(View pieGraph, View informGraph, View speedGraph ) {
		
		this.pieGraph = pieGraph;
		this.informGraph = informGraph;
		this.speedGraph = speedGraph;
		
	}
	
	public void init() {
		
		setPreferredSize(new Dimension(900, 600));
		setTitle("Simple Network Monitor");
		GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    setHorizontalGroup(layout);
	    setVerticalGroup(layout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	public void setHorizontalGroup(GroupLayout layout) {
		
		layout.setHorizontalGroup(
		           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		           .addGroup(layout.createSequentialGroup()
		               .addComponent((Component)pieGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		               .addComponent((Component)informGraph, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
		           .addComponent((Component)speedGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		       );
		
	}
	
    public void setVerticalGroup(GroupLayout layout) {
		
    	layout.setVerticalGroup(
 	           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
 	           .addGroup(layout.createSequentialGroup()
 	               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
 	                   .addComponent((Component)pieGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)  
 	                   .addComponent((Component)informGraph, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))        
 	                   .addComponent((Component)speedGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 	                  .addGap(0,0,0)
 	               .addContainerGap())
 	       );
    	
	}

	public View getInformGraph() {
		return informGraph;
	}

	public void setInformGraph(View informGraph) {
		this.informGraph = informGraph;
	}

	public View getPieGraph() {
		return pieGraph;
	}

	public void setPieGraph(View pieGraph) {
		this.pieGraph = pieGraph;
	}

	public View getSpeedGraph() {
		return speedGraph;
	}

	public void setSpeedGraph(View speedGraph) {
		this.speedGraph = speedGraph;
	}
	
}
