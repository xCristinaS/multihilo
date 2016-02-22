package c.ejercicio40_listarep_boundservice;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;

/**
 * Created by Cristina on 15/02/2016.
 */
public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    public static final String REPRODUCIENDO_ACTION = "c.ejercicio40_listarep_boundservice/reproduciendo_action";
    public static final String INDICE_CANCION = "indiceCancion";
    private MyBinder binder;
    private MediaPlayer reproductor;
    private ArrayList<Cancion> canciones;
    private int cancionActual = -1;

    public class MyBinder extends Binder {

        public MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
        reproductor = new MediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        if (reproductor != null) {
            reproductor.stop();
            reproductor.release();
        }
        super.onDestroy();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // Se inicia la reproducción.
        reproductor.start();
        // Se envía un broadcast informativo.
        enviarBroadcast();
    }

    private void enviarBroadcast() {
        Intent intentRespuesta = new Intent(REPRODUCIENDO_ACTION);
        intentRespuesta.putExtra(INDICE_CANCION, cancionActual);
        LocalBroadcastManager gestor = LocalBroadcastManager.getInstance(this);
        gestor.sendBroadcast(intentRespuesta);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        siguienteCancion();
    }

    public void reproducirCancion(int indice) {
        if (reproductor != null) {
            cancionActual = indice;
            reproductor.reset();
            reproductor.setLooping(false);
            reproductor.setAudioStreamType(AudioManager.STREAM_MUSIC);
            reproductor.setOnPreparedListener(this);
            reproductor.setOnCompletionListener(this);
            try {
                reproductor.setDataSource(canciones.get(indice).getUrl());
                reproductor.prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pausarCancion(){
        reproductor.pause();
    }

    public void seguirReproduciendo(){
        reproductor.start();
    }

    public boolean estaReproduciendo(){
        return reproductor.isPlaying();
    }

    public void reproducirAnterior(){
        if (cancionActual-1 >= 0)
            reproducirCancion(cancionActual-1);
    }

    public void reproducirSiguiente(){
        if (cancionActual+1 < canciones.size())
            reproducirCancion(cancionActual+1);
    }

    private void siguienteCancion(){
        if (cancionActual+1 < canciones.size())
            reproducirCancion(cancionActual+1);
        else if (cancionActual+1 == canciones.size())
            reproducirCancion(0);
    }

    public void setCanciones(ArrayList<Cancion> canciones){
        this.canciones = canciones;
    }
}
