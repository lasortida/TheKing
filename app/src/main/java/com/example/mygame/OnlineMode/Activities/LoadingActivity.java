package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mygame.MapActivity;
import com.example.mygame.OnlineMode.Classes.Format;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.OnlineMode.GameService;
import com.example.mygame.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadingActivity extends AppCompatActivity {

    String idOfRoom;
    int userCode;
    boolean start;
    String personName;
    boolean block = false;

    int flag = 0;
    Retrofit retrofit;
    GameService service;
    TextView view;
    TextView timer;

    CountDownTimer downTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Bundle args = getIntent().getExtras();
        personName = (String) args.get("userName");
        view = findViewById(R.id.textViewTitle);
        timer = findViewById(R.id.textViewTimer);
        view.setText("Поиск сервера");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(GameService.address)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GameService.class);

        thread.start();
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            while(true){
                if (flag == 0 && !block){
                    block = true;
                    Call<Format> call = service.getIDOfRoom();
                    call.enqueue(new Callback<Format>() {
                        @Override
                        public void onResponse(Call<Format> call, Response<Format> response) {
                            Format format = response.body();
                            idOfRoom = format.id;
                            view.setText("Сервер найден!");
                            flag = 1;
                            block = false;
                        }

                        @Override
                        public void onFailure(Call<Format> call, Throwable t) {
                            Log.d("fail", "fail");
                            Log.d("fail", t.getMessage());
                            block = false;
                        }
                    });
                }
                if (flag == 1 && !block){
                    block = true;
                    Call<Format> call = service.getUserCode(idOfRoom);
                    call.enqueue(new Callback<Format>() {
                        @Override
                        public void onResponse(Call<Format> call, Response<Format> response) {
                            Format format = response.body();
                            if (!format.error){
                                userCode = format.userCode;
                                view.setText("Ожидание игроков!");
                                flag = 2;
                            }
                            else{
                                flag = 0;
                                view.setText("Ошибка! Поиск сервера!");
                            }
                            block = false;
                        }

                        @Override
                        public void onFailure(Call<Format> call, Throwable t) {
                            Log.d("fail", "fail2");
                            Log.d("fail", t.getMessage());
                            block = false;
                        }
                    });
                }
                if (flag == 2 && !block){
                    block = true;
                    Call<Format> call = service.setUserName(idOfRoom, userCode, personName);
                    call.enqueue(new Callback<Format>() {
                        @Override
                        public void onResponse(Call<Format> call, Response<Format> response) {
                            Format format = response.body();
                            if (!format.error){
                                flag = 3;
                            }
                            else{
                                view.setText("Ошибка! Поиск сервера");
                                flag = 0;
                            }
                            block = false;
                        }

                        @Override
                        public void onFailure(Call<Format> call, Throwable t) {
                            Log.d("fail", "fail3");
                            Log.d("fail", t.getMessage());
                            block = false;
                        }
                    });
                }
                if (flag == 3 && !block){
                    block = true;
                    Call<Format> call = service.getStartStatus(idOfRoom, userCode);
                    call.enqueue(new Callback<Format>() {
                        @Override
                        public void onResponse(Call<Format> call, Response<Format> response) {
                            Format format = response.body();
                            if (!format.error){
                                if (format.timerStart){
                                    timer.setVisibility(View.VISIBLE);
                                    int reminder = format.timerReminder;
                                    timer.setText("Осталось: " + reminder);
                                }
                                if(format.start){
                                    flag = 4;
                                }
                                else{
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            else{
                                view.setText("Ошибка! Поиск сервера!");
                                flag = 0;
                            }
                            block = false;
                        }

                        @Override
                        public void onFailure(Call<Format> call, Throwable t) {
                            Log.d("fail", "fail4");
                            Log.d("fail", t.getMessage());
                            block = false;
                        }
                    });
                }
                if (flag == 4 && !block){
                    block = true;
                    Call<Format> call = service.getInitialStatus(idOfRoom, userCode);
                    call.enqueue(new Callback<Format>() {
                        @Override
                        public void onResponse(Call<Format> call, Response<Format> response) {
                            Format format = response.body();
                            if (!format.error){
                                view.setText(R.string.game_start);
                                flag = 5;
                                GameOnline game = format.getInitialGameOnline();
                                game.numberOfWeek = 1;
                                game.yourUserCode = userCode;
                                startActivity(new Intent(LoadingActivity.this, MapActivityOnline.class).putExtra("GAME", game));
                            }
                            else{
                                view.setText("Ошибка! Поиск сервера!");
                                flag = 0;
                            }
                            block = false;
                        }

                        @Override
                        public void onFailure(Call<Format> call, Throwable t) {
                            Log.d("idk", "fail");
                            Log.d("idk", t.getMessage());
                            block = false;
                        }
                    });
                }
            }
        }
    };
}