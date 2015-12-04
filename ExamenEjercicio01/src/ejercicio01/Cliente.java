package ejercicio01;

public class Cliente implements Runnable{
	
	BaldoCaja caja;
	
	public Cliente(BaldoCaja caja){
		this.caja = caja;
	}
	
	public void run(){
		try {
			caja.clienteEsperando();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
