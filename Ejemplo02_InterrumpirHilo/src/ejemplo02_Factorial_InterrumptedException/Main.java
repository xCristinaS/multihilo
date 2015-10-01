package ejemplo02_Factorial_InterrumptedException;

public class Main {

	public static void main(String[] args) {
		Factorial hilo = new Factorial();
		hilo.start();
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e){}
		
		hilo.interrupt();
	}
}
