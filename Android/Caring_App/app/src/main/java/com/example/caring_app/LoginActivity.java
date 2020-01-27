package com.example.caring_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail,txtPassword;
    Button Login;

      FirebaseAuth FirebaseAuth;
      private com.google.firebase.auth.FirebaseAuth.AuthStateListener AuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

        FirebaseAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        Login = findViewById(R.id.btnLogin);

        AuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull com.google.firebase.auth.FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = FirebaseAuth.getCurrentUser();
                if(firebaseUser!= null)
                {
                    Toast.makeText(LoginActivity.this,"You are logged in!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                }else
                {
                    Toast.makeText(LoginActivity.this,"Please login!",Toast.LENGTH_SHORT).show();
                }
            }
        };

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                if(email.isEmpty()){
                    txtEmail.setError("Please type in your Email");
                    txtEmail.requestFocus();
                }else if(password.isEmpty())
                {
                    txtPassword.setError("Please type in your Email");
                    txtPassword.requestFocus();
                }else if(email.isEmpty() && password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"Login failed, please try again!",Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty() && password.isEmpty())){
                    FirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login failed, please try again!",Toast.LENGTH_SHORT).show();
                            }else
                            {
                                Intent i = new Intent(LoginActivity.this,TestMapLOngEX.class);
                                startActivity(i);
                            }
                        }
                    });
                }else
                {
                    Toast.makeText(LoginActivity.this,"Error occured",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.addAuthStateListener(AuthStateListener);
    }
}
