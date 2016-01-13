package cristina.ejercicio30_progressbarmanejadorhilos;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final int onPreExecute = 0;
    private static final int onProgressUpdate = 1;
    private static final int onPostExecute = 2;

    Button btn;
    ProgressBar pbCircular, pbBarra;
    TextView lbl;

    Manejador manejador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        btn = (Button) findViewById(R.id.btn);
        pbCircular = (ProgressBar) findViewById(R.id.pbCircular);
        pbBarra = (ProgressBar) findViewById(R.id.pbBarra);
        lbl = (TextView) findViewById(R.id.lblMensaje);
        manejador = new Manejador(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
    }

    private void iniciar() {
        btn.setEnabled(false);
        // Se crea el manejador.
        manejador = new Manejador(this);
        // Se crea la tarea secundaria.
        Runnable tarea = new TareaSecundaria();
        // Se crea el hilo y se inicia.
        Thread hiloSecundario = new Thread(tarea);
        hiloSecundario.start();
    }

    // Hace visibles las vistas relacionadas con el progreso.
    private void mostrarBarras() {
        pbBarra.setVisibility(View.VISIBLE);
        lbl.setText("Trabajando");
        lbl.setVisibility(View.VISIBLE);
        pbCircular.setVisibility(View.VISIBLE);
    }

    // Actualiza el valor de las barras de progreso.
    private void actualizarBarras(int progreso) {
        lbl.setText(getString(R.string.trabajando, progreso, 10)); // MIRAR BIEN. PLURALS.
        pbBarra.setProgress(progreso);
    }

    // Muestra el total de tareas realizadas.
    private void mostrarRealizadas(int tareas) {
        lbl.setText(getResources().getQuantityString(R.plurals.realizadas, tareas, tareas)); // PARA LOS PLURALS. MIRAR BIEN.
    }

    // Resetea las vistas relacionadas con el progreso.
    private void resetearVistas() {
        pbBarra.setVisibility(View.INVISIBLE);
        pbBarra.setProgress(0);
        pbCircular.setVisibility(View.INVISIBLE);
        pbCircular.setProgress(0);
        btn.setEnabled(true);
    }

    // Clase interna para la Tarea Secundaria.
    private class TareaSecundaria implements Runnable {

        @Override
        public void run() {
            // Crea y envía el mensaje de inicio de ejecución al manejador.
            Message msgInicio = new Message();
            msgInicio.what = onPreExecute;
            manejador.sendMessage(msgInicio);
            // Se realizan diez pasos.
            for (int i = 0; i < 10; i++) {
                // Se pone a trabajar.
                trabajar();
                // Crea y envía un mensaje de actualización al manejador.
                Message msgProgreso = new Message();
                msgProgreso.what = onProgressUpdate;
                msgProgreso.arg1 = i + 1;
                manejador.sendMessage(msgProgreso);
            }
            // Crea y envía el mensaje de fin de ejecución al manejador.
            Message msgFin = new Message();
            msgFin.what = onPostExecute;
            msgFin.arg1 = 10;
            manejador.sendMessage(msgFin);
        }

        // Simula un trabajo de 1 segundo.
        private void trabajar() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // Clase interna para el Manejador.
    static class Manejador extends Handler {

        private final WeakReference<MainActivity> mActivityWeakReference; // Referencia débil a la actividad. Las actividades no se deben guardar, ni el contexto tampoco.

        Manejador(MainActivity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }
        // Debemos sobrescribir este método para recibir mensajes.
        @Override
        public void handleMessage(Message mensaje) {
            // Se obtiene la actividad (si aún existe).
            MainActivity activity = mActivityWeakReference.get();
            if (activity != null) {
                // Dependiendo del mensaje recibido.
                switch (mensaje.what) {
                    // Mensaje de inicio del hilo secundario.
                    case onPreExecute:
                        // Se hacen visibles las vistas para el progreso.
                        activity.mostrarBarras();
                        break;
                    // Mensaje de progreso del hilo secundario.
                    case onProgressUpdate:
                        // Se actualizan las barras.
                        int progreso = mensaje.arg1;
                        activity.actualizarBarras(progreso);
                        break;
                    // Mensaje de fin del hilo secundario.
                    case onPostExecute:
                        // Se informa al usuario y se resetean las vistas.
                        int tareas = mensaje.arg1;
                        activity.mostrarRealizadas(tareas);
                        activity.resetearVistas();
                        break;
                }
            }
        }
    }
}
