package com.example.quizmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizmaster.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    Button btnLogout;
    ImageView imageProfile;
    TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupViews();
    }

    private void setupViews() {
        getRefference();
        setUpListener();
        setupProfileView();
    }

    private void setupProfileView() {
        userEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void setUpListener() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                    finish();

                }

            }
        });
    }

    private void getRefference() {
        btnLogout = findViewById(R.id.btnLogout);
        imageProfile = findViewById(R.id.profileImageView);
        userEmail = findViewById(R.id.userEmail);
    }
}