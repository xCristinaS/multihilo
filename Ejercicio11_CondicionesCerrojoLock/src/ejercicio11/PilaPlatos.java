package ejercicio11;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PilaPlatos {

	LinkedList<Integer> pila = new LinkedList<Integer>();
	ReentrantLock cerrojo = new ReentrantLock();
	Condition noHayPlatos = cerrojo.newCondition();
	String nombre;
	
	public PilaPlatos(String nombre){
		this.nombre = nombre;
	}
	public Integer sacarPlato() /*throws InterruptedException*/{
		int r = -1;
		/*synchronized (this) {
			while(pila.size() <= 0)
				this.wait();
			
			r = pila.removeLast();
			
			System.out.printf("- Plato sacado de %s (%d)\n", nombre, pila.size());
			return r;
		}*/
		cerrojo.lock();
		try {
			while (pila.size() <= 0) // Mientras no haya platos, bloqueo a los hilos en la cola de espera de esa condición.
				noHayPlatos.await();
			
			r = pila.removeLast();
			
			System.out.printf("- Plato sacado de %s (%d)\n", nombre, pila.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			cerrojo.unlock();
		}
		return r;
	}
	public void meterPlato(Integer plato){
		/*synchronized (this) {
			pila.addLast(plato);
			System.out.printf("- Plato introducido en %s (%d)\n", nombre, pila.size());
			this.notifyAll();
		}*/
		
		cerrojo.lock();
		pila.addLast(plato);
		System.out.printf("- Plato introducido en %s (%d)\n", nombre, pila.size());
		noHayPlatos.signalAll(); // Como he metido plato, envío una señal a los que estaban esperando en la condición de "noHayPlatos". 
		cerrojo.unlock();
	}
}
