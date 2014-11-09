package bash;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		CreadorWindows creadorWindows = new CreadorWindows("D:\\adt-bundle-windows-x86_64-20140702\\sdk/",
//				"D:\\Lin\\AndroidWorkspace\\MyAppsTest\\","LaunchSettings", "paquete", 
//				"2", "MyAppsTest");
//		String pathAndroid, String pathCarpeta, String nombreClase, String numeroAPI, String nombreProyecto
		CreadorWindows creadorWindows = new CreadorWindows("D:\\adt-bundle-windows-x86_64-20140702\\sdk/",
				"D:\\Lin\\AndroidWorkspace\\","LaunchSettings", 
				"2", "Tig");
		
		creadorWindows.batCrearBuild();
		creadorWindows.batAntBuild("D:");
		creadorWindows.batPushRun();
		creadorWindows.ejecutar(CreadorWindows.CREAR_BUILD);
		creadorWindows.ejecutar(CreadorWindows.CREAR_ANT);
		creadorWindows.ejecutar(CreadorWindows.CREAR_PUSH_RUN);
	}

}
