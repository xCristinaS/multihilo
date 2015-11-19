package fixedThreadPool;

public class Main {

	public static void main(String[] args) {
		
		Teatro teatro = new Teatro();
		for (int i = 0; i < 50; i++){
			Cliente cliente = new Cliente("cliente_"+i);
			teatro.acomodarCliente(cliente);
		}
		teatro.cerrarTeatro();
	}

}
