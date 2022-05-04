package com.example.mygame;

import java.io.Serializable;

public class Relationship implements Serializable {
    int countryId;
    String text;
    String[] answers;
    double impact1;
    double impact2;
    double influence;
    String nameOfState;

    public Relationship(int countryId, String text, String[] answers, double impact1, double impact2){
        this.countryId = countryId;
        this.text = text;
        this.answers = answers;
        this.impact1 = impact1;
        this.impact2 = impact2;
    }

    public Relationship(String text, String[] answers, double impact1, double impact2, String nameOfState, double influence){
        this.text = text;
        this.answers = answers;
        this.impact1 = impact1;
        this.impact2 = impact2;
        this.nameOfState = nameOfState;
        this.influence = influence;
    }

    public String getText(){
        return text;
    }

    public String[] getAnswers(){
        return answers;
    }

    public int getIdOfCountry(){
        return countryId;
    }
}
