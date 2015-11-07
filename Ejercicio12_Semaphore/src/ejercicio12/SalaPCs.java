package ejercicio12;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SalaPCs {

	final int NUM_PCS = 8;
	Semaphore semaforo;
	Random rnd;
	boolean[] pcDisponibles;
	ReentrantLock cerrojo;
	
	public SalaPCs(){
		rnd = new Random();
		cerrojo = new ReentrantLock();
		semaforo = new Semaphore(NUM_PCS);
		pcDisponibles = new boolean[NUM_PCS];
		
		for (int i = 0; i < pcDisponibles.length; i++)
			pcDisponibles[i] = true;
	}
	
	public int usarPc(){
		int r = -1; 
		try {
			semaforo.acquire();
			r = cogerPC();
			TimeUnit.SECONDS.sleep(rnd.nextInt(3));
			dejarPc(r);
		} catch (InterruptedException e){
			
		} finally {
			semaforo.release();
		}
		return r;
	}
	
	public int cogerPC() {
		boolean parar = false; int r = -1;
		
		cerrojo.lock();
		for (int i = 0; !parar && i < pcDisponibles.length; i++)
			if (pcDisponibles[i]){
				System.out.printf("%s está usando el PC%d\n",Thread.currentThread().getName(), i);
				r = i;
				pcDisponibles[i] = false;
				parar = true;
			}
		cerrojo.unlock();
		return r;
	}
	public void dejarPc(int pc){
		cerrojo.lock();
		pcDisponibles[pc] = true;
		System.out.printf("\t%s ha dejado libre el PC%d\n",Thread.currentThread().getName(), pc);
		cerrojo.unlock();
	}
}
