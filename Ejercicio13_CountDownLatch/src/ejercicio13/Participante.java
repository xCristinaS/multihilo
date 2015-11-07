package ejercicio13;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Participante extends Thread{
	
	Pais pais;
	String nombre;
	Random rnd = new Random();
	
	public Participante(Pais pais, String nombre){
		this.pais = pais;
		this.nombre = nombre;
	}
	public void run(){
		try {
			TimeUnit.SECONDS.sleep(rnd.nextInt(3));
			pais.agregarParticipante(nombre);
		} catch (InterruptedException e){
			
		}
	}
}
