package c.ejercicio39_serviceapitimezonedb;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cristina on 08/02/2016.
 */
public class MyService extends IntentService {

    private MyRetrofit.MyRetrofitInterface servicio;
    static final String ZONA = "zona";

    public MyService() {
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
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        servicio = MyRetrofit.getServicio();
        Call<MyTimeZone> llamada = servicio.getHora(intent.hasExtra(ZONA)?intent.getStringExtra(ZONA):"");
        llamada.enqueue(new Callback<MyTimeZone>() {
            @Override
            public void onResponse(Response<MyTimeZone> response) {
                SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
                formato.setTimeZone(TimeZone.getTimeZone("GMT"));
                String hora = formato.format(new Date(response.body().getTimestamp()*1000L));
                Intent intentRespuesta = new Intent("c.ejercicio39_serviceapitimezonedb.action.MI_ACCION");
                intentRespuesta.putExtra(MainActivity.EXTRA_HORA, hora);
                sendOrderedBroadcast(intentRespuesta, null);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
