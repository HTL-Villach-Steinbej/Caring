package com.example.caring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class PayPal extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassw;
    Button Save;
    private FirebaseAuth mAuth;
    private FirebaseFirestore Firestoredb;
    private DatabaseReference FirebaseDB;

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
                    mAuth=FirebaseAuth.getInstance();
                    FirebaseUser User = mAuth.getCurrentUser();
                    Firestoredb = Firestoredb.getInstance();

                    try {
                        Firestoredb.collection("users").document(User.getUid()).update("paymethod","paypal");
                        Intent s = new Intent(PayPal.this,MainActivity.class);
                        startActivity(s);
                    }catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }


        });
    }

    public final static boolean isValidEmail(String target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }
}
