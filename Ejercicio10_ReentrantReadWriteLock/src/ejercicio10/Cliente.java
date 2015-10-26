package ejercicio10;

public class Cliente implements Runnable{

	private Producto producto;
	
	public Cliente(Producto producto){
		this.producto = producto;
	}
	
	public void run() {
		producto.getPrecio();
	}

}
