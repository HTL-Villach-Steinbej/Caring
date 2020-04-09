package com.example.caring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bll.Car;
import bll.Schaden;
import bll.SchadenUser;
import dal.Database;

public class DamageActivity extends AppCompatActivity {
    TextView txtMarke;
    TextView txtLaufleistung;
    TextView txtBezeichnung;
    Spinner idSpinnerSchaden;
    Button idSave;
    EditText txtSchadenbeschreibung;
    Car car;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            car = (Car) extras.getParcelable("car");
        }
        mAuth= FirebaseAuth.getInstance();

        txtMarke  = findViewById(R.id.txtMarke);
        txtLaufleistung = findViewById(R.id.txtLaufleistung);
        txtBezeichnung = findViewById(R.id.txtBezeichnung);
        idSpinnerSchaden=findViewById(R.id.idSpinnerSchaden);
        idSave= findViewById(R.id.idSave);
        txtSchadenbeschreibung = findViewById(R.id.txtSchadenbeschreibung);


        fillProvider();

        txtMarke.setText(car.getMarke());
        txtLaufleistung.setText(String.valueOf(car.getLaufleistung()));
        txtBezeichnung.setText(car.getBezeichnung());

        idSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = Database.newInstance();
                Date jetzt = new Date();
                java.sql.Date sqlDate = new java.sql.Date(jetzt.getTime());
                SchadenUser userschaden = new SchadenUser(1,mAuth.getCurrentUser().getUid(),car.getId(),txtSchadenbeschreibung.getText().toString(),idSpinnerSchaden.getSelectedItem().toString(),sqlDate);
                try {
                    db.insertSchaden(userschaden);
                }catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
                finish();
            }
        });
    }

    private void fillProvider(){
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Schaden am Motor");
        spinnerArray.add("Schaden an der Karosserie");
        spinnerArray.add("Reifenschaden");
        spinnerArray.add("Schaden in der Lenkung");
        spinnerArray.add("Schaden am Getriebe");
        spinnerArray.add("Schaden am Licht");
        spinnerArray.add("Schaden am Fenster");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idSpinnerSchaden.setAdapter(adapter);
    }
}
