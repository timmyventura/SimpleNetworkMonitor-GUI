package monitor.view.swing;


import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import monitor.controller.FrameModelController;

public class InitialFrame extends JFrame{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1455189770787016714L;
	
	private JButton cancel;
    private JButton ready;
    private JComboBox<String> interface_list;
    private JLabel select_label;
    private FrameModelController controller;
	
	public InitialFrame(FrameModelController controller) {
		
		this.controller = controller;
		initComponents();
	}
        
	@SuppressWarnings("unchecked")
	
	private void initComponents() {

        select_label = new JLabel();
        interface_list = new JComboBox<>();
        cancel = new JButton();
        ready = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        select_label.setText("Select an Interface:");

        cancel.setText("Cancel");

        ready.setText("Ready");
        
        interface_list.addActionListener(evt -> {
        	
        	JComboBox<String>cb = (JComboBox<String>)evt.getSource();
    		controller.setSelectedDevice((String)cb.getSelectedItem());
        	
        });
        
        
        ready.addActionListener(evt ->{
        	
        	controller.runningMainFrame();
        	
        });
        
        cancel.addActionListener(evt ->{
        	
        	Runtime.getRuntime().exit(0);
        	
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        setVerticalGroup(layout);
        setHorizontalGroup(layout);
        setVisible(false);
        pack();
    }
	
      private void setHorizontalGroup(GroupLayout layout) {
    	  layout.setHorizontalGroup(
    	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    	            .addGroup(layout.createSequentialGroup()
    	                .addContainerGap()
    	                .addComponent(select_label, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
    	                .addGap(18, 18, 18)
    	                .addComponent(interface_list, GroupLayout.DEFAULT_SIZE, 299, GroupLayout.PREFERRED_SIZE)
    	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    	            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
    	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    	                .addComponent(cancel)
    	                .addGap(18, 18, 18)
    	                .addComponent(ready)
    	                .addContainerGap())
    	        );
		
	}
	
    private void setVerticalGroup(GroupLayout layout) {
		
    	 layout.setVerticalGroup(
    	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    	            .addGroup(layout.createSequentialGroup()
    	                .addGap(32, 32, 32)
    	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    	                    .addComponent(select_label, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
    	                    .addComponent(interface_list, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    	                .addGap(18, 18, 18)
    	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    	                    .addComponent(cancel)
    	                    .addComponent(ready))
    	                .addContainerGap())
    	        );

    	
	}
    
    public void setComboModel(String [] interfaces) {
    	
    	
    	 interface_list.setModel(new DefaultComboBoxModel<>(interfaces));
    	
    }
	
}
