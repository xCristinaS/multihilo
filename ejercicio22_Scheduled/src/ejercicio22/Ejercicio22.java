package ejercicio22;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Ejercicio22 {

	public static void main(String[] args) {
		// Creo un ejecutor para tareas planificadas con 1 hilo
        ScheduledThreadPoolExecutor ejecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        // Envío cinco tareas al ejecutor para que sean ejecutadas con un
        // cierto retardo respecto a cuando son enviadas:
        // Tarea 0: 1 segundo, Tarea 1: 2 segundos, Tarea 2: 3 segundos, etc.
        for (int i = 0; i < 5; i++) {
            // Creo la tarea
            Tarea tarea = new Tarea("Tarea " + i);
            // Informo del envío de la tarea.
            System.out.printf("%s -> Enviada en: %s\n", "Tarea " + i, new Date());
            // Envío la tarea planificando su retardo.
            ejecutor.schedule(tarea, i + 1, TimeUnit.SECONDS);
        }
        // Finalizo el ejecutor.
        ejecutor.shutdown();
        // Espero la finalización de las tareas una vez finalizado el ejecutor.
        try {
            ejecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Todas las tareas finalizadas en: %s\n", new Date());
    
	}

}
