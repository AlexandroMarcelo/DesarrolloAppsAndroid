package csf.itesm.sqlite_compra_autos;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_user);

        final AdaptadorDB adaptador = new AdaptadorDB(this);

        //ListView list = (ListView)findViewById(R.id.list);

        Button btnAgregarAuto = (Button)findViewById(R.id.button);
        Button btnVerClientes = (Button)findViewById(R.id.button2);
        Button btnVerAutos = (Button)findViewById(R.id.button3);
        Button btnVerClienteYCompras = (Button)findViewById(R.id.button1);

        btnAgregarAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText marcaTxt = (EditText)findViewById(R.id.editText);
                EditText nombreTxt = (EditText)findViewById(R.id.editText1);
                EditText precioTxt = (EditText)findViewById(R.id.editText2);

                String marca = marcaTxt.getText().toString();
                String nombre = nombreTxt.getText().toString();
                String precio = precioTxt.getText().toString();

                insertarAuto(adaptador, marca, nombre, precio);
                marcaTxt.setText("");
                nombreTxt.setText("");
                precioTxt.setText("");
            }
        });

        btnVerClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verClientes(adaptador);
            }
        });

        btnVerAutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verAutos(adaptador);
            }
        });

        btnVerClienteYCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText idTxt = (EditText)findViewById(R.id.editText3);
                String id_cliente = idTxt.getText().toString();

                verClienteYCompras(adaptador, id_cliente);
            }
        });
    }

    public void insertarAuto(AdaptadorDB adaptador, String marca, String nombre, String precio){
        adaptador.open();
        long id = adaptador.insertaAutos(marca, nombre, precio);
        adaptador.close();
    }

    public void verClientes(AdaptadorDB adaptador){
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();

        ListView listClientes = (ListView) findViewById(R.id.list);
        List<String> clientes = new ArrayList<String>();
        clientes.add("ID - Nombre - Correo");
        if(c.moveToFirst()) {
            do {
                String id = c.getString(0);
                String name = c.getString(1);
                String mail = c.getString(2);

                clientes.add(id + " - " + name + " - " + mail);
            } while (c.moveToNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item, android.R.id.text1, clientes);
            listClientes.setAdapter(adapter);
            adaptador.close();
        }
    }
    public void verAutos(AdaptadorDB adaptador){
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosAutos();

        ListView listDb = (ListView) findViewById(R.id.list);
        List<String> values = new ArrayList<String>();
        values.add("ID - Marca - Automovil - Precio");
        if(c.moveToFirst()) {
            do {
                String id = c.getString(0);
                String marca = c.getString(1);
                String auto = c.getString(2);
                String precio = c.getString(3);

                values.add(id + " - " + marca + " - " + auto + " - $" + precio);
            } while (c.moveToNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item, android.R.id.text1, values);
            listDb.setAdapter(adapter);
            adaptador.close();
        }
    }

    public void verClienteYCompras(AdaptadorDB adaptador, String id_cliente){
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientesCompraAutos();

        ListView listCompras = (ListView) findViewById(R.id.listComprasCliente);
        List<String> values = new ArrayList<String>();
        values.add("ID - ID_Cliente - ID_Automovil");
        if(c.moveToFirst()) {
            do {
                String id_compra = c.getString(0);
                String cliente = c.getString(1);
                String auto = c.getString(2);

                values.add(id_compra + "  -  " + cliente + "  -  " + auto);
            } while (c.moveToNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item, android.R.id.text1, values);
            listCompras.setAdapter(adapter);
            adaptador.close();
        }
    }
}
