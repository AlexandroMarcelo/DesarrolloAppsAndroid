package csf.itesm.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class actividad_3 extends AppCompatActivity {
    Button btnObtener;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_3);
        btnObtener = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperar();
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
