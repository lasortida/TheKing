package com.example.mygame;

public class Country {
    String name;
    boolean isInterWar;
    boolean isCivilWar;

    public Country(String name){
        this.name = name;
        isInterWar = false;
        isCivilWar = false;
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
