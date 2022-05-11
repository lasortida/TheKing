package com.example.mygame;

import java.io.Serializable;

public class Invitation implements Serializable {
    private String text;
    private Country owner;
    private Country target;
    private boolean type; //true - Страна -> Альянс, false - Альянс -> Страна
    private boolean accept;

    public Invitation(String text, Country owner, Country target, boolean type){
        this.text = text;
        this.owner = owner;
        this.target = target;
        this.type = type;
    }

    public Invitation(Country owner, Country target, boolean type){
        this.owner = owner;
        this.target = target;
        this.type = type;
    }

    public boolean isAccept() {
        return accept;
    }

    public String getText(){
        return text;
    }
}
