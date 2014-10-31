package compilador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Compilador {
	
	//Cambiar path del archivo de escritura
	public void crearTest( String nameFile ) throws Exception{
		BufferedReader br = new BufferedReader( new FileReader( nameFile ) );
		PrintWriter pw = new PrintWriter("Test.java");
	}
	
	
}
