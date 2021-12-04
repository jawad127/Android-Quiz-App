package com.example.quizmaster.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizmaster.R;
import com.example.quizmaster.models.Question;
import com.example.quizmaster.utils.Utility;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {

    private Question question;
    private String options[];
    Context context;

    public OptionAdapter(Question question, Context context) {
        this.question = question;
        this.options = new String[]{question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4()};
        this.context=context;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.option_item,parent,false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        holder.title.setText(Utility.getStringValueOf(options[position],""));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setUserAnswer(options[position]);
                notifyDataSetChanged();
            }
        });
        if(question.getUserAnswer()==options[position]){

            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg);
            Log.d("TAG", "matched");
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg);
            Log.d("TAG", "unMatched");
        }
    }

    @Override
    public int getItemCount() {
        return options.length;
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.option_textView);

        }
    }
}
