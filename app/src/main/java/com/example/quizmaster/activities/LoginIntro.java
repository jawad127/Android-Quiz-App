package com.example.quizmaster.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizmaster.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginIntro extends AppCompatActivity {

    private Button btnGetStarted;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intro);


        getReferences();
        checklogin();
        setUpListeners();





    }

    private void setUpListeners() {
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect("login");
            }
        });
    }

    private void getReferences() {
        firebaseAuth = FirebaseAuth.getInstance();
        btnGetStarted =findViewById(R.id.getStarted);
    }

    private void checklogin() {
        if (firebaseAuth.getCurrentUser() != null) {
            redirect("main");

        }
    }

    public void redirect(String activity) {
        switch (activity) {
            case "login":
                startActivity(new Intent(LoginIntro.this, LoginActivity.class));
                finish();
                break;

            case "main":
                startActivity(new Intent(LoginIntro.this, MainActivity.class));
                finish();
                break;

            default:
                break;

        }
    }
}