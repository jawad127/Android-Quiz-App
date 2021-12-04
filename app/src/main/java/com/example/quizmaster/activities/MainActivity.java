package com.example.quizmaster.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizmaster.R;
import com.example.quizmaster.adapters.QuizAdapter;
import com.example.quizmaster.models.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle actionBarDrawerToggle;
    ProgressBar progressBar;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    List<Quiz> quizList;
    NavigationView navigationView;
    RecyclerView.Adapter quizAdapter;
    FirebaseFirestore db;
    RecyclerView recyclerView;
    Boolean isRetrieved = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*populateDummyData();*/

        setUpViews();

    }

   /* private void populateDummyData() {
        quizList=new ArrayList<Quiz>();
        quizList.add(new Quiz("1","History",R.drawable.icon_history,null));
        quizList.add(new Quiz("1","History",R.drawable.icon_history,null));
        quizList.add(new Quiz("1","History",R.drawable.icon_history,null));
        quizList.add(new Quiz("1","History",R.drawable.icon_history,null));
        quizList.add(new Quiz("1","History",R.drawable.icon_history,null));
        quizList.add(new Quiz("1","History",R.drawable.icon_history,null));

    }*/

    @Override
    protected void onResume() {
        super.onResume();
        quizAdapter.notifyDataSetChanged();
    }

    public void setUpViews() {

        getViewRefference();
        setUpDrawerLayout();
        setUpRecyclerView();
        setUpFireStore();


    }


    private void getViewRefference() {
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        progressBar = findViewById(R.id.progressBar);
        navigationView = findViewById(R.id.navigationLayout);
    }

    private void setUpFireStore() {

        db = FirebaseFirestore.getInstance();
        db.collection("quizzes")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            quizList.add(documentSnapshot.toObject(Quiz.class));
                        }
                        progressBar.setVisibility(View.GONE);

                        quizAdapter.notifyDataSetChanged();
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Log.d("error", e.getMessage());
            }
        });
    }

    private void setUpRecyclerView() {
        quizList = new ArrayList<Quiz>();
        quizAdapter = new QuizAdapter(quizList, getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(quizAdapter);
    }

    public void setUpDrawerLayout() {
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item_profile:
                        Log.d("item", "onNavigationItemSelected: ");
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        intent.putExtra("userEmail", FirebaseAuth.getInstance().getCurrentUser().getEmail());
                        startActivity(intent);
                        break;
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }
}