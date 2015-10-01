package joinCargadorConector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xCristina_S on 29/09/2015.
 */
public class Conector implements Runnable {
    public void run(){
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        System.out.printf("%s -- Conectando...\n", formato.format(new Date()));
        try{
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e){}
        System.out.printf("%s -- Conexión establecida.\n", formato.format(new Date()));
    }
}
