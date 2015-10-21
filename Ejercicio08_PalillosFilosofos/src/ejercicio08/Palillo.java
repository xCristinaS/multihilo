package ejercicio08;

public class Palillo {

	int numP = 0; private boolean disponible;
	
	public Palillo(int numP){
		disponible = true;
		this.numP = numP;
	}
	public void coger() throws InterruptedException{
		synchronized (this) {
			while (!disponible)
				wait();
			disponible = false;
		}
	}
	public void soltar(){
		synchronized (this) {
			disponible = true;
			notifyAll();
		}
	}
	public boolean isDisponible(){
		return disponible;
	}
}
