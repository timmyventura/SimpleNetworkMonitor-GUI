package monitor.view.swing;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	
	private int DEFAULT_COLUMN = 20;
		
	public InformGraph() {
		
    	initLabels();
    	initFields();
		
	}
	
	
	private void initLabels() {
		
		iplocallabel = new JLabel("Local IP:");
		ipexternallabel = new JLabel("External IP:");
		masklabel = new JLabel("Mask:");
		gatewaylabel = new JLabel("Gateway:");
		max_input_speedlabel = new JLabel("Max Input Speed:");
		max_output_speedlabel = new JLabel("Max Output Speed:");
	    hostnamelabel = new JLabel("Hostname:");
	    
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
		
		
	}
	
    public void init() {
    	
        GridLayout layout = new GridLayout(0,2);
    	setLayout(layout);
    	setBackground(Color.WHITE);
    	setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(4, 4, 4, 4),
				BorderFactory.createLineBorder(Color.black)));
    	add(iplocallabel);
    	add(iplocalfield);
    	add(ipexternallabel);
    	add(ipexternalfield);
    	add(masklabel);
    	add(maskfield);
    	add(gatewaylabel);
    	add(gatewayfield); 
    	if(dnsfields != null || dnslabels != null) {
    	for(int i=0; (i< dnsfields.length)||(i < dnslabels.length); i++) {
    		add(dnslabels[i]);
    		add(dnsfields[i]);
    	}
    	}
    	add(hostnamelabel);
    	add(hostnamefield);
    	add(max_input_speedlabel);
    	add(max_input_speedfield);
    	add(max_output_speedlabel);
    	add(max_output_speedfield);

    }
    
    public void setDNSLabelsFields(String [] label) {
    	
    	dnslabels = new JLabel[label.length];
    	dnsfields = new JTextField[label.length];
    	
    	for(int i=0; i<label.length;i++) {
    		int k=i;
    		dnslabels[i] = new JLabel(String.format("DNS %d:", k+1));
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
	public void addObservation(Map<Object, Object> param) {
		  
		throw new UnsupportedOperationException();

	}

	@Override
	public void addObservation(double... param) {

		max_input_speedfield.setText( (((int)(param[2]/1024)==0) ? String.format("%.2f kbits/s", param[2]) : String.format("%.2f Mbits/s", param[2]/1024)));
		max_output_speedfield.setText(((int)(param[3]/1024)==0) ? String.format("%.2f kbits/s", param[3]) : String.format("%.2f Mbits/s", param[3]/1024));
			
	}

}
