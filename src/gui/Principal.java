package gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.SWTResourceManager;

import bash.Creador;
import bash.CreadorWindows;

import com.android.uiautomator.tree.AttributePair;
import com.android.uiautomator.tree.BasicTreeNode;
import com.android.uiautomator.tree.BasicTreeNodeContentProvider;
import compilador.Comandos;
import compilador.Compilador;

import control.Controlador;
import control.Model;
import control.ScreenshotAction;

public class Principal {

	private static final int IMG_BORDER = 2;
	
	protected Shell shlUitestandroid;
	private Text text1;
	private static Controlador controlador;
	private CreadorWindows creador;

	
	private String pathSDK, workspace,api;
	private Text textX;
	private Text textY;
	private float mScale = 1.0f;
	private Image cachedScaleImage = null;
	private Canvas canvas;
	private TreeViewer treeViewer;
	private TableViewer tableViewer;
	private ScreenshotAction screenshotAction;
	private Text nameComponentClick;
	private Text nameComponentText;
	
	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Inicial inicial;
		try {
			Principal window = new Principal();
			inicial = new Inicial( window );
			inicial.open();
			if( window.pathSDK!=null && window.workspace!=null ){
				controlador = new Controlador(window.workspace+"comandos.pan");
				window.open();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
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
		screenshotAction = new ScreenshotAction(this);
		Model.createInstance( this );
		shlUitestandroid = new Shell();
		shlUitestandroid.setSize(899, 600);
		shlUitestandroid.setText("UiTestAndroid");
		
		Group grpGrupo = new Group(shlUitestandroid, SWT.NONE);
		grpGrupo.setText("Agregar Acciones");
		grpGrupo.setBounds(404, 222, 449, 241);
		
		Group grpAcciones = new Group(shlUitestandroid, SWT.NONE);
		grpAcciones.setText("Acciones");
		grpAcciones.setBounds(10, 259, 288, 213);
		
		final StyledText accionesTexto = new StyledText(grpAcciones, SWT.BORDER);
		accionesTexto.setBounds(0, 24, 288, 189);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(grpGrupo, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 27, 429, 204);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		TabFolder tabFolder = new TabFolder(scrolledComposite, SWT.NONE);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Acciones Dispositivo");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		
		Button btnGirarIzquierda = new Button(composite_1, SWT.NONE);
		btnGirarIzquierda.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		btnGirarIzquierda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				accionesTexto.append(Comandos.ROTATE + " " +Comandos.IZQUIERDA + "\n");
			}
		});
		btnGirarIzquierda.setBounds(75, 43, 100, 25);
		btnGirarIzquierda.setText("Girar Izquierda");
		
		Button btnGirarDerecha = new Button(composite_1, SWT.NONE);
		btnGirarDerecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				accionesTexto.append(Comandos.ROTATE + " "+ Comandos.DERECHA+ "\n");
			}
		});
		btnGirarDerecha.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		btnGirarDerecha.setBounds(205, 43, 100, 25);
		btnGirarDerecha.setText("Girar Derecha");
		
		Button btnBack = new Button(composite_1, SWT.NONE);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				accionesTexto.append(Comandos.ATRAS + "\n");
			}
		});
		btnBack.setBounds(24, 89, 90, 25);
		btnBack.setText("Back");
		
		Button btnHome = new Button(composite_1, SWT.NONE);
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				accionesTexto.append(Comandos.HOME + "\n");
			}
		});
		btnHome.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnHome.setBounds(138, 89, 90, 25);
		btnHome.setText("Home");
		
		Button btnRecentApps = new Button(composite_1, SWT.NONE);
		btnRecentApps.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				accionesTexto.append(Comandos.APPS_RECIENTES + "\n");
			}
		});
		btnRecentApps.setBounds(251, 89, 90, 25);
		btnRecentApps.setText("Recent Apps");
		
		TabItem tbtmClick = new TabItem(tabFolder, SWT.NONE);
		tbtmClick.setText("Click");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmClick.setControl(composite);
		
		Group grpPosicion = new Group(composite, SWT.NONE);
		grpPosicion.setText("Posicion");
		grpPosicion.setBounds(10, 10, 363, 65);
		
		Label lblX = new Label(grpPosicion, SWT.NONE);
		lblX.setFont(SWTResourceManager.getFont("Lucida Fax", 14, SWT.NORMAL));
		lblX.setBounds(37, 28, 18, 27);
		lblX.setText("X:");
		
		Label lblY = new Label(grpPosicion, SWT.NONE);
		lblY.setText("Y:");
		lblY.setFont(SWTResourceManager.getFont("Lucida Fax", 14, SWT.NORMAL));
		lblY.setBounds(151, 28, 18, 27);
		
		textX = new Text(grpPosicion, SWT.BORDER);
		textX.setBounds(59, 28, 76, 21);
		
		textY = new Text(grpPosicion, SWT.BORDER);
		textY.setBounds(175, 28, 71, 21);
		
		Button btnAgregar = new Button(grpPosicion, SWT.NONE);
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				accionesTexto.append(Comandos.CLICK + " "+ textX.getText() + " " + textY.getText() + "\n");
			}
		});
		btnAgregar.setBounds(278, 28, 75, 25);
		btnAgregar.setText("Agregar");
		
		Group grpComponente = new Group(composite, SWT.NONE);
		grpComponente.setText("Componente");
		grpComponente.setBounds(10, 80, 363, 65);
		
		Button btnAgregarComponente = new Button(grpComponente, SWT.NONE);
		btnAgregarComponente.setText("Agregar");
		btnAgregarComponente.setBounds(278, 30, 75, 25);
		
		nameComponentClick = new Text(grpComponente, SWT.BORDER);
		nameComponentClick.setEnabled(false);
		nameComponentClick.setBounds(25, 32, 220, 21);
		
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
		btnCrearAccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				accionesTexto.append(Comandos.INGRESAR_TEXTO + " "+ text1.getText() + "\n");
			}
		});
		btnCrearAccion.setBounds(112, 92, 75, 25);
		btnCrearAccion.setText("Crear Accion");
		
		nameComponentText = new Text(composite_2, SWT.BORDER);
		nameComponentText.setEnabled(false);
		nameComponentText.setBounds(112, 49, 220, 21);
		
		Label lblComponente = new Label(composite_2, SWT.NONE);
		lblComponente.setBounds(23, 55, 83, 15);
		lblComponente.setText("Componente:");
		
		TabItem tabAssert = new TabItem(tabFolder, SWT.NONE);
		tabAssert.setText("Assert");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tabAssert.setControl(composite_3);
		
		TabItem tabEspera = new TabItem(tabFolder, SWT.NONE);
		tabEspera.setText("Tiempo de Espera");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tabEspera.setControl(composite_4);
		scrolledComposite.setContent(tabFolder);
		scrolledComposite.setMinSize(tabFolder.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Group grpPropiedades = new Group(shlUitestandroid, SWT.NONE);
		grpPropiedades.setText("Propiedades");
		grpPropiedades.setBounds(650, 10, 203, 189);
		
		Composite tableContainer = new Composite(grpPropiedades, SWT.NONE);
		tableContainer.setBounds(10, 28, 193, 161);
		tableContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        TableColumnLayout columnLayout = new TableColumnLayout();
        tableContainer.setLayout(columnLayout);
		tableViewer = new TableViewer(tableContainer, SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		table.setLinesVisible( true );
		table.setBounds(10, 23, 183, 156);
		
		tableViewer.setContentProvider(new ArrayContentProvider());
        TableViewerColumn tableViewerColumnKey = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnKey = tableViewerColumnKey.getColumn();
        tableViewerColumnKey.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof AttributePair) {
                    // first column, shows the attribute name
                    return ((AttributePair)element).key;
                }
                return super.getText(element);
            }
        });
        columnLayout.setColumnData(tblclmnKey,
                new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, true));
        TableViewerColumn tableViewerColumnValue = new TableViewerColumn(tableViewer, SWT.NONE);
        tableViewerColumnValue.setEditingSupport(new AttributeTableEditingSupport(tableViewer));
        TableColumn tblclmnValue = tableViewerColumnValue.getColumn();
        columnLayout.setColumnData(tblclmnValue,
                new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));
        tableViewerColumnValue.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof AttributePair) {
                    // second column, shows the attribute value
                    return ((AttributePair)element).value;
                }
                return super.getText(element);
            }
        });
		
		Menu menu = new Menu(shlUitestandroid, SWT.BAR);
		shlUitestandroid.setMenuBar(menu);
		
		MenuItem mntmArchivo = new MenuItem(menu, SWT.NONE);
		mntmArchivo.setText("Archivo");
		
		MenuItem mntmEdicion = new MenuItem(menu, SWT.NONE);
		mntmEdicion.setText("Edicion");
		
		MenuItem mntmSeparador = new MenuItem(menu, SWT.SEPARATOR);
		mntmSeparador.setText("Separador");
		
		MenuItem mntmSnapshot = new MenuItem(menu, SWT.NONE);
		mntmSnapshot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				screenshotAction.run();
			}
		});
		
		mntmSnapshot.setImage(SWTResourceManager.getImage(Principal.class, "/res/mipmap-mdpi/sym_def_app_icon.png"));
		mntmSnapshot.setText("Snapshot");
		
		Button btnCompilar = new Button(shlUitestandroid, SWT.NONE);
		btnCompilar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Compilador compilador = new Compilador();
				try {
					compilador.crearTest(workspace+"comandos.pan", workspace);
					System.out.println("Funciono");
				} catch (Exception e1) {
					System.out.println(":( no funciono");
					e1.printStackTrace();
				}
			}
		});
		btnCompilar.setBounds(314, 297, 75, 25);
		btnCompilar.setText("Compilar");
		
		Button btnGuardar = new Button(shlUitestandroid, SWT.NONE);
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				boolean esExistoso = controlador.guardar(accionesTexto.getText());
				if(esExistoso){
					System.out.println("Todo bien");
				}else{
					System.out.println("Pu;alada");
				}
			}
		});
		btnGuardar.setBounds(314, 266, 75, 25);
		btnGuardar.setText("Guardar");
		
		Button btnEjecutar = new Button(shlUitestandroid, SWT.NONE);
		btnEjecutar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				creador = new CreadorWindows(pathSDK, workspace, "TestUi", api, "Tig");
				System.out.println(creador.getPathWorkspace());
				
				creador.ejecutar(Creador.CREAR_BUILD);
				creador.ejecutar(Creador.CREAR_ANT);
				creador.ejecutar(Creador.CREAR_PUSH_RUN);
			}
		});
		btnEjecutar.setBounds(314, 328, 75, 25);
		btnEjecutar.setText("Ejecutar");
		
		Button btnGuardarAcciones = new Button(shlUitestandroid, SWT.NONE);
		btnGuardarAcciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				JFrame aux = new JFrame();
				fc.setDialogTitle("Guardar Acciones");
				
				int returnVal = fc.showSaveDialog( aux );
				if( returnVal == JFileChooser.APPROVE_OPTION ){
					System.out.println("SELECTED FILE:"+( fc.getSelectedFile().getAbsolutePath()+"\\" ));
				}
				aux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				aux.dispose();
				aux = null;
			}
		});
		btnGuardarAcciones.setBounds(10, 478, 114, 25);
		btnGuardarAcciones.setText("Guardar Acciones");
		
		Button btnCargarAcciones = new Button(shlUitestandroid, SWT.NONE);
		btnCargarAcciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				JFrame aux = new JFrame();
				fc.setDialogTitle("Cargar Acciones");
				
				int returnVal = fc.showOpenDialog( aux );
				if( returnVal == JFileChooser.APPROVE_OPTION ){
					System.out.println("SELECTED FILE:"+( fc.getSelectedFile().getAbsolutePath()+"\\" ));
				}
				aux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				aux.dispose();
				aux = null;
			}
		});
		btnCargarAcciones.setBounds(153, 478, 98, 25);
		btnCargarAcciones.setText("Cargar Acciones");
		
		Group grpComponentes = new Group(shlUitestandroid, SWT.NONE);
		grpComponentes.setText("Componentes");
		grpComponentes.setBounds(404, 10, 240, 189);
		
		treeViewer = new TreeViewer(grpComponentes, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		tree.setBounds(10, 22, 220, 157);
		GridData gd_Tree = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
        gd_Tree.widthHint = 350;
        tree.setLayoutData(gd_Tree);
        treeViewer.setContentProvider(new BasicTreeNodeContentProvider());
     // default LabelProvider uses toString() to generate text to display
        treeViewer.setLabelProvider(new LabelProvider());
		
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (event.getSelection().isEmpty()) {
                    Model.getModel().setSelectedNode(null);
                } else if (event.getSelection() instanceof IStructuredSelection) {
                    IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                    Object o = selection.toArray()[0];
                    if (o instanceof BasicTreeNode) {
                        Model.getModel().setSelectedNode((BasicTreeNode)o);
                    }
                }
            }
        });
        // move focus so that it's not on tool bar (looks weird)
        tree.setFocus();
		
		
		canvas = new Canvas(shlUitestandroid, SWT.NONE);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		canvas.setBounds(10, 10, 379, 241);
		canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent e) {
                Model.getModel().toggleExploreMode();
            }
        });
		canvas.addMouseMoveListener(new MouseMoveListener() {
            @Override
            public void mouseMove(MouseEvent e) {
                if (Model.getModel().isExploreMode()) {
                    Model.getModel().updateSelectionForCoordinates(
                            getInverseScaledSize(e.x - IMG_BORDER),
                            getInverseScaledSize(e.y - IMG_BORDER));
                }
            }
        });
		canvas.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                if (cachedScaleImage != null) {
                    // shifting the image here, so that there's a border around screen shot
                    // this makes highlighting red rectangles on the screen shot edges more visible
                    e.gc.drawImage(cachedScaleImage, IMG_BORDER, IMG_BORDER);
                    Rectangle rect = Model.getModel().getCurrentDrawingRect();
                    if (rect != null) {
                        e.gc.setForeground(e.gc.getDevice().getSystemColor(SWT.COLOR_RED));
                        if (Model.getModel().isExploreMode()) {
                            // when we highlight nodes dynamically on mouse move,
                            // use dashed borders
                            e.gc.setLineStyle(SWT.LINE_DASH);
                            e.gc.setLineWidth(1);
                        } else {
                            // when highlighting nodes on tree node selection,
                            // use solid borders
                            e.gc.setLineStyle(SWT.LINE_SOLID);
                            e.gc.setLineWidth(2);
                        }
                        e.gc.drawRectangle(
                                IMG_BORDER + getScaledSize(rect.x),
                                IMG_BORDER + getScaledSize(rect.y),
                                getScaledSize(rect.width),
                                getScaledSize(rect.height));
                    }
                }
            }
        });
		
		
	}

	public String getPath() {
		return pathSDK;
	}

	public void setPath(String path) {
		this.pathSDK = path;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
	
	public Shell getShell() {
		return shlUitestandroid;
	}

	public void loadScreenshotAndXml() {
		
		GridData gd = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 3);
        Rectangle r = Model.getModel().getScreenshot().getBounds();
        mScale = calcScreenshotScale(r.width, r.height);
        updateScaledImage( Model.getModel().getScreenshot() );
        gd.minimumHeight = getScaledSize(r.height) + 2 * IMG_BORDER;
        gd.minimumWidth = getScaledSize(r.width) + 2 * IMG_BORDER;
        canvas.setLayoutData(gd);
        // load xml into tree
        BasicTreeNode wrapper = new BasicTreeNode();
        // putting another root node on top of existing root node
        // because Tree seems to like to hide the root node
        wrapper.addChild( Model.getModel().getXmlRootNode() );
        System.out.println("Wrapper: "+wrapper.x+" "+wrapper.y);
        treeViewer.setInput(wrapper);
        treeViewer.getTree().setFocus();
        // resize & reposition window
        getShell().pack();
        adjustShellLocation();
		
	}
	
	private void adjustShellLocation() {
        Monitor m = findCurrentMonitor();
        if (m == null) {
            System.err.println("Cannot find current monitor!");
            return;
        }
        Rectangle r = m.getBounds();
        Rectangle b = getShell().getBounds();
        int x = b.x, y = b.y;
        boolean shouldChangePosition = false;
        if (!(r.x <= b.x && b.x + b.width < r.x + r.width)) {
            // out of bounds horizontally, need adjustment
            shouldChangePosition = true;
            // since we are scaling down, the window really shouldn't be larger than monitor
            // i.e. should not have negative here, just a safety measure
            x = Math.max(0, (r.width - b.width) / 2) + r.x;
        }
        if (!(r.y <= b.y && b.y + b.height < r.y + r.height)) {
            // out of bounds vertically, need adjustment
            shouldChangePosition = true;
            y = Math.max(0, (r.height - b.height) / 2) + r.y;
        }
        if (shouldChangePosition) {
            getShell().setLocation(x, y);
        }
    }
	
	private void updateScaledImage(Image image) {
        Image scaled = image;
        if (mScale != 1.0f) {
            // some voodoo to get a smooth scaled image ,otherwise it looks like crap
            // but the actual outcome could still be platform dependent
            int w = image.getBounds().width;
            int h = image.getBounds().height;
            int ws = getScaledSize(w);
            int hs = getScaledSize(h);
            scaled = new Image(getShell().getDisplay(), ws, hs);
            GC gc = new GC(scaled);
            gc.setAntialias(SWT.ON);
            gc.setInterpolation(SWT.HIGH);
            gc.drawImage(image, 0, 0, w, h, 0, 0, ws, hs);
            gc.dispose();
        }
        if (cachedScaleImage != null) {
            cachedScaleImage.dispose();
        }
        cachedScaleImage = scaled;
    }
	
	public void updateTreeSelection(BasicTreeNode node) {
        treeViewer.setSelection(new StructuredSelection(node), true);
    }
	
	/**
     * Causes a redraw of the canvas.
     *
     * The drawing code of canvas will handle highlighted nodes and etc based on data
     * retrieved from Model
     */
    public void updateScreenshot() {
        canvas.redraw();
    }
	
    public void loadAttributeTable() {
        // udpate the lower right corner table to show the attributes of the node
        tableViewer.setInput(
                Model.getModel().getSelectedNode().getAttributesArray());
        for(Object o : Model.getModel().getSelectedNode().getAttributesArray() ){
        	AttributePair ap = (AttributePair) o;
        	System.out.println("KEY:"+ap.key+" VALUE"+ap.value);
        }
    }
    
    private int getInverseScaledSize(int size) {
        if (mScale == 1.0f) {
            return size;
        } else {
            return new Double(Math.floor((size / mScale))).intValue();
        }
    }
    
	private int getScaledSize(int size) {
        if (mScale == 1.0f) {
            return size;
        } else {
            return new Double(Math.floor((size * mScale))).intValue();
        }
    }
	
	private float calcScreenshotScale(int width, int height) {
		
        Rectangle r = canvas.getBounds();//findCurrentMonitor().getClientArea();
        // add some room
        width += 300;
        height += 100;
        float scale = Math.min(1.0f,Math.min(r.width / (float)width,
                r.height / (float)height));
        // if we are not showing the original size, scale down a bit more
        if (scale < 1.0f) {
            scale *= 1.2f;
        }
        System.out.println();
        return scale;
    }
	
	/**
     * Find out which monitor the current window's top left corner is in
     *
     * @return
     */
    private Monitor findCurrentMonitor() {
        Rectangle b = getShell().getBounds();
        for (Monitor m : getShell().getDisplay().getMonitors()) {
            Rectangle r = m.getBounds();
            if (r.x <= b.x && b.x < r.x + r.width
                    && r.y <= b.y && b.y < r.y + r.height) {
                return m;
            }
        }
        return null;
    }
}
