package com.example.mygame.OnlineMode.Classes;

import java.io.Serializable;

public class Note implements Serializable {
    public int idOfCountry;
    public int type;
    public String text;
    public int stateAway;
    public int stateToMe;

    public Note(int idOfCountry, int type, int stateAway, int stateToMe){
        this.type = type;
        if (type == 0){
            this.stateAway = stateAway;
            this.stateToMe = stateToMe;
            switch (stateAway){
                case 0:
                    text += "Деньги";
                    break;
                case 1:
                    text += "Армия";
                    break;
                case 2:
                    text += "Экономика";
                    break;
                case 3:
                    text += "Промышленность";
                    break;
                case 4:
                    text += "Еда";
                    break;
            }
            text += " --> ";
            switch (stateToMe){
                case 0:
                    text += "Деньги";
                    break;
                case 1:
                    text += "Армия";
                    break;
                case 2:
                    text += "Экономика";
                    break;
                case 3:
                    text += "Промышленность";
                    break;
                case 4:
                    text += "Еда";
                    break;
            }
        }
    }
}
