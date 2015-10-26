package ejercicio09;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Impresora {
	
	private final Lock cerrojo = new ReentrantLock(true); // Si le paso un true al constructor se activa el modo justo, si esta vacío el modo justo esta desactivado. 
	private final Random rnd = new Random();
	
	public void imprimir(String doc){
		// cerrojo.lock();
		if (!cerrojo.tryLock()) // Si está ocupada el resto de hilos pasa. 
			System.out.println("La impresora está ocupada.");
		else {
			System.out.printf("%s - %s\n",Thread.currentThread().getName(), doc);
			
			try {
				TimeUnit.SECONDS.sleep(rnd.nextInt(3));
			} catch (InterruptedException e){
				
			} finally {
				System.out.printf("\t%s ha finalizado.\n", Thread.currentThread().getName());
				cerrojo.unlock();
			}
		}
	}
}
