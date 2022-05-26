package com.example.mygame.OnlineMode.Classes;

import com.example.mygame.Country;
import com.example.mygame.Effect;
import com.example.mygame.Storage;
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

    public Post post;

    public int[] tradeWith;
    public int[] tradeAway;
    public int[] tradeToMe;
    public boolean[] isHandle;

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

        CountryOnline country = countries[yourCountryId];
        double[] chars = {
                country.moneyStatus, country.armyStatus, country.businessStatus, country.workerStatus, country.foodStatus
        };
        for(int i = 0; i < chars.length; ++i){
            if (chars[i] <= 0 || chars[i] >= 0.85){
                isGameOver = true;
                break;
            }
        }
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
        time = 90000;
        post = new Post();
        tradeWith = null;
        tradeAway = null;
        tradeToMe = null;
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
                except.add(alliances.get(countries[yourCountryId].idOfAlliance).countries.get(i).id);
            }
            for (int i = 0; i < countries.length; ++i){
                if (!except.contains(countries[i].id)){
                    result.add(countries[i]);
                }
            }
        }
        return result;
    }

    public void setWeek(){
        week = new Week(storage);
    }
}
