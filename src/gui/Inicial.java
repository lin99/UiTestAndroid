package gui;

import java.io.File;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import bash.CreadorWindows;
import control.CreadorCarpetasWindows;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import texto.Mensajes;
import texto.ValidadorTexto;

public class Inicial {

	protected Shell shell;
	private Text txtPathDelSdk;
	private Text txtPathDelAndroid;
	private Text textPathAndroid;
	private Text txtPathdelProyecto;
	private Text textPathProyecto;
	private Text txtIdAndroidApi_1;
	private Text textNumeroAPI;
	private Button btnGuardar;	

	private static CreadorWindows creador;
	private static CreadorCarpetasWindows creadorCarpetas;

	private String pathSDK, workspace, api;

	private Principal principal;
	
	private MensajeError mensajeError;
	
	private StringBuilder mensajes;
	
	public Inicial( Principal principal){
		super();
		pathSDK = "No asignado";
		workspace = "No asignado";
		this.principal = principal;

	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Inicial window = new Inicial( new Principal() );
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
		shell.setSize(552, 351);
		shell.setText("SWT Application");
		
		Group grpInformacion = new Group(shell, SWT.NONE);
		grpInformacion.setText("Informacion");
		grpInformacion.setBounds(37, 33, 461, 244);
		
		txtPathDelSdk = new Text(grpInformacion, SWT.NONE);
		txtPathDelSdk.setEditable(false);
		txtPathDelSdk.setText("Ingrese la siguiente informacion en los campos:");
		txtPathDelSdk.setBounds(22, 26, 263, 21);
		
		txtPathDelAndroid = new Text(grpInformacion, SWT.NONE);
		txtPathDelAndroid.setEditable(false);
		txtPathDelAndroid.setText("Path del Android SDK");
		txtPathDelAndroid.setBounds(22, 48, 125, 21);
		
		textPathAndroid = new Text(grpInformacion, SWT.BORDER);
		textPathAndroid.setBounds(22, 75, 392, 21);
		
		txtPathdelProyecto = new Text(grpInformacion, SWT.NONE);
		txtPathdelProyecto.setEditable(false);
		txtPathdelProyecto.setText("Path del Workspace");
		txtPathdelProyecto.setBounds(22, 114, 125, 21);
		
		textPathProyecto = new Text(grpInformacion, SWT.BORDER);
		textPathProyecto.setBounds(22, 141, 392, 21);
		
		txtIdAndroidApi_1 = new Text(grpInformacion, SWT.NONE);
		txtIdAndroidApi_1.setEditable(false);
		txtIdAndroidApi_1.setText("Id Android API");
		txtIdAndroidApi_1.setBounds(22, 181, 113, 21);
		
		textNumeroAPI = new Text(grpInformacion, SWT.BORDER);
		textNumeroAPI.setBounds(22, 211, 36, 21);
		
		btnGuardar = new Button(grpInformacion, SWT.NONE);
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				ValidadorTexto validador = new ValidadorTexto();
				
				validador = new ValidadorTexto(textPathAndroid.getText().toString(),validador.PATH_ANDROID);
				boolean v1 = validador.esValido();
				System.out.println(textPathAndroid.getText().toString()+" "+validador.PATH_ANDROID);
				
				validador = new ValidadorTexto(textPathProyecto.getText().toString(),validador.PATH_PROYECTO);
				boolean v2 = validador.esValido();
				System.out.println(textPathProyecto.getText().toString()+" "+validador.PATH_ANDROID);
	
				validador = new ValidadorTexto(textNumeroAPI.getText().toString(),validador.NUMERO_API);
				boolean v5 = validador.esValido();
				System.out.println(textNumeroAPI.getText().toString()+" "+validador.PATH_ANDROID);
				boolean creado = true;
			
				System.out.println(v1 + " " +v2 +  " " +v5);
				if(v1 && v2 && v5 ){
					
					pathSDK = textPathProyecto.getText()+"Bash\\";
					api = textNumeroAPI.getText();
					creadorCarpetas = new CreadorCarpetasWindows(pathSDK);
					boolean aux = creadorCarpetas.crearCarpetas();
					System.out.println("PATH: "+aux);
					if( !aux ){
						pathSDK = null;
						System.out.println("Error al crear Path");
						creado = false;
					}
					
					workspace = textPathProyecto.getText();
					creadorCarpetas = new CreadorCarpetasWindows(workspace+"Tig\\src\\");

					aux = creadorCarpetas.crearCarpetas();
					System.out.println("workspace: "+aux);
					if( !aux ){
						workspace = null;
						System.out.println("Error al crear Carpetas en Workspace");
						creado = false;
					}
					
					
					//workspace = textPathProyecto.getText();
					
					creador = new CreadorWindows(textPathAndroid.getText(), workspace, 
							"TestUi", textNumeroAPI.getText(),
							"Tig");

					StringTokenizer st = new StringTokenizer(workspace,"\\");
					String disco = st.nextToken();
					creador.batCrearBuild();
					creador.batAntBuild(disco);
					creador.batPushRun();
					
					principal.setPath( pathSDK );
					principal.setWorkspace( workspace );

					//creador.ejecutar(CreadorWindows.CREAR_BUILD);
					//creador.ejecutar(CreadorWindows.CREAR_ANT);
					//creador.ejecutar(CreadorWindows.CREAR_PUSH_RUN);
					if( creado )
						shell.close();
					principal.setApi(api);
				}
				
				
				else {
					mensajeError = new MensajeError();
					mensajes = new StringBuilder();
					
					if(!v1){
						mensajes.append(Mensajes.MJS_ERROR_SDK + "\n");
						//mensajeError.open( Mensajes.MJS_ERROR_SDK );
					}if(!v2){
						mensajes.append(Mensajes.MJS_DIRECTORIO + "\n");
					}if(!v5){
						mensajes.append(Mensajes.MJS_API+ "\n");
					}
					
					mensajeError.open(mensajes.toString());
					
					mensajeError = null;
					System.out.println("NO! (toca hacer una ventana!)");
				}
			}
		});
		btnGuardar.setBounds(364, 196, 75, 25);
		btnGuardar.setText("Guardar");
		
		Button btnBuscarSDK = new Button(grpInformacion, SWT.NONE);
		btnBuscarSDK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				JFrame aux = new JFrame();
				fc.setDialogTitle("Seleccionar el path del SDK");
				fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
				
				int returnVal = fc.showOpenDialog( aux );
				if( returnVal == JFileChooser.APPROVE_OPTION ){
					File file = fc.getSelectedFile();
					textPathAndroid.setText( file.getAbsolutePath()+"\\" );
				}
				aux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				aux.dispose();
				aux = null;
			}
		});
		btnBuscarSDK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnBuscarSDK.setBounds(420, 73, 31, 25);
		btnBuscarSDK.setText("...");
		
		Button btnBuscarWorkspace = new Button(grpInformacion, SWT.NONE);
		btnBuscarWorkspace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				JFrame aux = new JFrame();
				fc.setDialogTitle("Seleccionar el path del Workspace");
				fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
				
				int returnVal = fc.showOpenDialog( aux );
				if( returnVal == JFileChooser.APPROVE_OPTION ){
					File file = fc.getSelectedFile();
					textPathProyecto.setText( file.getAbsolutePath()+"\\" );
				}
				aux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				aux.dispose();
				aux = null;
			}
		});
		btnBuscarWorkspace.setBounds(420, 139, 31, 25);
		btnBuscarWorkspace.setText("...");

	}

	public static CreadorWindows getCreador() {
		return creador;
	}

	public static void setCreador(CreadorWindows creador) {
		Inicial.creador = creador;
	}
}
