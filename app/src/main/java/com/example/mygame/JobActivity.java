package com.example.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JobActivity extends AppCompatActivity implements View.OnTouchListener {

    private boolean flag = false;
    private Game game;
    private Week week;
    private View frameView;
    private View mainView;
    private TextView textViewLeft, textViewRight, textViewTask;
    private ImageWithParams coinImage, bombImage, tieImage, anvilImage, breadImage;
    private ImageView coinUnder, bombUnder, tieUnder, anvilUnder, breadUnder;
    private double prevCoin = 0.6, prevBomb = 0.6, prevTie = 0.6, prevAnvil = 0.6, prevBread = 0.6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        textViewTask = findViewById(R.id.textViewTask);
        textViewTask.setVisibility(View.VISIBLE);
        frameView = findViewById(R.id.frame);
        frameView.setOnTouchListener(this);

        Bundle arguments = getIntent().getExtras();
        game = (Game) arguments.getSerializable("GAME");
        week = game.getWeek();

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
        setImagesWithGame(game);
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
                    game.weekFinish();
                    startActivity(new Intent(JobActivity.this, GovernMenuActivity.class).putExtra("GAME", game));
                    finish();
                }
                if (v.getX() < 0  - mainView.getWidth() / 3 && numberOfActInADay != 0){
                    game.takeChanges(week.getActs()[numberOfActInADay - 1].getAnswer(0).getEffect());
                    flag = true;
                }
                if (v.getX() > mainView.getWidth() / 3 && numberOfActInADay != 0){
                    game.takeChanges(week.getActs()[numberOfActInADay - 1].getAnswer(1).getEffect());
                    flag = true;
                }
                if (numberOfActInADay == 3 || game.isGameOver()){
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
                    double nowCoin = game.getMoneyStatus();
                    double nowBomb = game.getArmyMood();
                    double nowTie = game.getBourgeoisMood();
                    double nowAnvil = game.getWorkersMood();
                    double nowBread = game.getFoodStatus();
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

    public void setImagesWithGame(Game game){
        coinImage.setNewImage(game.getMoneyStatus());
        bombImage.setNewImage(game.getArmyMood());
        tieImage.setNewImage(game.getBourgeoisMood());
        anvilImage.setNewImage(game.getWorkersMood());
        breadImage.setNewImage(game.getFoodStatus());
    }
}