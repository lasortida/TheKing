package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mygame.OnlineMode.Classes.Format;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.OnlineMode.GameService;
import com.example.mygame.R;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestActivity extends AppCompatActivity {

    TextView title;
    TextView action;

    Retrofit retrofit;
    GameService service;
    int flag = 0;
    boolean block;
    GameOnline game;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        title = findViewById(R.id.textViewTitle);
        action = findViewById(R.id.textViewAction);

        Bundle args = getIntent().getExtras();
        game = (GameOnline) args.getSerializable("GAME");
        int range = args.getInt("RANGE");

        if (range == 0){
            title.setText("Время вышло!");
        }
        if (range == 1){
            title.setText("Неделя закончилась!");
        }

        game.post.moneyStatus = game.countries[game.yourCountryId].moneyStatus;
        game.post.armyStatus = game.countries[game.yourCountryId].armyStatus;
        game.post.businessStatus = game.countries[game.yourCountryId].businessStatus;
        game.post.workerStatus = game.countries[game.yourCountryId].workerStatus;
        game.post.foodStatus = game.countries[game.yourCountryId].foodStatus;

        if (game.numberOfWeek - 2 == game.numberOfWeek && game.isGameLow){
            game.post.end = true;
        }
        if (game.isGameFull){
            game.post.end = true;
        }

        Gson gson = new Gson();
        jsonString = gson.toJson(game.post);

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
            while (true){
                if (flag == 0 && !block){
                    block = true;
                    Call<Format> call = service.sendData(game.idOfRoom, game.yourUserCode, jsonString);
                    call.enqueue(new Callback<Format>() {
                        @Override
                        public void onResponse(Call<Format> call, Response<Format> response) {
                            Log.d("sendData", "success");
                            Format format = response.body();
                            if (!format.error){
                                flag = 1;
                            }
                            block = false;
                        }
                        @Override
                        public void onFailure(Call<Format> call, Throwable t) {
                            Log.d("sendData", "fail");
                            block = false;
                        }
                    });
                }
                if (flag == 1 && !block){
                    block = true;
                    Call<Format> call = service.getContinue(game.idOfRoom, game.yourUserCode);
                    call.enqueue(new Callback<Format>() {
                        @Override
                        public void onResponse(Call<Format> call, Response<Format> response) {
                            Format format = response.body();
                            if (format.next){
                                flag++;
                                game.getDataFromFormat(format);
                                startActivity(new Intent(RestActivity.this, MapActivityOnline.class).putExtra("GAME", game));
                            }
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            block = false;
                        }

                        @Override
                        public void onFailure(Call<Format> call, Throwable t) {
                            Log.d("continue", "no");
                            block = false;
                        }
                    });
                }
            }
        }
    };
}