package ejercicio07;

public class Cocinero2 extends Thread{
	
	PilaPlatos platosLimpios, platosSecos;
	
	public Cocinero2(PilaPlatos platosSecos, PilaPlatos platosLimpios){
		super();
		this.platosSecos = platosSecos;
		this.platosLimpios = platosLimpios;
	}
	public void run(){
		while (true){
			try {
				platosSecos.meterPlato(platosLimpios.sacarPlato());
				Thread.sleep(500);
			} catch (InterruptedException e){
				
			}
		}
	}
}
