package ejercicio17;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class Poli extends Thread{
	
	ArrayList<String> rehenes;
	Exchanger<ArrayList<String>> exchanger;
	
	public Poli(Exchanger<ArrayList<String>> exchanger, ArrayList<String> rehenes){
		this.rehenes = rehenes;
		this.exchanger = exchanger;
	}
	
	public void run(){
		while (true){
			try {
				rehenes = exchanger.exchange(rehenes);
			} catch (InterruptedException e){}
			
			while(rehenes.listIterator().hasNext())
				rehenes.remove(rehenes.listIterator().next());
			
			System.out.println("\t10 rehenes RESCATADOS");
		}
	}
}
