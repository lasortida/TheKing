package com.example.mygame;

public class Effect {
    private double bourgeoisMoodChange;
    private double workersMoodChange;
    private double moneyStatusChange;
    private double armyMoodChange;
    private double foodStatusChange;
    private boolean canI;

    public Effect(double bourgeoisMoodChange, double workersMoodChange, double moneyStatusChange, double armyMoodChange, double foodStatusChange){
        this.bourgeoisMoodChange = bourgeoisMoodChange;
        this.workersMoodChange = workersMoodChange;
        this.moneyStatusChange = moneyStatusChange;
        this.armyMoodChange = armyMoodChange;
        this.foodStatusChange = foodStatusChange;
    }

    public boolean isCanI(){
        return canI;
    }

    public boolean isForId(int id){
        double[] array = { bourgeoisMoodChange, workersMoodChange, moneyStatusChange, armyMoodChange, foodStatusChange };
        if (array[id] < 0){
            return true;
        }
        else {
            return false;
        }
    }

    public Effect(boolean canI){
        this.canI = canI;
    }

    public double getBourgeoisMoodChange(){
        return bourgeoisMoodChange;
    }
    public double getWorkersMoodChange(){
        return workersMoodChange;
    }
    public double getMoneyStatusChange(){
        return moneyStatusChange;
    }
    public double getArmyMoodChange(){
        return armyMoodChange;
    }
    public double getFoodStatusChange(){
        return foodStatusChange;
    }
}
