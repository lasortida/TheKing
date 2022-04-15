package com.example.mygame;

public class Day {
    private Act[] acts = new Act[3];

    public Day(Storage storage){
        for (int i = 0; i < 3; ++i){
            int number = (int) (Math.random() * storage.acts.length);
            while (storage.getAct(number) == null){
                number = (int) (Math.random() * storage.acts.length);
            }
            acts[i] = storage.getAct(number);
            storage.setActsNull(number);
        }
    }

    public Act[] getActs(){
        return acts;
    }
}
