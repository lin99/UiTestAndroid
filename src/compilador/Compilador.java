package compilador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Compilador {

	
	
	//Cambiar path del archivo de escritura
	public void crearTest( String nameFile, String workspace ) throws Exception{
		
	
		
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
		//Agregando Variables Globales
		sb.append(" UiCollection objeto;");
		sb.append(" UiObject campo;");
		
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
			
			arg = line.substring(comando.length());
			
			
			sb.append( agregarComando( comando, arg.trim() ) );
			sb.append( agregarComando( Comandos.TIME, "1000" ) );
			
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
			
			res.append( ingresarTexto(res, arg) );
			
		} else if( comando.equals(Comandos.CLICK) ){
			args = arg.split(" ");
			try{
				int x = Integer.parseInt( args[0] );
				int y = Integer.parseInt( args[1] );
				res.append("\n getUiDevice().click("+x+", "+y+"); \n") ;
			} catch( Exception e) {
				//Hacer un procedimiento mas indicado
				
				e.printStackTrace();
			}
		} else if( comando.equals(Comandos.CLICK_COMPONENT) ){
		
			res.append( clickComponente( arg ) );
		} else if( comando.equals( Comandos.TIME ) ){
			int intervalo = Integer.parseInt( arg );
			res.append("getUiDevice().waitForWindowUpdate(null,"+intervalo+");");
		}
		return res;
	}

	

	
	/**
	 * Realiza el split de los componentes con <, >
	 * @param arg
	 * @return
	 */
	private String clickComponente( String arg ) {
		
		StringTokenizer  st = new StringTokenizer(  arg, "[>][<]");
		String[] values, atributos = new String[ st.countTokens() ];
		
		String text = "", content = "", clase = "", padre = "android.widget.LinearLayout";
		StringBuilder sb = new StringBuilder();
		
		
		for(int i = 0 ; st.hasMoreTokens() ; i++ ){
			atributos[i] = st.nextToken();
			
			values = atributos[i].split(":");
			if( i==0 && values.length > 1 ){
				text = values[1];
			} else if( i==1 && values.length > 1 ){
				clase = values[1];
			} else if (i == 2 && values.length > 1){
				content = values[1];
			}else if(i == 3 && values.length > 1){
				padre = values[1];
			}
		}
		

		
		if( !text.trim().equals("") || !content.trim().equals("") ){
			
			sb.append("	objeto = new UiCollection(new UiSelector().className(\""+padre+"\"));\n");
			if(!text.trim().equals("")){
				
				sb.append("	campo = objeto.getChildByText(new UiSelector().className(\""+clase+"\"), \""+ text+"\");\n");
			}else{
				sb.append("	campo = objeto.getChildByDescription( new UiSelector().className(\""+ clase+"\"), \""+content+"\");\n");

			}
			
			sb.append(" campo.click();\n");
		}
		
		
	
		return sb.toString();
	}

	private String ingresarTexto(StringBuilder res, String arg) {
		
		
		StringTokenizer st2, st = new StringTokenizer(  arg, "[\\[][\\]]");
		String texto, atrib;
		atrib = st.nextToken();
		texto = st.nextToken();
		String text = "", content = "", clase = "", padre = "android.widget.LinearLayout";
		StringBuilder sb = new StringBuilder();
		
		st2 = new StringTokenizer( atrib, "<>" );
		String[] values, atributos = new String[ st2.countTokens() ];
		for(int i = 0 ; st2.hasMoreTokens() ; i++ ){
			atributos[i] = st2.nextToken();
			values = atributos[i].split(":");
			if( i==0 && values.length > 1 ){
				text = values[1];
			} else if( i==1 && values.length > 1 ){
				clase = values[1];
			} else if ( i == 2 && values.length > 1){
				content = values[1];
			} else if(i == 3 && values.length > 1){
				padre = values[1];
			}
		}

		
		if( !text.trim().equals("") || !content.trim().equals("") ){
			
						
			
			if(clase.trim().equals("android.widget.EditText")){
				sb.append("	objeto = new UiCollection(new UiSelector().className(\""+ padre +"\"));\n");
				sb.append("	campo = objeto.getChildByText(new UiSelector().className(\""+clase+"\"), \""+ text+"\");\n");
				sb.append(" campo.setText(\""+texto+"\");\n");
			}

		}
		
		
	
		return sb.toString( );

		
		
	}
	
	
	
}
