package fixedThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Teatro {
	
	ThreadPoolExecutor ejecutor;
	
	public Teatro(){
		ejecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
	}
	
	public void acomodarCliente(Cliente c){
		ejecutor.execute(c);
		System.out.printf("\t\tTeatro -> Acomodadores contratados: %d\n", ejecutor.getPoolSize());
        System.out.printf("\t\tTeatro -> Acomodadores en movimiento: %d\n\n", ejecutor.getActiveCount());
	}
	
	public void cerrarTeatro(){
		System.out.println("EL TEATRO VA A CERRAR LAS PUERTAS");
		ejecutor.shutdown();
	}
}
