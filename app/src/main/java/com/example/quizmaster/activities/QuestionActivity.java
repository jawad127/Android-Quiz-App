package com.example.quizmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizmaster.R;
import com.example.quizmaster.adapters.OptionAdapter;
import com.example.quizmaster.models.Question;
import com.example.quizmaster.models.Quiz;
import com.example.quizmaster.utils.Utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class QuestionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnSubmit,btnNext,btnPrevious;
    TextView description;
    List<Question> questionList;
    private int index=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setUpViews();



    }

    private void setUpViews() {
        recyclerView=findViewById(R.id.questionRecyclerView);
        btnNext=findViewById(R.id.btnNext);
        btnPrevious=findViewById(R.id.btnPrevious);
        btnSubmit=findViewById(R.id.btnSubmit);
        description=findViewById(R.id.description);

        populateQuestionList();
        setUpDescription();
        setUpButtonVisibility();
        setUpActionListeners();
        setUpRecyclerView();

    }

    private void setUpDescription() {
        description.setText(Utility.getStringValueOf(questionList.get(index).getDescription(),""));
    }

    private void setUpButtonVisibility() {
        btnNext.setVisibility(View.GONE);
        btnPrevious.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);

        if(index==0){
            btnNext.setVisibility(View.VISIBLE);
        }

        else if(index == questionList.size()-1){
            btnNext.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
            btnPrevious.setVisibility(View.VISIBLE);
        }
        else {
            btnPrevious.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
        }
    }

    private void populateQuestionList() {

        Quiz quiz= (Quiz) getIntent().getSerializableExtra("quiz");
        questionList=new ArrayList<Question>();
        for(Map.Entry<String,Question> entry : quiz.getQuestions().entrySet()){
            questionList.add(entry.getValue());
        }

    }

    private void setUpActionListeners() {

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(index < questionList.size()){
                        index++;
                        setUpDescription();
                        setUpButtonVisibility();
                        setUpRecyclerView();

                        Log.d("TAG", "index : "+index);
                    }
                }
            });

            btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(index > 0){
                        index--;
                        setUpDescription();
                        setUpButtonVisibility();
                        setUpRecyclerView();

                        Log.d("TAG", "index : "+index);
                    }
                }
            });

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(index==questionList.size()-1){
                        index=0;
                        Intent intent =  new Intent(QuestionActivity.this,ResultActivity.class);
                        intent.putExtra("questionList", (Serializable) questionList);
                        startActivity(intent);
                        finish();
                        Log.d("TAG", "onsubmit pressed ");
                        Log.d("TAG", "index : "+index);
                    }
                }
            });
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OptionAdapter(questionList.get(index),getApplicationContext()));
    }
}