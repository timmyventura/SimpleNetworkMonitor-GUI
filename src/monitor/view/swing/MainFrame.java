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
	
	private View [] views;
	
    public MainFrame(View ... views ) {
		
		this.views = views;
		
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
		               .addComponent((Component)views[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		               .addComponent((Component)views[1], GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
		           .addComponent((Component)views[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		       );
		
	}
	
    public void setVerticalGroup(GroupLayout layout) {
		
    	layout.setVerticalGroup(
 	           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
 	           .addGroup(layout.createSequentialGroup()
 	               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
 	                   .addComponent((Component)views[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)  
 	                   .addComponent((Component)views[1], GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))        
 	                   .addComponent((Component)views[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 	                  .addGap(0,0,0)
 	               .addContainerGap())
 	       );
    	
	}
	
}
