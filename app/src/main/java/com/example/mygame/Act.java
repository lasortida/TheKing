package com.example.mygame;

import java.io.Serializable;

public class Act implements Serializable {

    private String text;
    private Answer[] answers;

    public Act(String text, Answer[] answers){
        this.text = text;
        this.answers = answers;
    }

    public String getText(){
        return text;
    }

    public Answer getAnswer(int number){
        return answers[number];
    }
}
