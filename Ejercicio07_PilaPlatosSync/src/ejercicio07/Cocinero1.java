package ejercicio07;

public class Cocinero1 extends Thread{
	
	PilaPlatos platosLimpios; int num = 0;
	
	public Cocinero1(PilaPlatos platosLimpios){
		super();
		this.platosLimpios = platosLimpios;
	}
	public void run(){
		while (true){
			num++;
			try {
				platosLimpios.meterPlato(new Integer(num));
				Thread.sleep(300);
			} catch (InterruptedException e){
				
			}
		}
	}
}
