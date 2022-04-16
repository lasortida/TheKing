package com.example.mygame;

import java.io.Serializable;

public class Week implements Serializable {
    private Act[] acts = new Act[3];

    public Week(Storage storage){
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
