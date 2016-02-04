package cristina.ejercicio36_broadcastreciever;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Cristina on 04/02/2016.
 */
public class MyIntentService extends IntentService{

    public MyIntentService() {
        super("");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Se llama al onStartCommand del padre.
        super.onStartCommand(intent, flags, startId);
        // El servicio NO será reiniciado automáticamente.
        return START_NOT_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendOrderedBroadcast(new Intent("cristina.ejercicio36_broadcastreciever.action.MI_ACCION"), null);
    }
}
