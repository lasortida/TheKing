package com.example.mygame.OnlineMode.ForServer;

import java.io.Serializable;

public class Post implements Serializable {
    public double moneyStatus;
    public double armyStatus;
    public double businessStatus;
    public double workerStatus;
    public double foodStatus;

    public int tradeWith = -1;
    public int tradeAway = -1;
    public int tradeToMe = -1;
    public boolean[] isTradeAccepted;
    public int[] confirmation;

    public String nameOfNewAlliance;
    public String description;
    public int avatar;

    public boolean end;

    public int idOfCountryOffer = -1;
    public boolean[] isOfferAccepted;
    public int[] allianceIdConfirmation;
}
