package ejercicio25;

import java.util.concurrent.Callable;

public class TareaSumar implements Callable<Integer>{
	String nombre;
	public TareaSumar(String nombre){
		this.nombre = nombre;
	}
	
	public Integer call() throws Exception{
		Integer r = 0;
		for (int i = 1; i <= 20; i++)
			r += i;
		return r;
	}
}
