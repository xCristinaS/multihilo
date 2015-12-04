package ejercicio01;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		
		boolean[] cajas = {true, true, true, true};
		Semaphore semaforo = new Semaphore(cajas.length, true);
		BaldoCaja caja = new BaldoCaja(semaforo, cajas);
		Random rnd = new Random();
		
		for (int i = 0; i < 50; i++){
			Thread hilo = new Thread(new Cliente(caja));
			try {
				TimeUnit.SECONDS.sleep(rnd.nextInt(2));
			} catch (InterruptedException e){}
			hilo.start();
		}
	}

}
