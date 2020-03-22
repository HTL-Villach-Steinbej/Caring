package com.example.caring_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RentCar extends AppCompatActivity {

    Button btnrentCar;
    Button btnStopp;
    Button btnReport;
    TextView tvPay;
Chronometer chronometer;
long abgelaufeneZeit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rentcar_popup);
        btnrentCar=findViewById(R.id.rentCar);
        btnStopp=findViewById(R.id.stoppRent);
        chronometer=findViewById(R.id.chronometer);
        tvPay=findViewById(R.id.toPay);
        abgelaufeneZeit=0;
        btnrentCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronometer.setBase(SystemClock.elapsedRealtime() + abgelaufeneZeit);
                chronometer.start();
                btnrentCar.setEnabled(false);
                btnStopp.setEnabled(true);
            }
        });
        btnStopp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount= Math.round(100.0 * ((SystemClock.elapsedRealtime() -chronometer.getBase() )*0.00020))/100.0;
                tvPay.setText("You have to pay: "+amount+"€");
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();

                btnStopp.setEnabled(false);
                btnrentCar.setEnabled(true);
                abgelaufeneZeit = 0;
                Intent intent = new Intent(RentCar.this, CarLocation.class);
                startActivityForResult(intent,0);

            }
        });


    btnReport=findViewById(R.id.reportP);

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RentCar.this,DamageActivity.class);
                startActivity(i);

            }
        });
    }


   /* private void initComponents() {

        btnrentCar = findViewById(R.id.rentCar);
        btnrentCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(RentCar.this, "Car renting in process!",
                        Toast.LENGTH_LONG).show();
            }
        });



        btnreportSchaden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }*/


}
