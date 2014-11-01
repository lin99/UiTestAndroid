package control;

/**
 * Se encarga de la logica de las acciones del proyecto.
 * Llamar a la clases para la creacion del bat
 * Cargar los archivos
 * Llamar a la clase que compila la nueva test suit
 * 
 * @author w7
 *
 */
public class Controlador {

	private EscritorArchivos escritor;
	private String path;
	
	
	public Controlador(String path) {
		super();
		this.path = path;
		escritor = new EscritorArchivos();
	}

	public boolean guardar(String text) {
		return escritor.crearArchivo( path, new StringBuilder(text) );
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
		
	
}

