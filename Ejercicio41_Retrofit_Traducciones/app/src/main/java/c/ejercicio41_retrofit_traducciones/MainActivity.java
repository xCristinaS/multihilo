package c.ejercicio41_retrofit_traducciones;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

        Call<Respuesta> llamada = MyRetrofit.getMyRetrofitInstance().getServicio().getTraduccion(text, String.format("%s-%s",idiomaFrom, idiomaTo));
        llamada.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta r = response.body();
                if (r != null && r.getText() != null && !r.getText().isEmpty())
                    lblTraduccion.setText(r.getText().get(0));
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {

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
