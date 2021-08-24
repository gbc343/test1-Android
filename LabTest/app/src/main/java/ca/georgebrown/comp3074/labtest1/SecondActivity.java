package ca.georgebrown.comp3074.labtest1;


import android.graphics.Color;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;




public class SecondActivity extends AppCompatActivity {

    private TextView temp;
    private TextView lit;
    private Temperature temperature;
    private Light light;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        temperature = new Temperature(this);
        light = new Light(this);
        temp = (TextView) findViewById(R.id.temperature);
        lit = (TextView) findViewById(R.id.light);

        light.setListener(new Light.Listener() {
            @Override
            public void onChange(float rx) {
                lit.setText("Light (lux): "+String.valueOf(rx));
            }
        });

        temperature.setListener(new Temperature.Listener() {
            @Override
            public void onIncrease(float rx) {
                if(rx <0){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    temp.setText("Temperature °C: "+String.valueOf(rx));

                }
                else if(rx >= 0 && rx <20 ){
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                    temp.setText("Temperature °C: "+String.valueOf(rx));


                }
                else if( rx >=20 && rx <40){
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    temp.setText("Temperature °C: "+String.valueOf(rx));


                }
                else{
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    temp.setText("Temperature °C: "+String.valueOf(rx));


                }
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        temperature.register();
        light.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        temperature.unregister();
        light.unregister();
    }
}
