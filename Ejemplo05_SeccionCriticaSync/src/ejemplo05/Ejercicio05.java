package ejemplo05;

public class Ejercicio05 {

	public static void main(String[] args) {
		
		Cuenta c = new Cuenta();
		Thread hilo1 = new Thread(new Tarea(c, true));
		Thread hilo2 = new Thread(new Tarea(c, false));
		hilo1.start();
		hilo2.start();
		
		try {
			Thread.sleep(500);
			hilo2.interrupt();
			Thread.sleep(200);
			hilo1.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

