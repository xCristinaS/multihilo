package ejercicio02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Tarea implements Callable<String>{

	Random rnd = new Random();
	String casa;
	SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
	
	public Tarea(String casa){
		this.casa = casa;
	}
	
	public String call(){
		try {
			TimeUnit.SECONDS.sleep(rnd.nextInt(3)+2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format("%s pintada a las %s por %s", casa, formato.format(new Date()), Thread.currentThread().getName());
	}
}
