package com.example.quizmaster.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizmaster.R;
import com.example.quizmaster.activities.QuestionActivity;
import com.example.quizmaster.models.Quiz;
import com.example.quizmaster.utils.ColorPicker;
import com.example.quizmaster.utils.IconGiver;
import com.example.quizmaster.utils.Utility;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private final Context context;
    private List<Quiz> quizList;
    public QuizAdapter(List<Quiz> quizList, Context context) {
        this.quizList = quizList;
        this.context = context;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.quiz_item,parent,false);
//        Log.d("TAG", "onCreateViewHolder");
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.textView.setText(Utility.getStringValueOf(quizList.get(position).getTitle(),""));
        holder.imageView.setImageResource(IconGiver.getInstance().getIcon(position));
        holder.container.setCardBackgroundColor(Color.parseColor(ColorPicker.getInstance().getColor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("quiz",  quizList.get(position));
                context.startActivity(intent);
            }
        });

//        Log.d("TAG", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
//        Log.d("TAG", "getItemCount");
        return quizList.size();

    }

    public class QuizViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        CardView container;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.quizIcon);
            textView = itemView.findViewById(R.id.quizText);
            container=itemView.findViewById(R.id.cardContainer);
        }
    }
}
