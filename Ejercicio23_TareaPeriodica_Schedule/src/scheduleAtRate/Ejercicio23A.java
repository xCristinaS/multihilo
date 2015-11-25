package scheduleAtRate;

import java.util.concurrent.Executors;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
// El tiempo que se indica en el segundo parametro (delay entre ejecuciones) para el scheduleAtFixedRate es el tiempo mínimo que debe esperar para iniciar la tarea. 
// si no se acaba de ejecutar la tarea anterior, esperará a que finalice. Con el scheduleWithFixedDelay es el tiempo que va a esperar para ejecutarse después de que
// acabara la tarea anterior. 
public class Ejercicio23A {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor ejecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		Thread tarea = new Tarea("Tarea");
		// ScheduledFuture<?> resultado; --> si quisiera recoger el resultado del callable, como he pasado un thread y no me interesa el resultado, no lo uso. 
		ejecutor.scheduleAtFixedRate(tarea, 1, 1, TimeUnit.SECONDS); // tarea + tiempo que tarda en iniciar + retardo entre ellas + unidad de tiempo. 
		
		for (int i = 0; i < 50; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		ejecutor.shutdown();
	}
}
