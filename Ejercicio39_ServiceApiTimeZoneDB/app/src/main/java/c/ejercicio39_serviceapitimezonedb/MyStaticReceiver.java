package c.ejercicio39_serviceapitimezonedb;

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
 * Created by Cristina on 09/02/2016.
 */
public class MyStaticReceiver extends BroadcastReceiver{

    public static final int DEFAULT_INTERVAL = 3000;
    private static final int NC_AVISAR = 1;
    private static final int RC_AVISO = 2;
    private static final int RC_ENTENDIDO = 1;
    private static final String EXTRA_MENSAJE = "Se ha actualizado la hora";
    private static final String PREF_ESTADO = "pref_estado";
    public static final String PREF_MENSAJE = "pref_mensaje";
    public static final String PREF_INTERVALO = "pref_intervalo";
    private static final String PREF_FILENAME = "alarmas";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Se obtiene el gestor de notificaciones del sistema.
        NotificationManager mGestor = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Se configura la notificación.
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setSmallIcon(android.R.drawable.ic_menu_info_details);
        b.setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(android.R.drawable.ic_menu_info_details)).getBitmap());
        b.setContentTitle("TimeZoneDB");
        b.setContentText(intent.getStringExtra(EXTRA_MENSAJE));
        b.setDefaults(Notification.DEFAULT_ALL);
        b.setTicker("aviso importante, hora actualizada");
        b.setAutoCancel(true);
        // Al pulsarse la notificación se mostrará la actividad principal.
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, RC_ENTENDIDO, i, 0);
        b.setContentIntent(pi);
        // Se construye y muestra la notificación.
        mGestor.notify(NC_AVISAR, b.build());
        abortBroadcast();
    }

    // Programa la alarma de aviso.
    static void programarAlarma(Context context, String mensaje, int intervalo) {
        AlarmManager gestorAlarmas = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyService.class);
        intent.putExtra(EXTRA_MENSAJE, mensaje);
        PendingIntent pi = PendingIntent.getService(context, RC_AVISO, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        gestorAlarmas.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + intervalo, intervalo, pi);
        guardarEstadoAlarma(context, true, mensaje, intervalo);
    }

    // Guarda en las preferencia esl estado de la alarma.
    private static void guardarEstadoAlarma(Context context, boolean on, String mensaje, int intervalo) {
        SharedPreferences preferencias = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean(PREF_ESTADO, on);
        // Si la alarma queda activada se guarda el mensaje y el intervalo.
        if (on) {
            editor.putString(PREF_MENSAJE, mensaje);
            editor.putInt(PREF_INTERVALO, intervalo);
        }
        editor.apply();
    }

    // Cancela la alarma.
    static void cancelarAlarma(Context context) {
        // Se obtiene el gestor de alarmas.
        AlarmManager gestorAlarmas = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // Se crea un pendingIntent equivalente al de la alarma programada.
        Intent intent = new Intent(context, MyService.class);
        PendingIntent pi = PendingIntent.getService(context, RC_AVISO, intent, 0);
        // Se cancela la alarma.
        gestorAlarmas.cancel(pi);
        // Se almacena el estado de la alarma.
        guardarEstadoAlarma(context, false, "", 0);
    }

    static boolean isAlarmaOn(Context context) {
        SharedPreferences preferencias = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        return preferencias.getBoolean(PREF_ESTADO, false);
    }
}
