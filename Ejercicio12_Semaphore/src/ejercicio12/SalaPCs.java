package ejercicio12;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SalaPCs {

	final int NUM_PCS = 8;
	Semaphore semaforo;
	Random rnd;
	boolean[] pcDisponibles;
	ReentrantLock cerrojo;
	//Condition noHayPcs;
	
	public SalaPCs(){
		rnd = new Random();
		cerrojo = new ReentrantLock();
		semaforo = new Semaphore(NUM_PCS);
		pcDisponibles = new boolean[NUM_PCS];
		//noHayPcs = cerrojo.newCondition();
		
		for (int i = 0; i < pcDisponibles.length; i++)
			pcDisponibles[i] = true;
	}
	
	public int cogerPc(){
		boolean ningunoLibre = true, parar = false;
		int r = -1; 
		try {
			semaforo.acquire();
			//cerrojo.lock();
			do {
				for (int i = 0; !parar && i < pcDisponibles.length; i++)
					if (pcDisponibles[i]){
						System.out.printf("%s está usando un PC\n",Thread.currentThread().getName());
						TimeUnit.SECONDS.sleep(rnd.nextInt(3));
						r = i;
						
						pcDisponibles[i] = false;
						ningunoLibre = false;
						parar = true;
						dejarPc(i);
					}
				/*if (ningunoLibre)
					noHayPcs.await();*/
			} while (ningunoLibre);
		} catch (InterruptedException e){
			
		} finally {
			//cerrojo.unlock();
			semaforo.release();
		}
		return r;
	}
	
	public void dejarPc(int pc){
		try {
			cerrojo.lock();
			pcDisponibles[pc] = true;
			System.out.printf("\t%s ha dejado libre el PC\n",Thread.currentThread().getName());
			TimeUnit.SECONDS.sleep(rnd.nextInt(3));
		} catch (InterruptedException e){
			
		} finally {
			cerrojo.unlock();
		}
	}
}
