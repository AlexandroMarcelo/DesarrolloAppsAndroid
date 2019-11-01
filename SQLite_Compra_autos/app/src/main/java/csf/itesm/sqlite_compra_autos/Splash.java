package csf.itesm.sqlite_compra_autos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private long tiempo_espera = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        final TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent intent = new Intent().setClass(Splash.this, MainActivity.class);
                startActivity(intent);
            }
        };
        Timer timer = new Timer();
        timer.schedule(tarea,tiempo_espera);
    }

}
