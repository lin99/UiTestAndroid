package control;

import java.io.File;

public class CreadorCarpetasWindows extends CreadorCarpetas{
	
	File file;
	String path;
	public String getPath() {
		return path;
	}

	public CreadorCarpetasWindows(String path){
		this.path = path;
	}

	public static void main(String[] args) {
	CreadorCarpetasWindows creador = new CreadorCarpetasWindows("D:\\directorio\\");
	creador.crearCarpeta();
	System.out.println(creador.getPath());
	}

	@Override
	public boolean crearCarpeta() {
		
		File file = new File(path);
		file.mkdir();
		return false;
	}
	
	public boolean crearCarpetas() {
		
		File file = new File(path);
		file.mkdirs();
		return file.exists();
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}
	
	public void setPath(String path) {
		this.path = path;
	}


	
	
}
