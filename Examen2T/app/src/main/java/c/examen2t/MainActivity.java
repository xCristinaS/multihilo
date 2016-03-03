package c.examen2t;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import c.examen2t.pojos.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private static final String LENGUAJE = "sp";
    private static final String UNITS = "metric";
    private static final String CADENA_IMG_ICONO = "http://api.openweathermap.org/img/w/";

    private EditText txtBuscar;
    private TextView lblNombre, lblbDescripcion, lblHumedad, lblViento, lblTemperatura, lblLluvia, lblNubosidad, lblAmanecer, lblAtardecer;
    private ImageView imgIco;
    private SwitchCompat swAlarma;

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
                buscar();
            }
        });

        initViews();
    }

    private void initViews() {
        txtBuscar = (EditText) findViewById(R.id.txtBuscar);
        lblNombre = (TextView) findViewById(R.id.lblNombre);
        lblAmanecer = (TextView) findViewById(R.id.lblAmanecer);
        lblbDescripcion = (TextView) findViewById(R.id.lblDescripcion);
        lblHumedad = (TextView) findViewById(R.id.lblHumedad);
        lblLluvia = (TextView) findViewById(R.id.lblLluvia);
        lblNubosidad = (TextView) findViewById(R.id.lblNubosidad);
        lblTemperatura = (TextView) findViewById(R.id.lblTemp);
        lblViento = (TextView) findViewById(R.id.lblViento);
        imgIco = (ImageView) findViewById(R.id.imgIco);
        lblAtardecer = (TextView) findViewById(R.id.lblAtardecer);
        swAlarma = (SwitchCompat) findViewById(R.id.swAlarma);
        swAlarma.setOnCheckedChangeListener(this);
        if (MyReciever.isAlarmaOn(this))
            swAlarma.setChecked(true);
    }

    private void bindResult(Respuesta r) {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        lblNombre.setText(r.getName());
        lblbDescripcion.setText(r.getWeather().get(0).getDescription());
        Picasso.with(this).load(CADENA_IMG_ICONO + r.getWeather().get(0).getIcon()).into(imgIco);
        lblTemperatura.setText(String.format("%s\nmin %.2f ºC\nmax %.2f ºC\nmedia %.2f ºC", getString(R.string.temperatura), r.getMain().getTempMin(), r.getMain().getTempMax(), r.getMain().getTemp()));
        lblHumedad.setText(String.format("%s %.2f %s", getString(R.string.humedad),r.getMain().getHumidity(), "%"));
        lblViento.setText(String.format("%s\n%s %.2f %s\n%s %.2f º", getString(R.string.viento), getString(R.string.velocidad), r.getWind().getSpeed(), getString(R.string.mps), getString(R.string.direccion), r.getWind().getDeg()));
        lblNubosidad.setText(String.format("%s %.2f %s", getString(R.string.nubosidad), r.getClouds().getAll(), "%"));
        lblAmanecer.setText(String.format("%s %s", getString(R.string.amanecer), formato.format(new Date(r.getSys().getSunrise()))));
        lblAtardecer.setText(String.format("%s %s", getString(R.string.atardecer), formato.format(new Date(r.getSys().getSunset()))));
        lblLluvia.setText(String.format("%s %s", getString(R.string.lluvia), r.getRain() == null ? "-":String.valueOf(r.getRain().get3h())));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            MyReciever.programarAlarma(getApplicationContext());
        } else
            MyReciever.cancelarAlarma(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void buscar(){
        Call<Respuesta> llamada = MyRetrofit.getMyRetrofitInstance().getServicio().getTraduccion(txtBuscar.getText().toString(), UNITS, LENGUAJE);
        llamada.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta r = response.body();
                if (r != null)
                    bindResult(r);
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fallo en retrofit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
