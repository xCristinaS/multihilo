package cristina.ejercicio32_httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String STATE_LISTA = "a";
    Button btn, btnPOST;
    RecyclerView lstAlumnos;
    LinearLayoutManager mLayoutManager;
    Parcelable mEstadoLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        configRecyclerView();
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnectionAvailable()) {
                    TareaSecundaria tarea = new TareaSecundaria();
                    tarea.execute("https://dl.dropboxusercontent.com/u/67422/Android/json/datos.json");
                }
            }
        });

        btnPOST = (Button) findViewById(R.id.btnEco);
        btnPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TareaSecundariaPost tarea2 = new TareaSecundariaPost();
                tarea2.execute("http://www.informaticasaladillo.es/echo.php");
            }
        });
    }

    private void configRecyclerView(){
        lstAlumnos = (RecyclerView) findViewById(R.id.lstAlumnos);
        lstAlumnos.setAdapter(new Adaptador(BDAlumnos.datos));
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lstAlumnos.setLayoutManager(mLayoutManager);
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
    }

    private String obtenerJSONInternet(String sURL){
        String contenido = "", linea;
        try {
            URL url = new URL(sURL);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setDoInput(true);
            conexion.connect();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                while ((linea = lector.readLine()) != null)
                    contenido += linea;

                lector.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido;
    }

    private String envioPost(String sURL){
        String resultado = "", linea, nombre = "Baldomero", fecha = "funciona POST";

        try {
            URL url = new URL(sURL);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setDoInput(true);
            conexion.setDoOutput(true);
            conexion.connect();

            PrintWriter escritor = new PrintWriter(conexion.getOutputStream());
            escritor.write("nombre=" + URLEncoder.encode(nombre, "UTF-8"));
            escritor.write("&fecha=" + URLEncoder.encode(fecha, "UTF-8"));
            escritor.flush();
            escritor.close();

            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                while ((linea = lector.readLine()) != null)
                    resultado += linea;

                lector.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    private void parsearJson(String json){
        Gson gson = new Gson();
        Type tipoAlumno = new TypeToken<List<Alumno>>(){}.getType();
        ((Adaptador)lstAlumnos.getAdapter()).addItems((List<Alumno>)gson.fromJson(json,tipoAlumno));
    }

    private boolean isConnectionAvailable() {
        // Se obtiene del gestor de conectividad la información de red.
        ConnectivityManager gestorConectividad = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo infoRed = gestorConectividad.getActiveNetworkInfo();
        // Se retorna si hay conexión.
        return (infoRed != null && infoRed.isConnected());
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

    private class TareaSecundaria extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return obtenerJSONInternet(params[0]);
        }

        @Override
        protected void onPostExecute(String contenido) {
            super.onPostExecute(contenido);
            parsearJson(contenido);
        }
    }

    private class TareaSecundariaPost extends  AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return envioPost(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
