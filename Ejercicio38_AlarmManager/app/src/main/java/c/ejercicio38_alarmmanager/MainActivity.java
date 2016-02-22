package c.ejercicio38_alarmmanager;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private EditText txtIntervalo, txtMensaje;
    private SwitchCompat swActivar;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        initViews();
    }

    private void initViews(){
        txtIntervalo = (EditText) findViewById(R.id.txtIntervalo);
        txtMensaje = (EditText) findViewById(R.id.txtMensaje);
        swActivar = (SwitchCompat) findViewById(R.id.swActivar);
        swActivar.setOnCheckedChangeListener(this);
        swActivar.setChecked(AvisarReceiver.isAlarmaOn(getApplicationContext()));
        SharedPreferences preferencias = getApplicationContext().getSharedPreferences("alarmas", Context.MODE_PRIVATE);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // Se programa la alarma con los datos introducidos por el usuario.
            String mensaje = TextUtils.isEmpty(txtMensaje.getText().toString()) ? "cadena vacia" : txtMensaje.getText().toString();
            int intervalo;
            try {
                intervalo = Integer.parseInt(txtIntervalo.getText().toString());
            } catch (NumberFormatException e) {
                intervalo = AvisarReceiver.DEFAULT_INTERVAL;
            }
            AvisarReceiver.programarAlarma(getApplicationContext(), mensaje,
                    intervalo);
        } else
            AvisarReceiver.cancelarAlarma(getApplicationContext());
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
