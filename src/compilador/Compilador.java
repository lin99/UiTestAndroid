package compilador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import control.CreadorCarpetasWindows;

public class Compilador {
	
	CreadorCarpetasWindows creadorCarpetas;
	
	
	//Cambiar path del archivo de escritura
	public void crearTest( String nameFile, String workspace ) throws Exception{
		
		//PATH QUEMADO!!!
		
		creadorCarpetas = new CreadorCarpetasWindows(workspace+"src\\");
		creadorCarpetas.crearCarpetas();
		
		String path = creadorCarpetas.getPath();
		BufferedReader br = new BufferedReader( new FileReader( nameFile ) );
		PrintWriter pw = new PrintWriter(path + "TestUi.java");
		StringBuilder sb = new StringBuilder();
		//Agregando Imports
		sb.append("import com.android.uiautomator.core.*;\n" +
				  "import com.android.uiautomator.core.UiObjectNotFoundException;\n" +
				  "import com.android.uiautomator.testrunner.UiAutomatorTestCase;\n\n");
		
		//Agregando definicion de la clase
		sb.append("public class TestUi extends UiAutomatorTestCase {\n");
		
		//Agregando Main
		sb.append("public static void main(String[] args) {\n");
		sb.append(" TestUi test = new TestUi();\n");
		sb.append("	try {\n");
		sb.append("	test.testDemo();\n");
		sb.append("	} catch (UiObjectNotFoundException e) {\n");
		sb.append("		System.out.println(\"Object not found\");\n");
		sb.append("		e.printStackTrace();\n");
		sb.append(" } catch (Exception e) {\n");
		sb.append("		System.out.println(\"Other Exception\");\n");
		sb.append("		e.printStackTrace();\n");
		sb.append(" }");
		sb.append("}\n");
		
		//
		sb.append("\n\n");
		
		//Agregando Metodo testDemo()
		sb.append("public void testDemo() throws Exception {\n");
		//Contenido del test
		sb.append( contenido(br) );
		
		sb.append("}\n");
		
		
		//Agregando final de la clase;
		sb.append("}");
		
		pw.print(sb);
		pw.close();
	}

	private StringBuilder contenido(BufferedReader br) throws Exception {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String comando, args[];
		for( String line = br.readLine() ; line!=null && line.length()>0; line = br.readLine() ){
			System.out.println("->"+line);
			st = new StringTokenizer( line );
			args = new String[ st.countTokens()-1 ];
			comando = st.nextToken();
			
			sb.append( agregarComando( comando, args ) );
			
		}
		
		return sb;
	}

	private StringBuilder agregarComando(String comando, String[] args) {
		StringBuilder res = new StringBuilder();
		if( comando.equals(Comandos.ROTATE) ){
			if( args[0].equals(Comandos.IZQUIERDA) ){
				res.append("\ngetUiDevice().setOrientationLeft();\n");
			} else if( args[0].equals(Comandos.DERECHA) ){
				res.append("\ngetUiDevice().setOrientationRight();\n");
			}
		} else if( comando.equals(Comandos.HOME) ){
			res.append("\ngetUiDevice().pressHome();\n") ;
		} else if( comando.equals(Comandos.ATRAS) ){
			res.append("\ngetUiDevice().pressBack();\n") ;
		} else if( comando.equals(Comandos.APPS_RECIENTES) ){
			res.append("\ngetUiDevice().pressRecentApps();\n") ;
		} else if( comando.equals(Comandos.INGRESAR_TEXTO) ){
			res.append("\ngetUiDevice().pressHome();\n") ;
		}
		return res;
	}
	
	
	
}
