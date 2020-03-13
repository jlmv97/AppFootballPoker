package com.example.pelotica;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.aware.WifiAwareManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class MiPelota extends View implements SensorEventListener {

    Paint pincel = new Paint();
    Paint porteria = new Paint();
    int alto,ancho;
    int tamario=40;
    int borde=12;
    float ejeX=0,ejeY=0,ejeZ=0;
    String X,Y,Z;
    int puntaje1=0,puntaje2=0;
    Bitmap bitmap;


    public MiPelota(Context interfaz){//Constructor del view
        super(interfaz);
        SensorManager sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor snSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,snSensor,sensorManager.SENSOR_DELAY_GAME);
        Display pantalla = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        //Point point = new Point();
        //pantalla.getSize(point);
        ancho = pantalla.getWidth();
        alto = pantalla.getHeight();
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.level1);//dibuja el campo
        bitmap = Bitmap.createScaledBitmap(bitmap,ancho,alto+20,true);
        ejeX=ancho/2;//hace que la pelota apareca al inicio
        ejeY=alto/2;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ejeX-=(event.values[0])*4;//Esta controla la velocidad
        X=Float.toString(ejeX);
        if(ejeX<(tamario+borde)){//Evita que se salga por los lados
            ejeX=(tamario+borde);
        }
        else if(ejeX>(ancho-(tamario+borde))){
            ejeX= ancho-(tamario+borde);
        }
        ejeY+=(event.values[1])*4;//Esta controla la velocidad
        Y=Float.toString(ejeY);
        if(ejeY<(tamario+borde)){//Evita que se salga la pelota por arriba y abajo y cuenta los puntos

            if(ejeX > (ancho/2) - 200 && ejeX < (ancho/2) + 200) {
                ejeX = ancho/2;
                ejeY = alto/2;
                puntaje2++;
            }
            else {
                ejeY=(tamario+borde);
            }

        }
        else if(ejeY>(alto-tamario-180)){
            ejeY=alto-tamario-180;
            if(ejeX>(ancho/2)-200 && ejeX<(ancho/2)+200){
                ejeX=ancho/2;
                ejeY=alto/2;
                puntaje1++;
            }
            else{
                ejeY=(tamario+borde);
            }

        }
        ejeZ=event.values[2];
        Z=String.format("%.2f",ejeZ);
        invalidate();
        /*Log.println(Log.INFO,"llego","mmmm");
        Log.println(Log.INFO,"Eje X "+ejeX +" = "+(ancho/2)+"  "+"Eje Y "+ejeY +" = "+(alto-170),"mmmm");
        if((ejeX==(ancho/2)) && (ejeY==1500)){
            ejeY=ancho/2;
            ejeY=alto/2;
            Log.println(Log.INFO,"llego queria","mmmm");
        }*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas canvas) {//Metodo que realiza el dibujo de la pelota y los puntajes
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,pincel);
        pincel.setColor(Color.WHITE);
        canvas.drawCircle(ejeX,ejeY,ejeZ+tamario,pincel);
        pincel.setColor(Color.WHITE);
        pincel.setTextSize(50);
        canvas.drawText(puntaje1+" J1",34,200,pincel);
        canvas.drawText(puntaje2+" J2",34,alto-300,pincel);


    }
}