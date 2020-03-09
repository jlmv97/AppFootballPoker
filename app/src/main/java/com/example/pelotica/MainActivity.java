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
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        puntaje = findViewById(R.id.txtPuntaje);
        pelota = findViewById(R.id.imgPelota);
        campo = findViewById(R.id.imgCampo);
        porteria = findViewById(R.id.imgPorteria);
    }

    public void Nomover(View view) {
        sensorManager.unregisterListener(sensorEventListener);
        Toast.makeText(this, "Termina el juego", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Movimiento(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL,SensorManager.SENSOR_DELAY_UI);
            Toast.makeText(this, "Inicia el juego", Toast.LENGTH_SHORT).show();
        }
    }

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            pelota.setY(pelota.getY()+(event.values[2]*100));
            pelota.setX(pelota.getX()+(event.values[0]*100));
            puntaje.setText("Movimiento  X: "+event.values[0]+" Y: "+event.values[1]+" Z: "+event.values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


}
