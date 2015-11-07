package ejercicio13;

import java.util.concurrent.CountDownLatch;

public class Videoconferencia implements Runnable{
	
	int paises;
	CountDownLatch pestillo;
	
	public Videoconferencia(int paises){
		this.paises = paises;
		pestillo = new CountDownLatch(paises);
	}
	public void unirse(String nombrePais){
		System.out.printf("\t\t Faltan %d paises.\n", pestillo.getCount() - 1);
		pestillo.countDown();
	}
	public void run(){
		try {
			pestillo.await();
			System.out.println("Todos los paises se han unido, comenzando...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
