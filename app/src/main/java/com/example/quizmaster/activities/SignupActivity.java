package com.example.quizmaster.activities;

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

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email,password,confirmPassword;
    Button btnSignup;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initUI();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
    }


    public void signup(){

        String email,password,confirmPassword;

        email =this.email.getText().toString();
        password = this.password.getText().toString();
        confirmPassword=this.confirmPassword.getText().toString();

        if(email.isEmpty()
                || password.isEmpty()
                || confirmPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!confirmPassword.equals(password)){
            Toast.makeText(getApplicationContext(),"passwords do not match",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Successfully registered",Toast.LENGTH_SHORT).show();
                            //    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                           //     finish();
                                Log.d("TAG", "token : " + task.toString());

                            } else {
                                Log.w("error", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Error getting registered",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

    }
    public void initUI(){
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.signUpEmail);
        btnSignup = findViewById(R.id.btnSignup);
        password=findViewById(R.id.signUpPassword);
        confirmPassword=findViewById(R.id.signUpConfirmPassword);
        loginText=findViewById(R.id.loginText);
    }
}