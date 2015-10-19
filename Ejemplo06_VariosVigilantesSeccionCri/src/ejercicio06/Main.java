package ejercicio06;

public class Main {

	public static void main(String[] args) {
		 // Creo un nuevo cine con 2 salas.
        int salas[] = { 20, 20 };
        Cine cine = new Cine(salas);
        // Creo e inicio dos hilos correspondientes a las dos taquillas.
        Thread hiloTaquilla1 = new Thread(new Taquilla1(cine), "Taquilla1");
        Thread hiloTaquilla2 = new Thread(new Taquilla2(cine), "Taquilla2");
        hiloTaquilla1.start();
        hiloTaquilla2.start();
        // Espero a que las taquillas cierran.
        try {
            // Waits for the finalization of the threads
            hiloTaquilla1.join();
            hiloTaquilla2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Imprimo el número de butacas libres en cada sala
        int numSalas = salas.length;
        for (int i = 0; i < numSalas; i++) {
            System.out.printf("Sala %d: %d butacas libres\n", i + 1,
                    cine.getLibres(i));
        }
    
	}

}
