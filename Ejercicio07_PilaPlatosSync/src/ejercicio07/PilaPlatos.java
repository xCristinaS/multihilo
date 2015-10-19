package ejercicio07;

import java.util.LinkedList;

public class PilaPlatos {

	LinkedList<Integer> pila = new LinkedList<Integer>();
	String nombre;
	
	public PilaPlatos(String nombre){
		this.nombre = nombre;
	}
	public Integer sacarPlato() throws InterruptedException{
		int r;
		synchronized (this) {
			while(pila.size() <= 0)
				this.wait();
			
			r = pila.removeLast();
			this.notifyAll();
			System.out.printf("- Plato sacado de %s (%d)\n", nombre, pila.size());
			return r;
		}
	}
	public void meterPlato(Integer plato){
		synchronized (this) {
			pila.addLast(plato);
			System.out.printf("- Plato introducido en %s (%d)\n", nombre, pila.size());
			this.notifyAll();
		}
	}
}
