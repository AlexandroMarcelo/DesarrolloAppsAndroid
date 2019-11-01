package csf.itesm.peticiones_volley;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ParseaArregloJSON extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_parsea_arreglo_json);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentoContenedor);

        if (fragment == null) {
            fragment = new ListaAutosFragment();

            fm.beginTransaction()
                    .add(R.id.fragmentoContenedor, fragment)
                    .commit();
        }
    }
}
