package ejercicio17;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class Terrorista extends Thread{
	
	ArrayList<String> rehenes;
	Exchanger<ArrayList<String>> exchanger;
	
	public Terrorista(Exchanger<ArrayList<String>> exchanger, ArrayList<String> rehenes){
		this.rehenes = rehenes;
		this.exchanger = exchanger;
	}
	
	public void run(){
		while (true){
			for (int i = 0; i < 10; i++)
				rehenes.add("rehen"+i);
			
			System.out.println("10 rehenes CAPTURADOS");
			
			try {
				rehenes = exchanger.exchange(rehenes);
			} catch (InterruptedException e){}
		}
	}
}
