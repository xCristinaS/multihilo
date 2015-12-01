package ejercicio27;

import java.util.concurrent.RecursiveAction;

public class Tarea extends RecursiveAction{

	private static final long serialVersionUID = 1L;
	
	int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	int desde, hasta, mitad;
	
	public Tarea(int desde, int hasta){
		this.desde = desde;
		this.hasta = hasta;
	}
	
	public void compute(){
		if (hasta - desde == 1)
			imprimirTabla();
		else {
			mitad = (hasta + desde)/2;
			Tarea subtarea1 = new Tarea(desde, mitad);
			Tarea subtarea2 = new Tarea(mitad+1, hasta);
			invokeAll(subtarea1, subtarea2);
		}
	}
	
	
	public void imprimirTabla(){
		System.out.println("\n TABLA DEL "+ numeros[desde]);
		System.out.println(numeros[desde]*1);
		System.out.println(numeros[desde]*2);
		System.out.println(numeros[desde]*3);
		System.out.println(numeros[desde]*4);
		System.out.println(numeros[desde]*5);
		System.out.println(numeros[desde]*6);
		System.out.println(numeros[desde]*7);
		System.out.println(numeros[desde]*8);
		System.out.println(numeros[desde]*9);
		System.out.println(numeros[desde]*10);
	}
}
