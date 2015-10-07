package ejemplo04;

import java.util.concurrent.TimeUnit;

public class Ejemplo04 {
	public static void main(String[] args) {
		Ganador ganador = new Ganador();
		Thread[] corredores = new Thread[10];
		ThreadGroup grupo = new ThreadGroup("Corredores");
		
		for (int i = 0; i < 10; i++)
			corredores[i] = new Thread(grupo, new Corredor(ganador));
		
		for (int j = 0; j < corredores.length; j++)
			corredores[j].start();
		
		while(grupo.activeCount() > 9){
			try{
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e){}
		}
		System.out.printf("- El ganador es: %s\n", ganador.getNombreGanador());
	}
}
