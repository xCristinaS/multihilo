package ejercicio10;

import java.util.Random;

public class Directivo implements Runnable {

	private Producto producto;
	private Random rnd = new Random();
	
	public Directivo(Producto producto){
		this.producto = producto;
	}
	public void run() {
		producto.setPrecio((float)(rnd.nextInt(1000)+1));
	}
}
