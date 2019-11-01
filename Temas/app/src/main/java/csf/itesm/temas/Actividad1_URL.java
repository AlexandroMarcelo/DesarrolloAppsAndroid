package csf.itesm.temas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Actividad1_URL extends AppCompatActivity {

    private EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad1__url);

        Button btnActividad1URL = (Button) findViewById(R.id.button1);
        texto = (EditText) findViewById(R.id.editText4);

        btnActividad1URL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(Actividad1_URL.this, Actividad2_URL.class);
                actividad.putExtra("url", texto.getText().toString());
                startActivity(actividad);
            }
        });
    }
}
