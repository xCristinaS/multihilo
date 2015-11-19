package cachedThreadPool;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable{
	
	String nombre; Random rnd = new Random();
	
	public Cliente(String nombre){
		this.nombre = nombre;
	}
	
	public void run(){
		try {
			System.out.printf("\n\t%s está siendo guiado a su asiento por %s.\n", nombre, Thread.currentThread().getName());
			TimeUnit.SECONDS.sleep(rnd.nextInt(3));
			System.out.printf("\t%s se ha acomodado.\n", nombre);
		} catch (InterruptedException e){}
	}
}
