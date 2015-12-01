package ejercicio26_RejectedExecutorHandler;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Ejercicio26 {

	public static void main(String[] args) {
		
		//ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		CompletionService<String> servicio;
		TareasRechazadas gestor = new TareasRechazadas();
		ejecutor.setRejectedExecutionHandler(gestor);
		servicio = new ExecutorCompletionService<String>(ejecutor);
		Cartero cartero = new Cartero(servicio);
		cartero.start();
		for (int i = 1; i <= 5; i++)
			new Remitente("remitente "+i, servicio).start();
		
		try {
			TimeUnit.SECONDS.sleep(20);
			ejecutor.shutdown();
			ejecutor.awaitTermination(1, TimeUnit.DAYS);
			cartero.oficinaAbierta = false;
		} catch (InterruptedException e){e.printStackTrace();}
	}
}
