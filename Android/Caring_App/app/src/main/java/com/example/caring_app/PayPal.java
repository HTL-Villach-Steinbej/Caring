package com.example.caring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PayPal extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassw;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassw = findViewById(R.id.txtPassword);
        Save=findViewById(R.id.btnVerbinden);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = txtEmail.getText().toString();
                String password = txtPassw.getText().toString();

                boolean valid = true;
                if(email.isEmpty())
                {
                    valid=false;
                    txtEmail.setError("Bitte ausfüllen!!");
                }

                if(!email.isEmpty())
                {
                    if(!isValidEmail(email))
                    {
                        valid= false;
                        txtEmail.setError("Ungültige Email Adresse!!");
                    }
                }
                if(password.isEmpty())
                {
                    valid=false;
                    txtPassw.setError("Bitte ausfüllen!!");
                }

                if(valid)
                {
                    //In Firebase DB Speichern dass Kreditkarte verbunden ist!!
                }
            }


        });
    }

    public final static boolean isValidEmail(String target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }
}
