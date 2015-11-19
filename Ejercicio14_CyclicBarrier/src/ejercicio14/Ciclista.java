package ejercicio14;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Ciclista extends Thread{
	
	Random rnd;
	String nombre;
	CyclicBarrier barrera;
	
	public Ciclista (String nombre, CyclicBarrier barrera){
		this.nombre = nombre;
		this.barrera = barrera;
		rnd = new Random();
	}
	public void correr() throws InterruptedException{
		System.out.println(nombre + " está corriendo.");
		TimeUnit.SECONDS.sleep(rnd.nextInt(5));
	}
	public void run(){
		try {
			correr();
			System.out.println("\t" + nombre + " ha llegado al punto de salida.");
			barrera.await();
			correr();
			System.out.println("\t" + nombre + " ha llegado a la META.");
		} catch (InterruptedException e){
			
		} catch (BrokenBarrierException e){
			
		}
	}
}
