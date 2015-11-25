package ejercicio26;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.TimeUnit;

public class Remitente extends Thread {
	
	String nombre;
	CompletionService<String> servicio;
	Random rnd = new Random();
	int contador = 1;
	
	public Remitente(String nombre, CompletionService<String> servicio){
		this.nombre = nombre;
		this.servicio = servicio;
	}
	
	public void run(){
		try {
			while (true){
				TimeUnit.SECONDS.sleep(rnd.nextInt(10));
				enviarCarta();
			}
		} catch (InterruptedException e){}
	}
	
	private void enviarCarta(){
		System.out.printf("- %s ha solicitado la recogida de la carta %d\n", nombre, contador);
		servicio.submit(new ServicioRecogida(nombre, "carta "+contador));
		contador++;
	}
}
