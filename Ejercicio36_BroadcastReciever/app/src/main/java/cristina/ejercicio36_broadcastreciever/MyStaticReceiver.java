package cristina.ejercicio36_broadcastreciever;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Cristina on 04/02/2016.
 */
public class MyStaticReceiver extends BroadcastReceiver{

    private static final int NOTIFICACION = 1;
    private static final int ABRIR_CHOOSER = 2;
    NotificationManager mGestor;

    @Override
    public void onReceive(Context context, Intent intent) {
        mGestor = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setSmallIcon(R.drawable.ic_not1); // Icono pequeño.
        b.setContentTitle("Broadcast leido"); // Título (1ª línea).
        b.setContentText("con el receiver"); // Texto (2º línea).
        b.setContentInfo("3"); // Info adicional (nº total tareas pendientes).
        b.setTicker("bien");  // Ticker.
        b.setAutoCancel(true); // para que se elimine al pinchar sobre la notificacion.
        Intent intento2 = new Intent(Intent.ACTION_VIEW);
        b.setContentIntent(PendingIntent.getActivity(context, ABRIR_CHOOSER, intento2, PendingIntent.FLAG_UPDATE_CURRENT));

        mGestor.notify(NOTIFICACION, b.build());
    }
}
