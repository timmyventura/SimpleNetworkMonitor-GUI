package monitor.view.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import monitor.capture.PacketCapture;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import swing2swt.layout.FlowLayout;

public class InitialForm {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(463, 141);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 31, 136, 29);
		lblNewLabel.setText("Choose an Interface");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(186, 31, 265, 29);
		
		PacketCapture pc = new PacketCapture();
		
		
		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
			     System.out.println(e.getSource());	
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(377, 79, 74, 29);
		btnNewButton.setText("Cancel");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(285, 79, 74, 29);
		btnNewButton_1.setText("Ready");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
