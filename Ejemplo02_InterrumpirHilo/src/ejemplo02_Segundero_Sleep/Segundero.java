package ejemplo02_Segundero_Sleep;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Segundero extends Thread{
	SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
	boolean salir = false;
	
	public void run(){
		while(!salir){
			try {
				System.out.printf("- Hora: %s\n", formato.format(new Date()));
				Thread.sleep(1000);
			} catch (InterruptedException e){
				salir = true;
			}
		}
	}
}
