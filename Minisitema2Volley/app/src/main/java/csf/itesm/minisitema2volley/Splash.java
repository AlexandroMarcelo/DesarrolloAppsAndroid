package csf.itesm.minisitema2volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private long tiempodeEspera = 5000; //milisegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        TimerTask tarea = new TimerTask() {

            public void run() {

                finish();
                Intent intentoPrincipal = new Intent().setClass(Splash.this, Login.class);
                startActivity(intentoPrincipal);
            }
        };

        Timer timer = new Timer();
        timer.schedule(tarea, tiempodeEspera);
    }
}