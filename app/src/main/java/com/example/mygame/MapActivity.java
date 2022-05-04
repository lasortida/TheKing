package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MapActivity extends AppCompatActivity {

    public ImageView curtain;
    public View view;
    public ImageView map;
    public TextView title;
    public TextView newsText;
    public TextView numberOfWeek;
    public Thread curtainAnimation;
    public Thread numberOfWeekAnimation;
    public TextView signature;
    public TextView textViewAdd;
    public Button buttonLeft;
    public Button buttonRight;

    public boolean isWorkCurtain;
    public boolean isWorkNumber;
    public boolean directionOfNumber;
    public boolean continuade;

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        curtainAnimation = new Thread(){
            //false - скрыть шторку
            //true - проявить шторку

            @Override
            public void run() {
                while(true){
                    if (isWorkCurtain){
                        isWorkNumber = true;
                        directionOfNumber = true;
                        try {
                            Thread.sleep(2000);
                            directionOfNumber = false;
                            isWorkNumber = true;
                            Thread.sleep(2000);
                            for (int i = 255; i >= 0; --i){
                                curtain.setImageAlpha(i);
                                Thread.sleep(6);
                            }
                            numberOfWeek.setVisibility(View.INVISIBLE);
                            for (int i = 0; i < 100; ++i){
                                Thread.sleep(20);
                            }
                            curtain.setImageAlpha(0);
                            for (int i = 0; i < 256; ++i){
                                curtain.setImageAlpha(i);
                                Thread.sleep(6);
                            }
                            map.setVisibility(View.INVISIBLE);
                            runOnUiThread(new Thread(){
                                @Override
                                public void run() {
                                    view.setBackgroundResource(R.drawable.wallpaper_text);
                                    title.setVisibility(View.VISIBLE);
                                    newsText.setVisibility(View.VISIBLE);
                                }
                            });
                            curtain.setVisibility(View.INVISIBLE);

                            if (game.relationship){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textViewAdd.setText(game.getTextOfRelationship());
                                        buttonLeft.setText(game.getLeftRelationshipAnswer());
                                        buttonRight.setText(game.getRightRelationshipAnswer());
                                        textViewAdd.setVisibility(View.VISIBLE);
                                        buttonRight.setVisibility(View.VISIBLE);
                                        buttonLeft.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                            else {
                                continuade = true;
                            }
                            isWorkCurtain = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        };

        numberOfWeekAnimation = new Thread(){
            //false - скрыть слова
            //true - проявить слова
            @Override
            public void run() {
                while(true){
                    if (isWorkNumber && !directionOfNumber){
                        float alpha = 1;
                        for (int i = 400; i >= 0; --i){
                            numberOfWeek.setAlpha(alpha);
                            try {
                                Thread.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            alpha -= 0.0025;
                        }
                        isWorkNumber = false;
                    }
                    if(isWorkNumber && directionOfNumber){
                        float alpha = 0;
                        for (int i = 0; i < 400; ++i){
                            numberOfWeek.setAlpha(alpha);
                            try {
                                Thread.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            alpha += 0.0025;
                        }
                        isWorkNumber = false;
                    }
                }
            }
        };

        curtain = findViewById(R.id.curtain);
        title = findViewById(R.id.textViewTitle);
        newsText = findViewById(R.id.textViewText);
        numberOfWeek = findViewById(R.id.textViewNumberOfWeek);
        map = findViewById(R.id.imageViewMap);
        view = findViewById(R.id.screen);
        signature= findViewById(R.id.textViewTask);
        textViewAdd = findViewById(R.id.textViewAdd);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Relationship relationship = game.getRelationshipOfCountry();
                int countryId = relationship.getIdOfCountry();
                game.setRelationshipChanging(countryId, (int)(relationship.impact1 * 100));
                readyToGo();
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Relationship relationship = game.getRelationshipOfCountry();
                int countryId = relationship.getIdOfCountry();
                game.setRelationshipChanging(countryId, (int)(relationship.impact2 * 100));
                readyToGo();
            }
        });

        Bundle args = getIntent().getExtras();

        int value;

        try{
            value = args.getInt("INTERMEDIATE");
        } catch (NullPointerException e){
            e.printStackTrace();
            value = 0;
        }
        numberOfWeekAnimation.start();
        curtainAnimation.start();
        if (value == 1){
            showOnlyMap();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        else{
            game = (Game) args.getSerializable("GAME");
            int number = game.getNumberOfWeek();
            showStart(number);

            if (game.isGameOver()){
                newsText.setText(game.getEndOfGame());
            }
            else{
                newsText.setText(game.getNews());
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (continuade){
                        numberOfWeekAnimation.interrupt();
                        curtainAnimation.interrupt();

                        if (game.isGameOver()){
                            startActivity(new Intent(MapActivity.this, MainActivity.class));
                        }
                        else{
                            startActivity(new Intent(MapActivity.this, GovernMenuActivity.class).putExtra("GAME", game));
                        }
                    }
                }
            });
        }
    }

    public void readyToGo(){
        buttonLeft.setVisibility(View.INVISIBLE);
        buttonRight.setVisibility(View.INVISIBLE);
        textViewAdd.setText("ГОТОВО!");
        continuade = true;
    }


    public void showStart(int number){
        curtain.setVisibility(View.VISIBLE);
        numberOfWeek.setAlpha(0);
        numberOfWeek.setVisibility(View.VISIBLE);

        if (game.isGameOver()){
            number++;
            numberOfWeek.setText("Неделя: " + number + "\n Конец игры!");
        }
        else{
            numberOfWeek.setText("Неделя: " + number);
        }

        isWorkCurtain = true;
    }

    public void showOnlyMap(){
        curtain.setVisibility(View.INVISIBLE);
        signature.setText("Нажмите, чтобы свернуть...");
    }

}