package csf.itesm.mx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadPrincipal extends AppCompatActivity {

    //Referencia al boton de mi layout
    //Button btnActividad1 = (Button) findViewById(R.id.button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_principal);

        //Referencia al boton de mi layout
        Button btnActividad1Video = (Button) findViewById(R.id.button);
        Button btnActividad2Imagen = (Button) findViewById(R.id.button2);
        Button btnActividad3Componentes = (Button)findViewById(R.id.button3);

        //metodo listener
        btnActividad1Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad1 = new Intent(ActividadPrincipal.this, Actividad1.class);
                startActivity(actividad1);
            }
        });

        btnActividad2Imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad2 = new Intent(ActividadPrincipal.this, Actividad2.class);
                startActivity(actividad2);
            }
        });

        btnActividad3Componentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad3 = new Intent(ActividadPrincipal.this, Actividad3.class);
                startActivity(actividad3);
            }
        });
    }
}
