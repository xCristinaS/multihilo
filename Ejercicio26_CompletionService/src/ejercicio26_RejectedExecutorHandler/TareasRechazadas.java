package ejercicio26_RejectedExecutorHandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class TareasRechazadas implements RejectedExecutionHandler{
	
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // Muestro información sobre la tarea y el ejecutor.
        System.out.printf("LA CARTA DE NO SE HA PODIDO ENTREGAR, OFICINA CERRADA\n");
    }
}
