package bash;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import control.EscritorArchivos;

public class CreadorWindows extends Creador{
	
	static final String TOOL = "tools/android";
	static final String CREATE = "create uitest-project -n";
	static final String T = "-t";
	static final String P = "-p"; 
	static final String ADB = "adb";
	static final String PUSH = "push";
	static final String BIN = "bin\\";
	static final String JAR = ".jar";
	static final String DESTINO = "/data/local/tmp/";
	static final String SHELL= "shell uiautomator runtest"; 
	static final String C = "-c";
	

	
 	

	public CreadorWindows(String pathAndroid, String pathProyecto, String nombreClase, String nombrePaquete, String numeroAPI, String nombreProyecto){
		this.pathAndroid = pathAndroid;
		this.pathProyecto = pathProyecto;
		this.nombreClase = nombreClase;
		this.nombrePaquete = nombrePaquete;
		this.numeroAPI = numeroAPI;
		this.nombreProyecto = nombreProyecto;
		
	}
 	
 	public CreadorWindows(String pathAndroid, String pathWorkspace, String nombreClase, String numeroAPI, String nombreProyecto){
		this.pathAndroid = pathAndroid;
		this.nombreClase = nombreClase;
		this.numeroAPI = numeroAPI;
		this.nombreProyecto = nombreProyecto;
		this.pathWorkspace = pathWorkspace;
	}
	
	
	@Override
	public void batCrearBuild() {
		String instruccion = pathAndroid + TOOL + " " + CREATE + " "+nombreProyecto+ " " + T + " "+numeroAPI+ 
							" "+ P + " " + pathWorkspace+nombreProyecto;
		System.out.println(instruccion);
		
		StringBuilder sb = new StringBuilder();
		sb.append(instruccion);
		
		crearArchivo(CREAR_BUILD, sb);
	}

	@Override
	public void batAntBuild() {
	
		String instruccion  = "ant " +  "build";
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("cd " + pathWorkspace+nombreProyecto);
		sb.append("\n");
		sb.append(instruccion);
		
		crearArchivo(CREAR_ANT, sb);
	}
	
	//adb push D:\Lin\AndroidWorkspace\MyAppsTest\bin\MyAppsTest.jar /data/local/tmp/
	//adb shell uiautomator runtest MyAppsTest.jar -c paquete.LaunchSettings
	@Override
	public void batPushRun() {
		String instruccion = ADB +" " + PUSH +" "+ pathWorkspace+nombreProyecto +"\\"+ BIN + nombreProyecto+ JAR 
							+" " + DESTINO;
		String instruccion2 = ADB +" " + SHELL +" "+ nombreProyecto+ JAR +" " + C +" " 
							+nombreClase;
		
		StringBuilder sb = new StringBuilder();
		sb.append(instruccion);
		sb.append("\n");
		sb.append(instruccion2);
		
		crearArchivo(CREAR_PUSH_RUN, sb);
	}

	@Override
	public void ejecutarInit() {
		
	
	}

	
	private static void crearArchivo(int n, StringBuilder sb) {
		
		String nombre = seleccionar(n);
		
		EscritorArchivos escritorArchivos = new EscritorArchivos();
		escritorArchivos.crearArchivo(nombre, sb);
					
	}
	
	public void ejecutar(int n){
		String archivo = seleccionar(n);
		String comando;
		try {
			Process proceso = Runtime.getRuntime().exec(archivo);
			BufferedReader lector = new BufferedReader (new InputStreamReader (proceso.getInputStream()));
            while ((comando = lector.readLine()) != null) {
                System.out.println(comando);
            }
                lector.close();
            }catch (Exception err) {
            }
		System.out.println(n);
		
	}


	private static String seleccionar(int n) {
		String nombre = pathWorkspace + "Bash/";
		if (n == CREAR_BUILD){
			nombre += "crearBuild.bat";
		}else if(n == CREAR_ANT){
			nombre += "anto.bat";
		}else if(n == CREAR_PUSH_RUN) {
			nombre += "pushRun.bat";
		}else{
			
			System.out.println("Tipo de archivo no reconocido");
		} 
		
		return nombre;
	}
	
	public String getPathWorkspace() {
		return pathWorkspace;
	}

	public void setPathWorkspace(String pathWorkspace) {
		this.pathWorkspace = pathWorkspace;
	}	
	
}
