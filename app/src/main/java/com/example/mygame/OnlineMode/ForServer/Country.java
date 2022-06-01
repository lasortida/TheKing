package com.example.mygame.OnlineMode.ForServer;

import java.io.Serializable;

public class Country implements Serializable {
    public String name;
    public int id;
    public int idOfIcon;
    public int idOfMarker;

    public float moneyStatus = 0.5f;
    public float armyStatus = 0.5f;
    public float businessStatus = 0.5f;
    public float workerStatus = 0.5f;
    public float foodStatus = 0.5f;

    @Override
    public boolean equals(Object o) {
        if (o instanceof Country){
            Country c = (Country) o;
            if (c.name.equals(this.name)){
                return true;
            }
        }
        return false;
    }

}
