package csf.itesm.autocompletar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class SplashVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_video);
        try {
            VideoView muestraVideo = new VideoView(this);
            setContentView(muestraVideo);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                    + R.raw.add);
            muestraVideo.setVideoURI(video);

            muestraVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
                    otraActividad();
                }
            });

            muestraVideo.start();

        } catch (Exception ex) {
            otraActividad();
        }
    }

    private void otraActividad() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, Menu.class));
        finish();
    }
}