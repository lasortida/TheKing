package com.example.mygame;

public class Answer {
    private String text;
    private Effect effect;

    public Answer(String text, Effect effect){
        this.text = text;
        this.effect = effect;
    }

    public boolean isTimeToNext(){
        return effect.isCanI();
    }

    public String getText(){
        return text;
    }

    public Effect getEffect(){
        return effect;
    }
}
