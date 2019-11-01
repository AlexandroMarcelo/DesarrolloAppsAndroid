package csf.itesm.testdraweractivity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;

public class ResumenVelocidad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resumen_velocidad);
        DecoView decoView = (DecoView) findViewById(R.id.dynamicArcView);

        SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, 50, 0)
                .build();

        int backIndex = decoView.addSeries(seriesItem);

    }
}
