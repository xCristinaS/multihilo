package ejemplo02_InterrumptedException;

public class Main {
	public static void main(String[] args) {
		Hilo hilo = new Hilo();
		hilo.start();
		
		try{
			Thread.sleep(2000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		
		hilo.interrupt();
	}
}
