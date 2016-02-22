package c.ejercicio40_listarep_boundservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyAdaptador.OnItemClick, ServiceConnection {

    private RecyclerView lstCanciones;
    private MyAdaptador adaptador;
    private LinearLayoutManager mLayoutManager;
    private MusicService servicio;
    private boolean vinculado;
    private FloatingActionButton fab;
    private Button btnAnterior, btnSiguiente;
    private BroadcastReceiver receptor;
    private IntentFilter filtro;
    private LocalBroadcastManager gestorLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vinculado && servicio.estaReproduciendo()) {
                    servicio.pausarCancion();
                    fab.setImageResource(android.R.drawable.ic_media_play);
                } else if (vinculado && !servicio.estaReproduciendo()){
                    servicio.seguirReproduciendo();
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });
    }

    private void initViews(){
        lstCanciones = (RecyclerView) findViewById(R.id.lstCanciones);
        adaptador = new MyAdaptador(BDDCanciones.canciones);
        adaptador.setOnItemClickListener(this);
        lstCanciones.setAdapter(adaptador);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lstCanciones.setLayoutManager(mLayoutManager);
        lstCanciones.setItemAnimator(new DefaultItemAnimator());

        btnAnterior = (Button) findViewById(R.id.btnAnterior);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio.reproducirAnterior();
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio.reproducirSiguiente();
            }
        });
        vincularServicio();

        receptor = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.hasExtra(MusicService.INDICE_CANCION))
                    marcarCancionEnReproduccion(intent.getIntExtra(MusicService.INDICE_CANCION, 0));
            }
        };

        filtro = new IntentFilter(MusicService.REPRODUCIENDO_ACTION);
        gestorLocal = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onResume() {
        gestorLocal.registerReceiver(receptor, filtro);
        super.onResume();
    }

    @Override
    protected void onPause() {
        gestorLocal.unregisterReceiver(receptor);
        super.onPause();
    }

    private void vincularServicio(){
        // Se crea el intent de vinculación con el servicio.
        Intent intent = new Intent(getApplicationContext(), MusicService.class);
        // Se inicia el servicio (para que se pare automáticamente al
        // desvincular).
        getApplicationContext().startService(intent);
        // Se vincula el servicio.
        getApplicationContext().bindService(intent, this, Context.BIND_AUTO_CREATE);
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
    public void onServiceConnected(ComponentName name, IBinder binder) {
        vinculado = true;
        // Se obtiene la referencia al servicio a partir del binder recibido.
        servicio = ((MusicService.MyBinder) binder).getService();
        // Se puede llamar a los métodos públicos de mServicio.
        servicio.setCanciones(BDDCanciones.canciones);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        vinculado = false;
        servicio = null;
    }

    @Override
    protected void onDestroy() {
        desvincularServicio();
        super.onDestroy();
    }

    private void desvincularServicio() {
        // Se desvincula del servicio.
        if (vinculado) {
            getApplicationContext().unbindService(this);
            vinculado = false;
            servicio = null;
        }
    }

    @Override
    public void onItemClick(int indiceCancion) {
        if (vinculado) {
            servicio.reproducirCancion(indiceCancion);
            adaptador.setSelectedElement(indiceCancion);
        }
    }

    private void marcarCancionEnReproduccion(int indiceCancion) {
        Toast.makeText(this, String.format("Se está reproduciendo \"%s\"", BDDCanciones.canciones.get(indiceCancion).getNombre()), Toast.LENGTH_SHORT).show();
    }
}
