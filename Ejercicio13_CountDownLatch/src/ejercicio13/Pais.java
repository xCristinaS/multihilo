package ejercicio13;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Pais implements Runnable{
	
	Videoconferencia videoCon;
	int participantes;
	CountDownLatch pestillo;
	String nombre;
	Random rnd = new Random();
	
	public Pais(int participantes, String nombre, Videoconferencia videoCon){
		this.videoCon = videoCon;
		this.nombre = nombre;
		this.participantes = participantes;
		pestillo = new CountDownLatch(participantes);
	}
	
	public void agregarParticipante(String nombreParticipante){
		System.out.println("El participante "+nombreParticipante+" ha sido agregado por el País "+nombre);
		pestillo.countDown();
	}
	public void run(){
		try {
			pestillo.await();
			TimeUnit.SECONDS.sleep(rnd.nextInt(3));
			System.out.println("\tEl pais " + nombre + " se ha unido a la videoconferencia");
			videoCon.unirse(nombre);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
