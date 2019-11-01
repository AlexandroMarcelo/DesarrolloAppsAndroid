package csf.itesm.sqlite_compra_autos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NuevoUsuario extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nuevo_usuario);

        Button btnRegistrarCliente = (Button)findViewById(R.id.button);

        final AdaptadorDB adaptador = new AdaptadorDB(this);

        btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nombreInsertado = (EditText)findViewById(R.id.editText);//nombre
                EditText correoInsertado = (EditText)findViewById(R.id.editText1);//correo
                EditText contraseniaInsertada = (EditText)findViewById(R.id.editText2);//correo
                String getContrasenia = contraseniaInsertada.getText().toString();
                String getNombre = nombreInsertado.getText().toString();
                String getCorreo = correoInsertado.getText().toString();

                insertarCliente(adaptador, getNombre, getCorreo, getContrasenia);
                NuevoUsuario.super.onBackPressed();
            }
        });
    }

    public void insertarCliente(AdaptadorDB adaptador, String nombre, String correo, String contrasenia){
        adaptador.open();
        long id = adaptador.insertaClientes(nombre, correo, contrasenia);
        adaptador.close();
    }

}
