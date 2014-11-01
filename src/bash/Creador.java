package bash;

public abstract class Creador {
	String pathAndroid;
	String pathProyecto;
	String nombreClase;
	String nombrePaquete;
	String numeroAPI;
	String nombreProyecto;
	static final int CREAR_BUILD = 1;
	static final int CREAR_ANT = 2;
	static final int CREAR_PUSH_RUN = 0;
	
	public abstract void batCrearBuild();
		
	
	
	public abstract void batAntBuild();
		
	
	
	protected abstract void batPushRun();
		
	
	
	public abstract void ejecutarInit();
	
	
		
}