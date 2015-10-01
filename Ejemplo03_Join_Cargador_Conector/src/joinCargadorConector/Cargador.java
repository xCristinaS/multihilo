package joinCargadorConector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xCristina_S on 29/09/2015.
 */
public class Cargador implements Runnable {
    public void run(){
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        System.out.printf("%s -- Cargando datos...\n", formato.format(new Date()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e){};
        System.out.printf("%s -- Carga de datos finalizada.\n", formato.format(new Date()));
    }
}
