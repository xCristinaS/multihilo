package cristina.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int NC_TAREA = 1;
    private static final int ABRIR_NAV = 2;
    NotificationManager mGestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestor = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configAndSendNotification();
            }
        });
    }

    public void configAndSendNotification() {
        NotificationCompat.Builder b = new NotificationCompat.Builder(this);
        b.setSmallIcon(R.drawable.ic_notification1); // Icono pequeño.
        b.setLargeIcon(((BitmapDrawable) getResources().getDrawable(R.drawable.ic_notification1)).getBitmap()); // Icono grande.
        b.setContentTitle("Nueva tarea pendiente"); // Título (1ª línea).
        b.setContentText("Pasar ITV al coche"); // Texto (2º línea).
        b.setContentInfo("3"); // Info adicional (nº total tareas pendientes).
        b.setTicker("Nueva tarea pendiente");  // Ticker.
        b.setAutoCancel(true); // para que se elimine al pinchar sobre la notificacion.
        // Para meterle la acción.
        b.setContentIntent(crearPendingIntent());

        mGestor.notify(NC_TAREA, b.build());
    }

    private PendingIntent crearPendingIntent(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        return PendingIntent.getActivity(this, ABRIR_NAV, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
