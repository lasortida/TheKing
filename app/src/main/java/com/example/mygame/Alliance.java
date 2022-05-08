package com.example.mygame;

import java.io.Serializable;
import java.util.ArrayList;

public class Alliance implements Serializable {
    String name;
    ArrayList<Country> countries;
    Country owner;
    int idOfAvatar;
    boolean isActiveWar;

    public Alliance(Country owner, String name){
        this.owner = owner;
        this.name = name;
        countries = new ArrayList<>();
        int value = (int)(Math.random() * Storage.imagesAvatar.length);
        idOfAvatar = Storage.imagesAvatar[value];
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
        countries.add(new Country("Test"));
    }

    public void addCountry(Country country){
        countries.add(country);
    }

    public String[] getNameOfCountries(){
        int len = countries.size();
        String[] result = new String[len];
        for (int i = 0; i < len; ++i){
            result[i] = countries.get(i).name;
        }
        return result;
    }
}
