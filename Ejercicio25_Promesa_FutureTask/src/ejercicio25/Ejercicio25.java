package ejercicio25;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Ejercicio25 {

	public static void main(String[] args) {
		ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		MyFutureTask tareaResult = new MyFutureTask(new TareaSumar("Tarea"));
		ejecutor.submit(tareaResult);
		// Si cacelas tareaResult estás cancelando la tarea. 
		//tareaResult.cancel(true);
		ejecutor.shutdown();
	}

}
