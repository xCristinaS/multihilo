package ejercicio21;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalculadoraFac implements Callable<Integer>{
	// Variables miembro.
    private Integer numero;

    // Constructor.
    public CalculadoraFac(Integer numero) {
        this.numero = numero;
    }

    @Override
    public Integer call() throws Exception {
        // Calculo el factorial.
        int factorial = 1;
        if ((numero == 0) || (numero == 1)) {
            factorial = 1;
        } else {
            for (int i = 2; i <= numero; i++) {
                factorial *= i;
                // Para que tarde un poco más hacemos que duerma una milésimas.
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        return factorial;
    }
}
