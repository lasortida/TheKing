package com.example.mygame;

import java.io.Serializable;

public class Game implements Serializable {

    private double bourgeoisMood = 0.5;
    private double workersMood = 0.5;
    private double moneyStatus = 0.5;
    private double armyMood = 0.5;
    private double foodStatus = 0.5;

    public Storage storage;

    private Week week;
    private static int numberOfWeek = 0;

    private String news;

    public Game(Storage storage){
        this.storage = storage;
    }

    public void takeChanges(Effect effect) {
        bourgeoisMood += effect.getBourgeoisMoodChange();
        workersMood += effect.getWorkersMoodChange();
        moneyStatus += effect.getMoneyStatusChange();
        armyMood += effect.getArmyMoodChange();
        foodStatus += effect.getFoodStatusChange();
    }

    public void setNewWeek(){
        week = new Week(storage);
        numberOfWeek++;
        setNews();
    }

    public void setNews(){
        int random = (int) Math.random() * storage.sampleNews.length;
        while (storage.sampleNews[random] == null){
            random = (int) Math.random() * storage.sampleNews.length;
        }
        this.news = storage.sampleNews[random];
        storage.setNewsNull(random);
    }

    public String getNews(){
        return news;
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
            return false;
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
}