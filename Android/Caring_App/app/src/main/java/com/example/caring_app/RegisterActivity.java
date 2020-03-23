package com.example.caring_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
//import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    EditText etPwd;
    EditText etEmail;
    EditText etFN;
    Button btnSignUp;
    TextView tvLogin;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);
        initComponents();
    }
    private void initComponents(){
        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();


        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                register();


            }
        });

        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateActivity(true);
            }
        });


    }

    private void  register(){

         etEmail = findViewById(R.id.etEmail);
         etPwd = findViewById(R.id.etPwd);
         etFN=findViewById(R.id.etFN);


        String email = etEmail.getText().toString();
        String password = etPwd.getText().toString();
        final String fullname=etFN.getText().toString();

        etEmail.setError(null);
        etPwd.setError(null);
        if(email.isEmpty()){
            etEmail.setError("Please enter a valid Email");
            etEmail.requestFocus();
        }
        else if(password.isEmpty()){
            etPwd.setError("Not a valid Password");
            etPwd.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password)

                   .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                       @Override
                       public void onSuccess(AuthResult authResult) {
                           FirebaseUser user=mAuth.getCurrentUser();
                           user.sendEmailVerification();

                           Map<String,String> userData=new HashMap<>();
                           userData.put("FullName",fullname);
                           userData.put("email",user.getEmail());
                           userData.put("paymethod","");


                           db.collection("users").document(mAuth.getCurrentUser().getUid()).set(userData)
                                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void aVoid) {
                                           updateActivity(true);
                                       }
                                   })
                                   .addOnFailureListener(new OnFailureListener() {
                                       @Override
                                       public void onFailure(@NonNull Exception e) {
                                           Log.e("registerFail",e.getMessage(),e);

                                       }
                                   });
                       }
                   })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("registerFail",e.getMessage(),e);
                        }
                    });

}
       }

       private void updateActivity(boolean success){
        if(success) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
       }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(i);
    }
}



