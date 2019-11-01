package csf.itesm.mx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gc.materialdesign.views.ButtonRectangle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int contador = 0;
    private EditText text1;
    private EditText text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ButtonRectangle btnGoActivity1 = (ButtonRectangle) findViewById(R.id.button);
        btnGoActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1 = (EditText)findViewById(R.id.editText);
                text2 = (EditText)findViewById(R.id.editText2) ;
                Intent activity1 = new Intent(MainActivity.this, Actividida1.class);
                activity1.putExtra("numero1", text1.getText().toString());
                activity1.putExtra("numero2", text2.getText().toString());;
                startActivity(activity1);
            }
        });
    }

}
