package joinCargadorConector;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xCristina_S on 29/09/2015.
 */
public class Ejemplo03 {
    public static void main (String[] args){
        Thread cargador = new Thread(new Cargador());
        Thread conector = new Thread(new Conector());
        cargador.start();
        conector.start();
        try {
            cargador.join();
            conector.join();
        } catch (InterruptedException e){}
        System.out.printf("Conexión y carga establecidas con éxito a las %s.", new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}
