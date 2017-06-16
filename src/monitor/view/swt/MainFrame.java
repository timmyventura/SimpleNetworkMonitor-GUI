package monitor.view.swt;



import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jnetpcap.PcapIf;

import monitor.network.OSNetworkFactory;
import monitor.model.InformMediator;
import monitor.capture.PacketCapture;
import monitor.model.PieMediator;
import monitor.model.SpeedMediator;
import monitor.view.View;



public class MainFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Composite  pie;
	public Composite inform;
	public Composite speed;
	public GridData data;
	private static final Shell shell = new Shell();
	
	public static class Test{
		
		public static void main(String [] args) {
			

			
			 PacketCapture pc = new PacketCapture();

		       PcapIf device = null;
		       pc.chooseDevice(device);
		      
		       PieMediator pm = new PieMediator();
		       SpeedMediator sm = new SpeedMediator(device);   
		       InformMediator im = new InformMediator(OSNetworkFactory.returnConcreteNetworkFactoryObject(System.getProperty("os.name"), device));
			
		       MainFrame frame = new MainFrame();
		      // frame.setShell(new Shell());
		       SpeedGraph speedgraph = new SpeedGraph(frame.getShell(), SWT.BORDER);
		       speedgraph.setMaxAge(60000);
		       PieGraph piegraph = new PieGraph(frame.getShell(), SWT.BORDER);
		       InformGraph inform = new InformGraph(frame.getShell(), SWT.BORDER);
		       
		      frame.inform = inform;
		      frame.speed = speedgraph;
		      frame.pie = piegraph;
		       
		       
		       pc.addMediator(sm);
			   pc.addMediator(pm);
			  
			   sm.addView((View)frame.getSpeedGraph());
			   sm.addView((View)frame.getInformGraph());
			   pm.addView((View)frame.getPieGraph());
			   im.addView((View)frame.getInformGraph());
			   
			  // mf.showMainFrame();
			   
			  // frame.showMainFrame();
				
				//NetstatHandler nh = new NetstatHandler();
				/*
		        ExecutorService exec = Executors.newFixedThreadPool(4);
		       //exec.execute(mf);
		        exec.execute(pc);
		        exec.execute(sm);
		        exec.execute(pm);
               */
			   
			   Display.getDefault().syncExec(new Runnable() {
				    public void run() {
				    	frame.showMainFrame();
				    }
				});
		       
			   Display.getDefault().syncExec(new Runnable() {
				    public void run() {
				       pc.run();
				    }
				});
			   Display.getDefault().syncExec(new Runnable() {
				    public void run() {
				    	sm.run();
				    }
				});
			   Display.getDefault().syncExec(new Runnable() {
				    public void run() {
				       pm.run();
				    }
				});

		       
		}
		
	}
	
	
	
	
    public void showMainFrame() {
    	
    	Display display = Display.getDefault();
		shell.setSize(1000, 600);
		shell.setText("SWT Application");
		GridLayout layout = new GridLayout(2, false);
		
		shell.setLayout(layout);
        
		
		data= new GridData();
		//pie = new PieGraph(shell, SWT.BORDER);
		data.grabExcessHorizontalSpace=true;
		data.grabExcessVerticalSpace=true;
		data.verticalAlignment=SWT.FILL;
		data.horizontalAlignment=SWT.FILL;
		data.heightHint = shell.getSize().y/2+100;
		data.widthHint = shell.getSize().x/2+100;
		System.out.println(pie);
		pie.setLayoutData(data);
		
		data= new GridData();
		//inform = new InformGraph(shell, SWT.BORDER);
		data.verticalAlignment=SWT.FILL;
		data.horizontalAlignment=SWT.FILL;
		data.grabExcessHorizontalSpace=true;
		data.grabExcessVerticalSpace=true;
		inform.setLayoutData(data);
		
		data= new GridData();
	   // speed = new SpeedGraph(shell, SWT.BORDER);
		data.grabExcessHorizontalSpace=true;
		data.grabExcessVerticalSpace=true;
		data.verticalAlignment=SWT.FILL;
		data.horizontalAlignment=SWT.FILL;
		data.horizontalSpan = layout.numColumns;
		data.heightHint = shell.getSize().y/2;
		data.widthHint = shell.getSize().x;
		speed.setLayoutData(data);
		
		
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
    	
    }




	public Composite getPieGraph() {
		return pie;
	}




	public void setPieGraph(Composite pie) {
		this.pie = pie;
	}




	public Composite getInformGraph() {
		return inform;
	}




	public void setInformGraph(Composite inform) {
		this.inform = inform;
	}




	public Composite getSpeedGraph() {
		return speed;
	}




	public void setSpeedGraph(Composite speed) {
		this.speed = speed;
	}




	public Shell getShell() {
		return shell;
	}




	@Override
	public void run() {
		
		
		
	}

	

/*
	public void setShell(Shell shell) {
		this.shell = shell;
	}
	*/
}
