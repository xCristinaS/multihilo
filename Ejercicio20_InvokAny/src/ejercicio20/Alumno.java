package ejercicio20;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Alumno implements Callable<String>{
	String nombre; Random rnd = new Random();
	
	public Alumno(String nombre){
		this.nombre = nombre;
	}
	
	public String call() throws Exception{
		TimeUnit.SECONDS.sleep(rnd.nextInt(5));
		return nombre;
	}
}
