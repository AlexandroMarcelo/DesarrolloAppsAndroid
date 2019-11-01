package csf.itesm.mx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;

public class Actividida1 extends AppCompatActivity {

    private float n1;
    private float n2;
    private float resultado;
    private String stringResultado;
    private TextView mostrarResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividida1);
        mostrarResultado = (TextView)findViewById(R.id.textView);
        Bundle bundle1 = getIntent().getExtras();
        String sn1 = bundle1.getString("numero1");
        String sn2 = bundle1.getString("numero2");
        n1 = Float.parseFloat(sn1);
        n2 = Float.parseFloat(sn2);


        ButtonRectangle btnResultadoSuma = (ButtonRectangle) findViewById(R.id.button1);
        ButtonRectangle btnResultadoResta = (ButtonRectangle) findViewById(R.id.button2);
        ButtonRectangle btnResultadoDivision = (ButtonRectangle) findViewById(R.id.button3);
        ButtonRectangle btnResultadoMultiplicacion = (ButtonRectangle) findViewById(R.id.button4);

        btnResultadoSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado = n1 + n2;
                stringResultado = Float.toString(resultado);
                mostrarResultado.setText(stringResultado);

            }
        });
        btnResultadoResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle bundle2 = getIntent().getExtras();
                resultado = n1 - n2;
                stringResultado = Float.toString(resultado);
                mostrarResultado.setText(stringResultado);
            }
        });
        btnResultadoDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle bundle3 = getIntent().getExtras();
                resultado = n1 / n2;
                stringResultado = Float.toString(resultado);
                mostrarResultado.setText(stringResultado);
            }
        });
        btnResultadoMultiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado = n1 * n2;
                stringResultado = Float.toString(resultado);
                mostrarResultado.setText(stringResultado);
            }
        });
    }
}
