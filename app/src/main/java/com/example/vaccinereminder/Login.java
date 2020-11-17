package com.example.vaccinereminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText getEmail,getPassword;
    Button login;
    TextView createAccount;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getEmail = findViewById(R.id.Email);
        getPassword = findViewById(R.id.Password);
        fAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.loginButton);
        createAccount = findViewById(R.id.createText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getEmail.getText().toString().trim();
                String password = getPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    getEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    getPassword.setError("Password is Required");
                    return;
                }

                if(password.length()<6){
                    getPassword.setError("Password Must be Greater Or Equal To 6 Characters");
                    return;
                }

                //Authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Login.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });


    }
}