package com.example.caring_app;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import bll.Car;

public class AddCar extends AppCompatActivity {
    Button addCar;
    Button addLocation;

    EditText edMarke;
    EditText edBezeichnung;
    EditText edLaufleistung;
    Location l;
    Car newCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);
        setTitle("Add Car");
        addLocation=findViewById(R.id.btnLocation);
        edMarke=findViewById(R.id.edMarke);
        edBezeichnung=findViewById(R.id.edBez);
        edLaufleistung= findViewById(R.id.edLauf);

        addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCar.this, CarLocation.class);
                startActivityForResult(intent,0);

            }
        });
        String a=edMarke.getText().toString();
        //WS neues Car in die DB speichern
//        int laufleistunf= Integer.parseInt(edLaufleistung.getText().toString());
         newCar=new Car(10,edMarke.getText().toString(),edBezeichnung.getText().toString(),10000,l);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent

                l= (Location)data.getSerializableExtra("carLocation");
                // Set text view with string

            }
        }
    }

}
