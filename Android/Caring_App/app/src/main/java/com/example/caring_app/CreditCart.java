package com.example.caring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreditCart extends AppCompatActivity {

    EditText txtNummer;
    EditText txtPruefnummer;
    EditText Datum;
    Spinner CreditType;
    Button Save;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_cart);

        txtNummer = findViewById(R.id.idNummer);
        txtPruefnummer = findViewById(R.id.idPrufnummer);
        Datum=findViewById(R.id.idDatum);
        CreditType=findViewById(R.id.idProvider);
        Save=findViewById(R.id.btnSave);

        fillProvider();

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nummer = txtNummer.getText().toString();
                String prnr = txtPruefnummer.getText().toString();
                String datum = Datum.getText().toString();
                Toast.makeText(CreditCart.this,nummer+" ",Toast.LENGTH_SHORT).show();
                boolean valid = true;
                if(nummer.isEmpty())
                {
                    valid=false;
                    txtNummer.setError("Bitte ausfüllen!!");
                }
                if(prnr.isEmpty())
                {
                    valid=false;
                    txtPruefnummer.setError("Bitte ausfüllen!!");
                }
                if(datum.isEmpty())
                {
                    valid=false;
                    Datum.setError("Bitte ausfüllen!!");
                }

                if(valid)
                {
                    //In Firebase DB Speichern dass Kreditkarte verbunden ist!!
                }
            }


        });

        Datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int Jahr = cal.get(Calendar.YEAR);
                int Monat = cal.get(Calendar.MONTH);
                int Day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreditCart.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        Jahr,Monat,Day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int Year, int Month, int i2) {
                Month = Month+1;
                Calendar cal = Calendar.getInstance();
                int Jahr = cal.get(Calendar.YEAR);
                int Monat = cal.get(Calendar.MONTH);
                Monat=Monat+1;
                Toast.makeText(CreditCart.this,Jahr+"-"+Monat,Toast.LENGTH_SHORT).show();
                if(Year < Jahr || Year <= Jahr && Month<Monat)
                {
                    Datum.setError("Kreditkarte abgelaufen!!!");
                }else {
                    Datum.setText(Year + "-" + Month);
                }
            }
        };
    }

    private void fillProvider(){
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("American Express");
        spinnerArray.add("Diners Club");
        spinnerArray.add("Mastercard");
        spinnerArray.add("Visa");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CreditType.setAdapter(adapter);
    }
}
