package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MensajeError {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MensajeError window = new MensajeError();
			window.open( "" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open( String s ) {
		Display display = Display.getDefault();
		createContents( s );
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents( String s ) {
		shell = new Shell();
		shell.setSize(327, 171);
		shell.setText("SWT Application");
		
		Button aceptarButton = new Button(shell, SWT.NONE);
		aceptarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
			}
		});
		aceptarButton.setBounds(113, 102, 75, 25);
		aceptarButton.setText("Aceptar");
		
		Label lblUps = new Label(shell, SWT.NONE);
		lblUps.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		lblUps.setBounds(47, 10, 65, 25);
		lblUps.setText("Ups!");
		
		StyledText styledTextErrors = new StyledText(shell, SWT.NONE);
		styledTextErrors.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		styledTextErrors.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledTextErrors.setEditable(false);
		styledTextErrors.setEnabled(false);
		styledTextErrors.setBounds(38, 41, 240, 49);
		styledTextErrors.setText(s);

	}

	
}
