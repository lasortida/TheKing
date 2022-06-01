package com.example.mygame.OnlineMode.Classes;

import com.example.mygame.Effect;
import com.example.mygame.OnlineMode.ForServer.Format;
import com.example.mygame.OnlineMode.ForServer.Post;
import com.example.mygame.Week;

import java.io.Serializable;
import java.util.ArrayList;

public class GameOnline implements Serializable {
    public String idOfRoom;
    public int numberOfWeek;
    public String news;
    public CountryOnline[] countries;
    public int yourUserCode;
    public int yourCountryId;
    public ArrayList<AllianceOnline> alliances;
    public boolean isJobDone;
    public boolean isGameOver;
    public Week week;
    public StorageOnline storage;
    public long time;
    public int warStart;
    public boolean isWar;
    public boolean end;


    public Post post;

    public int[] tradeWith;
    public int[] tradeAway;
    public int[] tradeToMe;
    public boolean[] isHandle;
    public int postIndex = 0;

    public int[] offersFromAlliances;
    public boolean[] isHandleOffer;
    public int postIndexOffer = 0;

    public GameOnline(){
        alliances = new ArrayList<>();
        post = new Post();
    }

    public void takeChanges(Effect effect){
        countries[yourCountryId].moneyStatus += effect.getMoneyStatusChange();
        countries[yourCountryId].armyStatus += effect.getArmyMoodChange();
        countries[yourCountryId].businessStatus += effect.getBourgeoisMoodChange();
        countries[yourCountryId].workerStatus += effect.getWorkersMoodChange();
        countries[yourCountryId].foodStatus += effect.getFoodStatusChange();

        check();
    }

    public ArrayList<Note> getNotes(){
        ArrayList<Note> notes = new ArrayList<>();
        if (tradeWith != null && tradeWith.length != 0){
            for (int i = 0; i < tradeWith.length; ++i){
                if (!isHandle[i]){
                    Note note = new Note(tradeWith[i], 0, tradeAway[i], tradeToMe[i]);
                    notes.add(note);
                }
            }
        }
        return notes;
    }

    public ArrayList<Note> getOfferNote(){
        ArrayList<Note> notes = new ArrayList<>();
        if (offersFromAlliances != null){
            for (int i = 0; i < offersFromAlliances.length; ++i){
                if (!isHandleOffer[i]){
                    AllianceOnline alliance = alliances.get(offersFromAlliances[i]);
                    Note note = new Note(alliance.idOfOwner, 1, 0, 0);
                    note.idOfAlliance = offersFromAlliances[i];
                    notes.add(note);
                }
            }
        }
        return notes;
    }

    public CountryOnline[] getListOfForeignCountries(){
        CountryOnline[] result = new CountryOnline[countries.length - 1];
        int j = 0;
        for (int i = 0; i < countries.length; ++i){
            if (countries[i].id != yourCountryId){
                result[j] = countries[i];
                j++;
            }
        }
        return result;
    }

