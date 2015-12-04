package ejercicio02;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		
		ThreadPoolExecutor ejecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
		ArrayList<FutureTask<String>> listaResultados = new ArrayList<>(); 
		
		for (int i = 1; i <= 10; i++)
			listaResultados.add((FutureTask<String>) ejecutor.submit(new Tarea("Casa "+i)));
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 11; i <= 25; i++)
			listaResultados.add((FutureTask<String>) ejecutor.submit(new Tarea("Casa "+i)));
		
		try {
			ejecutor.shutdown();
			ejecutor.awaitTermination(1, TimeUnit.DAYS);
			
			for (int i = 0; i < listaResultados.size(); i++)
				System.out.println(listaResultados.get(i).get());
		
		} catch (InterruptedException | ExecutionException e){}
	}

}
