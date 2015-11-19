package ejercicio16;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Estudiante extends Thread {

	Phaser phaser; 
	Random rnd;
	String nombre;
	int ejercicios;
	
	public Estudiante(String nombre, int ejercicios, Phaser phaser){
		this.phaser = phaser;
		this.nombre = nombre;
		this.ejercicios = ejercicios;
		rnd = new Random();
	}
	
	public void run(){
		sentarse();
		System.out.println(nombre + " se ha sentado.");
		phaser.arriveAndAwaitAdvance();
		
		for (int i = 1; i <= ejercicios; i++){
			System.out.println("\t" + nombre + " está comenzando el ejercicio " + i);
			hacerEjercicio();
			System.out.println("\t\t\t" + nombre + " ha acabado el ejercicio " + i);
			phaser.arriveAndAwaitAdvance();
		}
		System.out.println("\t\t\t\t" + nombre + " ha acabado TODOS los ejercicios.");
		phaser.arriveAndDeregister();
	}
	
	public void hacerEjercicio(){
		try {
			TimeUnit.SECONDS.sleep(rnd.nextInt(3));
		} catch (InterruptedException e){
			
		}
	}
	
	public void sentarse(){
		try {
			TimeUnit.SECONDS.sleep(rnd.nextInt(3));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
