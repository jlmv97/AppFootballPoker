package com.example.pelotica;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    TextView puntaje;
    ImageView pelota,campo,porteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        puntaje = findViewById(R.id.txtPuntaje);
        pelota = findViewById(R.id.imgPelota);
        campo = findViewById(R.id.imgCampo);
        porteria = findViewById(R.id.imgPorteria);
    }

    public void Nomover(View view) {
        //metoddo de boton que tetiene el juego
    }
    
    public void Movimiento(View view) {
        //Meotodo del boton para iniciar el juego
    }




}
