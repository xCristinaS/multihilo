package ejercicio21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Ejercicio21 {

	public static void main(String[] args) {
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> listaResultados; List<CalculadoraFac> listaTareas = new ArrayList<CalculadoraFac>();
        Random aleatorio = new Random(); Integer[] numeros = new Integer[10]; int cont = 0;
        
        for (int i = 0; i < 10; i++) {
            numeros[i] = new Integer(aleatorio.nextInt(10));
            listaTareas.add(new CalculadoraFac(numeros[i]));
        }
        
        try {
        	listaResultados = ejecutor.invokeAll(listaTareas);
			
			for (int i = 0; i < listaResultados.size(); i++)
				cont+= listaResultados.get(i).get();
			
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e){
			e.printStackTrace();
		}
        System.out.println("la suma de los factoriales es: " + cont);
        ejecutor.shutdown();
	}

}
