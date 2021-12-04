package com.example.quizmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizmaster.R;
import com.example.quizmaster.models.Question;
import com.example.quizmaster.utils.Utility;

import java.util.List;
import java.util.Map;

import io.grpc.okhttp.internal.Util;

public class ResultActivity extends AppCompatActivity {

    List<Question> questionList;
    int score;
    TextView scoreView, correctAnswerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setUpViews();

    }

    private void setUpViews() {
        populateQuestionList();
        calculateResult();
        getReferences();
        bindScoreToView();
        bindAnswersToView();
    }

    private void getReferences() {
        correctAnswerView = findViewById(R.id.correctAnswerView);
        scoreView =  findViewById(R.id.scoreTextView);
    }

    private void bindAnswersToView() {

        StringBuilder builder = new StringBuilder("");

        for(Question question : questionList){
            if(!Utility.getStringValueOf(question.getUserAnswer(),"").equals(question.getAnswer())){
                builder.append("<font color='#18206f'><b>Question: "+ question.getDescription()  +"</b></font><br/><br/>");
                builder.append("<font color='#009688'><b>Answer: "+ question.getAnswer() +"</b></font><br/><br/>");
            }
        }

        correctAnswerView.setText(Html.fromHtml(builder.toString(),Html.FROM_HTML_MODE_COMPACT));
    }

    private void bindScoreToView() {
        scoreView.setText("Your Score  :  " + score);
    }

    private void populateQuestionList() {
        questionList = (List<Question>) getIntent().getSerializableExtra("questionList");
    }

    private void calculateResult()  {
        score=0;
        if(questionList!=null){
            for(Question question : questionList){
                if(Utility.getStringValueOf(question.getUserAnswer(),"").equals(question.getAnswer())){
                    ++score;
                }
            }
        }
    }
}