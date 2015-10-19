package ejercicio06;

public class Taquilla1 implements Runnable{

    // Propieades.
    private Cine cine;

    // Constructor.
    public Taquilla1(Cine cine) {
        this.cine = cine;
    }

    @Override
    public void run() {
        cine.vender(0, 3);
        cine.vender(0, 2);
        cine.vender(1, 2);
        cine.devolver(0, 3);
        cine.vender(0, 5);
        cine.vender(1, 2);
        cine.vender(1, 2);
        cine.vender(1, 2);
    }

}
