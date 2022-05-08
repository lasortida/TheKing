package com.example.mygame;

import java.io.Serializable;
import java.util.ArrayList;

public class Alliance implements Serializable {
    String name;
    ArrayList<Country> countries;
    Country owner;
    int idOfAvatar;

    public Alliance(Country owner, String name){
        this.owner = owner;
        this.name = name;
        countries = new ArrayList<>();
        int value = (int)(Math.random() * Storage.imagesAvatar.length);
        idOfAvatar = Storage.imagesAvatar[value];
    }

    public void addCountry(Country country){
        countries.add(country);
    }
}
