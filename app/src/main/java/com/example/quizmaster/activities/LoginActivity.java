package com.example.quizmaster.activities;

import android.annotation.SuppressLint;
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

import com.example.quizmaster.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText textPassword, textEmail;
    TextView signupText;
    private Button btnLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        initUI();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });
    }

    private void loginUser(

    ) {
        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();
        if (email.isEmpty() || password.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login Succesful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void initUI() {
        btnLogin = findViewById(R.id.btnLogin);
        textPassword = findViewById(R.id.loginPassword);
        textEmail = findViewById(R.id.loginEmail);
        signupText = findViewById(R.id.textViewSignUp);
    }


}