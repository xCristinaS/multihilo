package ejercicio09;

public class Tarea implements Runnable{

	private Impresora impresora;
	
	public Tarea(Impresora impresora){
		this.impresora = impresora;
	}
	
	public void run() {
		impresora.imprimir("He imprimido");
	}
	
	
}
