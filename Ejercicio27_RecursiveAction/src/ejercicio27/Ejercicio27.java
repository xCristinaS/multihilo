package ejercicio27;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Ejercicio27 {

	public static void main(String[] args) {
		Tarea tarea = new Tarea(0, 10);
		ForkJoinPool ejecutor = new ForkJoinPool();
        // Se envía la tarea al ejecutor (llamada asíncrona).
        ejecutor.execute(tarea);
        ejecutor.shutdown();
        try {
			ejecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
