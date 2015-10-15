package ejemplo05;

public class Cuenta {
	
	float saldo = 50.0f;
	
	public Cuenta(){
		
	}
	public synchronized void incrementar(){
		saldo += 10;
		System.out.printf("Se ha realizado un abono. En cuenta: %.2f\n", saldo);
	}
	public synchronized void decrementar(){
		saldo -= 10;
		System.out.printf("Se ha realizado un cargo. En cuenta: %.2f\n", saldo);
	}
}
