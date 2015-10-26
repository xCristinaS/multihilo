package ejercicio10;

public class Ejercicio10 {

	public static void main(String[] args) {
		
		Producto producto = new Producto(100f);
		Thread[] hilos = new Thread[15];
		
		for (int i = 0; i < hilos.length; i++)
			if (i % 2 == 0)
				hilos[i] = new Thread(new Directivo(producto), "Directivo" + i);
			else 
				hilos[i] = new Thread(new Cliente(producto), "Cliente" + i);
		
		for (int i = 0; i < hilos.length; i++)
			hilos[i].start();
	}

}
