package csf.itesm.peticiones_volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class parseaJSON extends AppCompatActivity {

    // Definimos los componentes de nuestra app
    private Button boton_obtieneObjetoJSON;
    private Button boton_obtieneArregloJSON;

    private final String EXTRA_JSON_OBJECT_INDEX = "mx.itesm.csf.parseojson.parseajson";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_parsea_json);

        boton_obtieneObjetoJSON = (Button) findViewById(R.id.boton_objetojson);
        boton_obtieneArregloJSON = (Button) findViewById(R.id.boton_arreglojson);


        // Como hemos hecho en otros ejemplos, el setOnClickListener nos permite
        // identificar si ha sido pulsado un boton en particular para llevarnos
        // a una actividad definida
        boton_obtieneObjetoJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ParseaObjetoJSON.class);
                i.putExtra(EXTRA_JSON_OBJECT_INDEX, 0);
                startActivity(i);
            }
        });

        boton_obtieneArregloJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ParseaArregloJSON.class);
                startActivity(i);
            }
        });

    }
}
