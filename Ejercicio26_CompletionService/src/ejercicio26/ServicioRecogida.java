package ejercicio26;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ServicioRecogida implements Callable<String>{
	
	String remitente;
	String carta;
	
	public ServicioRecogida(String remitente, String carta){
		this.carta = carta;
		this.remitente = remitente;
	}
	
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(1);
		return recogerCarta();
	}
	
	private String recogerCarta(){
		System.out.printf("\t- El servicio de recogida ha recogido la %s del remitente %s\n", carta, remitente);
		return String.format("%s del remitente %s", carta, remitente);
	}
}
