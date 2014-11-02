package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Inicial {

	protected Shell shell;
	private Text txtPathDelSdk;
	private Text txtPathDelAndroid;
	private Text textPathAndroid;
	private Text txtPathdelProyecto;
	private Text txtNombreDeLa;
	private Text textPathProyecto;
	private Text txtNombredelProyecto;
	private Text textProyecto;
	private Text textClase;
	private Text txtNombreDelPaquete;
	private Text textPaquete;
	private Text txtIdAndroidApi_1;
	private Text text;
	private Button btnGuardar;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Inicial window = new Inicial();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
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
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(new Point(156, 54));
		shell.setSize(552, 410);
		shell.setText("SWT Application");
		
		Group grpInformacion = new Group(shell, SWT.NONE);
		grpInformacion.setText("Informacion");
		grpInformacion.setBounds(37, 33, 461, 316);
		
		txtPathDelSdk = new Text(grpInformacion, SWT.NONE);
		txtPathDelSdk.setText("Ingrese la siguiente informacion en los campos:");
		txtPathDelSdk.setBounds(22, 26, 263, 21);
		
		txtPathDelAndroid = new Text(grpInformacion, SWT.NONE);
		txtPathDelAndroid.setText("Path del Android SDK");
		txtPathDelAndroid.setBounds(22, 48, 125, 21);
		
		textPathAndroid = new Text(grpInformacion, SWT.BORDER);
		textPathAndroid.setBounds(22, 75, 417, 21);
		
		txtPathdelProyecto = new Text(grpInformacion, SWT.NONE);
		txtPathdelProyecto.setText("Path del proyecto");
		txtPathdelProyecto.setBounds(22, 114, 125, 21);
		
		txtNombreDeLa = new Text(grpInformacion, SWT.NONE);
		txtNombreDeLa.setText("Nombre de la clase");
		txtNombreDeLa.setBounds(238, 181, 113, 21);
		
		textPathProyecto = new Text(grpInformacion, SWT.BORDER);
		textPathProyecto.setBounds(22, 141, 417, 21);
		
		txtNombredelProyecto = new Text(grpInformacion, SWT.NONE);
		txtNombredelProyecto.setText("Nombre del proyecto");
		txtNombredelProyecto.setBounds(22, 181, 125, 21);
		
		textProyecto = new Text(grpInformacion, SWT.BORDER);
		textProyecto.setBounds(22, 208, 160, 21);
		
		textClase = new Text(grpInformacion, SWT.BORDER);
		textClase.setBounds(238, 208, 160, 21);
		
		txtNombreDelPaquete = new Text(grpInformacion, SWT.NONE);
		txtNombreDelPaquete.setText("Nombre del paquete");
		txtNombreDelPaquete.setBounds(22, 246, 125, 21);
		
		textPaquete = new Text(grpInformacion, SWT.BORDER);
		textPaquete.setBounds(22, 273, 160, 21);
		
		txtIdAndroidApi_1 = new Text(grpInformacion, SWT.NONE);
		txtIdAndroidApi_1.setText("Id Android API");
		txtIdAndroidApi_1.setBounds(238, 246, 113, 21);
		
		text = new Text(grpInformacion, SWT.BORDER);
		text.setBounds(238, 273, 36, 21);
		
		btnGuardar = new Button(grpInformacion, SWT.NONE);
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
			}
		});
		btnGuardar.setBounds(364, 273, 75, 25);
		btnGuardar.setText("Guardar");

	}
}
