package texto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorTexto {

	public static final int PATH_ANDROID = 1;
	public static final int PATH_PROYECTO = 2;
	public static final int NOMBRE_CLASE = 3;
	public static final int NOMBRE_PAQUETE = 4;
	public static final int NUMERO_API = 5;
	public static final int NOMBRE_PROYECTO = 6;
	public String CADENA;
	public int TIPO;
	
	
	public ValidadorTexto(String cadena, int tipo){
		this.CADENA = cadena;
		this.TIPO = tipo;
	}
	
	public ValidadorTexto() {
		
	}

	public boolean esValido(){
		if(this.TIPO == PATH_ANDROID){
			return ValidadorTexto.valPathAndroid(CADENA);
		}else if(TIPO == PATH_PROYECTO){
			return ValidadorTexto.valPathProyecto(CADENA);
		}else if(TIPO == NOMBRE_CLASE || TIPO == NOMBRE_PAQUETE || TIPO == NOMBRE_PROYECTO){
			return ValidadorTexto.valNomClasePaqueteProyecto(CADENA);
		}else if(TIPO == NUMERO_API){
			return ValidadorTexto.valNumAPI(CADENA);
		}else{
			System.out.println("Numero no valido" + TIPO);
			
			return false;
		}
		
	}

	private static boolean valNumAPI(String cadena) {
		
		return Pattern.matches("[0-9]",cadena);
		
	}

	private static boolean valNomClasePaqueteProyecto(String cadena) {
		
		return Pattern.matches("[[a-z]*[A-Z]*]*", cadena);
	}

	private static boolean valPathProyecto(String cadena) {
		boolean ud = ValidadorTexto.ubicDisco(cadena);
		return ud;
	}

	private static boolean valPathAndroid(String cadena) {
		boolean ud = ValidadorTexto.ubicDisco(cadena);
		boolean sdk = ValidadorTexto.contienePatron("sdk", cadena);
		System.out.println(ud);
		System.out.println(sdk);
		return ud&&sdk;
	}
	
	private static boolean contienePatron(String patron, String input){
		Pattern p = Pattern.compile(patron);
	    Matcher m = p.matcher(input);
	    return m.find();
	    	
	}
	
	private static boolean ubicDisco(String input){
		Pattern p = Pattern.compile("^[A-Z]:");
		Matcher m = p.matcher(input);
		return m.find();
	}
	
	public static void main(String[] args){
		ValidadorTexto validador = new ValidadorTexto("",NOMBRE_CLASE);
		System.out.println(validador.esValido());
	}

}
