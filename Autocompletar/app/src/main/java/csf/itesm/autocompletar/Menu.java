package csf.itesm.autocompletar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btnAuctocompletar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);
        btnAuctocompletar = (Button) findViewById(R.id.button);

        btnAuctocompletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(Menu.this, AutoCompletar.class);
                startActivity(intent);
            }
        });

    }
}
