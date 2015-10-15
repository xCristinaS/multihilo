package ejemplo05;

public class Tarea implements Runnable{
	Cuenta c;
	boolean incremento;
	
	public Tarea (Cuenta c, boolean incremento){
		this.c = c;
		this.incremento = incremento;
	}
	
	public void run(){
		while (!Thread.currentThread().isInterrupted()){
			if (incremento)
				c.incrementar();
			else 
				c.decrementar();
		}
	}
}
