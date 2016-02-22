package c.ejercicio41_retrofit_traducciones;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String KEY = "trnsl.1.1.20160219T114153Z.08a56fcdd8347276.2054e9715a6d4f29e494f247eb14a767c65e0e3f";

    private Spinner spLenguajeFrom, spLenguajeTo;
    private TextView lblTraduccion;
    private EditText txtTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerTraduccion();
            }
        });

        initViews();
    }

    private void initViews() {
        spLenguajeTo = (Spinner) findViewById(R.id.spLenguajeTo);
        spLenguajeFrom = (Spinner) findViewById(R.id.spLenguajeFrom);
        lblTraduccion = (TextView) findViewById(R.id.lblTraduccion);
        txtTexto = (EditText) findViewById(R.id.txtTexto);
    }

    private void obtenerTraduccion() {
        String idiomaFrom = obtenerAbr((String)spLenguajeFrom.getSelectedItem()), idiomaTo = obtenerAbr((String)spLenguajeTo.getSelectedItem()), text = txtTexto.getText().toString();

        Call<String> llamada = MyRetrofit.getMyRetrofitInstance().getServicio().getTraduccion(KEY, text, String.format("%s-%s",idiomaFrom, idiomaTo));
        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                lblTraduccion.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println();
            }
        });

    }

    private String obtenerAbr(String texto){
        switch (texto){
            case "Español":
                return "es";
            case "Francés":
                return "fr";
            case "Inglés":
                return "en";
            default:
                return "";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
