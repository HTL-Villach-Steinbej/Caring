package com.example.caring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class

Payment extends AppCompatActivity {
    Button PayPal;
    Button Credit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        PayPal = findViewById(R.id.buttonPayPal);
        Credit = findViewById(R.id.buttonCredit);

        PayPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Payment.this,PayPal.class);
                startActivity(i);
            }
        });

        Credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Payment.this,CreditCart.class);
                startActivity(i);
            }
        });


    }
}
