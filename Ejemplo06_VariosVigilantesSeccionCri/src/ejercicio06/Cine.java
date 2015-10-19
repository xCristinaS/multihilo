package ejercicio06;

public class Cine {
    // Propiedades.
    private int[] butacasSalas;
    private Object[] mutexSalas;

    // Constructor.
    public Cine(int[] butacasSalas) {
        this.butacasSalas = butacasSalas;
        mutexSalas = new Object[butacasSalas.length];
        for (int i = 0; i < mutexSalas.length; i++) {
            mutexSalas[i] = new Object();
        }
    }

    // Vende un número de entradas para una sala.
    // Retorna si se ha realizado la venta.
    public boolean vender(int sala, int cuantas) {
        if (sala < 0 || sala >= butacasSalas.length) {
            return false;
        }
        synchronized (mutexSalas[sala]) {
            if (cuantas <= butacasSalas[sala]) {
                butacasSalas[sala] -= cuantas;
                System.out.printf("Sala %d: %d entradas vendidas\n", sala,
                        cuantas);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            } else {
                return false;
            }
        }
    }

    // Devolver un número de entradas para una sala.
    // Retorna si se ha realizado la devolución.
    public boolean devolver(int sala, int cuantas) {
        if (sala < 0 || sala >= butacasSalas.length) {
            return false;
        }
        synchronized (mutexSalas[sala]) {
            butacasSalas[sala] += cuantas;
            System.out
                    .printf("Sala %d: %d entradas devueltas\n", sala, cuantas);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
    }

    // Retorna el número de butacas libres en una sala
    public int getLibres(int sala) {
        return butacasSalas[sala];
    }
}
