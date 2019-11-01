package csf.itesm.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText textField1,textField2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textField1 = findViewById(R.id.editText1);
        textField2 = findViewById(R.id.editText2);
        Button btnContinuar = (Button)findViewById(R.id.button1);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad2 = new Intent(MainActivity.this, actividad_2.class);
                startActivity(actividad2);
            }
        });
    }

    public void guardar(View v) {
        String nombre = textField1.getText().toString();
        String contacto = textField2.getText().toString();
        SharedPreferences preferencias = getSharedPreferences("crm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(nombre, contacto);
        /*
        editor.putString("name", nombre);
        editor.putString("correo", contacto);
        */
        editor.commit();
        Toast.makeText(this,"Datos de contacto guardados", Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"Nombre: " + textField1.getText().toString(), Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"InformciÃ³n de contacto: " + textField2.getText().toString(), Toast.LENGTH_LONG).show();
    }

    public void recuperar(View v) {
        String nombre = textField1.getText().toString();
        SharedPreferences preferencias = getSharedPreferences("crm", Context.MODE_PRIVATE);
        String datos = preferencias.getString("correo", "");
        if (datos.length()==0) {
            Toast.makeText(this,"No existe este contacto en el CRM", Toast.LENGTH_LONG).show();
        }
        else {
            textField2.setText(datos);
        }
    }
}
