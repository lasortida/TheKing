package com.example.mygame;

public class Act {

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
