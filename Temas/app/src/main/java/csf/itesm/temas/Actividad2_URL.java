package csf.itesm.temas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Actividad2_URL extends AppCompatActivity {

    private WebView webView1;
    Button btnActividad1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad2__url);

        btnActividad1 = (Button) findViewById(R.id.button1);
        webView1 = (WebView) findViewById(R.id.web_view1);

        btnActividad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                webView1.loadUrl("http://" + bundle.getString("url"));
            }
        });
    }
}
