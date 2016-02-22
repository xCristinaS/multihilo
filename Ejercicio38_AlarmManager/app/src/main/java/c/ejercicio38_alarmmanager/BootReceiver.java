package c.ejercicio38_alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Cristina on 08/02/2016.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (AvisarReceiver.isAlarmaOn(context))
            AvisarReceiver.reprogramarAlarma(context);
    }
}
