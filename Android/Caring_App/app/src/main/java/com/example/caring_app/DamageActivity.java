package com.example.caring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bll.Car;

public class DamageActivity extends AppCompatActivity {
    TextView txtMarke;
    TextView txtLaufleistung;
    TextView txtBezeichnung;
    Spinner idSpinnerSchaden;
    Button idSave;
    EditText txtSchadenbeschreibung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage);

        txtMarke  = findViewById(R.id.txtMarke);
        txtLaufleistung = findViewById(R.id.txtLaufleistung);
        txtBezeichnung = findViewById(R.id.txtBezeichnung);
        idSpinnerSchaden=findViewById(R.id.idSpinnerSchaden);
        idSave= findViewById(R.id.idSave);
        txtSchadenbeschreibung = findViewById(R.id.txtSchadenbeschreibung);

        Car car = new Car ();
        car.setLaufleistung(10000);
        car.setMarke("BMW");
        car.setBezeichnung("e92 335i");

        txtMarke.setText(car.getMarke());
        txtLaufleistung.setText(String.valueOf(car.getLaufleistung()));
        txtBezeichnung.setText(car.getBezeichnung());




    }

    private void fillProvider(){
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Schaden am Motor");
        spinnerArray.add("Schaden an der Karosserie");
        spinnerArray.add("Reifenschaden");
        spinnerArray.add("Schaden in der Lenkung");
        spinnerArray.add("Schaden am Getriebe");
        spinnerArray.add("Schaden am Licht");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idSpinnerSchaden.setAdapter(adapter);
    }


}
