package com.example.caring_app;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


import java.util.Date;

import bll.Car;
import bll.Fahrzeug;
import bll.Point;
import bll.Rent;
import dal.Database;

public class RentCar extends AppCompatActivity {

    Button btnrentCar;
    Button btnStopp;
    Button btnReport;
    Rent rentObj;
    Location l;
    Rent newRent;
    Date von;
    Date bis;
    TextView tvPay;
    private FirebaseAuth mAuth;
    Button btnNv;

    Double longitude;
    Double latitude;
    Location carlocation;
    Car car;
Chronometer chronometer;
long abgelaufeneZeit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rentcar_popup);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            car = (Car) extras.getParcelable("car");
        }
        btnrentCar=findViewById(R.id.rentCar);
        btnStopp=findViewById(R.id.stoppRent);
        btnNv=findViewById(R.id.btnNavigation);
        chronometer=findViewById(R.id.chronometer);
        tvPay=findViewById(R.id.toPay);
        abgelaufeneZeit=0;
        newRent = new Rent();
        this.mAuth = FirebaseAuth.getInstance();
        carlocation=new Location("");
        btnrentCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                von = new Date();
                bis = new Date();
                rentObj=new Rent(1,car.getId(),mAuth.getCurrentUser().getUid(),1,von,bis);

                Database db = Database.newInstance();

                try{
                    newRent = db.insertRent(rentObj);
                }catch(Exception ex)
                {
                    ex.getMessage();
                }

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
                i.putExtra("car", car);
                startActivity(i);

            }
        });
        btnNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RentCar.this, NavigationActivityCar.class);
                i.putExtra("long", car.getCarLocation().getLongitude());
                i.putExtra("lat", car.getCarLocation().getLatitude());
                startActivity(i);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                longitude=data.getDoubleExtra("locLongitude",0);
                latitude=data.getDoubleExtra("locLatitude",0);
                carlocation.setLatitude(latitude);
                carlocation.setLongitude(longitude);
                this.car.setCarLocation(carlocation);
                bis = new Date();
                newRent.setBis(bis);

                Database database = Database.newInstance();

                try {
                    database.updateRent(newRent);
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                Fahrzeug f = new Fahrzeug(car.getId(),car.getBezeichnung(),car.getMarke(),car.getLaufleistung(),new Point(carlocation.getLongitude(),carlocation.getLatitude()));
                Database db = Database.newInstance();
                try {
                    db.updateCar(f);
                }catch (Exception ex)
                {
                    ex.getMessage();
                }
            }
        }
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
