package com.example.mygame;

import java.io.Serializable;

public class Country implements Serializable {
    String name;
    boolean isInterWar;
    boolean isCivilWar;
    int populationMood;

    public Country(String name){
        this.name = name;
        isInterWar = false;
        isCivilWar = false;
        populationMood = 60;
    }

    public void newWeek(){
        int random = (int) Math.random() * 1;
        if (random == 0) populationMood -= 5;
        if (random == 1) populationMood += 5;
    }

    public void setInterWar(){
        isInterWar = true;
    }

    public void setCivilWar(){
        isCivilWar = true;
    }

    public boolean isInterWar(){
        return isInterWar;
    }

    public boolean isCivilWar(){
        return isCivilWar;
    }
}
