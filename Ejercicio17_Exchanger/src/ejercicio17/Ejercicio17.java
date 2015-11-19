package ejercicio17;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class Ejercicio17 {

	public static void main(String[] args) {
		
		ArrayList<String> rehenesT = new ArrayList<String>();
		ArrayList<String>rehenesP = new ArrayList<String>();
		Exchanger<ArrayList<String>> exchanger = new Exchanger<ArrayList<String>>();
		
		new Terrorista(exchanger, rehenesT).start();
		new Poli(exchanger, rehenesP).start();

	}

}
