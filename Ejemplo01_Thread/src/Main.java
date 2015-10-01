/**
 * Created by xCristina_S on 21/09/2015.
 */
public class Main {
    public static void main(String args[]) {
/*
        for (int i=1;i<=10;i++){
            Calculadora calc = new Calculadora(i);
            Thread hilo = new Thread(calc, "Tabla del "+i);
            hilo.setPriority(i);
            hilo.start();
        }
*/

        for (int i = 1; i <= 10; i++)
            (new Calculadora2(i, "Tabla del " + i)).start();

    }
}
