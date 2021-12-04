package com.example.quizmaster.utils;

import com.google.gson.annotations.SerializedName;

public class Utility {

    /**  Safety Checks for String
     *
     * @param stringValue  passed String Object
     * @param defaultValue  default value for the String Object if Object is null
     * @return  return type is String
     * */
    public static String getStringValueOf(String stringValue, String defaultValue){
        if(stringValue == null ){
            return defaultValue;
        }
        return stringValue;
    }

    public static int getIntegerValueOf(int integerValue, int defaultValue){
        if(integerValue == 0 ){
            return defaultValue;
        }
        return integerValue;
    }
}
