package ejercicio06;

public class Taquilla2 implements Runnable {

    // Propiedades.
    private Cine cine;

    // Constructor.
    public Taquilla2(Cine cine) {
        this.cine = cine;
    }

    @Override
    public void run() {
        cine.vender(1, 2);
        cine.vender(1, 4);
        cine.vender(0, 2);
        cine.vender(0, 1);
        cine.devolver(1, 2);
        cine.vender(0, 3);
        cine.vender(1, 2);
        cine.vender(0, 2);
    }
}
