package ejercicio08;

public class Ejercicio08 {

	public static void main(String[] args) {
		
		Palillo[] palillos = new Palillo[5];
		Comensal[] comensales = new Comensal[5];
		Thread[] hilos = new Thread[5];
		
		for (int i = 0; i < palillos.length; i++)
			palillos[i] = new Palillo(i);
		
		for (int i = 0; i < palillos.length; i++){
			comensales[i] = new Comensal(i, palillos[i], i == 0? palillos[palillos.length-1]:palillos[i-1]);
			(hilos[i] = new Thread(comensales[i])).start();
		}
	}

}
