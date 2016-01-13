package cristina.ejercicio31_asynktask;

import android.os.AsyncTask;
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

    Button btn;
    ProgressBar pbCircular, pbBarra;
    TextView lbl;

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
    }

    private void iniciar() {
        btn.setEnabled(false);
        TareaSecundaria hiloSecundario = new TareaSecundaria();
        hiloSecundario.execute(10, 10);
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

    // Simula un trabajo de 1 segundo.
    private void trabajar() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Clase interna para la Tarea Secundaria.
    private class TareaSecundaria extends AsyncTask<Integer, Integer, Void> {

        private int numTareasRealizadas;

        @Override
        protected Void doInBackground(Integer... params) {
            numTareasRealizadas = params[0];
            for (int i = 0; i <= params[0]; i++) {
                trabajar();
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            actualizarBarras(values[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mostrarBarras();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            resetearVistas();
            mostrarRealizadas(numTareasRealizadas);
        }
    }
}
