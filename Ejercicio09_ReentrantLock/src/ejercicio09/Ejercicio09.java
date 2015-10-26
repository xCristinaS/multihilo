package ejercicio09;

public class Ejercicio09 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Impresora impresora = new Impresora();
		Thread[] hilos = new Thread[10];
		
        for (int i = 0; i < 10; i++){
            hilos[i] = new Thread(new Tarea(impresora), "hilo" + i);
        }
		
        for (int i = 0; i < 10; i++){
            hilos[i].start();
        }
	}

}
