package ejemplo02_isInterrumpted;

public class Main {
	public static void main(String[] args) {
		Hilo hilo = new Hilo();
		hilo.start();
		
		try{
			Thread.sleep(3000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		
		hilo.interrupt();
	}
}
