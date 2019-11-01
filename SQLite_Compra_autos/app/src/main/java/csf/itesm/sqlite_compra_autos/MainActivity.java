package csf.itesm.sqlite_compra_autos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AdaptadorDB adaptador = new AdaptadorDB(this);

        Button btnIngresar = (Button)findViewById(R.id.button);
        Button btnNuevoUsuario = (Button)findViewById(R.id.button2);

        autoCompletarTextView(adaptador);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView correoTxt = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
                EditText contraseniaTxt = (EditText)findViewById(R.id.editText);
                String contrasenia = contraseniaTxt.getText().toString();
                String correo = correoTxt.getText().toString();

                if (correo.equals("admin") && contrasenia.equals("admin")){
                    Intent intento_admin = new Intent().setClass(MainActivity.this, AdminUser.class);
                    startActivity(intento_admin);
                }
                else {
                    if (evaluarCliente(adaptador, correo, contrasenia)){
                        Intent intento_ingresar = new Intent().setClass(MainActivity.this, ComprarAutos.class);
                        intento_ingresar.putExtra("correo", correo);
                        startActivity(intento_ingresar);
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "No estas en la base de datos", Toast.LENGTH_LONG);
                        toast.show();
                    }

                }
            }
        });

        btnNuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento_nuevo_usuario = new Intent().setClass(MainActivity.this, NuevoUsuario.class);
                startActivity(intento_nuevo_usuario);
            }
        });
    }

    public void onStart(){
        super.onStart();
        final AdaptadorDB adaptador = new AdaptadorDB(this);
        autoCompletarTextView(adaptador);
    }
    public void autoCompletarTextView(AdaptadorDB adaptador){
        // inicializamos el AutoCompleteTextView con el que tenemos en el xml y llamamos a 2 mÃ©todos:
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView);

        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();

        List<String> correos = new ArrayList<String>();
        if(c.moveToFirst()) {
            do {
                String correo = c.getString(2);

                correos.add(correo);
            } while (c.moveToNext());

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item, android.R.id.text1, values);
            //listDb.setAdapter(adapter);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, correos);

            textView.setThreshold(2);
            textView.setAdapter(adapter);

            adaptador.close();
        }
    }
    public boolean evaluarCliente(AdaptadorDB adaptador, String correo, String contrasenia){
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();

        List<String> correos = new ArrayList<String>();
        List<String> contrasenias = new ArrayList<String>();

        if(c.moveToFirst()) {
            do {
                String correo_cliente = c.getString(2);
                String contrasenia_cliente = c.getString(3);

                correos.add(correo_cliente);
                contrasenias.add(contrasenia_cliente);
            } while (c.moveToNext());
        }

        Integer pos_cliente = correos.indexOf(correo);
        adaptador.close();
        if (pos_cliente == -1){//si no esta el cliente
            return false;
        }
        String contraseniaTxt = contrasenias.get(pos_cliente);

        if (contrasenia.equals(contraseniaTxt)){//si esta el cliente y conicide con su contrasenia
            return true;
        }
        else {
            return false;
        }
    }

}
