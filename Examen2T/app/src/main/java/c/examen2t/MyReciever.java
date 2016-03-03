package c.examen2t;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Cristina on 03/03/2016.
 */
public class MyReciever extends BroadcastReceiver{
    public static final int DEFAULT_INTERVAL = 20000;
    private static final int NC_AVISAR = 1;
    private static final int RC_AVISO = 2;
    private static final int RC_ENTENDIDO = 1;
    private static final String EXTRA_MENSAJE = "extra_mensaje";
    private static final String PREF_ESTADO = "pref_estado";
    public static final String PREF_MENSAJE = "pref_mensaje";
    public static final String PREF_INTERVALO = "pref_intervalo";
    private static final String PREF_FILENAME = "alarmas";
    private static final String DEFAULT_MENSAJE = "Llevas mucho sin consultar el tiempo";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager mGestor = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setSmallIcon(android.R.drawable.ic_menu_info_details);
        b.setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(android.R.drawable.ic_menu_info_details)).getBitmap());
        b.setContentTitle("Aviso!");
        b.setContentText(intent.getStringExtra(EXTRA_MENSAJE));
        b.setDefaults(Notification.DEFAULT_ALL);
        b.setTicker("aviso importante, nitificación hecha");
        b.setAutoCancel(true);
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, RC_ENTENDIDO, i, 0);
        b.setContentIntent(pi);
        mGestor.notify(NC_AVISAR, b.build());
    }

    static void programarAlarma(Context context) {
        AlarmManager gestorAlarmas = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReciever.class);
        intent.putExtra(EXTRA_MENSAJE, DEFAULT_MENSAJE);
        PendingIntent pi = PendingIntent.getBroadcast(context, RC_AVISO, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        gestorAlarmas.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + DEFAULT_INTERVAL, DEFAULT_INTERVAL, pi);
        guardarEstadoAlarma(context, true, DEFAULT_MENSAJE, DEFAULT_INTERVAL);
    }

    static void cancelarAlarma(Context context) {
        AlarmManager gestorAlarmas = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReciever.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, RC_AVISO, intent, 0);
        gestorAlarmas.cancel(pi);
        guardarEstadoAlarma(context, false, "", 0);
    }

    private static void guardarEstadoAlarma(Context context, boolean on, String mensaje, int intervalo) {
        SharedPreferences preferencias = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean(PREF_ESTADO, on);
        if (on) {
            editor.putString(PREF_MENSAJE, mensaje);
            editor.putInt(PREF_INTERVALO, intervalo);
        }
        editor.apply();
    }

    static boolean isAlarmaOn(Context context) {
        SharedPreferences preferencias = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        return preferencias.getBoolean(PREF_ESTADO, false);
    }
}
