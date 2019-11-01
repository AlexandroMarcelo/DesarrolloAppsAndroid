package com.example.ciclodevida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final String MENSAJE = "Ciclo de vida";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(MENSAJE,"Estas en el metodo onCreate"); //d = debug, imprimir en consola
    }
    public void onStart(){
        super.onStart();
        Log.d(MENSAJE, "Dentro del método onStart");
    }
    public void onResume(){
        super.onResume();
        Log.d(MENSAJE, "Dentro del metodo onResume");
    }
    public void onPause(){
        super.onPause();
        Log.d(MENSAJE, "Dentro del método onPause");
    }
    public void onStop(){
        super.onStop();
        Log.d(MENSAJE, "Dentro del método onStop");
    }
    public void onDestroy(){
        super.onDestroy();
        Log.d(MENSAJE, "Dentro del método onDestroy");
    }
}
