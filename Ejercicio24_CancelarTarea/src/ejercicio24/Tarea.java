package ejercicio24;

import java.util.concurrent.Callable;

public class Tarea implements Callable<String>{
	
	String nombre;
	public Tarea(String nombre){
		this.nombre = nombre;
	}
	public String call() throws Exception{
		while (true){
			System.out.println("Ejecutando la tarea...");
			Thread.sleep(100);
		}
	}
}
