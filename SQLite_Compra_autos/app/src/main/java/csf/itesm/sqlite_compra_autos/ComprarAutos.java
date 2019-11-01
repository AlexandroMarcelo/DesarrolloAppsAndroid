package csf.itesm.sqlite_compra_autos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ComprarAutos extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comprar_autos);

        final AdaptadorDB adaptador = new AdaptadorDB(this);

        selectAll(adaptador);

        Button btnComprarAuto = (Button)findViewById(R.id.button);

        btnComprarAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String correo_cliente = bundle.getString("correo");

                EditText id_autoTxt = (EditText)findViewById(R.id.editText);
                String id = id_autoTxt.getText().toString();
                Integer id_auto = Integer.parseInt(id);
                Integer id_cliente = getIdCliente(adaptador, correo_cliente);
                String id_test = String.valueOf(id_cliente);

                comprarAuto(adaptador, id_cliente, id_auto);

                Toast toast = Toast.makeText(getApplicationContext(), "Has Comprado el auto con id" + id_autoTxt, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
    public void selectAll(AdaptadorDB adaptador){
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosAutos();

        ListView listDb = (ListView) findViewById(R.id.listAutos);
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
    public void comprarAuto(AdaptadorDB adaptador, Integer id_cliente, Integer id_auto){
        adaptador.open();
        long id = adaptador.insertaClientesCompraAutos(id_cliente, id_auto);
        adaptador.close();
    }

    private Integer getIdCliente(AdaptadorDB adaptador, String correo){
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();

        List<String> correos = new ArrayList<String>();
        List<String> id_clientes = new ArrayList<String>();
        if(c.moveToFirst()) {
            do {
                String id_cliente = c.getString(0);
                String correo_cliente = c.getString(2);

                correos.add(correo_cliente);
                id_clientes.add(id_cliente);
            } while (c.moveToNext());
        }
        int pos_id = correos.indexOf(correo);
        adaptador.close();
        if (pos_id == -1){
            return 0;
        }
        String id_str = id_clientes.get(pos_id);
        Integer id_return = Integer.parseInt(id_str);

        return id_return;
    }
}
