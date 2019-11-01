package csf.itesm.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class actividad_2 extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_2);
        textView = (TextView)findViewById(R.id.textView);
        recuperar();
        Button btnActividad3 = (Button)findViewById(R.id.button);
        btnActividad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activiad3 = new Intent(actividad_2.this, actividad_3.class);
                startActivity(activiad3);
            }
        });
    }
    public void recuperar() {
        SharedPreferences preferencias = getSharedPreferences("crm", Context.MODE_PRIVATE);
        String datos;
        String all_data = "";

        Map<String, ?> allEntries = preferencias.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            //Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            datos = "Nombre: "+entry.getKey() + " \nContacto: "+entry.getValue().toString()+"\n\n";
            all_data = all_data + datos;
        }
        textView.setText(all_data);
    }
}
