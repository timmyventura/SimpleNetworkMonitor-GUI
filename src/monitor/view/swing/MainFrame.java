package monitor.view.swing;


import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PieGraph piegraph;
	private InformGraph inform;
	private SpeedGraph speedgraph;
	
	public MainFrame(PieGraph piegraph, InformGraph inform, SpeedGraph speedgraph) {
		
		this.piegraph = piegraph;
		this.inform = inform;
		this.speedgraph = speedgraph;
		
	}
	
	public void init() {
		
		setPreferredSize(new Dimension(900, 700));
		setResizable(false);
		setName("Simple Network Monitor");
		GroupLayout layout = new GroupLayout(getContentPane());
		//layout.setAutoCreateGaps(true);
		//layout.setAutoCreateContainerGaps(true);
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
		               .addComponent(piegraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		               .addComponent(inform, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
		           .addComponent(speedgraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		       );
		
	}
	
    public void setVerticalGroup(GroupLayout layout) {
		
    	layout.setVerticalGroup(
 	           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
 	           .addGroup(layout.createSequentialGroup()
 	               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
 	                   .addComponent(piegraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)  
 	                   .addComponent(inform, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))        
 	                   .addComponent(speedgraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 	                  .addGap(0,0,0)
 	               .addContainerGap())
 	       );
    	
	}
	
}
