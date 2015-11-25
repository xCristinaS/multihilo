package scheduleWhitRate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import scheduleAtRate.Tarea;

public class Ejercicio23B {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor ejecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		Thread tarea = new Tarea("Tarea");
		// ScheduledFuture<?> resultado; --> si quisiera recoger el resultado del callable, como he pasado un thread y no me interesa el resultado, no lo uso. 
		ejecutor.scheduleWithFixedDelay(tarea, 1, 1, TimeUnit.SECONDS);
		
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
