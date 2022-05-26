package com.example.mygame.OnlineMode.Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class AllianceOnline implements Serializable {
    public String name;
    public String description;
    public ArrayList<Integer> countries;
    public int idOfOwner;
    public int idOfAvatar;
    public boolean isActiveWar;
    public int id;

    public AllianceOnline(int id, int idOfOwner, int idOfAvatar, String name, String description){
        this.id = id;
        this.idOfOwner = idOfOwner;
        this.idOfAvatar = idOfAvatar;
        this.name = name;
        this.description = description;
        countries = new ArrayList<>();
    }

    public int[] getIdOfCountries(){
        int[] result = new int[countries.size()];
        for (int i = 0; i < countries.size(); ++i){
            result[i] = countries.get(i);
        }
        return result;
    }
}
