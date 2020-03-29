package com.example.android.applicationtaskhi2020.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    public static final String PREFS = "myprefs";


    private Context context;

    public SharedPref(Context context){
        this.context = context;
    }

        public static void saveIntPoints(Context context, String key, int value) {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(key, value);
            editor.apply();
        }

        public static void saveIntActions(Context context, String key, int value){
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(key, value);
            editor.apply();
        }

        public static String getInt(Context context, String key, int value){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        int score = sharedPref.getInt(key, value);
        String scoreString = Integer.toString(score);
        return scoreString;
        }


}
