package ejercicio08;

import java.util.Random;

public class Comensal implements Runnable{
	
	Palillo palilloIzq, palilloDer;
	int numCom; private static Random rnd = new Random();
	
	public Comensal(int numCom, Palillo izq, Palillo der){
		this.numCom = numCom;
		palilloDer = der;
		palilloIzq = izq;
	}
	public void run(){
		try {
			while (true){
				pensar();
				if (cogerPalillos())
					comer();
				soltarPalillos();
			}
		} catch (InterruptedException e){}
	}
	public void pensar() throws InterruptedException{
		System.out.printf("- El filosofo %d está pensando.\n", numCom);
		Thread.sleep(rnd.nextInt(1000));
	}

	public void comer() throws InterruptedException{
		System.out.printf("- El filosofo %d está comiendo.\n", numCom);
		Thread.sleep(rnd.nextInt(1000));
	}
	public boolean cogerPalillos() throws InterruptedException{
		boolean r = false;
		if (numCom % 2 == 0){
			if (palilloIzq.isDisponible()){
				palilloIzq.coger();
				if (palilloDer.isDisponible()){
					palilloDer.coger();
					r = true;
				}
			}
		} else {
			if (palilloDer.isDisponible()){
				palilloDer.coger();
				if (palilloIzq.isDisponible()){
					palilloIzq.coger();
					r = true;
				}
			}
		}
		return r;
	}
	public void soltarPalillos(){
		if (numCom % 2 == 0){
			palilloIzq.soltar();
			palilloDer.soltar();
		} else {
			palilloDer.soltar();
			palilloIzq.soltar();
		}
	}
}
