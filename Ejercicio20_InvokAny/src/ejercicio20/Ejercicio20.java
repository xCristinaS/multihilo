package ejercicio20;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ejercicio20 {

	public static void main(String[] args) {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();

		for (int i = 0; i < 10; i ++)
			lista.add(new Alumno("Alumno" + i));
		
		try {
			 ExecutorService ejecutor = (ExecutorService) Executors.newCachedThreadPool();
			 System.out.printf("%s ha cogido el rotulador.", ejecutor.invokeAny(lista));
			 ejecutor.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
