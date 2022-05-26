package com.example.mygame;

import java.io.Serializable;

public class Post implements Serializable {

    private String text;

    public Post(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}