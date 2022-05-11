package com.example.mygame;

import java.io.Serializable;
import java.util.ArrayList;

public class Alliance implements Serializable {
    String name;
    ArrayList<Country> countries;
    Country owner;
    int idOfAvatar;
    boolean isActiveWar;
    ArrayList<Invitation> invitations;

    public Alliance(Country owner, String name){
        this.owner = owner;
        this.name = name;
        countries = new ArrayList<>();
        invitations = new ArrayList<>();
        int value = (int)(Math.random() * Storage.imagesAvatar.length);
        idOfAvatar = Storage.imagesAvatar[value];
        countries.add(new Country("TEST"));
    }

    public void setIdOfAvatar(int id){
        idOfAvatar = id;
    }

    public void addInvitation(Invitation invitation) {
        invitations.add(invitation);
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
