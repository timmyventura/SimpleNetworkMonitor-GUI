package monitor.view.swt;




import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import monitor.view.View;



public class InformGraph extends Composite implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Color white = new Color(Display.getDefault(), 0, 255, 255);
	private static final Color grey = new Color(Display.getDefault(), 0, 128, 128);
	
	private Label iplocallabel;
	private Label ipexternallabel;
	private Label masklabel;
	private Label gatewaylabel;
	private Label [] dnslabels;
	private Label max_input_speedlabel;
	private Label max_output_speedlabel;
	private Label hostnamelabel;
	
	private Text iplocalfield;
	private Text ipexternalfield;
	private Text maskfield;
	private Text gatewayfield;
	private Text [] dnsfields;
	private Text max_input_speedfield;
	private Text max_output_speedfield;
	private Text hostnamefield;
	
	
	private int DEFAULT_COLUMN = 20;
	private static GridData gridData;
	private String [] text;
	
	public static class Test{
		
		public static void main(String [] args) {
			
			Display display = Display.getDefault();
			
			Shell shell = new Shell(display);
			shell.setLayout(new GridLayout(1, false));
			shell.setSize(834, 430);
			
			Composite graph = new InformGraph(shell, SWT.BORDER);
			shell.setBackground(white);
			
			shell.open();
	        while (!shell.isDisposed()) {
	            if (!display.readAndDispatch()) {
	                display.sleep();
	            }
	        }
			
	        display.dispose();
		}
		
		
	}
	
	public InformGraph(Composite parent, int style) {
		
		super(parent, style);
		setBackground(grey);
        //init();
	}
	
	private void initLabelsAndFields() {
		
		iplocallabel = new Label(this, SWT.BORDER);
		setLabelParameters(iplocallabel, "Local IP:");
		iplocalfield = new Text(this, SWT.RIGHT | SWT.BORDER);
		setTextFieldParameters(iplocalfield);
		
		ipexternallabel = new Label(this, SWT.BORDER);
		setLabelParameters(ipexternallabel, "External IP:");
		ipexternalfield = new Text(this, SWT.RIGHT | SWT.BORDER);
		setTextFieldParameters(ipexternalfield);
		
		masklabel = new Label(this, SWT.BORDER);
		setLabelParameters(masklabel, "Mask:");
		maskfield = new Text(this, SWT.RIGHT | SWT.BORDER);
		setTextFieldParameters(maskfield);
		
		gatewaylabel = new Label(this, SWT.BORDER);
		setLabelParameters(gatewaylabel, "Gateway:");
		gatewayfield = new Text(this, SWT.RIGHT | SWT.BORDER);
		setTextFieldParameters(gatewayfield);
		/*
		for(int i=0; (i<text.length); i++) {
			int k=i;
			dnslabels[i] = new Label(this, SWT.BORDER);
			setLabelParameters(dnslabels[i], "DNS " + k++);
			dnsfields[i] = new Text(this, SWT.RIGHT | SWT.BORDER);
			dnsfields[i].setText(text[i]);
			setTextFieldParameters(dnsfields[i]);
		}
*/
		hostnamelabel = new Label(this, SWT.BORDER);
	    setLabelParameters(hostnamelabel, "Hostname:");
	    hostnamefield = new Text(this, SWT.RIGHT | SWT.BORDER);
		setTextFieldParameters(hostnamefield);
		
		max_input_speedlabel = new Label(this, SWT.BORDER);
		setLabelParameters(max_input_speedlabel, "Max Input Speed:");
		max_input_speedfield = new Text(this, SWT.RIGHT | SWT.BORDER);
		setTextFieldParameters(max_input_speedfield);
		
		max_output_speedlabel = new Label(this, SWT.BORDER);
		setLabelParameters(max_output_speedlabel, "Max Output Speed:");
		max_output_speedfield = new Text(this, SWT.RIGHT | SWT.BORDER);
		setTextFieldParameters(max_output_speedfield);
		
		
	    
	}
	
	private void setLabelParameters(Label label, String text) {
		
		label.setText(text);
		label.setLayoutData((new GridData(SWT.FILL, SWT.FILL, true, true)));
	}
	
	
	private void setTextFieldParameters(Text field) {
		
		field.setEditable(false);
		field.setEnabled(false);
		field.setBackground(white);
        field.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
        field.setLayoutData((new GridData(SWT.FILL, SWT.FILL, true, true)));
	}
	
    public void init() {
    	
    	
    	GridLayout layout = new GridLayout(2, false);        
    	setLayout(layout);
    	setLayoutData((new GridData(SWT.FILL, SWT.FILL, true, true)));
    	initLabelsAndFields();

    }
    
    public void repaint() {
    	
    	this.redraw();
    }
    
    public void setDNSLabelsFields(String [] label) {
    	
         text = label;
    	    	
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
	
		max_input_speedfield.setText( ((param[2]/1024==0) ? String.format("%.2f kbits/s", param[2]) : String.format("%.2f Mbits/s", param[2]/1024)));
		max_output_speedfield.setText((param[3]/1024==0) ? String.format("%.2f kbits/s", param[3]) : String.format("%.2f Mbits/s", param[3]/1024));
				
	}

}
