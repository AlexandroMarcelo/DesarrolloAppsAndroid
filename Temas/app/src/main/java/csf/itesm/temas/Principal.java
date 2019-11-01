package csf.itesm.temas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Button btnActividad1 = (Button) findViewById(R.id.button1);

        btnActividad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad1 = new Intent(Principal.this, Actividad1_URL.class);
                startActivity(actividad1);
            }
        });
    }
}
