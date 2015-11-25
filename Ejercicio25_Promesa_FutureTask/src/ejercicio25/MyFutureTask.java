package ejercicio25;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyFutureTask extends FutureTask<Integer>{
	
	String nombre;
	public MyFutureTask(Callable<Integer> tarea){
		super(tarea);
		this.nombre = ((TareaSumar)tarea).nombre;
	}
	
	protected void done(){
		try {
			if (isCancelled())
				System.out.println("Tarea cancelada");
			else 
				System.out.printf("Al acabar la tarea sumar el resultado es: %d, y el doble es %d\n", get(),get()*2);
		} catch (InterruptedException | ExecutionException e){}
	}
}
