package ejemplo04;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Corredor implements Runnable{
	
	private Ganador ganador;
	
	public Corredor(Ganador ganador){
		this.ganador = ganador;
	}
	public void run() {
		Random rnd = new Random();
		try {
			TimeUnit.SECONDS.sleep(rnd.nextInt(10));
			ganador.setGanador(Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}
	public Ganador getGanador(){
		return ganador;
	}
}