    public void getDataFromFormat(Format format){
        check();
        time = 90000;
        post = new Post();
        postIndex = 0;
        tradeWith = null;
        tradeAway = null;
        tradeToMe = null;
        isHandleOffer = null;
        isHandle = null;
        offersFromAlliances = null;
        news = "";
        numberOfWeek = format.numberOfWeek;
        setWeek();
        if (isGameOver){
            news += "К сожалению ваше правления оказалось неудачным для страны! Вы проиграли!";
            news += "\n\n";
            news += "Конец игры!";
            end = true;
        }
        else if (format.win){
            end = true;
            news = "Ура, вы выиграли!";
        }
        else{
            news = storage.getRandomNews() + "\n\n";
            isJobDone = false;
            countries[yourCountryId].wasTrade = false;
            numberOfWeek = format.numberOfWeek;
            countries[yourCountryId].moneyStatus = format.moneyStatus;
            countries[yourCountryId].armyStatus = format.armyStatus;
            countries[yourCountryId].businessStatus = format.businessStatus;
            countries[yourCountryId].workerStatus = format.workerStatus;
            countries[yourCountryId].foodStatus = format.foodStatus;
            if (numberOfWeek == countries[yourCountryId].weekOfOffer){
                if (format.isTradeAccepted){
                    news += "Сделка, которую вы недавно предложили состоялась! +";
                    countries[yourCountryId].weekOfOffer = 0;
                    countries[yourCountryId].safe = -1;
                    int state = countries[yourCountryId].treasure;
                    switch (state){
                        case 0:
                            countries[yourCountryId].moneyStatus += 0.17;
                            news += "Деньги";
                            break;
                        case 1:
                            countries[yourCountryId].armyStatus += 0.17;
                            news += "Армия";
                            break;
                        case 2:
                            countries[yourCountryId].businessStatus += 0.17;
                            news += "Экономика";
                            break;
                        case 3:
                            countries[yourCountryId].workerStatus += 0.17;
                            news += "Промышленность";
                            break;
                        case 4:
                            countries[yourCountryId].foodStatus += 0.17;
                            news += "Еда";
                            break;
                    }
                    news += "\n\n";
                    countries[yourCountryId].treasure = -1;
                }
                else{
                    news += "Сделка, которую вы недавно предложили не состоялась!\n\n";
                    countries[yourCountryId].weekOfOffer = 0;
                    countries[yourCountryId].treasure = -1;
                    int state = countries[yourCountryId].safe;
                    switch (state){
                        case 0:
                            countries[yourCountryId].moneyStatus -= 0.17;
                            break;
                        case 1:
                            countries[yourCountryId].armyStatus -= 0.17;
                            break;
                        case 2:
                            countries[yourCountryId].businessStatus -= 0.17;
                            break;
                        case 3:
                            countries[yourCountryId].workerStatus -= 0.17;
                            break;
                        case 4:
                            countries[yourCountryId].foodStatus -= 0.17;
                            break;
                    }
                    countries[yourCountryId].safe = -1;
                }
            }
            if (format.tradeWith != null && format.tradeWith.length != 0){
                tradeWith = format.tradeWith;
                tradeAway = format.tradeAway;
                tradeToMe = format.tradeToMe;
                isHandle = new boolean[tradeWith.length];
            }
            if (format.newAllianceNames != null && format.newAllianceNames.length != 0){
                for (int i = 0; i < format.newAllianceNames.length; ++i){
                    String name = format.newAllianceNames[i];
                    String description = format.newAllianceDescription[i];
                    int id = format.newAllianceIds[i];
                    int idOfOwner = format.newAllianceIdsOfOwner[i];
                    int avatar = format.newAllianceAvatars[i];
                    AllianceOnline alliance = new AllianceOnline(id, idOfOwner, avatar, name, description, 0);
                    try{
                        alliances.set(id, alliance);
                    } catch (Exception e){
                        alliances.add(alliance);
                    }
                }
            }
            if (format.offersFromAlliances != null && format.offersFromAlliances.length != 0){
                this.offersFromAlliances = format.offersFromAlliances;
                isHandleOffer = new boolean[offersFromAlliances.length];
            }
            if (format.newIdsOfAlliance != null && format.newIdsOfAlliance.length != 0){
                for (int i = 0; i < format.newIdsOfAlliance.length; ++i){
                    AllianceOnline alliance = alliances.get(format.newIdsOfAlliance[i]);
                    alliance.countries.add(format.newIdsOfCountry[i]);
                    alliances.set(format.newIdsOfAlliance[i], alliance);
                    if (countries[yourCountryId].idOfAlliance == format.newIdsOfAlliance[i]){
                        news += "\n К вашему альянсу присоединилась страна " + countries[format.newIdsOfCountry[i]].name;
                    }
                }
            }
        }
    }

    public ArrayList<CountryOnline> getBlankCountries(){
        ArrayList<CountryOnline> result = new ArrayList<>();
        AllianceOnline allianceOnline = alliances.get(countries[yourCountryId].idOfAlliance);
        if (allianceOnline.countries.size() == 0){
            int except = yourCountryId;
            for (int i = 0; i < countries.length; ++i){
                if (countries[i].id != except){
                    result.add(countries[i]);
                }
            }
        }
        else{
            ArrayList<Integer> except = new ArrayList<>();
            for (int i = 0; i < alliances.get(countries[yourCountryId].idOfAlliance).countries.size(); ++i){
                except.add(alliances.get(countries[yourCountryId].idOfAlliance).countries.get(i));
            }
            for (int i = 0; i < countries.length; ++i){
                if (!except.contains(countries[i].id)){
                    result.add(countries[i]);
                }
            }
        }
        return result;
    }

    public void check(){
        CountryOnline country = countries[yourCountryId];
        double[] chars = {
                country.moneyStatus, country.armyStatus, country.businessStatus, country.workerStatus, country.foodStatus
        };
        for(int i = 0; i < chars.length; ++i){
            if (chars[i] <= 0){
                isGameOver = true;
            }
            if (chars[i] >= 0.85){
                isGameOver = true;
            }
        }
    }

    public void setWeek(){
        week = new Week(storage);
    }
}
