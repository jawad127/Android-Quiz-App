package com.example.quizmaster.utils;


import com.example.quizmaster.R;

public class IconGiver {
    private  Integer icons[];
    private static IconGiver iconGiver;

    IconGiver(){
        icons=new Integer[]{R.drawable.icon_book,R.drawable.icon_cup,R.drawable.icon_history,R.drawable.icon_earth,R.drawable.icon_notes,R.drawable.icon_clip};
    }

    public static IconGiver getInstance(){
        if(iconGiver==null){
            iconGiver = new IconGiver();
        }
        return iconGiver;
    }

    public  int getIcon(int position){

        return icons[position];
    }
}

