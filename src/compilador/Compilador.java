package compilador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Compilador {
	
	//CreadorCarpetasWindows creadorCarpetas;
	
	
	//Cambiar path del archivo de escritura
	public void crearTest( String nameFile, String workspace ) throws Exception{
		
		//creadorCarpetas = new CreadorCarpetasWindows(workspace+"\\Tig\\src\\");
		//creadorCarpetas.crearCarpetas();
		
		String path = (workspace+"\\Tig\\src\\");
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
		String comando, arg;
		for( String line = br.readLine() ; line!=null && line.length()>0; line = br.readLine() ){		
			
			st = new StringTokenizer( line );
			comando = st.nextToken();
			System.out.println("->"+line + "  COMANDO: " + comando);
			arg = line.substring(comando.length());
			
			
			sb.append( agregarComando( comando, arg.trim() ) );
			
		}
		
		return sb;
	}

	private StringBuilder agregarComando(String comando, String arg) {
		StringBuilder res = new StringBuilder();
		String args[];
		if( comando.equals(Comandos.ROTATE) ){
			args = arg.split(" ");
			if( args[0].equals(Comandos.IZQUIERDA) ){
				res.append("\n getUiDevice().setOrientationLeft(); \n");
			} else if( args[0].equals(Comandos.DERECHA) ){
				res.append("\n getUiDevice().setOrientationRight(); \n");
			}
			
		} else if( comando.equals(Comandos.HOME) ){
			res.append("\n getUiDevice().pressHome();\n") ;
			
		} else if( comando.equals(Comandos.ATRAS) ){
			res.append("\n getUiDevice().pressBack();\n") ;
			
		} else if( comando.equals(Comandos.APPS_RECIENTES) ){
			res.append("\n getUiDevice().pressRecentApps(); \n") ;
			
		} else if( comando.equals(Comandos.INGRESAR_TEXTO) ){
			
			ingresarTexto(res, arg);
			
		} else if( comando.equals(Comandos.CLICK) ){
			args = arg.split(" ");
			try{
				int x = Integer.parseInt( args[0] );
				int y = Integer.parseInt( args[1] );
				res.append("\n getUiDevice().click("+x+", "+y+"); \n") ;
			} catch( Exception e) {
				//Hacer un procedimiento mas indicado
				System.out.println("Comando Click no Ingresado");
				e.printStackTrace();
			}
		} else if( comando.equals(Comandos.CLICK_COMPONENT) ){
			res.append( clickComponente( arg ) );
		}
		return res;
	}

	
//	 UiCollection objeto = new UiCollection(new UiSelector()
//	   .className("android.widget.LinearLayout"));
//	 String s = "TEXTO!!!!";
//	 
//	 UiObject campo = objeto.getChildByText(new UiSelector()
//	   .className("android.widget.EditText"), "Escribe un mensaje");
//	 
//
//	System.out.println("AQUI ->" + campo.setText(s));
//	System.out.println(" ->" + campo.getText()); 
//	 assertEquals(campo.getText(), s);
	
	private String clickComponente( String arg ) {
		
		return null;
	}

	private void ingresarTexto(StringBuilder res, String arg) {
		StringBuilder input = new StringBuilder( arg );
		
		
		//Not implement Yet
	}
	
	
	
}
