package ejemplo02_Segundero_Sleep;

public class Main {
	public static void main(String[] args) {
		Segundero segundero = new Segundero();
		segundero.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e){}
		
		segundero.interrupt();
	}
}
