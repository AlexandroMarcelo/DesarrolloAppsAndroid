package csf.itesm.esqueleto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashImage extends AppCompatActivity {

    private long tiempo_espera = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_image);
        final TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent intent = new Intent().setClass(SplashImage.this, SplashVideo.class);
                startActivity(intent);
            }
        };
        Timer timer = new Timer();
        timer.schedule(tarea,tiempo_espera);
    }
}
