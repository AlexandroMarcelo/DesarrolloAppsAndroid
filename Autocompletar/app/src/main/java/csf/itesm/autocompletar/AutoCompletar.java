package csf.itesm.autocompletar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompletar extends AppCompatActivity {

    String[] marcas = {
            "Mazda",
            "Toyota",
            "Honda",
            "Ford",
            "Acura",
            "Mini",
            "BMW",
            "Mercedes Benz",
            "Kia",
            "Chevrolet",
            "Baic",
            "Jeep",
            "Land Rover",
            "Jaguar",
            "GMC",
            "Audi"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_auto_completar);
        //  El objeto ArrayAdapter administra el arreglo de strings a mostrar por el AutoCompleteTextView.
        //  al crear el arrayAdapter, tenemos que pasar tres parÃ¡metros,
        //  el contexto, un layout que se usarÃ¡ para dibujar cada item simple_list_item_1,
        //  y como tercer parÃ¡metro la colecciÃ³n de datos.
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, marcas);

        // inicializamos el AutoCompleteTextView con el que tenemos en el xml y llamamos a 2 mÃ©todos:
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.txtMarcas);

        // setThreshold(3): Este mÃtodo recoge un int como argumento, el cual especifica la cantidad de letras
        // que tenemos que escribir para que empiecen a mostrarse sugerencias. Al poner 3, comprobaremos
        // que hasta que no escribamos la 3Âª letra, no empezarÃ¡n a salir sugerencias.
        textView.setThreshold(3);
        // setAdapter(adapter): Le pasamos el adapter que instanciamos con anterioridad, por tanto nuestro
        // AutocompleteTextView tendrÃ¡ ya cargados todos los elementos de nuestro array.
        textView.setAdapter(adaptador);
    }
}