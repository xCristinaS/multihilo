package ejercicio15;

import java.util.concurrent.Phaser;

public class Ejercicio15 {

	public static void main(String[] args) {
		
		Phaser phaser = new Phaser(10);
		Thread[] estudiantes = new Thread[10];
		
		for (int i = 0; i < estudiantes.length; i++)
			(estudiantes[i] = new Estudiante("estudiante" + i, 5, phaser)).start();
		
		for (int i = 0; i < estudiantes.length; i++)
			try {
				estudiantes[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("Todos los estudiantes han acabado.");
		System.out.printf("PHASER ACABADO?: %s", phaser.isTerminated()?"si":"no");

	}

}
