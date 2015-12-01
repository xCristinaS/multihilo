package ejercicio26_RejectedExecutorHandler;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Cartero extends Thread{
	
	CompletionService<String> servicio;
	Future<String> resultado;
	boolean oficinaAbierta = true;
	
	public Cartero(CompletionService<String> servicio){
		this.servicio = servicio;
	}
	
	public void run(){
		while (oficinaAbierta)
			entregarCarta();
	}
	
	private void entregarCarta(){
		try {
			resultado = servicio.poll(2, TimeUnit.SECONDS);
			if (resultado != null)
				System.out.printf("\t\t- El cartero ha entregado la %s\n", resultado.get());
		} catch (InterruptedException | ExecutionException e){
			e.printStackTrace();
		}
	}
}
