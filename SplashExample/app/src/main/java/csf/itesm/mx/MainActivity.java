package csf.itesm.mx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private long tiempoDeEspera = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent intentoPrincipal = new Intent().setClass(MainActivity.this, ActividadPrincipal.class);
                startActivity(intentoPrincipal);
            }
        };
        Timer timer = new Timer();
        timer.schedule(tarea,tiempoDeEspera);
    }
}
