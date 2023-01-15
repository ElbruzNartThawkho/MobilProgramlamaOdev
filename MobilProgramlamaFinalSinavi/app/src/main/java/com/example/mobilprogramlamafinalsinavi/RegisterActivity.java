package com.example.mobilprogramlamafinalsinavi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button login,signup;
    private EditText email,password;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login = (Button) findViewById(R.id.loginac);
        signup = (Button) findViewById(R.id.buttonsignup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        email = (EditText) findViewById(R.id.registerEmail);
        password = (EditText) findViewById(R.id.registerPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMail=email.getText().toString();
                String userPassword=password.getText().toString();
                if(userMail.isEmpty()||userPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Values is Empty",Toast.LENGTH_LONG).show();
                }
                else{
                    firebaseAuth.createUserWithEmailAndPassword(userMail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}