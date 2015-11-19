package ejercicio19;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Ejercicio19 {

	public static void main(String[] args) {
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> listaResultados = new ArrayList<>();
        Random aleatorio = new Random(); CalculadoraFac factorial; Future<Integer> futuroResultado;
        Integer[] numeros = new Integer[10]; int cont = 0;
        
        for (int i = 0; i < 10; i++) {
            numeros[i] = new Integer(aleatorio.nextInt(10));
            factorial = new CalculadoraFac(numeros[i]);
            futuroResultado = ejecutor.submit(factorial);
            listaResultados.add(futuroResultado);
        }
        ejecutor.shutdown();
        try {
			ejecutor.awaitTermination(500, TimeUnit.DAYS);
			for (int i = 0; i < listaResultados.size(); i++){
				cont+= listaResultados.get(i).get();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e){
			e.printStackTrace();
		}
        System.out.println("la suma de los factoriales es: " + cont);
	}

}
