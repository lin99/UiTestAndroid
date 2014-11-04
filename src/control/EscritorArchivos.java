package control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscritorArchivos {

	public boolean crearArchivo(String path, StringBuilder contenido){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try {
			
			fichero = new FileWriter(path);
			pw = new PrintWriter(fichero);
			pw.println(contenido);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		} finally {
	           try {
	               if (null != pw)
	                  pw.close();
	               } catch (Exception e2) {
	                  e2.printStackTrace();
	               }
		}
	}
	
}
