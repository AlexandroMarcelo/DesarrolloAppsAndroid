package csf.itesm.componentes;

import android.graphics.drawable.VectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickBtnGuardar(View view){
        DespliegaToast("Has dado click en el boton de guardar");
        //Toast.makeText(btnPulsame.getContext(), "Haz dado Click", Toast.LENGTH_LONG).show();//por si no quiero crear una funcion
    }
    public void clickBtnAbrir(View view){
        DespliegaToast("Has dado click en el boton de abrir");
    }
    public void clickBtnImage(View view){
        DespliegaToast("Has dado click en la imagen");
    }
    public void clickTextNombre(View view){
        EditText introducedText = (EditText)findViewById(R.id.txtNombre);
        String content = introducedText.getText().toString(); //gets you the contents of edit text
        if(!content.isEmpty()){
            DespliegaToast("Has introducido: " + content);
        }
    }
    public void clickCheckBtnAutoguardar(View view){
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkbtnAutoguardar);
        if(checkBox.isChecked())
        {
            DespliegaToast("Has dado click en Autoguardar");
        }
        else
        {
            DespliegaToast("Has deseleccionado Autoguardar");
        }
    }
    public void clickCheckBox2(View view){

        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox2);
        if(checkBox.isChecked())
        {
            DespliegaToast("Has dado click en Estrella");
        }
        else
        {
            DespliegaToast("Has deseleccionado la Estrella");
        }
    }

    public void clickBtnOff(View view){
        DespliegaToast("Has dado click en el boton OFF");
    }

    public void  clickCheckRadioButtons(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    DespliegaToast("Has dado click en la opcion 1 (RB)");
                break;
            case R.id.radioButton2:
                if (checked)
                    DespliegaToast("Has dado click en la opcion 2 (RB)");
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOpen = (Button) findViewById(R.id.btnGuardar);
        /*
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DespliegaToast("Has dado click en guardar");
            }
        });*/
    }
    private void DespliegaToast(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
