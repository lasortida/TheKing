package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mygame.MainActivity;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.R;

public class MapActivityOnline extends AppCompatActivity {

    public ImageView curtain;
    public View view;
    public ImageView map;
    public ImageView marker;
    public TextView title;
    public TextView newsText;
    public TextView numberOfWeek;
    public Thread curtainAnimation;
    public Thread numberOfWeekAnimation;
    public TextView signature;

    public boolean isWorkCurtain;
    public boolean isWorkNumber;
    public boolean directionOfNumber;
    public boolean continuade;

    GameOnline game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_online);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
                            marker.setVisibility(View.INVISIBLE);
                            runOnUiThread(new Thread(){
                                @Override
                                public void run() {
                                    view.setBackgroundResource(R.drawable.wallpaper_text);
                                    title.setVisibility(View.VISIBLE);
                                    newsText.setVisibility(View.VISIBLE);
                                }
                            });
                            curtain.setVisibility(View.INVISIBLE);


                            continuade = true;
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
        marker = findViewById(R.id.marker);
        view = findViewById(R.id.screen);
        signature= findViewById(R.id.textViewTask);

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
            int markerId = args.getInt("RESOURCES");
            marker.setImageResource(markerId);
            showOnlyMap();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        else{
            game = (GameOnline) args.getSerializable("GAME");
            int number = game.numberOfWeek;
            marker.setImageResource(game.countries[game.yourCountryId].idOfMarker);
            showStart(number);

            newsText.setText(game.news);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (continuade){
                        if (game.end){
                            startActivity(new Intent(MapActivityOnline.this, MainActivity.class));
                        }
                        else{
                            numberOfWeekAnimation.interrupt();
                            curtainAnimation.interrupt();
                            startActivity(new Intent(MapActivityOnline.this, GovernMenuActivityOnline.class).putExtra("GAME", game));
                        }
                    }
                }
            });
        }
    }

    public void showStart(int number){
        curtain.setVisibility(View.VISIBLE);
        numberOfWeek.setAlpha(0);
        numberOfWeek.setVisibility(View.VISIBLE);

        numberOfWeek.setText("Неделя: " + number);

        isWorkCurtain = true;
    }

    public void showOnlyMap(){
        curtain.setVisibility(View.INVISIBLE);
        signature.setText("Нажмите, чтобы свернуть...");
    }
}