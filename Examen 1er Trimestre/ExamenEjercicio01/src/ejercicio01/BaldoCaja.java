package ejercicio01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BaldoCaja{

	boolean[] cajas; 
	Semaphore semaforo;
	Random rnd = new Random();
	ReentrantLock cerrojo = new ReentrantLock();
	SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
	
	public BaldoCaja(Semaphore semaforo, boolean[] cajas){
		this.cajas = cajas;
		this.semaforo = semaforo;
	}
	
	public void clienteEsperando() throws InterruptedException{
		boolean cajaCogida = false;
		try {
			System.out.printf("%s Acaba de llegar (%s).\n", Thread.currentThread().getName(), formato.format(new Date()));
			semaforo.acquire();
			for (int i = 0; i < cajas.length && !cajaCogida; i++)
				if (cajas[i]){
					cerrojo.lock();
					cajas[i] = false;
					cajaCogida = true;
					cerrojo.unlock();
					atenderCliente(i);
				}
		} catch (InterruptedException e){
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
	
	public void atenderCliente(int i){
		try {
			System.out.printf("\t%s está siendo atendido en la caja %d a las %s.\n", Thread.currentThread().getName(), i, formato.format(new Date()));
			TimeUnit.SECONDS.sleep(rnd.nextInt(3)+1);
			liberarCaja(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void liberarCaja(int i){
		cerrojo.lock();
		cajas[i] = true;
		System.out.printf("\t\t%s ha sido atendido a las %s por el empleado de la caja %d.\n", Thread.currentThread().getName(), formato.format(new Date()), i);
		cerrojo.unlock();
	}
}
