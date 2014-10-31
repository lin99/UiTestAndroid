package gui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;


public class InterfazPrincipal {

	protected Shell shell;
	private ScrolledComposite scrolledComposite;
	private Table table;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InterfazPrincipal window = new InterfazPrincipal();
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
		shell.setSize(585, 415);
		shell.setLayout(new FormLayout());
		
		scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.left = new FormAttachment(0);
		fd_scrolledComposite.bottom = new FormAttachment(100, -180);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnAtributo = new TableColumn(table, SWT.CENTER);
		tblclmnAtributo.setWidth(124);
		tblclmnAtributo.setText("Atributo");
		
		TableColumn tblclmnValor = new TableColumn(table, SWT.NONE);
		tblclmnValor.setWidth(101);
		tblclmnValor.setText("Valor");
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		fd_scrolledComposite.top = new FormAttachment(toolBar, 6);
		FormData fd_toolBar = new FormData();
		fd_toolBar.top = new FormAttachment(0);
		fd_toolBar.left = new FormAttachment(0);
		fd_toolBar.right = new FormAttachment(0, 569);
		toolBar.setLayoutData(fd_toolBar);
		
		ToolItem tltmArchivo = new ToolItem(toolBar, SWT.NONE);
		tltmArchivo.setText("Archivo");
		
		ToolItem tltmEditar = new ToolItem(toolBar, SWT.NONE);
		tltmEditar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		tltmEditar.setText("Editar");
		
		Group grpAcciones = new Group(shell, SWT.NONE);
		fd_scrolledComposite.right = new FormAttachment(100, -319);
		grpAcciones.setText("Agregar Acciones");
		FormData fd_grpAcciones = new FormData();
		fd_grpAcciones.bottom = new FormAttachment(scrolledComposite, 38, SWT.BOTTOM);
		fd_grpAcciones.top = new FormAttachment(toolBar, 6);
		fd_grpAcciones.left = new FormAttachment(scrolledComposite, 6);
		fd_grpAcciones.right = new FormAttachment(100);
		grpAcciones.setLayoutData(fd_grpAcciones);
		
		TabFolder tabFolder = new TabFolder(grpAcciones, SWT.NONE);
		tabFolder.setBounds(0, 25, 313, 170);
		
		TabItem tbtmAccionesDispositivo = new TabItem(tabFolder, SWT.NONE);
		tbtmAccionesDispositivo.setText("Acciones Dispositivo");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmAccionesDispositivo.setControl(composite);
		
		Button btnGirarIzquierda = new Button(composite, SWT.NONE);
		btnGirarIzquierda.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnGirarIzquierda.setBounds(23, 26, 120, 25);
		btnGirarIzquierda.setText("Girar Izquierda");
		
		Button btnGirarDerecha = new Button(composite, SWT.NONE);
		btnGirarDerecha.setText("Girar Derecha");
		btnGirarDerecha.setBounds(163, 26, 120, 25);
		
		Button btnBack = new Button(composite, SWT.NONE);
		btnBack.setBounds(10, 79, 75, 25);
		btnBack.setText("Back");
		
		Button btnHome = new Button(composite, SWT.NONE);
		btnHome.setBounds(117, 79, 75, 25);
		btnHome.setText("Home");
		
		Button btnRecentApps = new Button(composite, SWT.NONE);
		btnRecentApps.setBounds(220, 79, 75, 25);
		btnRecentApps.setText("Recent Apps");
		
		TabItem tbtmClick = new TabItem(tabFolder, SWT.NONE);
		tbtmClick.setText("Click");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmClick.setControl(composite_1);
		
		Group grpPosicion = new Group(composite_1, SWT.NONE);
		grpPosicion.setText("Posicion");
		grpPosicion.setBounds(0, 0, 305, 62);
		
		Group grpComponente = new Group(composite_1, SWT.NONE);
		grpComponente.setText("Componente");
		grpComponente.setBounds(0, 68, 305, 64);
		
		TabItem tbtmTexto = new TabItem(tabFolder, SWT.NONE);
		tbtmTexto.setText("Texto");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmTexto.setControl(composite_2);
		
		text = new Text(composite_2, SWT.BORDER);
		text.setBounds(108, 16, 187, 21);
		
		Button btnNewButton = new Button(composite_2, SWT.NONE);
		btnNewButton.setBounds(29, 63, 75, 25);
		btnNewButton.setText("CrearAccion");
		
		Label lblIngresarTexto = new Label(composite_2, SWT.NONE);
		lblIngresarTexto.setBounds(10, 19, 94, 15);
		lblIngresarTexto.setText("Ingresar Texto: ");
		
		TabItem tbtmAssert = new TabItem(tabFolder, SWT.NONE);
		tbtmAssert.setText("Assert");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmAssert.setControl(composite_3);
		
		TabItem tbtmTiempoDeEspera = new TabItem(tabFolder, SWT.NONE);
		tbtmTiempoDeEspera.setText("Tiempo de Espera");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tbtmTiempoDeEspera.setControl(composite_4);

	}
}
