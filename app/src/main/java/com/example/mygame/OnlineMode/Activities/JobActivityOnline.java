package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygame.ImageWithParams;
import com.example.mygame.OnlineMode.Classes.CountryOnline;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.OnlineMode.ForServer.Country;
import com.example.mygame.OnlineMode.ForServer.Game;
import com.example.mygame.R;
import com.example.mygame.Week;

public class JobActivityOnline extends AppCompatActivity implements View.OnTouchListener{

    private boolean flag = false;
    private Game game;
    private Week week;
    private View frameView;
    private View mainView;
    private TextView textViewLeft, textViewRight, textViewTask;
    private ImageWithParams coinImage, bombImage, tieImage, anvilImage, breadImage;
    private ImageView coinUnder, bombUnder, tieUnder, anvilUnder, breadUnder;
    private double prevCoin = 0.6, prevBomb = 0.6, prevTie = 0.6, prevAnvil = 0.6, prevBread = 0.6;

    CountDownTimer timer;
    boolean timerStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_online);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textViewTask = findViewById(R.id.textViewTask);
        textViewTask.setVisibility(View.VISIBLE);
        frameView = findViewById(R.id.frame);
        frameView.setOnTouchListener(this);

        Bundle arguments = getIntent().getExtras();
        game = (Game) arguments.getSerializable("GAME");
        week = game.week;

        mainView = findViewById(R.id.fullscreen);
        textViewLeft = findViewById(R.id.textLeft);
        textViewRight = findViewById(R.id.textRight);
        coinImage = new ImageWithParams(findViewById(R.id.imageViewCoin), 0);
        bombImage = new ImageWithParams(findViewById(R.id.imageViewBomb), 1);
        tieImage = new ImageWithParams(findViewById(R.id.imageViewTie), 2);
        anvilImage = new ImageWithParams(findViewById(R.id.imageViewAnvil), 3);
        breadImage = new ImageWithParams(findViewById(R.id.imageViewBread), 4);
        coinUnder = findViewById(R.id.imageViewCoinUnderwrite);
        bombUnder = findViewById(R.id.imageViewBombUnderwrite);
        tieUnder = findViewById(R.id.imageViewTieUnderwrite);
        anvilUnder = findViewById(R.id.imageViewAnvilUnderwrite);
        breadUnder = findViewById(R.id.imageViewBreadUnderwrite);
        thread.start();
        setImagesWithGame(game.user.country);
    }

    private int numberOfDay = 0;
    private int numberOfActInADay = 0;
    private int id = -1;
    private float dx;
    private int endOfGame = 0; // исправить
    private boolean end;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        textViewLeft.setVisibility(View.VISIBLE);
        textViewRight.setVisibility(View.VISIBLE);
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            dx = v.getX() - event.getRawX();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE){
            v.setX(event.getRawX() + dx);
        }
        if (event.getAction() == MotionEvent.ACTION_UP){
            textViewLeft.setVisibility(View.INVISIBLE);
            textViewRight.setVisibility(View.INVISIBLE);
            if (v.getX() < 0 - mainView.getWidth() / 3 || v.getX() > mainView.getWidth() / 3){
                if (end){
                    v.setX(0);
                    game.isJobDone = true;
                    timerStop = true;
                    startActivity(new Intent(JobActivityOnline.this, GovernMenuActivityOnline.class).putExtra("GAME", game));
                }
                if (v.getX() < 0  - mainView.getWidth() / 3 && numberOfActInADay != 0){
                    game.takeChanges(week.getActs()[numberOfActInADay - 1].getAnswer(0).getEffect());
                    flag = true;
                }
                if (v.getX() > mainView.getWidth() / 3 && numberOfActInADay != 0){
                    game.takeChanges(week.getActs()[numberOfActInADay - 1].getAnswer(1).getEffect());
                    flag = true;
                }
                if (numberOfActInADay == 3 || game.isGameOver){
                    textViewTask.setText("Приём окончен!");
                    textViewLeft.setText("Далее");
                    textViewRight.setText("Далее");
                    v.setX(0);
                    end = true;
                    return true;

                }
                textViewTask.setText(week.getActs()[numberOfActInADay].getText());
                textViewLeft.setText(week.getActs()[numberOfActInADay].getAnswer(0).getText());
                textViewRight.setText(week.getActs()[numberOfActInADay].getAnswer(1).getText());
                numberOfActInADay++;
                v.setX(0);
            }
            else {
                v.setX(0);
            }
        }
        return true;
    }

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                if (flag){
                    flag = false;
                    float nowCoin = game.user.country.moneyStatus;
                    float nowBomb = game.user.country.armyStatus;
                    float nowTie = game.user.country.businessStatus;
                    float nowAnvil = game.user.country.workerStatus;
                    float nowBread = game.user.country.foodStatus;
                    boolean isChangeCoin = false, isChangeBomb = false, isChangeTie = false, isChangeAnvil = false, isChangeBread = false;
                    if (coinImage.isStateChange(nowCoin)){
                        isChangeCoin = true;
                    }
                    if (bombImage.isStateChange(nowBomb)){
                        isChangeBomb = true;
                    }
                    if (tieImage.isStateChange(nowTie)){
                        isChangeTie = true;
                    }
                    if (anvilImage.isStateChange(nowAnvil)){
                        isChangeAnvil = true;
                    }
                    if (breadImage.isStateChange(nowBread)){
                        isChangeBread = true;
                    }
                    try{
                        if (isChangeCoin) { coinUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                coinUnder.setVisibility(View.VISIBLE);
                            }
                        }); }
                        if (isChangeBomb) { bombUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                bombUnder.setVisibility(View.VISIBLE);
                            }
                        }); }
                        if (isChangeTie) { tieUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                tieUnder.setVisibility(View.VISIBLE);
                            }
                        }); }
                        if (isChangeAnvil) { anvilUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                anvilUnder.setVisibility(View.VISIBLE);
                            }
                        }); }
                        if (isChangeBread) { breadUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                breadUnder.setVisibility(View.VISIBLE);
                            }
                        }); }

                        for (int i = 255; i >= 0; --i){
                            if (isChangeCoin) { coinImage.setVis(i); }
                            if (isChangeBomb) { bombImage.setVis(i); }
                            if (isChangeTie) { tieImage.setVis(i); }
                            if (isChangeAnvil) { anvilImage.setVis(i); }
                            if (isChangeBread) { breadImage.setVis(i); }
                            Thread.sleep(1);
                        }
                        if (isChangeCoin) { coinImage.setNewImage(nowCoin); }
                        if (isChangeBomb) { bombImage.setNewImage(nowBomb); }
                        if (isChangeTie) { tieImage.setNewImage(nowTie); }
                        if (isChangeAnvil) { anvilImage.setNewImage(nowAnvil); }
                        if (isChangeBread) { breadImage.setNewImage(nowBread); }
                        if (flag) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    coinUnder.setVisibility(View.INVISIBLE);
                                    bombUnder.setVisibility(View.INVISIBLE);
                                    tieUnder.setVisibility(View.INVISIBLE);
                                    anvilUnder.setVisibility(View.INVISIBLE);
                                    breadUnder.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                        for (int i = 0; i < 256; ++i){
                            if (isChangeCoin) { coinImage.setVis(i); }
                            if (isChangeBomb) { bombImage.setVis(i); }
                            if (isChangeTie) { tieImage.setVis(i); }
                            if (isChangeAnvil) { anvilImage.setVis(i); }
                            if (isChangeBread) { breadImage.setVis(i); }
                            Thread.sleep(1);
                        }
                        if (flag) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    coinUnder.setVisibility(View.INVISIBLE);
                                    bombUnder.setVisibility(View.INVISIBLE);
                                    tieUnder.setVisibility(View.INVISIBLE);
                                    anvilUnder.setVisibility(View.INVISIBLE);
                                    breadUnder.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                        for (int i = 255; i >= 0; --i){
                            if (isChangeCoin) { coinImage.setVis(i); }
                            if (isChangeBomb) { bombImage.setVis(i); }
                            if (isChangeTie) { tieImage.setVis(i); }
                            if (isChangeAnvil) { anvilImage.setVis(i); }
                            if (isChangeBread) { breadImage.setVis(i); }
                            Thread.sleep(1);
                        }
                        if (flag) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    coinUnder.setVisibility(View.INVISIBLE);
                                    bombUnder.setVisibility(View.INVISIBLE);
                                    tieUnder.setVisibility(View.INVISIBLE);
                                    anvilUnder.setVisibility(View.INVISIBLE);
                                    breadUnder.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                        for (int i = 0; i < 256; ++i){
                            if (isChangeCoin) { coinImage.setVis(i); }
                            if (isChangeBomb) { bombImage.setVis(i); }
                            if (isChangeTie) { tieImage.setVis(i); }
                            if (isChangeAnvil) { anvilImage.setVis(i); }
                            if (isChangeBread) { breadImage.setVis(i); }
                            Thread.sleep(1
                            );
                        }
                        if (flag){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    coinUnder.setVisibility(View.INVISIBLE);
                                    bombUnder.setVisibility(View.INVISIBLE);
                                    tieUnder.setVisibility(View.INVISIBLE);
                                    anvilUnder.setVisibility(View.INVISIBLE);
                                    breadUnder.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                        if (isChangeCoin) { coinUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                coinUnder.setVisibility(View.INVISIBLE);
                            }
                        }); }
                        if (isChangeBomb) { bombUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                bombUnder.setVisibility(View.INVISIBLE);
                            }
                        }); }
                        if (isChangeTie) { tieUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                tieUnder.setVisibility(View.INVISIBLE);
                            }
                        }); }
                        if (isChangeAnvil) { anvilUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                anvilUnder.setVisibility(View.INVISIBLE);
                            }
                        }); }
                        if (isChangeBread) { breadUnder.post(new Runnable() {
                            @Override
                            public void run() {
                                breadUnder.setVisibility(View.INVISIBLE);
                            }
                        }); }
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    });

    public void setImagesWithGame(Country country){
        coinImage.setNewImage(country.moneyStatus);
        bombImage.setNewImage(country.armyStatus);
        tieImage.setNewImage(country.businessStatus);
        anvilImage.setNewImage(country.workerStatus);
        breadImage.setNewImage(country.foodStatus);
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, "Для выхода завершите рассматривать обращения!", Toast.LENGTH_SHORT);
        toast.show();
    }

}