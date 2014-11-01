package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class Principal {

	protected Shell shlUitestandroid;
	private Text text1;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Principal window = new Principal();
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
		shlUitestandroid.open();
		shlUitestandroid.layout();
		while (!shlUitestandroid.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlUitestandroid = new Shell();
		shlUitestandroid.setSize(646, 388);
		shlUitestandroid.setText("UiTestAndroid");
		
		Group grpGrupo = new Group(shlUitestandroid, SWT.NONE);
		grpGrupo.setText("Agregar Acciones");
		grpGrupo.setBounds(10, 10, 390, 241);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(grpGrupo, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 27, 370, 204);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		TabFolder tabFolder = new TabFolder(scrolledComposite, SWT.NONE);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Acciones Dispositivo");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		
		Button btnGirarIzquierda = new Button(composite_1, SWT.NONE);
		btnGirarIzquierda.setBounds(75, 43, 100, 25);
		btnGirarIzquierda.setText("Girar Izquierda");
		
		Button btnGirarDerecha = new Button(composite_1, SWT.NONE);
		btnGirarDerecha.setBounds(205, 43, 100, 25);
		btnGirarDerecha.setText("Girar Derecha");
		
		Button btnBack = new Button(composite_1, SWT.NONE);
		btnBack.setBounds(24, 89, 90, 25);
		btnBack.setText("Back");
		
		Button btnHome = new Button(composite_1, SWT.NONE);
		btnHome.setBounds(138, 89, 90, 25);
		btnHome.setText("Home");
		
		Button btnRecentApps = new Button(composite_1, SWT.NONE);
		btnRecentApps.setBounds(251, 89, 90, 25);
		btnRecentApps.setText("Recent Apps");
		
		TabItem tbtmClick = new TabItem(tabFolder, SWT.NONE);
		tbtmClick.setText("Click");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmClick.setControl(composite);
		
		Group grpPosicion = new Group(composite, SWT.NONE);
		grpPosicion.setText("Posicion");
		grpPosicion.setBounds(10, 10, 323, 65);
		
		Group grpComponente = new Group(composite, SWT.NONE);
		grpComponente.setText("Componente");
		grpComponente.setBounds(10, 80, 323, 82);
		
		TabItem tbtmTexto = new TabItem(tabFolder, SWT.NONE);
		tbtmTexto.setText("Texto");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmTexto.setControl(composite_2);
		
		Label lblIngresarTexto = new Label(composite_2, SWT.NONE);
		lblIngresarTexto.setBounds(23, 28, 83, 15);
		lblIngresarTexto.setText("Ingresar Texto:");
		
		text1 = new Text(composite_2, SWT.BORDER);
		text1.setBounds(112, 22, 220, 21);
		
		Button btnCrearAccion = new Button(composite_2, SWT.NONE);
		btnCrearAccion.setBounds(23, 63, 75, 25);
		btnCrearAccion.setText("Crear Accion");
		scrolledComposite.setContent(tabFolder);
		scrolledComposite.setMinSize(tabFolder.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Group grpPropiedades = new Group(shlUitestandroid, SWT.NONE);
		grpPropiedades.setText("Propiedades");
		grpPropiedades.setBounds(417, 10, 203, 241);
		
		table = new Table(grpPropiedades, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 25, 183, 206);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnAtributo = new TableColumn(table, SWT.NONE);
		tblclmnAtributo.setWidth(78);
		tblclmnAtributo.setText("Atributo");
		
		TableColumn tblclmnValor = new TableColumn(table, SWT.NONE);
		tblclmnValor.setWidth(100);
		tblclmnValor.setText("Valor");

	}

}
