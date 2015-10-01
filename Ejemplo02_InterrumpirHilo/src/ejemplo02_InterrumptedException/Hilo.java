package ejemplo02_InterrumptedException;

public class Hilo extends Thread{
	private long num = 1L;
	
	public void run(){
		try {
			mostrarPrimo();
		} catch(InterruptedException e){
			System.out.println("Se ha interrumpido el hilo.");
		}
	}
	
	private boolean esPrimo(long numero){
		if (numero <= 2)
			return true;
		
		for (long i = 2; i < numero/2; i++)
			if ((numero % i) == 0)
				return false;
		
		return true;
	}
	
	private void mostrarPrimo() throws InterruptedException{
		while(true){
			if (esPrimo(num))
				System.out.printf("- %d es un numero primo.\n", num);
			
			if (isInterrupted())
				throw new InterruptedException();
			num++;
		}
	}
}
