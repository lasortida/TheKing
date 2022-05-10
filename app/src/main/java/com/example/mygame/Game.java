package com.example.mygame;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private double bourgeoisMood = 0.5;
    private double workersMood = 0.5;
    public static double moneyStatus = 0.5;
    private double armyMood = 0.5;
    private double foodStatus = 0.5;

    private Country[] listOfCountries;

    public Storage storage;

    public ArrayList<Alliance> activeAlliances = new ArrayList<>();
    public ArrayList<Alliance> newAlliances = new ArrayList<>();

    private Week week;
    private static int numberOfWeek;
    private boolean isWeekDone;

    public boolean relationship;
    public boolean isStoryRelationship;
    private Relationship relationshipOfCountry;
    public static boolean isImproveNeed;
    public static int countryIdImprovement;

    private String news;

    public Game(Storage storage){
        this.storage = storage;
        this.listOfCountries = storage.listOfCountries;
        numberOfWeek = 4;
    }

    public Country[] getCountries(){
        return listOfCountries;
    }

    public void takeChanges(Effect effect) {
        bourgeoisMood += effect.getBourgeoisMoodChange();
        workersMood += effect.getWorkersMoodChange();
        moneyStatus += effect.getMoneyStatusChange();
        armyMood += effect.getArmyMoodChange();
        foodStatus += effect.getFoodStatusChange();
    }

    public Relationship getRelationshipOfCountry(){
        return relationshipOfCountry;
    }

    public void setRelationshipChanging(int idOfCountry, int value){
        listOfCountries[idOfCountry + 1].setRelationshipValueChanging(value);
        if (isStoryRelationship){
            if (value > 0){

            }
        }
    }

    public void setWeek(){
        news = "";
        week = new Week(storage);
        numberOfWeek++;
        isWeekDone = false;

        if (numberOfWeek >= 3){   // поменять!!!
            if (numberOfWeek == 5){
                setStoryRelationship(0);
            }
            else {
                setRelationShip();
            }
        }

        if (isImproveNeed){
            double value = Math.random();
            if (value >= 0.35){
                listOfCountries[countryIdImprovement].setRelationshipValueChanging(20);
                news = "У МИДа получилось договориться! Уровень взаимоотношений вырос на 20%";
            }
            else {
                news = "К сожалению, у МИДа не получилось договориться и уровень взаимоотношений остался на прежнем уровне!";
            }
            isImproveNeed = false;
        }
        else if(numberOfWeek != 4){
            setRandomSimpleNews();
        }
        if (numberOfWeek == 4){
            news += storage.storyNews[0];
        }

        checkNewAlliances();
    }

    public void setRelationShip(){
        double value = Math.random();
        if (value >= 0.5){  // поменять!!!
            relationship = true;
            relationshipOfCountry = storage.getRandomRelationship();
        }
        else{
            relationship = false;
        }
    }

    public void setStoryRelationship(int id){
        relationship = true;
        relationshipOfCountry = storage.storyRelationhips[id];
        isStoryRelationship = true;
        newAlliances.add(new Alliance(listOfCountries[relationshipOfCountry.countryId + 1], "Альянс Догсленда"));
    }

    public String[] getListOfCountries(){
        String[] result = new String[listOfCountries.length - 1];
        for (Country c: listOfCountries) {

        }
    }

    public void checkNewAlliances(){
        for (int i = 0; i < newAlliances.size(); ++i){
            Alliance a = newAlliances.get(i);
            if (a.countries.size() != 0){
                activeAlliances.add(a);
                newAlliances.remove(i);
            }
        }
    }

    public String getTextOfRelationship(){
        return relationshipOfCountry.getText();
    }

    public String getLeftRelationshipAnswer(){
        return relationshipOfCountry.answers[0];
    }

    public String getRightRelationshipAnswer(){
        return relationshipOfCountry.answers[1];
    }

    public String getEndOfGame(){
        String result = "";
        double[] array = {bourgeoisMood, workersMood, moneyStatus, armyMood, foodStatus};
        boolean downOrUp = false;
        int index = 0;
        for (int i = 0; i < array.length; ++i){
            if (array[i] <= 0){
                index = i;
                downOrUp = false;
                break;
            }
            if (array[i] >= 85){
                index = i;
                downOrUp = true;
                break;
            }
        }
        if (!downOrUp){
            int number = (int) (Math.random() * storage.gameOverZero[index].length);
            result = storage.gameOverZero[index][number];
        }
        if (downOrUp){
            int number = (int) (Math.random() * storage.gameOverFull[index].length);
            result = storage.gameOverFull[index][number];
        }
        return result;
    }

    public void setRandomSimpleNews(){
        int number = (int) (Math.random() * storage.simpleNews.length);
        while (storage.simpleNews[number] == null){
            number = (int) (Math.random() * storage.simpleNews.length);
        }
        String result = storage.simpleNews[number];
        storage.setNewsNull(number);
        this.news = result;
    }

    public String getNews(){
        return news;
    }

    public boolean isWeekDone(){
        return isWeekDone;
    }

    public void weekFinish(){
        isWeekDone = true;
    }

    public Week getWeek(){
        return week;
    }

    public int getNumberOfWeek(){
        return numberOfWeek;
    }

    public double getBourgeoisMood(){
        return bourgeoisMood;
    }

    public double getWorkersMood(){
        return workersMood;
    }

    public double getMoneyStatus(){
        return moneyStatus;
    }

    public double getArmyMood(){
        return armyMood;
    }

    public double getFoodStatus(){
        return foodStatus;
    }

    public String[] whoIsLoser(){
        int count = 0;
        String[] moods = new String[5];
        if (bourgeoisMood <= 0 || bourgeoisMood >= 0.85){
            moods[0] = "bourgeois";
            count += 1;
        }
        if (workersMood <= 0 || workersMood >= 0.85){
            moods[1] = "workers";
            count += 1;
        }
        if (moneyStatus <= 0 || moneyStatus >= 0.85){
            moods[2] = "moneys";
            count += 1;
        }
        if (armyMood <= 0 || armyMood >= 0.85){
            moods[3] = "army";
            count += 1;
        }
        if (foodStatus <= 0 || foodStatus >= 0.85){
            moods[4] = "food";
            count += 1;
        }
        if (count == 0){
            return null;
        }
        else{
            return moods;
        }
    }

    public boolean isGameOver(){
        if (bourgeoisMood <= 0 || workersMood <= 0 || moneyStatus <= 0 || armyMood <= 0 || foodStatus <= 0){
            return true;
        }
        if (bourgeoisMood >= 0.85 || workersMood >= 0.85 || moneyStatus >= 0.85 || armyMood >= 0.85 || foodStatus >= 0.85){
            return true;
        }
        return false;
    }

    public int minResultIndex(int exception){
        double[] moods = { bourgeoisMood, workersMood, moneyStatus, armyMood, foodStatus };
        double min = 1;
        int minIndex = -1;
        for (int i = 0; i < moods.length; ++i){
            if (moods[i] < min && i != exception){
                min = moods[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public int minResultIndex(){
        return minResultIndex(-1);
    }

    public void saveData(SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        double[] moods = { bourgeoisMood, workersMood, moneyStatus, armyMood, foodStatus };
        String[] names = {"bourgeoisMood", "workersMood", "moneyStatus", "armyMood", "foodStatus"};
        for (int i = 0; i < names.length; ++i){
            editor.putFloat(names[i], (float) moods[i]);
        }
        editor.putInt("numberOfWeek", numberOfWeek);
        editor.putBoolean("isWeekDone", isWeekDone);
        editor.putString("news", news);
        editor.apply();
    }

    public void loadData(SharedPreferences sp){
        bourgeoisMood = (double) sp.getFloat("bourgeoisMood", -1);
        workersMood = (double) sp.getFloat("workersMood", -1);
        moneyStatus = (double) sp.getFloat("moneyStatus", -1);
        armyMood = (double) sp.getFloat("armyMood", -1);
        foodStatus = (double) sp.getFloat("foodStatus", -1);
        numberOfWeek = sp.getInt("numberOfWeek", -1);
        isWeekDone = sp.getBoolean("isWeekDone", false);
        news = sp.getString("news", "ERROR");
    }
}