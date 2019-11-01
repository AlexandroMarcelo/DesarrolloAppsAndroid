package csf.itesm.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private void DespliegaToast(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private static Context context;

    public static Context getAppContext() {
        return MainActivity.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AdaptadorDB adaptador = new AdaptadorDB(this);


        Button btnInsert = (Button)findViewById(R.id.button1);//insert
        Button btnSelectId = (Button)findViewById(R.id.button2);//select id
        Button btnSelectAll = (Button)findViewById(R.id.button3);//select all

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nombreInsertado = (EditText)findViewById(R.id.editText1);//nombre
                EditText correoInsertado = (EditText)findViewById(R.id.editText2);//correo
                String getNombre = nombreInsertado.getText().toString();
                String getCorreo = correoInsertado.getText().toString();
                //TextView txtView = (TextView)findViewById(R.id.textView1);
                //txtView.setText(content);

                insertarCliente(adaptador, getNombre, getCorreo);
                //DespliegaToast("Has  a: " + getNombre + ", con correo: " + getCorreo + ", en la base de datos.");
            }
        });

        btnSelectId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText idItroducido = (EditText)findViewById(R.id.editText3);//id
                String getId = idItroducido.getText().toString();
                long id = Long.parseLong(getId);
                seleccionaCliente(adaptador, id);
                //TextView txtView = (TextView)findViewById(R.id.textView1);
                //txtView.setText();
                //DespliegaToast("El id insertado es: " + getId);
            }
        });

        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Context ct = getAppContext();
                //AdaptadorDB adaptador = new AdaptadorDB(ct);
                selectAll(adaptador);
            }
        });



        // #################### INSERT ########################################

        //--- Angregamos clientes ---
        //adaptador.open();
        //long id = adaptador.insertaClientes("Raul Morales Salcedo", "raulms@itesm.mx");
        //id = adaptador.insertaClientes("Felipe CalderÃ³n Hinojosa", "calderon@presidencia.com");
        //adaptador.close();


        // ##################### SELECT #######################################
        /*
        //-- Obtenemos todos los clientes ---
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();
        if (c.moveToFirst())
        {
            do {
                DespliegaCliente(c);
            } while (c.moveToNext());
        }
        adaptador.close();
        */

        /*
        // #################### SELECT ########################################
        //--- Obtenemos un cliente ---
        adaptador.open();
        Cursor c = adaptador.obtieneUnCliente(2);
        if (c.moveToFirst())
            DespliegaCliente(c);
        else
            Toast.makeText(this, "No se ha encontrado al cliente", Toast.LENGTH_LONG).show();
        adaptador.close();

*/
        // ##################### UPDATE #######################################
        /*
        //--- Actualiza un cliente ---
        adaptador.open();
        if (adaptador.actualizaCliente(2, "AndrÃ©s Manuelovich", "elpeje@presidencia.com"))
            Toast.makeText(this, "ActualizaciÃ³n exitosa!.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "FallÃ³ la actualizaciÃ³n del registro.", Toast.LENGTH_LONG).show();
        adaptador.close();
        */

        // ###################### DELETE ######################################
        /*
        //--- Borra un cliente ---
        adaptador.open();
        if (adaptador.borraCliente(2))
            Toast.makeText(this, "Registro eliminado exitosamente.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "FallÃ³ al eliminar registro.", Toast.LENGTH_LONG).show();
        adaptador.close();


        try {
            String destPath = "/data/data/" + getPackageName() +
                    "/basesdedatos";
            File f = new File(destPath);
            if (!f.exists()) {
                f.mkdirs();
                f.createNewFile();

                //--- copiamos la db del directorio assets al directorio
                // final de ls db ---
                CopyDB(getBaseContext().getAssets().open("CRM"),
                        new FileOutputStream(destPath + "/CRM"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 */


        // ####################### SELECT *  #####################################
        //--- Obtenemos todos los clientes ---
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();
        if (c.moveToFirst()) {
            do {
                DespliegaCliente(c);
            } while (c.moveToNext());
        }
        adaptador.close();
    }

    public void CopyDB(InputStream inputStream,
                       OutputStream outputStream) throws IOException {
        //--- Copiamos 1K bytes poco a poco ---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    public void insertarCliente(AdaptadorDB adaptador, String nombre, String correo){
        adaptador.open();
        long id = adaptador.insertaClientes(nombre, correo);
        adaptador.close();
    }

    public  void seleccionaCliente(AdaptadorDB adaptador, long id){
        adaptador.open();
        Cursor c = adaptador.obtieneUnCliente(id);
        if (c.moveToFirst()) {
            DespliegaCliente(c);
            String getId = c.getString(0);
            String nombre = c.getString(1);
            String correo = c.getString(2);
            TextView txtView = (TextView)findViewById(R.id.textView1);
            String getSeleccion = "Id: " + getId + "\nNombre: " + nombre + "\nCorreo: " + correo;
            txtView.setText(getSeleccion);
        }
        else
            Toast.makeText(this, "No se ha encontrado al cliente", Toast.LENGTH_LONG).show();
        adaptador.close();
    }

    public void selectAll(AdaptadorDB adaptador){
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();
        if (c.moveToFirst()) {
            do {
                DespliegaCliente(c);
            } while (c.moveToNext());
        }

        ListView listDb = (ListView) findViewById(R.id.listDb);
        List<String> values = new ArrayList<String>();
        if(c.moveToFirst()) {
            do {
                String id = c.getString(0);
                String name = c.getString(1);
                String mail = c.getString(2);

                values.add(id + " - " + name + " - " + mail);
            } while (c.moveToNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item, android.R.id.text1, values);
            listDb.setAdapter(adapter);
            Toast.makeText(getBaseContext(), values+"", Toast.LENGTH_SHORT).show();
            adaptador.close();
        }
    }

    // ########################### TOAST #################################
    public void DespliegaCliente(Cursor c) {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Nombre: " + c.getString(1) + "\n" +
                        "Email:  " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }
}



