package ejercicio07;

public class Cocinero3 extends Thread{
	
	PilaPlatos platosSecos;
	
	public Cocinero3(PilaPlatos platosSecos){
		super();
		this.platosSecos = platosSecos;
	}
	public void run(){
		while (true){
			try {
				platosSecos.sacarPlato();
				Thread.sleep(1000);
			} catch (InterruptedException e){
				
			}
		}
	}
}
