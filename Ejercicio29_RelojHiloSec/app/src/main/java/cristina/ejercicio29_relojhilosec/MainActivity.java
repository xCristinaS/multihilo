package cristina.ejercicio29_relojhilosec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView lbl;
    Button btn;
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    Thread hiloSecundario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        lbl = (TextView) findViewById(R.id.lbl);
        btn = (Button) findViewById(R.id.btn);

        lbl.setText(formato.format(new Date()));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn.getText().equals(getString(R.string.iniciar)))
                    iniciar();
                else
                    parar();
            }
        });
    }

    private void iniciar() {
        hiloSecundario = new Thread(new Reloj(), "Secundario");
        hiloSecundario.start();
        btn.setText(R.string.parar);
    }

    private void parar() {
        hiloSecundario.interrupt();
        btn.setText(R.string.iniciar);
    }

    private class Reloj implements Runnable {
        final SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

        @Override
        public void run() {
            while (true) {
                final String cadena = formateador.format(new Date());
                lbl.post(new Runnable() { // Para actualizar la IU (Interfaz de Usuario) desde un hilo secundario.
                    @Override
                    public void run() {
                        lbl.setText(cadena);
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

    }
}
