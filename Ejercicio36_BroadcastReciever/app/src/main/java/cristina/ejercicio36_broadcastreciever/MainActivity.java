package cristina.ejercicio36_broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    BroadcastReceiver myReceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceptor = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(MainActivity.this, "El receptor dinamico ha leido la accion", Toast.LENGTH_SHORT).show();
                abortBroadcast(); // si es con prioridad hay q abortar para que no se le pase a los demas receiver
            }
        };



        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity.this.sendBroadcast(new Intent("cristina.ejercicio36_broadcastreciever.action.MI_ACCION")); // Sin prioridad
                //MainActivity.this.sendOrderedBroadcast(new Intent("cristina.ejercicio36_broadcastreciever.action.MI_ACCION"), null); // con prioridad
                startService(new Intent(MainActivity.this, MyIntentService.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filtro = new IntentFilter("cristina.ejercicio36_broadcastreciever.action.MI_ACCION");
        filtro.setPriority(1); // si es con prioridad
        registerReceiver(myReceptor, filtro);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceptor);
    }
}
