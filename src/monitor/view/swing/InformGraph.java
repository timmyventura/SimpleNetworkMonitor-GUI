package monitor.view.swing;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import monitor.view.View;


public class InformGraph extends JPanel implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JLabel iplocallabel;
	private JLabel ipexternallabel;
	private JLabel masklabel;
	private JLabel gatewaylabel;
	private JLabel [] dnslabels;
	private JLabel max_input_speedlabel;
	private JLabel max_output_speedlabel;
	private JLabel hostnamelabel;
	
	private JTextField iplocalfield;
	private JTextField ipexternalfield;
	private JTextField maskfield;
	private JTextField gatewayfield;
	private JTextField [] dnsfields;
	private JTextField max_input_speedfield;
	private JTextField max_output_speedfield;
	private JTextField hostnamefield;
	
	private JPanel panel;
	
	private int DEFAULT_COLUMN = 20;

	
	
	public InformGraph() {
		
		super(new BorderLayout());
		if(panel==null) setPanel(new JPanel());
    	initLabels();
    	initFields();
		
	}
	
	public JPanel getPanel() {
		
		return panel;
	
	}


	public void setPanel(JPanel panel) {
		
		this.panel = panel;
	
	}
	
	
	private void initLabels() {
		
		iplocallabel = new JLabel("Local IP:");
		setLabelFieldParameters(iplocallabel);
		ipexternallabel = new JLabel("External IP:");
		setLabelFieldParameters(ipexternallabel);
		masklabel = new JLabel("Mask:");
		setLabelFieldParameters(masklabel);
		gatewaylabel = new JLabel("Gateway:");
		setLabelFieldParameters(gatewaylabel);
		max_input_speedlabel = new JLabel("Max Input Speed:");
		setLabelFieldParameters(max_input_speedlabel);
		max_output_speedlabel = new JLabel("Max Output Speed:");
		setLabelFieldParameters(max_output_speedlabel);
	    hostnamelabel = new JLabel("Hostname:");
	    setLabelFieldParameters(hostnamelabel);
	    
	}
	
	private void initFields() {
		
		iplocalfield = new JTextField();
		setTextFieldParameters(iplocalfield);
		ipexternalfield = new JTextField();
		setTextFieldParameters(ipexternalfield);
		maskfield = new JTextField();
		setTextFieldParameters(maskfield);
		gatewayfield = new JTextField();
		setTextFieldParameters(gatewayfield);
		max_input_speedfield = new JTextField();
		setTextFieldParameters(max_input_speedfield);
		max_output_speedfield = new JTextField();
		setTextFieldParameters(max_output_speedfield);
		hostnamefield = new JTextField();
		setTextFieldParameters(hostnamefield);
	}
	
	private void setTextFieldParameters(JTextField field) {
		
		field.setColumns(DEFAULT_COLUMN);
		field.setEditable(false);	
		field.setBackground(Color.WHITE);
		field.setHorizontalAlignment(JTextField.RIGHT);
		field.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		field.setFont(new Font("SansSerif", Font.BOLD, 14));
		field.setForeground(new Color(65, 0, 160));
	}
	
	private void setLabelFieldParameters(JLabel label) {
		
		label.setFont(new Font("SansSerif", Font.BOLD, 14));

	}
	
    public void init() {
    	
        GridLayout layout = new GridLayout(0,2,8,8);
        getPanel().setLayout(layout);
        getPanel().setBackground(new Color(211,211,211));
        getPanel().setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(8,0,0,0),
        		                                             "Brief network info",
        		                                              TitledBorder.CENTER,
        		                                              TitledBorder.TOP,
        		                                              new Font("SansSerif", Font.BOLD, 16) ));

    	setBorder(BorderFactory.createCompoundBorder(
				  BorderFactory.createEmptyBorder(4, 4, 4, 4),
				  BorderFactory.createLineBorder(Color.black)));
    	getPanel().add(iplocallabel);
    	getPanel().add(iplocalfield);
    	getPanel().add(masklabel);
    	getPanel().add(maskfield);
    	getPanel().add(gatewaylabel);
    	getPanel().add(gatewayfield);
    	getPanel().add(ipexternallabel);
    	getPanel().add(ipexternalfield);
    	if(dnsfields != null || dnslabels != null) {
    	for(int i=0; (i< dnsfields.length)||(i < dnslabels.length); i++) {
    		getPanel().add(dnslabels[i]);
    		getPanel().add(dnsfields[i]);
    	}
    	}
    	getPanel().add(hostnamelabel);
    	getPanel().add(hostnamefield);
    	getPanel().add(max_input_speedlabel);
    	getPanel().add(max_input_speedfield);
    	getPanel().add(max_output_speedlabel);
    	getPanel().add(max_output_speedfield);
        add(getPanel());
    }
    
    public void setDNSLabelsFields(String [] label) {
    	
    	dnslabels = new JLabel[label.length];
    	dnsfields = new JTextField[label.length];
    	
    	for(int i=0; i<label.length;i++) {
    		int k=i;
    		dnslabels[i] = new JLabel(String.format("DNS %d:", k+1));
    		setLabelFieldParameters(dnslabels[i]);
    		dnsfields[i] = new JTextField(label[i]);
    		setTextFieldParameters(dnsfields[i]);
    	}
    	
    }
    
    public void setIPToLocalIPField(String localIP) {
    	
    	iplocalfield.setText(localIP);
    	
    }
    
    public String getIPFromLocalIPField() {
    	
    	return iplocalfield.getText();
    }
    
    public void setIPToExternalIPField(String externalIP) {
    	
    	ipexternalfield.setText(externalIP);
    	
    	
    }
    
    public String getIPFromExternalIPField() {
    	
    	return ipexternalfield.getText();
    }
    
    
    public void setMaskToMaskField(String mask) {
    	
    	maskfield.setText(mask);
    	
    }
    
    public String getMaskFromMaskField() {
    	
    	return maskfield.getText();
    }
    
    public void setGatewayToGatewayField(String gateway) {
    	
    	gatewayfield.setText(gateway);
    	
    }
    
    public String getGatewayFromGatewayField() {
    	
    	return gatewayfield.getText();
    }
    
    public void setMaxInputSpeedToMaxSpeedField(String maxinspeed) {
    	
    	max_input_speedfield.setText(maxinspeed);
    	
    }
    
    public String getMaxInputSpeedFromMaxSpeedField() {
    	
    	return max_input_speedfield.getText();
    }
    
    public void setMaxOutputSpeedToMaxSpeedField(String maxoutspeed) {
    	
    	max_output_speedfield.setText(maxoutspeed);
    	
    }
    
    public String getMaxOutputSpeedFromMaxSpeedField() {
    	
    	return max_output_speedfield.getText();
    }
    
    public void setHostnameToHostnameField(String hostname) {
    	
    	hostnamefield.setText(hostname);
    	
    }
    
    public String getHostnameFromHostnameField() {
    	
    	return hostnamefield.getText();
    }

	
    
	@Override
	public void addObservation(Map<Object, Object> param) throws UnsupportedOperationException{
		  
		throw new UnsupportedOperationException("Using addObservation(double input_length, double output_length, double max_input_length, " + 
				"double max_output_length) method");

	}

	@Override
	public void addObservation(double input_length, double output_length, double max_input_length,
			double max_output_length) {
		max_input_speedfield.setText( (((int)(max_input_length/1024)==0) ? String.format("%.2f kbits/s", max_input_length) : String.format("%.2f Mbits/s", max_input_length/1024)));
		max_output_speedfield.setText(((int)(max_output_length/1024)==0) ? String.format("%.2f kbits/s",  max_output_length) : String.format("%.2f Mbits/s",  max_output_length/1024));
		
	}





}
