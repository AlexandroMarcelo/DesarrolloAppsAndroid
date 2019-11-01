package csf.itesm.mx;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class Actividad1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad1);

        VideoView videoView = (VideoView)findViewById(R.id.videoView);


        String path = "android.resource://" + getPackageName() + "/"
                + R.raw.video;
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();
    }
}
