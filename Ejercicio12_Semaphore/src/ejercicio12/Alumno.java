package ejercicio12;

public class Alumno implements Runnable{

	SalaPCs sala;
	
	public Alumno(SalaPCs sala){
		this.sala = sala;
	}
	
	public void run(){
		sala.usarPc();
	}
}
