package ejercicio11;

public class Ejercicio11 {

	public static void main(String[] args) {
		
		PilaPlatos platosLimpios = new PilaPlatos("platos limpios");
		PilaPlatos platosSecos = new PilaPlatos("platos secos");
		Thread hilo1 = new Thread(new Cocinero1(platosLimpios));
		Thread hilo2 = new Thread(new Cocinero2(platosSecos, platosLimpios));
		Thread hilo3 = new Thread(new Cocinero3(platosSecos));
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
	}
}
