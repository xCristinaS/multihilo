package ejercicio14;

import java.util.concurrent.CyclicBarrier;

public class Ejercicio14 {

	public static void main(String[] args) {
		
		final int NUM_CICLISTAS = 10;
		Thread[] ciclistas = new Thread[NUM_CICLISTAS];
		CyclicBarrier barrera = new CyclicBarrier(NUM_CICLISTAS, new Runnable() {
			public void run() {
				System.out.println("\t\t COMIENZA LA CARRERA.");
			}
		});
		
		for (int i = 0; i < NUM_CICLISTAS; i++)
			(ciclistas[i] = new Ciclista("Ciclista "+i, barrera)).start();
		
		for (int i = 0; i < ciclistas.length; i++)
			try {
				ciclistas[i].join();
			} catch (InterruptedException e){
				
			}
		
		System.out.println("TODOS HAN LLEGADO A LA META.");
	}

}
