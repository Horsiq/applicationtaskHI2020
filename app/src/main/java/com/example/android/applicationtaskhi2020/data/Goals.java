package com.example.android.applicationtaskhi2020.data;

import android.os.Parcelable;

public class Goals {

    private int mImage;
    private int mGoalString;
    private int mPoints;
    private int mDescription;

    public Goals(int image, int goalString, int points){
        this.mImage = image;
        this.mGoalString = goalString;
        this.mPoints = points;
    }

    public Goals(int image, int goalString, int points, int description){
        this.mImage = image;
        this.mGoalString = goalString;
        this.mPoints = points;
        this.mDescription = description;

    }

    public int getImage(){return mImage;}
    public int getGoalString(){return mGoalString;}
    public int getPoints(){return mPoints;}
    public int getDescription(){return mDescription;}



}
