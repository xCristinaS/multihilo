package c.ejercicio39_serviceapitimezonedb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    static final String EXTRA_HORA = "hora result";
    TextView lblHora;
    BroadcastReceiver myReceptor;
    Spinner spZonas;
    SwitchCompat swAlarma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initViews() {
        lblHora = (TextView) findViewById(R.id.lblHora);
        spZonas = (Spinner) findViewById(R.id.spZonas);

        spZonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startService(new Intent(MainActivity.this, MyService.class).putExtra(MyService.ZONA, (String) spZonas.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        myReceptor = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                abortBroadcast();
                lblHora.setText(intent.hasExtra(EXTRA_HORA) ? intent.getStringExtra(EXTRA_HORA) : "");
                //MyStaticReceiver.cancelarAlarma(getApplicationContext());
            }
        };

        swAlarma = (SwitchCompat) findViewById(R.id.swAlarma);
        swAlarma.setOnCheckedChangeListener(this);
        swAlarma.setChecked(MyStaticReceiver.isAlarmaOn(getApplicationContext()));
        SharedPreferences preferencias = getApplicationContext().getSharedPreferences("alarmas", Context.MODE_PRIVATE);
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

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filtro = new IntentFilter("c.ejercicio39_serviceapitimezonedb.action.MI_ACCION");
        filtro.setPriority(2); // si es con prioridad
        registerReceiver(myReceptor, filtro);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceptor);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // Se programa la alarma con los datos introducidos por el usuario.
            String mensaje = TextUtils.isEmpty(lblHora.getText().toString()) ? "cadena vacia" : lblHora.getText().toString();
            int intervalo = MyStaticReceiver.DEFAULT_INTERVAL;
            MyStaticReceiver.programarAlarma(getApplicationContext(), mensaje, intervalo);
        } else
            MyStaticReceiver.cancelarAlarma(getApplicationContext());
    }
}
