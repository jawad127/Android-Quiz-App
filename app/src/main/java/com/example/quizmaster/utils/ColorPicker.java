package com.example.quizmaster.utils;



public class ColorPicker {
    private  String colors[];
    private  int colorIndex;
    private static ColorPicker colorPicker;

    ColorPicker(){
        colorIndex=0;
        colors=new String[]{"#3EB9DF","#3685BC","#D36280","#E44F55","#FA80F6","#818BCA","#7D659A","#51BAB3","#4FB66C","#E3AD17","#B5BFE6","#627991","#EF8EAD"};
    }

    public static ColorPicker getInstance(){
        if(colorPicker==null){
            colorPicker = new ColorPicker();
        }
        return colorPicker;
    }

    public  String getColor(){
        colorIndex = (colorIndex + 1) % colors.length;
        return colors[colorIndex];
    }
}
