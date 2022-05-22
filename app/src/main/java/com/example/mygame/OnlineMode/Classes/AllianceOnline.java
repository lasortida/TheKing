package com.example.mygame.OnlineMode.Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class AllianceOnline implements Serializable {
    public String name;
    public String description;
    public ArrayList<CountryOnline> countries;
    public int idOfOwner;
    public int idOfAvatar;
    public boolean isActiveWar;

    public AllianceOnline(int idOfOwner, int idOfAvatar, String name, String description){
        this.idOfOwner = idOfOwner;
        this.idOfAvatar = idOfAvatar;
        this.name = name;
        this.description = description;
        countries = new ArrayList<>();
    }

    public String[] getNameOfCountries(){
        String[] result = new String[countries.size()];
        for (int i = 0; i < countries.size(); ++i){
            result[i] = countries.get(i).name;
        }
        return result;
    }
}
