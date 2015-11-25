package ejercicio24;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Ejercicio24 {

	public static void main(String[] args) {
		ThreadPoolExecutor ejecutor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		Future<String> resultado = ejecutor.submit(new Tarea("Tarea"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Cancelando la tarea...\n");
        resultado.cancel(true);
        System.out.printf("Tarea cancelada? %s\n", resultado.isCancelled());
        System.out.printf("Tarea terminada? %s\n", resultado.isDone());
        ejecutor.shutdown();
	}

}
