package bash;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreadorWindows creadorWindows = new CreadorWindows("D:\\adt-bundle-windows-x86_64-20140702\\sdk/",
				"D:\\Lin\\AndroidWorkspace\\MyAppsTest\\","LaunchSettings", "paquete", 
				"2", "MyAppsTest");
		creadorWindows.batCrearBuild();
		creadorWindows.batAntBuild();
		creadorWindows.batPushRun();
		creadorWindows.ejecutar(CreadorWindows.CREAR_BUILD);
		creadorWindows.ejecutar(CreadorWindows.CREAR_ANT);
		creadorWindows.ejecutar(CreadorWindows.CREAR_PUSH_RUN);
	}

}
