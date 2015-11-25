package scheduleAtRate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarea extends Thread{
	
	String nombre; SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
	public Tarea(String nombre){
		this.nombre = nombre;
	}
	
	public void run(){
		System.out.printf("- %s Iniciada y ejecutandose en %s\n", nombre, formato.format(new Date()));
	}
}
