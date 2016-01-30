package cristina.ejercicio34_retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Adaptador.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener{

    private static final int RC_NUEVO_ALUMNO = 1;
    private static final int RC_EDIT_ALUMNO = 2;

    Instituto.InstitutoInterface servicio;
    private int idAux;
    private static final String STATE_LISTA = "a";
    RecyclerView lstAlumnos;
    LinearLayoutManager mLayoutManager;
    Parcelable mEstadoLista;
    SwipeRefreshLayout swipeRefresh;

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
                AgregarActualizarActivity.start(MainActivity.this, null, ((Adaptador) lstAlumnos.getAdapter()).getIdLastAlumno() + 1, RC_NUEVO_ALUMNO);
            }
        });

        initViews();
        if (savedInstanceState == null)
            obtenerAlumnos();
    }

    private void initViews(){
        configRecyclerView();
        configSwipeRefresh();
    }

    private void configRecyclerView(){
        lstAlumnos = (RecyclerView) findViewById(R.id.lstAlumnos);
        Adaptador adaptador = new Adaptador(BDAlumnos.datos);
        adaptador.setOnItemClickListener(this);
        lstAlumnos.setAdapter(adaptador);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lstAlumnos.setLayoutManager(mLayoutManager);
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
    }

    private void configSwipeRefresh(){
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void onRefresh() {
        ((Adaptador)lstAlumnos.getAdapter()).removeAllItems();
        obtenerAlumnos();
    }

    private void obtenerAlumnos(){
        servicio = Instituto.getServicio();
        Call<List<Alumno>> llamada = servicio.listarAlumnos();
        llamada.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Response<List<Alumno>> response) {
                ((Adaptador) lstAlumnos.getAdapter()).addItems(response.body());
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("error");
            }
        });
    }

    @Override
    public void onItemClick(Alumno alumno) {
        AgregarActualizarActivity.start(MainActivity.this, alumno, 0, RC_EDIT_ALUMNO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Alumno a;
        if (resultCode == RESULT_OK) {
            if (requestCode == RC_NUEVO_ALUMNO && data.hasExtra(AgregarActualizarActivity.EXTRA_ALUMNO)) {
                a = data.getParcelableExtra(AgregarActualizarActivity.EXTRA_ALUMNO);
                Instituto.getServicio().agregarAlumno(a).enqueue(new Callback<Alumno>() {
                    @Override
                    public void onResponse(Response<Alumno> response) {

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
                ((Adaptador) lstAlumnos.getAdapter()).addItem(a);
            } else if (requestCode == RC_EDIT_ALUMNO && data.hasExtra(AgregarActualizarActivity.EXTRA_ALUMNO)){
                a = data.getParcelableExtra(AgregarActualizarActivity.EXTRA_ALUMNO);
                Instituto.getServicio().updateAlumno(a.getId(), a).enqueue(new Callback<Alumno>() {
                    @Override
                    public void onResponse(Response<Alumno> response) {

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Se salva el estado del RecyclerView.
        mEstadoLista = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(STATE_LISTA, mEstadoLista);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Se obtiene el estado anterior de la lista.
        mEstadoLista = savedInstanceState.getParcelable(STATE_LISTA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Se retaura el estado de la lista.
        if (mEstadoLista != null) {
            mLayoutManager.onRestoreInstanceState(mEstadoLista);
        }
    }

}
